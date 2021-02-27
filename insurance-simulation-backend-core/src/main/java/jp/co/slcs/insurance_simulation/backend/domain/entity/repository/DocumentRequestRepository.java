package jp.co.slcs.insurance_simulation.backend.domain.entity.repository;

import java.util.List;

import jp.co.slcs.insurance_simulation.backend.domain.entity.DocumentRequest;

public interface DocumentRequestRepository {

  // TODO 現状不要なメソッドをコメントアウト。後にリファクタリング実施
  void add(DocumentRequest documentRequest);

  List<DocumentRequest> getAll();

  // void update(DocumentRequest documentRequest);

  // void remove(Long receiptNo);

}
