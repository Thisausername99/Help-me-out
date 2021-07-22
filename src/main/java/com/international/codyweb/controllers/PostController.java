package com.international.codyweb.controllers;


import java.util.ArrayList;
import java.util.List;

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



import com.international.codyweb.exception.*;

/*For request parsing */
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



/*For model*/
import com.international.codyweb.models.Post;
import com.international.codyweb.repositories.PostRepository;
import com.international.codyweb.repositories.UserRepository;

@RestController
@RequestMapping (path = "api/post")
public class PostController {
	
	
	private final PostRepository postRepository;
	
	private final UserRepository userRepository;

	@Autowired
	public PostController(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	
	
	
	@GetMapping (path = "/find/all")
	public ResponseEntity<List<Post>> getAllPosts(){
		List<Post> posts = new ArrayList<>();
		postRepository.findAll().forEach(posts::add);
		if (posts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	
	@GetMapping (path = "/find/{category}")
	public ResponseEntity<List<Post>> getPostByCategory(@PathVariable(value = "category") String category){
		List<Post> posts = new ArrayList<>();
		postRepository.findByCategory(category).forEach(posts::add);
		if (posts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	
	/*Upload a post associate with a user */
	@PostMapping (path ="/{userId}/create")
//	@PathVariable (value = "userId") long userId,
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, HttpServletRequest request){
		//retrieve userId to add post create by user
		Long userId = (Long) request.getSession().getAttribute("currentUser");
		System.out.printf("User id is %d", userId);
		// find if user exist
		// set user for post 
		// save post to table
		// return response entity with created status
		// else throw error
		return userRepository.findById(userId).map(user -> {
            post.setUser(user);
            Post _post = postRepository.save(post);
            return new ResponseEntity<>(_post,HttpStatus.CREATED);
        }).orElseThrow(() -> new ResourceNotFoundException("user not found"));
	}
	
	
	@PutMapping("/update/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable long postId, @Valid @RequestBody Post postRequest) {
        
		// get post from post table if found update field then save
		// else throw error
		return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setCategory(postRequest.getCategory());
            post.setContent(postRequest.getContent());
            return new ResponseEntity<>(postRepository.save(post),HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("post not found with id "+ postId));
	}
		
}
