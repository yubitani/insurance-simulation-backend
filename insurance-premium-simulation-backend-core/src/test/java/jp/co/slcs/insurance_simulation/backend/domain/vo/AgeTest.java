package jp.co.slcs.insurance_simulation.backend.domain.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AgeTest {

    @Test
    void 年齢が０歳よりも前の時はエラー() {
        assertThrows(IllegalArgumentException.class, () -> new Age(-1));
    }

    @Test
    void 年齢が１３０歳を超えている時はエラー() {
        assertThrows(IllegalArgumentException.class, () -> new Age(131));
    }

    @Test
    void 年齢が同じ場合はイコールとなる() {
        assertAll(() -> {
            Age age1 = new Age(20);
            Age age2 = new Age(20);
            assertEquals(age1, age2);
        }, () -> {
            Age age1 = new Age(30);
            assertEquals(age1.getAge(), 30);
        });
    }

    @Test
    void 年齢が異なる場合はノットイコールとなる() {
        Age age1 = new Age(20);
        Age age2 = new Age(21);
        assertNotEquals(age1, age2);
    }

}
