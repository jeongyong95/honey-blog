package com.joojeongyong.honey.blog.api.user;

import com.joojeongyong.honey.blog.domain.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequest {
	
	public record UserAddRequest(
		
		@NotBlank
		String name,
		
		@Email
		@NotBlank
		String email,
		
		@NotBlank
		String password,
		
		@NotNull
		Role roleCode
	) {
	}
}
