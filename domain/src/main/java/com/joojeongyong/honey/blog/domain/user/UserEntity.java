package com.joojeongyong.honey.blog.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserEntity {
	private Long userId;
	private String name;
	private String email;
	private String password;
	private Role roleCode;
	private UserStatus status;
	
	public static UserEntity of(
		Long userId, String name, String email, String password, Role roleCode, UserStatus status
	) {
		return UserEntity.builder()
			.userId(userId)
			.name(name)
			.email(email)
			.password(password)
			.roleCode(roleCode)
			.status(status)
			.build();
	}
}
