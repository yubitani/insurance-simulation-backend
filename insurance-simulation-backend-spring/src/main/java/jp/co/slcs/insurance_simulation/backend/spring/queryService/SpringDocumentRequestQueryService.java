package jp.co.slcs.insurance_simulation.backend.spring.queryService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo.Benefit;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo.BenefitChoices;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo.Option;
import jp.co.slcs.insurance_simulation.backend.domain.entity.InsuranceInfo.PeriodOfInsurance;
import jp.co.slcs.insurance_simulation.backend.queryService.DocumentRequestQueryService;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.CustomerDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.CustomerDaoEntity;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.DocumentRequestDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.DocumentRequestDaoEntity;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationDaoEntity;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationOptionDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationOptionDaoEntity;
import jp.co.slcs.insurance_simulation.backend.usecase.GetDocumentRequestsUsecase.DocumentRequestList;
import jp.co.slcs.insurance_simulation.backend.usecase.GetDocumentRequestsUsecase.OptionName;
import jp.co.slcs.insurance_simulation.backend.usecase.GetDocumentRequestsUsecase.Simulation;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * 資料請求一覧を取得する際に使用するQueryService。（SpringDataJDBCでの実装）
 *
 * @author Inadome Takayuki
 * @version 1.0.0 2021.1.6
 */
@AllArgsConstructor
@Service
public class SpringDocumentRequestQueryService implements DocumentRequestQueryService {

  @NonNull
  private final DocumentRequestDao documentRequestDao;

  @NonNull
  private final CustomerDao customerDao;

  @NonNull
  private final SimulationDao simulationDao;

  @NonNull
  private final SimulationOptionDao simulationOptionDao;

