/**
 * 
 */
package com.international.codyweb.member;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.international.codyweb.post.Post;

import lombok.*;

/**
 * @author Cody Hoang
 *
 */
@Entity
@Table(name = "members")
@Getter @Setter @NoArgsConstructor
public class Member implements Serializable {

	private static final long serialVersionUID = 2L;

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



	//User is parent of posts
	@JsonManagedReference
	//	@JsonIgnore
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,
	cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<>();

	//Member can have many role and role can be assigned to many member

	public Member(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;

	}

//	public void addUserRoles(Role role){
//		roles.add(role);
//		role.getUsers().add(this);
//	}
//
//	public void removeUserRoles(Role role){
//		roles.remove(role);
//		role.getUsers().remove(this);
//	}



	@Override
	public int hashCode() {
		return Objects.hash(id, email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}

}


