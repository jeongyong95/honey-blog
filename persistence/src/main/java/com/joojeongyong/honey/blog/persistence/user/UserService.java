package com.joojeongyong.honey.blog.persistence.user;

import com.joojeongyong.honey.blog.domain.user.UserCommandRepository;
import com.joojeongyong.honey.blog.domain.user.UserEntity;
import com.joojeongyong.honey.blog.domain.user.UserQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService implements UserCommandRepository, UserQueryRepository {
	private final UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserEntity getUser(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException(String.valueOf(userId)))
			.toEntity();
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserEntity getUser(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new EntityNotFoundException(email))
			.toEntity();
	}
	
	@Override
	public UserEntity addUser(UserEntity userEntity) {
		return userRepository.save(User.from(userEntity))
			.toEntity();
	}
}
