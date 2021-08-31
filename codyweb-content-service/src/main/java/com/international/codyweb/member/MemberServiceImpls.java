/**
 * 
 */
package com.international.codyweb.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.international.codyweb.exception.ResourceNotFoundException;

/**
 * @author Cody Hoang
 *
 */
@Service
public class MemberServiceImpls implements MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public List<Member> getAllMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member getMemberById(Long id) {
		Member memberEntity = memberRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Member not found with id "+ id));
		
		return memberEntity;
	}

	
}
