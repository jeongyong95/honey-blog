package com.joojeongyong.honey.blog.application.user;

import com.joojeongyong.honey.blog.domain.user.UserCommandRepository;
import com.joojeongyong.honey.blog.domain.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserCommandService {
	private UserCommandRepository userCommandRepository;
	
	public UserEntity addUser(UserEntity userEntity) {
		return userCommandRepository.addUser(userEntity);
	}
}
