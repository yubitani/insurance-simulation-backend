package jp.co.slcs.insurancepremiumsimulation.backend.spring.presentation.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * 保険商品情報取得APIにて、JSONに変換してユーザに返すModelクラス。
 * 
 * @author Okamoto Yuma
 * @version 1.0.0:2020.11.18
 */

@Builder
@Getter
public class InsuranceProductInfoResDto {
    
    @NonNull
    private final String productCode; //保険コード

    @NonNull
    private final String name; //保険名

    @NonNull
    private final Benefit benefit; //保険金情報

    @NonNull
    private final List<PeriodOfInsurance> periodOfInsuranceList; //保険期間リスト

    @NonNull
    private final List<Option> optionList; //特約リスト

    @Builder
    @Getter
    public static class Benefit {

        @NonNull
        private final String benefitName;//保険金名

        @NonNull
        private final List<BenefitChoices> benefitConditionList; //保険金の選択肢

    }

    /**
     * 保険期間の選択肢クラス
     * Codeは稲留さんWiki参照
     * 
     * @author Okamoto Yuma
     * @version 1.0.0:2020.11.19
     */
    @Builder
    @Getter
    public static class PeriodOfInsurance {

        @NonNull
        private final String periodOfInsuranceCode;//保険期間コード

        @NonNull
        private final String periodOfInsuranceName;//保険期間名
    }

    /**
     * 保険金の選択肢クラス
     * 保険金の選択肢に対するコードと表示名を保持。
     * 
     * 例）
     * 500万から3000万で500円単位で選択できる場合。
     * 500万,1000万,1500万・・・,3000万が選択肢として与えられる。
     * 
     * @author Okamoto Yuma
     * @version 1.0.0:2020.11.19
     */
    @Builder
    @Getter
    public static class BenefitChoices { 

        @NonNull
        private final String benefitCode; //選択肢の保険金コード

        @NonNull
        private final String benefitChoicesName; //選択肢の表示名
    }

    /**
     * 特約クラス
     * 特約コードと特約名を保持。
     * 
     * DBから特約の情報を取得し、格納するクラス
     * 
     * @author Okamoto Yuma
     * @version 1.0.0:2020.11.19
     */
    @Builder
    @Getter
    public static class Option { 

        @NonNull
        private final String optionCode;// 特約コード

        @NonNull
        private final String name;//特約名
    }
}
