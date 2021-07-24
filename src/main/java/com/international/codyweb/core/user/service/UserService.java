package com.international.codyweb.core.user.service;

import com.international.codyweb.core.user.model.User;
import com.international.codyweb.web.payload.request.SignupRequest;

public interface UserService {
	void register(final SignupRequest signupRequest);
    boolean checkIfUserExist(final String email);
    void sendRegistrationConfirmationEmail(final User user);
    boolean verifyUser(final String token) throws Exception;
    		//throws InvalidTokenException;
    User getUserById(final Long id) ;
    		//throws UnkownIdentifierException;
}
