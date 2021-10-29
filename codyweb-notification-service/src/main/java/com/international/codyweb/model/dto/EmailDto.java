package com.international.codyweb.model.dto;

import javax.validation.constraints.NotBlank;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class EmailDto {
	@NotBlank
	private String email;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String token;
}