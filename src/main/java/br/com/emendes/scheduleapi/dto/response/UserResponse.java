package br.com.emendes.scheduleapi.dto.response;

import lombok.Builder;

/**
 * Record DTO para enviar informações do User para o cliente no corpo da resposta.
 *
 * @param id    Identificador do User
 * @param name  do User
 * @param email do User
 */
@Builder
public record UserResponse(
    Long id,
    String name,
    String email
) {
}
