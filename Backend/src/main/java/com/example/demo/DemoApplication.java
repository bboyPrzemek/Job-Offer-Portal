package com.example.demo;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		SpringApplication.run(DemoApplication.class, args);
	}
}
