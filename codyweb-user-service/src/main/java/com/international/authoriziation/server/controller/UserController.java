package com.international.authoriziation.server.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import com.international.authoriziation.server.auth.UserDetailsImpl;
import com.international.authoriziation.server.exception.UserNotVerifiedException;
//import com.international.authoriziation.server.jwt.JwtUtils;
import com.international.authoriziation.server.model.dto.LoginRequest;
import com.international.authoriziation.server.model.dto.MessageResponse;
import com.international.authoriziation.server.service.user.UserService;




//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	

	
	@PostMapping("/sign-in")
	//@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, @AuthenticationPrincipal Jwt principal
	public ResponseEntity<?> authenticateUser(@AuthenticationPrincipal Jwt principal) throws UserNotVerifiedException {
		LoginRequest loginDto = LoginRequest.builder()
							.email(principal.getClaimAsString("preferred_username"))
							.authId(principal.getClaimAsString("subject"))
							.verify(principal.getClaimAsBoolean("email_verified"))
							.build();
		try {
			LOG.info("setting up...");
			userService.userSetup(loginDto);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(new MessageResponse("User logged in!"));
	}

	
//	@PostMapping("/sign-up")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
//
//		try {
//			userService.register(signupRequest);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(new MessageResponse("User registered"));
//	}

	
	
//	@PostMapping(value="/confirm-account")
//	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String verificationToken){
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