package com.vivo.finance.mapper.erp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vivo.finance.dto.query.erp.ERPBasicQueryDto;
import com.vivo.finance.dto.query.fundmonitor.FundMonitorQuery;
import com.vivo.finance.dto.response.erp.FmAcctFlowMonth;
import com.vivo.finance.entity.erp.FmAcctFlowDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zyk 资金流动余额表
 * @date 2022年03月2日
 */
@Mapper
public interface FmAcctFlowDayMapper extends BaseMapper<FmAcctFlowDay> {

    List<FmAcctFlowMonth> getFlowMoth(FundMonitorQuery queryDto);

    List<FmAcctFlowMonth> getFlowDay(FundMonitorQuery queryDto);

    List<FmAcctFlowMonth> getClassFlowDay(FundMonitorQuery queryDto);


}
