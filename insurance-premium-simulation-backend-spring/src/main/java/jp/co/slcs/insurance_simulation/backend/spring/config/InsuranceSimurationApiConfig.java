package jp.co.slcs.insurance_simulation.backend.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.co.slcs.insurance_simulation.backend.queryService.DocumentRequestQueryService;
import jp.co.slcs.insurance_simulation.backend.queryService.StatisticQueryService;
import jp.co.slcs.insurance_simulation.backend.spring.mybatis.InsuranceProductInfoMapper;
import jp.co.slcs.insurance_simulation.backend.spring.usecase.InsuranceProductsInfoUsecase;
import jp.co.slcs.insurance_simulation.backend.usecase.GetDocumentRequestsUsecase;
import jp.co.slcs.insurance_simulation.backend.usecase.GetTotalUsecase;
import jp.co.slcs.insurance_simulation.backend.usecase.GetTransitionUsecase;
import jp.co.slcs.insurance_simulation.backend.usecase.InsurancePremiumCalculateUsecase;

/**
 * SpringCoreにUsecaseインスタンスを登録するConfigクラス
 * insuranceSimulationConfigクラスとInsuranceProductsInfoConfigクラスを統合しました。
 * @author OkamotoYuma
 * @version 1.0.0:2020.12.2
 *
 * GetDocumentRequestsUsecaseクラスに引数を追加しました。
 * @author Inadome Takayuki
 * @version 1.1.0:2021.1.6
 *
 */
@Configuration
public class InsuranceSimurationApiConfig {

	@Bean
	public InsurancePremiumCalculateUsecase insuranceCalculateUsecase() {
		return new InsurancePremiumCalculateUsecase();
	}

	@Bean
	public InsuranceProductsInfoUsecase insuranceProductsInfoUsecase(InsuranceProductInfoMapper mapper) {
		return new InsuranceProductsInfoUsecase(mapper);
	}

	@Bean
	public GetDocumentRequestsUsecase getDocumentRequestsUsecase(
			DocumentRequestQueryService documentRequestQueryService) {
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
