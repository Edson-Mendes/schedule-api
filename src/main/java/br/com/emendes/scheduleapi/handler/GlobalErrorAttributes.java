package br.com.emendes.scheduleapi.handler;

import br.com.emendes.scheduleapi.exception.PasswordsDoNotMatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.dao.DuplicateKeyException;
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

      errorAttributes.put("error", "Invalid fields");
      errorAttributes.put("fields", fields);
      errorAttributes.put("messages", messages);
    } else if (throwable instanceof PasswordsDoNotMatch exception) {
      errorAttributes.put("message", exception.getMessage());
      errorAttributes.put("error", "Password do not match");
      errorAttributes.put("status", 400);
    } else if (throwable instanceof ResponseStatusException exception) {
      errorAttributes.put("message", exception.getReason());
    }

    return errorAttributes;
  }

}
