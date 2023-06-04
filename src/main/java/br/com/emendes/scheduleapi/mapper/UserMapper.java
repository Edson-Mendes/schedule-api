package br.com.emendes.scheduleapi.mapper;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.model.entity.User;

/**
 * Interface component que contém as abstrações de mapeamento de DTOs para a entidade User e vice-versa.
 */
public interface UserMapper {

  /**
   * Mapeia o DTO RegisterUserRequest para a entidade User.
   * userRequest não deve ser null.
   * @param userRequest que será mapeado para User
   * @return User contendo as informações que estavam em userRequest.
   * @throws IllegalArgumentException se userRequest for null.
   */
  User toUser(RegisterUserRequest userRequest);

}
