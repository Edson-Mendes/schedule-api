package br.com.emendes.scheduleapi.repository;

import br.com.emendes.scheduleapi.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface repository com as abstrações para persistência do recurso User.
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long>, ReactiveSortingRepository<User, Long> {


  /**
   * Busca UserDetails por username.
   *
   * @param username corresponde ao E-mail do User.
   * @return Mono of UserDetails.
   */
  Mono<UserDetails> findByEmail(String username);

  /**
   * Busca paginada de User.
   * @param pageable Contendo as informações de paginação.
   * @return Flux of User.
   */
  Flux<User> findBy(Pageable pageable);

}
