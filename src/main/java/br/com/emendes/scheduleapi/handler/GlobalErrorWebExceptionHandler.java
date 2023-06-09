package br.com.emendes.scheduleapi.handler;

import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

/**
 * Exception handler da aplicação.
 */
@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

  public GlobalErrorWebExceptionHandler(
      ErrorAttributes errorAttributes,
      WebProperties.Resources resources,
      ApplicationContext applicationContext,
      ServerCodecConfigurer codecConfigurer) {
    super(errorAttributes, resources, applicationContext);
    this.setMessageWriters(codecConfigurer.getWriters());
  }

  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
    return RouterFunctions.route(
        RequestPredicates.all(), this::renderErrorResponse);
  }

  private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
    Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
    int status = (int) Optional.ofNullable(errorPropertiesMap.get("status")).orElse(500);

    return ServerResponse.status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(errorPropertiesMap));
  }


}
