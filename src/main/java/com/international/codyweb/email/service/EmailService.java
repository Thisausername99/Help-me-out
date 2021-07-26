package com.international.codyweb.email.service;

import javax.mail.MessagingException;

import com.international.codyweb.email.context.AbstractEmailContext;

public interface EmailService {
	 void sendMail(final AbstractEmailContext email) throws MessagingException;
}
