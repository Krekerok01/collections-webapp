package com.varvara.service;



import com.varvara.entity.Role;
import com.varvara.entity.User;
import com.varvara.repository.RoleRepository;
import com.varvara.repository.UserRepository;
import com.varvara.dto.UserDataFromInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository,  RoleRepository roleRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
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


	public void save(UserDataFromInput userDataFromInput) {

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


	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}


	public List<User> getAllUsers() {
		return userRepository.findAll();
	}



	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).get();
		if (user == null || user.getStatus().equals("BLOCKED")) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}



	public Role findRoleByName(String name) {

		Optional<Role> role = roleRepository.findRoleByName(name);

		if(!role.isPresent()) {

			throw new UsernameNotFoundException("Role Not Found");
		}

		return role.get();
	}

}


