package com.vivo.finance.service.erp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.client.AMQP;
import com.vivo.finance.entity.erp.BankintgStatement;
import com.vivo.finance.mapper.erp.BankintgStatementMapper;
import com.vivo.finance.service.erp.BankintgStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankintgStatementServiceImpl extends ServiceImpl<BankintgStatementMapper, BankintgStatement> implements BankintgStatementService {
    @Autowired
    private BankintgStatementMapper bankintgStatementMapper;


}
