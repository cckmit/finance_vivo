package com.vivo.finance.dto.query.erp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zyk
 * @Description 资金管理系统交易明细统计表查询条件
 * @date 2022年03月07日
 */
@Data
public class FMStatementQueryDto extends ERPBasicQueryDto implements Serializable {

    private String ruleCode;

    public FMStatementQueryDto() {
        super();
    }
}
