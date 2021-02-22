package jp.co.slcs.insurancepremiumsimulation.backend.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.NonNull;


/**
 * 性別の値オブジェクト
 * @author Inadome Takayuki
 * @version 1.0.1: 2020.12.20
 */
@EqualsAndHashCode
public class Sex {

	private static final String man = "1"; //男性
	private static final String woman = "2"; //女性

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
