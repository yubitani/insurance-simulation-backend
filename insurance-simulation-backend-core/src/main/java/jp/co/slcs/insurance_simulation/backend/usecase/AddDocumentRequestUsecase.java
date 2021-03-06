package jp.co.slcs.insurance_simulation.backend.usecase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jp.co.slcs.insurance_simulation.backend.domain.entity.DocumentRequest;
import jp.co.slcs.insurance_simulation.backend.domain.entity.DocumentRequest.DocumentRequestProduct;
import jp.co.slcs.insurance_simulation.backend.domain.entity.repository.DocumentRequestRepository;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Birthday;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;



/**
 * 資料請求を受け付けるAPIのユースケース ※DB接続周りについて、一旦コメントアウト中
 */
@AllArgsConstructor
public class AddDocumentRequestUsecase {


  @NonNull
  private final DocumentRequestRepository repository;

  public String invoke(@NonNull String inputName, @NonNull String inputNameKana,
      @NonNull LocalDate inputBirthday, @NonNull String inputSex, @NonNull String inputZipCode,
      @NonNull String inputAddress, @NonNull String inputTellNo, @NonNull String inputMailAddress,
      @NonNull List<Simulation> simulationList, @NonNull String inputSumInsurancePremium) {

    // 入力チェック（生年月日、性別）
    Birthday birthday = new Birthday(inputBirthday);
    Sex sex = new Sex(inputSex);

    // 受け付け番号を採番する
    String receiptNo = this.creatReceiptNo(inputTellNo);

    // 受け取った商品情報リストをEntityクラスへ変換する
    List<DocumentRequestProduct> documentRequestProductList = new ArrayList<>();
    for (Simulation inputDocumentRequestProductReq : simulationList) {
      documentRequestProductList.add(DocumentRequestProduct.builder()
          .productCode(inputDocumentRequestProductReq.getProductCode())
          .benefitCode(inputDocumentRequestProductReq.getBenefitCode())
          .periodOfInsuranceCode(inputDocumentRequestProductReq.getPeriodOfInsuranceCode())
          .optionList(inputDocumentRequestProductReq.getOptionList())
          .insurancePremium(Integer.parseInt(inputDocumentRequestProductReq.getInsurancePremium()))
          .build());
    }

    // 受け取った情報を元にEntityクラスを生成
    DocumentRequest documentRequest = DocumentRequest.builder().receiptNo(receiptNo).name(inputName)
        .nameKana(inputNameKana).birthday(birthday).sex(sex).zipCode(inputZipCode)
        .address(inputAddress).tellNo(inputTellNo).mailAddress(inputMailAddress)
        .documentRequestProductList(documentRequestProductList)
        .sumInsurancePremium(Integer.parseInt(inputSumInsurancePremium)).build();

    // documentRequestをDBへ保存する
    repository.add(documentRequest);

    // 受け付け番号を返す
    return receiptNo;

    // TODO DB接続時のエラーに対する処理を追加
  }

  // 受け付け番号を採番するメソッド
  private String creatReceiptNo(String telNo) {
    // 電話番号とランダムな数字２桁から受付番号を採番
    return telNo.substring(telNo.length() - 2)
        + Double.toString(Math.random() * 10000).substring(0, 2);
  }

  /**
   * @author 堀川 TODO 要確認 AddDocumentRequestApiクラスと当クラスのやり取りに使用 AddDocumentRequestReqDtoクラスに使用。
   */
  @Builder
  @Getter
  @Setter
  public static class Simulation {
    // 取り扱い商品コード
    private String productCode;
    // 保険金・給付金コード
    private String benefitCode;
    // 保険期間コード
    private String periodOfInsuranceCode;
    // オプション
    private List<String> optionList;
    // 試算した保険料
    private String insurancePremium;
  }
}

