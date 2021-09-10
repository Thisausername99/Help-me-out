package com.international.codyweb.service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.international.codyweb.model.dto.EmailDto;
import com.international.codyweb.util.AbstractEmailContext;
import com.international.codyweb.util.AccountVerificationEmailContext;



@Service
public class EmailServiceImpl implements EmailService{
	
	@Value("${cody.app.base.url}")
	private String baseURL;
	
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Autowired
	public EmailServiceImpl(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}
	
	
	@Override
	@Async
	public void sendMail(AbstractEmailContext email) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariables(email.getContext());
		String emailContent = templateEngine.process(email.getTemplateLocation(), context);

		mimeMessageHelper.setTo(email.getTo());
		mimeMessageHelper.setSubject(email.getSubject());
		mimeMessageHelper.setFrom(email.getFrom());
		mimeMessageHelper.setText(emailContent, true);
		emailSender.send(message);
	}
}
