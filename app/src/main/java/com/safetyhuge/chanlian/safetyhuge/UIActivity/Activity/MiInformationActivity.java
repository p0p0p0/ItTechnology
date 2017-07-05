package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.MsgBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/23 0023 18:56
 * 邮箱：995696826@qq.com
 */

public class MiInformationActivity extends HBaseAct {
    KProgressHUD mKProgressHUD;
    Context mContext;
    @Bind(R.id.mimsg_content)
    TextView mMimsgContent;
    @Bind(R.id.mimsg_time)
    TextView mMimsgTime;
    @Bind(R.id.mimsg_layout1)
    LinearLayout mMimsgLayout1;
    @Bind(R.id.mimsg_layout2)
    LinearLayout mMimsgLayout2;
    private String mUserid;
    private List<MsgBean.DataBean> mData;

    @Override
    protected void onCreate(Bundle arg0) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(arg0);
        setContentView(R.layout.activity_mimsg);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        ButterKnife.bind(this);
        mContext = this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        NetWork();
    }

    @OnClick({R.id.mimsg_layout1, R.id.mimsg_layout2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mimsg_layout1:
                Intent intent = new Intent(mContext, MinInfoActivity.class);
                intent.putExtra("id",mUserid);
                startActivity(intent);
                break;
            case R.id.mimsg_layout2:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
    public void NetWork() {
        OkGo.post(RequestAddress.HOST + "xiaoxi.php").params("to_uid", mUserid).execute(new JsonCallback<MsgBean>() {
            @Override
            public void onSuccess(MsgBean msgBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mData = msgBean.getData();
                if (mData!=null){
                    mMimsgContent.setText(mData.get(0).getTitle());
                    mMimsgTime.setText(DateUtils.timesTwo(mData.get(0).getOn_time()));
                }else{
                    mMimsgLayout1.setClickable(false);
                    mMimsgContent.setText("暂无通知消息");
                }
            }
        });
    }
}
