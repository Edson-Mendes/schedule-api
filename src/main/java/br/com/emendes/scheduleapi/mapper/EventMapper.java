package br.com.emendes.scheduleapi.mapper;

import br.com.emendes.scheduleapi.dto.request.EventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import br.com.emendes.scheduleapi.model.entity.Event;

/**
 * Interface component que contém as abstrações de mapeamento de DTOs para a entidade Event e vice-versa.
 */
public interface EventMapper {

  /**
   * Mapeia o DTO {@link EventRequest} para a entidade {@link Event}.<br>
   * CreateEventRequest não deve ser null.
   *
   * @param eventRequest que será mapeado para Event
   * @return Event contendo as informações que estavam em eventRequest.
   */
  Event toEvent(EventRequest eventRequest);

  /**
   * Mapeia uma entidade Event para o DTO {@link EventResponse}.<br>
   * Event não deve ser null.
   *
   * @param event que será mapeado para EventResponse.
   * @return EventResponse contendo informações que estavam em Event.
   */
  EventResponse toEventResponse(Event event);

  /**
   * Insere as informações que estão em EventRequest em Event.
   * @param event Entidade que receberá as novas informações.
   * @param eventRequest DTO contendo as informações que serão inseridas.
   */
  void merge(Event event, EventRequest eventRequest);

}
