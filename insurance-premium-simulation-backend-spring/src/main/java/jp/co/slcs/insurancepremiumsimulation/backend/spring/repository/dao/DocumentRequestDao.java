package jp.co.slcs.insurancepremiumsimulation.backend.spring.repository.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author horikawakeisuke
 *　資料請求テーブルのDAO
 */
public interface DocumentRequestDao extends CrudRepository<DocumentRequestDaoEntity, String>{


	/**
	 * 資料請求日時（requestedDateTime）について、特定の期間内（引数で指定）のDocumentRequestDaoEntityを取得する。
	 *
	 * @author KentoOtani
	 *
	 * @param int from 本日の0:00を起点（ゼロ）とした時に取得の起点となる日数。（例: 14日前から => -14 ）
	 * @param int to 本日の0:00を起点（ゼロ）とした時に取得の終点となる日数。（例: 明日の0:00まで => 1 ）
	 * @return List<DocumentRequestDaoEntity>
	 */
	@Query("SELECT * FROM DOCUMENT_REQUEST WHERE REQUESTED_DATE_TIME BETWEEN (TRUNC(CURRENT_DATE) + :from) AND (TRUNC(CURRENT_DATE) + :to)")
	public List<DocumentRequestDaoEntity> fetchForSpecificPeriod(int from, int to);

	/**
	 * 資料請求日時（requestedDateTime）の降順に直近１０件分のDocumentRequestDaoEntityを取得する。
	 *
	 * @author Inadome Takayuki
	 *
	 * @return List<DocumentRequestDaoEntity>
	 */
	@Query("SELECT * FROM ( SELECT * FROM DOCUMENT_REQUEST ORDER BY REQUESTED_DATE_TIME DESC ) WHERE ROWNUM <= 10 ")
	public List<DocumentRequestDaoEntity> fetchForTop10();

}
