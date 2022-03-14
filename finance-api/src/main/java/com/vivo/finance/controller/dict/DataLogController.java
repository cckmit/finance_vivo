package com.vivo.finance.controller.dict;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vivo.finance.controller.BasicController;
import com.vivo.finance.dto.query.dict.DataLogQueryDto;
import com.vivo.finance.dto.response.Result;
import com.vivo.finance.entity.base.FMDataLog;
import com.vivo.finance.service.dict.FMDataLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author zyk
 * @Decription: 数据日志
 * @date 2022年02月27日
 */
@RestController
@RequestMapping("globalConfig/dataLog/")
@Slf4j
public class DataLogController extends BasicController {

    @Autowired
    private FMDataLogService dataLogService;

    /*
     * @Description: 根据查询条件获取数据字典集合
     * @author zyk
     * @date 2022/1/20 19:44
     * @param dictQuery 数据字典查询实体
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<FMDataLog>> queryPageList(DataLogQueryDto logQueryDto) {
        Result<IPage<FMDataLog>> result = new Result<>();
        IPage<FMDataLog> pageList = dataLogService.getPageByCondition(getPageNotOrder(), logQueryDto);
        return result.OK(pageList);
    }

    /**
     * @Description: 查询数据日志版本信息
     * @author zyk
     * @date 2022/2/18 9:36
     */
    @RequestMapping(value = "/queryDataVerList", method = RequestMethod.GET)
    public Result<List<FMDataLog>> queryDataVerList( String dataTable, String dataId) {
        Result<List<FMDataLog>> result = new Result<>();
        QueryWrapper<FMDataLog> queryWrapper = new QueryWrapper<FMDataLog>();
        queryWrapper.eq("data_table", dataTable);
        queryWrapper.eq("data_id", dataId);
        List<FMDataLog> list = dataLogService.list(queryWrapper);
        if(list==null||list.size()<=0) {
            result.error("未找到版本信息");
        }else {
            result.success(list);
        }
        return result;
    }

    /**
     * @Description: 查询对比数据
     * @author zyk
     * @date 2022/2/18 9:37
     */
    @RequestMapping(value = "/queryCompareList", method = RequestMethod.GET)
    public Result<List<FMDataLog>> queryCompareList(@RequestParam(name="dataId1",required=true) String dataId1,
                                                    @RequestParam(name="dataId2",required=true) String dataId2) {
        Result<List<FMDataLog>> result = new Result<>();
        try {
            List<FMDataLog> list =  dataLogService.listByIds(Arrays.asList(dataId1,dataId2));
            result.success(list);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
}
