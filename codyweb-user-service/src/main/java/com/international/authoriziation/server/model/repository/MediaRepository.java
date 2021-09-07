package com.international.authoriziation.server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.international.authoriziation.server.model.entity.MediaEntity;

@Repository
public interface MediaRepository extends JpaRepository<MediaEntity,Long>{
	MediaEntity findByTitle(String title);
}
