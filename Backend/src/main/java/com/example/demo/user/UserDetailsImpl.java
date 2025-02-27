package com.example.demo.user;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
		
	}

	@Override
	public String getUsername() {
		return user.getEmail();
		
	}

	public Long getId() {
		return user.getId();
	}
	
	public String getDisplayName() {
		return user.getDisplayName();
	}
}
