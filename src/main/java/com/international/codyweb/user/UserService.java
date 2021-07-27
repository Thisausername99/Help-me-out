package com.international.codyweb.user;

import com.international.codyweb.exception.InvalidTokenException;
import com.international.codyweb.exception.ResourceNotFoundException;
import com.international.codyweb.exception.UserAlreadyExistException;
import com.international.codyweb.exception.UserNotVerifiedException;
import com.international.codyweb.web.payload.request.SignupRequest;

public interface UserService {
	void register(final SignupRequest signupRequest) throws UserAlreadyExistException;
	boolean checkIfUserExist(final String email);
	void sendRegistrationConfirmationEmail(final User user);
	boolean verifyUser(final String token) throws InvalidTokenException;
	//throws InvalidTokenException;
	boolean checkIfUserVerified(final String email) throws UserNotVerifiedException;
	User getUserById(final Long id) throws ResourceNotFoundException;
	//throws UnkownIdentifierException;
}
