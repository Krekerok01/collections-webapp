package com.varvara.service;



import com.varvara.entity.Role;
import com.varvara.entity.User;
import com.varvara.repository.RoleRepository;
import com.varvara.repository.UserRepository;
import com.varvara.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;



	@Transactional
	public User findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);

		if(!user.isPresent()) {

			throw new UsernameNotFoundException("User Not Found");
		}
		// passwordEncoder.matches("123", user.get().getPassword());
		User user1 = user.get();

		return user1;
	}



	@Transactional
	public void save(CrmUser crmUser) {

		User user = new User();

		user.setUsername(crmUser.getUsername());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());

		Role role = roleRepository.findRoleByName("ROLE_USER").get();
		user.setRoles(Arrays.asList(role));

		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setStatus("ACTIVE");


		userRepository.save(user);
	}


	@Transactional
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Transactional
	public void delete(int id) {
		userRepository.deleteById(id);
	}


	@Transactional
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	@Override
	//@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if(!user.isPresent()) {

			throw new UsernameNotFoundException("User Not Found");
		}

		User user1 = user.get();
		return user1;
	}


}


