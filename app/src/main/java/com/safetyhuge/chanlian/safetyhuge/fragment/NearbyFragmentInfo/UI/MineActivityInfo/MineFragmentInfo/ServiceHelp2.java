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
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ServiceMinuteBean2;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceHelpActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 我求助的
 * 作者：王海宾 on 2017/5/17 0017 22:16
 * 邮箱：995696826@qq.com
 */

@SuppressLint("ValidFragment")
public class ServiceHelp2 extends Fragment {
    @Bind(R.id.serviceindent_list)
    ListView mServiceIndentList;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Context mContext;
    private List<ServiceMinuteBean2.DataBean> mData;
    private List<ServiceMinuteBean2.DataBean> mDataInfo;
    private KProgressHUD mKProgressHUD;
    private String mUserid;
    ServiceHelpActivity serviceHelpActivity;
    private ServiceHelp2Adapter mAdapter;

    @SuppressLint("ValidFragment")
    public ServiceHelp2(ServiceHelpActivity serviceHelpActivity) {
        this.serviceHelpActivity = serviceHelpActivity;
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
        NetWork();
        mContext = getActivity();
        registerBoradcastReceiver();
        return view;
    }

    private void NetWork() {
      /*  action	SellerSeekHelp
        uid	102*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "SellerSeekHelp");
        hashMap.put("uid", mUserid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ServiceMinuteBean2>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(ServiceMinuteBean2 helpBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mData = helpBean.getData();
                mDataInfo = new ArrayList<>();
                if (!mData.isEmpty()) {
                    for (ServiceMinuteBean2.DataBean datum : mData) {
                        String help_status = datum.getHelp_status();
                        if (help_status!=null&&!help_status.equals("")&&!help_status.equals("2")&&!help_status.equals("1")){
                            mDataInfo.add(datum);
                        }
                    }
                    mAdapter = new ServiceHelp2Adapter(serviceHelpActivity, mDataInfo, mUserid);
                    mServiceIndentList.setAdapter(mAdapter);
                } else {
                    mQuanbuImage.setVisibility(View.VISIBLE);
                    mServiceIndentList.setVisibility(View.GONE);
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
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    public void registerBoradcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.help2.jujue");
        intentFilter.addAction("com.help2.tongyi");
        intentFilter.addAction("com.help.wancheng");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("com.help2.jujue")){
                    int item = intent.getIntExtra("item", -1);
                    KLog.e("我接收到了");
                    KLog.e("mData",mDataInfo);
                    KLog.e("po",item);
                    mDataInfo.get(item).setHelp_status("-1");
                    mAdapter.notifyDataSetChanged();;
                }
                if (action.equals("com.help2.tongyi")){
                    int item = intent.getIntExtra("item", -1);
                    KLog.e("我接收到了");
                    KLog.e("mData",mData);
                    KLog.e("po",item);
                    mDataInfo.get(item).setHelp_status("4");
                    mAdapter.notifyDataSetChanged();;
                }
                if (action.equals("com.help.wancheng")){
                    int item = intent.getIntExtra("item", -1);
                    KLog.e("我接收到了");
                    KLog.e("mData",mData);
                    KLog.e("po",item);
                    mDataInfo.get(item).setHelp_status("6");
                    mAdapter.notifyDataSetChanged();;
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }
}

class ServiceHelp2Adapter extends BaseAdapter {
    Activity context;
    List<ServiceMinuteBean2.DataBean> mData;
    String f = new String();
    MaterialDialog mMaterialDialog;
    KProgressHUD mKProgressHUD;
    String userid;

    public ServiceHelp2Adapter(Activity context, List<ServiceMinuteBean2.DataBean> mData, String userid) {
        this.context = context;
        this.mData = mData;
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
        return mData.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_serice_d, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String s = mData.get(position).getHelp_status();
        KLog.e("ss",s);
        if (s != null && !s.equals("")) {
            if (s.equals("-1")){
                holder.mSericeState1.setText("求助中");
                holder.mHelpLayout3.setVisibility(View.GONE);
                holder.mSericeLayout1.setClickable(false);
            }else if (s.equals("3")) {
                holder.mSericeLayout1.setClickable(true);
                holder.mSericeState1.setText("待接受");
                holder.mHelpLayout3.setVisibility(View.VISIBLE);
                //拒绝帮助
                holder.mButton31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog = new MaterialDialog(context)
                                .setTitle("提示")
                                .setMessage("您确定要拒绝接受吗?")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mKProgressHUD.show();
                                        mMaterialDialog.dismiss();
                                        JJJD(position, 0, "RefuseSeekHelp");
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
                //接受帮助
                holder.mButton32.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog = new MaterialDialog(context)
                                .setTitle("提示")
                                .setMessage("您确定要接受帮助吗?")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mKProgressHUD.show();
                                        mMaterialDialog.dismiss();
                                        JJJD(position, 1, "ConfirmSeekHelp");
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
            }else  if (s.equals("6")){
                holder.mSericeLayout1.setClickable(true);
                holder.mSericeState1.setText("已完成");
                holder.mHelpLayout2.setVisibility(View.GONE);
                holder.mHelpLayout3.setVisibility(View.GONE);
            }else if (s.equals("5")){
                holder.mSericeLayout1.setClickable(true);
                holder.mSericeState1.setText("待确认");
                holder.mHelpLayout2.setVisibility(View.VISIBLE);
                holder.mHelpLayout3.setVisibility(View.GONE);
                holder.mButton11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog = new MaterialDialog(context)
                                .setTitle("提示")
                                .setMessage("您确定已完成帮助吗?")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mKProgressHUD.show();
                                        mMaterialDialog.dismiss();
                                        JJJD(position, 2, "ConfirmCompleteSeekHelp");
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
            }else if (s.equals("4")){
                holder.mSericeState1.setText("待完成");
                holder.mHelpLayout2.setVisibility(View.GONE);
                holder.mHelpLayout3.setVisibility(View.GONE);
            }else  if (s.equals("7")){
                holder.mSericeState1.setText("已放弃");
                holder.mHelpLayout2.setVisibility(View.GONE);
                holder.mHelpLayout3.setVisibility(View.GONE);
            }
        }
        if (mData.get(position).getSeller_pic() != "" && !mData.get(position).getSeller_pic().equals("")) {
            Picasso.with(context).load(RequestAddress.IMAGE1+mData.get(position).getSeller_pic().replace("../", "")).fit().into(holder.mSericeImage11);
        } else {
            //icon_small_tx
            holder.mSericeImage11.setImageResource(R.drawable.icon_small_tx);
        }
        if (mData.get(position).getPic() != null && !mData.get(position).getPic().equals("")) {
            Picasso.with(context).load(RequestAddress.IMAGE1+mData.get(position).getPic()).into(holder.mSericeImage2);
        } else {
            //img_fw_small
            holder.mSericeImage2.setImageResource(R.drawable.img_fw_small);
        }
        holder.mSericeName1.setText(mData.get(position).getUsername());
        holder.mSericeTitle1.setText(mData.get(position).getTitle());
        holder.mSericeMoney1.setText("¥" + mData.get(position).getPrice() + "/次");
        holder.mSericeCount1.setText("x" + "1");
        holder.mSericeMoneys1.setText("¥" + mData.get(position).getPrice() + "元");
        holder.mSericeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ServiceHelp2Activity.class);
                //id
                intent.putExtra("hid", mData.get(position).getHelp_id());
                //状态
                intent.putExtra("status", holder.mSericeState1.getText().toString().trim());
                if (mData.get(position).getSeller_pic() != "" && !mData.get(position).getSeller_pic().equals("")) {
                    //头像
                    intent.putExtra("TxImage",mData.get(position).getSeller_pic());
                }else{
                    intent.putExtra("TxImage","");
                }
                //用户名
                intent.putExtra("Username", mData.get(position).getUsername());
                //电话
                intent.putExtra("phone", mData.get(position).getPhone());
                if (mData.get(position).getPic() != null && !mData.get(position).getPic().equals("")) {
                    //图片
                    intent.putExtra("Bgimage", mData.get(position).getPic());
                }else{
                    intent.putExtra("Bgimage", "");
                }
                //标题
                intent.putExtra("title", mData.get(position).getTitle());
                //钱数
                intent.putExtra("money", mData.get(position).getPrice());
                //时间
                intent.putExtra("time",mData.get(position).getOn_time());
                //tiem
                intent.putExtra("item",position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    public void JJJD(final int position, final int flag, String action) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", action);
        hashMap.put("uid", userid);
        hashMap.put("hid", mData.get(position).getHelp_id());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mKProgressHUD.dismiss();
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                if (code.equals("888") && secess.equals("true")) {
                    if (flag == 0) {
                        mData.get(position).setHelp_status("-1");
                        notifyDataSetChanged();
                    }
                    if (flag == 1) {
                        mData.get(position).setHelp_status("4");
                        notifyDataSetChanged();
                    }
                    if (flag == 2) {
                        //6
                        mData.get(position).setHelp_status("6");
                        notifyDataSetChanged();
                    }
                    if (flag == 3) {
                        //  mData.get(position).setOrder_status("complete");
                        // mData.get(position).setMark_status("0");
                        notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class ViewHolder {
        @Bind(R.id.serice_image11)
        CircleImageView mSericeImage11;
        @Bind(R.id.serice_name1)
        TextView mSericeName1;
        @Bind(R.id.serice_state1)
        TextView mSericeState1;
        @Bind(R.id.serice_image2)
        ImageView mSericeImage2;
        @Bind(R.id.serice_title1)
        TextView mSericeTitle1;
        @Bind(R.id.serice_money1)
        TextView mSericeMoney1;
        @Bind(R.id.serice_count1)
        TextView mSericeCount1;
        @Bind(R.id.serice_layout1)
        LinearLayout mSericeLayout1;
        @Bind(R.id.serice_moneys1)
        TextView mSericeMoneys1;
        @Bind(R.id.button1)
        Button mButton1;
        @Bind(R.id.help_layout1)
        RelativeLayout mHelpLayout1;
        @Bind(R.id.button11)
        Button mButton11;
        @Bind(R.id.help_layout2)
        RelativeLayout mHelpLayout2;
        @Bind(R.id.button3_1)
        Button mButton31;
        @Bind(R.id.button3_2)
        Button mButton32;
        @Bind(R.id.help_layout3)
        RelativeLayout mHelpLayout3;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
