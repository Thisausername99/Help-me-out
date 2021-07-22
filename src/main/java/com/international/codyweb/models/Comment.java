package com.international.codyweb.models;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table 

public class Comment {
	
	
	private Long id; //foreign ID that link to the post
	private String content;
	public Comment() {
	}
	
	public Comment(Long id, String content) {
		this.id = id;
		this.content = content;
	}
	
	
	public Comment(String content) {
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

}
