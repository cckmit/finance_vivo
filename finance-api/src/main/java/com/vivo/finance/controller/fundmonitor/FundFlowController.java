package com.vivo.finance.controller.fundmonitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vivo.finance.controller.BasicController;
import com.vivo.finance.dto.query.fundmonitor.FundMonitorQuery;
import com.vivo.finance.dto.response.Result;
import com.vivo.finance.dto.response.erp.FmAcctFlowMonth;
import com.vivo.finance.dto.response.fundmonitor.SpliceTaleData;
import com.vivo.finance.entity.base.FMDataLog;
import com.vivo.finance.service.erp.CuxBankAcctBalancesService;
import com.vivo.finance.service.erp.FmAcctFlowDayService;
import com.vivo.finance.utils.BigDecimalUtils;
import com.vivo.finance.utils.excel.EasyExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zyk
 * @Description
 * @date 2022年03月08日
 */
@RestController
@RequestMapping("fundmonitor/")
@Slf4j
public class FundFlowController extends BasicController {

    @Autowired
    private FmAcctFlowDayService flowDayService;

    @Autowired
    private CuxBankAcctBalancesService cuxBankAcctBalancesService;


    @RequestMapping(value = "/getCNYFlow", method = RequestMethod.GET)
    public Result<List<FMDataLog>> getCNYFlow(FundMonitorQuery query) {
        Result<IPage<FMDataLog>> result = new Result<>();
        List<FmAcctFlowMonth> flowDayList = flowDayService.getFlowDay(query);
        List<FmAcctFlowMonth> flowMothList = flowDayService.getFlowMoth(query);
        Map<String, FmAcctFlowMonth> flowDayMap = flowDayList.stream().collect(Collectors.toMap(k -> k.getBankAccountName(), v -> v));

        for (FmAcctFlowMonth fmAcctFlowMonth : flowMothList) {
            String bankAccountName = fmAcctFlowMonth.getBankAccountName();
            FmAcctFlowMonth flowMonth = flowDayMap.get(bankAccountName);
            fmAcctFlowMonth.setMonthExpense(flowMonth.getMonthExpense());
            fmAcctFlowMonth.setMonthIncome(flowMonth.getMonthIncome());
            fmAcctFlowMonth.setDayExpense(flowMonth.getDayExpense());
            fmAcctFlowMonth.setDayIncome(flowMonth.getDayIncome());
        }

        BigDecimalUtils.collectSum(flowMothList, FmAcctFlowMonth.class, "合计：", "bankAccountName", null);

        return result.OK();
    }

    /**
     * @param query
     * @Description: 查询资金余额表信息
     * @author zyk
     * @date 2022/3/10 9:45
     */
     @RequestMapping(value = "/getFundBalance", method = RequestMethod.GET)
    public Result<SpliceTaleData> getFundBalance(FundMonitorQuery query) {
        SpliceTaleData fundBalance = cuxBankAcctBalancesService.getFundBalance(query);
        return Result.OK(fundBalance);
    }

    /**
     * @param query
     * @Description: 下载资金余额表信息
     * @author zyk
     * @date 2022/3/10 9:16
     */
    @RequestMapping(value = "/cc", method = RequestMethod.GET)
    public Result downFundBalance(FundMonitorQuery query) {
        SpliceTaleData fundBalance = cuxBankAcctBalancesService.getFundBalance(query);
        List<List<String>> lists = fundBalance.buildSimpleHead();
        EasyExcelUtils.writeTableByDataListAndHead(response, fundBalance.getDataList(), lists, "资金余额表下载");
        return Result.OK();
    }

    /**
     * @param query
     * @Description:查询资金余额日记表
     * @author zyk
     * @date 2022/3/10 9:45
     */
    @RequestMapping(value = "/getFundBalanceDiary", method = RequestMethod.GET)
    public Result<SpliceTaleData> getFundBalanceDiary(FundMonitorQuery query) {
        /*获取境内数据*/
        query.setCompanyType("1");
        SpliceTaleData  domesticBalance = cuxBankAcctBalancesService.getFundBalanceDiary(query);

        /*获取境内数据*/
        query.setCompanyType("2");
        SpliceTaleData  overseasBalance = cuxBankAcctBalancesService.getFundBalanceDiary(query);

        /*计算境内外合计*/
        List<Object>  domesticSum= domesticBalance.getDataList().get(domesticBalance.getDataList().size() - 1);
        domesticSum.remove(0);
        domesticSum.add(0,"境内合计:");
        List<Object>  overseasSum= overseasBalance.getDataList().get(overseasBalance.getDataList().size() - 1);
        overseasSum.remove(0);
        overseasSum.add(0,"境外合计:");

        List<Object> sumList = new ArrayList<>();
        for (int i = 1; i < domesticSum.size(); i++) {
            BigDecimal value = BigDecimalUtils.add((BigDecimal) domesticSum.get(i), (BigDecimal) overseasSum.get(i));
            sumList.add(value);
        }
        sumList.add(0,"境内外合计:");

        List<List<Object>> dataList = domesticBalance.getDataList();
        List<Object> splitlist = new ArrayList<>();
        splitlist.add("境外公司");
        dataList.add(splitlist);
        dataList.addAll(overseasBalance.getDataList());
        dataList.add(sumList);
        return Result.OK(domesticBalance);
    }

    /**
     * @param query
     * @Description: 下载资金余额日记表
     * @author zyk
     * @date 2022/3/10 9:16
     */
    @RequestMapping(value = "/downFundBalanceDiary", method = RequestMethod.GET)
    public Result downFundBalanceDiary(FundMonitorQuery query) {
        SpliceTaleData fundBalance = cuxBankAcctBalancesService.getFundBalance(query);
        List<List<String>> lists = fundBalance.buildSimpleHead();
        return Result.OK();
    }


}
