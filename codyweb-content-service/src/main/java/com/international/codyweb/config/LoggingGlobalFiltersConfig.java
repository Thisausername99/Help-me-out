///**
// * 
// */
//package com.international.codyweb.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.publisher.Mono;
///**
// * @author Cody Hoang
// *
// */
//@Configuration
//public class LoggingGlobalFiltersConfig {
//
//    final Logger logger =
//      LoggerFactory.getLogger(
//        LoggingGlobalFiltersConfig.class);
//
//    @Bean
//    public GlobalFilter postGlobalFilter() {
//        return (exchange, chain) -> {
//            return chain.filter(exchange)
//              .then(Mono.fromRunnable(() -> {
//                  logger.info("Global Post Filter executed");
//              }));
//        };
//    }
//}