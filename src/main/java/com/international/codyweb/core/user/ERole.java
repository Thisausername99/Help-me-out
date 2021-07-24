package com.international.codyweb.core.user;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ERole {
	ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN
}
