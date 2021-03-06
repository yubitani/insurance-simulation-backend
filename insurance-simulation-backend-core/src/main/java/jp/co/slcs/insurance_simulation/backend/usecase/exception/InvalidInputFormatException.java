package jp.co.slcs.insurance_simulation.backend.usecase.exception;

import lombok.NoArgsConstructor;

/**
 * 入力チェック例外クラス
 *
 */
@NoArgsConstructor
public class InvalidInputFormatException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidInputFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidInputFormatException(String message) {
    super(message);
  }

  public InvalidInputFormatException(Throwable cause) {
    super(cause);
  }

}
