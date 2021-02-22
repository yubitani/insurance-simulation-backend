package jp.co.slcs.insurance_simulation.backend.spring.mybatis.entity;

import java.util.List;

import lombok.Data;

@Data
public class ProductOption{

    String productcode;
    List<OptionChoice> optionlist;

}
