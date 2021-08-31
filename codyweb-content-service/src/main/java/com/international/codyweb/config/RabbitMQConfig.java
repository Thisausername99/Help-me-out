///**
// * 
// */
//package com.international.codyweb.config;
//
//import java.io.IOException;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.rabbitmq.client.Channel;
///**
// * @author Cody Hoang
// *
// */
//
//@EnableRabbit
//@Configuration
//public class RabbitMQConfig {
//
////	@Value("${cody.app.rabbitmq.queue}")
////	private String queueName;
//
//	@Value("${cody.app.rabbitmq.exchange}")
//	private String exchange;
//
//	@Value("${cody.app.rabbitmq.routingkey}")
//	private String routingkey;
//
//	private Channel channel;
//	
//	@Bean
//	Queue queue() throws IOException {
//		return new Queue(channel.queueDeclare().getQueue(), false);
//	}
//	
//	
//	//queue to exchange
//	@Bean
//	DirectExchange exchange() {
//		return new DirectExchange(exchange);
//	}
//
//	//bind queue with exchange queue through routing lkey
//	@Bean
//	Binding binding(Queue queue, DirectExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
//	}
//
//	@Bean
//	public MessageConverter jsonMessageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}
//
//
//	@Bean
//	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(jsonMessageConverter());
//		return rabbitTemplate;
//	}
//}
//
//
//
