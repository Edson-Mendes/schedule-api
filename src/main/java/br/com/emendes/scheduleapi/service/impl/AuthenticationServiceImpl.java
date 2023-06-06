package br.com.emendes.scheduleapi.service.impl;

import br.com.emendes.scheduleapi.config.security.service.JwtService;
import br.com.emendes.scheduleapi.dto.request.AuthenticationRequest;
import br.com.emendes.scheduleapi.dto.response.AuthenticationResponse;
import br.com.emendes.scheduleapi.model.entity.User;
import br.com.emendes.scheduleapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link AuthenticationService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final ReactiveAuthenticationManager reactiveAuthenticationManager;
  private final JwtService jwtService;

  @Override
  public Mono<AuthenticationResponse> authenticate(AuthenticationRequest authRequest) {
    return reactiveAuthenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()))
        .map(authentication -> {
          User user = (User) authentication.getPrincipal();
          String token = jwtService.generateJWT(user);

          log.info("JWT generated successfully for user {}", user.getUsername());

          return AuthenticationResponse.builder()
              .token(token)
              .type("Bearer")
              .build();
        });
  }

}
