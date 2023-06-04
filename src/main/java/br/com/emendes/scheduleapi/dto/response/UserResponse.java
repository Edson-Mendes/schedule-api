package br.com.emendes.scheduleapi.dto.response;

/**
 * Record DTO para enviar informações do User para o cliente no corpo da resposta.
 *
 * @param id Identificador do User
 * @param name do User
 * @param email do User
 */
public record UserResponse(
    Long id,
    String name,
    String email
) {
}
