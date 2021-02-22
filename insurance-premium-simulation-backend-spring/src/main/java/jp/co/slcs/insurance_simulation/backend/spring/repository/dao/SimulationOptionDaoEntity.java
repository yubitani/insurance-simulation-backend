package jp.co.slcs.insurance_simulation.backend.spring.repository.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author horikawakeisuke 見積もりオプションテーブルのDAOEntity
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor(onConstructor = @__({ @PersistenceConstructor }))
@Builder
@Getter
@ToString
@Table("SIMULATION_OPTION")
public class SimulationOptionDaoEntity implements Persistable<Integer> {

	@Id
	private Integer simulationOptionId;
	@NonNull
	private String optionCode;
	@NonNull
	private Integer simulationId;

	@Override
	public Integer getId() {
		return simulationOptionId;
	}

	@Builder.Default
	@Setter
	@Transient
	private boolean isNew = false;
}
