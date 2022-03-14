package com.vivo.finance.dto.query.dict;

import com.vivo.finance.aspect.annotation.FMQuery;
import lombok.Data;

/**
 * @author zyk
 * @Description 数据字典查询类
 * @date 2022年01月20日
 */
@Data
public class DataLogQueryDto {

    // 表名
    @FMQuery(blurry = "dataTable" )
    private String dataTable;

    // 数据ID
    @FMQuery
    private String dataId;
}
