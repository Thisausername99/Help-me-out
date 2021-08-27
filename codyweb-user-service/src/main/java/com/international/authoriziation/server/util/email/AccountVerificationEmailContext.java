package com.international.authoriziation.server.util.email;

import org.springframework.web.util.UriComponentsBuilder;

import com.international.authoriziation.server.model.entity.UserEntity;


public class AccountVerificationEmailContext extends AbstractEmailContext{



	public void setToken(String token) {
		put("token", token);
	}


	@Override
	public <T> void init(T context){
		//we can do any common configuration setup here
		// like setting up some base URL and context
		UserEntity user = (UserEntity) context;
		put("userName", user.getUsername());
		setTemplateLocation("emails/email-verification");
		setSubject("Complete your registration");
		setFrom("no-reply@codyweb.com");
		setTo(user.getEmail());
	}



	public void buildVerificationUrl(final String baseURL, final String token){
		final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
				.path("/confirm-account").queryParam("token", token).toUriString();
		put("verificationURL", url);
	}
}
