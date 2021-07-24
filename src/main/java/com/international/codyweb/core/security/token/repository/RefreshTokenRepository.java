package com.international.codyweb.core.security.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.international.codyweb.core.security.token.model.RefreshToken;
import com.international.codyweb.core.user.model.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);
    
    
    @Modifying
    int deleteByUser(User user);

}