package com.vivo.finance.service.erp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vivo.finance.dto.query.erp.FMStatementQueryDto;
import com.vivo.finance.dto.query.fundmonitor.FundMonitorQuery;
import com.vivo.finance.dto.response.erp.FmAcctFlowMonth;
import com.vivo.finance.entity.erp.CuxBankAcctBalances;
import com.vivo.finance.entity.erp.FmAcctFlowDay;
import com.vivo.finance.entity.erp.FmStatement;
import com.vivo.finance.mapper.erp.FmAcctFlowDayMapper;
import com.vivo.finance.service.erp.CuxBankAcctBalancesService;
import com.vivo.finance.service.erp.FmAcctFlowDayService;
import com.vivo.finance.service.erp.FmStatementService;
import com.vivo.finance.utils.BigDecimalUtils;
import com.vivo.finance.utils.DateUtils;
import com.vivo.finance.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FmAcctFlowDayServiceImpl extends ServiceImpl<FmAcctFlowDayMapper, FmAcctFlowDay> implements FmAcctFlowDayService {

    @Autowired
    private FmAcctFlowDayMapper acctFlowDayMapper;

    @Autowired
    private FmStatementService statementService;

    @Autowired
    private CuxBankAcctBalancesService acctBalancesService;


    @Override
    public List<FmAcctFlowMonth> getFlowMoth(FundMonitorQuery queryDto) {
        return acctFlowDayMapper.getFlowMoth(queryDto);
    }

    @Override
    public List<FmAcctFlowMonth> getFlowDay(FundMonitorQuery queryDto) {
        return acctFlowDayMapper.getFlowDay(queryDto);
    }

    @Override
    public List<FmAcctFlowMonth> getClassFlowDay(FundMonitorQuery queryDto) {
        return acctFlowDayMapper.getClassFlowDay(queryDto);
    }

    @Override
    public void saveBalance() throws NoSuchAlgorithmException {
        saveTye();
    }

    private void saveDeatil() throws NoSuchAlgorithmException {
        FMStatementQueryDto queryDto = new FMStatementQueryDto();
        List<CuxBankAcctBalances> acctBalances = acctBalancesService.getGroupByContion(queryDto);
        queryDto.setRuleCode("CASH_FLOW_NOWDATE");
        List<FmStatement> statementGroup = statementService.getStatementGroup(queryDto);
        /*获取扩展信息*/
        Map<String, FmStatement> collect = statementGroup.stream().collect(Collectors.toMap(e -> e.getBankAccountId() + "_" + e.getCurrencyCode() + "_" + DateUtils.dateToStr(e.getTrxDate()), v -> v));
        BigDecimal monthIncome = BigDecimal.ZERO;
        BigDecimal monthExpense = BigDecimal.ZERO;
        Set<String> keyList = new HashSet<>();

        List<FmAcctFlowDay> acctFlowDays = new ArrayList<>();
        for (CuxBankAcctBalances acctBalance : acctBalances) {
            Long bankAccountId = acctBalance.getBankAccountId();
            String currencyCode = acctBalance.getCurrencyCode();
            Date balanceDate = acctBalance.getBalanceDate();
            String key = bankAccountId + "_" + currencyCode + "_" + DateUtils.dateToStr(balanceDate);

            FmStatement fmStatement = collect.get(key);
            if (fmStatement == null) {
                fmStatement = new FmStatement();
            }

            FmAcctFlowDay fmAcctFlowDay = new FmAcctFlowDay();
            fmAcctFlowDay.setBalanceDate(balanceDate);
            fmAcctFlowDay.setBankAccountId(bankAccountId);
            fmAcctFlowDay.setCurrencyCode(currencyCode);
            fmAcctFlowDay.setType("1");
            fmAcctFlowDay.setId(getMd5Str(fmAcctFlowDay));
            /*当天收款*/
            BigDecimal dayIncome = BigDecimalUtils.add(acctBalance.getDebitBalance(), fmStatement.getSumAmountIncome());
            fmAcctFlowDay.setDayIncome(dayIncome);
            /*当天付款*/
            BigDecimal dayExpense = BigDecimalUtils.add(acctBalance.getCreditBalance(), fmStatement.getSumAmountExpense());
            fmAcctFlowDay.setDayExpense(dayExpense);
            //
            if (DateUtils.isMonthOnDay(balanceDate) || !keyList.contains(bankAccountId + "_" + currencyCode)) {
                monthIncome = BigDecimal.ZERO;
                monthExpense = BigDecimal.ZERO;
            } else {
                monthIncome = BigDecimalUtils.add(monthIncome, dayIncome);
                monthExpense = BigDecimalUtils.add(monthExpense, dayExpense);
            }
            fmAcctFlowDay.setMonthExpense(monthExpense);
            fmAcctFlowDay.setMonthIncome(monthIncome);
            fmAcctFlowDay.setUpdateTime(new Date());
            acctFlowDays.add(fmAcctFlowDay);
            keyList.add(bankAccountId + "_" + currencyCode);
        }
        saveOrUpdateBatch(acctFlowDays,5000);
    }


    private void saveTye() throws NoSuchAlgorithmException {
        FMStatementQueryDto queryDto = new FMStatementQueryDto();
        queryDto.setRuleCode("CASH_FLOW_CLASS");
        List<FmStatement> statementGroup = statementService.getStatementGroup(queryDto);

        BigDecimal monthIncome = BigDecimal.ZERO;
        BigDecimal monthExpense = BigDecimal.ZERO;
        Set<String> keyList = new HashSet<>();
        List<FmAcctFlowDay> acctFlowDays = new ArrayList<>();

        for (FmStatement fmStatement : statementGroup) {
            String attribute1 = fmStatement.getAttribute1();
            Date trxDate = fmStatement.getTrxDate();
            String currencyCode = fmStatement.getCurrencyCode();
            Long bankAccountId = fmStatement.getBankAccountId();

            FmAcctFlowDay fmAcctFlowDay = new FmAcctFlowDay();
            fmAcctFlowDay.setBalanceDate(trxDate);
            fmAcctFlowDay.setBankAccountId(bankAccountId);
            fmAcctFlowDay.setCurrencyCode(currencyCode);
            fmAcctFlowDay.setAttribute1(attribute1);
            fmAcctFlowDay.setType("2");
            fmAcctFlowDay.setId(getMd5Str(fmAcctFlowDay));
            /*当天收款*/
            BigDecimal dayIncome = fmStatement.getSumAmountIncome();
            fmAcctFlowDay.setDayIncome(dayIncome);
            /*当天付款*/
            BigDecimal dayExpense = fmStatement.getSumAmountExpense();
            fmAcctFlowDay.setDayExpense(dayExpense);

            if (DateUtils.isMonthOnDay(trxDate) || !keyList.contains(bankAccountId + "_" + currencyCode + "_" + attribute1)) {
                monthIncome = BigDecimal.ZERO;
                monthExpense = BigDecimal.ZERO;
            } else {
                monthIncome = BigDecimalUtils.add(monthIncome, dayIncome);
                monthExpense = BigDecimalUtils.add(monthExpense, dayExpense);
            }

            fmAcctFlowDay.setMonthExpense(monthExpense);
            fmAcctFlowDay.setMonthIncome(monthIncome);
            fmAcctFlowDay.setUpdateTime(new Date());
            acctFlowDays.add(fmAcctFlowDay);
            keyList.add(bankAccountId + "_" + currencyCode + "_" + attribute1);
        }
        saveOrUpdateBatch(acctFlowDays);
    }


    /**
     * 根据对象生成MD加密ID
     *
     * @param fmAcctFlowBalance
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String getMd5Str(FmAcctFlowDay fmAcctFlowBalance) throws NoSuchAlgorithmException {

        StringBuffer signStr = new StringBuffer();
        signStr.append("bankAccountId=" + fmAcctFlowBalance.getBankAccountId());
        signStr.append("balanceDate=" + DateUtils.dateToStr(fmAcctFlowBalance.getBalanceDate()));
        signStr.append("currencyCode=" + fmAcctFlowBalance.getCurrencyCode());
        signStr.append("type=" + fmAcctFlowBalance.getType());
        signStr.append("currencyCode=" + fmAcctFlowBalance.getCurrencyCode());
        signStr.append("attribute1=" + fmAcctFlowBalance.getAttribute1());
        log.info("加密前值：{}", signStr.toString());
        String result = MD5Utils.getMd5String(signStr.toString());
        log.info("加密后值：{}", result);
        return result;

    }

}
