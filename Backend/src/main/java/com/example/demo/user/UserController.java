package com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/me")
	public UserDao userDetails() {
		return userService.getUserDetails();
	}
	
	@GetMapping("/status")
	public Boolean getAuthStatus() {
		return true;
	}
}
