package br.com.emendes.scheduleapi.service;

import br.com.emendes.scheduleapi.dto.request.EventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

/**
 * Interface service com as abstrações para manipulação do recurso Event.
 */
public interface EventService {

  /**
   * Cria um Event.
   *
   * @param eventRequest DTO contendo as informações do Event a ser salvo.
   * @return EventResponse contendo as informações do Event salvo.
   */
  Mono<EventResponse> create(EventRequest eventRequest);

  /**
   * Busca paginada de Events relacionadas com o usuário atual.
   *
   * @param page Página a ser buscada.
   * @param size Tamanho da página a ser buscada.
   */
  Mono<Page<EventResponse>> fetchAll(int page, int size);

  /**
   * Busca Event por User e Event#id.
   *
   * @param eventId identificador do Event.
   * @return Mono of EventResponse.
   */
  Mono<EventResponse> findById(Long eventId);

  /**
   * Busca paginada de Event por data (yyyy-MM-dd)
   *
   * @param date Data no formato yyyy-MM-dd
   * @param page Número da página requerida
   * @param size Tamanho da página requerida.
   */
  Mono<Page<EventResponse>> findByDate(String date, int page, int size);

  /**
   * Atualiza um Event por id.
   *
   * @param eventId      identicador do Event.
   * @param eventRequest DTO contendo as novas informações do Event.
   */
  Mono<Void> update(Long eventId, EventRequest eventRequest);

  /**
   * Deleta um Event por id.
   *
   * @param eventId identificador do Event.
   */
  Mono<Void> delete(Long eventId);

}
