/**
 * 
 */
package com.international.codyweb.service;

import java.util.*;
import org.springframework.web.multipart.MultipartFile;

import com.international.codyweb.exception.ResourceNotFoundException;
import com.international.codyweb.model.dto.PostDto;
import com.international.codyweb.model.entity.PostEntity;

/**
 * @author Cody Hoang
 *
 */
public interface PostService {
	
	List <PostEntity> getPostByUserId(final Long userId);
	List <PostEntity> getPostByCategory(final String category);
	PostEntity uploadPost (final PostDto post, final Long userId, final MultipartFile file);
	PostEntity updatePost (final PostDto post, final Long postId) throws ResourceNotFoundException;

}
