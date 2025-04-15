package com.example.demo.exceptions;

public class EmailServiceException extends RuntimeException{
	public EmailServiceException(String message) {
		super(message);	
	}
}
