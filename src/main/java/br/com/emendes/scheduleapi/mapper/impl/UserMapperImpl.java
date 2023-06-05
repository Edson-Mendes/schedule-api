package br.com.emendes.scheduleapi.mapper.impl;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import br.com.emendes.scheduleapi.mapper.UserMapper;
import br.com.emendes.scheduleapi.model.entity.User;
import org.springframework.stereotype.Component;

/**
 * Implementação de {@link UserMapper}
 */
@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public User toUser(RegisterUserRequest userRequest) {
    if (userRequest == null)
      throw new IllegalArgumentException("userRequest must not be null");

    return User.builder()
        .name(userRequest.name())
        .email(userRequest.email())
        .build();
  }

  @Override
  public UserResponse toUserResponse(User user) {
    if (user == null)
      throw new IllegalArgumentException("userRequest must not be null");

    return UserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }

}
