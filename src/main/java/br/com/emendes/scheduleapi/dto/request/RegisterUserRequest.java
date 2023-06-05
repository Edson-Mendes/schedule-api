package br.com.emendes.scheduleapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Record DTO para receber dados de criação de User no corpo da requisição.
 *
 * @param name            do User
 * @param email           do User, deve ser único no banco de dados.
 * @param password        do User, deve conter entre 8 e 30 caracteres.
 * @param confirmPassword confirmação da senha do User.
 */
@Builder
public record RegisterUserRequest(
    @NotBlank(message = "name must not be blank")
    @Size(min = 2, max = 100, message = "name must contain between {min} and {max} characters")
    String name,
    @NotBlank(message = "email must not be blank")
    @Size(max = 150, message = "email must contain max {max} characters")
    @Email(message = "must be a well formed email")
    String email,
    @NotBlank(message = "password must not be blank")
    @Size(min = 8, max = 30, message = "password must contain between {min} and {max} characters")
    String password,
    @NotBlank(message = "confirmPassword must not be blank")
    @Size(min = 8, max = 30, message = "confirmPassword must contain between {min} and {max} characters")
    String confirmPassword
) {

  /**
   * Verifica se password e confirmPassword são iguais.
   *
   * @return true, se password e confirmPassword forem iguais, false caso contrário.
   */
  public boolean passwordsMatch() {
    if (password == null || confirmPassword == null) return false;
    return password.equals(confirmPassword);
  }

}
