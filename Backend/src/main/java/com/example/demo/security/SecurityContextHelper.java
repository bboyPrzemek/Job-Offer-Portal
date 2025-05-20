package com.example.demo.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import com.example.demo.auth.OidcUserCustomImpl;
import com.example.demo.user.UserDao;
import com.example.demo.user.UserDetailsImpl;

@Component
public class SecurityContextHelper {
	
    public UserDao getUserDetails() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDao userDao = null;
        
        if (authentication instanceof OAuth2AuthenticationToken) {
        	OidcUserCustomImpl oidcUser = (OidcUserCustomImpl)authentication.getPrincipal();
        	userDao = new UserDao(oidcUser.getUser().getId(), oidcUser.getEmail(), oidcUser.getUser().getDisplayName(), oidcUser.getPicture());
        }
        else if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
            userDao = new UserDao(userDetailsImpl.getId(),userDetailsImpl.getUsername(), userDetailsImpl.getDisplayName(),"");
        }
        return userDao;
    }
}
