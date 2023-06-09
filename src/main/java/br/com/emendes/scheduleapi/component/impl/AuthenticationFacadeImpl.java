package br.com.emendes.scheduleapi.component.impl;

import br.com.emendes.scheduleapi.component.AuthenticationFacade;
import br.com.emendes.scheduleapi.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link AuthenticationFacade}
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

  @Override
  public Mono<User> getCurrentUser() {
    return getAuthentication().map(auth -> (User) auth.getPrincipal());
  }

  private Mono<Authentication> getAuthentication() {
    return ReactiveSecurityContextHolder
        .getContext().map(SecurityContext::getAuthentication);
  }

}
