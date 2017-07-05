package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.FananFragmentInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.FananBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.FananYsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment.QuanAdapter;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment.QuanbuBean;
import com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter.Fananadapter;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/21 0021 16:16
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class FananFragment extends Fragment {
    @Bind(R.id.fanan_scrollview)
    ScrollView mFananScrollview;
    private List<QuanbuBean.DataBean.WorkInfoBean> mWork_info;
    private List<QuanbuBean.DataBean.WorkInfoBean> mWork_info1;
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
    private QuanAdapter mQuanAdapter;

    public FananFragment(int a) {
        this.a = a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fananfragment, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        KLog.e("走了走了");
        flag(a);
        return view;
    }

    public void flag(int bb) {
        switch (bb) {
            case 0:
                NetWork(mUserid);
                break;
            case 1:
                NetWork1(mUserid);
                break;
            case 2:
                NetWork2(mUserid);
                break;
        }
    }

    private void NetWork1(String userid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "SellerGoodsOrder");
        hashMap.put("uid", userid);
        hashMap.put("mid", "13");
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<FananYsBean>() {
            @Override
            public void onSuccess(FananYsBean ysBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                List<FananYsBean.DataBean> data = ysBean.getData();
                if (data.isEmpty()) {
                    mFananScrollview.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                    mText111.setText("暂无已售方案");
                } else {
                    mQuanbuImage.setVisibility(View.GONE);
                    mFananScrollview.setVisibility(View.VISIBLE);
                    mQuanbuList1.setAdapter(new FananYsAdapter(data, getActivity(), 1));
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                super.onError(call, response, e);
            }
        });
    }

    private void NetWork2(String userid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "BuyerGoodsOrder");
        hashMap.put("uid", userid);
        hashMap.put("mid", "13");
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<FananYsBean>() {
            @Override
            public void onSuccess(FananYsBean ysBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                List<FananYsBean.DataBean> data = ysBean.getData();
                if (data.isEmpty()) {
                    mFananScrollview.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                    mText111.setText("暂无已购方案");
                } else {
                    mQuanbuImage.setVisibility(View.GONE);
                    mFananScrollview.setVisibility(View.VISIBLE);
                    mQuanbuList1.setAdapter(new FananYsAdapter(data, getActivity(), 2));

                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                super.onError(call, response, e);
            }
        });
    }

    public void NetWork(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GoodsManagement");
        hashMap.put("uid", id);
        hashMap.put("mid", "13");
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<FananBean>() {
            @Override
            public void onSuccess(FananBean fananbean, Call call, Response response) {
                mKProgressHUD.dismiss();
                List<FananBean.DataBean> data = fananbean.getData();
                if (data.isEmpty()) {
                    mFananScrollview.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                    mText111.setText("暂无发布的方案");
                } else {
                    mQuanbuImage.setVisibility(View.GONE);
                    mFananScrollview.setVisibility(View.VISIBLE);
                    mQuanbuList1.setAdapter(new Fananadapter(data, getActivity()));

                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                super.onError(call, response, e);
            }
        });
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}