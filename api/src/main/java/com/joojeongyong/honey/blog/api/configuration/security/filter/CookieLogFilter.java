package com.joojeongyong.honey.blog.api.configuration.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CookieLogFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		var httpRequest = (HttpServletRequest) request;
		log.info("------------------------------");
		log.info("쿠키 확인 필터 Request URI : " + httpRequest.getRequestURI());
		for (Cookie cookie : httpRequest.getCookies()) {
			log.info(cookie.getName() + " : " + cookie.getValue());
		}
		log.info("------------------------------");
		chain.doFilter(request, response);
	}
}
