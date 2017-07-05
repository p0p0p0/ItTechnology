package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.RegionInfoFragment.ChargeInfoFragment;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.RegionInfoFragment.CollectInfoFragment;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TechnologyTrainActivity extends HBaseAct {


    @Bind(R.id.technology_button1)
    Button mTechnologyButton1;
    @Bind(R.id.view_1)
    View mView1;
    @Bind(R.id.technology_button2)
    Button mTechnologyButton2;
    @Bind(R.id.view_2)
    View mView2;
    @Bind(R.id.technology)
    FrameLayout mTechnology;
    @Bind(R.id.technolog_layout)
    LinearLayout mTechnologLayout;
    @Bind(R.id.layout_error)
    LinearLayout mLayoutError;
    private Context mContext;
    private boolean b1 = true;
    private boolean b2 = true;
    private ChargeInfoFragment mChargeInfoFragment;
    private CollectInfoFragment mInfoFragment;
    private KProgressHUD mKProgressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology_train);
        mContext = TechnologyTrainActivity.this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        ButterKnife.bind(this);
        List<Fragment> fragments = new ArrayList<>();
        mChargeInfoFragment = new ChargeInfoFragment();
        fragments.add(mChargeInfoFragment);
        FragmentInfo(mChargeInfoFragment, 1, null);
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick({R.id.technology_button1, R.id.technology_button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.technology_button1:
                mView2.setVisibility(View.INVISIBLE);
                mView1.setVisibility(View.VISIBLE);
                if (b1 == true) {
                    List<Fragment> fragments = new ArrayList<>();
                    mChargeInfoFragment = new ChargeInfoFragment();
                    fragments.add(mInfoFragment);
                    FragmentInfo(mChargeInfoFragment, 0, fragments);
                }
                b1 = false;
                b2 = true;
                break;
            case R.id.technology_button2:
                mView1.setVisibility(View.INVISIBLE);
                mView2.setVisibility(View.VISIBLE);
                //   CollectInfoFragment
                if (b2 == true) {
                    List<Fragment> fragments = new ArrayList<>();
                    mInfoFragment = new CollectInfoFragment();
                    fragments.add(mChargeInfoFragment);
                    FragmentInfo(mInfoFragment, 0, fragments);
                }

                b1 = true;
                b2 = false;
                break;
        }
    }

    private void FragmentInfo(Fragment fragment, int a, List<Fragment> fragments) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.technology, fragment);
        transaction.commit();
        if (a == 0) {
            KLog.e("size", fragments.size());
            for (Fragment fragment1 : fragments) {
                if (fragment1 != null) {
                    transaction.remove(fragment1);
                    KLog.e("fragments", fragment1.toString());
                }
            }
        }
    }


    @Override
    protected void onResume() {
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
