package br.com.emendes.scheduleapi.service;

import br.com.emendes.scheduleapi.dto.request.RegisterUserRequest;
import br.com.emendes.scheduleapi.dto.response.UserResponse;
import br.com.emendes.scheduleapi.mapper.UserMapper;
import br.com.emendes.scheduleapi.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link UserService}
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  @Override
  public Mono<UserResponse> register(RegisterUserRequest userRequest) {
    // TODO: Verificar se RegisterUserRequest#password e RegisterUserRequest#confirmPassword correspondem.

    User user = userMapper.toUser(userRequest);

    // TODO: Encriptar o password e settar no user.

    // TODO: Chamar o repository e persistir o User no banco de dados.
    // TODO: Mapear o User salvo para UserResponse e retornar.

    return null;
  }

}
