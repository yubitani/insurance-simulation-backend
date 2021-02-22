package jp.co.slcs.insurancepremiumsimulation.backend.usecase.exception;

import lombok.NoArgsConstructor;


/**
 * システム例外クラス
 * @author Inadome Takayuki
 * @version 1.0.0: 2020.11.18
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
