package com.international.authoriziation.server.model.entity;

import java.io.Serializable;
//import java.io.Serializable;

import javax.persistence.*;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;

//import com.international.authoriziation.server.role.Role;
//import com.international.codyweb.post.Post;



//use Data encounter servlet error ?
@Entity
@Table(name = "users") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1;

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) 
	private Long id;
	
	@Column(unique = true)
	@NotBlank
	private String authId;
	
	@Size(max = 20)
	private String username;


	@NotEmpty
	@Email
	@Column(unique = true)
	private String email;
	
	
	private boolean accountVerified;
	//	//User is parent of posts
	//	@JsonManagedReference
	//	//	@JsonIgnore
	//	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,
	//	cascade = CascadeType.ALL)
	//	private Set<Post> posts = new HashSet<>();

	//Member can have many role and role can be assigned to many member

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "user_roles", 
//	joinColumns = @JoinColumn(name = "user_id"), 
//	inverseJoinColumns = @JoinColumn(name = "role_i))
//	@JsonManagedReference
//	@Builder.Default
//	private Set<Role> roles = new HashSet<>();
//
//	
//	public void addUserRoles(Role role){
//		roles.add(role);
//		role.getUsers().add(this);
//	}
//
//	public void removeUserRoles(Role role){
//		roles.remove(role);
//		role.getUsers().remove(this);
//	}



}
