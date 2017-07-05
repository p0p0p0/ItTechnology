package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.GoodsFragmentInfo;

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
public class GoodsMainPagerAdater extends FragmentPagerAdapter {
    //标题数组
    private String[] title0;
    private int mCode;
    private String[] mTitle1;
    private String[] mTitle2;
    private String[] mTitle3;
    private String[] mTitle4;
    private String[] mTitle5;
    int i;

    public GoodsMainPagerAdater(FragmentManager fm, int i) {
        super(fm);
        this.i = i;
        mCode = (int) SharedPrefsUtil.get(ECApplication.context, "goods", 0);
        switch (mCode) {
            case 0:
                title0 = CommonUtil.getStringArray(R.array.goods_names);
            case 1:
                mTitle1 = CommonUtil.getStringArray(R.array.goods_names1);
                break;
            case 2:
                mTitle2 = CommonUtil.getStringArray(R.array.goods_names2);
                break;
            case 3:
                mTitle3 = CommonUtil.getStringArray(R.array.goods_names3);
                break;
            case 4:
                mTitle4 = CommonUtil.getStringArray(R.array.goods_names4);
                break;
            case 5:
                mTitle5 = CommonUtil.getStringArray(R.array.goods_names5);
                break;
        }
    }


    /**
     * /*  * 返回每一页需要的fragment
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if (i == 0) {
            return GoodsFragmentFactory.create(position);
        } else if (i == 1) {
            return GoodsFragmentFactory.create(position);
        } else if (i == 2) {
            return GoodsFragmentFactory.create(position);
        } else if (i == 3) {
            return GoodsFragmentFactory.create(position);
        } else if (i == 4) {
            return GoodsFragmentFactory.create(position);
        } else if (i == 5) {
            return GoodsFragmentFactory.create(position);
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
        if (mCode == 0) {
            return title0.length;
        }
        if (mCode == 1) {
            return mTitle1.length;
        }
        if (mCode == 2) {
            return mTitle2.length;
        }
        if (mCode == 3) {
            return mTitle3.length;
        }
        if (mCode == 4) {
            return mTitle4.length;
        }
        if (mCode == 5) {
            return mTitle5.length;
        }
        KLog.e("title0.length", title0.length);
        return title0.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mCode == 0) {
            return title0[position];
        }
        if (mCode == 1) {
            return mTitle1[position];
        }
        if (mCode == 2) {
            return mTitle2[position];
        }
        if (mCode == 3) {
            return mTitle3[position];
        }
        if (mCode == 4) {
            return mTitle4[position];
        }
        if (mCode == 5) {
            return mTitle5[position];
        }
        return title0[position];
    }

}
