package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.SaleGoodsFragmentInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.baseview.NoScrollViewPager;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.widget.PagerSlidingTab;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/17 0017 21:53
 * 邮箱：995696826@qq.com
 */

public class SaleGoodsInfoActivity extends HBaseAct {
    @Bind(R.id.solo_danren)
    NoScrollViewPager mSoloDanren;
    @Bind(R.id.SlidingTab)
    PagerSlidingTab mSlidingTab;
    @Bind(R.id.solo_info)
    TextView mSoloInfo;
    private NoScrollViewPager viewPager;
    private PagerSlidingTab slidingTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        //ViewPager的adapter
        setContentView(R.layout.activity_solo);
       // mSoloInfo.setText("");
        ButterKnife.bind(this);
        mSoloInfo.setText("我的订单-售出");
        initView();
    }

    private void initView() {
        //初始化Viewpager
        viewPager = (NoScrollViewPager) findViewById(R.id.solo_danren);
        viewPager.setOffscreenPageLimit(1);
        //初始化SlidingTab
        slidingTab = (PagerSlidingTab) findViewById(R.id.SlidingTab);
        //获取fragment工厂对象
        SaleGoodsMainPagerAdater pagerAdater = new SaleGoodsMainPagerAdater(getSupportFragmentManager(), 0);
        //填充Viewpager
        viewPager.setAdapter(pagerAdater);
        Intent intent = getIntent();
        int item = intent.getIntExtra("item", 0);
        //设置viewpager 显示的页面
        KLog.e("MyGoodsActivity.initView:" + item);
        viewPager.setCurrentItem(item, true);
        //绑定viewpager和Indicator
        slidingTab.setViewPager(viewPager);
    }

    public void back_text_view(View view) {
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
