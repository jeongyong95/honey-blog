package com.joojeongyong.honey.blog.api.configuration.security;

import com.joojeongyong.honey.blog.api.auth.TokenResponse;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Component
public class JwtProvider {
	@Value("${spring.application.name:honey-blog}")
	private String applicationName;
	
	@Value("${honey.blog.secret:An application for Sungheon, who I love the most in the world.}")
	private String secret;
	
	private Key secretKey;
	
	@PostConstruct
	public void init() {
		secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	
	public TokenResponse generateTokens(String email) {
		var issuedAt = LocalDateTime.now();
		return new TokenResponse(
			"Bearer " + generateAccessToken(email, issuedAt),
			generateRefreshToken(email, issuedAt),
			issuedAt
		);
	}
	
	private String generateAccessToken(String audience, LocalDateTime issuedAt) {
		return generateToken(audience, issuedAt)
			.setSubject("access_token")
			.setExpiration(Timestamp.valueOf(issuedAt.plusMinutes(30L)))
			.signWith(secretKey)
			.compact();
	}
	
	private String generateRefreshToken(String audience, LocalDateTime issuedAt) {
		return generateToken(audience, issuedAt)
			.setSubject("refresh_token")
			.setExpiration(Timestamp.valueOf(issuedAt.plusDays(7L)))
			.signWith(secretKey)
			.compact();
	}
	
	private JwtBuilder generateToken(String audience, LocalDateTime issuedAt) {
		return Jwts.builder()
			.setIssuedAt(Timestamp.valueOf(issuedAt))
			.setAudience(audience)
			.setIssuer(applicationName);
	}
	
	public String getAudience(String accessToken) {
		if (accessToken == null) {
			throw new JwtException("토큰이 없습니다.");
		}
		if (!accessToken.startsWith("Bearer ")) {
			throw new MalformedJwtException("Bearer 토큰이 아닙니다.");
		}
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.requireIssuer(applicationName)
			.requireSubject("access_token")
			.build()
			.parseClaimsJws(accessToken.substring(7))
			.getBody()
			.getAudience();
	}
}
