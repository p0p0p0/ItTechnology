package com.safetyhuge.chanlian.safetyhuge.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.safetyhuge.chanlian.ECApplication;

public class CommonUtil {
    /**
     * 在主线程执行一段任务
     *
     * @param r
     */
    public static void runOnUIThread(Runnable r) {
        ECApplication.mainHandler.post(r);
    }

    /**
     * 移除子View
     *
     * @param child
     */
    public static void removeSelfFromParent(View child) {
        if (child != null) {
            ViewParent parent = child.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(child);//移除子View
            }
        }
    }

    /**
     * 获取图片资源
     *
     * @param id
     * @return
     */
    public static Drawable getDrawable(int id) {
        return ECApplication.context.getResources().getDrawable(id);
    }

    /**
     * 获取字符串
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return ECApplication.context.getResources().getString(id);
    }

    /**
     * 获取资源数组
     *
     * @param id
     * @return
     */
    public static String[] getStringArray(int id) {
        return ECApplication.context.getResources().getStringArray(id);
    }

    /**
     * 获取颜色
     *
     * @param id
     * @return
     */

    public static int getColor(int id) {
        return ECApplication.context.getResources().getColor(id);
    }

    /**
     * 获取dp资源，并且会自动将dp值转为px值
     *
     * @param id
     * @return
     */
    public static int getDimens(int id) {
        return ECApplication.context.getResources().getDimensionPixelSize(id);
    }
}
