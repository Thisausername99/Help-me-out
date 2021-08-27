//package com.international.codyweb.config;
//
//import java.util.List;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.international.authoriziation.server.role.ERole;
//import com.international.authoriziation.server.role.Role;
//import com.international.authoriziation.server.role.RoleRepository;
//import com.international.authoriziation.server.user.User;
//import com.international.authoriziation.server.user.UserRepository;
//
//@Configuration
////@Transactional("primaryTransactionManager")
//public class StartupConfig {
//	// this to set up fake data before application finishes running
//	
//	
//	
//	@Bean
//	CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository) {
//		return args -> {
//			Role user = new Role (ERole.ROLE_USER);
//			Role mod = new Role (ERole.ROLE_MODERATOR);
//			Role admin = new Role (ERole.ROLE_ADMIN);
////			User template = new User();
//			
//			//pre insert role into db
//			roleRepository.saveAll(
//					List.of(user, mod ,admin));
//			
//		};
//		
//	}
//}
