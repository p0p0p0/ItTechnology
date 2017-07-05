package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.FuWuBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.FuJInSSActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.HelpFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.MinesFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.ReleaseActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.ServeFragment;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.widget.NearByInfo;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

public class LeaseEquipmentActivity extends CheckPermissionsActivity {
    @Bind(R.id.zhaofuwu)
    RadioButton mZhaofuwu;
    @Bind(R.id.qubangmang)
    RadioButton mQubangmang;
    @Bind(R.id.fabu)
    RadioButton mFabu;
    @Bind(R.id.critic_gwc)
    RadioButton mCriticGwc;
    int count = 1;
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    @Bind(R.id.fuwu_soushuo)
    LinearLayout mFuwuSoushuo;
    @Bind(R.id.geren_layout)
    TextView mGerenLayout;
    private boolean mB;
    private boolean mA = true;
    private boolean mA1 = true;
    private boolean mA2 = true;
    private String mUserid;
    private List<NearByInfo.DataBean> mDataBean;
    private List<FuWuBean.DataBean> mData;
    private ServeFragment mServeFragment;
    BottomSheetBehavior behavior = null;
    private HelpFragment mMServeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_fujin);
        ButterKnife.bind(this);
        NewWork();
        mA = false;
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        SharedPrefsUtil.remove(ECApplication.context, "log");
        mServeFragment = new ServeFragment(LeaseEquipmentActivity.this);
        showFirstFragment(mServeFragment);
        mServeFragment.setListener(new ServeFragment.OnListener() {
            @Override
            public void onItemClick(BottomSheetBehavior b) {
                behavior = b;
            }
        });
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        mB = (boolean) SharedPrefsUtil.get(ECApplication.context, "release", true);
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        if (mB == false) {
            if (mA == false) {
                mZhaofuwu.setChecked(true);
                count++;
            } else if (mA1 == false) {
                mQubangmang.setChecked(true);
                count2++;
            } else if (mA2 == false) {
                mCriticGwc.setChecked(true);
                count3++;
            }
        }
        if (mUserid != "") {
            boolean b = (boolean) SharedPrefsUtil.get(ECApplication.context, "log", false);
            if (b == true) {
                MinesFragment mineFragment = new MinesFragment();
                showFirstFragment(mineFragment);
            }
            SharedPrefsUtil.remove(ECApplication.context, "log");
        }
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @OnClick({R.id.zhaofuwu, R.id.qubangmang, R.id.fabu, R.id.critic_gwc})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhaofuwu:
                mFuwuSoushuo.setVisibility(View.VISIBLE);
                mGerenLayout.setVisibility(View.GONE);
                mA1 = true;
                mA2 = true;
                mA = false;
                if (count == 0) {
                    mServeFragment = new ServeFragment(LeaseEquipmentActivity.this);
                    showFirstFragment(mServeFragment);
                    count++;
                }
                count1 = 0;
                count2 = 0;
                count3 = 0;
                break;
            case R.id.qubangmang:
                mFuwuSoushuo.setVisibility(View.VISIBLE);
                mGerenLayout.setVisibility(View.GONE);
                mA1 = false;
                mA = true;
                mA2 = true;
                if (count1 == 0) {
                    mMServeFragment = new HelpFragment(LeaseEquipmentActivity.this);
                    showFirstFragment(mMServeFragment);
                    mMServeFragment.setListener(new HelpFragment.OnListener() {
                        @Override
                        public void onItemClick(BottomSheetBehavior be) {
                            behavior = be;
                        }
                    });
                    count1++;
                }
                count = 0;
                count2 = 0;
                count3 = 0;
                break;
            case R.id.fabu:
                mFuwuSoushuo.setVisibility(View.VISIBLE);
                mGerenLayout.setVisibility(View.GONE);
                startActivity(new Intent(this, ReleaseActivity.class));
                break;
            case R.id.critic_gwc:
                mFuwuSoushuo.setVisibility(View.GONE);
                mGerenLayout.setVisibility(View.VISIBLE);
                mA2 = false;
                mA = true;
                mA1 = true;
                if (mUserid != "") {
                    if (count3 == 0) {
                        MinesFragment mMineFragment = new MinesFragment();
                        showFirstFragment(mMineFragment);
                        count3++;
                    }
                } else {
                    showNormalDialog();
                }
                count1 = 0;
                count = 0;
                count2 = 0;
                break;
        }
    }

    private void showFirstFragment(Fragment fragment) {
        // 1 获取Fragment布局管理器
        FragmentManager manager = getSupportFragmentManager();
        // 2 通过manager开启事务操作
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container1, fragment);
        //把当前的事物加入到回退栈
        transaction.addToBackStack(null);
        // 提交事务
        transaction.commit();
    }

    public void back_text_view(View view) {
        finish();
    }


    private void showNormalDialog() {
        new CBDialogBuilder(this)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle("提示")
                .setMessage("请先登录")
                .setConfirmButtonText("确定")
                .setCancelButtonText("取消").setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                switch (whichBtn) {
                    case BUTTON_CONFIRM:
                        startActivity(new Intent(ECApplication.context, LoginsActivity.class));
                        break;
                    case BUTTON_CANCEL:
                        break;
                    default:
                        break;
                }
            }
        })
                .setDialogAnimation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                .create()
                .show();
    }

    private void FragmentInfo(android.app.Fragment fragment, int a, List<android.app.Fragment> fragments) {
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container1, fragment);
        transaction.commit();
        if (a == 0) {
            KLog.e("size", fragments.size());
            for (android.app.Fragment fragment1 : fragments) {
                if (fragment1 != null) {
                    transaction.remove(fragment1);
                    KLog.e("fragments", fragment1.toString());
                }
            }
        }
    }

    @OnClick(R.id.fuwu_soushuo)
    public void onClick() {
        Intent intent = new Intent(this, FuJInSSActivity.class);
        startActivity(intent);
    }

    public void NewWork() {
        mDataBean = new ArrayList<>();
        OkGo.post(RequestAddress.HOST + "goodsfuwu.php").execute(new JsonCallback<FuWuBean>() {
            @Override
            public void onSuccess(FuWuBean fuWuBean, Call call, Response response) {
                mData = fuWuBean.getData();
                for (FuWuBean.DataBean datum : mData) {
                    final NearByInfo.DataBean dataBean = new NearByInfo.DataBean();
                    if (datum.getLat() != null && datum.getLng() != null && datum.getUser_pic() != null) {
                        dataBean.setLatitude(datum.getLat());
                        dataBean.setLongitude(datum.getLng());
                        if (datum.getPhone() != null && !datum.getPhone().equals("") && !datum.getPhone().equals("0")) {
                            //电话
                            dataBean.setPhone(datum.getPhone());
                        }
                        //头像
                        dataBean.setMedal(RequestAddress.IMAGE1 + datum.getUser_pic().toString().replace("../", ""));
                        //图片
                        if (datum.getPic() != null) {
                            dataBean.setUrl(RequestAddress.IMAGE1 + datum.getPic().replace("../", ""));
                        }
                        dataBean.setClassX(datum.getIndus_id());
                        //标题
                        dataBean.setTitle(datum.getTitle());
                        //id
                        dataBean.setId(datum.getService_id());
                        //内容
                        dataBean.setType(datum.getContent());
                        //金钱
                        dataBean.setMoney(datum.getPrice());
                        //名字
                        dataBean.setName(datum.getUsername());
                       /* //服务id
                        intent.putExtra("sid", data.getId());
                        //图片
                        intent.putExtra("image", data.getUrl());
                        //标题
                        intent.putExtra("title", data.getTitle());
                        //钱数
                        intent.putExtra("money", data.getMoney());
                        //头像图片
                        intent.putExtra("useriamge", data.getMedal());
                        //名称
                        intent.putExtra("username", data.getName());
                        //内容
                        intent.putExtra("content", data.getType());
                        //电话
                        intent.putExtra("phone", data.getPhone());*/
                        mFuwuSoushuo.setClickable(true);
                        mDataBean.add(dataBean);
                    }
                }
                KLog.e("size", mDataBean.size());
            }
        });
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        KLog.e("behavior", behavior + "");
//            KLog.e("behavior",behavior.getState());

        if (behavior != null) {
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            if (behavior.getState() == 5) {
                finish();
            }
            behavior = null;
        } else {

            KLog.e("走了");
            finish();
        }
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
