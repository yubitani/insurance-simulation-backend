package jp.co.slcs.insurance_simulation.backend.spring.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * エラー内容のレスポンスDTOクラス
 */
@Builder
@Getter
public class ErrorResDto {

  @NonNull
  private final String errorMessage;

}
