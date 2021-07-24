package com.international.codyweb.core.user.model;

//import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
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
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	public boolean isAccountVerified() {
		return accountVerified;
	}

	public void setAccountVerified(boolean accountVerified) {
		this.accountVerified = accountVerified;
	}

	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public boolean isLoginDisabled() {
		return loginDisabled;
	}

	public void setLoginDisabled(boolean loginDisabled) {
		this.loginDisabled = loginDisabled;
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
