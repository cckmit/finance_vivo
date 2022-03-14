package com.vivo.finance.dto.response.fundmonitor;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zyk
 * @Description
 * @date 2022年03月08日
 */
@Data
public class FundMonitorResponse {


    /*公司名称*/
    private String bankAccountName;
    /*银行名称*/
    private String bankName;
    /*余额*/
    private BigDecimal balance;
    /*币种*/
    private String currencyCode;


}
