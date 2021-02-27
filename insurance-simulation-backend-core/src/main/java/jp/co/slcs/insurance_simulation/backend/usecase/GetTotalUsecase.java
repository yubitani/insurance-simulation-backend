package jp.co.slcs.insurance_simulation.backend.usecase;

import java.time.LocalDateTime;
import java.util.List;

import jp.co.slcs.insurance_simulation.backend.queryService.StatisticQueryService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NonNull;

/**
 * 資料請求件数の累計データを取得するAPIのユースケース
 *
 * @author KentoOtani
 *
 * @version 1.0.0: 2020.12.15
 * @version 1.1.0: 2020.1.4 queryServiceを呼び出すよう修正。
 */
@AllArgsConstructor
public class GetTotalUsecase {

  /**
   * QueryServiceクラスを取得
   */
  @NonNull
  private final StatisticQueryService queryService;

  /**
   * invokeメソッド
   *
   * 保険商品単位での資料請求件数の累計データを取得する。
   *
   * @return TotalData 資料請求件数の累計データ（自クラス内で定義）
   */
  public TotalData invoke() {
    return TotalData.builder().data(queryService.fetchTotalData()).build();

  }

  /**
   * 以下、DTO（usecase → presentation）
   */
  @Builder
  @Getter
  public static class TotalData {
    @Default
    @NonNull
    private final LocalDateTime acquiredDateTime = LocalDateTime.now();
    @NonNull
    private final List<TotalDataRow> data;
  }
  @Builder
  @Getter
  public static class TotalDataRow {
    @NonNull
    private final String productName;
    @NonNull
    private final Long number;
  }

}
