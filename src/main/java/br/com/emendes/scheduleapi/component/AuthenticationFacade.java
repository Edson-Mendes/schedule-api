package br.com.emendes.scheduleapi.component;

import br.com.emendes.scheduleapi.model.entity.User;
import reactor.core.publisher.Mono;

/**
 * Interface component com as abstrações para buscar current authentication e current User.
 */
public interface AuthenticationFacade {

  /**
   * Retorna o User logado na requisição atual.
   */
  Mono<User> getCurrentUser();

}
