package jp.co.slcs.insurance_simulation.backend.spring.presentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.slcs.insurance_simulation.backend.usecase.GetDocumentRequestsUsecase;
import jp.co.slcs.insurance_simulation.backend.usecase.GetDocumentRequestsUsecase.DocumentRequestList;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * 資料請求内容を一覧表示用に取得するAPI
 * 
 * @author Otani Kento
 * @version 1.0.0:2020.12.10
 */

@AllArgsConstructor
@RestController
public class GetDocumentRequestsApi {

  /**
   * 資料請求内容のリストを取得する業務ロジック
   */
  @NonNull
  private final GetDocumentRequestsUsecase usecase;

  /**
   * 業務ロジックで取得した資料請求内容（POJO）のリストをJSONでクライアントに返す。
   * 
   * @return List<DocumentRequestList>
   */
  @NonNull
  @GetMapping(path = "/document-request", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<DocumentRequestList> getDocumentRequests() {
    return usecase.invoke();
  }
}
