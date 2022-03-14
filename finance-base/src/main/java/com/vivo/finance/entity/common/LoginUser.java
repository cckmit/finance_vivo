package com.vivo.finance.entity.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zyk
 * @Description 登录用户信息
 * @date 2022年01月20日
 */
@Data
public class LoginUser   implements Serializable {
    /*用户ID*/
    private Long id;
    /*备注*/
    private String remark;
    /*uid*/
    private String uid;
    /*登录失败次数*/
    private String loginFailNum;
    /*用户名*/
    private String name;
    /*邮箱*/
    private String userEmail;
    /*手机号*/
    private String userMobile;
    /*用户类型名称*/
    private String userType;
    /*登录用户名称*/
    private String username;
    /*用户状态*/
    private Integer status;
}
