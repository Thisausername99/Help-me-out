package com.international.authoriziation.server.model.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	  }
}