//package com.international.codyweb.model.entity;
//
//import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import lombok.*;
//
//@Entity
//@Table(name = "users")
//@Getter @Setter 
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class UserEntity implements Serializable {
//	private static final long serialVersionUID = 1;
//
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
//	private Long id;
//
//
//	@NotBlank
//	@Size(max = 20)
//	private String username;
//
//
//	@NotEmpty
//	@Email
//	@Column(unique = true)
//	private String email;
//
//	@NotEmpty
//	private String password;
//
////    @Enumerated(EnumType.STRING)
////    private Status status;
//
//	private boolean accountVerified;
////
////	private int failedLoginAttempts;
////
//	private boolean loginDisabled;
//
//
//
//	//User is parent of posts
//	@JsonManagedReference
//	//	@JsonIgnore
//	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,
//	cascade = CascadeType.ALL)
//	private Set<PostEntity> posts = new HashSet<>();
//}
