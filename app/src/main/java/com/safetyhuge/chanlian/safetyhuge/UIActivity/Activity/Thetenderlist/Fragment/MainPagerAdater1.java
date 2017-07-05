package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;

/**
 * 全部订单的fragment
 */
public class MainPagerAdater1 extends FragmentPagerAdapter {
    //标题数组
    private String[] title0;
    private int mCode ;
    private String[] mTitle1;
    private String[] mTitle2;
    int i;
    public MainPagerAdater1(FragmentManager fm, int i) {
        super(fm);
        this.i=i;
        mCode = (int) SharedPrefsUtil.get(ECApplication.context, "sote", 0);
        switch (mCode) {
            case 0:
                mTitle2 = CommonUtil.getStringArray(R.array.tab_names2);
                break;
            case 1:
                mTitle1 = CommonUtil.getStringArray(R.array.tab_names2);
                break;
        }
    }



    /**
     /*  * 返回每一页需要的fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if (i==0){
            return FragmentFactory1.create(position);
        }else if(i==1){
            return FragmentFactory1.create(position);
        }
        return null;
    }


    /**
     * 返回字符串的长度
     *
     * @return
     */
    @Override
    public int getCount() {
        if (mCode==0){
            return mTitle2.length;
        }else if (mCode==1){
            return mTitle1.length;
        }
        return -1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle2[position];
    }

}