  @Override
  public List<DocumentRequestList> fetchDocumentRequestLists() {

    List<DocumentRequestDaoEntity> documentRequestDaoEntityList =
        documentRequestDao.fetchForTop10();

    if (documentRequestDaoEntityList != null) {

      // ↓↓↓ MyBatisでの取得が上手くいかなかったため、DB取得は行わず、一旦コンスタントで設定

      @NonNull
      List<InsuranceInfo> insuranceInfos = new ArrayList<>();

      // 死亡保険

      @NonNull
      List<BenefitChoices> benefitConditionList_1 = new ArrayList<>();
      @NonNull
      List<PeriodOfInsurance> periodOfInsuranceList_1 = new ArrayList<>();
      @NonNull
      List<Option> optionList_1 = new ArrayList<>();

      BenefitChoices benefitChoiceBuild_1 =
          BenefitChoices.builder().benefitCode("5million").benefitChoicesName("5,000,000円").build();
      benefitConditionList_1.add(benefitChoiceBuild_1);
      benefitChoiceBuild_1 = BenefitChoices.builder().benefitCode("10million")
          .benefitChoicesName("10,000,000円").build();
      benefitConditionList_1.add(benefitChoiceBuild_1);
      benefitChoiceBuild_1 = BenefitChoices.builder().benefitCode("15million")
          .benefitChoicesName("15,000,000円").build();
      benefitConditionList_1.add(benefitChoiceBuild_1);
      benefitChoiceBuild_1 = BenefitChoices.builder().benefitCode("20million")
          .benefitChoicesName("20,000,000円").build();
      benefitConditionList_1.add(benefitChoiceBuild_1);
      benefitChoiceBuild_1 = BenefitChoices.builder().benefitCode("25million")
          .benefitChoicesName("25,000,000円").build();
      benefitConditionList_1.add(benefitChoiceBuild_1);
      benefitChoiceBuild_1 = BenefitChoices.builder().benefitCode("30million")
          .benefitChoicesName("30,000,000円").build();
      benefitConditionList_1.add(benefitChoiceBuild_1);

      // 保険期間選択肢を取得
      PeriodOfInsurance periodOfInsuranceBuild_1 = PeriodOfInsurance.builder()
          .periodOfInsuranceCode("10years").periodOfInsuranceName("10年").build();
      periodOfInsuranceList_1.add(periodOfInsuranceBuild_1);
      periodOfInsuranceBuild_1 = PeriodOfInsurance.builder().periodOfInsuranceCode("20years")
          .periodOfInsuranceName("20年").build();
      periodOfInsuranceList_1.add(periodOfInsuranceBuild_1);
      periodOfInsuranceBuild_1 = PeriodOfInsurance.builder().periodOfInsuranceCode("30years")
          .periodOfInsuranceName("30年").build();
      periodOfInsuranceList_1.add(periodOfInsuranceBuild_1);

      // 特約一覧を取得
      Option optionBuild_1 =
          Option.builder().optionCode("ThreeMajorIllnessInsurance").name("三大疾病保障特約").build();
      optionList_1.add(optionBuild_1);
      optionBuild_1 = Option.builder().optionCode("LivingNeeds").name("リビング・ニーズ特約").build();
      optionList_1.add(optionBuild_1);

      InsuranceInfo insuranceInfoBuild =
          InsuranceInfo.builder().productCode("DeathInsurance-001").productName("死亡保険")
              .benefit(Benefit.builder().benefitName("死亡保険金額")
                  .benefitConditionList(benefitConditionList_1).build())
              .periodOfInsuranceList(periodOfInsuranceList_1).optionList(optionList_1).build();
      insuranceInfos.add(insuranceInfoBuild);

      // 医療保険

      @NonNull
      List<BenefitChoices> benefitConditionList_2 = new ArrayList<>();
      @NonNull
      List<PeriodOfInsurance> periodOfInsuranceList_2 = new ArrayList<>();
      @NonNull
      List<Option> optionList_2 = new ArrayList<>();

      BenefitChoices benefitChoiceBuild_2 =
          BenefitChoices.builder().benefitCode("5000perday").benefitChoicesName("5,000円").build();
      benefitConditionList_2.add(benefitChoiceBuild_2);
      benefitChoiceBuild_2 =
          BenefitChoices.builder().benefitCode("10000perday").benefitChoicesName("10,000円").build();
      benefitConditionList_2.add(benefitChoiceBuild_2);

      // 保険期間選択肢を取得
      PeriodOfInsurance periodOfInsuranceBuild_2 = PeriodOfInsurance.builder()
          .periodOfInsuranceCode("lifetime").periodOfInsuranceName("終身").build();
      periodOfInsuranceList_2.add(periodOfInsuranceBuild_2);
      periodOfInsuranceBuild_2 = PeriodOfInsurance.builder().periodOfInsuranceCode("60yearsold")
          .periodOfInsuranceName("60歳").build();
      periodOfInsuranceList_2.add(periodOfInsuranceBuild_2);

      // 特約一覧を取得
      Option optionBuild_2 = Option.builder().optionCode("AdvancedMedical").name("先進医療特約").build();
      optionList_2.add(optionBuild_2);
      optionBuild_2 =
          Option.builder().optionCode("TemporaryHospitalizationBenefit").name("入院一時給付特約").build();
      optionList_2.add(optionBuild_2);

      insuranceInfoBuild =
          InsuranceInfo.builder().productCode("MedicalInsurance-001").productName("医療保険")
              .benefit(Benefit.builder().benefitName("入院給付金（日額）")
                  .benefitConditionList(benefitConditionList_2).build())
              .periodOfInsuranceList(periodOfInsuranceList_2).optionList(optionList_2).build();
      insuranceInfos.add(insuranceInfoBuild);

      // がん保険

      @NonNull
      List<BenefitChoices> benefitConditionList_3 = new ArrayList<>();
      @NonNull
      List<PeriodOfInsurance> periodOfInsuranceList_3 = new ArrayList<>();
      @NonNull
      List<Option> optionList_3 = new ArrayList<>();

      BenefitChoices benefitChoiceBuild_3 =
          BenefitChoices.builder().benefitCode("5000perday").benefitChoicesName("5,000円").build();
      benefitConditionList_3.add(benefitChoiceBuild_3);
      benefitChoiceBuild_3 =
          BenefitChoices.builder().benefitCode("10000perday").benefitChoicesName("10,000円").build();
      benefitConditionList_3.add(benefitChoiceBuild_3);

      // 保険期間選択肢を取得
      PeriodOfInsurance periodOfInsuranceBuild_3 = PeriodOfInsurance.builder()
          .periodOfInsuranceCode("lifetime").periodOfInsuranceName("終身").build();
      periodOfInsuranceList_3.add(periodOfInsuranceBuild_3);
      periodOfInsuranceBuild_3 = PeriodOfInsurance.builder().periodOfInsuranceCode("10years")
          .periodOfInsuranceName("10年").build();
      periodOfInsuranceList_3.add(periodOfInsuranceBuild_3);

      // 特約一覧を取得
      Option optionBuild_3 = Option.builder().optionCode("AdvancedMedical").name("先進医療特約").build();
      optionList_3.add(optionBuild_3);

      insuranceInfoBuild =
          InsuranceInfo.builder().productCode("CancerInsurance-001").productName("がん保険")
              .benefit(Benefit.builder().benefitName("入院給付金（日額）")
                  .benefitConditionList(benefitConditionList_3).build())
              .periodOfInsuranceList(periodOfInsuranceList_3).optionList(optionList_3).build();
      insuranceInfos.add(insuranceInfoBuild);

      // ↑↑↑

      List<DocumentRequestList> documentRequestList = new ArrayList<>();

      for (DocumentRequestDaoEntity documentRequestDaoEntity : documentRequestDaoEntityList) {

        CustomerDaoEntity customerDaoEntity =
            customerDao.findById(documentRequestDaoEntity.getCustomerId()).get();

        List<SimulationDaoEntity> simulationDaoEntityList =
            simulationDao.findByReceiptNo(documentRequestDaoEntity.getReceiptNo());

        List<Simulation> simulationList = new ArrayList<>();

        Integer sumInsurancePremium = 0;

        for (SimulationDaoEntity simulationDaoEntity : simulationDaoEntityList) {

          InsuranceInfo insuranceInfoTemp = null;

          for (InsuranceInfo insuranceInfo : insuranceInfos) {

            if (insuranceInfo.getProductCode().equals(simulationDaoEntity.getProductCode())) {
              insuranceInfoTemp = insuranceInfo;
              break;
            }
          }

          List<SimulationOptionDaoEntity> simulationOptionDaoEntityList =
              simulationOptionDao.findBySimulationId(simulationDaoEntity.getSimulationId());

          List<OptionName> optionNameList = new ArrayList<>();

          for (SimulationOptionDaoEntity simulationOptionDaoEntity : simulationOptionDaoEntityList) {

            Option optionTemp = null;

            for (Option option : insuranceInfoTemp.getOptionList()) {

              if (option.getOptionCode().equals(simulationOptionDaoEntity.getOptionCode())) {
                optionTemp = option;
                break;
              }
            }

            optionNameList.add(OptionName.builder().name(optionTemp.getName()).build());

          }

          String benefitChoicesName = null;

          for (BenefitChoices benefitChoices : insuranceInfoTemp.getBenefit()
              .getBenefitConditionList()) {

            if (benefitChoices.getBenefitCode().equals(simulationDaoEntity.getBenefitCode())) {
              benefitChoicesName = benefitChoices.getBenefitChoicesName();
              break;
            }
          }

          String periodOfInsuranceName = null;

          for (PeriodOfInsurance periodOfInsurance : insuranceInfoTemp.getPeriodOfInsuranceList()) {

            if (periodOfInsurance.getPeriodOfInsuranceCode()
                .equals(simulationDaoEntity.getPeriodOfInsulanceCode())) {
              periodOfInsuranceName = periodOfInsurance.getPeriodOfInsuranceName();
              break;
            }
          }

          simulationList.add(
              Simulation.builder().simulationId(simulationDaoEntity.getSimulationId().toString())
                  .productName(insuranceInfoTemp.getProductName())
                  .benefitHeaderName(insuranceInfoTemp.getBenefit().getBenefitName())
                  .benefit(benefitChoicesName).periodOfInsurance(periodOfInsuranceName)
                  .optionNameList(optionNameList)
                  .insurancePremium(
                      String.format("%,d", simulationDaoEntity.getInsurancePremium()) + "円")
                  .build());

          sumInsurancePremium += simulationDaoEntity.getInsurancePremium();

        }

        documentRequestList
            .add(DocumentRequestList.builder().receiptNo(documentRequestDaoEntity.getReceiptNo())
                .registrationDate(documentRequestDaoEntity.getRequestedDateTime().toLocalDate())
                .name(customerDaoEntity.getName()).nameKana(customerDaoEntity.getKana())
                .birthday(customerDaoEntity.getBirthday()).sex(customerDaoEntity.getSex())
                .zipCode(customerDaoEntity.getZipcode()).address(customerDaoEntity.getAddress())
                .telNo(customerDaoEntity.getTelNo()).mailAddress(customerDaoEntity.getMail())
                .simulations(simulationList)
                .sumInsurancePremium(String.format("%,d", sumInsurancePremium) + "円").build());

      }
      return documentRequestList;
    } else {
      return null;
    }
  }

}
