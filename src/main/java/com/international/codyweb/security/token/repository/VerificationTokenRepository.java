package com.international.codyweb.security.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.international.codyweb.security.token.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long>{
	
	VerificationToken findByToken(String token);
	Long removeByToken(String token);
}
