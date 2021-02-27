package jp.co.slcs.insurance_simulation.backend.domain.vo;

import java.time.LocalDate;
import java.time.Period;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * 生年月日の値オブジェクト
 * 
 * @author Inadome Takayuki
 * @version 1.0.1: 2020.12.20
 */
@Getter
@EqualsAndHashCode
@ToString
public class Birthday {

  private static final LocalDate minDate = LocalDate.of(1900, 1, 1);
  private static final LocalDate maxDate = LocalDate.now();

  @NonNull
  private final LocalDate birthday;

  public Birthday(@NonNull LocalDate birthday) {
    if (birthday.isBefore(minDate) || birthday.isAfter(maxDate)) {
      throw new IllegalArgumentException(
          "生年月日の値が不正です。" + minDate + "から" + maxDate + "の範囲内で入力してください。");
    }

    this.birthday = birthday;
  }

  public Age calculateAge(LocalDate localDate) {
    int age = Period.between(birthday, localDate).getYears();
    return new Age(age);
  }

}
