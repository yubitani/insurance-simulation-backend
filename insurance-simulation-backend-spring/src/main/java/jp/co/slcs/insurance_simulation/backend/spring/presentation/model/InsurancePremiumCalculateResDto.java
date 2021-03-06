package jp.co.slcs.insurance_simulation.backend.spring.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * 保険料計算APIのレスポンスDTOクラス
 */
@Builder
@Getter
public class InsurancePremiumCalculateResDto {

  @NonNull
  private final String insurancePremium;

}
