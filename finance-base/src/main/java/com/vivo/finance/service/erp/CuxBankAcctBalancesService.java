package com.vivo.finance.service.erp;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vivo.finance.dto.query.erp.ERPBasicQueryDto;
import com.vivo.finance.dto.query.fundmonitor.FundMonitorQuery;
import com.vivo.finance.dto.response.fundmonitor.FundMonitorResponse;
import com.vivo.finance.dto.response.fundmonitor.SpliceTaleData;
import com.vivo.finance.entity.erp.BankintgStatement;
import com.vivo.finance.entity.erp.CuxBankAcctBalances;

import java.util.List;
import java.util.Map;

/**
 * erp12 银行账户余额表 Service
 * @author zyk
 * @date 2022年03月2日
 */
public interface CuxBankAcctBalancesService extends IService<CuxBankAcctBalances> {

    /*
     * @Description: 根据查询条件获取ERP12 交易明细表的 分组统计信息
     * @author zyk
     * @date 2022/3/8 9:33
     * @param queryDto
     */
    List<CuxBankAcctBalances> getGroupByContion(ERPBasicQueryDto queryDto);

    /**
     * @Description:获取资金余额信息,
     * @author zyk
     * @date 2022/3/9 16:02
     * @param query
     */
    SpliceTaleData getFundBalance(FundMonitorQuery query);

    /**
     * @Description:获取资金余额日记表信息 根据资金余额信息，查询币种
     * @author zyk
     * @date 2022/3/9 16:02
     * @param query
     */
    SpliceTaleData getFundBalanceDiary(FundMonitorQuery query);

}
