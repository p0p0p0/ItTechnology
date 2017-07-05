package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

/**
 * 全部订单的fragment
 */
public class MainPagerAdater extends FragmentPagerAdapter {
    //标题数组
    private String[] title0;
    private int mCode ;
    private String[] mTitle1;
    private String[] mTitle2;
    int i;
    public MainPagerAdater(FragmentManager fm,int i) {
        super(fm);
        this.i=i;
        mCode = (int) SharedPrefsUtil.get(ECApplication.context, "sote", -1);
        switch (mCode) {
            case 0:
                title0 = CommonUtil.getStringArray(R.array.tab_names);
                break;
            case 1:
                KLog.e("whb","走了1");
                mTitle1 = CommonUtil.getStringArray(R.array.tab_names1);
                break;
            case 2:
                mTitle2 = CommonUtil.getStringArray(R.array.tab_names2);
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
            return FragmentFactory.create(position);
        }else if(i==1){
            KLog.e("whb","走了2");
            return FragmentFactory.create(position);
        }else if(i==2){
            return FragmentFactory.create(position);
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
        if (mCode == 1) {
            return mTitle1.length;
        }
        if (mCode == 2) {
            return mTitle2.length;
        }
        KLog.e("title0.length",title0.length);
        return title0.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      if (mCode == 1) {
            return mTitle1[position];
        }
        if (mCode == 2) {
            return mTitle2[position];
        }
        return title0[position];
    }

}
