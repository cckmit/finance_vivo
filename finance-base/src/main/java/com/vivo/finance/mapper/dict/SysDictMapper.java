package com.vivo.finance.mapper.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vivo.finance.aspect.annotation.FMQuery;
import com.vivo.finance.entity.dict.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zyk
 * @date 2022年01月20日
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    /*
     * @Description: 获取已经失效的数据
     * @author zyk
     * @date 2022/1/21 9:37
     */
    @Select("SELECT * FROM FM_DICT WHERE del_flag = '1'")
    List<SysDict> getInvalidList();

}
