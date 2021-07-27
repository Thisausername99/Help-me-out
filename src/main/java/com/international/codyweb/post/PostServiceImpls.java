/**
 * 
 */
package com.international.codyweb.post;

import java.util.*;
import java.util.concurrent.TimeUnit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.international.codyweb.exception.ResourceNotFoundException;
import com.international.codyweb.user.User;
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
		List <Post> posts = postRepository.findAll();
		posts.forEach(post ->  postRedisUtil.putMap(TABLE_POST, POST_ + post.getId(), post));
		postRedisUtil.setExpire(TABLE_POST, 2, TimeUnit.MINUTES);
		return posts;

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
		
		postEntity.setUser(userEntity);
		postRepository.save(postEntity);
		try {

			postRedisUtil.putMap(TABLE_POST, POST_ + postEntity.getId(), postEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOG.warn(postEntity.toString());
		return postEntity; 
	}

	//	@CachePut(value = "postCache", key = "#p0")
	@Override
	public Post updatePost(Long postId, Post post) throws ResourceNotFoundException {
		Post postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));

		BeanUtils.copyProperties(postEntity, post);

		postRepository.save(postEntity);
		try {
			postRedisUtil.putMap(POST_, POST_ + postId, postEntity);
		} catch (Exception e){
			e.printStackTrace();
		}
		LOG.warn(postEntity.toString());
		return postEntity;
	}

}
