package com.international.codyweb.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.international.codyweb.core.user.ERole;
import com.international.codyweb.core.user.model.Role;
import com.international.codyweb.core.user.model.User;
import com.international.codyweb.core.user.repository.RoleRepository;
import com.international.codyweb.core.user.repository.UserRepository;

@Configuration
//@Transactional("primaryTransactionManager")
public class StartupConfig {
	// this to set up fake data before application finishes running
	
	
	
	@Bean
	CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
		return args -> {
			Role user = new Role (ERole.ROLE_USER);
			Role mod = new Role (ERole.ROLE_MODERATOR);
			Role admin = new Role (ERole.ROLE_ADMIN);
			
			
			//pre insert role into db
			roleRepository.saveAll(
					List.of(user, mod ,admin));
			
		};
		
	}
}
