package com.shine.seckill.util;

import java.util.UUID;

/**
 * Create By shine
 * 2018-09-07 13:46
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
