package com.international.authoriziation.server.controller;

import java.util.*;
import java.util.stream.*;

import javax.mail.AuthenticationFailedException;
import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.international.authoriziation.server.auth.UserDetailsImpl;
import com.international.authoriziation.server.exception.UserNotVerifiedException;
import com.international.authoriziation.server.jwt.JwtUtils;
import com.international.authoriziation.server.model.dto.LoginRequest;
import com.international.authoriziation.server.model.dto.MessageResponse;
import com.international.authoriziation.server.model.dto.SignupRequest;
//import com.international.authoriziation.server.model.dto.User;
//import com.international.authoriziation.server.service.KeycloakUserService;
import com.international.authoriziation.server.service.UserService;




//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user/")
public class AuthController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private KeycloakUserService keycloakUserService;
    
	
//	@PostMapping(value = "/register")
//    public ResponseEntity<?> createUser(@RequestBody User request) {
//		System.out.println("Im here");
//        LOG.info("Creating user with {}", request.toString());
//        return ResponseEntity.ok(userService.createUser(request));
//    }
	
	
//	@GetMapping(value = "/all")
//	public ResponseEntity<?> getUser(){
//		LOG.info("Getting all users...");
//		return ResponseEntity.ok(userService.getUser());
//	}
	
//	@GetMapping("/home")
//	public ResponseEntity<?> home(){
//		return ResponseEntity.ok(new MessageResponse("Home"));
//	}
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
//
//	@Autowired
//	private RefreshTokenServiceImpls refreshTokenService;
//
//
//	@Autowired
//	private JwtUtils jwtUtils;
//
//
	@PostMapping("/sign-in")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) throws UserNotVerifiedException {

		//Encapsulate authentication object from login request payload and perform authentication
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		//get the current security context object then set up the new authentication object (username, pw)
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	

		//check if user has been verified
		try {
			userService.checkIfUserVerified(userDetails.getUsername());
			
		} catch (Exception e) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: " + e.getMessage()));	
		}
//
//
//		String jwt = jwtUtils.generateJwtToken(authentication);
//
//		List<String> roles = Optional.ofNullable(userDetails.getAuthorities()).orElseGet(Collections::emptyList).stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//
//		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
//
//		//Store user id for later use in parameter
//		
//		
//		request.getSession().setAttribute("userId", userDetails.getId());
//		request.getSession().setAttribute("userName", userDetails.getUsername());
//		
//
//
		return ResponseEntity.ok(userDetails.getUsername());
	}
//
	@PostMapping("/sign-up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

		try {
			userService.register(signupRequest);
		}
		catch (Exception e) {
			//			e.printStackTrace();
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: " + e.getMessage()));
		}
	
		return ResponseEntity.ok(new MessageResponse("User registered"));
	}
//
//
//	@PostMapping(value="/confirm-account")
//	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String verificationToken){
//		//        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
//		try {
//			userService.verifyUser(verificationToken);
//		} catch (Exception e) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Token is invalid!"));
//			//          redirAttr.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.invalid.token", null,LocaleContextHolder.getLocale()));
//			//          return REDIRECT_LOGIN;
//		}
//
//		//      redirAttr.addFlashAttribute("verifiedAccountMsg", messageSource.getMessage("user.registration.verification.success", null,LocaleContextHolder.getLocale()));
//		//      return REDIRECT_LOGIN;
//
//		//        if(token != null){
//		//            User user = userService.findUserByEmail(token.getUser().getEmail()).
//		//            		orElseThrow(() -> new ResourceNotFoundException("user not found with token" + token));
//		//            user.setEnabled(true);
//		//            userService.saveUser(user);        
//		//        }
//		//        else {
//		//        	return ResponseEntity
//		//					.badRequest()
//		//					.body(new MessageResponse("Error: Token is invalid!"));
//		////        	modelAndView.addObject("message","The link is invalid or broken!");
//		////          modelAndView.setViewName("error");
//		//        }
//
//		return ResponseEntity.ok(new MessageResponse("User successfully verified!"));
//	}
//
//
//
//
//	@PostMapping("/refreshtoken")
//	public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
//		String requestRefreshToken = request.getRefreshToken();
//
//		return refreshTokenService.findByToken(requestRefreshToken)
//				.map(refreshTokenService::verifyExpiration)
//				.map(RefreshToken::getUser)
//				.map(user -> {
//					String token = jwtUtils.generateTokenFromUsername(user.getUsername());
//					return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
//				})
//				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
//						"Refresh token is not in database!"));
//	}

}