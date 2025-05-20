package com.example.demo.security;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.example.demo.auth.OidcServiceCustomImpl;
import jakarta.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	private final String CLIENT_URL = "http://localhost:4200";
	
	@Autowired
	private OidcServiceCustomImpl oidcCustom;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Order(1)
	public SecurityFilterChain regularLoginFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/", "/login", "/offer/**", "/register","/apply/**", "/email/**", "/logout", "/create")
		 .authorizeHttpRequests(
				 auth -> auth
				 .requestMatchers("/").permitAll()
				 .requestMatchers("/login").permitAll()
				 .requestMatchers("/offer/**").permitAll()
				 .requestMatchers("/register").permitAll()
				 .requestMatchers("/apply/**").permitAll()
				 .requestMatchers("/email/**").permitAll()
				 .anyRequest().authenticated())
		 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	     .csrf(AbstractHttpConfigurer::disable)
	     .exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, authException) -> {
	    	 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	     }))
	     .formLogin(form -> form
	    		 .loginPage(CLIENT_URL + "/login").loginProcessingUrl("/login")
	    		 .successHandler((request, response, authentication) -> {
	    			response.setStatus(HttpServletResponse.SC_OK);
	    		 })).logout(logout->logout.logoutSuccessHandler((request, response, authentication) ->{
	    			 response.setStatus(HttpServletResponse.SC_OK);
	    		 }));
	     return http.build();
	}
	
	@Bean
	@Order(2)
	public SecurityFilterChain oauthFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.anyRequest().permitAll())
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.oauth2Login(a -> a.userInfoEndpoint(userInfo -> userInfo.oidcUserService(oidcCustom))
				.successHandler((request, response, authentication) -> {
					response.sendRedirect(CLIENT_URL);
				}));
		return http.build();
	}
	
	 public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}


