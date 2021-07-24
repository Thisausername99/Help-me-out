package com.international.codyweb.core.email.service;

import javax.mail.MessagingException;

import com.international.codyweb.core.email.context.AbstractEmailContext;

public interface EmailService {
	 void sendMail(final AbstractEmailContext email) throws MessagingException;
}
