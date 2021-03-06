package jp.co.slcs.insurance_simulation.backend.spring.repository.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * 見積もりオプションテーブルのDAO
 */
public interface SimulationOptionDao extends CrudRepository<SimulationOptionDaoEntity, Integer> {

  public List<SimulationOptionDaoEntity> findBySimulationId(int simulationId);
}
