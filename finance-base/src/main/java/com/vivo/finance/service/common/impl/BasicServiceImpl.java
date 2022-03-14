package com.vivo.finance.service.common.impl;

import com.alibaba.fastjson.JSONObject;
import com.vivo.finance.constants.AuthNameConstants;
import com.vivo.finance.entity.base.FMDataLog;
import com.vivo.finance.entity.common.LoginUser;
import com.vivo.finance.mapper.common.BasicMapper;
import com.vivo.finance.service.common.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zyk
 * @date 2022年02月23日
 */
@Slf4j
@Service
public class BasicServiceImpl implements BasicService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private HttpServletRequest request;


    //@Autowired
    //@Lazy
    //private BasicMapper basicMapper;

    /**
     * @Description: 获取当前登录用户的工号
     * @author zyk
     * @date 2022/2/21 16:46
     */
    public String getLoginUserNo() {
        // 获取token
        String accessToken = AuthNameConstants.ACCESS_TOKEN;
        String header = request.getHeader(accessToken);
        // 用户信息转换为JSon
        String serviceInfo = redisService.getUserInfoStr(header);
        JSONObject json = JSONObject.parseObject(serviceInfo);
        // 获取用户信息
        LoginUser loginUser = JSONObject.toJavaObject(json, LoginUser.class);
        return loginUser.getName();
    }


}
