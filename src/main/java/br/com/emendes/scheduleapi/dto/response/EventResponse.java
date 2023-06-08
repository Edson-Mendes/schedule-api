package br.com.emendes.scheduleapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Record DTO para enviar JWT de autenticação do usuário no corpo da resposta.
 * @param id do Event
 * @param title do Event
 * @param description do Event
 * @param dateTime do Event
 * @param userId id do User dono do Event.
 */
@Builder
public record EventResponse(

    long id,
    String title,
    String description,
    @JsonProperty(value = "date_time")
    LocalDateTime dateTime,
    @JsonProperty(value = "user_id")
    long userId

) {
}
