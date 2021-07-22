//package com.international.codyweb.security.config;
//
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource({"classpath:application.properties"})
//@EnableJpaRepositories(basePackages = {"com.international.codyweb.repositories"},
//        entityManagerFactoryRef = "primaryEntityManagerFactory",
//        transactionManagerRef= "primaryTransactionManager")
//public class PrimaryDataSourceConfig {
//	
//	
//	public PrimaryDataSourceConfig() {
//		super();
//	}
//	
//	
//	
//	@Primary
//    @Bean(name = "primaryDataSource")
////	@ConfigurationProperties(prefix="spring.datasource")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create()
//                .driverClassName("org.postgresql.Driver")
//                .url("jdbc:postgresql://localhost:5432/post")
//                .username("postgres")
//                .password("cody3399")
//                .build();
//    }
//
//	
//	
//	@Primary
//    @Bean(name = "primaryEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
//	
//		
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(primaryDataSource());
//		em.setPackagesToScan(new String[] { "com.international.codyweb.models" });
//		
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		HashMap<String, Object> properties = new HashMap<>();
//		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		properties.put("hibernate.ddl-auto", "create-drop");
//		em.setJpaPropertyMap(properties);
//
//		return em;
//		
//	    }
//	
//	 @Primary
//	 @Bean
//	 public PlatformTransactionManager primaryTransactionManager() {
//		 	final JpaTransactionManager transactionManager= new JpaTransactionManager();
//	        transactionManager.setEntityManagerFactory(primaryEntityManager().getObject());
//	        return transactionManager;
//	    }
//}