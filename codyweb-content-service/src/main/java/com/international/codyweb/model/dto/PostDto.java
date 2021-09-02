package com.international.codyweb.model.dto;
import lombok.*;
import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

import com.international.codyweb.model.entity.PostMedia;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	@NotBlank
	private String category;
	
	@NotBlank
	private String content;
	
	@NotBlank
	private String title;
	
}	
