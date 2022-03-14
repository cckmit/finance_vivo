package com.vivo.finance.dto.query.fundmonitor;

import lombok.Data;

/**
 * @author zyk
 * @Description
 * @date 2022年03月08日
 */
@Data
public class FundMonitorQuery {

    private String currencCode;
    /*开始时间*/

    private String startDate;
    /*结束时间*/
    private String endDate;
    /*公司类型：境内 公司名称为中文， 境外公司名称为英文*/
    private String companyType;

}
