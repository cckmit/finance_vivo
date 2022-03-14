package com.vivo.finance.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author zyk
 * @Description：md5 加密工具类
 * @date 2022年03月02日
 */
@Slf4j
public class MD5Utils {

    /**
     * @param str 被加密的字符串
     * @return java.lang.String
     * @author panhao
     * @description 获取md5加密的字符串
     * @createTime 2020/2/26 10:25
     */
    public static String getMd5String(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (byte b : byteArray) {
            if (Integer.toHexString(0xFF & b).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & b));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & b));
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * @Description: 传入可变长度加密成MD5
     * @author zyk
     * @date 2022/3/2 19:29
     * @param obj
     */
    public static String getString(Object ... obj) throws NoSuchAlgorithmException {
        String signStr = "";
        for (Object o : obj) {
            if (null == o )
                continue;
            if (o instanceof Date){
                signStr =signStr +DateUtils.dateToStr((Date)o);
            }else {
                signStr =signStr + o.toString();
            }
        }
        log.info("加密前值：{}",signStr);
        String result = getMd5String(signStr);
        log.info("加密后值：{}",result);
        return result;
    }
}
