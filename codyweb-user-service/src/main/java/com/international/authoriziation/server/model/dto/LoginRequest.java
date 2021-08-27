package com.international.authoriziation.server.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.*;


@Getter @Setter @NoArgsConstructor
public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
