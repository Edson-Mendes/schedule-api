package br.com.emendes.scheduleapi.controller;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import br.com.emendes.scheduleapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Classe o qual é mapeado as requisições de /api/users.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  /**
   * Método que trata requisição POST /api/users.
   * @param userRequest DTO que contém as informações do User a ser registrado.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<UserResponse> register(@Valid @RequestBody RegisterUserRequest userRequest) {
    return userService.register(userRequest);
  }

}
