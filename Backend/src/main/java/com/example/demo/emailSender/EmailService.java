package com.example.demo.emailSender;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendEmailWithAttachment(EmailDTO emailDTO) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			message.setFrom(emailDTO.getFromAddress());
			helper.setTo(emailDTO.getToAddress());
			helper.setSubject(emailDTO.getSubject());
			helper.setText(emailDTO.getBody());

			ByteArrayResource byteArrayResource = null;
			String fileName = "";
			try {
				byteArrayResource = new ByteArrayResource(emailDTO.getAttachment().getBytes());
				fileName = emailDTO.getAttachment().getOriginalFilename();
			} catch (IOException e) {
				e.printStackTrace();
			}
			helper.addAttachment(fileName, byteArrayResource);
			emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
