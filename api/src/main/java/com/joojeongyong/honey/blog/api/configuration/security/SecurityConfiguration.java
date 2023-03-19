package com.joojeongyong.honey.blog.api.configuration.security;

import com.joojeongyong.honey.blog.api.configuration.security.filter.CookieLogFilter;
import com.joojeongyong.honey.blog.api.configuration.security.filter.JwtAuthenticationFilter;
import com.joojeongyong.honey.blog.api.configuration.security.filter.LoginFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
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
	private final JwtProvider jwtProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.formLogin().disable()
			.httpBasic().disable()
			.csrf().disable()
			.cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
			.sessionManagement(customizer ->
				customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.authorizeHttpRequests(customizer ->
				customizer.requestMatchers("/actuator/**",
						"/", "/hello", "/v1/auth/login", "/v1/users"
					)
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			.addFilterBefore(
				new LoginFilter("/v1/auth/login", authenticationManager(), jwtProvider),
				UsernamePasswordAuthenticationFilter.class
			)
			.addFilterBefore(new JwtAuthenticationFilter(jwtProvider, userDetailService), LoginFilter.class)
			.addFilterBefore(new CookieLogFilter(), LogoutFilter.class)
			.logout(customizer ->
				customizer.logoutUrl("/v1/auth/logout")
					.addLogoutHandler(logoutHandler)
					.deleteCookies("refresh_token")
					.logoutSuccessHandler(
						(request, response, authentication) -> {
							response.setStatus(HttpStatus.OK.value());
							log.info("로그아웃 성공");
						}
					))
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
		var configuration = new CorsConfiguration();
		configuration.setAllowedMethods(Arrays.asList(
			HttpMethod.OPTIONS.name(),
			HttpMethod.GET.name(), HttpMethod.POST.name(),
			HttpMethod.PUT.name(), HttpMethod.DELETE.name()
		));
		configuration.setAllowedOrigins(List.of(
			"http://localhost:3000"
		));
		configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
		
		var configurationSource = new UrlBasedCorsConfigurationSource();
		configurationSource.registerCorsConfiguration("/**", configuration);
		return configurationSource;
	}
}
