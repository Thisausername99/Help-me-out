package com.international.codyweb.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{


	//	@QueryHints(QueryHint(name = HINT_CACHEABLE, value = "true"))
	//	@Query("SELECT s FROM Member s WHERE s.email = ?1")
	Optional <User> findByUsername(String username);

	Optional <User> findByEmail(String email);



	//	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username and u.enabled = true")
	//	Boolean existsByEnabled(@Param("username") String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
