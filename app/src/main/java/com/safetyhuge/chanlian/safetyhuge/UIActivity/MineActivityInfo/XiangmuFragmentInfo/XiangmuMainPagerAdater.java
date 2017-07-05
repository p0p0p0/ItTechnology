package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.XiangmuFragmentInfo;

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
public class XiangmuMainPagerAdater extends FragmentPagerAdapter {
    //标题数组
    private String[] title0;
    private int mCode;
    int i;

    public XiangmuMainPagerAdater(FragmentManager fm, int i) {
        super(fm);
        this.i = i;
        mCode = (int) SharedPrefsUtil.get(ECApplication.context, "xiangmu", 0);
        switch (mCode) {
            case 0:
                title0 = CommonUtil.getStringArray(R.array.goods_names3);
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
            return XiangmuFragmentFactory.create(position);
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

        return title0.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mCode == 0) {
            return title0[position];
        }
        return title0[position];
    }

}
