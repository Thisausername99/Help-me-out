package com.international.codyweb.core.user.service;

import com.international.codyweb.core.exception.InvalidTokenException;
import com.international.codyweb.core.exception.ResourceNotFoundException;
import com.international.codyweb.core.exception.UserAlreadyExistException;
import com.international.codyweb.core.user.model.User;
import com.international.codyweb.web.payload.request.SignupRequest;

public interface UserService {
	void register(final SignupRequest signupRequest) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);
    void sendRegistrationConfirmationEmail(final User user);
    boolean verifyUser(final String token) throws InvalidTokenException;
    		//throws InvalidTokenException;
    User getUserById(final Long id) throws ResourceNotFoundException;
    		//throws UnkownIdentifierException;
}
