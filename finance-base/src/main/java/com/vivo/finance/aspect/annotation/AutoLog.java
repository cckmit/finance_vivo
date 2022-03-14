package com.vivo.finance.aspect.annotation;

import com.vivo.finance.constants.CommonConstant;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {

    /**
     * @Description: 日志内容
     * @author zyk
     * @date 2022/2/21 15:04
     */
    String value() default "";

    /**
     * 日志类型
     * @return 0:操作日志;1:登录日志;2:定时任务;
     */
    int logType() default CommonConstant.LOG_TYPE_2;

    /**
     * 操作日志类型
     * @return （1查询，2添加，3修改，4删除）
     */
    int operateType() default 0;


}
