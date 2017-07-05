package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.SaleGoodsFragmentInfo;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * FragmentFactory工厂类
 */
public class SaleGoodsFragmentFactory {
    private static boolean flage = true;
    private static int i;
    public SaleGoodsFragmentFactory(int i) {
        this.i = i;
    }

    public static void setFlage(boolean flage) {
        SaleGoodsFragmentFactory.flage = flage;
    }

    /**
     * 用于缓存Fragment实例的Map集合
     */
    public static Map<Integer, Fragment> mFragments = new HashMap<Integer, Fragment>();

    /**
     * 根据不同的position生产对应的Fragment对象(不缓存Fragment实例)
     */
    public static Fragment create(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            if (i==0){
                switch (position) {
                    //商品
                    case 0:
                        fragment = new SaleGoodsFragment(0);
                        break;
                    case 1:
                        fragment = new SaleGoodsFragment(1);
                        break;
                    case 2:
                        fragment = new SaleGoodsFragment(2);
                        break;
                    case 3:
                        fragment = new SaleGoodsFragment(3);
                        break;
                    case 4:
                        fragment = new SaleGoodsFragment(4);
                        break;
                }
                //收藏
            }
            //保存到集合
            mFragments.put(position, fragment);
        }
        return fragment;
    }
}
