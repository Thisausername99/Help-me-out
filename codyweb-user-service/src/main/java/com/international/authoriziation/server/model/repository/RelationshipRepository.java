package com.international.authoriziation.server.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.international.authoriziation.server.model.entity.RelationshipEntity;
import com.international.authoriziation.server.model.entity.UserEntity;

@Repository
public interface RelationshipRepository extends JpaRepository<RelationshipEntity,Long>{
	boolean existsByFirstUserAndSecondUser(UserEntity first,UserEntity second);

    List<RelationshipEntity> findByFirstUser(UserEntity user);
    List<RelationshipEntity> findBySecondUser(UserEntity user);
}
