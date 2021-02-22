package jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity;

import java.util.List;

import lombok.Data;

@Data
public class BenefitBenefitChoice {
	String productcode;
	String productname;
	String benefitname;
	List<BenefitChoice> benefit;
}