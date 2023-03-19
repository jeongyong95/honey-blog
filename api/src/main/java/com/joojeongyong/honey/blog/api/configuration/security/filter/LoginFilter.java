package com.joojeongyong.honey.blog.api.configuration.security.filter;

import com.joojeongyong.honey.blog.api.auth.LoginRequest;
import com.joojeongyong.honey.blog.api.configuration.security.JwtProvider;
import com.joojeongyong.honey.blog.api.configuration.security.LoginAuthenticationToken;
import com.joojeongyong.honey.blog.domain.util.JsonUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

@Slf4j
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	private final JwtProvider jwtProvider;
	
	public LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
		super(defaultFilterProcessesUrl, authenticationManager);
		this.jwtProvider = jwtProvider;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		var loginRequest = JsonUtils.read(request.getInputStream(), LoginRequest.class);
		
		return getAuthenticationManager().authenticate(
			LoginAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword())
		);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		var tokenResponse = jwtProvider.generateTokens(authResult.getName());
		
		response.setStatus(HttpStatus.OK.value());
		response.setHeader(HttpHeaders.AUTHORIZATION, tokenResponse.accessToken());
		response.addCookie(generateRefreshTokenCookie(tokenResponse.refreshToken()));
	}
	
	private Cookie generateRefreshTokenCookie(String refreshToken) {
		var cookie = new Cookie("refresh_token", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(60 * 60 * 24 * 7);
		return cookie;
	}
}
