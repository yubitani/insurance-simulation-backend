package jp.co.slcs.insurance_simulation.backend.spring.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * エラー内容のレスポンスDTOクラス
 * @author Masato Uraji
 * @version 1.0.0: 2020.12.20
 */
@Builder
@Getter
public class ErrorResDto {

	@NonNull
	private final String errorMessage;

}
