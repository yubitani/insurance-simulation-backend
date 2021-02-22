package jp.co.slcs.insurance_simulation.backend.spring.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity.BenefitBenefitChoice;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity.InsuranceProductInfo;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity.ProductOption;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity.ProductPeriodOfInsurance;

/**
 * @author Okamoto Yuma
 * @version 2.0.0 :2021.01.07
 *
 */
@Mapper
public interface InsuranceProductInfoMapper {

	List<InsuranceProductInfo> getInfos();

	//今のところ使ってないけど一応用意
	List<BenefitBenefitChoice> getBenefitBenefitChoice();

	//今のところ使ってないけど一応用意
	List<ProductPeriodOfInsurance> getProductPeriodOfInsurance();

	//今のところ使ってないけど一応用意
	List<ProductOption> getProductOption();

}
