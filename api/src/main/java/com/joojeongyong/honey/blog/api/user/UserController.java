package com.joojeongyong.honey.blog.api.user;

import com.joojeongyong.honey.blog.application.user.UserCommandService;
import com.joojeongyong.honey.blog.domain.user.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1/users")
@RestController
public class UserController {
	private final UserCommandService userCommandService;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping
	public void registerUser(@RequestBody @Valid UserRequest.UserAddRequest request) {
		userCommandService.addUser(UserEntity.createActiveUser(request.name(), request.email(), passwordEncoder.encode(request.password()), request.roleCode()));
	}
}
