package com.vivo.finance.entity.erp;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*资管~明细统计表(FM_STATEMENT)
*@auther zhangyukui
*@version 1.0 2022-03-02
*/
@Data
@TableName(value = "scf.fm_statement")
public class FmStatement  implements Serializable {

    /** 主键ID（账号ID,交易日期，币种 组合MD5 生成） */
    @TableField
    private String id;

    /** 内部银行账号ID */
    private Long bankAccountId;

    /** 交易日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trxDate;

    /** 币种 */
    private String currencyCode;

    /** 规则编号 */
    private String ruleCode;

    /** 规则编号 */
    private String ruleId;

    /** 交易类型class（出纳标记） */
    private String attribute1;

    /** 支出总金额 */
    private BigDecimal sumAmountExpense;

    /** 收入总金额 */
    private BigDecimal sumAmountIncome;

    /** 发生总额 */
    private BigDecimal sumAmount;

    /** 支出笔数 */
    private BigDecimal countAmountExpense;

    /** 收入笔数 */
    private BigDecimal countAmountIncome;

    /** 发生总笔数 */
    private BigDecimal countAmount;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


}