package com.vivo.finance.dto.query.dict;

import com.vivo.finance.aspect.annotation.FMQuery;
import lombok.Data;

/**
 * @author zyk
 * @Description 数据字典查询类
 * @date 2022年01月20日
 */
@Data
public class DictQueryDto {

    // 字典名称 模糊查询
    @FMQuery(blurry = "dictName" )
    private String dictName;

    // 字典编号 全等于查询
    @FMQuery
    private String dictCode;




}
