package com.international.authoriziation.server.service.user;

import com.international.authoriziation.server.exception.UserAlreadyExistException;
import com.international.authoriziation.server.exception.UserNotVerifiedException;
import com.international.authoriziation.server.model.dto.LoginRequest;
import com.international.authoriziation.server.model.entity.UserEntity;
import java.util.*;


public interface UserService {
	List<UserEntity> getUser();
	void userSetup(final LoginRequest loginRequest) throws UserAlreadyExistException;
	boolean checkIfUserExist(final String email);
//	void sendRegistrationConfirmationEmail(final UserEntity user) ;
//	boolean verifyUser(final String token) throws InvalidTokenException;
//	void checkIfUserVerified(final String email) throws UserNotVerifiedException;
}
