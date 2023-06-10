package br.com.emendes.scheduleapi.repository;

import br.com.emendes.scheduleapi.model.entity.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

/**
 * Interface repository com as abstrações para persistência do recurso Event.
 */
public interface EventRepository extends ReactiveCrudRepository<Event, Long>, ReactiveSortingRepository<Event, Long> {

  Flux<Event> findAllBy(Pageable pageable);
  Flux<Event> findByUserId(long userId, Pageable pageable);



}
