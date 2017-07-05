package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment;

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
 * 作者：王海宾 on 2017/4/15 0015 17:43
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class QuanbuFragment extends Fragment {
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
    private QuanAdapter mQuanAdapter;

    public QuanbuFragment(int a) {
        this.a = a;
        KLog.e("whb",a);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_quanbufragment, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mId = (String) SharedPrefsUtil.get(ECApplication.context, "xmid", "");
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mQuanbuList2.setVisibility(View.GONE);
        NetWork(mId, mUserid);
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        KLog.e("whb","zoule");
        return view;
    }

    private void aa() {
        if (mQuanAdapter != null) {
            mQuanAdapter.setCallBack(new QuanAdapter.EditDialogCallBack() {
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
                mKProgressHUD.dismiss();
                mWork_info = quanbuBean.getData().getWork_info();
                mWork_info1 = new ArrayList<>();
                if (a == 0) {
                    if (mWork_info != null) {
                        mQuanAdapter = new QuanAdapter(mContext, mWork_info, 0);
                        initView();
                        mQuanbuList1.setAdapter(mQuanAdapter);
                        mKProgressHUD.dismiss();
                        mQuanbuImage.setVisibility(View.GONE);
                    } else {
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //未浏览
                } else if (a == 1) {
                    if (mWork_info != null) {
                        for (QuanbuBean.DataBean.WorkInfoBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getIs_view();
                            if (str.equals("0")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size() != 0) {
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanbuList1.setAdapter(new QuanAdapter(mContext, mWork_info1, 0));
                            mKProgressHUD.dismiss();

                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无未浏览订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }

                    //中标
                } else if (a == 2) {
                    if (mWork_info != null) {
                        for (QuanbuBean.DataBean.WorkInfoBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getWork_status();
                            if (str.equals("4")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size() != 0) {
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanbuList1.setAdapter(new QuanAdapter(mContext, mWork_info1, 0));
                            mKProgressHUD.dismiss();
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无中标订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //入围
                } else if (a == 3) {
                    if (mWork_info != null) {
                        for (QuanbuBean.DataBean.WorkInfoBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getWork_status();
                            if (str.equals("5")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        KLog.e("mWork_info1", mWork_info1.size());
                        if (mWork_info1.size() != 0) {
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanbuList1.setAdapter(new QuanAdapter(mContext, mWork_info1, 0));
                            mKProgressHUD.dismiss();
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无入围订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //淘汰
                } else if (a == 4) {
                    if (mWork_info != null) {
                        for (QuanbuBean.DataBean.WorkInfoBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getWork_status();
                            if (str.equals("7")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size() != 0) {
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanbuList1.setAdapter(new QuanAdapter(mContext, mWork_info1, 0));
                            mKProgressHUD.dismiss();
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无淘汰订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
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
}
