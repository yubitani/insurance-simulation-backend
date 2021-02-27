package jp.co.slcs.insurance_simulation.backend.usecase;

import java.time.LocalDate;
import java.util.List;

import jp.co.slcs.insurance_simulation.backend.queryService.DocumentRequestQueryService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * 資料請求内容を一覧表示用に取得するAPIのユースケース
 * 
 * @author Inadome Takayuki
 * @version 1.0.0: 2021.1.5
 */
@AllArgsConstructor
public class GetDocumentRequestsUsecase {

  /**
   * QueryServiceクラスを取得
   */
  @NonNull
  private final DocumentRequestQueryService queryService;

  /**
   * invokeメソッド
   *
   * 資料請求内容を一覧表示用に取得する。
   *
   *
   * @return List<DocumentRequestList> 資料請求のリスト
   * @author Inadome Takayuki
   * @version 1.0.0:2021.1.6
   */
  public List<DocumentRequestList> invoke() {
    return queryService.fetchDocumentRequestLists();
  }

  /**
   * 資料請求一覧取得APIにて、JSONに変換してユーザに返すModelクラス。
   *
   * @author Inadome Takayuki
   * @version 1.0.0:2021.1.5
   */
  @Builder
  @Getter
  public static class DocumentRequestList {

    @NonNull
    private final String receiptNo; // 受付番号

    @NonNull
    private final LocalDate registrationDate; // 登録日

    @NonNull
    private final String name; // 名前

    @NonNull
    private final String nameKana; // 名前（カナ）

    @NonNull
    private final LocalDate birthday; // 生年月日

    @NonNull
    private final String sex; // 性別

    @NonNull
    private final String zipCode; // 郵便番号

    @NonNull
    private final String address; // 住所

    @NonNull
    private final String telNo; // 電話番号

    @NonNull
    private final String mailAddress; // メールアドレス

    private final List<Simulation> simulations; // 見積もり情報

    @NonNull
    private final String sumInsurancePremium; // 合計保険料

  }

  /**
   * 見積もり情報クラス 見積もり情報を保持。
   *
   * DBから見積もりの情報を取得し、格納するクラス
   *
   * @author Inadome Takayuki
   * @version 1.0.0:2021.1.5
   */
  @Builder
  @Getter
  public static class Simulation {

    @NonNull
    private final String simulationId; // 見積もりID

    @NonNull
    private final String productName; // 商品名

    @NonNull
    private final String benefitHeaderName; // 保険金表示名

    @NonNull
    private final String benefit; // 保険金・給付金

    @NonNull
    private final String periodOfInsurance; // 保険期間

    private final List<OptionName> optionNameList; // 特約情報

    @NonNull
    private final String insurancePremium; // 保険料

  }

  /**
   * 特約クラス 特約名を保持。
   *
   * DBから特約の情報を取得し、格納するクラス
   *
   * @author Inadome Takayuki
   * @version 1.0.0:2021.1.5
   */
  @Builder
  @Getter
  public static class OptionName {

    @NonNull
    private final String name; // 特約名

  }

}
