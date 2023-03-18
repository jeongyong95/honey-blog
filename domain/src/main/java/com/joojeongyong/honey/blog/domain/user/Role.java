package com.joojeongyong.honey.blog.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	ROLE_ADMIN("ADMIN", "관리자"),
	ROLE_VISITOR("VISITOR", "방문자");
	
	private final String shortCode;
	private final String description;
}
