package jp.co.slcs.insurancepremiumsimulation.backend.domain.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class BirthdayTest {

    @Test
    void 引数がNullの時はエラー() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    void 日付が1900年１月１日よりも前の時はエラー() {
        assertThrows(IllegalArgumentException.class, () -> new Birthday(LocalDate.of(1899, 12, 31)));
    }

    @Test
    void 日付が未来の時はエラー() {
        assertThrows(IllegalArgumentException.class, () -> new Birthday(LocalDate.now().plusDays(1)));
    }

    @Test
    void 誕生日が同じ場合はイコールとなる() {
        assertAll(() -> {
            Birthday birthday1 = new Birthday(LocalDate.of(1985, 10, 15));
            Birthday birthday2 = new Birthday(LocalDate.of(1985, 10, 15));
            assertEquals(birthday1, birthday2);
        }, () -> {
            Birthday birthday1 = new Birthday(LocalDate.of(1985, 10, 15));
            Birthday birthday2 = new Birthday(birthday1.getBirthday());
            assertEquals(birthday1, birthday2);
        });
    }

    @Test
    void 誕生日が異なる場合はノットイコールとなる() {
        Birthday birthday1 = new Birthday(LocalDate.of(1977, 6, 16));
        Birthday birthday2 = new Birthday(LocalDate.of(1985, 10, 15));
        assertNotEquals(birthday1, birthday2);
    }

    @Test
    void 年齢計算が想定通り計算できる() {
        assertAll(() -> {
            Birthday birthday = new Birthday(LocalDate.of(2000, 10, 15));
            LocalDate localDate = LocalDate.now();
            Age age = birthday.calculateAge(localDate);
            assertEquals(20, age.getAge());
        }, () -> {
            Birthday birthday = new Birthday(LocalDate.of(1985, 10, 15));
            LocalDate localDate = LocalDate.now();
            Age age = birthday.calculateAge(localDate);
            assertEquals(35, age.getAge());
        });
    }

}
