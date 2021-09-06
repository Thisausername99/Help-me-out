package com.international.authoriziation.server.service.token;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import com.international.authoriziation.server.token.pojo.VerificationToken;
import com.international.authoriziation.server.token.repo.VerificationTokenRepository;

@Service
public class VerifcationTokenServiceImpls implements VerificationTokenService{
	
	private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");
	
    @Value("${cody.app.verification.token.validity}")
    private int tokenValidityInSeconds;
    
    
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    
    
	@Override
	public VerificationToken createVerificationToken() {
		String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII); // this is a sample, you can adapt as per your security need
        VerificationToken verficationToken = new VerificationToken();
        verficationToken.setToken(tokenValue);
        verficationToken.setExpireAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
        this.saveVerificationToken(verficationToken);
        return verficationToken;
	}

	@Override
	public void saveVerificationToken(VerificationToken token) {
		verificationTokenRepository.save(token);
	}

	@Override
	public VerificationToken findByToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}

	@Override
	public void removeToken(VerificationToken token) {
		verificationTokenRepository.delete(token);
	}

	@Override
	public void removeTokenByToken(String token) {
		verificationTokenRepository.removeByToken(token);
		
	}
	
	public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

}
