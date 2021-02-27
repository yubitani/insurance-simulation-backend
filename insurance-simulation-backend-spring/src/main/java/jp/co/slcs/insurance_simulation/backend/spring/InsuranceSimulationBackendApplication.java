package jp.co.slcs.insurance_simulation.backend.spring;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsuranceSimulationBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(InsuranceSimulationBackendApplication.class, args);
  }

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
  }

}
