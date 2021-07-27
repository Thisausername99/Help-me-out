package com.international.codyweb.web.controller;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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


import com.international.codyweb.post.Post;
import com.international.codyweb.post.PostService;


@RestController
@RequestMapping (path = "api/post")
public class PostController {

	@Autowired
	private PostService postService;


	@GetMapping (path = "/find/all")
	public ResponseEntity<List<Post>> getAll(){
		List<Post> posts = new ArrayList<>();
		postService.getAllPosts().forEach(posts::add);
		if (posts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(posts, HttpStatus.OK);
	}


	@GetMapping (path = "/find/{category}")
	public ResponseEntity<List<Post>> getPostByCategory(@PathVariable(value = "category") String category){
		List<Post> posts = new ArrayList<>();
		postService.getPostByCategory(category).forEach(posts::add);
		if (posts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}


	/*Upload a post associate with a user */
	@PostMapping (path ="/create")
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, HttpServletRequest request){
		//retrieve userId to add post create by user
		Long userId = (Long) request.getSession().getAttribute("userId");
		//		System.out.printf("User id is %d", userId);
		Post _post = postService.uploadPost(post, userId);
		return new ResponseEntity<>(_post,HttpStatus.CREATED);	
	}


	@PutMapping("/update/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable long postId, @Valid @RequestBody Post postRequest) {
		
		Post _post = postService.updatePost(postId, postRequest);
		// get post from post table if found update field then save
		// else throw error
		return new ResponseEntity<>(_post, HttpStatus.OK);
	}

}
