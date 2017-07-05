package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.MainPagerAdater;
import com.safetyhuge.chanlian.safetyhuge.baseview.NoScrollViewPager;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.widget.PagerSlidingTab;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**多人
 * 作者：王海宾 on 2017/4/15 0015 16:08
 * 邮箱：995696826@qq.com
 */

public class PeopleActivity extends FragmentActivity {
    @Bind(R.id.solo_danren)
    NoScrollViewPager mSoloDanren;
    @Bind(R.id.SlidingTab)
    PagerSlidingTab mSlidingTab;
    private NoScrollViewPager viewPager;
    private PagerSlidingTab slidingTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        //ViewPager的adapter
        setContentView(R.layout.activity_solo);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        KLog.e("whb","initView");
        //初始化Viewpager
        viewPager = (NoScrollViewPager) findViewById(R.id.solo_danren);
        //初始化SlidingTab
        slidingTab = (PagerSlidingTab) findViewById(R.id.SlidingTab);
        //获取fragment工厂对象
        MainPagerAdater pagerAdater = new MainPagerAdater(getSupportFragmentManager(),1);
        //填充Viewpager
        viewPager.setAdapter(pagerAdater);
        Intent intent = getIntent();
        int item = intent.getIntExtra("item",0);
        //设置viewpager 显示的页面
        KLog.e("\"MyGoodsActivity.initView:"+item);
        viewPager.setCurrentItem(item,true);
        //绑定viewpager和Indicator
        slidingTab.setViewPager(viewPager);
    }

    public void back_text_view(View view) {
        finish();
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
