package com.international.codyweb.web.payload.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	  }
}