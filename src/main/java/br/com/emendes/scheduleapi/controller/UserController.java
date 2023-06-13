package br.com.emendes.scheduleapi.controller;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import br.com.emendes.scheduleapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Controller o qual é mapeado as requisições de /api/users.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  /**
   * Trata requisição POST /api/users.
   *
   * @param userRequest DTO que contém as informações do User a ser registrado.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<UserResponse> register(@Valid @RequestBody RegisterUserRequest userRequest) {
    return userService.register(userRequest);
  }

  /**
   * Trata requisição GET /api/users. Apenas ADMIN podem usar esse endpoint.
   *
   * @param page Número da página a ser buscada, valor padrão é 0.
   * @param size Tamanho da página a ser buscada, valor padrão é 10.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Mono<Page<UserResponse>> fetchAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size
  ) {
    return userService.fetchAll(page, size);
  }

  /**
   * Trata requisição GET /api/users/{userId}.
   *
   * @param userId Identificador do User a ser buscado.
   */
  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<UserResponse> findById(@PathVariable(name = "userId") Long userId) {
    return userService.findById(userId);
  }

}
