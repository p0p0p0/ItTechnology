package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.FuWuXiangQingActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.MineFBBean;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceIssueActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/18 0018 20:17
 * 邮箱：995696826@qq.com
 */

@SuppressLint("ValidFragment")
public class ServiceIssue1 extends Fragment {
    @Bind(R.id.serviceindent_list)
    ListView mServiceIndentList;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Context mContext;
    private KProgressHUD mKProgressHUD;
    private String mUserid;
    private List<MineFBBean.DataBean> mData;
    ServiceIssueActivity serviceIssueActivity;
    private ServiceIssue1Adapter mAdapter;

    @SuppressLint("ValidFragment")
    public ServiceIssue1(ServiceIssueActivity serviceIssueActivity) {
        this.serviceIssueActivity = serviceIssueActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serviceindenta, null);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        ButterKnife.bind(this, view);
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mContext = getActivity();
        NetWork();
        registerBoradcastReceiver();
        return view;
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
        hashMap.put("action", "GoodsManagement");
        hashMap.put("uid", mUserid);
        hashMap.put("mid", "7");

        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<MineFBBean>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(MineFBBean mineFBBean, Call call, Response response) {
                mData = mineFBBean.getData();
                mKProgressHUD.dismiss();
                if (mServiceIndentList != null) {
                    if (mData != null) {
                        if (!mData.isEmpty()) {
                            mServiceIndentList.setVisibility(View.VISIBLE);
                            mQuanbuImage.setVisibility(View.GONE);
                            mAdapter = new ServiceIssue1Adapter(serviceIssueActivity, mData, mUserid);
                            mServiceIndentList.setAdapter(mAdapter);
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

    @Override
    public void onResume() {
        super.onResume();
        /*mKProgressHUD.show();
        NetWork();*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
    }

    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;

    public void registerBoradcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.serviceissue.zanting");
        intentFilter.addAction("com.serviceissue.huifu");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("com.serviceissue.zanting")) {
                    KLog.e("whb", "我接收到了");
                    int mPosition = intent.getIntExtra("mPosition", -1);
                    KLog.e("mPosition", mPosition);
                    mData.get(mPosition).setService_status("3");
                    mAdapter.notifyDataSetInvalidated();
                }
                if (action.equals("com.serviceissue.huifu")) {
                    KLog.e("whb", "我接收到了");
                    int mPosition = intent.getIntExtra("mPosition", -1);
                    KLog.e("mPosition", mPosition);
                    mData.get(mPosition).setService_status("1");
                    mAdapter.notifyDataSetInvalidated();
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }
}

class ServiceIssue1Adapter extends BaseAdapter {
    Activity context;
    List<MineFBBean.DataBean> data;
    private String mFw_status;
    private Map<String, Object> mMapForJson;
    MaterialDialog mMaterialDialog;
    KProgressHUD mKProgressHUD;
    String userid;

    public ServiceIssue1Adapter(Activity context, List<MineFBBean.DataBean> data, String userid) {
        this.context = context;
        this.data = data;
        this.userid = userid;
        mKProgressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_serviceissue_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String replace = data.get(position).getPic().replace("../", "");
        KLog.e("whbimage", replace);
        if (!replace.equals("")) {
            Picasso.with(context).load(RequestAddress.IMAGE1 + replace).into(holder.mSericeImage1);
        } else {
            holder.mSericeImage1.setImageResource(R.drawable.img_fw_small);
        }
        if (!data.get(position).getTitle().equals("") && data.get(position).getTitle() != null) {
            holder.mSericeTitle.setText(data.get(position).getTitle());
            holder.mSericeMoney.setText("¥ " + data.get(position).getPrice() + "/次");
        }
        if (!data.get(position).getService_status().equals("") && data.get(position).getService_status() != null) {
            mFw_status = data.get(position).getService_status();
            final int b = Integer.valueOf(mFw_status);
            switch (b) {
                case 1:
                    holder.mFuwuZhuangtai.setText("服务状态: 审核中");
                    holder.mServiceissueLayout1.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.mFuwuZhuangtai.setText("服务状态: 已通过");
                    holder.mServiceissueLayout1.setVisibility(View.VISIBLE);
                    holder.mServiceissueButton1.setText("暂停服务");
                    break;
                case 3:
                    holder.mFuwuZhuangtai.setText("服务状态: 已暂停");
                    holder.mServiceissueLayout1.setVisibility(View.VISIBLE);
                    holder.mServiceissueButton1.setText("恢复服务");
                    break;
            }
        }
        holder.mServiceissueButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mServiceissueButton1.setOnClickListener(new View.OnClickListener() {

            private Object mObject;

            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                String trim = holder.mServiceissueButton1.getText().toString().trim();
                if (trim.equals("恢复服务")) {
                    Start(context, userid, data.get(position).getService_id(), holder.mServiceissueLayout1, holder.mFuwuZhuangtai);
                } else {
                    Stop(context, userid, data.get(position).getService_id(), holder.mServiceissueButton1, holder.mFuwuZhuangtai);
                }
            }
        });
        holder.mSericeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FuWuXiangQingActivity.class);
                intent.putExtra("sid", data.get(position).getService_id());
                intent.putExtra("fwuid", data.get(position).getUid());
                intent.putExtra("mposition", position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.serice_image1)
        ImageView mSericeImage1;
        @Bind(R.id.serice_title)
        TextView mSericeTitle;
        @Bind(R.id.serice_money)
        TextView mSericeMoney;
        @Bind(R.id.serice_layout)
        LinearLayout mSericeLayout;
        @Bind(R.id.serviceissue_button1)
        Button mServiceissueButton1;
        @Bind(R.id.serviceissue_button2)
        Button mServiceissueButton2;
        @Bind(R.id.fuwu_zhuangtai)
        TextView mFuwuZhuangtai;
        @Bind(R.id.serviceissue_layout)
        RelativeLayout mServiceissueLayout1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void Stop(Activity activity, final String mUserid, final String mSid, final Button layout, final TextView text) {
        mMaterialDialog = new MaterialDialog(activity)
                .setTitle("提示")
                .setMessage("你确定要暂停服务吗?暂停期间,您的服务不会被展示,也无法再被预约.但不会影响已经接单的服务")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mKProgressHUD.show();
                        mMaterialDialog.dismiss();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "Undercarriage");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", mSid);
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                mKProgressHUD.dismiss();
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    KLog.e("whb", code);
                                    layout.setText("恢复服务");
                                    text.setText("服务状态: 已暂停");
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });

        mMaterialDialog.show();
    }

    //恢复服务
    public void Start(Activity activity, final String mUserid, final String mSid, final RelativeLayout layout, final TextView text) {
        mMaterialDialog = new MaterialDialog(activity)
                .setTitle("提示")
                .setMessage("你确定要恢复服务吗?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        mKProgressHUD.show();
                        mMaterialDialog.dismiss();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "Grounding");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", mSid);
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    KLog.e("whb", code);
                                    layout.setVisibility(View.GONE);
                                    text.setText("服务状态: 审核中");
                                    mKProgressHUD.dismiss();
                                 /*   mFwxiangxiYyButton1.setBackgroundColor(CommonUtil.getColor(R.color.white));
                                    mFwxiangxiYyButton1.setText("审核中");
                                    mFwxiangxiYyButton1.setTextColor(CommonUtil.getColor(R.color.text_brown));*/
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });

        mMaterialDialog.show();
    }
}
