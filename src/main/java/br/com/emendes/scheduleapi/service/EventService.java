package br.com.emendes.scheduleapi.service;

import br.com.emendes.scheduleapi.dto.request.CreateEventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

/**
 * Interface service com as abstrações para manipulação do recurso Event.
 */
public interface EventService {

  /**
   * Cria um Event.
   *
   * @param eventRequest contendo as informações do Event a ser salvo.
   * @return EventResponse contendo as informações do Event salvo.
   */
  Mono<EventResponse> create(CreateEventRequest eventRequest);

  /**
   * Busca paginada de Events relacionadas com o usuário atual.
   */
  Mono<Page<EventResponse>> fetchAll(Pageable pageable);

  /**
   * Busca Event por User e Event#id.
   *
   * @param eventId identificador do Event.
   * @return Mono of EventResponse.
   */
  Mono<EventResponse> findById(Long eventId);

}
