package br.com.emendes.scheduleapi.mapper.impl;

import br.com.emendes.scheduleapi.dto.request.EventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import br.com.emendes.scheduleapi.mapper.EventMapper;
import br.com.emendes.scheduleapi.model.entity.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Implementação de {@link EventMapper}.
 */
@Component
public class EventMapperImpl implements EventMapper {

  /**
   * @throws IllegalArgumentException caso eventRequest seja null.
   */
  @Override
  public Event toEvent(EventRequest eventRequest) {
    if (eventRequest == null) throw new IllegalArgumentException("eventRequest must not be null");

    return Event.builder()
        .title(eventRequest.title())
        .description(eventRequest.description())
        .date(toLocalDateTime(eventRequest.dateTime()))
        .build();
  }

  /**
   * @throws IllegalArgumentException caso event seja null.
   */
  @Override
  public EventResponse toEventResponse(Event event) {
    if (event == null) throw new IllegalArgumentException("event must not be null");

    return EventResponse.builder()
        .id(event.getId())
        .title(event.getTitle())
        .description(event.getDescription())
        .dateTime(event.getDate())
        .userId(event.getUserId())
        .build();
  }

  /**
   * @throws IllegalArgumentException caso event or eventRequest sejam null.
   */
  @Override
  public void merge(Event event, EventRequest eventRequest) {
    if (event == null || eventRequest == null)
      throw new IllegalArgumentException("event nor eventRequest must not be null");

    event.setTitle(eventRequest.title());
    event.setDescription(eventRequest.description());
    event.setDate(toLocalDateTime(eventRequest.dateTime()));
  }

  private LocalDateTime toLocalDateTime(String dateTime) {
    return LocalDateTime.parse(dateTime);
  }

}
