package com.international.authoriziation.server.service.user;

import java.util.*;
//import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;


//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.international.authoriziation.server.exception.InvalidTokenException;
import com.international.authoriziation.server.exception.ResourceNotFoundException;
import com.international.authoriziation.server.exception.UserAlreadyExistException;
import com.international.authoriziation.server.exception.UserNotVerifiedException;
import com.international.authoriziation.server.model.dto.EmailDto;
import com.international.authoriziation.server.model.dto.LoginRequest;
import com.international.authoriziation.server.model.dto.SignupRequest;
import com.international.authoriziation.server.model.mapper.UserMapper;
//import com.international.authoriziation.server.model.entity.User;
import com.international.authoriziation.server.model.repository.UserRepository;
import com.international.authoriziation.server.role.ERole;
import com.international.authoriziation.server.role.Role;
import com.international.authoriziation.server.role.RoleRepository;
import com.international.authoriziation.server.service.token.VerificationTokenService;
import com.international.authoriziation.server.token.pojo.VerificationToken;
import com.international.authoriziation.server.util.broker.RabbitProducer;
//import com.international.codyweb.util.AccountVerificationEmailContext;
//import com.international.codyweb.service.EmailService;
import com.international.authoriziation.server.model.entity.UserEntity;

//import com.international.codyweb.util.RedisUtil;
//import com.international.codyweb.web.payload.request.SignupRequest;

@Service
@Transactional
public class UserServiceImpls implements UserService {

	//	private final String USER_ = "USER_";
	//
	//	private final String TABLE_USER = "TABLE_USER";

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpls.class);

	private UserMapper userMapper = new UserMapper();


	@Autowired	
	private RabbitProducer rabbitProducer;

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private RoleRepository roleRepository;

	@Autowired
	private VerificationTokenService verificationTokenService;

//	@Autowired
//	private PasswordEncoder passwordEncoder;


	@Override
	public List<UserEntity> getUser() {
		return userRepository.findAll();
	}



	//	@Autowired
	//	private RedisUtil<User> userRedisUtil;

	
	//    @Autowired
	//    RedisTemplate<String, Object> redisTemplate;
	//    private HashOperations<String, Long, User> hashOperations;




	//    // This annotation makes sure that the method needs to be executed after 
	//    // dependency injection is done to perform any initialization.
	//    @PostConstruct
	//    private void intializeHashOperations() {
	//        hashOperations = redisTemplate.opsForHash();
	//    }
   

	public UserEntity findUserByEmail(final String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User is not found with email: " + email));
	}


	public UserEntity findById(Long id) throws ResourceNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: User is not found."));
	}
	// Find all employees' operation.
	//    public Map<Long, User> findAllUser() {
	//        return hashOperations.entries(USER_CACHE);
	//    }

	// Delete employee by id operation.
	//	public void delete(Long id) {
	//		//        hashOperations.delete(USER_CACHE, id);
	//		userRepository.deleteById(id);
	//	}

	@Override
	public void userSetup(LoginRequest loginDto) throws UserAlreadyExistException {
		if(checkIfUserExist(loginDto.getEmail())){
			LOG.info("user " + loginDto.getEmail() + " exist!");
			return;
		}

		UserEntity user = userMapper.convertToEntity(loginDto, new UserEntity());
		userRepository.save(user);
		LOG.info("user saved!");
	}


//	private void updateUserRoles(UserEntity user){ 
//		Role userRole= roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));;
//		user.addUserRoles(userRole);
//	}


	@Override
	public boolean checkIfUserExist(String email) {
		return userRepository.existsByEmail(email);
	}

//	@Override
//	public void sendRegistrationConfirmationEmail(UserEntity user) {
//		VerificationToken verificationToken = verificationTokenService.createVerificationToken();
//		verificationToken.setUser(user);
//
//		//		System.out.println(user.getEmail());
//		verificationTokenService.saveVerificationToken(verificationToken);
//		EmailDto emailDto = EmailDto.builder()
//				.email(user.getEmail())
//				.token(verificationToken.getToken())
//				.username(user.getUsername()).build();
//		try {
//			rabbitProducer.produce(emailDto);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}



	//	@Async
//	@Override
//	public boolean verifyUser(String token) throws InvalidTokenException {
//		VerificationToken verificationToken = verificationTokenService.findByToken(token);
//		if(Objects.isNull(verificationToken) || !StringUtils.equals(token, verificationToken.getToken()) || verificationToken.isExpired()){
//			throw new InvalidTokenException("Token is not valid");
//		}
//		try {
//			UserEntity user = findById(verificationToken.getUser().getId());
//			user.setAccountVerified(true);
//
//
//			
//			userRepository.save(user); // let's same user details
//
//			// we don't need invalid password now
//			verificationTokenService.removeToken(verificationToken);
//			//		try {
//			//			userRedisUtil.putMap(TABLE_USER, USER_ + userEntity.getId(), userEntity);
//			//			userRedisUtil.setExpire(TABLE_USER, 2, TimeUnit.MINUTES);
//			//		} catch (Exception e) {
//			//			System.out.println(e.getCause());
//			//		}
//
//
//			return true;
//		}
//		catch (ResourceNotFoundException e) {
//			return false;
//		}
//	}





//	@Override
//	public void checkIfUserVerified(String email) throws UserNotVerifiedException {
//		try {
//			UserEntity userEntity = findUserByEmail(email);
//			if (!userEntity.isAccountVerified()) {
//				throw new UserNotVerifiedException("User is not verified");
//			}
//		} catch (ResourceNotFoundException e){
//			e.getCause();
//		}
//
//	}




}
