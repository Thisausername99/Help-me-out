/**
 * 
 */
package com.international.codyweb.post;

import java.util.*;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.international.codyweb.CodyWebApplication;
import com.international.codyweb.exception.ResourceNotFoundException;
import com.international.codyweb.user.User;
import com.international.codyweb.user.UserRepository;
import com.international.codyweb.user.UserService;
import com.international.codyweb.util.RedisUtil;

/**
 * @author Cody Hoang
 *
 */
@Service
@Transactional
public class PostServiceImpls implements PostService {


	private final String POST_ = "POST_";

	private final String TABLE_POST = "TABLE_POST";

	private static final Logger LOG = LoggerFactory.getLogger(PostServiceImpls.class);
	

	@Autowired
	private RedisUtil <Post> postRedisUtil;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserService userService;



	//@CachePut(value = "postCache", key = "#post.id")
	@Override
	public List <Post> getAllPosts() {
//		Map <Object, Post> redisCacheTable= postRedisUtil.getMapAsAll(TABLE_POST);
		return postRepository.findAll();
	}

//	@CachePut(value = "postCache", key = "#p0")
	@Override
	public List <Post> getPostByCategory(String category) {
		
		return postRepository.findByCategory(category);
	}

//	@CachePut(value = "postCache", key ="{ #root.methodName, #post.title }")
	@Override
	public Post uploadPost(Post post, Long userId) {
		User userEntity = userService.getUserById(userId);
		Post postEntity = new Post();
		BeanUtils.copyProperties(post, postEntity);
		try {
			
			postRedisUtil.putMap(TABLE_POST, POST_ + postEntity.getTitle(), postEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		postEntity.setUser(userEntity);
		postRepository.save(postEntity);
		LOG.warn(postEntity.toString());
		return postEntity; 
	}

	//	@CachePut(value = "postCache", key = "#p0")
	@Override
	public Post updatePost(Post post, Long postId) throws ResourceNotFoundException {
		
		return post;
		// TODO Auto-generated method stub

	}

}
