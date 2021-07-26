package com.international.codyweb;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.international.codyweb.user.User;
import com.international.codyweb.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class JpaMultipleDBIntegrationTest {
 
    @Autowired
    private UserRepository userRepository;

    

    @Test
    @Transactional("primaryTransactionManager")
    public void whenCreatingUser_thenCreated() {
        User user = new User();
        user.setUsername("John");
        user.setEmail("john@test.com");
        user = userRepository.save(user);

        assertNotNull(userRepository.findById(user.getId()));
    }

//    @Test
//    @Transactional("userTransactionManager")
//    public void whenCreatingUsersWithSameEmail_thenRollback() {
//        User user1 = new User();
//        user1.setUsername("John");
//        user1.setEmail("john@test.com");
//        user1.setAge(20);
//        user1 = userRepository.save(user1);
//        assertNotNull(userRepository.findOne(user1.getId()));
//
//        User user2 = new User();
//        user2.setUsername("Tom");
//        user2.setEmail("john@test.com");
//        user2.setAge(10);
//        try {
//            user2 = userRepository.save(user2);
//        } catch (DataIntegrityViolationException e) {
//        }
//
//        assertNull(userRepository.findOne(user2.getId()));
    }
