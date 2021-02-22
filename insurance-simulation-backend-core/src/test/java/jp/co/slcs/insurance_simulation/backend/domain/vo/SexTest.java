package jp.co.slcs.insurance_simulation.backend.domain.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SexTest {

	@Test
	void 引数がNullの時はエラー() {
		assertThrows(NullPointerException.class, () -> new Sex(null));
	}

	@Test
	void 男性１女性２以外の時はエラー() {
		assertThrows(IllegalArgumentException.class, () -> new Sex("0"));
	}

	@Test
	void 性別が同じ場合はイコールとなる() {
		assertAll(() -> {
			Sex sex1 = new Sex("1");
			Sex sex2 = new Sex("1");
			assertEquals(sex1, sex2);
		}, () -> {
			Sex sex1 = new Sex("2");
			Sex sex2 = new Sex("2");
			assertEquals(sex1, sex2);
		});
	}

	@Test
	void 性別が異なる場合はノットイコールとなる() {
		Sex sex1 = new Sex("1");
		Sex sex2 = new Sex("2");
		assertNotEquals(sex1, sex2);
	}

}
