package com.vivo.finance.service.erp;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vivo.finance.dto.query.erp.FMStatementQueryDto;
import com.vivo.finance.entity.erp.BankintgStatement;
import com.vivo.finance.entity.erp.FmStatement;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 资金系统 交易统计 Service
 * @author zyk
 * @date 2022年03月2日
 */
public interface FmStatementService extends IService<FmStatement> {


     void saveByRule() throws NoSuchAlgorithmException;
     /**
      * @Description: 获取资金明细的分组信息
      * @author zyk
      * @date 2022/3/7 18:55
      * @param queryDto
      */
     List<FmStatement> getStatementGroup(FMStatementQueryDto queryDto);
}
