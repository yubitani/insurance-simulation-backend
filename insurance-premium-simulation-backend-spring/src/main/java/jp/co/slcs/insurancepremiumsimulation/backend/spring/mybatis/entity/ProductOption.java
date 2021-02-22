package jp.co.slcs.insurancepremiumsimulation.backend.spring.mybatis.entity;

import java.util.List;

import lombok.Data;

@Data
public class ProductOption{

    String productcode;
    List<OptionChoice> optionlist;
    
}
