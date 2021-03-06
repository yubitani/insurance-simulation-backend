package jp.co.slcs.insurance_simulation.backend.domain.entity;

import java.util.List;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Birthday;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 資料請求クラス 顧客から請求された資料において必要な情報を定義しているクラス
 */
@Builder
@Getter
@Setter
public class DocumentRequest {

  // 受け付け番号
  private String receiptNo;
  // 資料請求を実施した顧客情報
  private String name;

  private String nameKana;

  private Birthday birthday;

  private Sex sex;

  private String zipCode;

  private String address;

  private String tellNo;

  private String mailAddress;
  // 顧客が資料請求した保険商品の一覧
  private List<DocumentRequestProduct> documentRequestProductList;
  // 顧客が試算した保険料の合計
  private int sumInsurancePremium;

  /**
   * 顧客が資料請求した保険商品の情報を定義するクラス
   */
  @Builder
  @Getter
  @Setter
  public static class DocumentRequestProduct {
    // 取り扱い商品コード
    private String productCode;
    // 保険金・給付金コード
    private String benefitCode;
    // 保険期間コード
    private String periodOfInsuranceCode;
    // オプション
    private List<String> optionList;
    // 試算した保険料
    private int insurancePremium;
  }
}
