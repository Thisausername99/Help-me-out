package com.international.codyweb.security.services;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.international.codyweb.models.User;
import com.international.codyweb.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	//Build user authentication object
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

//		boolean enabled = !customer.isAccountVerified();
//	    UserDetails user = User.withUsername(customer.getEmail())
//	            .password(customer.getPassword())
//	            .disabled(enabled)
//	            .authorities(“USER”).build();

		
		
		return UserDetailsImpl.build(user);
	}	

}
