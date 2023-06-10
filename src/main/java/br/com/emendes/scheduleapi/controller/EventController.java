package br.com.emendes.scheduleapi.controller;

import br.com.emendes.scheduleapi.dto.request.CreateEventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import br.com.emendes.scheduleapi.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller o qual é mapeado as requisições de /api/events.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;

  /**
   * Trata requisição POST /api/events.
   *
   * @param eventRequest DTO que contém as informações do Event a ser registrado.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<EventResponse> create(@Valid @RequestBody CreateEventRequest eventRequest) {
    return eventService.create(eventRequest);
  }

  /**
   * Trata requisição GET /api/events?page={page}&size={size}.
   *
   * @param page Número da página a ser buscada, valor padrão é 0.
   * @param size Tamanho da página a ser buscada, valor padrão é 10.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<EventResponse> fetchAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {
    return eventService.fetchAll(PageRequest.of(page, size));
  }

}
