package jp.co.slcs.insurancepremiumsimulation.backend.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.co.slcs.insurancepremiumsimulation.backend.usecase.GetDocumentRequestsUsecase;
import jp.co.slcs.insurancepremiumsimulation.backend.usecase.GetTotalUsecase;
import jp.co.slcs.insurancepremiumsimulation.backend.usecase.GetTransitionUsecase;
import jp.co.slcs.insurancepremiumsimulation.backend.usecase.InsurancePremiumCalculateUsecase;
import jp.co.slcs.insurancepremiumsimulation.backend.queryService.StatisticQueryService;
import jp.co.slcs.insurancepremiumsimulation.backend.queryService.DocumentRequestQueryService;
import jp.co.slcs.insurancepremiumsimulation.backend.spring.mybatis.InsuranceProductInfoMapper;
import jp.co.slcs.insurancepremiumsimulation.backend.spring.usecase.InsuranceProductsInfoUsecase;

/**
 * SpringCoreにUsecaseインスタンスを登録するConfigクラス
 * InsurancePremiumSimulationConfigクラスとInsuranceProductsInfoConfigクラスを統合しました。
 * @author OkamotoYuma
 * @version 1.0.0:2020.12.2
 *
 * GetDocumentRequestsUsecaseクラスに引数を追加しました。
 * @author Inadome Takayuki
 * @version 1.1.0:2021.1.6
 *
 */
@Configuration
public class InsurancePremiumSimurationApiConfig {

    @Bean
	public InsurancePremiumCalculateUsecase insurancePremiumCalculateUsecase() {
		return new InsurancePremiumCalculateUsecase();
    }

    @Bean
    public InsuranceProductsInfoUsecase insuranceProductsInfoUsecase(InsuranceProductInfoMapper mapper){
        return new InsuranceProductsInfoUsecase(mapper);
    }

    @Bean
    public GetDocumentRequestsUsecase getDocumentRequestsUsecase(DocumentRequestQueryService documentRequestQueryService) {
    	return new GetDocumentRequestsUsecase(documentRequestQueryService);
    }

    @Bean
    public GetTotalUsecase getTotalUsecase(StatisticQueryService statisticQueryService) {
    	return new GetTotalUsecase(statisticQueryService);
    }

    @Bean
    public GetTransitionUsecase getTransitionUsecase(StatisticQueryService statisticQueryService) {
    	return new GetTransitionUsecase(statisticQueryService);
    }

}
