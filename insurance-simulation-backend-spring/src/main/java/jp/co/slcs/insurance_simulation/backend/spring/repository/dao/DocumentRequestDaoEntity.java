package jp.co.slcs.insurance_simulation.backend.spring.repository.dao;

import java.time.LocalDateTime;
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
 * 資料請求テーブルのDAOEntity
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor(onConstructor = @__({@PersistenceConstructor}))
@Builder
@Getter
@ToString
@Table("DOCUMENT_REQUEST")
public class DocumentRequestDaoEntity implements Persistable<String> {

  @Id
  @NonNull
  private String receiptNo;

  @NonNull
  private LocalDateTime requestedDateTime;

  @NonNull
  private Integer customerId;

  @Override
  public String getId() {
    // TODO 自動生成されたメソッド・スタブ
    return receiptNo;
  }

  @Builder.Default
  @Setter
  @Transient
  private boolean isNew = false;
}
