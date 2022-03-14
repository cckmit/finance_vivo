package com.vivo.finance.utils.wrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColumnUtils {

    private static final char SEPARATOR = '_';

    /**
     *下划线转驼峰 如：USER_TEST  转为 userTest
     * @param  s 驼峰字符串
     * @return
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     * @param param 字段名称 userTest
     * @param upperCase 是否转为大写
     *                  如果为true 为USER_TEST
     *                  如果为false 为：user_test
     * @return
     */
    public static String camelToUnderline(String param, boolean upperCase) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(SEPARATOR);
            }
            if (upperCase) {
                //统一都转大写
                sb.append(Character.toUpperCase(c));
            } else {
                //统一都转小写
                sb.append(Character.toLowerCase(c));
            }


        }
        return sb.toString();
    }


    public static void main(String[] args) {
        // String USER_TEST = toCamelCase("USER_TEST");
        // System.out.println("下划线转驼峰01："+USER_TEST);
        // String user_Test = toCamelCase("user_Test");
        // System.out.println("下划线转驼峰02："+user_Test);

        String   toUnderline01 = camelToUnderline("userTest",false);
        System.out.println("驼峰转下划线01："+toUnderline01);
        String toUnderline02 = camelToUnderline("userTest",true);
        System.out.println("驼峰转下划线02："+toUnderline02);

    }


}
