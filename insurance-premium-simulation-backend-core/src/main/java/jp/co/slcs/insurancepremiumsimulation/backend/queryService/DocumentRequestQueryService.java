package jp.co.slcs.insurancepremiumsimulation.backend.queryService;

import java.util.List;

import jp.co.slcs.insurancepremiumsimulation.backend.usecase.GetDocumentRequestsUsecase.DocumentRequestList;

/**
 * 資料請求一覧（５件）を取得する際に使用するQueryService。
 *
 * @author Inadome Takayuki
 * @version 1.0.0 2021.1.6
 *
 */
public interface DocumentRequestQueryService {

	/**
	 * 資料請求一覧を取得するメソッド。
	 *
	 * @return 資料請求一覧データ（最新５件分）
	 */
	public List<DocumentRequestList> fetchDocumentRequestLists();

}
