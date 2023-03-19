package com.joojeongyong.honey.blog.api.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class JwtAuthentication implements Authentication {
	private final UserDetails userDetails;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userDetails.getAuthorities();
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}
	
	@Override
	public Object getDetails() {
		return userDetails;
	}
	
	@Override
	public Object getPrincipal() {
		return userDetails;
	}
	
	@Override
	public boolean isAuthenticated() {
		return userDetails != null;
	}
	
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	}
	
	@Override
	public String getName() {
		return userDetails.getUsername();
	}
}
