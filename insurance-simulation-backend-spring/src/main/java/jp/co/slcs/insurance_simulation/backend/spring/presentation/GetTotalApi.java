package jp.co.slcs.insurance_simulation.backend.spring.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jp.co.slcs.insurance_simulation.backend.usecase.GetTotalUsecase;
import jp.co.slcs.insurance_simulation.backend.usecase.GetTotalUsecase.TotalData;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * 資料請求件数の累計データを取得するAPI
 *
 */

@AllArgsConstructor
@RestController
public class GetTotalApi {

  /**
   * 資料請求件数の累計データを取得する業務ロジック
   */
  @NonNull
  private final GetTotalUsecase usecase;

  /**
   * 業務ロジックで取得した資料請求件数の累計データ（POJO）をJSONでクライアントに返す。
   *
   * @return 累計データ
   */
  @NonNull
  @GetMapping(path = "/statistics/total", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public TotalData getTotal() {
    return usecase.invoke();
  }

}
