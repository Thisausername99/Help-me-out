package com.international.codyweb.controller;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*For CRUD */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
/*For request parsing */
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.international.codyweb.model.entity.PostEntity;
import com.international.codyweb.service.PostService;


@RestController
@RequestMapping (path = "api/post")
public class PostController {

	@Autowired
	private PostService postService;
	
//	@Autowired 
//	private RabbitTemplate template;
//	
	
	@GetMapping (path = "/find/all")
	public ResponseEntity<List<PostEntity>> getAll(){
		List<PostEntity> posts = new ArrayList<>();
		postService.getAllPosts().forEach(posts::add);
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


	/*Upload a post associate with a user */
	@PostMapping (path ="/create")
	public ResponseEntity<PostEntity> createPost(@Valid @RequestBody PostEntity post, HttpServletRequest request){
		//retrieve userId to add post create by user
		Long userId = (Long) request.getSession().getAttribute("userId");
		//		System.out.printf("User id is %d", userId);
		
//		templet.c
		PostEntity _post = postService.uploadPost(post, userId);
		
		
		return new ResponseEntity<>(_post,HttpStatus.CREATED);	
	}


	@PutMapping("/update/{postId}")
	public ResponseEntity<PostEntity> updatePost(@PathVariable long postId, @Valid @RequestBody PostEntity postRequest) {
		
		PostEntity _post = postService.updatePost(postId, postRequest);
		// get post from post table if found update field then save
		// else throw error
		return new ResponseEntity<>(_post, HttpStatus.OK);
	}

}
