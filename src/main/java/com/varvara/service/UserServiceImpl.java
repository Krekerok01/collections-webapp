package com.varvara.service;



import com.varvara.entity.Collection;
import com.varvara.entity.OtherField;
import com.varvara.entity.Role;
import com.varvara.entity.User;
import com.varvara.repository.RoleRepository;
import com.varvara.repository.UserRepository;
import com.varvara.dto.UserDataFromInput;
import com.varvara.service.interfaces.CollectionService;
import com.varvara.service.interfaces.OtherFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.NonUniqueResultException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserName;

@Service
public class UserServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

	private Logger logger = Logger.getLogger(getClass().getName());

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private OtherFieldService otherFieldService;
	private CollectionService collectionService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, @Lazy BCryptPasswordEncoder passwordEncoder, OtherFieldService otherFieldService, @Lazy CollectionService collectionService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.otherFieldService = otherFieldService;
		this.collectionService = collectionService;
	}


	public User findById(int id){
		Optional<User> user = userRepository.findById(id);

		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User Not Found");
		}

		return user.get();
	}


	public User findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);

		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User Not Found");
		}

		return user.get();
	}


	public void saveUserDataFromInput(UserDataFromInput userDataFromInput) {

		User user = new User();

		user.setUsername(userDataFromInput.getUsername());
		user.setPassword(passwordEncoder.encode(userDataFromInput.getPassword()));
		user.setFirstName(userDataFromInput.getFirstName());
		user.setLastName(userDataFromInput.getLastName());
		user.setEmail(userDataFromInput.getEmail());

		Role role = roleRepository.findRoleByName("ROLE_USER").get();
		user.setRoles(Arrays.asList(role));

		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setStatus("ACTIVE");


		userRepository.save(user);
	}

	public void addUserToAdmins(String username){
		User user = findByUsername(username);

		List<Role> roles = (List<Role>) user.getRoles();
		if (roles.size() == 1){
			roles.add(findRoleByName("ROLE_ADMIN"));
		}

		user.setRoles(roles);
		saveUser(user);
	}

	public void removeUserFromAdmins(String username){

		User user = findByUsername(username);
		List<Role> roles = new ArrayList<>();

		roles.add(findRoleByName("ROLE_USER"));
		user.setRoles(roles);

		saveUser(user);
	}

	public void blockUser(String username){
		User user = findByUsername(username);
		user.setAccountNonLocked(false);
		saveUser(user);
	}

	public void unblockUser(String username){
		User user = findByUsername(username);
		user.setAccountNonLocked(true);
		saveUser(user);
	}


	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}


	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	public String getStringOfUserRoles(User user){
		List<Role> roles = (List<Role>) user.getRoles();

		String rolesString = "";

		for (Role r: roles){
			rolesString += r.getName() + ", ";
		}
		return rolesString.substring(0, rolesString.length() - 2);
	}


	public Role findRoleByName(String name) {
		Optional<Role> role = roleRepository.findRoleByName(name);

		if(!role.isPresent()) {
			throw new UsernameNotFoundException("Role Not Found");
		}

		return role.get();
	}

	public void saveCollectionToTheUser(Collection collection,
										String firstAdditionalStringType, String firstAdditionalStringName, String secondAdditionalStringType, String secondAdditionalStringName, String thirdAdditionalStringType, String thirdAdditionalStringName,
										String firstAdditionalIntegerType, String firstAdditionalIntegerName, String secondAdditionalIntegerType, String secondAdditionalIntegerName, String thirdAdditionalIntegerType, String thirdAdditionalIntegerName,
										String firstAdditionalMultilineTextType, String firstAdditionalMultilineTextName, String secondAdditionalMultilineTextType, String secondAdditionalMultilineTextName, String thirdAdditionalMultilineTextType, String thirdAdditionalMultilineTextName,
										String firstAdditionalCheckboxType, String firstAdditionalCheckboxName, String secondAdditionalCheckboxType, String secondAdditionalCheckboxName, String thirdAdditionalCheckboxType, String thirdAdditionalCheckboxName,
										String firstAdditionalDateType, String firstAdditionalDateName, String secondAdditionalDateType, String secondAdditionalDateName, String thirdAdditionalDateType, String thirdAdditionalDateName){
		User user = findByUsername(authenticationUserName);
		List<Collection> userCollections = user.getCollections();

		userCollections.add(collection);
		saveUser(user);

		try {
			otherFieldService.saveCollection(collectionService.getCollectionByNameAndUserId(collection.getName(), user.getId()),
					firstAdditionalStringType, firstAdditionalStringName, secondAdditionalStringType, secondAdditionalStringName, thirdAdditionalStringType, thirdAdditionalStringName,
					firstAdditionalIntegerType, firstAdditionalIntegerName, secondAdditionalIntegerType, secondAdditionalIntegerName, thirdAdditionalIntegerType, thirdAdditionalIntegerName,
					firstAdditionalMultilineTextType, firstAdditionalMultilineTextName, secondAdditionalMultilineTextType, secondAdditionalMultilineTextName, thirdAdditionalMultilineTextType, thirdAdditionalMultilineTextName,
					firstAdditionalCheckboxType, firstAdditionalCheckboxName, secondAdditionalCheckboxType, secondAdditionalCheckboxName, thirdAdditionalCheckboxType, thirdAdditionalCheckboxName,
					firstAdditionalDateType, firstAdditionalDateName, secondAdditionalDateType, secondAdditionalDateName, thirdAdditionalDateType, thirdAdditionalDateName);
		} catch (NonUniqueResultException | IncorrectResultSizeDataAccessException e){
			logger.warning(e.getMessage());
		}
	}


	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optionalUser = userRepository.findByUsername(username);
		isOptionalUserPresent(optionalUser);

		User user = optionalUser.get();
		checkUserСondition(user);

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private java.util.Collection<? extends GrantedAuthority> mapRolesToAuthorities(java.util.Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	private void isOptionalUserPresent(Optional<User> optionalUser){
		if (!optionalUser.isPresent()){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	private void checkUserСondition(User user){
		if (user == null || user.getStatus().equals("BLOCKED")) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}
}


