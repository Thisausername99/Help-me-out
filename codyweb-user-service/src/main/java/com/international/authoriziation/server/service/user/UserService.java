package com.international.authoriziation.server.service.user;

import com.international.authoriziation.server.exception.InvalidTokenException;
import com.international.authoriziation.server.exception.ResourceNotFoundException;
import com.international.authoriziation.server.exception.UserAlreadyExistException;
import com.international.authoriziation.server.exception.UserNotVerifiedException;
import com.international.authoriziation.server.model.dto.SignupRequest;
import com.international.authoriziation.server.model.entity.UserEntity;

import java.util.*;
//import com.international.codyweb.exception.InvalidTokenException;
//import com.international.codyweb.exception.ResourceNotFoundException;
//import com.international.codyweb.exception.UserAlreadyExistException;
//import com.international.codyweb.exception.UserNotVerifiedException;
//import com.international.codyweb.web.payload.request.SignupRequest;

public interface UserService {
	List<UserEntity> getUser();
	void register(final SignupRequest signupRequest) throws UserAlreadyExistException;
	boolean checkIfUserExist(final String email);
	void sendRegistrationConfirmationEmail(final UserEntity user) ;
	boolean verifyUser(final String token) throws InvalidTokenException;
	void checkIfUserVerified(final String email) throws UserNotVerifiedException;
}
