package com.joojeongyong.honey.blog.persistence.user;

import com.joojeongyong.honey.blog.domain.user.Role;
import com.joojeongyong.honey.blog.domain.user.UserEntity;
import com.joojeongyong.honey.blog.domain.user.UserStatus;
import com.joojeongyong.honey.blog.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {
	@GeneratedValue
	@Id
	private Long userId;
	
	private String name;
	
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role roleCode;
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	public static User from(UserEntity userEntity) {
		return User.builder()
			.name(userEntity.getName())
			.email(userEntity.getEmail())
			.password(userEntity.getPassword())
			.roleCode(userEntity.getRoleCode())
			.status(userEntity.getStatus())
			.build();
	}
	
	public UserEntity toEntity() {
		return UserEntity.of(userId, name, email, password, roleCode, status);
	}
}
