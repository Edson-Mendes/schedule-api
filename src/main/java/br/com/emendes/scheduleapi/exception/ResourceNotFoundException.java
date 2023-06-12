package br.com.emendes.scheduleapi.exception;

/**
 * Exception para ser usada quando um recurso não for encontrado no banco de dados.
 */
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String messege) {
    super(messege);
  }

}
