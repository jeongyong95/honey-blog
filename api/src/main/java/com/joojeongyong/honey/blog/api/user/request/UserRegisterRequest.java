package com.joojeongyong.honey.blog.api.user.request;

import com.joojeongyong.honey.blog.domain.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(

    @Schema(
        description = "이름",
        example = "주주"
    )
    @NotBlank
    String name,

    @Schema(
        description = "이메일",
        example = "test111@test.com"

    )
    @Email
    @NotBlank
    String email,

    @Schema(
        description = "비밀번호",
        example = "password123"
    )
    @NotBlank
    String password,

    @Schema(
        description = "역할(권한)",
        example = "ROLE_VISITOR"
    )
    @NotNull
    Role roleCode
) {
    
}
