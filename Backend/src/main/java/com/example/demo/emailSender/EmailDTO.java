package com.example.demo.emailSender;

import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmailDTO {
	private String fromAddress;
	private String toAddress;
	private String subject;
	private String body;
	private MultipartFile attachment;
}
