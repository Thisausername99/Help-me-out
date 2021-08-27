//package com.international.authoriziation.server.token.service;
//
//import java.time.Instant;
//import java.util.Optional;
//import java.util.UUID;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.international.authoriziation.server.model.repository.UserRepository;
//import com.international.authoriziation.server.token.pojo.RefreshToken;
//import com.international.authoriziation.server.token.repo.RefreshTokenRepository;
//import com.international.codyweb.exception.TokenRefreshException;
//
//@Service
//public class RefreshTokenServiceImpls implements RefreshTokenService {
//  
//	  @Value("${cody.app.jwtRefreshExpirationMs}")
//	  private Long refreshTokenDurationMs;
//	
//	  @Autowired
//	  private RefreshTokenRepository refreshTokenRepository;
//	
//	  @Autowired
//	  private UserRepository userRepository;
//	
//	  
//	  /* Find a RefreshToken based on the natural id i.e the token itself  */
//	  @Override
//	  public Optional<RefreshToken> findByToken(String token) {
//	    return refreshTokenRepository.findByToken(token);
//	  }
//	
//	  
//	  /* Create and return a new Refresh Token */
//	  @Override
//	  public RefreshToken createRefreshToken(Long userId) {
//	    RefreshToken refreshToken = new RefreshToken();
//	
//	    refreshToken.setUser(userRepository.findById(userId).get());
//	    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
//	    refreshToken.setToken(UUID.randomUUID().toString());
//	
//	    refreshToken = refreshTokenRepository.save(refreshToken);
//	    return refreshToken;
//	  }
//  
//  
//  
//	  /* Verify whether the token provided has expired or not. 
//	   * If the token was expired, delete it from database and throw TokenRefreshException 
//	   */
//	  @Override
//	  public RefreshToken verifyExpiration(RefreshToken token) {
//	    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
//	      refreshTokenRepository.delete(token);
//	      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
//	    }
//	
//	    return token;
//	  }
//
//	  @Transactional
//	  @Override
//	  public int deleteByUserId(Long userId) {
//	    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
//	  }
//}