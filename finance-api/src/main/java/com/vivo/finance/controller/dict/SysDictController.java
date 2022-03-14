package com.vivo.finance.controller.dict;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vivo.finance.aspect.annotation.AutoLog;
import com.vivo.finance.controller.BasicController;
import com.vivo.finance.dto.query.dict.DictQueryDto;
import com.vivo.finance.dto.response.Result;
import com.vivo.finance.dto.response.dict.DictModel;
import com.vivo.finance.entity.dict.SysDict;
import com.vivo.finance.service.dict.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyk
 * @Decription: 数据字典控制层
 * @date 2022年01月20日
 */
@RestController
@RequestMapping("globalConfig/dict/")
@Slf4j
public class SysDictController extends BasicController {

    @Autowired
    private SysDictService sysDictService;

    /*
     * @Description: 根据查询条件获取数据字典集合
     * @author zyk
     * @date 2022/1/20 19:44
     * @param dictQuery 数据字典查询实体
     */
    @AutoLog(value = "数据字典-分页查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<SysDict>> queryPageList(DictQueryDto dictQuery) {
        Result<IPage<SysDict>> result = new Result<>();
        IPage<SysDict> pageList = sysDictService.getPageByCondition(getPageNotOrder(), dictQuery);
        return result.OK(pageList);
    }

    /**
     * @Description: 获取数据字典的所有信息
     * @author zyk
     * @date 2022/2/17 11:17
     */
    @RequestMapping(value = "/queryAllDictItems", method = RequestMethod.GET)
    public Result queryAllDictItems() {
        Map<String, List<DictModel>> res = new HashMap<>();
        res = sysDictService.queryAllDictItems();
        return Result.OK(res);
    }


    /*
     * @Description: 数据字典信息新增
     * @author zyk
     * @date 2022/1/20 20:09
     * @param sysDict 数据字典对象
     */
    @AutoLog(value = "数据字典-新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<SysDict> add(@RequestBody SysDict sysDict) {
        Result<SysDict> result = new Result<>();
        sysDictService.save(sysDict);
        result.success("保存成功！");
        return result;
    }

    /*
     * @Description: 数据字典编辑保存
     * @author zyk
     * @date 2022/1/20 20:09
     * @param sysDict 数据字典对象
     */
    @AutoLog(value = "数据字典-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<SysDict> edit(@RequestBody SysDict sysDict) {
        Result<SysDict> result = new Result<>();
        boolean ok = sysDictService.updateById(sysDict);
        if (ok) {
            result.success("编辑成功!");
        }else {
            result.success("编辑失败，请刷新");
        }
        return result;
    }

    /*
     * @Description:
     * @author zyk
     * @date 2022/1/20 20:08
     * @param id 数据字典主键ID
     */
    @AutoLog(value = "数据字典-删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<SysDict> delete(@RequestParam(name="id",required=true) String id) {
        Result<SysDict> result = new Result<>();
        boolean ok = sysDictService.removeById(id);
        if(ok) {
            result.success("删除成功!");
        }else{
            result.success("删除失败!请刷新");
        }
        return result;
    }

    /**
     * @Description: 刷新缓存
     * @author zyk
     * @date 2022/3/14 11:50
     */
    @RequestMapping(value = "/refleshCache", method = RequestMethod.GET)
    public  Result refleshCache(){
        return Result.OK();
    }
}
