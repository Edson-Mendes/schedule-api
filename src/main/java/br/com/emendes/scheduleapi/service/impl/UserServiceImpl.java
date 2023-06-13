package br.com.emendes.scheduleapi.service.impl;

import br.com.emendes.scheduleapi.component.AuthenticationFacade;
import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import br.com.emendes.scheduleapi.exception.PasswordsDoNotMatch;
import br.com.emendes.scheduleapi.exception.ResourceNotFoundException;
import br.com.emendes.scheduleapi.mapper.UserMapper;
import br.com.emendes.scheduleapi.model.entity.User;
import br.com.emendes.scheduleapi.repository.UserRepository;
import br.com.emendes.scheduleapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link UserService}
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final AuthenticationFacade authenticationFacade;
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

  @Override
  public Mono<UserResponse> findById(Long userId) {
    log.info("Searching for user with id: {}", userId);

    return authenticationFacade.getCurrentUser()
        .doOnNext(user -> {
          if (!isSameUserId(userId, user)) {
            throw new ResourceNotFoundException("User not found for id: " + userId);
          }
        })
        .flatMap(user -> findUserById(user.getId()))
        .map(userMapper::toUserResponse);
  }

  /**
   * Verifica se userId fornecido é igual ao id do User.
   */
  private boolean isSameUserId(Long userId, User user) {
    return user.getId().equals(userId);
  }

  /**
   * Busca User por id.
   *
   * @param userId identificador do User.
   * @return Em caso de sucesso, retorna Mono of User encontrado, e Mono of Error caso contrário.
   * @throws ResourceNotFoundException se User não for encontrado para o userId.
   */
  private Mono<User> findUserById(Long userId) {
    return userRepository.findById(userId)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found for id: " + userId)));
  }

}
