package jp.co.slcs.insurancepremiumsimulation.backend.spring;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsurancePremiumSimulationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurancePremiumSimulationBackendApplication.class, args);
	}
	
	@PostConstruct
    public void init(){
      TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
    }

}
