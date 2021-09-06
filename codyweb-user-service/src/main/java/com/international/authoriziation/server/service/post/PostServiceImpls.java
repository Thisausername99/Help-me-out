/**
 * 
 */
package com.international.authoriziation.server.service.post;

import java.util.*;
import java.util.concurrent.TimeUnit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.international.authoriziation.server.model.dto.PostDto;
import com.international.authoriziation.server.model.entity.PostEntity;
import com.international.authoriziation.server.model.entity.PostMedia;
import com.international.authoriziation.server.model.mapper.PostMapper;
import com.international.authoriziation.server.model.repository.PostRepository;
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

//	@Autowired
//	UserRepository userRepository;

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
	public PostEntity uploadPost(PostDto post, Long userId, MultipartFile file) {
//		UserEntity user = userRepository.findById(1L).get();
		PostEntity postEntity = postMapper.convertToEntity(post, new PostEntity());
		postEntity.setUserId(userId);
		LOG.info(postEntity.getCategory());
		LOG.info(postEntity.getTitle());
		LOG.info(postEntity.getContent());
		LOG.info(postEntity.getUserId().toString());
		
//		postEntity.setUser(user);
		List <PostMedia> media = new ArrayList<>(); 
		if (!file.isEmpty()) {
			media.add(storageService.saveMedia(file.getName(),file.getContentType(),file));
			postEntity.setMedia(media);
			postEntity.setContainMedia(true);
		}
		postRepository.save(postEntity);
		
//		try {
//
//			postRedisUtil.putMap(TABLE_POST, POST_ + postEntity.getId(), postEntity);
//			postRedisUtil.setExpire(TABLE_POST, 2, TimeUnit.MINUTES);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		LOG.warn(postEntity.toString());
		return postEntity; 
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
