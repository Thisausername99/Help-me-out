package com.international.authoriziation.server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.international.authoriziation.server.model.entity.PostMedia;

@Repository
public interface MediaRepository extends JpaRepository<PostMedia,Long>{
	PostMedia findByTitle(String title);
}
