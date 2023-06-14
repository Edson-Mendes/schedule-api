package br.com.emendes.scheduleapi.validation.annotation;

import br.com.emendes.scheduleapi.validation.validator.DateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * O elemento anotado deve ser uma {@code String} que pode ser convertido para {@code LocalDate} de acordo com o
 * {@link #pattern} padrão informado.<br>
 * Elementos {@code null} ou {@code blank} são considerados válidos.<br>
 * O pattern padrão é o ISO_LOCAL_DATE (yyyy-MM-dd)<br>
 * Se o {@code pattern} informado for <strong>INVÁLIDO</strong>, então ValidationException será lançada ao validar.<br><br>
 * <p>
 * {@code Exemplo:} 26/09/2022 é válido para o pattern dd/MM/yyyy<br>
 *
 * @author Edson Mendes
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface DateValidation {

  String message() default "Invalid Date Time";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String pattern() default "yyyy-MM-dd";

}
