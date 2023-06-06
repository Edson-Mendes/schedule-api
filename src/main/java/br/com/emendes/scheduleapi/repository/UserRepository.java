package br.com.emendes.scheduleapi.repository;

import br.com.emendes.scheduleapi.model.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

/**
 * Interface repository com as abstrações para persistência do recurso User.
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

  Mono<UserDetails> findByEmail(String username);

}
