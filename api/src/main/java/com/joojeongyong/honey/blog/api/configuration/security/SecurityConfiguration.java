package com.joojeongyong.honey.blog.api.configuration.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	private final List<AuthenticationProvider> authenticationProviders;
	private final CustomUserDetailService userDetailService;
	private final CustomLogoutHandler logoutHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.formLogin().disable()
			.httpBasic().disable()
			.csrf().disable()
			.cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
			.authorizeHttpRequests(customizer ->
				customizer.requestMatchers("/actuator/**", "/", "/hello", "/v1/auth/login")
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			.userDetailsService(userDetailService)
			.logout(customizer ->
				customizer.logoutUrl("/v1/auth/logout")
					.addLogoutHandler(logoutHandler)
					.deleteCookies("refresh_token"))
			.exceptionHandling(customizer -> customizer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				.accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(HttpStatus.FORBIDDEN.value())))
			.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProviders);
	}
	
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		return null;
	}
}
