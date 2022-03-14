package com.vivo.finance.constants;

/**
 * 授权名称常量
 * @Author: zhangyukui
 * @CreateDate: 2020/8/14 17:39
 */
public class AuthNameConstants {
    // 授权服务超级管理员
   final public static String ROLE_AUTH_SUPER_ADMIN = "ROLE_AUTH_SUPER_ADMIN";
    // redis存储的前缀等信息
    final public static String AUTH_PREFIX = "auth-server-auth:";

    final public  static   String AUTH_ACCESS_PREFIX = "auth-server-access:";

    final public static String REFRESH_TOKEN_PREFIX = "auth-server-access_to_refresh:";
    final public static String REFRESH_AUTH_PREFIX = "auth-server-refresh_auth:";
    final public static String ACCESS_TOKEN = "D-Token";
}
