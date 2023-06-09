package br.com.emendes.scheduleapi.controller;

import br.com.emendes.scheduleapi.dto.request.AuthenticationRequest;
import br.com.emendes.scheduleapi.dto.response.AuthenticationResponse;
import br.com.emendes.scheduleapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Controller o qual é mapeado as requisições de /api/auth.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Método que trata a requisição POST /api/auth<br>
   * @param authenticationRequest contendo as informações de autenticação de User.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public Mono<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
    return authenticationService.authenticate(authenticationRequest);
  }

}
