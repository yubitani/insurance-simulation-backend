package jp.co.slcs.insurancepremiumsimulation.backend.spring.mybatis.entity;
 
import java.util.List;
 
import lombok.Data;
@Data
public class InsuranceProductInfo {
 
    private String productcode;
    private String productname;
    private String benefitname;
    private List<BenefitChoice> benefit;
    private List<PeriodOfInsuranceChoice> periodOfInsuranceList;
    private List<OptionChoice> optionlist;
 
}
