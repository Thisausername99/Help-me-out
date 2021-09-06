package com.international.authoriziation.server.service.token;

import com.international.authoriziation.server.token.pojo.VerificationToken;

public interface VerificationTokenService {
	VerificationToken createVerificationToken();
    void saveVerificationToken(final VerificationToken token);
    VerificationToken findByToken(final String token);
    void removeToken(final VerificationToken token);
    void removeTokenByToken(final String token);
}
