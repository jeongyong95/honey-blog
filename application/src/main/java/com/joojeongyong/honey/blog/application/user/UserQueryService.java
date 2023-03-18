package com.joojeongyong.honey.blog.application.user;

import com.joojeongyong.honey.blog.domain.user.UserEntity;
import com.joojeongyong.honey.blog.domain.user.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserQueryService {
	private final UserQueryRepository userQueryRepository;
	
	public UserEntity getUser(Long userId) {
		return userQueryRepository.getUser(userId);
	}
	
	public UserEntity getUser(String email) {
		return userQueryRepository.getUser(email);
	}
}
