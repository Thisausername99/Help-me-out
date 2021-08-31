package com.international.codyweb.web.payload.response;

import java.util.List;
import lombok.*;

@Getter @Setter @NoArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
//	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username,  List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}
}