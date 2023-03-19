package com.joojeongyong.honey.blog.api.auth;

import java.time.LocalDateTime;

public record TokenResponse(
	String accessToken,
	String refreshToken,
	LocalDateTime issuedAt
) {
}
