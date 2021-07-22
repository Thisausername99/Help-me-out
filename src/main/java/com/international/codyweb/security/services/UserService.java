package com.international.codyweb.security.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.international.codyweb.models.User;
import com.international.codyweb.repositories.UserRepository;

@Service
public class UserService {
	
    private final String USER_CACHE = "USER";
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, User> hashOperations;
	
    
    
    // This annotation makes sure that the method needs to be executed after 
    // dependency injection is done to perform any initialization.
    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }
	
    //save to redis first then db
    public void saveUser(final User user) {
        userRepository.save(user);
    }
        
    
    public User findUserById(final Long id) {
       User usr =  (User) hashOperations.get(USER_CACHE, id);
       if (usr == null) {
    	   return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: User is not found."));
       }
       return usr;
    }
    
    public Optional<User> findUserByEmail(final String email) {
    	return userRepository.findByEmail(email);
    }
    
    
    public User findUserByUsername(final String username) {
       return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User is not found.")); 
     }
  
    public Boolean userValidation(String username) {
    	return userRepository.existsByEnabled(username);
    }
    
    
	
	public Boolean userEmailValidation(String email) {
		return userRepository.existsByEmail(email);
    }

	public Boolean usernameValidation(String username) {
		return userRepository.existsByUsername(username);
	}
    
    
 
    // Find all employees' operation.
    public Map<Long, User> findAllUser() {
        return hashOperations.entries(USER_CACHE);
    }
 
    // Delete employee by id operation.
    public void delete(Long id) {
        hashOperations.delete(USER_CACHE, id);
        userRepository.deleteById(id);
    }
    
    
    
    
	

}
