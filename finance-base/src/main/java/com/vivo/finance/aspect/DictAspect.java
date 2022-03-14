package com.vivo.finance.aspect;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vivo.finance.dto.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author zyk
 * @Description
 * @date 2022年02月21日
 */
//@Aspect
//@Component
@Slf4j
public class DictAspect {
    @Pointcut("execution(public * com.vivo.finance..*.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time1=System.currentTimeMillis();
        Object result = pjp.proceed();
        long time2=System.currentTimeMillis();
        log.debug("获取JSON数据 耗时："+(time2-time1)+"ms");
        long start=System.currentTimeMillis();
        this.parseDictText(result);
        long end=System.currentTimeMillis();
        log.debug("注入字典到JSON数据  耗时"+(end-start)+"ms");
        return result;
    }

    /**
     * @Description: 针对返回的对象动态主图字典
     * @author zyk
     * @date 2022/2/21 10:43
     * @param reult
     */
    private void parseDictText(Object reult){
        if ( reult instanceof Result){
          if(((Result) reult).getResult() instanceof IPage){

          }
        }
    }




}
