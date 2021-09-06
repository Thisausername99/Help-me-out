package com.international.authoriziation.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.international.authoriziation.server.client.ContentClient;
import com.international.authoriziation.server.model.dto.PostDto;


@RestController
public class ContentController {
	
//	@Autowired
//	private ContentClient contentClient;
	
//	@PostMapping
//	public ResponseEntity<?> uploadPost(@RequestPart("post") PostDto post, @RequestPart("file") MultipartFile file, HttpServletRequest request){
//		Long userId = (Long) request.getAttribute("userId");	
//		return contentClient.uploadPost(post, file, userId);
//	}
	
}
