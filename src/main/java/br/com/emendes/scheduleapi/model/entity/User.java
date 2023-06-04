package br.com.emendes.scheduleapi.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "t_user")
public class User {

  @Id
  private Long id;
  private String name;
  private String email;
  private String password;
  private String roles;

}
