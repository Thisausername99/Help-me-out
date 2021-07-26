package com.international.codyweb.post;


import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.international.codyweb.user.User;


@Entity
@Table(name = "posts")

public class Post extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;
	
	@NotNull
	@Size(max = 250)
	private String category;
	
	
	@NotNull
    @Lob
    private String content;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	
	
	
	
	public Post() {
	}

	

	
	
	public Post(String title, String category, String content) {
		this.title = title;
		this.category = category;
		this.content = content;
	}


	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}


	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getCategory() {
		return category;
	}





	public void setCategory(String category) {
		this.category = category;
	}


	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id, user);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id) && Objects.equals(user, other.user);
	}





	
	
}
