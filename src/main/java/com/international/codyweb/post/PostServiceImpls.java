/**
 * 
 */
package com.international.codyweb.post;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.international.codyweb.exception.ResourceNotFoundException;
import com.international.codyweb.user.User;
import com.international.codyweb.user.UserRepository;
import com.international.codyweb.user.UserService;

/**
 * @author Cody Hoang
 *
 */
@Service
@Transactional
public class PostServiceImpls implements PostService {

	
	private final String POST_CACHE = "POST";
	
	@Autowired
    RedisTemplate <String, Object> redisTemplate;
    private HashOperations <String, Long, Post> hashOperations;
	
    @Autowired
    PostRepository postRepository;
    
    @Autowired
    UserService userService;
    
    // This annotation makes sure that the method needs to be executed after 
    // dependency injection is done to perform any initialization.
    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }
	
    
    @CachePut(value = "posts", key = "#post.id")
	@Override
	public List <Post> getAllPosts() {
		return postRepository.findAll();
	}

    @CachePut(value = "posts", key = "#post.id")
	@Override
	public List <Post> getPostByCategory(String category) {
		return postRepository.findByCategory(category);
	}

	@Cacheable(value = "posts", key = "#post.id")
	@Override
	public void uploadPost(Post post, Long userId)  {
		User userEntity = userService.getUserById(userId);
		post.setUser(userEntity);
		hashOperations.put(POST_CACHE, post.getId() , post);	
	}

	@CachePut(value = "users", key = "#post.id")
	@Override
	public void updatePost(Post post, Long postId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
}
