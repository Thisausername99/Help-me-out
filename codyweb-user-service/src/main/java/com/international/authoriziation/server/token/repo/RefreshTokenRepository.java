//package com.international.authoriziation.server.token.repo;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//
//import com.international.authoriziation.server.model.entity.User;
//import com.international.authoriziation.server.token.pojo.RefreshToken;
//
//import java.util.Optional;
//
//public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
//
//    @Override
//    Optional<RefreshToken> findById(Long id);
//
//    Optional<RefreshToken> findByToken(String token);
//    
//    
//    @Modifying
//    int deleteByUser(User user);
//
//}