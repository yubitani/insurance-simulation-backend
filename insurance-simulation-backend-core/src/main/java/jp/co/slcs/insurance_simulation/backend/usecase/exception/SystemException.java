package jp.co.slcs.insurance_simulation.backend.usecase.exception;

import lombok.NoArgsConstructor;


/**
 * システム例外クラス
 */
@NoArgsConstructor
public class SystemException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SystemException(String message, Throwable cause) {
    super(message, cause);
  }

  public SystemException(String message) {
    super(message);
  }

  public SystemException(Throwable cause) {
    super(cause);
  }

}
