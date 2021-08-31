package com.international.codyweb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.international.codyweb.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
