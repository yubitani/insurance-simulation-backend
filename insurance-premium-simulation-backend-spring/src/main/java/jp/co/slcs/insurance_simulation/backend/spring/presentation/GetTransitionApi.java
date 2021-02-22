package jp.co.slcs.insurance_simulation.backend.spring.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.slcs.insurance_simulation.backend.usecase.GetTransitionUsecase;
import jp.co.slcs.insurance_simulation.backend.usecase.GetTransitionUsecase.TransitionData;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * 資料請求件数の推移データを取得するAPI
 *
 * @author KentoOtani
 * @version 1.0.0 2020.12.16
 * @version 1.1.0 2020.1.4 DB格納値を返却できるよう修正。
 */
@AllArgsConstructor
@RestController
public class GetTransitionApi {

	/**
	 * 資料請求件数の推移データを取得する業務ロジック
	 */
	@NonNull
	private final GetTransitionUsecase usecase;

	/**
	 * 統計単位（日別 or 時間別）を受け取り、業務ロジックで取得した資料請求件数の推移データ（POJO）をJSONでクライアントに返すメソッド。
	 *
	 * @param String 統計単位
	 * @return TotalData 推移データ
	 */
	@NonNull
	@GetMapping(path = "/statistics/transition/{unitType}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public TransitionData getTransition(@NonNull @PathVariable("unitType") String unitType) {
		return usecase.invoke(unitType);
	}
}
