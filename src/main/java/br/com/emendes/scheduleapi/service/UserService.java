package br.com.emendes.scheduleapi.service;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import org.springframework.data.domain.Page;
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
   * Busca paginada de User.
   *
   * @param page Página a ser buscada.
   * @param size Tamanho da página a ser buscada.
   * @return Mono of UserResponse paginado.
   */
  Mono<Page<UserResponse>> fetchAll(int page, int size);

  /**
   * Busca Event por User e Event#id. Usuário comum só pode buscar a si mesmo.
   *
   * @param userId Identificador do User a ser buscado.
   * @return Mono of UserResponse contendo as informações do usuário encontrado.
   */
  Mono<UserResponse> findById(Long userId);

}
