package com.international.codyweb.controllers;

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


/* For Services and Utils*/
import com.international.codyweb.security.services.UserDetailsImpl;
import com.international.codyweb.security.services.RefreshTokenService;
import com.international.codyweb.security.services.EmailSenderServiceImpl;
import com.international.codyweb.security.services.UserService;
import com.international.codyweb.security.jwt.JwtUtils;


/* For Models */
import com.international.codyweb.models.ERole;
import com.international.codyweb.models.Role;
import com.international.codyweb.models.User;
import com.international.codyweb.models.VerificationToken;
import com.international.codyweb.models.RefreshToken;


/* For Request */
import com.international.codyweb.payload.request.LoginRequest;
import com.international.codyweb.payload.request.SignupRequest;
import com.international.codyweb.payload.request.TokenRefreshRequest;


/* For Response */
import com.international.codyweb.payload.response.JwtResponse;
import com.international.codyweb.payload.response.MessageResponse;
import com.international.codyweb.payload.response.TokenRefreshResponse;


/* For Repositories */
import com.international.codyweb.repositories.RoleRepository;
import com.international.codyweb.repositories.VerificationTokenRepository;



/* For Exception */
import com.international.codyweb.exception.*;


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
	private RefreshTokenService refreshTokenService;
	
	
	@Autowired
	private EmailSenderServiceImpl emailSenderService;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private VerificationTokenRepository verificationTokenRepository;

  
	

	@PostMapping("/sign-in")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
		
		//Construct authentication object from login request payload and perform authentication
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		//get the current security context object then set up the new authentication object (username, pw)
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
		
		//check if user has been verified
		if (!userService.userValidation(userDetails.getUsername())) {
			System.out.println("Here");
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: User has not been verifed!"));
		}
		
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
			        .collect(Collectors.toList());
		
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
		
		//Store user id for later use in parameter
		request.getSession().setAttribute("currentUser", userDetails.getId());
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 refreshToken.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userService.usernameValidation(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.userEmailValidation(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		// Create new user's account
		// set enable to be false for testing purpose
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			// if no role is set then automatically assign user privilege to the sign up
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else { // if roles are assigned then add the role to the user role's set to set up his/her privilege
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		
		//Step 2
		//generate new verification token
		VerificationToken verificationToken = new VerificationToken(user);
		verificationTokenRepository.save(verificationToken);

		//send the token to user 
//		try {
//			SimpleMailMessage mailMessage = new SimpleMailMessage();
//			mailMessage.setTo(user.getEmail());
//	        mailMessage.setSubject("Complete Registration!");
//	        mailMessage.setFrom("chand312902@gmail.com");
//	        mailMessage.setText("To confirm your account, please click here : "
//	        +"http://localhost:8082/confirm-account?token="+verificationToken.getToken());
//	        emailSenderService.sendEmail(new SimpleMailMessage(), user.getEmail(), verificationToken.getToken());
	        
	        //Save user to db
	        
		
//		}
//		catch (Exception e) {
//			System.out.println(e.toString());
//			
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Can't init mail service!"));
//		}
//		
		user.setRoles(roles);
		LOG.info("Saving the new employee to the redis.");
  		userService.saveUser(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered with token: " + verificationToken.getToken()));
		
  		
	}
	
	
	@PostMapping(value="/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String verificationToken){
        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);

        if(token != null){
            User user = userService.findUserByEmail(token.getUser().getEmail()).
            		orElseThrow(() -> new ResourceNotFoundException("user not found with token" + token));
            user.setEnabled(true);
            userService.saveUser(user);        
        }
        else {
        	return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Token is invalid!"));
//        	modelAndView.addObject("message","The link is invalid or broken!");
//          modelAndView.setViewName("error");
        }

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