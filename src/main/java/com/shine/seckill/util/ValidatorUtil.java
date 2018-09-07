package com.shine.seckill.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    public static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String str){
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(str);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("13350376845"));
        System.out.println(isMobile("133503768451"));
    }

}
