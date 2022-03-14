package com.vivo.finance.service.erp.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vivo.finance.dto.query.erp.ERPBasicQueryDto;
import com.vivo.finance.dto.query.fundmonitor.FundMonitorQuery;
import com.vivo.finance.dto.response.Result;
import com.vivo.finance.dto.response.fundmonitor.FundMonitorResponse;
import com.vivo.finance.dto.response.fundmonitor.SpliceTaleData;
import com.vivo.finance.entity.erp.BankintgStatement;
import com.vivo.finance.entity.erp.CuxBankAcctBalances;
import com.vivo.finance.mapper.erp.BankintgStatementMapper;
import com.vivo.finance.mapper.erp.CuxBankAcctBalancesMapper;
import com.vivo.finance.service.erp.BankintgStatementService;
import com.vivo.finance.service.erp.CuxBankAcctBalancesService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CuxNamkAcctBalancesServiceImpl extends ServiceImpl<CuxBankAcctBalancesMapper, CuxBankAcctBalances> implements CuxBankAcctBalancesService {

    @Autowired
    private CuxBankAcctBalancesMapper cuxBankAcctBalancesMapper;

    /*
     * @Description: 根据查询条件获取ERP12 交易明细表的 分组统计信息
     * @author zyk
     * @date 2022/3/8 9:33
     * @param queryDto
     */
    public List<CuxBankAcctBalances> getGroupByContion(ERPBasicQueryDto queryDto) {
        QueryWrapper<CuxBankAcctBalances> queryWrapper = new QueryWrapper();
        queryWrapper.ge("balance_date", queryDto.getStartDate());
        if (StringUtils.isNotEmpty(queryDto.getCurrencyCode())){
            queryWrapper.eq("currency_code", queryDto.getCurrencyCode());
        }
        queryWrapper.orderByAsc("bank_account_id","currency_code","balance_date");
        List<CuxBankAcctBalances> bankAcctBalanceList = cuxBankAcctBalancesMapper.selectList(queryWrapper);
        return bankAcctBalanceList;
    }

    @Override
    public SpliceTaleData getFundBalance(FundMonitorQuery query) {
        List<FundMonitorResponse> fundBalance = cuxBankAcctBalancesMapper.getFundBalance(query);
        List<FundMonitorResponse> bankNameSum = cuxBankAcctBalancesMapper.getFundBalanceSumByBankName(query);
        List<String> bankNameList = bankNameSum.stream().map(e -> e.getBankName()).distinct().collect(Collectors.toList());

        List<String> acctNameList = fundBalance.stream().map(e -> e.getBankAccountName()).distinct().sorted().collect(Collectors.toList());
        acctNameList.add("合计");
        /*将币种合计数 添加进去*/
        fundBalance.addAll(bankNameSum);
        Map<String, BigDecimal> balanceMap = fundBalance.stream().collect(Collectors.toMap(e -> e.getBankName() + "_" + e.getBankAccountName(), v -> v.getBalance()));

        List<List<Object>>  resultList = new ArrayList<>();

        for (String acctName : acctNameList) {
            List<Object>  list = new ArrayList<>();
            list.add(acctName);
            for (String bankName : bankNameList) {
                BigDecimal balance = balanceMap.get(bankName + "_" + acctName);
                list.add(balance);
            }
            resultList.add(list);
        }
        bankNameList.add(0,"公司/银行");

        SpliceTaleData taleData = new SpliceTaleData();
        taleData.setColumnList(bankNameList);
        taleData.setDataList(resultList);
        return taleData;
    }

    @Override
    public SpliceTaleData getFundBalanceDiary(FundMonitorQuery query) {
        List<FundMonitorResponse>  balanceDiary = cuxBankAcctBalancesMapper.getFundBalanceDiary(query);
        List<FundMonitorResponse> balanceSum = cuxBankAcctBalancesMapper.getFundBalanceDiarySumByCurrencyCode(query);

        //获取公司名称信息
        List<String> acctNameList = balanceDiary.stream().map(e -> e.getBankAccountName()).distinct().sorted().collect(Collectors.toList());
        acctNameList.add("合计");

        balanceDiary.addAll(balanceSum);
        Map<String, BigDecimal> balanceMap = balanceDiary.stream().collect(Collectors.toMap(e -> e.getBankAccountName() + "_" + e.getCurrencyCode(), v -> v.getBalance()));
        List<List<Object>>  resultList = new ArrayList<>();

        // 币种集合
        List<String> currencyCodeList = cuxBankAcctBalancesMapper.getFundBalanceDiaryCurrencyCode(query);

        /*拼接返回界面table信息*/
        for (String acctName : acctNameList) {
            List<Object>  list = new ArrayList<>();
            if(acctName.equals("东莞市金仕达贸易有限公司")){
                System.out.println("12");
            }
            list.add(acctName);
            for (String currencyCode : currencyCodeList) {
                BigDecimal balance = balanceMap.get( acctName + "_" + currencyCode);
                list.add(balance);
            }
            resultList.add(list);
        }
        currencyCodeList.add(0,"公司/币种");

        SpliceTaleData taleData = new SpliceTaleData();
        taleData.setColumnList(currencyCodeList);
        taleData.setDataList(resultList);
        return taleData;
    }
}
