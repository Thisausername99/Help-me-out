package com.international.authoriziation.server.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import javax.persistence.CascadeType;


import lombok.Data;

//package com.international.codyweb.core.user.model;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
@Data
@Entity(name = "comment")


public class CommentEntity {	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) 
	private Long id; //foreign ID that link to the post
	private String content;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
	PostEntity post;

}
