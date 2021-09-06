package com.international.authoriziation.server.controller;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*For CRUD */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.GetMapping;
/*For request parsing */
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.international.authoriziation.server.model.dto.PostDto;
import com.international.authoriziation.server.model.entity.PostEntity;
import com.international.authoriziation.server.service.post.PostService;
import com.international.authoriziation.server.service.storage.StorageService;

import lombok.extern.java.Log;


@RestController
@RequestMapping (path = "api/v1/post")
public class PostController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PostController.class);

	
	@Autowired
	private PostService postService;
	
	@Autowired
	private StorageService storageService;
//	@Autowired 
//	private RabbitTemplate template;
//	
	
	@GetMapping (path = "/find/all")
	public ResponseEntity<List<PostEntity>> getPostByUserId(@RequestParam("id")Long userId){
		List<PostEntity> posts = new ArrayList<>();
		postService.getPostByUserId(userId).forEach(posts::add);
		if (posts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(posts, HttpStatus.OK);
	}


	@GetMapping (path = "/find/{category}")
	public ResponseEntity<List<PostEntity>> getPostByCategory(@PathVariable(value = "category") String category){
		List<PostEntity> posts = new ArrayList<>();
		postService.getPostByCategory(category).forEach(posts::add);
		if (posts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}


	/*Upload a post associate with a user 
	 * User retrieve from local cache
	 * 
	 * */
	@PostMapping (path ="/create")
	public ResponseEntity<PostEntity> createPost(@RequestPart("post") PostDto post, @RequestPart("file") MultipartFile file, HttpServletRequest request){
		//retrieve userId to add post create by user
//		Long userId = (Long) request.getSession().getAttribute("userId");
		
		System.out.printf("file content is %s", file.getContentType());
//		request.getAttribute(null) -> get userId from session
		LOG.info(post.getTitle());
//		templet.c
		PostEntity _post = postService.uploadPost(post, 1L, file);
		
		
		return new ResponseEntity<>(_post,HttpStatus.CREATED);	
	}

	@PostMapping (path ="/file")
	public ResponseEntity<PostEntity> uploadTest(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		//retrieve userId to add post create by user
//		Long userId = (Long) request.getSession().getAttribute("userId");
		System.out.printf("file content is %s", file.getContentType());
		LOG.info(file.getContentType());
		
		storageService.saveMedia(file.getName(), file.getContentType(), file);
//		templet.c
//		PostEntity _post = postService.uploadPost(post, 1L, file);
		
		
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	@GetMapping(path = "{id}/file/download")
	public byte[] downloadMedia(@PathVariable("id") Long id) {
		return storageService.downloadMedia(id);
	} 

	
	
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostEntity> updatePost(@Valid @RequestBody PostDto post, @PathVariable long postId) {
		
		PostEntity _post = postService.updatePost(post, postId);
		// get post from post table if found update field then save
		// else throw error
		return new ResponseEntity<>(_post, HttpStatus.OK);
	}

}
