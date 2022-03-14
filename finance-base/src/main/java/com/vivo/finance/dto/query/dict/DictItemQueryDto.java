package com.vivo.finance.dto.query.dict;

import com.vivo.finance.aspect.annotation.FMQuery;
import lombok.Data;

/**
 * @author zyk
 * @Description 数据字典查询类
 * @date 2022年01月20日
 */
@Data
public class DictItemQueryDto {

    // 字典明细名称
    @FMQuery(blurry = "itemText" )
    private String itemText;

    // 字典明细值
    @FMQuery
    private String itemValue;

    // 字典ID
    @FMQuery
    private String dictId;

    // 启用状态
    @FMQuery
    private String status;

}
