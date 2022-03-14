package com.vivo.finance.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vivo.finance.dto.query.dict.DictItemQueryDto;
import com.vivo.finance.dto.query.dict.DictQueryDto;
import com.vivo.finance.entity.dict.SysDict;
import com.vivo.finance.entity.dict.SysDictItem;
import com.vivo.finance.mapper.dict.SysDictItemMapper;
import com.vivo.finance.mapper.dict.SysDictMapper;
import com.vivo.finance.service.dict.SysDictItemService;
import com.vivo.finance.utils.wrapper.WrapperQueryHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.APRep;

import java.util.List;

/**
 * @author zyk
 * @date 2022年01月20日
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper,SysDictItem> implements SysDictItemService {

    @Autowired
    private SysDictItemMapper dictItemMapper ;

    /**
     * @param queryDto
     * @param page
     * @Description: 根据查询条件获取字典的分页集合
     * @author zyk
     * @date 2022/1/21 9:05
     */
    public Page<SysDictItem> getPageByCondition(Page<SysDictItem> page, DictItemQueryDto queryDto) {
        QueryWrapper<SysDictItem> wrapper = WrapperQueryHelp.getQueryWrapper(queryDto);

        Page<SysDictItem> pageList = dictItemMapper.selectPage(page, wrapper);
        return pageList;
    }

    /**
     * @Description: 根据查询条件获取字典的集合
     * @author zyk
     * @date 2022/1/21 9:05
     * @param queryDto 查询条件实体类
     * @param queryDto 分页对象
     */
    public List<SysDictItem> getListByCondition(DictItemQueryDto queryDto){
        QueryWrapper<SysDictItem> wrapper = WrapperQueryHelp.getQueryWrapper(queryDto);
        List<SysDictItem> list = dictItemMapper.selectList( wrapper);
        return list;
    }

}
