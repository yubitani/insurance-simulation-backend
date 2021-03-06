package jp.co.slcs.insurance_simulation.backend.spring.repository.dao;

import org.springframework.data.repository.CrudRepository;

/**
 * お客様テーブルのDAO
 */
public interface CustomerDao extends CrudRepository<CustomerDaoEntity, Integer> {
}
