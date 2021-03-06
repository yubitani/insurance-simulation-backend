package jp.co.slcs.insurance_simulation.backend.queryService;

import java.util.List;
import jp.co.slcs.insurance_simulation.backend.usecase.GetDocumentRequestsUsecase.DocumentRequestList;

/**
 * 資料請求一覧（５件）を取得する際に使用するQueryService。
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
