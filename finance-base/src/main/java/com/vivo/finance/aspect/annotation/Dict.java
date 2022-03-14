package com.vivo.finance.aspect.annotation;


/**
 * @Description: 字典注解
 * @author zyk
 * @date 2022/2/21 9:09
 */
public @interface Dict {

    /**
     * @Description: 数据code
     * @author zyk
     * @date 2022/2/21 9:09
     */
    String dicCode();

    /**
     * @Description: 数据text
     * @author zyk
     * @date 2022/2/21 9:10
     */
    String dicText() default "";

    /**
     * @Description: 数据字典表
     * @author zyk
     * @date 2022/2/21 9:11
     */
    String dictTable() default "";



}
