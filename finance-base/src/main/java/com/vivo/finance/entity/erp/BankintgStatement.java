package com.vivo.finance.entity.erp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zyk
 * @Description erp12 交易流水信息
 * @date 2022年03月01日
 */
@Data
@TableName(value = "scf.fmviews_bankintg_statement")
public class BankintgStatement {
    private Long statementId;
    private Long bankAccountId;
    private Long lineNumber;
    private Date trxDate;
    private String trxType;
    private BigDecimal amountExpense;
    private BigDecimal amountIncome;
    private BigDecimal amount;
    private BigDecimal availableBalance;
    private String status;
    private Date effectiveDate;
    private String bankTrxNumber;
    private String trxText;
    private String customerText;
    private String invoiceText;
    private String bankAccountText;
    private String currencyCode;
    private BigDecimal exchangeRate;
    private Date exchangeRateDate;
    private String ceStatementLines;
    private Date accountingDate;
    private String referenceTxt;
    private String trxCode;
    private String detailNo;
    private Long requestId;
    private String attributeCategory;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private String createdBy;
    private Date creationDate;
    private String  lastUpdatedBy;
    private Date lastUpdateDate;
    private Long lastUpdateLogin;
    private Long docSequenceValue;
    private String postedFlag;
    private String glPostedFlag;
    private Long glDocSequenceValue;
    private Long ledgerId;
    private Long orgId;
    private String checkState;
    private String bankAccountNum;
    private String sequenceNo;

    /** 支出总金额 */
    @TableField(exist = false)
    private BigDecimal sumAmountExpense;

    /** 收入总金额 */
    @TableField(exist = false)
    private BigDecimal sumAmountIncome;

    /** 发生总额 */
    @TableField(exist = false)
    private BigDecimal sumAmount;

    /** 支出笔数 */
    @TableField(exist = false)
    private BigDecimal countAmountExpense;

    /** 收入笔数 */
    @TableField(exist = false)
    private BigDecimal countAmountIncome;

    /** 发生总笔数 */
    @TableField(exist = false)
    private BigDecimal countAmount;


}
