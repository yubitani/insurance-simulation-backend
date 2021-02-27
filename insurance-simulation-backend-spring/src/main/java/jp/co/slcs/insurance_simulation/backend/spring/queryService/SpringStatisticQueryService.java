package jp.co.slcs.insurance_simulation.backend.spring.queryService;

import static java.util.stream.Collectors.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import jp.co.slcs.insurance_simulation.backend.queryService.StatisticQueryService;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.DocumentRequestDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.DocumentRequestDaoEntity;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationDao;
import jp.co.slcs.insurance_simulation.backend.usecase.GetTotalUsecase.TotalDataRow;
import jp.co.slcs.insurance_simulation.backend.usecase.GetTransitionUsecase.TransitionDataRow;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * 資料請求件数についての統計情報（累計データ、推移データ）を取得する際に使用するQueryService。（SpringDataJDBCでの実装）
 *
 * @author KentoOtani
 * @version 1.0.0 2021.01.04
 */
@AllArgsConstructor
@Service
public class SpringStatisticQueryService implements StatisticQueryService {

  @NonNull
  private final DocumentRequestDao documentRequestDao;

  @NonNull
  private final SimulationDao simulationDao;

  /**
   * 引数の商品コードを商品名に変換するメソッド。
   *
   * @param productCode 商品コード
   * @return 商品名
   *
   *         TODO 商品コードと商品名をベタ書きしているので修正要。 TODO MapからListへの変換や商品コードから商品名への変換はusecaseで実施すべき？
   */
  private static String convertToProductName(String productCode) {

    Map<String, String> productNameMap = new HashMap<>();
    productNameMap.put("DeathInsurance-001", "死亡保険");
    productNameMap.put("MedicalInsurance-001", "医療保険");
    productNameMap.put("CancerInsurance-001", "がん保険");

    Optional<Map.Entry<String, String>> result =
        productNameMap.entrySet().stream().filter(p -> p.getKey().equals(productCode)).findFirst();

    if (result.isEmpty()) {
      return null;
    }

    return result.get().getValue();
  }

  /**
   * 資料請求件数の商品別累計データを取得するメソッド。
   *
   * @return 資料請求件数の商品別（プロダクトコード別）累計データ
   *
   *         TODO productCodeを直接記述しているので修正要。
   */
  @Override
  public List<TotalDataRow> fetchTotalData() {
    List<TotalDataRow> result = new ArrayList<>();
    List<String> productCodeList =
        Arrays.asList("DeathInsurance-001", "MedicalInsurance-001", "CancerInsurance-001");
    productCodeList.stream()
        .forEach(pc -> result.add(TotalDataRow.builder().productName(convertToProductName(pc))
            .number(simulationDao.countByProductCode(pc)).build()));
    return result;
  }

  /**
   * 資料請求件数の推移データ（日別）を取得するメソッド。期間は14日分（15日前から前日）。
   *
   * @return 資料請求件数の日別推移データ TODO Map<String, Long> -> List<TransitionDataRow>はstaticメソッド化する。
   */
  @Override
  public List<TransitionDataRow> fetchTransitionDataPerDay() {
    // メソッド実行日の15日前から前日までのDocumentRequestDaoEntityのリストを取得。
    List<DocumentRequestDaoEntity> list = documentRequestDao.fetchForSpecificPeriod(-14, 0);

    if (list != null) {
      // 日単位での件数集計
      Map<String, Long> rawData = list.stream()
          .collect(groupingBy(
              e -> e.getRequestedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
              counting()));

      // Map<String, Long> -> List<TransitionDataRow>
      List<TransitionDataRow> result = rawData.entrySet().stream()
          .map(e -> TransitionDataRow.builder().dateTime(e.getKey()).number(e.getValue()).build())
          .collect(toList());

      // 日付順にソート
      result = result.stream().sorted(Comparator.comparing(TransitionDataRow::getDateTime))
          .collect(toList());

      return result;

    } else {
      return new ArrayList<>();
    }
  }

  /**
   * 資料請求件数の推移データ（1時間単位）を取得するメソッド。期間はメソッド実行日の0:00から実行時点の時刻まで。
   *
   * @return 資料請求件数の1時間単位の推移データ TODO Map<String, Long> -> List<TransitionDataRow>はstaticメソッド化する。
   */
  @Override
  public List<TransitionDataRow> fetchTransitionDataPerHour() {
    // メソッド実行日の0:00から実行時点の時刻までのDocumentRequestDaoEntityのリストを取得。
    List<DocumentRequestDaoEntity> list = documentRequestDao.fetchForSpecificPeriod(0, 1);

    if (list != null) {
      // 1時間単位での件数集計
      Map<String, Long> rawData = list.stream().collect(groupingBy(
          e -> e.getRequestedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:00")),
          counting()));

      // Map<String, Long> -> List<TransitionDataRow>
      List<TransitionDataRow> result = rawData.entrySet().stream()
          .map(e -> TransitionDataRow.builder().dateTime(e.getKey()).number(e.getValue()).build())
          .collect(toList());

      // 時刻順にソート
      result = result.stream().sorted(Comparator.comparing(TransitionDataRow::getDateTime))
          .collect(toList());

      return result;

    } else {
      return new ArrayList<>();
    }
  }

}
