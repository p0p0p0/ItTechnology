package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CleanMessageUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.umeng.analytics.MobclickAgent;
import com.yuntongxun.ecsdk.ECDevice;
import com.zhl.cbdialog.CBDialogBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by Chinalink on 2017/3/25 0025.
 */

public class SettingActivity extends HBaseAct {
    @Bind(R.id.layout_personage)
    LinearLayout mLayoutPersonage;
    @Bind(R.id.layout_paypassword)
    LinearLayout mLayoutPaypassword;
    @Bind(R.id.layout_password)
    LinearLayout mLayoutPassword;
    @Bind(R.id.layout_firm)
    LinearLayout mLayoutFirm;
    @Bind(R.id.layout_cache)
    LinearLayout mLayoutCache;
    @Bind(R.id.bt_quit)
    Button mBtQuit;
    @Bind(R.id.app_cash)
    TextView mAppCash;
    private Intent intent;
    private String mUserid;
    KProgressHUD  mKProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        intent = new Intent();
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        ECApplication.addActivity(SettingActivity.this);
        try {
            String totalCacheSize = CleanMessageUtil.getTotalCacheSize(this);
            mAppCash.setText("缓存大小("+totalCacheSize+")");
        } catch (Exception e) {
            e.printStackTrace();
        }
          mKProgress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("清理中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }

    @OnClick({R.id.layout_personage, R.id.layout_paypassword, R.id.layout_password, R.id.layout_firm, R.id.layout_cache, R.id.bt_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            //个人资料
            case R.id.layout_personage:
                if (mUserid != "") {
                    startActAnim(intent.setClass(SettingActivity.this, PersonageActivity.class));
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //支付密码
            case R.id.layout_paypassword:
                if (mUserid != "") {
                    startActAnim(intent.setClass(SettingActivity.this, PaypasswordActivity.class));
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //登录密码
            case R.id.layout_password:
                if (mUserid != "") {
                    startActAnim(intent.setClass(SettingActivity.this, AmendasswordActivity.class));
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //企业认证
            case R.id.layout_firm:
                if (mUserid != "") {
                    startActAnim(intent.setClass(SettingActivity.this, FirmActivity.class));
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //清理缓存
            case R.id.layout_cache:
                HBaseAct.showKProgressHUD("加载中....",this);
                if (CleanMessageUtil.clearAllCache(this)){
                    HBaseAct.dismissProgressHUD();
                    Toast.makeText(this, "清理图片缓存成功", Toast.LENGTH_SHORT).show();
                    try {
                        String totalCacheSize = CleanMessageUtil.getTotalCacheSize(this);
                        mAppCash.setText("缓存大小("+totalCacheSize+")");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            //退出登录
            case R.id.bt_quit:
                if (mUserid != "") {
                    showNormalDialog();
                } else {
                    Toasty.error(SettingActivity.this, "当前没有账号登录", Toast.LENGTH_SHORT, true).show();
                }
                break;
        }
    }

    private void showNormalDialog() {
        new CBDialogBuilder(this)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle("提示")
                .setMessage("您确定要退出吗?")
                .setConfirmButtonText("确定")
                .setCancelButtonText("取消").setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                switch (whichBtn) {
                    case BUTTON_CONFIRM:
                        if (mUserid != "") {
                            SharedPrefsUtil.clear(ECApplication.context);
                            Toasty.success(context, "退出登录成功!", Toast.LENGTH_SHORT, true).show();
                            exitAct();
                            ECDevice.logout(new ECDevice.OnLogoutListener() {
                                @Override
                                public void onLogout() {
                                    ECDevice.unInitial();
                                    SDKCoreHelper.release();
                                }
                            });
                        }
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

    public void back_text_view(View view) {
        exitAct();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
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
