package com.joojeongyong.honey.blog.api.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginRequest {
	@Email
	private String email;
	
	@NotEmpty
	private String password;
}
