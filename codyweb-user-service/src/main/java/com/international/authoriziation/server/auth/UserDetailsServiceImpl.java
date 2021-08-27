package com.international.authoriziation.server.auth;


import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.international.authoriziation.server.model.entity.UserEntity;
import com.international.authoriziation.server.model.repository.UserRepository;
//import com.international.authoriziation.server.role.Role;
import com.international.authoriziation.server.role.Role;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	//Build user authentication object
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//		System.out.println(username);
		UserEntity user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

		// we can use this in case we want to activate account after customer verified the account
		//		boolean enabled = !user.isAccountVerified();		
		UserDetails userDetail = UserDetailsImpl.withUsername(user.getEmail())
				.id(user.getId())
				.password(user.getPassword())
				.disabled(user.isLoginDisabled())
				.authorities(getAuthorities(user)).build();

		return userDetail;
	}

	private Collection <GrantedAuthority> getAuthorities(UserEntity user) {
		Set <Role> userRoles = user.getRoles();
		//	        if (userRoles.isEmpty()) {
		//	        	throw new Exception("User has no role");
		//	        }
		Collection<GrantedAuthority> authorities = new ArrayList<>(userRoles.size());
		for(Role userRole : userRoles){
			authorities.add(new SimpleGrantedAuthority( (String) userRole.getName().name()));
		}

		return authorities;
	}



}
