package com.vivo.finance.utils.wrapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.vivo.finance.utils.FMStringUtils;
import com.vivo.finance.utils.reflection.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 更新语句的mapper
 */
@Slf4j
public class WrapperUpdateHelp {

    /**
     * 变更Wrapper构建
     * @param entity 需要变更的对象实体
     * @param columns 有变更权限的信息
     * @param <R> 变更对象泛型
     * @return
     */
    public static <R> UpdateWrapper<R> getUpdateWrapper(R entity, List<String> columns) {
        UpdateWrapper<R> updateWrapper = new UpdateWrapper<>();
        if (entity == null) {
            return null;
        }
        List<Field> fields = ReflectionUtils.getAllFields(entity.getClass(), new ArrayList<>());
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                TableField  columnField = field.getAnnotation(TableField.class);
                /*如果实体类上没有标注TableField 或者 TableField.exist  为true 则为数据库字段，可以更新*/
                if (null == columnField||columnField.exist()){
                    /*获取数据库列名*/
                    String column = ColumnUtils.camelToUnderline(field.getName(),true);
                    if (null != columnField && FMStringUtils.isBlank(columnField.value())){
                        column = columnField.value();
                    }

                    Object val = field.get(entity);
                    if (columns.contains(column)){
                        updateWrapper.set(column,val);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.error("编辑条件拼接错误：{}",e);
        }
        return updateWrapper;
    }


    /**
     * 变更Wrapper构建 根据主键进行变更
     * @param entity 需要变更的对象实体
     * @param columns 有变更权限的信息
     * @param <R> 变更对象泛型
     * @return
     */
    public static <R> UpdateWrapper<R> getUpdateByIdWrapper(R entity, List<String> columns) {
        UpdateWrapper<R> updateWrapper = new UpdateWrapper<>();
        if (entity == null) {
            return null;
        }
        /*获取实体类的反射集合*/
        List<Field> fields = ReflectionUtils.getAllFields(entity.getClass(), new ArrayList<>());
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                TableField  columnField = field.getAnnotation(TableField.class);
                TableId tableId = field.getAnnotation(TableId.class);
                /*如果实体类上没有标注TableField 或者 TableField.exist  为true 则为数据库字段，可以更新*/
                if (null == columnField||columnField.exist()){
                    /*获取数据库列名*/
                    String column =ColumnUtils.camelToUnderline(field.getName(),true);
                    if (null != columnField && FMStringUtils.isBlank(columnField.value())){
                        column = columnField.value();
                    }
                    Object val = field.get(entity);
                    if (columns.contains(column)){
                        updateWrapper.set(column,val);
                    }
                    /*如果存在ID 并且主键ID不为null 则根据逐渐ID来变更 */
                    if (null!=tableId && ObjectUtils.isNotNull(val)){
                        updateWrapper.eq(column,val);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.error("编辑条件拼接错误：{}",e);
        }
        return updateWrapper;
    }



}
