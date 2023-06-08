package br.com.emendes.scheduleapi.repository;

import br.com.emendes.scheduleapi.model.entity.Event;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Interface repository com as abstrações para persistência do recurso Event.
 */
public interface EventRepository extends ReactiveCrudRepository<Event, Long> {

}
