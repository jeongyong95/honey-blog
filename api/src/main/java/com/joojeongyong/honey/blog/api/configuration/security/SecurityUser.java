package com.joojeongyong.honey.blog.api.configuration.security;

import com.joojeongyong.honey.blog.domain.user.UserEntity;
import com.joojeongyong.honey.blog.domain.user.UserStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
public class SecurityUser implements UserDetails {
	private final UserEntity userEntity;
	
	private final List<GrantedAuthority> authorities;
	
	private SecurityUser(UserEntity userEntity) {
		this.userEntity = userEntity;
		this.authorities = Arrays.asList(
			new SimpleGrantedAuthority(userEntity.getRoleCode().name())
		);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}
	
	@Override
	public String getUsername() {
		return userEntity.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		return Objects.equals(userEntity.getStatus(), UserStatus.ACTIVE);
	}
	
	
	public static SecurityUser of(UserEntity userEntity) {
		return new SecurityUser(userEntity);
	}
}
