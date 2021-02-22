package jp.co.slcs.insurancepremiumsimulation.backend.spring.repository.dao;

import org.springframework.data.repository.CrudRepository;

/**
 * @author horikawakeisuke お客様テーブルのDAO
 */
public interface CustomerDao extends CrudRepository<CustomerDaoEntity, Integer> {
}
