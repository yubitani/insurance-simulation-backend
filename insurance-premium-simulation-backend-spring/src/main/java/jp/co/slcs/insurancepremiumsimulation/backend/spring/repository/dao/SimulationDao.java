package jp.co.slcs.insurancepremiumsimulation.backend.spring.repository.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lombok.NonNull;

/**
 * @author horikawakeisuke 見積もりテーブルのDAO
 */
public interface SimulationDao extends CrudRepository<SimulationDaoEntity, Integer>{

	long countByProductCode(String productCode);

	public List<SimulationDaoEntity> findByReceiptNo(@NonNull String receiptNo);

}
