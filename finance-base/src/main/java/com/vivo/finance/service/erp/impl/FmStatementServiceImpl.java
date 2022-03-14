package com.vivo.finance.service.erp.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vivo.finance.dto.query.erp.FMStatementQueryDto;
import com.vivo.finance.entity.erp.BankintgStatement;
import com.vivo.finance.entity.erp.FmStatement;
import com.vivo.finance.entity.erp.FmStatementRule;
import com.vivo.finance.mapper.erp.BankintgStatementMapper;
import com.vivo.finance.mapper.erp.FmStatementMapper;
import com.vivo.finance.mapper.erp.FmStatementRuleMapper;
import com.vivo.finance.service.erp.FmStatementService;
import com.vivo.finance.utils.DateUtils;
import com.vivo.finance.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlType;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FmStatementServiceImpl extends ServiceImpl<FmStatementMapper, FmStatement> implements FmStatementService {
    @Autowired
    private FmStatementMapper fmStatementMapper;

    @Autowired
    private FmStatementRuleMapper fmStatementRuleMapper;

    @Autowired
    private BankintgStatementMapper bankintgStatementMapper;

    private final String[] DEFAULT_GROUP_CLOWNS = {"bank_account_id", "currency_code", "trunc(trx_date)"};
    private final String[] DEFAULT_SHOW_CLOWNS = {"bank_account_id", "currency_code", "trunc(trx_date) as trxDate",
            "count(AMOUNT_EXPENSE) as COUNT_AMOUNT_EXPENSE", "count(AMOUNT_INCOME) as COUNT_AMOUNT_INCOME",
            "count(1) as COUNT_AMOUNT", "sum(AMOUNT_EXPENSE) as SUM_AMOUNT_EXPENSE",
            "sum(AMOUNT_INCOME) as SUM_AMOUNT_INCOME", "sum(AMOUNT) as SUM_AMOUNT"};

    private final String DATA_START_TIME = "2021-01-01";


    /**
     * @param queryDto
     * @Description: 获取资金明细的分组信息
     * @author zyk
     * @date 2022/3/7 18:55
     */
    @Override
    public List<FmStatement> getStatementGroup(FMStatementQueryDto queryDto) {
        QueryWrapper<FmStatement> queryWrapper = new QueryWrapper<>();
        String[] selectShow = {"bank_account_id", "attribute1", "trx_date", "currency_code", "sum(sum_amount_expense) as sum_amount_expense", "sum(sum_amount_income) as  sum_amount_income"};
        queryWrapper.select(selectShow);
        queryWrapper.eq("rule_code", queryDto.getRuleCode());
        queryWrapper.ge("trx_date", queryDto.getStartDate());
        queryWrapper.groupBy("bank_account_id", "attribute1", "trx_date", "currency_code");
        queryWrapper.orderByDesc("bank_account_id");
        queryWrapper.orderByDesc("trx_date");
        List<FmStatement> fmStatements = fmStatementMapper.selectList(queryWrapper);
        return fmStatements;
    }

    /**
     * @Description: 根据规则生成交易明细
     * @author zyk
     * @date 2022/3/2 18:19
     */
    @Override
    public void saveByRule() throws NoSuchAlgorithmException {
        List<FmStatementRule> rules = fmStatementRuleMapper.selectList(null);
        for (FmStatementRule rule : rules) {
            QueryWrapper<BankintgStatement> queryWrapper = new QueryWrapper();

            // 获取数据时间
            if (null == rule.getRuleTimeInterval()) {
                queryWrapper.ge("trx_date", DateUtils.strToDate(DATA_START_TIME));
            } else {
                queryWrapper.ge("trx_date", DateUtils.getStartTime(DateUtils.rollMon(new Date(), -rule.getRuleTimeInterval())));
            }

            // 拼接显示字段
            if (StringUtils.isEmpty(rule.getStrShow())) {
                queryWrapper.select(DEFAULT_SHOW_CLOWNS);
            } else {
                queryWrapper.select(rule.getStrShow().split(","));
            }

            // 拼接查询条件
            if (StringUtils.isEmpty(rule.getStrWhere()))
                continue;
            queryWrapper.apply(rule.getStrWhere());

            // 拼接分组字段
            if (StringUtils.isEmpty(rule.getStrGroup())) {
                queryWrapper.groupBy(DEFAULT_GROUP_CLOWNS);
            } else {
                queryWrapper.groupBy(rule.getStrGroup());
            }

            List<BankintgStatement> bankintgStatements = bankintgStatementMapper.selectList(queryWrapper);
            List<FmStatement> fmStatements = JSONObject.parseArray(JSONObject.toJSONString(bankintgStatements), FmStatement.class);
            for (FmStatement fmStatement : fmStatements) {
                fmStatement.setUpdateTime(new Date());
                fmStatement.setRuleCode(rule.getRuleCode());
                fmStatement.setRuleId(rule.getId());
                fmStatement.setId(getMd5Str(fmStatement));
            }
            saveOrUpdateBatch(fmStatements);

        }
    }

    private String getMd5Str(FmStatement fmStatement) throws NoSuchAlgorithmException {
        StringBuffer signStr = new StringBuffer();
        signStr.append("bankAccountId=" + fmStatement.getBankAccountId());
        signStr.append("trx_date=" + DateUtils.dateToStr(fmStatement.getTrxDate()));
        signStr.append("attribute1=" + fmStatement.getAttribute1());
        signStr.append("ruleId=" + fmStatement.getRuleId());
        signStr.append("currencyCode=" + fmStatement.getCurrencyCode());
        log.info("加密前值：{}", signStr.toString());
        String result = MD5Utils.getMd5String(signStr.toString());
        log.info("加密后值：{}", result);
        return result;
    }

}
