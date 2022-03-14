package com.vivo.finance.utils.wrapper;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vivo.finance.aspect.annotation.FMQuery;
import com.vivo.finance.utils.FMStringUtils;
import com.vivo.finance.utils.reflection.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SuppressWarnings({"unchecked", "all"})
public class WrapperQueryHelp {

    public static <R, Q> QueryWrapper getQueryWrapper(Q query) {
        QueryWrapper<R> queryWrapper = new QueryWrapper();
        queryWrapper.or();
        if (query == null) {
            return null;
        }
        try {
            List<Field> fields = ReflectionUtils.getAllFields(query.getClass(), new ArrayList<>());
            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                // 设置对象的访问权限，保证对private的属性的访
                field.setAccessible(true);
                FMQuery q = field.getAnnotation(FMQuery.class);
                if (q != null) {
                    String propName = q.propName();
                    String blurry = q.blurry();
                    Object val = field.get(query);
                    String attributeName = FMStringUtils.isBlank(propName) ? ColumnUtils.camelToUnderline(field.getName(),false) : propName;
                    if (ObjectUtil.isNull(val) || "".equals(val)) {
                        continue;
                    }
                    
                    /*拼接多个like 查询 以 or连接*/
                    if (ObjectUtil.isNotEmpty(blurry)) {
                        String[] blurrys = blurry.split(",");
                        queryWrapper.and(queryOr->{
                            for (String s : blurrys) {
                                queryOr.or();
                                queryOr.like(ColumnUtils.camelToUnderline(s,false),val);
                            }
                        });
                        continue;
                    }

                    switch (q.type()) {
                        case EQUAL:
                            queryWrapper.eq(attributeName,val);
                            break;
                        case GREATER_THAN:
                            queryWrapper.ge(attributeName,val);
                            break;
                        case LESS_THAN:
                            queryWrapper.le(attributeName,val);
                            break;
                        case LESS_THAN_NQ:
                            queryWrapper.lt(attributeName,val);
                            break;
                        case GREATER_THAN_NQ:
                            queryWrapper.gt(attributeName,val);
                            break;
                        case INNER_LIKE:
                            queryWrapper.like(attributeName,val);
                            break;
                        case LEFT_LIKE:
                            queryWrapper.likeLeft(attributeName,val);
                            break;
                        case RIGHT_LIKE:
                            queryWrapper.likeRight(attributeName,val);
                            break;
                        case NOT_LIKE:
                            queryWrapper.notLike(attributeName,val);
                            break;
                        case IN:
                            queryWrapper.in(attributeName,val);
                            break;
                        case NOT_IN:
                            queryWrapper.notIn(attributeName,val);
                            break;
                        case NOT_EQUAL:
                            queryWrapper.ne(attributeName,val);
                            break;
                        case NOT_NULL:
                            queryWrapper.isNotNull(attributeName);
                            break;
                        case IS_NULL:
                            queryWrapper.isNull(attributeName);
                            break;
                        case BETWEEN:
                            List<Object> between = new ArrayList<>((List<Object>)val);
                            queryWrapper.between(attributeName,between.get(0),between.get(1));
                            break;
                        case NOT_BETWEEN:
                            List<Object> not_between = new ArrayList<>((List<Object>)val);
                            queryWrapper.notBetween(attributeName,not_between.get(0),not_between.get(1));
                            break;
                        default: break;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.error("查询条件拼接错误：{}",e);
        }
        return queryWrapper;
    }

}
