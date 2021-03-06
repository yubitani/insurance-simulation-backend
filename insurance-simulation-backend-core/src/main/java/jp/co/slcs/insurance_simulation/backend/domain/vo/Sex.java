package jp.co.slcs.insurance_simulation.backend.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * 性別の値オブジェクト
 */
@EqualsAndHashCode
public class Sex {

  private static final String man = "1"; // 男性
  private static final String woman = "2"; // 女性

  @NonNull
  private final String sex;

  public Sex(@NonNull String sex) {

    if (!sex.equals(man) && !sex.equals(woman)) {
      throw new IllegalArgumentException("性別の値が不正です。");
    }

    this.sex = sex;
  }

  @Override
  public String toString() {
    return sex;
  }

}
