package com.joojeongyong.honey.blog.api.user.response;

import com.joojeongyong.honey.blog.domain.user.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record UserDetailResponse(
    @Schema(description = "계정 ID")
    Long userId,

    @Schema(description = "이름")
    String name,

    @Schema(description = "이메일")
    String email,
    @Schema(description = "역할 코드")
    String roleCode,

    @Schema(description = "역할 설명")
    String roleDescription
) {

  public static UserDetailResponse from(UserEntity user) {
    return UserDetailResponse.builder()
        .userId(user.getUserId())
        .name(user.getName())
        .email(user.getEmail())
        .roleCode(user.getRoleCode().name())
        .roleDescription(user.getRoleCode().getDescription())
        .build();
  }
}
