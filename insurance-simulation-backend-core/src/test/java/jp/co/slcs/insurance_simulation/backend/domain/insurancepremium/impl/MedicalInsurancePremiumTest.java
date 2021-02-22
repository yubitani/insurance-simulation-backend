package jp.co.slcs.insurance_simulation.backend.domain.insurancepremium.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.slcs.insurance_simulation.backend.domain.vo.Birthday;
import jp.co.slcs.insurance_simulation.backend.domain.vo.Sex;

class MedicalInsurancePremiumTest {

	@Test
	void 保険料計算が想定通り計算できる() {
		MedicalInsurancePremium medicalInsurancePremium = new MedicalInsurancePremium();
		LocalDate localDate = LocalDate.of(2020, 12, 1);
		Birthday birthday = new Birthday(LocalDate.of(2000, 2, 15));
		Sex sex = new Sex("1");
		String benefitCode = "5000perday";
		String periodOfInsuranceCode = "lifetime";
		List<String> optionList = new ArrayList<>();
		optionList.add("AdvancedMedical");
		optionList.add("TemporaryHospitalizationBenefit");
		int actual = medicalInsurancePremium.insurancePremiumCalculate(
				localDate, birthday, sex, benefitCode,
				periodOfInsuranceCode, optionList);
		assertEquals(2000, actual);
	}
}
