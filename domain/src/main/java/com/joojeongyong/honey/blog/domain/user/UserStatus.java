package com.joojeongyong.honey.blog.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
	ACTIVE("활성"),
	INACTIVE("비활성");
	
	private final String description;
	
	public String getStatus() {
		return name();
	}
}
