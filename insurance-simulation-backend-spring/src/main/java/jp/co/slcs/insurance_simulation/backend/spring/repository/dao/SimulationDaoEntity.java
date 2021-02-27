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
 * @author horikawakeisuke 見積もりテーブルのDAOEntity
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor(onConstructor = @__({@PersistenceConstructor}))
@Builder
@Getter
@ToString
@Table("SIMULATION")
public class SimulationDaoEntity implements Persistable<Integer> {

  @Id
  private final Integer simulationId;

  @NonNull
  private final String productCode;

  @NonNull
  private final String benefitCode;

  @NonNull
  private final String periodOfInsulanceCode;

  @NonNull
  private final Integer insurancePremium;

  @NonNull
  private final String receiptNo;

  @Override
  public Integer getId() {
    return simulationId;
  }

  @Builder.Default
  @Setter
  @Transient
  private boolean isNew = false;

}
