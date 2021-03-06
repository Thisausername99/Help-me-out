package com.international.authoriziation.server.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.international.authoriziation.server.model.entity.PostEntity;



@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
	
	@Query(value = "SELECT u FROM Posts u WHERE u.category = ?1", nativeQuery = true)
	List <PostEntity> findByCategory(String category);
	
//	@Query("SELECT u FROM Posts u WHERE u.user_id = %:userId%")
	List <PostEntity> findByuserId(Long userId);
}
