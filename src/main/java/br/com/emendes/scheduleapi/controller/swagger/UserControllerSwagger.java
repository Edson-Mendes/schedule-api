package br.com.emendes.scheduleapi.controller.swagger;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
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
 * Interface para manter as anotações do Swagger e não deixar UserController poluído.
 */
@Tag(name = "User", description = "User management APIs")
public interface UserControllerSwagger {

  @Operation(
      summary = "Register User",
      description = "Register user providing user's info at request body (name, email, password and confirmPassword).",
      tags = {"User"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successful registered user",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content)
  })
  Mono<UserResponse> register(RegisterUserRequest userRequest);

  @Operation(
      summary = "Fetch pageable Users",
      description = "Fetch pageable of User's info that are id, name and email. Only Admin can fetch."+
          " Default value to page and size are 0 and 10, respectively.",
      tags = {"User"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful fetch users",
          content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized, client must be authenticate",
          content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden, only ADMIN can access",
          content = @Content)
  })
  Mono<Page<UserResponse>> fetchAll(int page, int size);

  @Operation(
      summary = "find user by id",
      description = "Find user by id, the user's info received are id, name and email. " +
          "Common users can only search for themself, Admin users can search for everyone.",
      tags = {"User"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful found user",
          content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized, client must be authenticate",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content)
  })
  Mono<UserResponse> findById(Long userId);

}
