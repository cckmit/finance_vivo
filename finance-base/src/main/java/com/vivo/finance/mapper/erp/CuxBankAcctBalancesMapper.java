package com.vivo.finance.mapper.erp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vivo.finance.dto.query.fundmonitor.FundMonitorQuery;
import com.vivo.finance.dto.response.fundmonitor.FundMonitorResponse;
import com.vivo.finance.entity.erp.BankintgStatement;
import com.vivo.finance.entity.erp.CuxBankAcctBalances;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zyk
 * @date 2022年03月2日
 * @Desceptire: erp 12 银行账户余额表
 */
@Mapper
public interface CuxBankAcctBalancesMapper extends BaseMapper<CuxBankAcctBalances> {


    /**
     * @Description:获取资金余额信息,
     * @author zyk
     * @date 2022/3/9 16:02
     * @param query
     */
    List<FundMonitorResponse> getFundBalance(FundMonitorQuery query);
    /**
     * @Description: 获取资金余额的统计信息，
     * @author zyk
     * @date 2022/3/9 16:59
     * @param query
     */
    List<FundMonitorResponse> getFundBalanceSumByBankName(FundMonitorQuery query);

    /**
     * @Description:获取资金余额日记表信息 根据资金余额信息，查询币种
     * @author zyk
     * @date 2022/3/9 16:02
     * @param query
     */
    List<FundMonitorResponse> getFundBalanceDiary(FundMonitorQuery query);

    List<FundMonitorResponse> getFundBalanceDiarySumByCurrencyCode(FundMonitorQuery query);
    /**
     * @Description: 获取资金余额日记表币种集合
     * @author zyk
     * @date 2022/3/10 10:21
     * @param query
     */
    List<String> getFundBalanceDiaryCurrencyCode(FundMonitorQuery query);

}
