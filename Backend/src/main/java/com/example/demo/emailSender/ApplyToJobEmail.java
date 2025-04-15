package com.example.demo.emailSender;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.joboffer.JobOffer;

public class ApplyToJobEmail implements EmailTemplate {
	
	private String toAddress;
	private MultipartFile file;
	private JobOffer jobOffer;
	
	public ApplyToJobEmail(String toAddress, MultipartFile file, JobOffer jobOffer) {
		this.file = file;
		this.toAddress = toAddress;
		this.jobOffer = jobOffer;
	}

	@Override
	public String getBody() {
		return "Someone's just applied for your job offer";
	}

	@Override
	public String getSubject() {
		return "Candidate has applied for " + jobOffer.getTitle();
	}

	@Override
	public String getFromAddress() {
		return "contact@demomailtrap.co";
	}

	@Override
	public String getToAddress() {
		return this.toAddress;
	}

	@Override
	public MultipartFile getAttachment() {
		return this.file;
	}
}
