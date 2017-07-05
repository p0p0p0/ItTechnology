package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Chinalink on 2017/3/27 0027.
 */

public class GoodsAdapter extends PagerAdapter {
    private ArrayList<View> viewContainter;
    public GoodsAdapter(ArrayList<View> viewContainter) {
        this.viewContainter = viewContainter;
    }

    @Override
    public int getCount() {
        return viewContainter.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方推荐写法
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewContainter.get(position));//添加页卡
        return viewContainter.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewContainter.get(position));//删除页卡
    }
}
