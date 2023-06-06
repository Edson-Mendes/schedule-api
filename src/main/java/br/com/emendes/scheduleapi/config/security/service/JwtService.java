package br.com.emendes.scheduleapi.config.security.service;

import br.com.emendes.scheduleapi.model.entity.User;

/**
 * Interface component com as abstrações para manipulação de JWT.
 */
public interface JwtService {

  /**
   * Gera um JWT a partir dos dados do User.
   *
   * @return jwt
   */
  String generateJWT(User user);

}
