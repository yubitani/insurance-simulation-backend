package jp.co.slcs.insurancepremiumsimulation.backend.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.slcs.insurancepremiumsimulation.backend.spring.config.InsurancePremiumSimurationApiConfig;
import jp.co.slcs.insurancepremiumsimulation.backend.spring.presentation.model.InsurancePremiumCalculateReqDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * SpringMVCのテストクラス
 * @author OkamotoYuma
 * @version 1.0.0:2020.12.3
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(InsurancePremiumSimurationApiConfig.class)
class InsurancePremiumSimulationBackendApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	/**
	 * InsuranceProductInfoApiクラスのテスト
	 * ステータスがOKかどうかを判断している
	 * @author OkamotoYma
	 * @version 1.0.0:2020.12.3
	 * @throws Exception
	 */
	@Test
	void testInsuranceProductsInfoApi() throws Exception {
		this.mockMvc.perform(get("/InsuranceProductsInfo")
							 .param("birthday", "1997-02-16")
							 .param("sex", "1")
							)
						.andDo(print())
						.andExpect(status().isOk());
	}

	@Test
	void testInsurancePremiumCaluculateApi() throws Exception {
		
		//Test用リクエストの生成
		InsurancePremiumCalculateReqDto request = new InsurancePremiumCalculateReqDto();
		LocalDate birthday = LocalDate.of(1997, 02, 16);
		String sex = "1";
		String productCode = "DeathInsurance-001";
		String benefitCode = "5million";
		String periodOfInsuranceCode = "10years";
		List<String> optionList = new ArrayList<>();
		optionList.add("ThreeMajorIllnessInsurance");
		optionList.add("LivingNeeds");

		request.setBirthday(birthday);
		request.setSex(sex);
		request.setProductCode(productCode);
		request.setBenefitCode(benefitCode);
		request.setPeriodOfInsuranceCode(periodOfInsuranceCode);
		request.setOptionList(optionList);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);
		
		System.out.println(json);

		//MockMVCでテスト
		mockMvc.perform(post("/premiumcalc")
        	// ContentTypeの設定
        	.contentType(MediaType.APPLICATION_JSON)
        	// Jsonの設定
        	.content(json)
    	).andExpect(status().isOk());
	}
}
