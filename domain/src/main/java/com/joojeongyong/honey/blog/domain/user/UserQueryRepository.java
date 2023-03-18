package com.joojeongyong.honey.blog.domain.user;

public interface UserQueryRepository {
	UserEntity getUser(Long userId);
	
	UserEntity getUser(String email);
}
