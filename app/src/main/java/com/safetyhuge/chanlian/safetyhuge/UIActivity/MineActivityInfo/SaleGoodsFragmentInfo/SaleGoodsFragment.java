package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.SaleGoodsFragmentInfo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.FormGoodsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter.FormGoodsAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 全部订单
 * 作者：王海宾 on 2017/4/20 0020 13:46
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class SaleGoodsFragment extends Fragment {
    private List<FormGoodsBean.DataBean> mWork_info;
    private List<FormGoodsBean.DataBean> mWork_info1,mWork_info2,mWork_info3,mWork_info4;
    @Bind(R.id.text111)
    TextView mText111;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.quanbu_list1)
    MyListView mQuanbuList1;
    private String mUserid;
    private String mId;
    private Context mContext;
    private int a;
    private KProgressHUD mKProgressHUD;
    private FormGoodsAdapter mQuanAdapter;


    public SaleGoodsFragment(int a) {
        this.a = a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_goodsfragment, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        KLog.e("走了走了");
           NetWork(mUserid);
        //registerBoradcastReceiver();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
      NetWork(mUserid);
        MobclickAgent.onResume(getActivity());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    public void NetWork(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "SellerGoodsOrder");
        hashMap.put("uid", id);
        hashMap.put("mid", "6");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<FormGoodsBean>() {
            @Override
            public void onSuccess(FormGoodsBean taskList, Call call, Response response) {
                mKProgressHUD.dismiss();
                mWork_info = taskList.getData();
                mWork_info1 = new ArrayList<>();
                mWork_info2 = new ArrayList<>();
                mWork_info3 = new ArrayList<>();
                mWork_info4 = new ArrayList<>();

                //全部
                if (a == 0) {
                    if (mWork_info != null) {
                        if (mWork_info.size() != 0) {
                            mQuanAdapter = new FormGoodsAdapter(getActivity(), mWork_info, 0);
                         //   initView();
                            mQuanbuList1.setAdapter(mQuanAdapter);
                            mQuanbuImage.setVisibility(View.GONE);
                        } else {
                            mQuanbuList1.setVisibility(View.GONE);
                            mText111.setText("暂无订单");
                            mQuanbuImage.setVisibility(View.VISIBLE);
                        }
                    }
                    //待付款
                } else if (a == 1) {
                    if (mWork_info != null) {
                        for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getOrder_status();
                            if (str.equals("wait")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        KLog.e("mWork_info1", mWork_info1.size());
                        if (mWork_info1.size() != 0) {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanAdapter = new FormGoodsAdapter(getActivity(), mWork_info1, 1);
                            mQuanbuList1.setAdapter(mQuanAdapter);
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无待付款订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }

                    //待收货
                } else if (a == 2) {
                    if (mWork_info != null) {
                        for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getOrder_status();
                            if (str.equals("ok")) {
                                mWork_info2.add(workInfoBean);
                            }
                        }
                        if (mWork_info2.size() != 0) {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanAdapter = new FormGoodsAdapter(getActivity(), mWork_info2, 2);
                            mQuanbuList1.setAdapter(mQuanAdapter);
                        } else {
                            mQuanbuList1.setVisibility(View.GONE);
                            mKProgressHUD.dismiss();
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无待收货订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //待评价
                } else if (a == 3) {
                    if (mWork_info != null) {
                        for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                            Object str = workInfoBean.getMark_status();
                            KLog.e("getMark_status", str + "");
                            if (str != null) {
                                if (str.equals("0")) {
                                    mWork_info3.add(workInfoBean);
                                }
                            }
                        }
                        KLog.e("mWork_info1", mWork_info1.size());
                        if (mWork_info3.size() != 0) {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanAdapter = new FormGoodsAdapter(getActivity(), mWork_info3, 3);
                            mQuanbuList1.setAdapter(mQuanAdapter);
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂待评价订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //仲裁
                } else if (a == 4) {
                    if (mWork_info != null) {
                        for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getOrder_status();
                            if (str.equals("arbitral")) {
                                mWork_info4.add(workInfoBean);
                            }
                        }
                        if (mWork_info4.size() != 0) {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanAdapter = new FormGoodsAdapter(getActivity(), mWork_info4, 4);
                            mQuanbuList1.setAdapter(mQuanAdapter);
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无仲裁订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
            }


            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mKProgressHUD.dismiss();
            }
        });
    }

    private void initView() {
        KLog.e("aaaaaaaaaaa");
        if (mQuanAdapter != null) {
            mQuanAdapter.setCallBack(new FormGoodsAdapter.EditDialogCallBack() {
                @Override
                public void clickOk(int i) {
                    if (i==0){
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                        KLog.e("whb","zzzz");
                    }else if (i==1){
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }else{
                        mQuanAdapter.notifyDataSetChanged();
                        NetWork(mUserid);
                    }
                }
            });
        }
    }

    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;

    public void registerBoradcastReceiver() {
        KLog.e("whb", "我接收到了");
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.goods.zhifu");
        intentFilter.addAction("com.goods.pingjia");
        intentFilter.addAction("com.goods.shouhuo");
        intentFilter.addAction("com.goods.shanchu");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                KLog.e("whb", "我接收到了");
                String action = intent.getAction();
                if (action.equals("com.goods.zhifu")) {
                    KLog.e("whb", "我接收到了");
                    int mPosition = intent.getIntExtra("mPosition", -1);
                    mWork_info1.get(mPosition).setMark_status("0");
                    mQuanAdapter.notifyDataSetChanged();
                }
                if (action.equals("com.goods.pingjia")) {
                    KLog.e("whb", "我接收到了");
                    int mPosition = intent.getIntExtra("whb_flag", -1);
                    mWork_info3.get(mPosition).setMark_status("1");
                    mQuanAdapter.notifyDataSetChanged();
                }
                if (action.equals("com.goods.shouhuo")) {
                    int mPosition = intent.getIntExtra("whb_flag", -1);
                    mWork_info2.remove(mPosition);
                    if (mWork_info.isEmpty()) {
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    } else {
                        mQuanAdapter.notifyDataSetChanged();
                    }
                }
                if (a==1){
                    if (action.equals("com.goods.shanchu")) {
                        int mPosition = intent.getIntExtra("whb_flag", -1);
                        KLog.e("whb", "我接收到了");
                        KLog.e("whb", mPosition);
                        KLog.e("whb",mWork_info1);
                        KLog.e("whb",mQuanAdapter);
                        mWork_info1.remove(mPosition);
                        mQuanAdapter.notifyDataSetChanged();
                    }
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }
}