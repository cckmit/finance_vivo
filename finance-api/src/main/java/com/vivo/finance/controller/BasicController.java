package com.vivo.finance.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vivo.finance.constants.AuthNameConstants;
import com.vivo.finance.constants.BasicEntityFieldConstant;
import com.vivo.finance.entity.common.LoginUser;
import com.vivo.finance.service.common.impl.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BasicController {

    @Autowired
    protected RedisService redisService;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /*数据来源*/
    private final String ourcesCode = "VIVO";


    /**
     * 根据request 获取缓存
     * @author zhangyukui
     * @createTime 2022/01/20 15:05
     */
    protected String getToken(){
        return request.getHeader(AuthNameConstants.ACCESS_TOKEN);
    }

    /**
     * 根据token 获取 redis 对象信息
     * @author zyk
     * @createTime 2022/01/20 15:06
     */
    protected JSONObject getUserJson(){
        String serviceInfo =  redisService.getUserInfoStr(getToken());
        JSONObject userJson = JSONObject.parseObject(serviceInfo);
        return userJson;
    }

    /**
     * @Description: 获取登录用户信息
     * @author zyk
     * @date 2022/1/20 20:44
     */
    public LoginUser getLoginUser(){
        JSONObject json = getUserJson();
        JSONObject userJson = json.getJSONObject("principal").getJSONObject("userInfo");
        LoginUser loginUser = JSONObject.toJavaObject(userJson, LoginUser.class);
        return loginUser;
    }

    /**
     * @Description: 获取登录用户角色集合
     * @author zyk
     * @date 2022/1/20 20:44
     */
    public List<String> getRoleList(){
        // 获取角色名列表
        JSONObject json = getUserJson();
        JSONArray authorizes = json.getJSONObject("principal").getJSONArray("authorizes");
        List<String> roleNameList = authorizes.parallelStream()
                .filter(authorizeObject -> {
                    JSONObject authorize = (JSONObject) authorizeObject;
                    return authorize.getString("authority").startsWith("ROLE_");
                })
                .map(authorizeObject -> {
                    JSONObject authorize = (JSONObject) authorizeObject;
                    return authorize.getString("authority");
                }).collect(Collectors.toList());
        return roleNameList;
    }

    public Page  PagegetPage() {
        String pageNum = request.getParameter("pageNo");
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = "1";
        }
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = "20";
        }
        String sortName = request.getParameter("column");
        String sortOrder = request.getParameter("order");
        if(StringUtils.isBlank(sortName)){
            sortName = BasicEntityFieldConstant.NAME_ID;
        }
        Page page = new Page(Long.valueOf(pageNum), Long.valueOf(pageSize));
        List<OrderItem> orderList = getOrder(sortName,sortOrder);
        page.setOrders(orderList);
        return page;
    }

    /**
     * @Description: 获取无序默认排序的几个
     * @author zyk
     * @date 2022/1/21 9:59
     * @param
     */
    public  Page getPageNotOrder() {
        String pageNum = request.getParameter("pageNo");
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = "1";
        }

        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = "20";
        }
        String sortName = request.getParameter("column");
        String sortOrder = request.getParameter("order");
        Page page = new Page(Long.valueOf(pageNum), Long.valueOf(pageSize));
        if(!StringUtils.isBlank(sortName)){
            List<OrderItem> orderList = getOrder(sortName,sortOrder);
            page.setOrders(orderList);
        }
        return page;
    }

    /**
     * @Description: 获取排序集合
     * @author zyk
     * @date 2022/1/21 9:59
     * @param sort
     * @param order
     */
    private List<OrderItem> getOrder(String sort, String order ){
        List<OrderItem> orderList = new ArrayList<>();
        String[] columnArray = sort.split(",");
        for (String str : columnArray) {
            OrderItem orderItem =  new OrderItem();
            orderItem.setColumn(str);
            if (BasicEntityFieldConstant.SORT_ASC.equalsIgnoreCase(order)) {
                orderItem.setAsc(true);
            } else {
                orderItem.setAsc(false);
            }
            orderList.add(orderItem);
        }
        return orderList;
    }


}
