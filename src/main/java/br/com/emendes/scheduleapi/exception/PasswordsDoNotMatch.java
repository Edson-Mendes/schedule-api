package br.com.emendes.scheduleapi.exception;

/**
 * Exception para ser lançada quando password e confirmação de password não corresponderem.
 */
public class PasswordsDoNotMatch extends RuntimeException {

  public PasswordsDoNotMatch(String message) {
    super(message);
  }

}
