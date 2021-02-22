package jp.co.slcs.insurancepremiumsimulation.backend.usecase;

import jp.co.slcs.insurancepremiumsimulation.backend.queryService.StatisticQueryService;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Builder.Default;

/**
 * 資料請求件数の推移データを取得するAPIのユースケース
 * 
 * @author KentoOtani
 * 
 * @version 1.0.0: 2020.12.15
 * @version 1.1.0: 2020.1.4 queryServiceを呼び出すよう修正。
 */
@AllArgsConstructor
public class GetTransitionUsecase {
	
    /**
     * QueryServiceクラスを取得
     */
	@NonNull
    private final StatisticQueryService queryService;

    /**
     * invokeメソッド
     *  
     * 保険商品単位での資料請求件数の推移データを取得する。
     * 
     * @param unitType 統計単位（日単位 or 時間単位）
     * @return 資料請求件数の推移データ（自クラス内で定義）
     * 
     */
    public TransitionData invoke(String unitType){
    	switch (unitType) {
			case "day":				
				return TransitionData.builder()
						.unitType(unitType)
						.data(queryService.fetchTransitionDataPerDay())
						.build();
			case "hour":
				return TransitionData.builder()
						.unitType(unitType)
						.data(queryService.fetchTransitionDataPerHour())
						.build();
			default:
				//TODO	独自例外をthrowしてpresentation層で処理させる。
				return null;
    	}	
    }
    
    /**
     * 推移データを格納するDTO（usecase → presentation）。
     * 
     */
    @Builder
    @Getter
    public static class TransitionData{
    	@Default
    	@NonNull
    	private final String acquiredDateTime = LocalDateTime.now().toString();
    	@NonNull
    	private final String unitType;
    	@NonNull
    	private final List<TransitionDataRow> data;
    }
    @Builder
    @Getter
    public static class TransitionDataRow{
    	@NonNull
    	private final String dateTime;
    	@NonNull
    	private final Long number; 
    }
    
}
