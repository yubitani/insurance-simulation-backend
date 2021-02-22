package jp.co.slcs.insurancepremiumsimulation.backend.queryService;

import java.util.List;

import jp.co.slcs.insurancepremiumsimulation.backend.usecase.GetTotalUsecase.TotalDataRow;
import jp.co.slcs.insurancepremiumsimulation.backend.usecase.GetTransitionUsecase.TransitionDataRow;


/**
 * 資料請求件数についての統計情報（累計データ、推移データ）を取得する際に使用するQueryService。
 * 
 * @author KentoOtani
 * @version 1.0.0 2021.1.4
 *
 */
public interface StatisticQueryService {
	
	/**
	 * 資料請求件数の商品別累計データを取得するメソッド。
	 * 
	 * @return 資料請求件数の商品別（プロダクトコード別）累計データ
	 */
	public List<TotalDataRow> fetchTotalData();
	
	/**
	 * 資料請求件数の推移データ（日別）を取得するメソッド。
	 * 
	 * @return 資料請求件数の日別推移データ
	 */
	public List<TransitionDataRow> fetchTransitionDataPerDay();
	
	/**
	 * 資料請求件数の推移データ（1時間単位）を取得するメソッド。
	 * 
	 * @return 資料請求件数の1時間単位の推移データ
	 */
	public List<TransitionDataRow> fetchTransitionDataPerHour();

}
