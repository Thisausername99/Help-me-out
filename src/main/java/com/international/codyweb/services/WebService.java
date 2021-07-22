//package com.international.codyweb.services;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import com.international.codyweb.models.Post;
//import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
//
//
//@Service
//public class WebService {
//	
//	
//
//	private final PostService postService;
//	
//	@Autowired
//	public WebService(PostService postService) {
//		this.postService = postService;
//	}
//	
//	
//	
//	
//
////	public void validateLogin(String email, String password) throws MemberNotFoundException {
////		try
////        {
////            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
////            Thread.sleep(1000*3);
////        } 
////        catch (InterruptedException e) 
////		{
////            e.printStackTrace();
////        }
//		
//		
////		Member memberValidate = memberRepository.validateMember(email, password);
////		if (memberValidate == null) {
////			throw new MemberNotFoundException();
////		}
//		
////		return memberValidate;
////		return //memberRepository.validateMember(email, password);
////	}
//	
//	
//	
//	
//	
//	
//	
//}
