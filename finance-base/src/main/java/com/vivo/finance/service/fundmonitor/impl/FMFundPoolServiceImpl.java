package com.vivo.finance.service.fundmonitor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vivo.finance.dto.query.dict.DictQueryDto;
import com.vivo.finance.dto.query.fundmonitor.FundPoolQuery;
import com.vivo.finance.dto.response.dict.DictModel;
import com.vivo.finance.entity.base.FMDataLog;
import com.vivo.finance.entity.dict.SysDict;
import com.vivo.finance.entity.fundmonitor.FmFundPool;
import com.vivo.finance.mapper.dict.FMDataLogMapper;
import com.vivo.finance.mapper.fundmonitor.FMFundPoolMapper;
import com.vivo.finance.service.dict.FMDataLogService;
import com.vivo.finance.service.fundmonitor.FMFundPoolService;
import com.vivo.finance.utils.wrapper.WrapperQueryHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资金池管理 Service 实现类
 *
 * @author zyk
 * @date 2022年01月20日
 */
@Service
public class FMFundPoolServiceImpl extends ServiceImpl<FMFundPoolMapper, FmFundPool> implements FMFundPoolService {

    @Autowired
    private FMFundPoolMapper fundPoolMapper;

    @Override
    public Page<FmFundPool> getPageByCondition(Page<FmFundPool> page, FundPoolQuery queryDto) {
        QueryWrapper<FmFundPool> wrapper = WrapperQueryHelp.getQueryWrapper(queryDto);
        Page<FmFundPool> pageList = fundPoolMapper.selectPage(page, wrapper);
        return pageList;
    }

    @Override
    public List<FmFundPool> getListByCondition(FundPoolQuery queryDto) {
        QueryWrapper<FmFundPool> wrapper = WrapperQueryHelp.getQueryWrapper(queryDto);
        List<FmFundPool> list = fundPoolMapper.selectList(wrapper);
        return list;
    }


}
