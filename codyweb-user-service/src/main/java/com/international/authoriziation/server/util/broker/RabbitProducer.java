package com.international.authoriziation.server.util.broker;

import java.io.IOException;

import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import com.international.authoriziation.server.model.dto.EmailDto;
//import com.rabbitmq.client.Channel;

@Component
public class RabbitProducer {

//	@Value("${cody.app.rabbitmq.queue}")
//	private String queueName;
	

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.routingkey}")
	private String routingkey;

	
	
	public void produce(EmailDto emailDto ){
		amqpTemplate.convertAndSend(exchange, routingkey, emailDto);
		System.out.println("Send msg = " + emailDto);
	}
}
