package jp.co.slcs.insurance_simulation.backend.spring.presentation.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jp.co.slcs.insurance_simulation.backend.usecase.AddDocumentRequestUsecase.Simulation;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddDocumentRequestReqDto {
	@NonNull
	private String name;
	@NonNull
	private String nameKana;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@NonNull
	private LocalDate birthday;
	@NonNull
	private String sex;
	@NonNull
	private String zipCode;
	@NonNull
	private String address;
	@NonNull
	private String telNo;
	@NonNull
	private String mailAddress;
	@NonNull
	private List<Simulation> simulationList;
	@NonNull
	private String sumInsurancePremium;

}
