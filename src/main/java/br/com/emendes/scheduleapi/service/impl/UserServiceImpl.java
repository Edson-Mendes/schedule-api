package br.com.emendes.scheduleapi.service.impl;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import br.com.emendes.scheduleapi.exception.PasswordsDoNotMatch;
import br.com.emendes.scheduleapi.mapper.UserMapper;
import br.com.emendes.scheduleapi.model.entity.User;
import br.com.emendes.scheduleapi.repository.UserRepository;
import br.com.emendes.scheduleapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link UserService}
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Override
  public Mono<UserResponse> register(RegisterUserRequest userRequest) {
    if (!userRequest.passwordsMatch()) {
      throw new PasswordsDoNotMatch("password and confirmPassword do not matches");
    }

    User user = userMapper.toUser(userRequest);

    // TODO: Encriptar o password e settar no user.

    return userRepository.save(user)
        .map(userMapper::toUserResponse);
  }

}