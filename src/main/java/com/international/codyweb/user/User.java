package com.international.codyweb.user;

//import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.international.codyweb.post.Post;
import com.international.codyweb.role.Role;



@Entity
@Table(name = "users")
@Getter @Setter
public class User {
//	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	
	@NotBlank
	@Size(max = 20)
	private String username;

	
	@NotEmpty
	@Email
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	private String password;
	

	private boolean accountVerified;
	 
	private int failedLoginAttempts;
	 
	private boolean loginDisabled;
	
	
	
	//Member is parent of posts
	@JsonIgnore
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();
	
	//Member can have many role and role can be assigned to many member
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	
	
	public User() {
		super();
	}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		
	}
	
	public void addUserRoles(Role role){
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeUserRoles(Role role){
        roles.remove(role);
        role.getUsers().remove(this);
    }
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}
	
}
