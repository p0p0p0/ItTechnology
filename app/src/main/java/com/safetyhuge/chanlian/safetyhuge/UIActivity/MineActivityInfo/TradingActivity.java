package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.os.Bundle;
import android.view.View;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.JYjiluBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.JYjiluAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/18 0018 10:15
 * 邮箱：995696826@qq.com
 */
public class TradingActivity extends HBaseAct {
    @Bind(R.id.my_lsitview)
    MyListView mMyLsitview;
    private String mUserid;
    private KProgressHUD mMProgressHUD;
    private List<JYjiluBean.DataBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading);
        ButterKnife.bind(this);
        mMProgressHUD = KProgressHUD.create(TradingActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        NetWork();
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void NetWork() {
        OkGo.post(RequestAddress.HOST + RequestAddress.JYJJ).params("uid", mUserid).execute(new JsonCallback<JYjiluBean>() {
            @Override
            public void onSuccess(JYjiluBean jYjiluBean, Call call, Response response) {
                mMProgressHUD.dismiss();
                mData = jYjiluBean.getData();
                if (mData!=null){
                    mMyLsitview.setAdapter(new JYjiluAdapter(mData));
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}

