package com.vivo.finance.service.dict;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vivo.finance.dto.query.dict.DictItemQueryDto;
import com.vivo.finance.dto.query.dict.DictQueryDto;
import com.vivo.finance.entity.dict.SysDict;
import com.vivo.finance.entity.dict.SysDictItem;

import java.util.List;

/**
 * @author zyk
 * @date 2022年01月20日
 */
public interface SysDictItemService extends IService<SysDictItem> {

    /**
     * @Description: 根据查询条件获取字典明细的分页集合
     * @author zyk
     * @date 2022/2/16 9:05
     * @param queryDto 查询条件实体类
     * @param queryDto 分页对象
     */
    Page<SysDictItem> getPageByCondition(Page<SysDictItem> page, DictItemQueryDto queryDto);

    /**
     * @Description: 根据查询条件获取字典明细的集合
     * @author zyk
     * @date 2022/1/21 9:05
     * @param queryDto 查询条件实体类
     * @param queryDto 分页对象
     */
    List<SysDictItem> getListByCondition(DictItemQueryDto queryDto);

}
