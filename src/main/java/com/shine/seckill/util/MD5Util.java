package com.shine.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassFormPass(String inputPass) {
        String str =""+salt.charAt(0) + salt.charAt(1) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassDBPass(String formPass,String salt) {
        String str =""+salt.charAt(0) + salt.charAt(1) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass,String saltDB) {
        return formPassDBPass(inputPassFormPass(inputPass),saltDB);

    }

    public static void main(String[] args) {
//        System.out.println(inputPassFormPass("123456"));
        System.out.println(formPassDBPass(inputPassFormPass("133331"),"1a2b3c4d"));
        System.out.println(inputPassToDBPass("133331", "1a2b3c4d"));
    }

}
