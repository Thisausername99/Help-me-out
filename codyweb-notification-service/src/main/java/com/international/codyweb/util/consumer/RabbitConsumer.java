package com.international.codyweb.util.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.international.codyweb.model.dto.EmailDto;
import com.international.codyweb.service.EmailService;
import com.international.codyweb.util.AccountVerificationEmailContext;

@Component
public class RabbitConsumer {
	
	@Value("${cody.app.base.url}")
	private String baseURL;
	
	@Autowired
	EmailService emailService;
	
	@RabbitListener(queues="${rabbitmq.queue}", containerFactory="jsaFactory")
    public void recievedMessage(EmailDto emailDto) {
		AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
		emailContext.init(emailDto);
		emailContext.setToken(emailDto.getToken());
		emailContext.buildVerificationUrl(baseURL, emailDto.getToken());
		System.out.println(emailDto.getToken());
		try {
			emailService.sendMail(emailContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
