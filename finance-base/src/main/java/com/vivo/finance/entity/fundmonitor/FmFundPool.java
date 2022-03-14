package com.vivo.finance.entity.fundmonitor;/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.8.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vivo.finance.entity.BasicEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
*(FM_FUND_POOL)
*@auther zhangyukui
*@version 1.0 2022-03-13
*/
@TableName(value = "scf.FM_FUND_POOL")
public class FmFundPool extends BasicEntity {
    /** 版本号 */
    private static final long serialVersionUID = -4034662237057486378L;

    /** 主键ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 账号ID */
    private Long bankAccountId;

    /** 余额日期 */
    private Date BalanceDate;

    /**币种*/
    private String currencyCode;

    /** 资金余额 */
    private BigDecimal fundBalance;

    /** 资金池类型 */
    private String poolType;

}