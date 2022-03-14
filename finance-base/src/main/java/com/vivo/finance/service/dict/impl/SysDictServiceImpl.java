package com.vivo.finance.service.dict.impl;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vivo.finance.constants.CommonConstant;
import com.vivo.finance.dto.query.dict.DictQueryDto;
import com.vivo.finance.dto.response.dict.DictModel;
import com.vivo.finance.entity.dict.SysDict;
import com.vivo.finance.entity.dict.SysDictItem;
import com.vivo.finance.mapper.dict.SysDictItemMapper;
import com.vivo.finance.mapper.dict.SysDictMapper;
import com.vivo.finance.service.dict.SysDictService;
import com.vivo.finance.utils.wrapper.WrapperQueryHelp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据字典Service实现
 *
 * @author zyk
 * @date 2022年01月20日
 */
@Service
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Autowired
    private SysDictMapper dictMapper;

    @Autowired
    private SysDictItemMapper dictItemMapper;

    /**
     * @param queryDto
     * @param page
     * @Description: 根据查询条件获取字典的分页集合
     * @author zyk
     * @date 2022/1/21 9:05
     */
    public Page<SysDict> getPageByCondition(Page<SysDict> page, DictQueryDto queryDto) {
        QueryWrapper<SysDict> wrapper = WrapperQueryHelp.getQueryWrapper(queryDto);
        Page<SysDict> pageList = dictMapper.selectPage(page, wrapper);
        return pageList;
    }

    /**
     * @param queryDto 查询条件实体类
     * @Description: 根据查询条件获取字典的集合
     * @author zyk
     * @date 2022/1/21 9:05
     */
    public List<SysDict> getListByCondition(DictQueryDto queryDto) {
        QueryWrapper<SysDict> wrapper = WrapperQueryHelp.getQueryWrapper(queryDto);
        List<SysDict> list = dictMapper.selectList(wrapper);
        return list;
    }

    /*
     * @Description: 获取已经失效的数据
     * @author zyk
     * @date 2022/1/21 9:37
     */
    public List<SysDict> getInvalidList() {
        return dictMapper.getInvalidList();
    }

    /**
     * @Description: 获取所有得数据字典信息
     * @author zyk
     * @date 2022/2/17 11:11
     */
    @Override
    public Map<String, List<DictModel>> queryAllDictItems() {
        Map<String, List<DictModel>> res = new HashMap<>();
        List<SysDict> sysDicts = dictMapper.selectList(null);
        LambdaQueryWrapper<SysDictItem> queryWrapper = new LambdaQueryWrapper<SysDictItem>();
        queryWrapper.eq(SysDictItem::getStatus, CommonConstant.STATUS_NORMAL);
        queryWrapper.orderByAsc(SysDictItem::getSortOrder);
        List<SysDictItem> dictItemList = dictItemMapper.selectList(queryWrapper);

        for (SysDict d : sysDicts) {
            List<DictModel> dictModelList = dictItemList.stream().filter(s -> d.getId().equals(s.getDictId())).map(item -> {
                DictModel dictModel = new DictModel();
                dictModel.setText(item.getItemText());
                dictModel.setValue(item.getItemValue());
                return dictModel;
            }).collect(Collectors.toList());
            res.put(d.getDictCode(), dictModelList);
        }
        log.debug("-------登录加载系统字典-----" + res.toString());
        return res;
    }


}
