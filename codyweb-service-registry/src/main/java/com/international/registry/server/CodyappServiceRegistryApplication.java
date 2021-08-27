package com.international.registry.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CodyappServiceRegistryApplication {
	
//	@Value("${server.port}")
//	private static String loginUrl;
	
	public static void main(String[] args) {
//		System.out.println(loginUrl);
		SpringApplication.run(CodyappServiceRegistryApplication.class, args);
		
	}

}
