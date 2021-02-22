package jp.co.slcs.insurancepremiumsimulation.backend.domain.insurancepremium.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.slcs.insurancepremiumsimulation.backend.domain.vo.Birthday;
import jp.co.slcs.insurancepremiumsimulation.backend.domain.vo.Sex;

class DeathInsurancePremiumTest {

	@Test
	void 保険料計算が想定通り計算できる() {
		DeathInsurancePremium deathInsurancePremium = new DeathInsurancePremium();
		LocalDate localDate = LocalDate.of(2020, 12, 1);
		Birthday birthday = new Birthday(LocalDate.of(2000, 2, 15));
		Sex sex = new Sex("1");
		String benefitCode = "5million";
		String periodOfInsuranceCode = "10years";
		List<String> optionList = new ArrayList<>();
		optionList.add("ThreeMajorIllnessInsurance");
		optionList.add("LivingNeeds");
		int actual = deathInsurancePremium.insurancePremiumCalculate(
								localDate, birthday, sex, benefitCode,
								periodOfInsuranceCode, optionList);
		assertEquals(1600, actual);
	}

}
