package jp.co.slcs.insurancepremiumsimulation.backend.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;


/**
 * 年齢の値オブジェクト
 * @author Inadome Takayuki
 * @version 1.0.1: 2020.12.20
 */
@Getter
@EqualsAndHashCode
public class Age {

	private static final int max = 130;
	private static final int min = 0;

	private final int age;

	public Age(int age) {

		if (age < min || max < age) {
			throw new IllegalArgumentException("年齢の値が不正です。"+min+"から"+max+"の範囲内で入力してください。");
		}

		this.age = age;
	}

}
