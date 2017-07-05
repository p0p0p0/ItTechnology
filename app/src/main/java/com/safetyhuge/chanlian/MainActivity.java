package com.safetyhuge.chanlian;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferenceSettings;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferences;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.BlankFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.ContFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.IncpFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.MineFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.ShaopingCatFragment;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECInitParams;

/**
 * 作者：王海宾 on 2017/5/25 0025 10:20
 * 邮箱：995696826@qq.com
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment[] fragments;
    private ImageView[] tableIvs;
    private TextView[] tvs;
    private LinearLayout ll_shouye, ll_xunjia, ll_main_zx, ll_main_goods, ll_main_my;
    private FragmentManager fm;
    private int currentPage;
    private ImageView mById;
    ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ll_shouye.setOnClickListener(this);
        ll_xunjia.setOnClickListener(this);
        ll_main_zx.setOnClickListener(this);
        ll_main_goods.setOnClickListener(this);
        ll_main_my.setOnClickListener(this);
        //初始化首页
        FragmentTransaction beginTransaction = fm.beginTransaction();
        fragments[0] = new BlankFragment();
        beginTransaction.add(R.id.fl_container_main, fragments[0]);
        initBottomBars();
        beginTransaction.show(fragments[0]);
        beginTransaction.commit();
       tvs[currentPage].setEnabled(false);
        tableIvs[currentPage].setEnabled(false);
        String mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        //login(mUserid);
        KLog.e("ECDevice.isInitialized()",ECDevice.isInitialized());
        //判断SDK是否已经初始化
        if(ECDevice.isInitialized()==true) {
                KLog.e("whb","初始化sdk成功");
        }
    }

    //初始化控件
    @SuppressLint("WrongConstant")
    private void init() {
        tvs = new TextView[5];
        tableIvs = new ImageView[5];
        fragments = new Fragment[5];
        ll_shouye = (LinearLayout) findViewById(R.id.main_sy1);
        ll_xunjia = (LinearLayout) findViewById(R.id.ll_xunjia);
        ll_main_zx = (LinearLayout) findViewById(R.id.ll_main_zx);
        ll_main_goods = (LinearLayout) findViewById(R.id.ll_main_goods);
        ll_main_my = (LinearLayout) findViewById(R.id.ll_main_my);
        tvs[0] = (TextView) findViewById(R.id.tv_main_shouye);
        tvs[1] = (TextView) findViewById(R.id.tv_main_xunjia);
        tvs[2] = (TextView) findViewById(R.id.tv_main_zx);
        tvs[3] = (TextView) findViewById(R.id.tv_main_goods);
        tvs[4] = (TextView) findViewById(R.id.tv_main_my);
        tableIvs[0] = (ImageView) findViewById(R.id.iv_main_shouye);
        tableIvs[1] = (ImageView) findViewById(R.id.iv_main_xunjia);
        tableIvs[2] = (ImageView) findViewById(R.id.iv_main_zx);
        tableIvs[3] = (ImageView) findViewById(R.id.iv_main_goods);
        tableIvs[4] = (ImageView) findViewById(R.id.iv_main_my);
        mById = (ImageView) findViewById(R.id.main_sy);
        fm = getSupportFragmentManager();
        currentPage = 0;
        mById.setVisibility(View.VISIBLE);
        ll_shouye.setVisibility(View.GONE);
       /* tableIvs[currentPage].setEnabled(true);
        tvs[currentPage].setEnabled(true);*/

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        FragmentTransaction beginTransaction = fm.beginTransaction();
        //先把所有的fragment隐藏
        hideAll(beginTransaction);
        initBottomBars();
        switch (v.getId()) {
            case R.id.main_sy1:
                if (currentPage != 0) {
                    currentPage = 0;
                    if (fragments[currentPage] == null) {
                        fragments[currentPage] = new BlankFragment();
                        beginTransaction.add(R.id.fl_container_main, fragments[currentPage]);
                    }
                }
                break;
            case R.id.ll_xunjia:
                if (currentPage != 1) {
                    currentPage = 1;
                    if (fragments[currentPage] == null) {
                        fragments[currentPage] = new IncpFragment();
                        beginTransaction.add(R.id.fl_container_main, fragments[currentPage]);
                    }
                }
                break;
            case R.id.ll_main_zx:
                if (currentPage != 2) {
                    currentPage = 2;
                    if (fragments[currentPage] == null) {
                        fragments[currentPage] = new ContFragment();
                        beginTransaction.add(R.id.fl_container_main, fragments[currentPage]);
                    }
                }
                break;
            case R.id.ll_main_goods:
                if (currentPage != 3) {
                    currentPage = 3;
                    if (fragments[currentPage] == null) {
                        fragments[currentPage] = new ShaopingCatFragment();
                        beginTransaction.add(R.id.fl_container_main, fragments[currentPage]);
                    }
                }
                break;
            case R.id.ll_main_my:
                if (currentPage != 4) {
                    currentPage = 4;
                    if (fragments[currentPage] == null) {
                        fragments[currentPage] = new MineFragment();
                        beginTransaction.add(R.id.fl_container_main, fragments[currentPage]);
                    }
                }
                break;
        }

        if (currentPage == 0) {
            mById.setVisibility(View.VISIBLE);
            ll_shouye.setVisibility(View.GONE);
        } else {
            mById.setVisibility(View.GONE);
            ll_shouye.setVisibility(View.VISIBLE);
        }
        //变为用户选择的页面
        tableIvs[currentPage].setEnabled(true);
        tvs[currentPage].setEnabled(true);
        beginTransaction.show(fragments[currentPage]);
        beginTransaction.commitAllowingStateLoss();
    }

    //隐藏所有fragment
    private void hideAll(FragmentTransaction transaction) {
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
    }
    //初始化底部标题栏
    private void initBottomBars(){
        for (TextView tv:tvs){
            tv.setEnabled(false);
        }
        for (ImageView iv:tableIvs){
            iv.setEnabled(false);
        }
    }
    //下面两个方法为解决重影问题的
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        currentPage = savedInstanceState.getInt("position");
        FragmentTransaction beginTransaction = fm.beginTransaction();

        hideAll(beginTransaction);
        beginTransaction.show(fragments[currentPage]);
        beginTransaction.commitAllowingStateLoss();
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position", currentPage);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /**
     * 处理一些初始化操作
     */
    public void doInitAction() {

    }
    /**
     * 检查是否需要自动登录
     *
     * @return
     */
    private String getAutoRegistAccount() {
        SharedPreferences sharedPreferences = ECPreferences
                .getSharedPreferences();
        ECPreferenceSettings registAuto = ECPreferenceSettings.SETTINGS_REGIST_AUTO;
        String registAccount = sharedPreferences.getString(registAuto.getId(),
                (String) registAuto.getDefaultValue());
        return registAccount;
    }
    //初始化信息后登陆im
    private void login(String  selfId) {
        String appkey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appkey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(mLoginAuthType);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);
        SDKCoreHelper.init(this, ECInitParams.LoginMode.FORCE_LOGIN);
    }
}
