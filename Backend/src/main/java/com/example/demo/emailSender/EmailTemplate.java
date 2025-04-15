package com.example.demo.emailSender;

import org.springframework.web.multipart.MultipartFile;

public interface EmailTemplate {
	String getBody();
	String getSubject();
	String getFromAddress();
	String getToAddress();
	MultipartFile getAttachment();
}
