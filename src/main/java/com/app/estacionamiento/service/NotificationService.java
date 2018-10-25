package com.app.estacionamiento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	private NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotification() throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("f.maldonadom@alumnos.duoc.cl");
//		mail.setFrom("felipe8maldonado@gmail.com");
		mail.setSubject("Prueba de correo");
		mail.setText("texto de prueba para enviar el correo");
		
		javaMailSender.send(mail);
	}
}
