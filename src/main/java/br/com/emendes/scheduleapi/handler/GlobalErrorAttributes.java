package br.com.emendes.scheduleapi.handler;

import br.com.emendes.scheduleapi.exception.PasswordsDoNotMatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe para manipulação de atributos em respostas de erros.
 */
@Slf4j
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
    Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
    Throwable throwable = getError(request);

    if (throwable instanceof WebExchangeBindException exception) {
      String fields = exception.getFieldErrors().stream().map(FieldError::getField)
          .collect(Collectors.joining("; "));
      String messages = exception.getFieldErrors().stream().map(FieldError::getDefaultMessage)
          .collect(Collectors.joining("; "));

      putFields(400, "Invalid field(s)", "Some fields are invalid",
          Map.of("fields", fields, "messages", messages),
          errorAttributes);
    } else if (throwable instanceof PasswordsDoNotMatch exception) {
      putFields(400, "Password do not match", exception.getMessage(), errorAttributes);
    } else if (throwable instanceof ResponseStatusException exception) {
      putFields(exception.getStatusCode().value(), "Bad request", exception.getReason(), errorAttributes);
    } else if (throwable instanceof BadCredentialsException exception) {
      putFields(400, exception.getMessage(), "Wrong E-mail or password", errorAttributes);
    }

    return errorAttributes;
  }

  private void putFields(int status, String error, String message, Map<String, Object> errorAttributes) {
    putFields(status, error, message, null, errorAttributes);
  }

  private void putFields(int status, String error, String message, Map<String, Object> extraFields, Map<String, Object> errorAttributes) {
    errorAttributes.put("status", status);
    errorAttributes.put("error", error);
    errorAttributes.put("message", message);

    if (extraFields != null) {
      errorAttributes.putAll(extraFields);
    }
  }

}
