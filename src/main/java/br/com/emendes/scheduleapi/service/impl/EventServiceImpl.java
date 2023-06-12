package br.com.emendes.scheduleapi.service.impl;

import br.com.emendes.scheduleapi.component.AuthenticationFacade;
import br.com.emendes.scheduleapi.dto.request.EventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import br.com.emendes.scheduleapi.exception.ResourceNotFoundException;
import br.com.emendes.scheduleapi.mapper.EventMapper;
import br.com.emendes.scheduleapi.model.entity.Event;
import br.com.emendes.scheduleapi.model.entity.User;
import br.com.emendes.scheduleapi.repository.EventRepository;
import br.com.emendes.scheduleapi.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

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
  public Mono<EventResponse> create(EventRequest eventRequest) {
    Event event = eventMapper.toEvent(eventRequest);

    log.info("Attempt to create a Event");

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
  public Mono<Page<EventResponse>> fetchAll(Pageable pageable) {
    log.info("fetching page: {} and size: {} of events", pageable.getPageNumber(), pageable.getPageSize());

    Mono<Long> userIdMono = authenticationFacade.getCurrentUser()
        .map(User::getId);

    // Busca paginada de EventResponse por userId
    Mono<List<EventResponse>> eventResponseListMono = userIdMono
        .flatMapMany(userId -> eventRepository.findByUserId(userId, pageable))
        .map(eventMapper::toEventResponse)
        .collectList();

    // Count events por userId
    Mono<Long> countByUserIdMono = userIdMono.flatMap(eventRepository::countByUserId);

    return Mono.zip(eventResponseListMono, countByUserIdMono)
        .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
  }

  /**
   * @throws ResourceNotFoundException se Event não for encontrado para o current User e eventId.
   */
  @Override
  public Mono<EventResponse> findById(Long eventId) {
    return findEventById(eventId)
        .map(eventMapper::toEventResponse);
  }

  @Override
  public Mono<Page<EventResponse>> findByDate(String date, int page, int size) {
    log.info("fetching page: {} and size: {} of events with date: {}", page, size, date);

    LocalDate localDate = LocalDate.parse(date);

    Mono<Long> userIdMono = authenticationFacade.getCurrentUser().map(User::getId);

    Mono<List<EventResponse>> eventResponseListMono = userIdMono
        .flatMapMany(userId -> eventRepository.findByDate(userId, localDate, size, page*size))
        .map(eventMapper::toEventResponse)
        .collectList();

    Mono<Long> countMono = userIdMono.flatMap(userId -> eventRepository.countByUserIdAndDate(userId, localDate));

    return Mono.zip(eventResponseListMono, countMono)
        .map(tuple -> new PageImpl<>(tuple.getT1(), PageRequest.of(page, size), tuple.getT2()));
  }

  /**
   * @throws ResourceNotFoundException se Event não for encontrado para o current User e eventId.
   */
  @Override
  public Mono<Void> update(Long eventId, EventRequest eventRequest) {
    log.info("Attempt to update event with id: {}", eventId);

    return findEventById(eventId)
        .doOnNext(event -> eventMapper.merge(event, eventRequest))
        .flatMap(eventRepository::save)
        .then();
  }

  /**
   * @throws ResourceNotFoundException se Event não for encontrado para o current User e eventId.
   */
  @Override
  public Mono<Void> delete(Long eventId) {
    log.info("Attempt to delete event with id: {}", eventId);

    return findEventById(eventId)
        .flatMap(eventRepository::delete);
  }

  /**
   * Busca Event por id e pelo current User.
   * @param eventId identificador do Event.
   * @return Em caso de sucesso, retorna Mono of Event encontrado, e Mono of Error caso contrário.
   */
  private Mono<Event> findEventById(Long eventId) {
    log.info("Searching for event with id: {}", eventId);

    return authenticationFacade.getCurrentUser()
        .flatMap(user -> eventRepository
            .findByIdAndUserId(eventId, user.getId())
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("Event not found for id: " + eventId)))
        );
  }

}
