package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.Adapter.ServiceIndent1Adapter;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.servuceIndentBean;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceIndentActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.ECApplication.context;

/**
 * 我购买的
 * 作者：王海宾 on 2017/5/17 0017 21:02
 * 邮箱：995696826@qq.com
 */

@SuppressLint("ValidFragment")
public class ServiceIndent1 extends Fragment {

    ListView mServiceIndentList;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Context mContext;
    private String mUserid;
    private KProgressHUD mKProgressHUD;
    private ServiceIndent1Adapter mAdapter;
    ServiceIndentActivity serviceIndent;
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    private List<servuceIndentBean.DataBean> mMData;
    private int mP;
    private List<servuceIndentBean.DataBean> mData;

    @SuppressLint("ValidFragment")
    public ServiceIndent1(ServiceIndentActivity serviceIndent) {
        this.serviceIndent = serviceIndent;
    }

    public static final String ACTION_NAME_PJ = "com.pingjia";
    public static final String ACTION_NAME_Zf = "com.zhifu";
    public static final String ACTION_NAME_Qr = "com.queren";
    public String[] mStrings = {"com.pingjia", "com.zhifu", "com.queren"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serviceindenta, null);
        mServiceIndentList = (ListView) view.findViewById(R.id.serviceindent_list);
        mContext = getActivity();
        mUserid = (String) SharedPrefsUtil.get(context, "UserUid", "");
        NetWork();
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        ButterKnife.bind(this, view);
        mServiceIndentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mP = position;
            }
        });
        registerBoradcastReceiver();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void NetWork() {
        /*action	GoodsManagement	必填
        uid	1	必填 用户id
        mid	6||7||13||14||15	必填
        6产品
        7服务
        13方案
        14 课件
        15资料*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "BuyerGoodsOrder");
        hashMap.put("uid", mUserid);
        hashMap.put("mid", "7");

        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<servuceIndentBean>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(servuceIndentBean mineFBBean, Call call, Response response) {
                mData = mineFBBean.getData();
                mKProgressHUD.dismiss();
                if (mServiceIndentList != null) {
                    if (mData != null) {
                        if (!mData.isEmpty()) {
                            mServiceIndentList.setVisibility(View.VISIBLE);
                            mQuanbuImage.setVisibility(View.GONE);
                            mAdapter = new ServiceIndent1Adapter(serviceIndent, mData, mUserid);
                            mServiceIndentList.setAdapter(mAdapter);
                            huidiao();
                        } else {
                            mServiceIndentList.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mServiceIndentList.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
    }


    private void huidiao() {
        mAdapter.setListener(new ServiceIndent1Adapter.OnListener() {
            @Override
            public void onItemClick(int position) {
                mKProgressHUD.show();
                NetWork();
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mBroadcastManager.unregisterReceiver(mReceiver);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void registerBoradcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        for (String string : mStrings) {
            intentFilter.addAction(string);
        }
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(ACTION_NAME_PJ)) {
                    KLog.e("whb", "我接收到了");
                    KLog.e("whb", mData);
                    KLog.e("whb", mAdapter);
                    KLog.e("whb", mP);
                    int mPosition = intent.getIntExtra("mPosition", -1);
                    KLog.e("mPosition", mPosition);
                    mData.get(mPosition).setOrder_status("confirm");
                    mData.get(mPosition).setMark_status("1");
                    mAdapter.notifyDataSetInvalidated();
                }
                if (action.equals(ACTION_NAME_Zf)) {
                    KLog.e("whb", "我接收到了");
                    int mPosition = intent.getIntExtra("mPosition", -1);
                    KLog.e("mPosition", mPosition);
                    mData.get(mPosition).setOrder_status("ok");
                    mAdapter.notifyDataSetInvalidated();
                }
                if (action.equals(ACTION_NAME_Qr)) {
                    int mPosition = intent.getIntExtra("mPosition", -1);
                    mData.get(mPosition).setOrder_status("confirm");
                    mData.get(mPosition).setMark_status("0");
                    mAdapter.notifyDataSetInvalidated();
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }
}

