package com.international.codyweb;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing

public class CodyWebApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(CodyWebApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CodyWebApplication.class, args);
//		System.out.println("Let's inspect the beans provided by Spring Boot:");
//		  
//		String[] beanNames = ctx.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//		    System.out.println(beanName);
//		  }
	}
	
	
//	 public static void displayAllBeans() {
//	        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
//	        for(String beanName : allBeanNames) {
//	            System.out.println(beanName);
//	        }
//	 }
//	
	

}
