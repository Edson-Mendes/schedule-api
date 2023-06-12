package br.com.emendes.scheduleapi.repository;

import br.com.emendes.scheduleapi.model.entity.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface repository com as abstrações para persistência do recurso Event.
 */
public interface EventRepository extends ReactiveCrudRepository<Event, Long>, ReactiveSortingRepository<Event, Long> {

  /**
   * Busca Flux of Event por userId e pageable.
   * @param userId id do User.
   * @param pageable Contendo page number e page size.
   * @return Flux of Event.
   */
  Flux<Event> findByUserId(long userId, Pageable pageable);

  /**
   * Conta quantos events um User possui.
   * @param userId id do User
   * @return Número de Event que o User possui.
   */
  Mono<Long> countByUserId(Long userId);

  /**
   * Busca Event por Event#id e Event#userId.
   * @param eventId id do Event
   * @param userId id do User a quem o Event pertence.
   * @return Mono of Event.
   */
  Mono<Event> findByIdAndUserId(Long eventId, Long userId);
}
