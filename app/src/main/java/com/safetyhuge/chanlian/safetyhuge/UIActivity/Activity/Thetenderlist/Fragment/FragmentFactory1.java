package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment;

import android.support.v4.app.Fragment;

import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DIngjinFragment.DingjinFragment;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.PutongFragment.PutongFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * FragmentFactory工厂类
 */
public class FragmentFactory1 {
    private static boolean flage = true;
    private static int i;
    public FragmentFactory1(int i) {
        this.i = i;
    }

    public static void setFlage(boolean flage) {
        FragmentFactory1.flage = flage;
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
            if(i==2){
                switch (position) {
                    case 0:
                        fragment = new PutongFragment(0);
                        break;
                    case 1:
                        fragment = new PutongFragment(1);
                        break;
                    case 2:
                        fragment = new PutongFragment(2);
                        break;
                    case 3:
                        fragment = new PutongFragment(3);
                        break;
                }
            } if(i==3){
                switch (position) {
                    case 0:
                        fragment = new DingjinFragment(0);
                        break;
                    case 1:
                        fragment = new DingjinFragment(1);
                        break;
                    case 2:
                        fragment = new DingjinFragment(2);
                        break;
                    case 3:
                        fragment = new DingjinFragment(3);
                        break;
                }
            }
            //保存到集合
            mFragments.put(position, fragment);
        }
        return fragment;
    }
}
