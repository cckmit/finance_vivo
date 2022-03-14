package com.vivo.finance.dto.response.erp;

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
public class FmAcctFlowMonth implements Serializable {

    /** 版本号 */
    private static final long serialVersionUID = 4500669371374594829L;

    /** 账号 */
    private String bankAccountName ;

    /** 期初 */
    private BigDecimal beginningBanlance;

    /** 期末 */
    private BigDecimal endingBanlance;


    /** 总收入 */
    private BigDecimal dayIncome;

    /** 总支出 */
    private BigDecimal monthIncome;

    /** 总收入 笔数 */
    private BigDecimal dayExpense;

    /** 总支出 笔数*/
    private BigDecimal monthExpense;

}