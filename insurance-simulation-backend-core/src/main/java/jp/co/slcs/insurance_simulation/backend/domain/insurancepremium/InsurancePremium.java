package jp.co.slcs.insurance_simulation.backend.domain.insurancepremium;

import java.time.LocalDate;
import java.util.List;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Birthday;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Sex;
import lombok.NonNull;


/**
 * 保険料計算のインターフェイスクラス
 */
public interface InsurancePremium {

  // 引数は基準日、生年月日、性別、商品構成要素（給付金コード、保険期間コード、オプション）
  public int insurancePremiumCalculate(@NonNull LocalDate localDate, @NonNull Birthday birthday,
      @NonNull Sex sex, @NonNull String benefitCode, @NonNull String periodOfInsuranceCode,
      List<String> optionList);

}
