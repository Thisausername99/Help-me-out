package com.international.codyweb.model.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "media")
public class PostMedia {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private Long id;
    private String title;
    private String description;
    private String imagePath;
    private String imageFileName;
}
