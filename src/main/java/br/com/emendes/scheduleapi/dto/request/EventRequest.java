package br.com.emendes.scheduleapi.dto.request;

import br.com.emendes.scheduleapi.validation.annotation.DateTimeValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Record DTO para receber dados de criação de Event no corpo da requisição.
 * @param title do Event
 * @param description do Event
 * @param dateTime do Event, deve ser no formato yyyy-MM-dd'T'HH:mm:ss
 */
public record EventRequest(

    @NotBlank(message = "title must not be blank")
    @Size(min = 1, max = 100, message = "title must contains between {min} and {max} characters long")
    String title,
    @Size(max = 255, message = "description must contains max {max} characters long")
    String description,
    @NotBlank(message = "date_time must not be blank")
    @DateTimeValidation(message = "date_time must be on format {pattern}")
    @JsonProperty(value = "date_time")
    String dateTime

) {
}
