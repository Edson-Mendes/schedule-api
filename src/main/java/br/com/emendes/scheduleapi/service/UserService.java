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

  /**
   * Busca Event por User e Event#id. Usuário comum só pode buscar a si mesmo.
   *
   * @param userId Identificador do User a ser buscado.
   * @return Mono of UserResponse contendo as informações do usuário encontrado.
   */
  Mono<UserResponse> findById(Long userId);
}
