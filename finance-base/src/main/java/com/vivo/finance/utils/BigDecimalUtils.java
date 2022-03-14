package com.vivo.finance.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zyk
 * @Description
 * @date 2022年03月08日
 */
public class BigDecimalUtils {

    /**
     * @Description: 两个数字相加， 如果有一个为null 则赋值为0
     * @author zyk
     * @date 2022/3/8 9:54
     * @param value1
     * @param value2
     */
    public static BigDecimal  add(BigDecimal value1,BigDecimal value2){
        if(value1 == null && value2 == null){
            return null;
        }
        if ( value1 == null){
            value1 = BigDecimal.ZERO;
        }
        if ( value2 == null){
            value2 = BigDecimal.ZERO;
        }
        return value1.add(value2);
    }
    
    /**
     * @Description: 传入集合， 统计合计值
     * @author zyk
     * @date 2022/3/8 17:39
     * @param list 原始集合
     * @param className  返回对象的class
     * @param name  合计名称 默认为合计
     * @param sumNameClomun  合计名称应放在哪一列
     * @param sumColumns  需要统计的对象列 必须为BigDecimal 类型
     */
    public static void collectSum(List list,Class className,String name,String sumNameClomun,Set<String> sumColumns){
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        JSONObject sum = new JSONObject();
        if (null == className){
            className = Map.class;
        }
        if (null == sumColumns){
            sumColumns =((JSONObject)jsonArray.get(0)).keySet();
        }
        if (StringUtils.isEmpty(name)){
            name ="合计:";
        }
        if (StringUtils.isNotEmpty(sumNameClomun)){
            sum.put(sumNameClomun,name);
        }
        for (Object o : jsonArray) {
            JSONObject json = (JSONObject) o;
            for (String clomun : sumColumns) {
                Object value = json.get(clomun);
                if (value instanceof BigDecimal){
                    BigDecimal bigDecimal = json.getBigDecimal(clomun);
                    sum.put(clomun,add(bigDecimal, sum.getBigDecimal(clomun)));
                }
            }
        }
        Object obj = JSONObject.parseObject(sum.toJSONString(), className);
        list.add(obj);
    }
}
