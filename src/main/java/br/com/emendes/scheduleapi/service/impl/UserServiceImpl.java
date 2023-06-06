package br.com.emendes.scheduleapi.service.impl;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import br.com.emendes.scheduleapi.exception.PasswordsDoNotMatch;
import br.com.emendes.scheduleapi.mapper.UserMapper;
import br.com.emendes.scheduleapi.model.entity.User;
import br.com.emendes.scheduleapi.repository.UserRepository;
import br.com.emendes.scheduleapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link UserService}
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<UserResponse> register(RegisterUserRequest userRequest) {
    if (!userRequest.passwordsMatch()) {
      throw new PasswordsDoNotMatch("Password and confirmPassword do not matches");
    }

    User user = userMapper.toUser(userRequest);
    user.setPassword(passwordEncoder.encode(userRequest.password()));
    user.setRoles("ROLE_USER");

    return userRepository.save(user)
        .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail already in use")))
        .map(userMapper::toUserResponse);
  }

}
