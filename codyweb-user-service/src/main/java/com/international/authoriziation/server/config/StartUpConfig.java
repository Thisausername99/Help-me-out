/**
 * 
 */
package com.international.authoriziation.server.config;

/**
 * @author Cody Hoang
 *
 */
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.Transactional;

import com.international.authoriziation.server.model.entity.UserEntity;
import com.international.authoriziation.server.model.repository.UserRepository;
import com.international.authoriziation.server.role.ERole;
import com.international.authoriziation.server.role.Role;
//import com.international.authoriziation.server.role.Role;
//import com.international.authoriziation.server.role.RoleRepository;
import com.international.authoriziation.server.role.RoleRepository;

@Configuration
//@Transactional("primaryTransactionManager")
public class StartUpConfig {
	// this to set up fake data before application finishes running
	
	
	
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository) {
		return args -> {
			Role user = new Role (ERole.ROLE_USER);
			Role mod = new Role (ERole.ROLE_MODERATOR);
			Role admin = new Role (ERole.ROLE_ADMIN);
//			UserEntity adam= new UserEntity();
			
			
			//pre insert role into db
			roleRepository.saveAll(
					List.of(user,mod,admin));
			
		};
		
	}
}

