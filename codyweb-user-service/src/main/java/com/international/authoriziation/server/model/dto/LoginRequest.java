package com.international.authoriziation.server.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import lombok.*;


@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String authId;
	
	private Boolean verify;
}
