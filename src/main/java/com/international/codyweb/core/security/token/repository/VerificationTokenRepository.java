package com.international.codyweb.core.security.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.international.codyweb.core.security.token.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long>{
	
	VerificationToken findByToken(String token);
	Long removeByToken(String token);
}
