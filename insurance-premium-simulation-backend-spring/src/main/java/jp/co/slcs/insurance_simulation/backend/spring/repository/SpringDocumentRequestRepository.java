package jp.co.slcs.insurance_simulation.backend.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.slcs.insurance_simulation.backend.domain.entity.DocumentRequest;
import jp.co.slcs.insurance_simulation.backend.domain.entity.DocumentRequest.DocumentRequestProduct;
import jp.co.slcs.insurance_simulation.backend.domain.entity.repository.DocumentRequestRepository;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.CustomerDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.CustomerDaoEntity;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.DocumentRequestDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.DocumentRequestDaoEntity;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationDaoEntity;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationOptionDao;
import jp.co.slcs.insurance_simulation.backend.spring.repository.dao.SimulationOptionDaoEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * @author horikawakeisuke 資料請求を受け付ける際に使用するRepository
 */
@AllArgsConstructor
@Repository
public class SpringDocumentRequestRepository implements DocumentRequestRepository {

	@NonNull
	private final DocumentRequestDao documentRequestDao;

	@NonNull
	private final CustomerDao customerdao;

	@NonNull
	private final SimulationDao simulationDao;

	@NonNull
	private final SimulationOptionDao simulationOptionDao;

	private CustomerDaoEntity buildCustomerDaoEntity(@NonNull DocumentRequest documentRequest, boolean isNew) {
		return CustomerDaoEntity.builder().name(documentRequest.getName()).kana(documentRequest.getNameKana())
				.birthday(documentRequest.getBirthday().getBirthday()).sex(documentRequest.getSex().toString())
				.zipcode(documentRequest.getZipCode()).address(documentRequest.getAddress())
				.telNo(documentRequest.getTellNo()).mail(documentRequest.getMailAddress()).isNew(isNew).build();
	}

	private DocumentRequestDaoEntity buildDocumentRequestDaoEntity(@NonNull DocumentRequest documentRequest,
			Integer customerId, boolean isNew) {
		return DocumentRequestDaoEntity.builder().receiptNo(documentRequest.getReceiptNo())
				.requestedDateTime(LocalDateTime.now()).customerId(customerId).isNew(isNew).build();
	}

	private SimulationDaoEntity buildSimulationDaoEntity(@NonNull DocumentRequestProduct product, String receiptNo,
			boolean isNew) {
		return SimulationDaoEntity.builder().productCode(product.getProductCode()).benefitCode(product.getBenefitCode())
				.periodOfInsulanceCode(product.getPeriodOfInsuranceCode())
				.insurancePremium(product.getInsurancePremium()).receiptNo(receiptNo).isNew(isNew).build();
	}

	private SimulationOptionDaoEntity buildSimulationOptionDaoEntity(@NonNull String optionCode, Integer simulationId,
			boolean isNew) {
		return SimulationOptionDaoEntity.builder().optionCode(optionCode).simulationId(simulationId).isNew(isNew)
				.build();
	}

	// 資料請求を受付にあたり、各情報をDBへ格納
	@Override
	public void add(@NonNull DocumentRequest documentRequest) {
		// お客様テーブルへ格納
		CustomerDaoEntity customerDaoEntity = buildCustomerDaoEntity(documentRequest, true);
		customerDaoEntity = customerdao.save(customerDaoEntity);
		// 資料請求テーブルへ格納
		DocumentRequestDaoEntity documentRequestDaoEntity = buildDocumentRequestDaoEntity(documentRequest,
				customerDaoEntity.getId(), true);
		documentRequestDao.save(documentRequestDaoEntity);
		// 見積もりテーブルへ格納
		for (DocumentRequestProduct product : documentRequest.getDocumentRequestProductList()) {
			SimulationDaoEntity simulationDaoEntity = buildSimulationDaoEntity(product,
					documentRequestDaoEntity.getReceiptNo(), true);
			simulationDaoEntity = simulationDao.save(simulationDaoEntity);
			// 見積もりオプションテーブルへ格納
			if (product.getOptionList().size() != 0) {
				for (String optionCode : product.getOptionList()) {
					SimulationOptionDaoEntity simulationOptionDaoEntity = buildSimulationOptionDaoEntity(optionCode,
							simulationDaoEntity.getId(), true);
					simulationOptionDao.save(simulationOptionDaoEntity);
				}
			}
		}
	}

	@Override
	public List<DocumentRequest> getAll() {
		return null;
	}

}
