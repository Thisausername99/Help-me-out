package com.international.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CodyappGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodyappGatewayApplication.class, args);
	}

}
