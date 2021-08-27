//package com.international.authoriziation.server.token.pojo;
//
//import java.time.Instant;
//
//import javax.persistence.*;
//
//import com.international.authoriziation.server.model.entity.User;
//import com.international.authoriziation.server.model.entity.UserEntity;
//
//import lombok.*;
//
//@Entity(name = "refreshtoken")
//@Getter @Setter @NoArgsConstructor
//public class RefreshToken {
//	 @Id
//	 @GeneratedValue(strategy = GenerationType.AUTO)
//	 private long id;
//	
//	 @OneToOne
//	 @JoinColumn(name = "user_id", referencedColumnName = "id")
//	 private UserEntity user;
//	
//	 @Column(nullable = false, unique = true)
//	 private String token;
//
//  	 @Column(nullable = false)
//  	 private Instant expiryDate;
//}