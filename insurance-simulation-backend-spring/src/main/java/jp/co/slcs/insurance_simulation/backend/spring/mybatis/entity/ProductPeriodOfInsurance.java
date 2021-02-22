package jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity;

import java.util.List;

import lombok.Data;

@Data
public class ProductPeriodOfInsurance {

	String productcode;
	List<PeriodOfInsuranceChoice> periodOfInsuranceList;

}
