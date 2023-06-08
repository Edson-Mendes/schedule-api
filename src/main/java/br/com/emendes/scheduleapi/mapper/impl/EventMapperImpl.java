package br.com.emendes.scheduleapi.mapper.impl;

import br.com.emendes.scheduleapi.dto.request.CreateEventRequest;
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

  @Override
  public Event toEvent(CreateEventRequest eventRequest) {
    return Event.builder()
        .title(eventRequest.title())
        .description(eventRequest.description())
        .date(LocalDateTime.parse(eventRequest.dateTime()))
        .build();
  }

  @Override
  public EventResponse toEventResponse(Event event) {
    return EventResponse.builder()
        .id(event.getId())
        .title(event.getTitle())
        .description(event.getDescription())
        .dateTime(event.getDate())
        .userId(event.getUserId())
        .build();
  }

}
