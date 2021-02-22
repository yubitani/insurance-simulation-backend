package jp.co.slcs.insurance_simulation.backend.domain.insurancepremium;

import jp.co.slcs.insurance_simulation.backend.domain.insurancepremium.impl.CancerInsurancePremium;
import jp.co.slcs.insurance_simulation.backend.domain.insurancepremium.impl.DeathInsurancePremium;
import jp.co.slcs.insurance_simulation.backend.domain.insurancepremium.impl.MedicalInsurancePremium;

/**
 * 保険料計算のファクトリークラス
 * @author Inadome Takayuki
 * @version 1.0.0: 2020.11.18
 */
public enum InsurancePremiumFactory {

	DEATHINSURANCE("DeathInsurance-001", new DeathInsurancePremium()), MEDICALINSURANCE("MedicalInsurance-001",
			new MedicalInsurancePremium()), CANCERINSURANCE("CancerInsurance-001", new CancerInsurancePremium());

	private final String productCode;
	private final InsurancePremium insurancePremium;

	private InsurancePremiumFactory(
			String productCode, InsurancePremium insurancePremium) {
		this.productCode = productCode;
		this.insurancePremium = insurancePremium;
	}

	public static InsurancePremium create(String productCode) {
		for (InsurancePremiumFactory insurancePremiumFactoryType : InsurancePremiumFactory.values()) {
			if (productCode.equals(insurancePremiumFactoryType.productCode)) {
				return insurancePremiumFactoryType.insurancePremium;
			}
		}
		throw new IllegalArgumentException(productCode + "invalid");
	}

}
