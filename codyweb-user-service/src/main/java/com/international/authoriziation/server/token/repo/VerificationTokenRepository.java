package com.international.authoriziation.server.token.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.international.authoriziation.server.token.pojo.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long>{
	
	VerificationToken findByToken(String token);
	Long removeByToken(String token);
}
