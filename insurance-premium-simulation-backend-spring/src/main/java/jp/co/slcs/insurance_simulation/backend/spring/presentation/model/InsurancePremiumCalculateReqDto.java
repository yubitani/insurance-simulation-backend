package jp.co.slcs.insurance_simulation.backend.spring.presentation.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * 保険料計算APIのリクエストDTOクラス
 * @author Inadome Takayuki
 * @version 1.0.0: 2020.11.18
 */
@Getter
@Setter
@ToString
public class InsurancePremiumCalculateReqDto {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@NonNull
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate birthday;

	@NonNull
	private String sex;

	@NonNull
	private String productCode;

	@NonNull
	private String benefitCode;

	@NonNull
	private String periodOfInsuranceCode;

	private List<String> optionList;

}
