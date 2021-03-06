package jp.co.slcs.insurance_simulation.backend.spring.repository.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import lombok.NonNull;

/**
 * 見積もりテーブルのDAO
 */
public interface SimulationDao extends CrudRepository<SimulationDaoEntity, Integer> {

  long countByProductCode(String productCode);

  public List<SimulationDaoEntity> findByReceiptNo(@NonNull String receiptNo);

}
