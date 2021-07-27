package com.international.codyweb.user;

import java.util.*;
import org.apache.commons.lang3.StringUtils;


import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
//import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.international.codyweb.email.context.AccountVerificationEmailContext;
import com.international.codyweb.email.service.EmailService;
import com.international.codyweb.exception.InvalidTokenException;
import com.international.codyweb.exception.ResourceNotFoundException;
import com.international.codyweb.exception.UserAlreadyExistException;
import com.international.codyweb.exception.UserNotVerifiedException;
import com.international.codyweb.role.ERole;
import com.international.codyweb.role.Role;
import com.international.codyweb.role.RoleRepository;
import com.international.codyweb.security.token.model.VerificationToken;
import com.international.codyweb.security.token.repository.VerificationTokenRepository;
import com.international.codyweb.security.token.service.VerificationTokenService;
import com.international.codyweb.web.payload.request.SignupRequest;

@Service
@Transactional
public class UserServiceImpls implements UserService {

	private final String USER_CACHE = "USER";

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Value("${site.base.url.https}")
	private String baseURL;
	//    
	//    @Autowired
	//    RedisTemplate<String, Object> redisTemplate;
	//    private HashOperations<String, Long, User> hashOperations;




	//    // This annotation makes sure that the method needs to be executed after 
	//    // dependency injection is done to perform any initialization.
	//    @PostConstruct
	//    private void intializeHashOperations() {
	//        hashOperations = redisTemplate.opsForHash();
	//    }

	//save to redis first then db
	//    public void saveUser(final User user) {
	//        userRepository.save(user);
	//    }
	//        

	//    public User findUserById(final Long id) {
	//       User usr =  (User) hashOperations.get(USER_CACHE, id);
	//       if (usr == null) {
	//    	   return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: User is not found."));
	//       }
	//       return usr;
	//    }

	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User is not found with email: " + email));
	}


	public User findUserByUsername(final String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User is not found with username: " + username)); 
	}

	// Find all employees' operation.
	//    public Map<Long, User> findAllUser() {
	//        return hashOperations.entries(USER_CACHE);
	//    }

	// Delete employee by id operation.
	public void delete(Long id) {
		//        hashOperations.delete(USER_CACHE, id);
		userRepository.deleteById(id);
	}

	@Override
	public void register(SignupRequest signupRequest) throws UserAlreadyExistException{
		if(checkIfUserExist(signupRequest.getEmail())){
			throw new UserAlreadyExistException("User already exists for this email");
		}
		User userEntity = new User();
		BeanUtils.copyProperties(signupRequest, userEntity);
		encodePassword(signupRequest, userEntity);
		updateUserRoles(userEntity);
		userRepository.save(userEntity);
		//        System.out.println("GOT HERE");
		sendRegistrationConfirmationEmail(userEntity);

	}


	private void updateUserRoles(User user){ 
		Role userRole= roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));;
		user.addUserRoles(userRole);
	}


	@Override
	public boolean checkIfUserExist(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void sendRegistrationConfirmationEmail(User user) {
		VerificationToken verificationToken = verificationTokenService.createVerificationToken();
		verificationToken.setUser(user);

		//		System.out.println(user.getEmail());
		verificationTokenRepository.save(verificationToken);
		AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
		emailContext.init(user);
		emailContext.setToken(verificationToken.getToken());
		emailContext.buildVerificationUrl(baseURL, verificationToken.getToken());
		System.out.println(verificationToken.getToken());
		//        try {
		//        	System.out.println("Stuck here");
		//            emailService.sendMail(emailContext);
		//        } catch (MessagingException e) {
		//        	
		//            e.printStackTrace();
		//        }

	}



	//	@Async
	@Override
	public boolean verifyUser(String token) throws InvalidTokenException {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		if(Objects.isNull(verificationToken) || !StringUtils.equals(token, verificationToken.getToken()) || verificationToken.isExpired()){
			throw new InvalidTokenException("Token is not valid");
		}
		Optional <User> userOptional = userRepository.findById(verificationToken.getUser().getId());

		if(userOptional.isEmpty()){
			//        	throw new ResourceNotFoundException("User not exists for given token");
			return false;
		}

		User userEntity = userOptional.get();
		userEntity.setAccountVerified(true);

		userRepository.save(userEntity); // let's same user details

		// we don't need invalid password now
		verificationTokenRepository.delete(verificationToken);
		return true;
	}



	@Override
	public User getUserById(Long id) throws ResourceNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: User is not found."));
	}


	private void encodePassword(SignupRequest source, User target){
		target.setPassword(passwordEncoder.encode(source.getPassword()));
	}

	@Override
	public void checkIfUserVerified(String email) throws UserNotVerifiedException {
		User userEntity = findUserByEmail(email);
		if (!userEntity.isAccountVerified()) {
			throw new UserNotVerifiedException("User is not verified");
		}
	}




}
