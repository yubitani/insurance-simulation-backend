package jp.co.slcs.insurance_simulation.backend.spring.presentation;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.AddDocumentRequestReqDto;
import jp.co.slcs.insurance_simulation.backend.spring.presentation.model.AddDocumentRequestResDto;
import jp.co.slcs.insurance_simulation.backend.usecase.AddDocumentRequestUsecase;
import jp.co.slcs.insurance_simulation.backend.usecase.AddDocumentRequestUsecase.Simulation;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * @author horikawakeisuke
 * 資料請求を受け付けるAPI
 */
@AllArgsConstructor
@RestController
public class AddDocumentRequestApi {

	@NonNull
	private final AddDocumentRequestUsecase usecase;

	@NonNull
	@PostMapping(path = "/document-request", consumes = MediaType.APPLICATION_JSON_VALUE)
	public AddDocumentRequestResDto getCalcResult(@NonNull @RequestBody AddDocumentRequestReqDto reqDto) {

		List<Simulation> simulationList = reqDto.getSimulationList();

		String usecaseResult = usecase.invoke(reqDto.getName(), reqDto.getNameKana(), reqDto.getBirthday(),
				reqDto.getSex(), reqDto.getZipCode(), reqDto.getAddress(), reqDto.getTelNo(), reqDto.getMailAddress(),
				simulationList, reqDto.getSumInsurancePremium());

		AddDocumentRequestResDto result = AddDocumentRequestResDto.builder().receiptNo(usecaseResult)
				.build();

		return result;
	};

}
