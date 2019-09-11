package io.aktoluna.slnarch.common.exception;

public class SlnException extends RuntimeException {

  public SlnException(String message) {
    super(message);
  }

  public SlnException(String message, Throwable cause) {
    super(message, cause);
  }
}
