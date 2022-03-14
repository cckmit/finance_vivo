package com.vivo.finance.dto.query.erp;

import com.vivo.finance.utils.DateUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author zyk
 * @Description 开始时间默认获取3个月之前的日期， 结束时间默认为当前日期
 * @date 2022年03月07日
 */
@Data
public class ERPBasicQueryDto {
    /*开始时间字符串*/
    private String startDateStr;

    /*结束时间字符串*/
    private String endDateStr;

    /*开始时间*/
    private Date startDate;

    /*结束时间*/
    private Date endDate;

    /*币种信息*/
    private String currencyCode;

    public ERPBasicQueryDto() {
        this.endDate =  DateUtils.getStartTime(new Date());
        this.endDateStr =  DateUtils.dateToStr(this.endDate);
        //Date date = DateUtils.rollMon(this.endDate, -3);
        Date date = DateUtils.strToDate("2021-01-01");
        /*开始时间默认为 3个月前时间的1号*/
        this.startDate = DateUtils.getMonthOneDay(date);
        this.startDateStr =  DateUtils.dateToStr(this.startDate);
    }
}
