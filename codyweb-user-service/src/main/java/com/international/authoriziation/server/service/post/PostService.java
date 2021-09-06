/**
 * 
 */
package com.international.authoriziation.server.service.post;

import java.util.*;
import org.springframework.web.multipart.MultipartFile;

import com.international.authoriziation.server.model.dto.PostDto;
import com.international.authoriziation.server.model.entity.PostEntity;
import com.international.authoriziation.server.exception.ResourceNotFoundException;

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
