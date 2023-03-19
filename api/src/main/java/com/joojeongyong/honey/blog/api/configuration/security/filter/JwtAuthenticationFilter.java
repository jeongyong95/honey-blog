package com.joojeongyong.honey.blog.api.configuration.security.filter;

import com.joojeongyong.honey.blog.api.configuration.security.JwtAuthentication;
import com.joojeongyong.honey.blog.api.configuration.security.JwtProvider;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtProvider jwtProvider;
	private final UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		try {
			String email = jwtProvider.getAudience(accessToken);
			
			SecurityContextHolder.getContext()
				.setAuthentication(
					new JwtAuthentication(userDetailsService.loadUserByUsername(email))
				);
		} catch (JwtException e) {
			log.error(e.getMessage());
		}
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getRequestURI().equals("/v1/auth/login") ||
			(request.getRequestURI().equals("/v1/users") && request.getMethod().equals(HttpMethod.POST.name()));
	}
}
