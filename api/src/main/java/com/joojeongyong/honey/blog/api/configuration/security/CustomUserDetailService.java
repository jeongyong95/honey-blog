package com.joojeongyong.honey.blog.api.configuration.security;

import com.joojeongyong.honey.blog.application.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomUserDetailService implements UserDetailsService {
	private final UserQueryService userQueryService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return SecurityUser.of(userQueryService.getUser(username));
	}
}
