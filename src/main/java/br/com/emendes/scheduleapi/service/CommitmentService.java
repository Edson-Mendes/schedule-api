package br.com.emendes.scheduleapi.service;

import br.com.emendes.scheduleapi.dto.request.CreateCommitmentRequest;
import br.com.emendes.scheduleapi.dto.response.CommitmentResponse;
import reactor.core.publisher.Mono;

/**
 * Interface service com as abstrações para manipulação do recurso Commitment.
 */
public interface CommitmentService {

  /**
   * Cria um Commitment.
   * @param commitmentRequest contendo as informações do Commitment a ser salvo.
   * @return CommitmentResponse contendo as informações do Commitment salvo.
   */
  Mono<CommitmentResponse> create(CreateCommitmentRequest commitmentRequest);

}
