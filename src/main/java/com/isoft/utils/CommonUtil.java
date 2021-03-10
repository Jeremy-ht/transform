package com.isoft.utils;

import java.util.UUID;

/**
 * 公共工具类
 */
public class CommonUtil {

    /**
     * 获取六位随机数
     */
    public static String getRandomSixNum() {
        return UUID.randomUUID().toString().substring(0,6);
    }

}
