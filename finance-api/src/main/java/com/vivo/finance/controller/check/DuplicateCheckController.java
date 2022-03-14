package com.vivo.finance.controller.check;

import com.vivo.finance.controller.BasicController;
import com.vivo.finance.dto.query.check.DuplicateCheckVo;
import com.vivo.finance.dto.response.Result;
import com.vivo.finance.mapper.check.DuplicateCheckMapper;
import com.vivo.finance.utils.sql.SqlInjectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyk
 * @Description 重复校验工具
 * @date 2022年02月14日
 */
@Slf4j
@RestController
@RequestMapping("/duplicate")
public class DuplicateCheckController  extends BasicController {

    @Autowired
    DuplicateCheckMapper duplicateCheckMapper;

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<Object> doDuplicateCheck(DuplicateCheckVo checkVo){
        Long num = null;

        log.info("----duplicate check------："+ checkVo.toString());
        final String[] sqlInjCheck = {checkVo.getTableName(),checkVo.getFieldName()};
        SqlInjectionUtil.filterContent(sqlInjCheck);
        if (StringUtils.isNotBlank(checkVo.getDataId())) {
            num = duplicateCheckMapper.duplicateCheckCountSql(checkVo);
        }else {
            num = duplicateCheckMapper.duplicateCheckCountSqlNoDataId(checkVo);
        }
        if (num == null || num == 0) {
            // 该值可用
            return Result.OK("该值可用！");
        } else {
            // 该值不可用
            log.info("该值不可用，系统中已存在！");
            return Result.error("该值不可用，系统中已存在！");
        }
    }
}
