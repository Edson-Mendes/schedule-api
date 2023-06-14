package br.com.emendes.scheduleapi.controller.swagger;

import br.com.emendes.scheduleapi.dto.request.AuthenticationRequest;
import br.com.emendes.scheduleapi.dto.response.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

/**
 * Interface para manter as anotações do Swagger e não deixar AuthenticationController poluído.
 */
@Tag(name = "Authentication", description = "Authentication management APIs")
public interface AuthenticationControllerSwagger {

  @Operation(
      summary = "Perform authentication",
      description = "Endpoint to user perform authentication. The response, if successful, is a JSON with JWT.",
      tags = {"Authentication"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful user authentication",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content)
  })
  Mono<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);

}
