package com.international.codyweb.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderServiceImpl {

	private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email, String userEmail, String token) {
    	email.setTo(userEmail);
    	email.setSubject("Complete Registration!");
    	email.setFrom("chand312902@gmail.com");
    	email.setText("To confirm your account, please click here : "
        +"http://localhost:8082/confirm-account?token="+ token);
    	
    	javaMailSender.send(email);
    }
	
   
    
}
