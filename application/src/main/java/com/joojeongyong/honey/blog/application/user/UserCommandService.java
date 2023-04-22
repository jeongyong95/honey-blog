package com.joojeongyong.honey.blog.application.user;

import com.joojeongyong.honey.blog.domain.user.UserCommandRepository;
import com.joojeongyong.honey.blog.domain.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserCommandService {
	private final UserCommandRepository userCommandRepository;
	
	public UserEntity addUser(UserEntity userEntity) {
		return userCommandRepository.addUser(userEntity);
	}
}
