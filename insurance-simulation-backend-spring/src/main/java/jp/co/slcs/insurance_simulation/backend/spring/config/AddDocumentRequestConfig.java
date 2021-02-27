package jp.co.slcs.insurance_simulation.backend.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.co.slcs.insurance_simulation.backend.domain.entity.repository.DocumentRequestRepository;
import jp.co.slcs.insurance_simulation.backend.usecase.AddDocumentRequestUsecase;

@Configuration
public class AddDocumentRequestConfig {

  @Bean
  public AddDocumentRequestUsecase addDocumentRequestUsecase(
      DocumentRequestRepository documentRequestRepository) {
    return new AddDocumentRequestUsecase(documentRequestRepository);
  }
}
