package com.vivo.finance.dto.query.fundmonitor;

import com.vivo.finance.aspect.annotation.FMQuery;
import lombok.Data;

import java.util.List;

/**
 * @author zyk
 * @Description
 * @date 2022年03月13日
 */
@Data
public class FundPoolQuery {

    /** 币种 */
    @FMQuery
    private String currencyCode;

    /** 公司名称 */
    @FMQuery(blurry = "bankAccountName")
    private String bankAccountName;

    /** 银行账号*/
    @FMQuery
    private String bankAccountNum;

    @FMQuery(type = FMQuery.Type.IN,propName = "bankAccountId")
    private List<Long> bankNumList;

}
