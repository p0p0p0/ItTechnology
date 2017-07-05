package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.FrescoImageLoader;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：王海宾 on 2017/5/8 0008 17:30
 * 邮箱：995696826@qq.com
 */
public class CertificateActivity extends Activity {
    @Bind(R.id.interchangger_dianji)
    LinearLayout mInterchanggerDianji;
    @Bind(R.id.sbimage)
    Banner mBanner;
    @Bind(R.id.interchanger_bianhua)
    TextView mInterchangerBianhua;
    @Bind(R.id.interchanger_info)
    TextView mInterchangerInfo;
    private ArrayList<String> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interchanger);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mImages = intent.getStringArrayListExtra("image");
        mBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initview();
    }

    public void initview() {
        mInterchangerInfo.setText(mImages.size()+"");
        mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR).
                setImageLoader(new FrescoImageLoader()).
                setImages(mImages).isAutoPlay(false).
                setBannerAnimation(Transformer.DepthPage).
                setIndicatorGravity(BannerConfig.CENTER).
                setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                }).
                setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                       // KLog.e("position1",position);
                        if (position==0){
                            mInterchangerBianhua.setText(position+1+"");
                        }else if (position==mImages.size()+1){
                            mInterchangerBianhua.setText(position-1+"");
                        }else{
                            mInterchangerBianhua.setText(position+"");
                        }

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        KLog.e("state",state);
                    }
                });
        mBanner.start();
    }

    @OnClick(R.id.interchangger_dianji)
    public void onClick() {
        finish();
        overridePendingTransition(0, 0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}

class ZsImageAdapter extends PagerAdapter {

    ArrayList<String> images;

    public ZsImageAdapter(ArrayList<String> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(ECApplication.context);
        container.addView(iv);// 1. 向ViewPager中添加一个view对象
        Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + images.get(position)).into(iv);
        return iv; // 2. 返回当前添加的view对象
    }
}
