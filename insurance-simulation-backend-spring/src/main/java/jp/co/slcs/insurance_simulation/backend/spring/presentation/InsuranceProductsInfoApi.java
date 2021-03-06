package jp.co.slcs.insurance_simulation.backend.spring.presentation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.InsuranceProductInfoMapper;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.InsuranceProductInfoResDto;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.InsuranceProductInfoResDto.Benefit;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.InsuranceProductInfoResDto.BenefitChoices;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.InsuranceProductInfoResDto.Option;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.InsuranceProductInfoResDto.PeriodOfInsurance;
import jp.co.slcs.insurance_simulation.backend.spring.usecase.InsuranceProductsInfoUsecase;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@RestController
public class InsuranceProductsInfoApi {

  /**
   * 保険商品情報一覧取得業務ロジック
   */
  @NonNull
  private final InsuranceProductsInfoUsecase usecase;

  @NonNull
  private final InsuranceProductInfoMapper mapper;

  /**
   * 業務ロジックで取得した保険商品情報を加工し、Modelに移し変える
   *
   * @return
   */
  @NonNull
  @GetMapping(path = "/InsuranceProductsInfo", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<InsuranceProductInfoResDto> getInsuranceProductInfo() {
    return usecase.invoke().stream().map((insuranceInfo -> {
      return InsuranceProductInfoResDto.builder().productCode(insuranceInfo.getProductCode())
          .name(insuranceInfo.getProductName())
          .benefit(Benefit.builder().benefitName(insuranceInfo.getBenefit().getBenefitName())
              .benefitConditionList(insuranceInfo.getBenefit().getBenefitConditionList().stream()
                  .map(benefitChoices -> {
                    return BenefitChoices.builder().benefitCode(benefitChoices.getBenefitCode())
                        .benefitChoicesName(benefitChoices.getBenefitChoicesName()).build();
                  }).collect(Collectors.toList()))
              .build())
          .periodOfInsuranceList(
              insuranceInfo.getPeriodOfInsuranceList().stream().map(periodOfInsurance -> {
                return PeriodOfInsurance.builder()
                    .periodOfInsuranceCode(periodOfInsurance.getPeriodOfInsuranceCode())
                    .periodOfInsuranceName(periodOfInsurance.getPeriodOfInsuranceName()).build();
              }).collect(Collectors.toList()))
          .optionList(insuranceInfo.getOptionList().stream().map(option -> {
            return Option.builder().optionCode(option.getOptionCode()).name(option.getName())
                .build();
          }).collect(Collectors.toList())).build();
    })).collect(Collectors.toList());
  }
}
