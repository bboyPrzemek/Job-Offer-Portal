package com.example.demo.emailSender;

public class TemplateToDTOMapper {
	
	public static EmailDTO map(EmailTemplate emailTemplate) {
		EmailDTO e = new EmailDTO();
		e.setAttachment(emailTemplate.getAttachment());
		e.setBody(emailTemplate.getBody());
		e.setFromAddress(emailTemplate.getFromAddress());
		e.setSubject(emailTemplate.getSubject());
		e.setToAddress(emailTemplate.getToAddress());
		return e;
	}
}
