package com.vivo.finance.entity.erp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*(FM_CUX_BANK_ACCT_BALANCES)
*@auther zhangyukui  银行账户余额表
*@version 1.0 2022-03-07
*/
@Data
@TableName(value = "scf.fm_cux_bank_acct_balances")
public class CuxBankAcctBalances implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5221767252225509387L;

    /** 账号ID */
    @TableField
    private Long bankAccountId;

    /** 币种 */
    private String currencyCode;

    /** 余额时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date balanceDate;

    /** 期初 */
    private BigDecimal beginningBalance;

    /** 期末 */
    private BigDecimal debitBalance;

    /** creditBalance */
    private BigDecimal creditBalance;

    /** endingBalance */
    private BigDecimal endingBalance;

    /** debitLineCount */
    private BigDecimal debitLineCount;

    /** creditLineCount */
    private BigDecimal creditLineCount;

    /** bankAccountName */
    private String bankAccountName;

    /** bankAccountNum */
    private String bankAccountNum;

    /** bankName */
    private String bankName;

    /** bankAccountNameAlt */
    private String bankAccountNameAlt;

    /** requestId */
    private Integer requestId;

    /** status */
    private String status;

    /** sourceType */
    private String sourceType;

    /** messageText */
    private String messageText;

    /** createdBy */
    private Integer createdBy;

    /** creationDate */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date creationDate;

    /** lastUpdatedBy */
    private Integer lastUpdatedBy;

    /** lastUpdateLogin */
    private Integer lastUpdateLogin;

    /** lastUpdateDate */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdateDate;

}