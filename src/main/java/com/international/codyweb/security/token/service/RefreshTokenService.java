package com.international.codyweb.security.token.service;

import java.util.Optional;

import com.international.codyweb.security.token.model.RefreshToken;

public interface RefreshTokenService {
	public int deleteByUserId(Long userId);
	public RefreshToken verifyExpiration(RefreshToken token);
	public RefreshToken createRefreshToken(Long userId);
	public Optional<RefreshToken> findByToken(String token);
}
