package com.international.codyweb.service;

import javax.mail.MessagingException;

import com.international.codyweb.model.dto.EmailDto;
import com.international.codyweb.util.AbstractEmailContext;


public interface EmailService {
	 void sendMail(final AbstractEmailContext email) throws MessagingException;
	 
//	 void setupMail(EmailDto emailDto);
	 
}
