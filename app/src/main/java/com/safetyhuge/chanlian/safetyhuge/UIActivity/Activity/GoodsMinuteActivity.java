package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsXgBean;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.LauncherActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsFragment.GoodsDetailsFragment;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsFragment.GoodsEvaluateFragment;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.yuntongxun.ecsdk.ECInitParams;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * Created by Chinalink on 2017/3/27 0027.
 */

public class GoodsMinuteActivity extends HBaseAct {
    @Bind(R.id.bt_chanpin)
    Button mBtChanpin;
    @Bind(R.id.bt_pingjia)
    Button mBtPingjia;
    @Bind(R.id.goodsm_xian)
    View mGoodsmXian;
    @Bind(R.id.goodsm_xian1)
    View mGoodsmXian1;
    @Bind(R.id.goodsm_flayout)
    FrameLayout mGoodsmFlayout;
    @Bind(R.id.goods_im)
    ImageView mGoodsIm;
    private Intent intent;
    private String mGoodsid;
    private String mUserid;
    public boolean b = true;
    private List<GoodsBean.DataBean.MarkInfoBean> mMarkInfo;
    private List<GoodsBean.DataBean.MarkInfoBean.AidinfoBean> mAidinfo;
    private GoodsEvaluateFragment mEvaluateFragment;
    private GoodsDetailsFragment mGoodsDetailsFragment;
    private GoodsBean.DataBean mData;
    private List<GoodsBean.DataBean.MarkInfoBean> mMark_info;
    private KProgressHUD mKProgressHUD;
    private List<GoodsXgBean.DataBean> mDataBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsm);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        intent = getIntent();
        mKProgressHUD = KProgressHUD.create(GoodsMinuteActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mGoodsid = intent.getStringExtra("goodsid");
        KLog.e("goodsid", mGoodsid);
        KLog.e("idid", mUserid);
        if (mData == null) {
            NetWork(mGoodsid);
        }
        mGoodsmXian.setVisibility(View.VISIBLE);
    }


    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick({R.id.bt_chanpin, R.id.bt_pingjia})
    public void onClick(View view) {
        switch (view.getId()) {
            //商品详细
            case R.id.bt_chanpin:
                mGoodsmXian1.setVisibility(View.INVISIBLE);
                mGoodsmXian.setVisibility(View.VISIBLE);
                mGoodsDetailsFragment = new GoodsDetailsFragment(mGoodsid, mData, mMark_info, GoodsMinuteActivity.this);
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(mEvaluateFragment);
                FragmentInfo(R.id.goodsm_flayout, mGoodsDetailsFragment, 0, fragments);
                break;
            //商品评价
            case R.id.bt_pingjia:
                mGoodsmXian.setVisibility(View.INVISIBLE);
                mGoodsmXian1.setVisibility(View.VISIBLE);
                mEvaluateFragment = new GoodsEvaluateFragment(mGoodsid, mMark_info);
                List<Fragment> fragments1 = new ArrayList<>();
                fragments1.add(mGoodsDetailsFragment);
                FragmentInfo(R.id.goodsm_flayout, mEvaluateFragment, 0, fragments1);
                break;
        }
    }

    //商品详细 682
    public void NetWork(String goodsid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsDetails");
        hashMap.put("uid", mUserid);
        hashMap.put("sid", goodsid);
        KLog.e("hashmap", hashMap);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<GoodsBean>() {
            @Override
            public void onSuccess(GoodsBean goodsBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mData = goodsBean.getData();
                if (mData != null) {
                    String username = mData.getUsername();
                    mMark_info = goodsBean.getData().getMark_info();
                    mGoodsDetailsFragment = new GoodsDetailsFragment(mGoodsid, mData, mMark_info, GoodsMinuteActivity.this);
                    List<Fragment> fragments = new ArrayList<>();
                    fragments.add(mEvaluateFragment);
                    FragmentInfo(R.id.goodsm_flayout, mGoodsDetailsFragment, 0, fragments);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }
        });
    }

    public void onResume() {
        super.onResume();
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void TiHuan() {
        mGoodsmXian.setVisibility(View.INVISIBLE);
        mGoodsmXian1.setVisibility(View.VISIBLE);
        mEvaluateFragment = new GoodsEvaluateFragment(mGoodsid, mMark_info);
        List<Fragment> fragments1 = new ArrayList<>();
        fragments1.add(mGoodsDetailsFragment);
        FragmentInfo(R.id.goodsm_flayout, mEvaluateFragment, 0, fragments1);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }

    @OnClick(R.id.goods_im)
    public void onClick() {
        if (mUserid != "") {
            login(mUserid);
        } else {
            showNormalDialog();
        }
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
                        startActivity(new Intent(GoodsMinuteActivity.this, LoginsActivity.class));
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
    //初始化信息后登陆im
    private void login(String  selfId) {
        ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
        String appkey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appkey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(mLoginAuthType);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);
        SDKCoreHelper.init(this, ECInitParams.LoginMode.FORCE_LOGIN);
        Intent intent = new Intent(this, LauncherActivity.class);
        intent.putExtra("launcher_from", 1);
        // 注册成功跳转
        startActivity(intent);
    }
}
