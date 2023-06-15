package br.com.emendes.scheduleapi.repository;

import br.com.emendes.scheduleapi.model.entity.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * Interface repository com as abstrações para persistência do recurso Event.
 */
public interface EventRepository extends ReactiveCrudRepository<Event, Long>, ReactiveSortingRepository<Event, Long> {

  /**
   * Busca Flux of Event por userId e pageable.
   *
   * @param userId   id do User.
   * @param pageable Contendo page number e page size.
   * @return Flux of Event.
   */
  Flux<Event> findByUserId(long userId, Pageable pageable);

  /**
   * Conta quantos events um User possui.
   *
   * @param userId id do User
   * @return Número de Event que o User possui.
   */
  Mono<Long> countByUserId(Long userId);

  /**
   * Busca Event por Event#id e Event#userId.
   *
   * @param eventId id do Event
   * @param userId  id do User a quem o Event pertence.
   * @return Mono of Event.
   */
  Mono<Event> findByIdAndUserId(Long eventId, Long userId);

  /**
   * Busca Flux of events por userId, eventDate (yyyy-MM-dd), limit e offset.
   *
   * @param userId    identificador do User
   * @param eventDate data do Event no formato (yyyy-MM-dd)
   * @param limit     limit a ser buscado
   * @param offset    offset a ser buscado
   * @return Flux of Event
   */
  @Query("SELECT * FROM t_event te WHERE DATE(te.event_date) = :eventDate AND te.user_id = :userId LIMIT :limit OFFSET :offset")
  Flux<Event> findByDate(Long userId, LocalDate eventDate, int limit, int offset);

  /**
   * Conta quantos events um User possui em dada data.
   *
   * @param userId    id do User
   * @param eventDate data a ser contada.
   * @return Número de Event que o User possui em dada data.
   */
  @Query("SELECT count(*) FROM t_event te WHERE DATE(te.event_date) = :eventDate AND te.user_id = :userId")
  Mono<Long> countByUserIdAndDate(Long userId, LocalDate eventDate);

  /**
   * Busca Flux of events por userId, eventDate (yyyy-MM-dd), limit e offset.<br>
   * Necessário para banco de dados H2, pois para pegar yyyy-MM-dd da coluna event_date usa-se a função
   * FORMATDATETIME(column_name, format).
   *
   * @param userId    identificador do User
   * @param eventDate data do Event no formato (yyyy-MM-dd)
   * @param limit     limit a ser buscado
   * @param offset    offset a ser buscado
   * @return Flux of Event
   */
  @Query("SELECT * FROM t_event te WHERE FORMATDATETIME(te.event_date,'yyyy-MM-dd') = :eventDate AND te.user_id = :userId LIMIT :limit OFFSET :offset")
  Flux<Event> findByDateH2(Long userId, LocalDate eventDate, int limit, int offset);

  /**
   * Conta quantos events um User possui em dada data.
   * Necessário para banco de dados H2, pois para pegar yyyy-MM-dd da coluna event_date usa-se a função
   * FORMATDATETIME(column_name, format).
   *
   * @param userId    id do User
   * @param eventDate data a ser contada.
   * @return Número de Event que o User possui em dada data.
   */
  @Query("SELECT count(*) FROM t_event te WHERE FORMATDATETIME(te.event_date,'yyyy-MM-dd') = :eventDate AND te.user_id = :userId")
  Mono<Long> countByUserIdAndDateH2(Long userId, LocalDate eventDate);

}
