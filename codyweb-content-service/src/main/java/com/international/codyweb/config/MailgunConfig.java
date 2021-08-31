///**
// * 
// */
//package com.international.codyweb.config;
//
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author Cody Hoang
// *
// */
//@Configuration
//public class MailgunConfig {
//	private String domain;
//	
//    private String apiKey;
//    
//    private String from;
//    
//    private String fromAddress;
//    
//    private String mailgunResource;
//	
//    @Bean
//    public net.sargue.mailgun.Configuration mailgunConfiguration(){
//        net.sargue.mailgun.Configuration configuration = new net.sargue.mailgun.Configuration()
//                .from(mailgunConfigProperties.getFrom(), mailgunConfigProperties.getFromAddress())
//                .apiKey(mailgunConfigProperties.getApiKey())
//                .domain(mailgunConfigProperties.getDomain());
//        return configuration;
//    }
//}
