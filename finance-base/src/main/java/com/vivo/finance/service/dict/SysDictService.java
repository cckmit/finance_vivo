package com.vivo.finance.service.dict;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vivo.finance.dto.query.dict.DictQueryDto;
import com.vivo.finance.dto.response.dict.DictModel;
import com.vivo.finance.entity.dict.SysDict;

import java.util.List;
import java.util.Map;

/**
 * 数据字典 Service
 * @author zyk
 * @date 2022年01月20日
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * @Description: 根据查询条件获取字典的分页集合
     * @author zyk
     * @date 2022/1/21 9:05
     * @param queryDto 查询条件实体类
     * @param queryDto 分页对象
     */
    Page<SysDict> getPageByCondition(Page<SysDict> page,DictQueryDto queryDto);

    /**
     * @Description: 根据查询条件获取字典的集合
     * @author zyk
     * @date 2022/1/21 9:05
     * @param queryDto 查询条件实体类
     * @param queryDto 分页对象
     */
    List<SysDict> getListByCondition(DictQueryDto queryDto);


    /*
     * @Description: 获取已经失效的数据
     * @author zyk
     * @date 2022/1/21 9:37
     */
    List<SysDict> getInvalidList();

    /**
     * @Description: 获取所有得数据字典信息
     * @author zyk
     * @date 2022/2/17 11:11
     */
    Map<String,List<DictModel>> queryAllDictItems();
}
