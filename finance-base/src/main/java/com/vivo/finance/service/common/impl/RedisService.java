package com.vivo.finance.service.common.impl;

import com.vivo.finance.constants.AuthNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author zyk
 * @Description reidis 实现类
 * @date 2022年01月20日
 */
@Slf4j
@Component
public class RedisService {
    private StringRedisTemplate redisTemplate;
    private final String projectName="fiance_";

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 根据token获取用户信息
     * @author panhao
     * @createTime 2020/7/21 8:47
     * @param accessToken token
     * @return java.lang.String
     */
    public String getUserInfoStr(String accessToken){
        return redisTemplate.opsForValue().get(AuthNameConstants.AUTH_PREFIX + accessToken);
    }

    /**
     * 过期某个key
     * @author panhao
     * @createTime 2020/7/21 8:50
     * @param key key
     */
    public void expireData(String key){
        redisTemplate.expire(key, Duration.ofMinutes(0));
    }

    /**
     * 资金管理系统信息缓存
     * @author zhangyukui
     * @createTime 2022/01/20 15:39
     * @return void
     */
    public void setGlobalValue(String key,String value){
        redisTemplate.opsForValue().set(projectName+key,value);
    }

    /**
     * 资金管理系统缓存信息获取
     * @param key
     * @return
     */
    public String getGlobalValue(String key){
        return redisTemplate.opsForValue().get(projectName+key);
    }

}
