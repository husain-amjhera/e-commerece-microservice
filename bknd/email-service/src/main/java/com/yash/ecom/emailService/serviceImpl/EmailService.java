package com.yash.ecom.emailService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.yash.ecom.emailService.DTO.Email;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	
	@KafkaListener(topics = "email", group = "email", containerFactory = "emailKafkaListenerFactory")
	public void sendEmail(@Payload Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getSendTo());
		message.setSubject(email.getSubject());
		message.setText(email.getText());
		mailSender.send(message);
	}
}
