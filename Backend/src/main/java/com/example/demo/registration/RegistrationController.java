package com.example.demo.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.User;
import com.example.demo.user.UserService;

@RestController
public class RegistrationController {
	
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterUserDTO registerUserDTO) {
			User user = userService.saveUser(registerUserDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Created");
	}
}
