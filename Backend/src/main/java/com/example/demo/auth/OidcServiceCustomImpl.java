package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import com.example.demo.user.User;
import com.example.demo.user.UserService;

@Service
public class OidcServiceCustomImpl implements OAuth2UserService<OidcUserRequest, OidcUser>{
	
	@Autowired
	private UserService userService;

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2UserService delegate = new OidcUserService();
		OidcUser oidcUser = (OidcUser)delegate.loadUser(userRequest);
			
		User user = userService.saveOidcUser(oidcUser);
		
		return new OidcUserCustomImpl(user, oidcUser);
	}
}
