package br.com.emendes.scheduleapi.controller.swagger;

import br.com.emendes.scheduleapi.dto.request.EventRequest;
import br.com.emendes.scheduleapi.dto.response.EventResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import static br.com.emendes.scheduleapi.config.OpenAPIConfig.SECURITY_SCHEME_KEY;

/**
 * Interface para manter as anotações do Swagger e não deixar EventController poluído.
 */
@Tag(name = "Event", description = "Event management APIs")
public interface EventControllerSwagger {

  @Operation(
      summary = "Create event",
      description = "Create event providing event's info at request body (title, description and date_time).",
      tags = {"Event"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successful created event",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized, client must be authenticate",
          content = @Content)
  })
  Mono<EventResponse> create(EventRequest eventRequest);

  @Operation(
      summary = "Fetch pageable events",
      description = "Fetch events related to current user. Optional fetch for date (yyyy-MM-dd)." +
          " Default value to page and size are 0 and 10, respectively.",
      tags = {"Event"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful fetched event",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized, client must be authenticate",
          content = @Content)
  })
  public Mono<Page<EventResponse>> fetch(String date, int page, int size);

  @Operation(
      summary = "Find event by id",
      description = "Find event by id related to current user. Event's info are id, title, description and date_time.",
      tags = {"Event"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful found event",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized, client must be authenticate",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Event not found",
          content = @Content)
  })
  Mono<EventResponse> findById(Long eventId);

  @Operation(
      summary = "Update event",
      description = "Update event providing new event's info at request body (title, description and date_time).",
      tags = {"Event"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Successful updated event",
          content = @Content),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized, client must be authenticate",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Event not found",
          content = @Content)
  })
  Mono<Void> update(Long eventId, EventRequest eventRequest);

  @Operation(
      summary = "Delete event by id",
      description = "Delete event by id related to current user.",
      tags = {"Event"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Successful deleted event",
          content = @Content),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized, client must be authenticate",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Event not found",
          content = @Content)
  })
  Mono<Void> delete(Long eventId);

}
