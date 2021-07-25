package com.international.codyweb.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.AuthenticationFailedException;
import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.international.codyweb.web.payload.request.LoginRequest;
import com.international.codyweb.web.payload.request.SignupRequest;
import com.international.codyweb.web.payload.request.TokenRefreshRequest;
import com.international.codyweb.web.payload.response.JwtResponse;
import com.international.codyweb.web.payload.response.MessageResponse;
import com.international.codyweb.web.payload.response.TokenRefreshResponse;
import com.international.codyweb.core.email.service.EmailSenderServiceImpl;
import com.international.codyweb.core.exception.*;
import com.international.codyweb.core.security.jwt.JwtUtils;
import com.international.codyweb.core.security.services.UserDetailsImpl;
import com.international.codyweb.core.security.token.model.RefreshToken;
import com.international.codyweb.core.security.token.model.VerificationToken;
import com.international.codyweb.core.security.token.repository.VerificationTokenRepository;
import com.international.codyweb.core.security.token.service.RefreshTokenServiceImpls;
import com.international.codyweb.core.email.service.EmailService;


import com.international.codyweb.core.user.ERole;
import com.international.codyweb.core.user.model.Role;
import com.international.codyweb.core.user.model.User;
import com.international.codyweb.core.user.repository.RoleRepository;
import com.international.codyweb.core.user.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	
	@Autowired
	private RefreshTokenServiceImpls refreshTokenService;
	
	
	@Autowired
	private JwtUtils jwtUtils;

//	@Autowired
//	private PasswordEncoder encoder;
//
//	@Autowired
//	private RoleRepository roleRepository;
//	
//	@Autowired
//    private VerificationTokenRepository verificationTokenRepository;

  
	

//	@PostMapping("/sign-in")
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
//		
//		//Construct authentication object from login request payload and perform authentication
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//		
//		//get the current security context object then set up the new authentication object (username, pw)
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
//		
//		//check if user has been verified
//		if (!userService.userValidation(userDetails.getUsername())) {
//			System.out.println("Here");
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: User has not been verifed!"));
//		}
//		
//		
//		String jwt = jwtUtils.generateJwtToken(authentication);
//		
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//			        .collect(Collectors.toList());
//		
//		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
//		
//		//Store user id for later use in parameter
//		request.getSession().setAttribute("currentUser", userDetails.getId());
//		
//
//		return ResponseEntity.ok(new JwtResponse(jwt, 
//												 refreshToken.getId(), 
//												 userDetails.getUsername(), 
//												 userDetails.getEmail(), 
//												 roles));
//	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
//		if (userService.usernameValidation(signUpRequest.getUsername())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Username is already taken!"));
//		}
//
//		if (userService.userEmailValidation(signUpRequest.getEmail())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Email is already in use!"));
//		}
		
		// Create new user's account
		// set enable to be false for testing purpose
//		User user = new User(signUpRequest.getUsername(), 
//							 signUpRequest.getEmail(),
//							 signUpRequest.getPassword());
		try {
			userService.register(signupRequest);
		}
		catch (Exception e) {
//			e.printStackTrace();
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: " + e.getMessage()));
		}
//		Set<String> strRoles = signUpRequest.getRole();
//		Set<Role> roles = new HashSet<>();
//
//		if (strRoles == null) {
//			// if no role is set then automatically assign user privilege to the sign up
//			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else { // if roles are assigned then add the role to the user role's set to set up his/her privilege
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "admin":
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				case "mod":
//					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
//				default:
//					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}
//		
//		//Step 2
//		//generate new verification token
//		VerificationToken verificationToken = new VerificationToken(user);
//		verificationTokenRepository.save(verificationToken);
//
//		user.setRoles(roles);
//		LOG.info("Saving the new employee to the redis.");
//  		userService.saveUser(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered"));
		
  		
	}
	
	
	@PostMapping(value="/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String verificationToken){
//        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
        try {
            userService.verifyUser(verificationToken);
        } catch (Exception e) {
        	return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Token is invalid!"));
//          redirAttr.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.invalid.token", null,LocaleContextHolder.getLocale()));
//          return REDIRECT_LOGIN;
        }

//      redirAttr.addFlashAttribute("verifiedAccountMsg", messageSource.getMessage("user.registration.verification.success", null,LocaleContextHolder.getLocale()));
//      return REDIRECT_LOGIN;
        
//        if(token != null){
//            User user = userService.findUserByEmail(token.getUser().getEmail()).
//            		orElseThrow(() -> new ResourceNotFoundException("user not found with token" + token));
//            user.setEnabled(true);
//            userService.saveUser(user);        
//        }
//        else {
//        	return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Token is invalid!"));
////        	modelAndView.addObject("message","The link is invalid or broken!");
////          modelAndView.setViewName("error");
//        }

        return ResponseEntity.ok(new MessageResponse("User successfully verified!"));
    }
	
	
	
	
	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();

		return refreshTokenService.findByToken(requestRefreshToken)
							        .map(refreshTokenService::verifyExpiration)
							        .map(RefreshToken::getUser)
							        .map(user -> {
							          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
							          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
							        })
							        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
							            "Refresh token is not in database!"));
	}
	
}