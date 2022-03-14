package com.vivo.finance.service.erp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vivo.finance.entity.erp.FmStatementRule;
import com.vivo.finance.mapper.erp.FmStatementRuleMapper;
import com.vivo.finance.service.erp.FmStatementRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FmStatementRuleServiceImpl extends ServiceImpl<FmStatementRuleMapper, FmStatementRule> implements FmStatementRuleService {
    @Autowired
    private FmStatementRuleMapper fmStatementRuleMapper;


}
