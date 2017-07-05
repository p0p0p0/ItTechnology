package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServicePingjia1;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServicePingjia2;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**我评价的
 * 作者：王海宾 on 2017/5/17 0017 20:44
 * 邮箱：995696826@qq.com
 */
public class ServiceEvaluateActivity extends HBaseAct{
    @Bind(R.id.technology_button1)
    Button mTechnologyButton1;
    @Bind(R.id.view_1)
    View mView1;
    @Bind(R.id.technology_button2)
    Button mTechnologyButton2;
    @Bind(R.id.view_2)
    View mView2;
    @Bind(R.id.serviceindent)
    FrameLayout mServiceindent;
    @Bind(R.id.title)
    TextView mTitle;
    private KProgressHUD mKProgressHUD;
    private ServicePingjia1 mServiceIndent1;
    private int count = 0;
    private int count2 = 0;
    private ServicePingjia2 mServiceIndent2;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceindent_layout);
        Intent intent = getIntent();
        //用户id
        String mUserid = (String) get(ECApplication.context, "UserUid", "");
        //用户名称
        String mUsername = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        netWork();
        ButterKnife.bind(this);
        mTitle.setText("我的评价");
        mTechnologyButton1.setText("发出的评价");
        mTechnologyButton2.setText("收到的评价");
        List<Fragment> fragments = new ArrayList<>();
        mServiceIndent1 = new ServicePingjia1();
        fragments.add(mServiceIndent1);
        FragmentInfo(mServiceIndent1, 1, null);
        count++;
    }

    private void netWork() {
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.technology_button1, R.id.technology_button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.technology_button1:
                mView2.setVisibility(View.INVISIBLE);
                mView1.setVisibility(View.VISIBLE);
                if (count == 0) {
                    mServiceIndent1 = new ServicePingjia1();
                    List<Fragment> fragments = new ArrayList<>();
                    fragments.add(mServiceIndent2);
                    FragmentInfo(mServiceIndent1, 0, fragments);
                }
                count++;
                count2 = 0;
                break;
            case R.id.technology_button2:
                mView1.setVisibility(View.INVISIBLE);
                mView2.setVisibility(View.VISIBLE);
                if (count2 == 0) {
                    mServiceIndent2 = new ServicePingjia2();
                    List<Fragment> fragments = new ArrayList<>();
                    fragments.add(mServiceIndent1);
                    FragmentInfo(mServiceIndent2, 0, fragments);
                }
                count2++;
                count = 0;
                break;
        }
    }

    public void back_text_view(View view) {
        finish();
    }

    private void FragmentInfo(Fragment fragment, int a, List<Fragment> fragments) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.serviceindent, fragment);
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
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
