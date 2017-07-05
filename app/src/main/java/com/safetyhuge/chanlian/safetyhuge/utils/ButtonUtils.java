package com.safetyhuge.chanlian.safetyhuge.utils;

/**
 * 作者：王海宾 on 2017/6/22 0022 14:27
 * 邮箱：995696826@qq.com
 */

public class ButtonUtils {
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
