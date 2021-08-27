/**
 * 
 */
package com.international.codyweb.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Cody Hoang
 *
 */
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Optional <Member> findByUsername(String username);
	Optional <Member> findByEmail(String email);


}
