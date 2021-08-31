package com.international.codyweb.web.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class TokenRefreshRequest {
  @NotBlank
  private String refreshToken;
}
