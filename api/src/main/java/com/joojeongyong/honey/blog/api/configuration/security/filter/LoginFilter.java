package com.joojeongyong.honey.blog.api.configuration.security.filter;

import com.joojeongyong.honey.blog.api.auth.LoginRequest;
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
	protected LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(defaultFilterProcessesUrl, authenticationManager);
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
		// TODO : JWT를 발행해서 response에 내려준다.
		
		response.setStatus(HttpStatus.OK.value());
		response.setHeader(HttpHeaders.AUTHORIZATION, "");
		response.addCookie(generateRefreshTokenCookie(""));
	}
	
	private Cookie generateRefreshTokenCookie(String refreshToken) {
		var cookie = new Cookie("refresh_token", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(60 * 30);
		
		return cookie;
	}
}
