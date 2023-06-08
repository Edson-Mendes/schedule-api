package br.com.emendes.scheduleapi.service.impl;

import br.com.emendes.scheduleapi.dto.request.CreateEventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import br.com.emendes.scheduleapi.mapper.EventMapper;
import br.com.emendes.scheduleapi.model.entity.Event;
import br.com.emendes.scheduleapi.repository.EventRepository;
import br.com.emendes.scheduleapi.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link EventService}
 */
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

  @Override
  public Mono<EventResponse> create(CreateEventRequest eventRequest) {
    Event event = eventMapper.toEvent(eventRequest);

    // TODO: Buscar o id do User da requisição.

    // TODO: Settar o id do User em Event.user_id.

    return eventRepository.save(event)
        .map(eventMapper::toEventResponse);
  }

}
