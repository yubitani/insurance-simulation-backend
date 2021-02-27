package jp.co.slcs.insurance_simulation.backend.usecase;

import java.time.LocalDate;
import java.util.List;

import jp.co.slcs.insurance_simulation.backend.domain.insurancepremium.InsurancePremium;
import jp.co.slcs.insurance_simulation.backend.domain.insurancepremium.InsurancePremiumFactory;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Birthday;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Sex;
import jp.co.slcs.insurance_simulation.backend.usecase.exception.InvalidInputFormatException;
import lombok.NonNull;


/**
 * 保険料計算のユースケースクラス
 * 
 * @author Inadome Takayuki
 * @version 1.0.1: 2020.12.20
 */
public class InsurancePremiumCalculateUsecase {

  public int invoke(@NonNull LocalDate inputBirthday, @NonNull String inputSex,
      @NonNull String productCode, @NonNull String benefitCode,
      @NonNull String periodOfInsuranceCode, List<String> optionList) {

    try {
      // 入力チェック（生年月日、性別）
      Birthday birthday = new Birthday(inputBirthday);
      Sex sex = new Sex(inputSex);

      // 保険料オブジェクト生成（商品別保険料オブジェクトをファクトリーで生成）
      InsurancePremium insurancePremium = InsurancePremiumFactory.create(productCode);

      // 基準日設定（シミュレーションでは入力日時を基準日とする）
      LocalDate localDate = LocalDate.now();

      // 保険料計算（基準日、生年月日、性別、商品構成要素）
      int result = insurancePremium.insurancePremiumCalculate(localDate, birthday, sex, benefitCode,
          periodOfInsuranceCode, optionList);

      return result;

    } catch (IllegalArgumentException e) {
      throw new InvalidInputFormatException(e.getMessage());
    }
  }

}
