package com.international.codyweb.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.international.codyweb.model.entity.UserEntity;
import com.international.codyweb.model.repository.UserRepository;


@Configuration
public class StartUpConfig {
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
			UserEntity adam = UserEntity.builder()
					.email("test@gmail.com")
					.password("1234")
					.username("sheesh")
					.build();
//			
			
			
			//pre insert role into db
			userRepository.save(adam);
			
		};
		
	}
}
