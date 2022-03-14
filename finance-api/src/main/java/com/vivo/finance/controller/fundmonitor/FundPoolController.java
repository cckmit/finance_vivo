package com.vivo.finance.controller.fundmonitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vivo.finance.aspect.annotation.AutoLog;
import com.vivo.finance.controller.BasicController;
import com.vivo.finance.dto.query.dict.DictQueryDto;
import com.vivo.finance.dto.query.fundmonitor.FundPoolQuery;
import com.vivo.finance.dto.response.Result;
import com.vivo.finance.entity.dict.SysDict;
import com.vivo.finance.entity.fundmonitor.FmFundPool;
import com.vivo.finance.service.fundmonitor.FMFundPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zyk
 * @Description
 * @date 2022年03月13日
 */
public class FundPoolController extends BasicController {

    @Autowired
    private FMFundPoolService fundPoolService;

    /**
     * @Description:
     * @author zyk
     * @date 2022/3/13 14:45
     * @param query
     */
    @AutoLog(value = "资金池管理-分页查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<FmFundPool>> queryPageList(FundPoolQuery query) {
        Result<IPage<FmFundPool>> result = new Result<>();
        IPage<FmFundPool> pageList = fundPoolService.getPageByCondition(getPageNotOrder(), query);
        return result.OK(pageList);
    }


    /**
     * @Description: 资金池管理
     * @author zyk
     * @date 2022/3/13 14:48
     * @param fundPool
     */
    @AutoLog(value = "资金池管理-新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<FmFundPool> add(@RequestBody FmFundPool fundPool) {
        Result<FmFundPool> result = new Result<>();
        fundPoolService.save(fundPool);
        result.success("保存成功！");
        return result;
    }

    /**
     * @Description: 资金池管理 新增
     * @author zyk
     * @date 2022/3/13 14:49
     * @param fundPool
     */
    @AutoLog(value = "资金池管理-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<FmFundPool> edit(@RequestBody FmFundPool fundPool) {
        Result<FmFundPool> result = new Result<>();
        boolean ok = fundPoolService.updateById(fundPool);
        if (ok) {
            result.success("编辑成功!");
        }else {
            result.success("编辑失败，请刷新");
        }
        return result;
    }

    /**
     * @Description: 资金池管理 删除
     * @author zyk
     * @date 2022/3/13 14:51
     * @param id
     */
    @AutoLog(value = "资金池管理-删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<FmFundPool> delete(@RequestParam(name="id",required=true) String id) {
        Result<FmFundPool> result = new Result<>();
        boolean ok = fundPoolService.removeById(id);
        if(ok) {
            result.success("删除成功!");
        }else{
            result.success("删除失败!请刷新");
        }
        return result;
    }



}
