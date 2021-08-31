/**
 * 
 */
package com.international.codyweb.service;

import java.util.*;

import com.international.codyweb.exception.ResourceNotFoundException;
import com.international.codyweb.model.entity.PostEntity;

/**
 * @author Cody Hoang
 *
 */
public interface PostService {
	
	List <PostEntity> getAllPosts();
	List <PostEntity> getPostByCategory(final String category);
	PostEntity uploadPost (final PostEntity post, final Long userId);
	PostEntity updatePost (final Long postId, final PostEntity post) throws ResourceNotFoundException;

}
