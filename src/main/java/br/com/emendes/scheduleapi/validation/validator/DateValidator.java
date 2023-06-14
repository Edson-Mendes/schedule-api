package br.com.emendes.scheduleapi.validation.validator;

import br.com.emendes.scheduleapi.validation.annotation.DateTimeValidation;
import br.com.emendes.scheduleapi.validation.annotation.DateValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<DateValidation, String> {

  private DateTimeFormatter formatter;

  @Override
  public void initialize(DateValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    formatter = DateTimeFormatter.ofPattern(constraintAnnotation.pattern());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isBlank()){
      return true;
    }
    return isConvertible(value);
  }

  private boolean isConvertible(String value) {
    try {
      LocalDate.parse(value, formatter);
      return true;
    } catch (DateTimeParseException ex){
      return false;
    }
  }

}
