package jp.co.slcs.insurance_simulation.backend.domain.insurancepremium.impl;

import java.time.LocalDate;
import java.util.List;
import jp.co.slcs.insurance_simulation.backend.domain.insurancepremium.InsurancePremium;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Age;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Birthday;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Sex;
import lombok.NonNull;

/**
 * 医療保険 保険料計算の実装クラス
¥
 */
public class MedicalInsurancePremium implements InsurancePremium {

  @Override
  public int insurancePremiumCalculate(@NonNull LocalDate localDate, @NonNull Birthday birthday,
      @NonNull Sex sex, @NonNull String benefitCode, @NonNull String periodOfInsuranceCode,
      List<String> optionList) {

    // 商品構成要素チェック
    // 医療保険金額区分チェック
    switch (benefitCode) {
      case "5000perday":
        break;
      case "10000perday":
        break;
      default:
        throw new IllegalArgumentException();
    }

    double periodOfInsurance = 0;

    // 保険期間区分チェック
    switch (periodOfInsuranceCode) {
      case "lifetime":
        periodOfInsurance = 1.5;
        break;
      case "60yearsold":
        periodOfInsurance = 1;
        break;
      default:
        throw new IllegalArgumentException();
    }

    int optionCounter = 0;

    // オプションチェック
    if (optionList != null) {
      for (String option : optionList) {
        switch (option) {
          case "AdvancedMedical":
            break;
          case "TemporaryHospitalizationBenefit":
            break;
          default:
            throw new IllegalArgumentException();
        }
      }
      optionCounter = optionList.size();
    }

    // 年齢計算
    Age age = birthday.calculateAge(localDate);

    // 計算式
    int result = (int) ((1000 + age.getAge() * 10) * periodOfInsurance + optionCounter * 100);

    return result;
  }

}
