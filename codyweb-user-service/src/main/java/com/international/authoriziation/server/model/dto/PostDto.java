package com.international.authoriziation.server.model.dto;

import lombok.*;
import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String category;
	
	@NotBlank
	private String content;
	
}	

