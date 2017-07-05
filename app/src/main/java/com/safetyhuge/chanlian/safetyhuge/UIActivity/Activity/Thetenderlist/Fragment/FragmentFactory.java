package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment;

import android.support.v4.app.Fragment;

import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment.QuanbuFragment;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DuorenFragment.DuorenFragment;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

/**
 * FragmentFactory工厂类
 */
public class FragmentFactory {
    private static boolean flage = true;
    private static int i;
    public FragmentFactory(int i) {
        this.i = i;
    }

    public static void setFlage(boolean flage) {
        FragmentFactory.flage = flage;
    }

    /**
     * 用于缓存Fragment实例的Map集合
     */

    /**
     * 根据不同的position生产对应的Fragment对象(不缓存Fragment实例)
     */
    public static Fragment create(int position) {
        KLog.e("whb",i);
        KLog.e("whb",position);
        Map<Integer, Fragment> mFragments = new HashMap<Integer, Fragment>();
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            if (i==0){
                KLog.e("whb",position);
                switch (position) {
                    case 0:
                        fragment = new QuanbuFragment(0);
                        break;
                    case 1:
                        fragment = new QuanbuFragment(1);
                        break;
                    case 2:
                        fragment = new QuanbuFragment(2);
                        break;
                    case 3:
                        fragment = new QuanbuFragment(3);
                        break;
                    case 4:
                        fragment = new QuanbuFragment(4);
                        break;
                }
                //保存到集合
                mFragments.put(position, fragment);
            }else if(i==1){
                KLog.e("whb","走了3");
                KLog.e("whb",position);
                switch (position) {
                    case 0:
                        fragment = new DuorenFragment(0);
                        break;
                    case 1:
                        fragment = new DuorenFragment(1);
                        break;
                    case 2:
                        fragment = new DuorenFragment(2);
                        break;
                    case 3:
                        fragment = new DuorenFragment(3);
                        break;
                    case 4:
                        fragment = new DuorenFragment(4);
                        break;
                    case 5:
                        fragment = new DuorenFragment(5);
                        break;
                }
                //保存到集合
                mFragments.put(position, fragment);
            }/*else if(i==2){
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
            }else if(i==3){
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
            }*/

        }
        return fragment;
    }
}
