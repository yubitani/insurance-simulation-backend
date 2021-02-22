package jp.co.slcs.insurancepremiumsimulation.backend.spring.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;


/**
 * 保険料計算APIのレスポンスDTOクラス
 * @author Inadome Takayuki
 * @version 1.0.0: 2020.11.18
 */
@Builder
@Getter
public class InsurancePremiumCalculateResDto {

	@NonNull
	private final String insurancePremium;

}
