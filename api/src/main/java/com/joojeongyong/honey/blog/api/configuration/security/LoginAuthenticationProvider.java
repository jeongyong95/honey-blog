package com.joojeongyong.honey.blog.api.configuration.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var user = userDetailsService.loadUserByUsername(authentication.getName());
		if (passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
			return LoginAuthenticationToken.authenticated(user.getUsername(), user.getAuthorities());
		}
		return null;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return LoginAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
