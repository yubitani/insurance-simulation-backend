package jp.co.slcs.insurance_simulation.backend.usecase;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class InsurancePremiumCalculateUsecaseTest {

  @Test
  void 死亡保険の保険料計算が想定通り計算できる() {
    InsurancePremiumCalculateUsecase usecase = new InsurancePremiumCalculateUsecase();

    LocalDate inputBirthday = LocalDate.now().minusYears(20).minusMonths(1);
    String inputSex = "1";
    String productCode = "DeathInsurance-001";
    String benefitCode = "5million";
    String periodOfInsuranceCode = "10years";
    List<String> optionList = new ArrayList<>();
    optionList.add("ThreeMajorIllnessInsurance");
    optionList.add("LivingNeeds");

    int actual = usecase.invoke(inputBirthday, inputSex, productCode, benefitCode,
        periodOfInsuranceCode, optionList);
    assertEquals(1600, actual);
  }

  @Test
  void 医療保険の保険料計算が想定通り計算できる() {
    InsurancePremiumCalculateUsecase usecase = new InsurancePremiumCalculateUsecase();

    LocalDate inputBirthday = LocalDate.now().minusYears(20).minusMonths(1);
    String inputSex = "1";
    String productCode = "MedicalInsurance-001";
    String benefitCode = "5000perday";
    String periodOfInsuranceCode = "lifetime";
    List<String> optionList = new ArrayList<>();
    optionList.add("AdvancedMedical");
    optionList.add("TemporaryHospitalizationBenefit");

    int actual = usecase.invoke(inputBirthday, inputSex, productCode, benefitCode,
        periodOfInsuranceCode, optionList);
    assertEquals(2000, actual);
  }

  @Test
  void がん保険の保険料計算が想定通り計算できる() {
    InsurancePremiumCalculateUsecase usecase = new InsurancePremiumCalculateUsecase();

    LocalDate inputBirthday = LocalDate.now().minusYears(20).minusMonths(1);
    String inputSex = "1";
    String productCode = "CancerInsurance-001";
    String benefitCode = "5000perday";
    String periodOfInsuranceCode = "lifetime";
    List<String> optionList = new ArrayList<>();
    optionList.add("AdvancedMedical");

    int actual = usecase.invoke(inputBirthday, inputSex, productCode, benefitCode,
        periodOfInsuranceCode, optionList);
    assertEquals(2100, actual);
  }

}
