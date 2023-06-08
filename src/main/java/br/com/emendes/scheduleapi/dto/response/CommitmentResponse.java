package br.com.emendes.scheduleapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommitmentResponse(

    long id,
    String title,
    String description,
    @JsonProperty(value = "date_time")
    String dateTime,
    @JsonProperty(value = "user_id")
    long userId

) {
}
