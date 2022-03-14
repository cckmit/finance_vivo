package com.vivo.finance.service.common;

import com.alibaba.fastjson.JSONObject;
import com.vivo.finance.constants.AuthNameConstants;
import com.vivo.finance.entity.base.FMDataLog;
import com.vivo.finance.entity.common.LoginUser;
import com.vivo.finance.service.common.impl.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zyk
 * @Description
 * @date 2022年02月21日
 */
public interface BasicService {

     /**
      * @Description: 获取当前登录用户的工号
      * @author zyk
      * @date 2022/2/24 9:09
      */
     String getLoginUserNo();


}
