package com.international.codyweb.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import com.international.codyweb.model.dto.EmailDto;


public class AccountVerificationEmailContext extends AbstractEmailContext{


	public void setToken(String token) {
		put("token", token);
	}


	@Override
	public <T> void init(T context){
		//we can do any common configuration setup here
		// like setting up some base URL and context
		EmailDto emailDto = (EmailDto) context;
//		put("userName", user.getUsername());
		setTemplateLocation("emails/email-verification");
		setSubject("Complete your registration");
		setFrom("no-reply@codyweb.com");
		System.out.println(emailDto.getEmail());
		setTo(emailDto.getEmail());
	}



	public void buildVerificationUrl(final String baseURL, final String token){
		final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
				.path("/confirm-account").queryParam("token", token).toUriString();
		put("verificationURL", url);
	}
}
