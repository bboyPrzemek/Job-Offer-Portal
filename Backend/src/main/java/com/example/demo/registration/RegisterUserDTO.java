package com.example.demo.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterUserDTO {
	private String email;
	private String password;
	private String displayName;
	
	@Override
	public String toString() {
		return "RegisterUserDTO [email=" + email + ", password=" + password + ", displayName=" + displayName + "]";
	}
}
