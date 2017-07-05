package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ReleaseBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.RelesaseFragmentInfo.CommonTaskFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.RelesaseFragmentInfo.IssueTaskFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.RelesaseFragmentInfo.PluralTaskFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.RelesaseFragmentInfo.ReserveTaskFragment;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.StringUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 发布项目
 * 2017.3.31
 */

public class ReleaseProjectActivity extends HBaseAct {
    Context mContext;
    KProgressHUD mKProgressHUD;
    //单人悬赏
    @Bind(R.id.release_bt1)
    Button mReleaseBt1;
    @Bind(R.id.release_bt2)
    Button mReleaseBt2;
    @Bind(R.id.release_bt3)
    Button mReleaseBt3;
    @Bind(R.id.release_bt4)
    Button mReleaseBt4;
    @Bind(R.id.releaseinfo_image)
    ImageView mReleaseinfoImage;
    @Bind(R.id.relesaseinfo_title)
    TextView mRelesaseinfoTitle;
    @Bind(R.id.relesaseinfo_explain_text)
    TextView mRelesaseinfoExplainText;
    private String mId;
    private boolean mA1 = true;
    private int count = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private List<ReleaseBean.DataBean> mBean;
    private IssueTaskFragment mIssueTaskFragment;
    private PluralTaskFragment mPluralTaskFragment;
    private CommonTaskFragment mCommonTaskFragment;
    private ReserveTaskFragment mReserveFragment;
    public static ReleaseProjectActivity instance=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_relesase);
        Intent intent = getIntent();
        mId = intent.getStringExtra("taskid");
        KLog.e("mid", mId);
        mContext = ReleaseProjectActivity.this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        alents();
        ButterKnife.bind(this);
        mIssueTaskFragment = new IssueTaskFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mPluralTaskFragment);
        fragments.add(mCommonTaskFragment);
        fragments.add(mReserveFragment);
        FragmentInfo(R.id.fragment_container, mIssueTaskFragment, 1, null);
        count++;
        instance=this;
    }


    @OnClick({
            R.id.release_bt1, R.id.release_bt2, R.id.release_bt3, R.id.release_bt4,
            R.id.releaseinfo_explain
    })
    public void onClick(View view) {
        switch (view.getId()) {
            //单人悬赏
            case R.id.release_bt1:
                mReleaseBt1.setBackgroundColor(CommonUtil.getColor(R.color.jue));
                mReleaseBt1.setTextColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt2.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt2.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mReleaseBt3.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt3.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mReleaseBt4.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt4.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                if (mRelesaseinfoExplainText.getVisibility() == View.VISIBLE) {
                    mRelesaseinfoExplainText.setVisibility(View.GONE);
                    mReleaseinfoImage.setImageResource(R.drawable.icon_px_sx);
                    mA1 = true;
                }
                mRelesaseinfoTitle.setText("什么是单人悬赏?");
                mRelesaseinfoExplainText.setText(StringUtils.Transition(mBean.get(0).getDesc()));
                if (count == 0) {
                    mIssueTaskFragment = new IssueTaskFragment();
                    List<Fragment> fragments = new ArrayList<>();
                    fragments.add(mPluralTaskFragment);
                    fragments.add(mCommonTaskFragment);
                    fragments.add(mReserveFragment);
                    FragmentInfo(R.id.fragment_container, mIssueTaskFragment, 0, fragments);
                }
                count++;
                count2 = 0;
                count3 = 0;
                count4 = 0;
                break;
            case R.id.release_bt2:
                mReleaseBt2.setBackgroundColor(CommonUtil.getColor(R.color.jue));
                mReleaseBt2.setTextColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt1.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt1.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mReleaseBt3.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt3.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mReleaseBt4.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt4.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                if (mRelesaseinfoExplainText.getVisibility() == View.VISIBLE) {
                    mRelesaseinfoExplainText.setVisibility(View.GONE);
                    mReleaseinfoImage.setImageResource(R.drawable.icon_px_sx);
                    mA1 = true;
                }
                mRelesaseinfoTitle.setText("什么是多人悬赏?");
                mRelesaseinfoExplainText.setText(StringUtils.Transition(mBean.get(1).getDesc()));
                if (count2 == 0) {
                    mPluralTaskFragment = new PluralTaskFragment();
                    List<Fragment> fragments = new ArrayList<>();
                    fragments.add(mIssueTaskFragment);
                    fragments.add(mCommonTaskFragment);
                    fragments.add(mReserveFragment);
                    FragmentInfo(R.id.fragment_container, mPluralTaskFragment, 0, fragments);
                }
                count = 0;
                count2++;
                count3 = 0;
                count4 = 0;
                break;
            case R.id.release_bt3:
                mReleaseBt3.setBackgroundColor(CommonUtil.getColor(R.color.jue));
                mReleaseBt3.setTextColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt1.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt1.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mReleaseBt2.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt2.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mReleaseBt4.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt4.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                if (mRelesaseinfoExplainText.getVisibility() == View.VISIBLE) {
                    mRelesaseinfoExplainText.setVisibility(View.GONE);
                    mReleaseinfoImage.setImageResource(R.drawable.icon_px_sx);
                    mA1 = true;
                }
                mRelesaseinfoTitle.setText("什么是普通招标?");
                mRelesaseinfoExplainText.setText(StringUtils.Transition(mBean.get(2).getDesc()));
                if (count3 == 0) {
                    mCommonTaskFragment = new CommonTaskFragment();
                    List<Fragment> fragments = new ArrayList<>();
                    fragments.add(mIssueTaskFragment);
                    fragments.add(mPluralTaskFragment);
                    fragments.add(mReserveFragment);
                    FragmentInfo(R.id.fragment_container, mCommonTaskFragment, 0, fragments);
                }
                count = 0;
                count3++;
                count2 = 0;
                count4 = 0;
                break;
            case R.id.release_bt4:
                mReleaseBt4.setBackgroundColor(CommonUtil.getColor(R.color.jue));
                mReleaseBt4.setTextColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt1.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt1.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mReleaseBt2.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt2.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mReleaseBt3.setBackgroundColor(CommonUtil.getColor(R.color.rippelColor));
                mReleaseBt3.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                if (mRelesaseinfoExplainText.getVisibility() == View.VISIBLE) {
                    mRelesaseinfoExplainText.setVisibility(View.GONE);
                    mReleaseinfoImage.setImageResource(R.drawable.icon_px_sx);
                    mA1 = true;
                }
                mRelesaseinfoTitle.setText("什么是订金招标?");
                KLog.e("str", StringUtils.Transition(mBean.get(3).getDesc()));
                mRelesaseinfoExplainText.setText(StringUtils.Transition(mBean.get(3).getDesc()));
                if (count4 == 0) {
                    mReserveFragment = new ReserveTaskFragment();
                    ArrayList<Fragment> fragments = new ArrayList<>();
                    fragments.add(mIssueTaskFragment);
                    fragments.add(mPluralTaskFragment);
                    fragments.add(mCommonTaskFragment);
                    FragmentInfo(R.id.fragment_container, mReserveFragment, 0, fragments);
                }
                count = 0;
                count2 = 0;
                count3 = 0;
                count4++;
                break;
            //项目说明
            case R.id.releaseinfo_explain:
                if (mA1 == true) {
                    mReleaseinfoImage.setImageResource(R.drawable.icon_px_sx_up);
                    mRelesaseinfoExplainText.setVisibility(View.VISIBLE);
                } else {
                    mReleaseinfoImage.setImageResource(R.drawable.icon_px_sx);
                    mRelesaseinfoExplainText.setVisibility(View.GONE);
                }
                mA1 = !mA1;
                break;
        }
    }

    private void FragmentInfo(@IdRes int containerViewId, Fragment fragment, int a, List<Fragment> fragments) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(containerViewId, fragment);
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

    private void alents() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskModelInfo");
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<ReleaseBean>() {
            @Override
            public void onSuccess(ReleaseBean releaseBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mBean = releaseBean.getData();
                String danren = mBean.get(0).getName();
                String duoren = mBean.get(1).getName();
                String putong = mBean.get(2).getName();
                String dingjin = mBean.get(3).getName();
                mReleaseBt1.setText(danren.substring(0,2)+"\n"+danren.substring(2));
                mReleaseBt2.setText(duoren.substring(0,2)+"\n"+duoren.substring(2));
                mReleaseBt3.setText(putong.substring(0,2)+"\n"+putong.substring(2));
                mReleaseBt4.setText(dingjin.substring(0,2)+"\n"+dingjin.substring(2));
                String a = mBean.get(0).getId();
                SharedPrefsUtil.put(ECApplication.context,"xs01",a);
                String a1 = mBean.get(1).getId();
                SharedPrefsUtil.put(ECApplication.context,"xs02",a1);
                String a2 = mBean.get(2).getId();
                SharedPrefsUtil.put(ECApplication.context,"xs03",a2);
                String a3 = mBean.get(3).getId();
                SharedPrefsUtil.put(ECApplication.context,"xs04",a3);
                mRelesaseinfoExplainText.setText(StringUtils.Transition(mBean.get(0).getDesc()));
            }
        });
    }


    public void back_text_view(View view) {
        exitAct();
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
