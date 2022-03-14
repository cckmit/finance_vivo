package com.vivo.finance.config.mybatisplus;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.vivo.finance.entity.base.FMDataLog;
import com.vivo.finance.mapper.dict.FMDataLogMapper;
import com.vivo.finance.service.common.BasicService;
import com.vivo.finance.service.dict.FMDataLogService;
import com.vivo.finance.utils.wrapper.ColumnUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Autowired
    private BasicService BasicService;

    //采用懒加载， 否则会报错：The dependencies of some of the beans in the application context form a cycle:
    @Lazy
    @Autowired
    private FMDataLogMapper dataLogMapper;


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //MP起始版本 3.3.3(推荐)
        String userNo = BasicService.getLoginUserNo();
        this.strictInsertFill(metaObject, "createBy", String.class, userNo);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "ver", Integer.class, 1);
        this.strictInsertFill(metaObject, "delFlag", Integer.class, 0);
        addLog(metaObject.getOriginalObject());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("start update fill ....");
        String userNo = BasicService.getLoginUserNo();
        this.strictUpdateFill(metaObject, "updateBy", String.class, userNo);
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        addLog(metaObject.getOriginalObject());

    }

    /**
     * @param object
     * @Description: 保存变更，新增数据日志记录
     * @author zyk
     * @date 2022/2/21 16:49
     */
    private void addLog(Object object) {

        // 如果保存的对象为 数据日志对象则返回
        if (object.getClass() == FMDataLog.class) {
            return;
        }
        String dataJson = JSONObject.toJSONString(object);
        String tableName = "";
        TableName anno = object.getClass().getAnnotation(TableName.class);
        if (null != anno) {
            String value = anno.value();
            tableName = value.substring(value.indexOf("."));
        } else {
            tableName = ColumnUtils.camelToUnderline(object.getClass().getName(), true);
        }
        JSONObject json = JSONObject.parseObject(dataJson);
        FMDataLog log = JSONObject.parseObject(dataJson, FMDataLog.class);
        log.setDataId(json.getString("id"));
        log.setId(null);
        log.setDataTable(tableName.replace(".", "").toUpperCase(Locale.ROOT));
        log.setDataContent(json.toJSONString());
        dataLogMapper.insert(log);

    }

}