package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.AddressBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter.AdderssAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/17 0017 21:54
 * 邮箱：995696826@qq.com
 */

public class ArrdessActivity extends HBaseAct implements AdderssAdapter.EditDialogCallBack {
    @Bind(R.id.dizhi_list)
    MyListView mDizhiList;
    @Bind(R.id.dizhi_add)
    Button mDizhiAdd;
    @Bind(R.id.text111)
    TextView mText111;
    @Bind(R.id.dizhi_layout)
    LinearLayout mDizhiLayout;
    @Bind(R.id.scrollView1)
    ScrollView mScrollView1;
    @Bind(R.id.layout_address)
    LinearLayout mLayoutAddress;
    private String mUid;
    private KProgressHUD mProgressHUD;
    private Intent mIntent;
    private List<AddressBean.DataBean> mData;
    private AdderssAdapter mAdapter;
    private String mFlag;
    private Object mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(ArrdessActivity.this);
        super.onCreate(savedInstanceState);

        mUid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mName = SharedPrefsUtil.get(ECApplication.context, "name", "");
        setContentView(R.layout.activity_arrdess);
        ButterKnife.bind(this);
        NetWork(mUid);
        mProgressHUD = KProgressHUD.create(ArrdessActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        Intent intent = getIntent();
        mFlag = intent.getStringExtra("flag");
        if (!mFlag.equals("0")){
            mLayoutAddress.setVisibility(View.GONE);
        }else{
            mLayoutAddress.setVisibility(View.VISIBLE);
        }
        //aa();
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick({R.id.dizhi_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dizhi_add:
                mIntent = new Intent(ArrdessActivity.this, AdddizhiActivity.class);
                mIntent.putExtra("flags","0");
                startActivity(mIntent);
                break;
        }

    }

    public void NetWork(String uid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        OkGo.post(RequestAddress.HOST + RequestAddress.DZGL).params(hashMap).execute(new JsonCallback<AddressBean>() {
            @Override
            public void onSuccess(AddressBean addressBean, Call call, Response response) {
                mProgressHUD.dismiss();
                mData = addressBean.getData();
                if (mData!=null){
                    mScrollView1.setVisibility(View.VISIBLE);
                    mDizhiLayout.setVisibility(View.GONE);
                    mAdapter = new AdderssAdapter(mData, ArrdessActivity.this,mFlag);
                    mDizhiList.setAdapter(mAdapter);
                }else{
                    mLayoutAddress.setVisibility(View.VISIBLE);
                    mDizhiLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mProgressHUD.show();
        NetWork(mUid);
    }
    private void aa() {
        if (mAdapter!=null){
            mAdapter.setCallBack(new AdderssAdapter.EditDialogCallBack() {
                @Override
                public void clickOk() {
                    KLog.e("走了");
                    mProgressHUD.show();
                    mAdapter.notifyDataSetChanged();
                    NetWork(mUid);
                }
            });
        }
    }

    @Override
    public void clickOk() {
        KLog.e("走了");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
