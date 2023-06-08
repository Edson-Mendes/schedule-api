package br.com.emendes.scheduleapi.service;

import br.com.emendes.scheduleapi.dto.request.CreateEventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import reactor.core.publisher.Mono;

/**
 * Interface service com as abstrações para manipulação do recurso Event.
 */
public interface EventService {

  /**
   * Cria um Event.
   * @param eventRequest contendo as informações do Event a ser salvo.
   * @return EventResponse contendo as informações do Event salvo.
   */
  Mono<EventResponse> create(CreateEventRequest eventRequest);

}
