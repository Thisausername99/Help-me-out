/**
 * 
 */
package com.international.codyweb.post;

import java.util.*;

import com.international.codyweb.exception.ResourceNotFoundException;

/**
 * @author Cody Hoang
 *
 */
public interface PostService {
	
	List <Post> getAllPosts();
	List <Post> getPostByCategory(final String category);
	Post uploadPost (final Post post, final Long userId);
	Post updatePost (final Post post, final Long postId) throws ResourceNotFoundException;

}
