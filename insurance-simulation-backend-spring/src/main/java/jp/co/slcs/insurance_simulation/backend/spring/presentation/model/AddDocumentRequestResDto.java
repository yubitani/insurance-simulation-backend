package jp.co.slcs.insurance_simulation.backend.spring.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class AddDocumentRequestResDto {

  @NonNull
  private final String receiptNo;

}
