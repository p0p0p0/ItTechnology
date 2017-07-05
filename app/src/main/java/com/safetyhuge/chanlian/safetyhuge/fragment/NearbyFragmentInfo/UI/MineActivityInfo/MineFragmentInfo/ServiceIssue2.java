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
import com.safetyhuge.chanlian.safetyhuge.Bean.ServiceMinuteBean2;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.QZXiangXiActivity;
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

public class ServiceIssue2 extends Fragment {
    @Bind(R.id.serviceindent_list)
    ListView mServiceIndentList;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Context mContext;
    private KProgressHUD mKProgressHUD;
    private String mUserid;
    private List<ServiceMinuteBean2.DataBean> data;
    private ServiceIssue2Adapter mAdapter;


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
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "SellerSeekHelp");
        hashMap.put("uid", mUserid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ServiceMinuteBean2>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(ServiceMinuteBean2 mineFBBean, Call call, Response response) {
                data = mineFBBean.getData();
                mKProgressHUD.dismiss();
                if (data != null) {
                    if (!data.isEmpty()) {
                        mServiceIndentList.setVisibility(View.VISIBLE);
                        mQuanbuImage.setVisibility(View.GONE);
                        mAdapter = new ServiceIssue2Adapter(mContext, data, mUserid);
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
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBroadcastManager.unregisterReceiver(mReceiver);
    }

    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;

    public void registerBoradcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.my.qiuzhu");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                KLog.e("whb", "我接收到了");
                if (action.equals("com.my.qiuzhu")) {
                    KLog.e("whb", "我接收到了");
                    int mPosition = intent.getIntExtra("mPosition", -1);
                    KLog.e("mPosition", mPosition);
                    data.get(mPosition).setHelp_status("7");
                    mAdapter.notifyDataSetInvalidated();
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }
}

class ServiceIssue2Adapter extends BaseAdapter {
    Context context;
    List<ServiceMinuteBean2.DataBean> data;
    KProgressHUD mProgressHUD;
    MaterialDialog mMaterialDialog;
    String userid;

    //接口
    public interface OnListener {
        //回调方法
        void onItemClick(int position);
    }

    public ServiceIssue2Adapter(Context context, List<ServiceMinuteBean2.DataBean> data, String userid) {
        this.context = context;
        this.data = data;
        this.userid = userid;
        mProgressHUD = KProgressHUD.create(context)
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

    @SuppressLint("WrongConstant")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_serviceissue_layout1, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String help_status = data.get(position).getHelp_status();
        if (help_status != null && !help_status.equals("")) {
            if (help_status.equals("2")) {
                //求助中
                holder.mZhuangtai.setText("求助中");
                holder.mServiceissueLayout.setVisibility(View.VISIBLE);
            } else if (help_status.equals("7")) {
                //已放弃
                holder.mZhuangtai.setText("已放弃");
                holder.mServiceissueLayout.setVisibility(View.GONE);
            } else if (help_status.equals("6")) {
                holder.mZhuangtai.setText("已完成");
                holder.mServiceissueLayout.setVisibility(View.GONE);
            } else if (help_status.equals("3")) {
                holder.mZhuangtai.setText("处理中");
                holder.mServiceissueLayout.setVisibility(View.GONE);
            }
        }
        if (data.get(position).getPic() != null && !data.get(position).getPic().equals("")) {
            Picasso.with(context).load(RequestAddress.IMAGE1 + data.get(position).getPic()).into(holder.mSericeImage1);
        } else {
            //img_fw_small
            holder.mSericeImage1.setImageResource(R.drawable.img_fw_small);
        }
        holder.mSericeTitle.setText(data.get(position).getTitle());
        holder.mSericeMoney.setText("赏金" + data.get(position).getPrice());
        holder.mSericeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QZXiangXiActivity.class);
                String pic = data.get(position).getPic();
                if (pic != null && !pic.equals("")) {
                    intent.putExtra("pic", pic);
                } else {
                    intent.putExtra("pic", "");
                }
                String user_pic = data.get(position).getSeller_pic();
                if (user_pic != null && !user_pic.equals("")) {
                    intent.putExtra("user_pic", user_pic);
                } else {
                    intent.putExtra("user_pic", "");
                }
                String username = data.get(position).getUsername();
                intent.putExtra("username", username);
                intent.putExtra("zhuangtai", holder.mZhuangtai.getText().toString());
                String title = data.get(position).getTitle();
                intent.putExtra("title", title);
                String price = data.get(position).getPrice();
                intent.putExtra("price", price);
                String s = data.get(position).getHelp_time() + data.get(position).getUnit_time();
                intent.putExtra("time", s);
                String content = data.get(position).getContent();
                intent.putExtra("content", content);
                String lng = data.get(position).getLng();
                intent.putExtra("lng", lng);
                String lat = data.get(position).getLat();
                intent.putExtra("lat", lat);
                intent.putExtra("hid", data.get(position).getHelp_id());
                intent.putExtra("mposition", position);
                context.startActivity(intent);
            }
        });
        holder.mServiceissueButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog = new MaterialDialog(context)
                        .setTitle("提示")
                        .setMessage("您确定要放弃求助吗?" + "\n" + "放弃后将无法恢复")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                mProgressHUD.show();
                                  /*  action	DelSeekHelp
                                    uid	102
                                    hid	22*/

                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("action", "DelSeekHelp");
                                hashMap.put("uid", userid);
                                hashMap.put("hid", data.get(position).getHelp_id());
                                OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        mProgressHUD.dismiss();
                                        KLog.json(s);
                                        Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                        String code = (String) mapForJson.get("code");
                                        String secess = (String) mapForJson.get("secess");
                                        if (code.equals("888") && secess.equals("true")) {
                                            data.get(position).setHelp_status("7");
                                            notifyDataSetChanged();
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
        @Bind(R.id.serviceissue_layout1)
        RelativeLayout mServiceissueLayout1;
        @Bind(R.id.serviceissue_layout)
        RelativeLayout mServiceissueLayout;
        @Bind(R.id.zhuangtai)
        TextView mZhuangtai;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
