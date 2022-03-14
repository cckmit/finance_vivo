package com.vivo.finance.entity.erp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vivo.finance.entity.BasicEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*(FM_STATEMENT_RULE) 交易明细获取规则
*@auther zhangyukui
*@version 1.0 2022-03-02
*/
@Data
@TableName(value = "scf.fm_statement_rule")
public class FmStatementRule extends BasicEntity {
    /** 版本号 */
    private static final long serialVersionUID = 2359268407842975202L;

    /** 主键ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 规则编码 */
    private String ruleCode;

    /** 规则中文名称 */
    private String ruleName;

    /** 取数间隔(月) */
    private Integer ruleTimeInterval;

    /** sql 条件字段串 */
    private String strWhere;

    /** sql 显示字段串 */
    private String strShow;

    /** 分组字段 串 */
    private String strGroup;

    /** 排序字段 串*/
    private String strOrder;

}