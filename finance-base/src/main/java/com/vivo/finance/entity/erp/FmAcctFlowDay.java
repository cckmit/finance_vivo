package com.vivo.finance.entity.erp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*资金流动余额表(FM_ACCT_FLOW_BALANCE)
*@auther zhangyukui
*@version 1.0 2022-03-07
*/
@Data
@TableName(value = "scf.fm_acct_flow_day")
public class FmAcctFlowDay implements Serializable {

    /** 版本号 */
    private static final long serialVersionUID = 4500669371374594829L;

    @TableField
    private String id;

    /** 账号 */
    private Long bankAccountId;

    /** 币种 */
    private String currencyCode;

    /** 余额日期 */
    private Date balanceDate;

    /** 当天收款 */
    private BigDecimal dayIncome;

    /** 当月累积收款 */
    private BigDecimal monthIncome;

    /** 当天付款 */
    private BigDecimal dayExpense;

    /** 当月累积付款 */
    private BigDecimal monthExpense;

    /** 币种 */
    private String type;

    /** class类型 */
    private String attribute1;

    /** 更新时间 */
    private Date updateTime;

}