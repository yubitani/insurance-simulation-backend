package jp.co.slcs.insurance_simulation.backend.spring.presentation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.InsurancePremiumCalculateReqDto;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.InsurancePremiumCalculateResDto;
import jp.co.slcs.insurance_simulation.backend.usecase.InsurancePremiumCalculateUsecase;
import lombok.AllArgsConstructor;
import lombok.NonNull;


/**
 * 保険料計算APIクラス
 */
@AllArgsConstructor
@RestController
public class InsurancePremiumCalculateApi {

  @NonNull
  private final InsurancePremiumCalculateUsecase usecase;


  @NonNull
  @PostMapping(path = "/premiumcalc", consumes = MediaType.APPLICATION_JSON_VALUE)
  public InsurancePremiumCalculateResDto getCalcResult(
      @NonNull @RequestBody InsurancePremiumCalculateReqDto reqDto) {

    int usecaseResult =
        usecase.invoke(reqDto.getBirthday(), reqDto.getSex(), reqDto.getProductCode(),
            reqDto.getBenefitCode(), reqDto.getPeriodOfInsuranceCode(), reqDto.getOptionList());

    InsurancePremiumCalculateResDto result = InsurancePremiumCalculateResDto.builder()
        .insurancePremium(Integer.toString(usecaseResult)).build();

    return result;
  };

}
