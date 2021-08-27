package com.international.authoriziation.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@EnableZuulProxy
@SpringBootApplication
//@EnableEurekaServer
@EnableEurekaClient
public class CodywebUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodywebUserServiceApplication.class, args);
	}

}
