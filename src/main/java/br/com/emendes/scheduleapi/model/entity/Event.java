package br.com.emendes.scheduleapi.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entidade Commitment, refere-se a tabela t_commitment no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "t_event")
public class Event {

  @Id
  private Long id;
  private String title;
  private String description;
  private LocalDateTime date;
  private Long userId;
  @Transient
  private User user;

}
