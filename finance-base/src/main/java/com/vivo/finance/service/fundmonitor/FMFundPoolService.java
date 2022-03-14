package com.vivo.finance.service.fundmonitor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vivo.finance.dto.query.dict.DictQueryDto;
import com.vivo.finance.dto.query.fundmonitor.FundPoolQuery;
import com.vivo.finance.dto.response.dict.DictModel;
import com.vivo.finance.entity.dict.SysDict;
import com.vivo.finance.entity.fundmonitor.FmFundPool;

import java.util.List;
import java.util.Map;

/**
 * 资金池管理 Service
 * @author zyk
 * @date 2022年01月20日
 */
public interface FMFundPoolService extends IService<FmFundPool> {



    /**
     * @Description: 根据查询条件
     * @author zyk
     * @date 2022/1/21 9:05
     * @param queryDto 查询条件实体类
     * @param queryDto 分页对象
     */
    Page<FmFundPool> getPageByCondition(Page<FmFundPool> page, FundPoolQuery queryDto);


    /**
     * @Description: 根据查询条件获取字典的集合
     * @author zyk
     * @date 2022/1/21 9:05
     * @param queryDto 查询条件实体类
     * @param queryDto 分页对象
     */
    List<FmFundPool> getListByCondition(FundPoolQuery queryDto);



}
