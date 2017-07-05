package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DIngjinFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment.QuanSAdapter;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment.QuanbuBean;
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
 * 订金招标
 * 作者：王海宾 on 2017/4/17 0017 21:27
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class DingjinFragment extends Fragment{
    @Bind(R.id.quanbu_list1)
    ListView mQuanbuList1;
    @Bind(R.id.quanbu_list2)
    ListView mQuanbuList2;
    @Bind(R.id.text111)
    TextView mText111;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private String mUserid;
    private String mId;
    private Context mContext;
    private List<QuanbuBean.DataBean.WorkInfoBean> mWork_info;
    private List<QuanbuBean.DataBean.WorkInfoBean> mWork_info1;

    private int a;
    private KProgressHUD mKProgressHUD;
    private QuanSAdapter mQuanAdapter;

    public DingjinFragment(int a) {
        this.a = a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_quanbufragment, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mQuanbuList1.setVisibility(View.GONE);
        mId = (String) SharedPrefsUtil.get(ECApplication.context, "xmid", "");
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        NetWork(mId, mUserid);
        KLog.e("mUserid",mUserid);
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        return view;
    }

    private void aa() {
        if (mQuanAdapter!=null){
            mQuanAdapter.setCallBack(new QuanSAdapter.EditDialogCallBack() {
                @Override
                public void clickOk() {
                    mQuanAdapter.notifyDataSetChanged();
                    NetWork(mId, mUserid);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initView() {
        aa();
    }

    public void NetWork(String id, String id1) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetDeliveryInfo");
        hashMap.put("uid", id1);
        hashMap.put("task_id", id);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<QuanbuBean>() {
            @Override
            public void onSuccess(QuanbuBean quanbuBean, Call call, Response response) {
                mWork_info = quanbuBean.getData().getWork_info();
                mWork_info1 = new ArrayList<QuanbuBean.DataBean.WorkInfoBean>();
                mKProgressHUD.dismiss();
                if (a == 0) {
                    if (mWork_info!=null){
                        mQuanAdapter = new QuanSAdapter(mContext, mWork_info,3);
                        initView();
                        mQuanbuList2.setAdapter(mQuanAdapter);
                        mKProgressHUD.dismiss();
                        mQuanbuImage.setVisibility(View.GONE);
                    }else{
                        mQuanbuList2.setVisibility(View.GONE);
                        mText111.setText("暂无未人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //未浏览
                } else if (a == 1) {
                    if (mWork_info!=null){
                        for (QuanbuBean.DataBean.WorkInfoBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getIs_view();
                            if (str.equals("0")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size()!=0){
                            mQuanbuList2.setVisibility(View.VISIBLE);
                            mQuanbuList2.setAdapter(new QuanSAdapter(mContext, mWork_info1,3));
                            mKProgressHUD.dismiss();

                        }else{
                            mKProgressHUD.dismiss();
                            mQuanbuList2.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无未浏览订单");
                        }
                    }else{
                        mKProgressHUD.dismiss();
                        mQuanbuList2.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }

                    //中标
                } else if (a == 2) {
                    if (mWork_info!=null){
                        for (QuanbuBean.DataBean.WorkInfoBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getBid_status();
                            if (str.equals("4")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size() != 0) {
                            mQuanbuList2.setVisibility(View.VISIBLE);
                            mQuanbuList2.setAdapter(new QuanSAdapter(mContext, mWork_info1,3));
                            mKProgressHUD.dismiss();
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无中标订单");
                        }
                    }else{
                        mKProgressHUD.dismiss();
                        mQuanbuList2.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //淘汰
                }  else if (a == 3) {
                    if (mWork_info!=null){
                        for (QuanbuBean.DataBean.WorkInfoBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getBid_status();
                            if (str.equals("7")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size() != 0) {
                            mQuanbuList2.setVisibility(View.VISIBLE);
                            mQuanbuList2.setAdapter(new QuanSAdapter(mContext, mWork_info1,3));
                            mKProgressHUD.dismiss();
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList2.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无淘汰订单");
                        }
                    }else{
                        mKProgressHUD.dismiss();
                        mQuanbuList2.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
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
}
