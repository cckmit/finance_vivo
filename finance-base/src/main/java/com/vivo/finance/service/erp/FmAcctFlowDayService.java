package com.vivo.finance.service.erp;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vivo.finance.dto.query.fundmonitor.FundMonitorQuery;
import com.vivo.finance.dto.response.erp.FmAcctFlowMonth;
import com.vivo.finance.entity.erp.FmAcctFlowDay;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * erp12 交易明细 Service
 * @author zyk
 * @date 2022年03月2日
 */
public interface FmAcctFlowDayService extends IService<FmAcctFlowDay> {


    void saveBalance() throws NoSuchAlgorithmException;

    List<FmAcctFlowMonth> getFlowMoth(FundMonitorQuery queryDto);

    List<FmAcctFlowMonth> getFlowDay(FundMonitorQuery queryDto);

    List<FmAcctFlowMonth> getClassFlowDay(FundMonitorQuery queryDto);

}
