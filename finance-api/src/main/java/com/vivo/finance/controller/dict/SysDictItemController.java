package com.vivo.finance.controller.dict;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vivo.finance.controller.BasicController;
import com.vivo.finance.dto.query.dict.DictItemQueryDto;
import com.vivo.finance.dto.response.Result;
import com.vivo.finance.entity.dict.SysDictItem;
import com.vivo.finance.service.dict.SysDictItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zyk
 * @Description 数据字典明细控制层
 * @date 2022年02月16日
 */
@RestController
@RequestMapping("globalConfig/dictItem/")
@Slf4j
public class SysDictItemController extends BasicController {
    @Autowired
    private SysDictItemService dictItemService;


    /**
     * @param dictItemQuery
     * @Description: 查询字典数据
     * @author zyk
     * @date 2022/2/16 16:58
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<SysDictItem>> queryPageList(DictItemQueryDto dictItemQuery) {
        Result<IPage<SysDictItem>> result = new Result<>();
        IPage<SysDictItem> pageList = dictItemService.getPageByCondition(getPageNotOrder(), dictItemQuery);
        return result.OK(pageList);
    }

    /**
     * @param dictItem
     * @Description: 新增字典明细
     * @author zyk
     * @date 2022/2/16 17:02
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<SysDictItem> add(@RequestBody SysDictItem dictItem) {
        Result<SysDictItem> result = new Result<>();
        dictItemService.save(dictItem);
        result.success("保存成功！");
        return result;
    }

    /**
     * @param sysDictItem
     * @Description: 编辑字典明细
     * @author zyk
     * @date 2022/2/16 17:02
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<SysDictItem> edit(@RequestBody SysDictItem sysDictItem) {
        Result<SysDictItem> result = new Result<>();
        boolean ok = dictItemService.updateById(sysDictItem);
        if (ok) {
            result.success("编辑成功!");
        } else {
            result.success("编辑失败，请刷新");
        }
        return result;
    }

    /**
     * @param id
     * @Description: 删除字典明细
     * @author zyk
     * @date 2022/2/16 17:04
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<SysDictItem> delete(@RequestParam(name = "id", required = true) String id) {
        Result<SysDictItem> result = new Result<>();
        boolean ok = dictItemService.removeById(id);
        if (ok) {
            result.success("删除成功!");
        } else {
            result.success("删除失败!请刷新");
        }
        return result;
    }

    @RequestMapping(value = "/dictItemCheck", method = RequestMethod.GET)
    public Result<Object> doDictItemCheck(SysDictItem sysDictItem) {
        Long num = 0L;
        LambdaQueryWrapper<SysDictItem> queryWrapper = new LambdaQueryWrapper<SysDictItem>();
        queryWrapper.eq(SysDictItem::getItemValue,sysDictItem.getItemValue());
        queryWrapper.eq(SysDictItem::getDictId,sysDictItem.getDictId());
        if (StringUtils.isNotBlank(sysDictItem.getId())) {
            // 编辑页面校验
            queryWrapper.ne(SysDictItem::getId,sysDictItem.getId());
        }
        num = dictItemService.count(queryWrapper);
        if (num == 0) {
            // 该值可用
            return Result.OK("该值可用！");
        } else {
            // 该值不可用
            log.info("该值不可用，系统中已存在！");
            return Result.error("该值不可用，系统中已存在！");
        }
    }
}
