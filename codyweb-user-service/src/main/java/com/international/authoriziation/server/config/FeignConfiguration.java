//package com.international.authoriziation.server.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import feign.RequestInterceptor;
//
//@Configuration
//public class FeignConfiguration {
//	@Bean
//    public RequestInterceptor requestTokenBearerInterceptor() {
//        return requestTemplate -> {
//            JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext()
//                    .getAuthentication();
//
//            requestTemplate.header("Authorization", "Bearer " + token.getToken().getTokenValue());
//        };
//    }
//}