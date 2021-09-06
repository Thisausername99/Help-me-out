package com.international.authoriziation.server.service.email;

import javax.mail.MessagingException;

import com.international.authoriziation.server.util.email.AbstractEmailContext;

public interface EmailService {
	 void sendMail(final AbstractEmailContext email) throws MessagingException;
}
