/**
 * 
 */
package com.international.codyweb.member;

import java.util.*;

import com.international.codyweb.exception.ResourceNotFoundException;

/**
 * @author Cody Hoang
 *
 */
public interface MemberService {
	List<Member> getAllMember();
	Member getMemberById(Long id) throws ResourceNotFoundException;
}
