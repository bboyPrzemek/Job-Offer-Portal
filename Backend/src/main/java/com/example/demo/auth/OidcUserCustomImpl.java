package com.example.demo.auth;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import com.example.demo.user.User;

public class OidcUserCustomImpl implements OidcUser{
	
	private User user;
	private OidcUser oidcUser;
	
	public OidcUserCustomImpl(User user, OidcUser oidcUser) {
		this.user = user;
		this.oidcUser = oidcUser;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.oidcUser.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.oidcUser.getAuthorities();
	}

	@Override
	public String getName() {
		return this.oidcUser.getName();
	}

	@Override
	public Map<String, Object> getClaims() {
		return this.oidcUser.getClaims();
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return this.oidcUser.getUserInfo();
	}

	@Override
	public OidcIdToken getIdToken() {
		return this.oidcUser.getIdToken();
	}
	
	public User getUser() {
		return this.user;
	}

}
