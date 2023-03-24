package com.mindfire.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindfire.entities.User;
import com.mindfire.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	/**
	 * Loads a User by username.
	 * @param username the username of the User to load
	 * @return a UserDetails object representing the loaded User
	 * @throws UsernameNotFoundException if no User is found with the given username
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Retrieve the User entity with the given username, or throw a
		// UsernameNotFoundException if no entity is found
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		// Build and return a UserDetails object based on the retrieved User entity
		return UserDetailsImpl.build(user);
	}

}
