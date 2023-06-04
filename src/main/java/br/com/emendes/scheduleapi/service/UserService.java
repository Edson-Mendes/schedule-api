package br.com.emendes.scheduleapi.service;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import reactor.core.publisher.Mono;

/**
 * Interface service com as abstrações para manipulação do recurso User.
 */
public interface UserService {

  /**
   * Registra um User no sistema.
   *
   * @param userRequest contendo as informações sobre User.
   * @return UserResponse contendo informações do User registrado.
   */
  Mono<UserResponse> register(RegisterUserRequest userRequest);

}
