package jp.co.slcs.insurance_simulation.backend.spring.usecase;

// java import
import java.util.ArrayList;
import java.util.List;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo.Benefit;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo.BenefitChoices;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo.Option;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo.PeriodOfInsurance;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.InsuranceProductInfoMapper;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity.BenefitChoice;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity.InsuranceProductInfo;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity.OptionChoice;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity.PeriodOfInsuranceChoice;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * 生年月日をもとに、見積可能な保険商品の情報を取得するAPIのユースケース
 */
@AllArgsConstructor
public class InsuranceProductsInfoUsecase {

  /**
   * リポジトリクラスを取得
   */
  private InsuranceProductInfoMapper mapper;

  /**
   * invokeメソッド 顧客から入力された情報(nputBirthday, inputSex)をもとに、 保険商品情報一覧を取得する。
   *
   * ※本来は生年月日、性別をもとに表示させる保険を絞る必要があるが、 当演習では特に加入条件考慮していないため、判定しない。
   *
   * @param inputBirthday , not {@code null} 顧客に入力された日付
   * @param inputSex, not {@code null} 顧客に入力された性別
   * @return List<InsuranceInfo> 保険商品情報
   * @author Okamoto Yuma
   * @version 1.0.0:2020.11.20
   */
  public List<InsuranceInfo> invoke() {

    /**
     * DBから保険商品情報を取得する
     */
    // List<InsuranceInfo> insuranceInfos = new ArrayList<>();
    List<InsuranceInfo> insuranceInfos2 = new ArrayList<>();

    /**
     * 下記 List<InsuranceProductInfo> infos = mapper.getInfos();で一発で取れるので削除
     * List<BenefitBenefitChoice> benefitBenefitChoices = mapper.getBenefitBenefitChoice();
     * List<ProductPeriodOfInsurance> productPeriodOfInsurances =
     * mapper.getProductPeriodOfInsurance(); List<ProductOption> productOptions =
     * mapper.getProductOption();
     */

    List<InsuranceProductInfo> infos = mapper.getInfos();

    for (InsuranceProductInfo info : infos) {
      @NonNull
      List<BenefitChoices> benefitConditionList = new ArrayList<>();;
      @NonNull
      List<PeriodOfInsurance> periodOfInsuranceList = new ArrayList<>();;
      @NonNull
      List<Option> optionList = new ArrayList<>();

      // 保険金選択肢を取得
      for (BenefitChoice benefit : info.getBenefit()) {
        BenefitChoices benefitChoice =
            BenefitChoices.builder().benefitCode(benefit.getBenefitchoicecode())
                .benefitChoicesName(benefit.getBenefitchoicename()).build();
        benefitConditionList.add(benefitChoice);
      }

      // 保険期間選択肢を取得
      for (PeriodOfInsuranceChoice periodOfInsuranceChoice : info.getPeriodOfInsuranceList()) {
        PeriodOfInsurance periodOfInsurance = PeriodOfInsurance.builder()
            .periodOfInsuranceCode(periodOfInsuranceChoice.getProductinsurancecode())
            .periodOfInsuranceName(periodOfInsuranceChoice.getProductinsurancename()).build();
        periodOfInsuranceList.add(periodOfInsurance);
      }

      // 特約一覧を取得
      for (OptionChoice optionChoice : info.getOptionlist()) {
        Option option = Option.builder().optionCode(optionChoice.getOptioncode())
            .name(optionChoice.getOptionname()).build();
        optionList.add(option);
      }

      InsuranceInfo insuranceInfo = InsuranceInfo.builder().productCode(info.getProductcode())
          .productName(info.getProductname())
          .benefit(Benefit.builder().benefitName(info.getBenefitname())
              .benefitConditionList(benefitConditionList).build())
          .periodOfInsuranceList(periodOfInsuranceList).optionList(optionList).build();
      insuranceInfos2.add(insuranceInfo);
    }

    /*
     * for(BenefitBenefitChoice benefitBenefitChoice : benefitBenefitChoices){
     *
     * @NonNull List<BenefitChoices> benefitConditionList = new ArrayList<>();;
     *
     * @NonNull List<PeriodOfInsurance> periodOfInsuranceList = new ArrayList<>();;
     *
     * @NonNull List<Option> optionList = new ArrayList<>();
     *
     * //BenefitBenefitChoiceからbenefitConditionListを取得 benefitConditionList.clear();
     * for(BenefitChoice benefitChoice : benefitBenefitChoice.getBenefit()){ BenefitChoices
     * benefitChoices = BenefitChoices.builder() .benefitCode(benefitChoice.getBenefitchoicecode())
     * .benefitChoicesName(benefitChoice.getBenefitchoicename()) .build();
     * benefitConditionList.add(benefitChoices); }
     *
     * //保険商品コードを取得 String productCode = benefitBenefitChoice.getProductcode();
     *
     * //ProductPeriodOfInsuanceからBenefitBenefitChoiceのproductcodeと同じcodeであるインスタンスを見つけ、
     * そこからperiodOfInsuranceListを取得する periodOfInsuranceList.clear(); for(ProductPeriodOfInsurance
     * productPeriodOfInsurance : productPeriodOfInsurances){
     * if(!productPeriodOfInsurance.getProductcode().equals(productCode)) continue; else
     * for(PeriodOfInsuranceChoice choice : productPeriodOfInsurance.getPeriodOfInsuranceList()){
     * PeriodOfInsurance periodOfInsurance = PeriodOfInsurance.builder()
     * .periodOfInsuranceCode(choice.getProductinsurancecode())
     * .periodOfInsuranceName(choice.getProductinsurancename()) .build();
     * periodOfInsuranceList.add(periodOfInsurance); } }
     *
     * //ProductOptionからBenefitBenefitChoiceのproductcodeと同じcodeであるインスタンスを見つけ、そこからoptionListを取得する
     * optionList.clear(); for(ProductOption productOption : productOptions){
     * if(!productOption.getProductcode().equals(productCode)) continue; else for(OptionChoice
     * choice : productOption.getOptionlist()){ Option option = Option.builder()
     * .optionCode(choice.getOptioncode()) .name(choice.getOptionname()) .build();
     * optionList.add(option); } }
     *
     * InsuranceInfo info = InsuranceInfo.builder()
     * .productCode(benefitBenefitChoice.getProductcode())
     * .productName(benefitBenefitChoice.getProductname()) .benefit(Benefit.builder()
     * .benefitName(benefitBenefitChoice.getBenefitname())
     * .benefitConditionList(benefitConditionList) .build() )
     * .periodOfInsuranceList(periodOfInsuranceList) .optionList(optionList) .build();
     * insuranceInfos.add(info); }
     */

    return insuranceInfos2;
  }

}
