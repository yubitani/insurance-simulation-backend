package jp.co.slcs.insurance_simulation.backend.spring.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.ErrorResDto;
import jp.co.slcs.insurance_simulation.backend.usecase.exception.InvalidInputFormatException;


/**
 * APIアドバイスクラス
 */
@RestControllerAdvice
public class InsurancePremiumSimulationApiAdvice {

  @ExceptionHandler(InvalidInputFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResDto handleException(InvalidInputFormatException e) {
    // エラーに応じたメッセージを返す
    ErrorResDto result = ErrorResDto.builder().errorMessage(e.getMessage()).build();

    return result;
  }
}
