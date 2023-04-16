package com.joojeongyong.honey.blog.api.user;

import com.joojeongyong.honey.blog.api.user.request.UserRegisterRequest;
import com.joojeongyong.honey.blog.api.user.response.UserDetailResponse;
import com.joojeongyong.honey.blog.application.user.UserCommandService;
import com.joojeongyong.honey.blog.application.user.UserQueryService;
import com.joojeongyong.honey.blog.domain.user.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "계정", description = "계정 CRUD")
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@RestController
public class UserController {

  private final UserCommandService userCommandService;
  private final UserQueryService userQueryService;
  private final PasswordEncoder passwordEncoder;

  @Operation(
      summary = "계정 생성",
      description = "계정을 생성하는 API",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "요청 성공"
          ),
          @ApiResponse(
              responseCode = "400",
              description = "잘못된 요청"
          )
      }
  )
  @PostMapping
  public void registerUser(@RequestBody @Valid UserRegisterRequest request) {
    userCommandService.addUser(UserEntity.createActiveUser(request.name(), request.email(),
        passwordEncoder.encode(request.password()), request.roleCode()));
  }

  @PreAuthorize(value = "#userId == authentication.principal.userEntity.userId")
  @GetMapping("/{userId}")
  public UserDetailResponse getUser(
      @PathVariable Long userId
  ) {
    return UserDetailResponse.from(userQueryService.getUser(userId));
  }
}
