//package com.international.authoriziation.server.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
////import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.session.HttpSessionEventPublisher;
////import org.springframework.stereotype.Component;
//
//import com.international.authoriziation.server.auth.UserDetailsServiceImpl;
//import com.international.authoriziation.server.jwt.AuthEntryPointJwt;
//import com.international.authoriziation.server.jwt.AuthTokenFilter;
//
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//
//
//@Configuration
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//@EnableGlobalMethodSecurity(
//		// securedEnabled = true,
//		// jsr250Enabled = true,
//		prePostEnabled = true)
//
//public class WebSecurityConfig {
//	//	@Autowired
//	//	UserDetailsServiceImpl userDetailsService;
//	//
//	//	@Autowired
//	//	private AuthEntryPointJwt unauthorizedHandler;
//	//
//	//
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	//
//	//
//	//	@Bean
//	//	public AuthTokenFilter authenticationJwtTokenFilter() {
//	//		return new AuthTokenFilter();
//	//	}
//	//
//	//
//	//
//	//	/**
//	//	 * DAO authentication provider. This authentication provider will authenticate the user with the help of
//	//	 * @UserdetailsService. This is based on the validating the user with the username and password.
//	//	 * @return
//	//	 */
//	//	@Bean
//	//	public DaoAuthenticationProvider authProvider() {
//	//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//	//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//	//		authenticationProvider.setUserDetailsService(userDetailsService);
//	//		return authenticationProvider;
//	//	}
//	//
//	//
//	//	@Override
//	//	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//	//		authenticationManagerBuilder.authenticationProvider(authProvider());
//	//		//userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	//	}
//	//
//	//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	//
//	//
//	//	@Bean
//	//	@Override
//	//	public AuthenticationManager authenticationManagerBean() throws Exception {
//	//		return super.authenticationManagerBean();
//	//	}
//	//
//	//
//	//
//	@Bean
//	public SessionRegistry sessionRegistry() {
//		SessionRegistry sessionRegistry = new SessionRegistryImpl();
//		return sessionRegistry;
//	}
//	//
//	//
//	//	/**
//	//	 * We need this bean for the session management. Specially if we want to control the concurrent session-control support
//	//	 * with Spring security.
//	//	 * @return
//	//	 */
//	//	@Bean
//	//	public HttpSessionEventPublisher httpSessionEventPublisher(){
//	//		return new HttpSessionEventPublisher();
//	//	}
//	//
//	//
//	//
//	//
//	//
//	//	@Override
//	//	protected void configure(HttpSecurity http) throws Exception {
//	//		http.cors().and().csrf().disable()
//	//		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//	//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//	//		.authorizeRequests()								
//	//		.antMatchers("/api/post/**").permitAll()		//.antMatchers(HttpMethod.POST,"/api/post")
//	//		.antMatchers("/api/auth/**").permitAll()        //.antMatchers("/swagger-ui/**").permitAll()
//	//		.antMatchers("/api/test/**").permitAll()
//	//		.antMatchers("/console/**").permitAll()
//	//		.anyRequest().authenticated();
//	////		.oauth2ResourceServer().jwt();
//	//
//	//		http.headers().frameOptions().disable();
//	//		//		  http
//	//		//	        .logout(logout -> logout                                                
//	//		//	            .logoutUrl("/my/logout")                                            
//	//		//	            .logoutSuccessUrl("/home")                                      
//	//		//	            .logoutSuccessHandler(logoutSuccessHandler)                         
//	//		//	            .invalidateHttpSession(true)                                        
//	//		//	            .addLogoutHandler(logoutHandler)                                    
//	//		//	            .deleteCookies(cookieNamesToClear)                                  
//	//		//	        );
//	//
//	//		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//	//	}
//	//	@Override
//	//	protected void configure(HttpSecurity http) throws Exception {
//	//	        http.authorizeRequests()
//	//	                .anyRequest().authenticated()
//	//	                .and()	
//	//	                .oauth2Login()
//	//	                .and()
//	//	                .oauth2ResourceServer()
//	//	                .jwt();
//	//	    }
//	
//	
//	
//	@Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                //ALLOWING REGISTER API FOR DIRECT ACCESS
//                .pathMatchers("/api/auth/register").permitAll()
//                //ALL OTHER APIS ARE AUTHENTICATED
//                .anyExchange().authenticated()
//                .and()
//                .csrf().disable()
//                .oauth2Login()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//        return http.build();
//    }
//
//}