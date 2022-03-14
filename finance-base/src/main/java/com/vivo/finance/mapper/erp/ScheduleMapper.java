package com.vivo.finance.mapper.erp;

import java.util.Map;

/**
 * @author zyk
 * @Description 定时任务 Mapper
 * @date 2022年03月07日
 */
public interface ScheduleMapper {


    /**
     * @Description: 同步erp12 账户余额表
     * @author zyk
     * @date 2022/3/7 14:38
     */
    void synCuxBankAcctBalances();

}
