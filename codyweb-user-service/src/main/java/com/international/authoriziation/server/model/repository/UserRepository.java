package com.international.authoriziation.server.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.international.authoriziation.server.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{


	//	@QueryHints(QueryHint(name = HINT_CACHEABLE, value = "true"))
	//	@Query("SELECT s FROM Member s WHERE s.email = ?1")
//	Optional <UserEntity> findByUsername(String username);
	Optional <UserEntity> findByEmail(String email);

//	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE u.username = :username and u.enabled = true")
//	Boolean existsByEnabled(@Param("username") String username);

//	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
