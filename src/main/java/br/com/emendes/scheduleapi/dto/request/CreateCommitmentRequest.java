package br.com.emendes.scheduleapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Record DTO para receber dados de criação de Commitment no corpo da requisição.
 * @param title do Commitment
 * @param description do Commitment
 * @param dateTime do Commitment, deve ser no formato yyyy-MM-dd'T'HH:mm:ss
 */
public record CreateCommitmentRequest(

    @NotBlank(message = "title must not be blank")
    @Size(min = 1, max = 100, message = "title must contains between {min} and {max} characters long")
    String title,
    @Size(max = 255, message = "description must contains max {max} characters long")
    String description,
    @NotBlank(message = "date_time must not be blank")
    @Pattern(regexp = "yyyy-MM-dd'T'HH:mm:ss", message = "date_time must be in yyyy-MM-dd'T'HH:mm:ss format")
    @JsonProperty(value = "date_time")
    String dateTime

) {
}
