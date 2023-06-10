package br.com.emendes.scheduleapi.service.impl;

import br.com.emendes.scheduleapi.component.AuthenticationFacade;
import br.com.emendes.scheduleapi.dto.request.CreateEventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import br.com.emendes.scheduleapi.mapper.EventMapper;
import br.com.emendes.scheduleapi.model.entity.Event;
import br.com.emendes.scheduleapi.repository.EventRepository;
import br.com.emendes.scheduleapi.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link EventService}
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;
  private final AuthenticationFacade authenticationFacade;

  @Override
  public Mono<EventResponse> create(CreateEventRequest eventRequest) {
    Event event = eventMapper.toEvent(eventRequest);

    return authenticationFacade.getCurrentUser()
        .map(user -> {
          event.setUserId(user.getId());
          event.setUser(user);
          return event;
        })
        .flatMap(eventRepository::save)
        .map(eventMapper::toEventResponse);
  }

  @Override
  public Flux<EventResponse> fetchAll(Pageable pageable) {
    log.info("fetching page: {} and size: {}", pageable.getPageNumber(), pageable.getPageSize());

    return authenticationFacade.getCurrentUser()
        .flatMapMany(user -> eventRepository.findByUserId(user.getId(), pageable))
        .map(eventMapper::toEventResponse);
  }

}
