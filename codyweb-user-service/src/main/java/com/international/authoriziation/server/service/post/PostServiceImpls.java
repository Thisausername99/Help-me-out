/**
 * 
 */
package com.international.authoriziation.server.service.post;

import java.util.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.international.authoriziation.server.model.dto.PostDto;
import com.international.authoriziation.server.model.entity.MediaEntity;
import com.international.authoriziation.server.model.entity.PostEntity;
import com.international.authoriziation.server.model.entity.UserEntity;
import com.international.authoriziation.server.model.mapper.PostMapper;
import com.international.authoriziation.server.model.repository.PostRepository;
import com.international.authoriziation.server.model.repository.UserRepository;
import com.international.authoriziation.server.service.storage.StorageService;
import com.international.authoriziation.server.exception.ResourceNotFoundException;
import com.international.authoriziation.server.util.cache.RedisUtil;

import lombok.AllArgsConstructor;

/**
 * @author Cody Hoang
 *
 */
@Service
@Transactional
@AllArgsConstructor
public class PostServiceImpls implements PostService {


	private final String POST_ = "POST_";

	private final String TABLE_POST = "TABLE_POST";

	private static final Logger LOG = LoggerFactory.getLogger(PostServiceImpls.class);
	
	private final PostMapper postMapper = new PostMapper();

//	@Autowired
//	private RedisUtil <PostEntity> postRedisUtil;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	StorageService storageService;
	
	//@CachePut(value = "postCache", key = "#post.id")
	@Override
	public List <PostEntity> getPostByUserId(Long userId) {
		List <PostEntity> posts = postRepository.findByuserId(userId);
//		posts.forEach(post ->  postRedisUtil.putMap(TABLE_POST, POST_ + post.getId(), post));
//		postRedisUtil.setExpire(TABLE_POST, 2, TimeUnit.MINUTES);
		return posts;

	}

	//	@CachePut(value = "postCache", key = "#p0")
	@Override
	public List <PostEntity> getPostByCategory(String category) {

		return postRepository.findByCategory(category);
	}
	

	
	//	@CachePut(value = "postCache", key ="{ #root.methodName, #post.title }")
	@Override
	public PostEntity uploadPost(PostDto postDto, Long userId, MultipartFile file) {
		UserEntity user = userRepository.findById(userId).get();
		PostEntity post = postMapper.convertToEntity(postDto, new PostEntity());
		post.setUser(user);
		LOG.info(post.getCategory());
		LOG.info(post.getTitle());
		LOG.info(post.getContent());
//		LOG.info(postEntity.getUserId().toString());
		
//		postEntity.setUser(user);
		List <MediaEntity> media = new ArrayList<>(); 
		if (!file.isEmpty()) {
			media.add(storageService.saveMedia(file.getName(),file.getContentType(),file));
			post.setMedia(media);
			post.setContainMedia(true);
		}
		
		
//		try {
//
//			postRedisUtil.putMap(TABLE_POST, POST_ + postEntity.getId(), postEntity);
//			postRedisUtil.setExpire(TABLE_POST, 2, TimeUnit.MINUTES);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		LOG.warn(post.toString());
		return postRepository.save(post); 
	}

	//	@CachePut(value = "postCache", key = "#p0")
	@Override
	public PostEntity updatePost(PostDto post, Long postId) throws ResourceNotFoundException {
		PostEntity postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
		
		postMapper.convertToEntity(post, postEntity);
//		.copyProperties(postEntity, post);

		postRepository.save(postEntity);
//		try {
//			postRedisUtil.putMap(POST_, POST_ + postId, postEntity);
//			postRedisUtil.setExpire(TABLE_POST, 2, TimeUnit.MINUTES);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
		LOG.warn(postEntity.toString());
		return postEntity;
	}

}
