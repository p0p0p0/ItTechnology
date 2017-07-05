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
import android.widget.AdapterView;
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
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.HelpBean;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceHelpActivity;
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
 * 我帮助的
 * 作者：王海宾 on 2017/5/17 0017 22:16
 * 邮箱：995696826@qq.com
 */

@SuppressLint("ValidFragment")
public class ServiceHelp1 extends Fragment {

    @Bind(R.id.serviceindent_list)
    ListView mServiceIndentList;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Activity mContext;
    private KProgressHUD mKProgressHUD;
    private List<HelpBean.DataBean> mData;
    private String mUserid;
    ServiceHelpActivity serviceHelpActivity;
    private ServiceHelp1Adapter mAdapter;
    int po = -1;
    private IFragmentListener mListener;
    public interface IFragmentListener {
        void onFragmentClick(View v);
    }
    @SuppressLint("ValidFragment")
    public ServiceHelp1(ServiceHelpActivity serviceHelpActivity) {
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
        mContext = getActivity();
        NetWork();
        mServiceIndentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                po = position;
                Toast.makeText(ECApplication.context, position+"", Toast.LENGTH_SHORT).show();
            }
        });
        registerBoradcastReceiver();
        return view;
    }

    private void NetWork() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "BuyerSeekHelp");
        hashMap.put("uid", mUserid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<HelpBean>() {
            @Override
            public void onSuccess(HelpBean helpBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mData = helpBean.getData();
                if (!mData.isEmpty()) {
                    mAdapter = new ServiceHelp1Adapter(serviceHelpActivity, mData, mUserid);
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
        intentFilter.addAction("com.help.wancheng");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("com.help.wancheng")){
                    int item = intent.getIntExtra("item", -1);
                    KLog.e("我接收到了");
                    KLog.e("mData",mData);
                    KLog.e("mAdapter",mAdapter);
                    KLog.e("po",po);
                    KLog.e("po",item);
                    mData.get(item).setHelp_status("5");
                    mAdapter.notifyDataSetChanged();;
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }
}

class ServiceHelp1Adapter extends BaseAdapter {
    ServiceHelpActivity context;
    List<HelpBean.DataBean> mData;
    String f = new String();
    MaterialDialog mMaterialDialog;
    KProgressHUD mKProgressHUD;
    String userid;

    public ServiceHelp1Adapter(ServiceHelpActivity context, List<HelpBean.DataBean> mData, String userid) {
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_serice_c, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String fw_status = mData.get(position).getHelp_status();
        if (fw_status != null && !fw_status.equals("")) {
            if (fw_status.equals("3")) {
                //待接受
                holder.mSericeState.setText("待接受");
            } else if (fw_status.equals("4")) {
                //待完成
                holder.mSericeState.setText("待完成");
                holder.mHelpWancheng.setVisibility(View.VISIBLE);
                holder.mWanchengButton.setOnClickListener(new View.OnClickListener() {
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
                                        JJJD(position, 0, "CompleteSeekHelp");
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
            } else if (fw_status.equals("5")) {
                //待确认
                holder.mSericeState.setText("待确认");
                holder.mHelpWancheng.setVisibility(View.GONE);
            } else if (fw_status.equals("6")) {
                //已完成
                holder.mSericeState.setText("已完成");
                holder.mHelpWancheng.setVisibility(View.GONE);
            } /*else if (fw_status.equals("4")) {
                //待完成
            } else if (fw_status.equals("4")) {
                //待完成
            }*/
        }
        if (mData.get(position).getSeller_pic() != "" && !mData.get(position).getSeller_pic().equals("")) {
            Picasso.with(context).load(RequestAddress.IMAGE1+mData.get(position).getSeller_pic()).fit().into(holder.mSericeImage);
        } else {
            //icon_small_tx
            holder.mSericeImage.setImageResource(R.drawable.icon_small_tx);
        }
        if (mData.get(position).getPic() != null && !mData.get(position).getPic().equals("")) {
            Picasso.with(context).load(RequestAddress.IMAGE1+mData.get(position).getPic()).into(holder.mSericeImage1);
        } else {
            //img_fw_small
            holder.mSericeImage1.setImageResource(R.drawable.img_fw_small);
        }
        holder.mSericeName.setText(mData.get(position).getUsername());
        holder.mSericeTitle.setText(mData.get(position).getTitle());
        holder.mSericeMoney.setText("¥" + mData.get(position).getPrice() + "/次");
        holder.mSericeCount.setText("x" + "1");
        holder.mSericeMoneys.setText("¥" + mData.get(position).getPrice() + "元");
       holder.mSericeLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context,ServiceHelp1Activity.class);
               //id
               intent.putExtra("hid", mData.get(position).getHelp_id());
               //状态
               intent.putExtra("status", holder.mSericeState.getText().toString().trim());
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

    class ViewHolder {
        @Bind(R.id.serice_image11)
        CircleImageView mSericeImage;
        @Bind(R.id.serice_name1)
        TextView mSericeName;
        @Bind(R.id.serice_state1)
        TextView mSericeState;
        @Bind(R.id.serice_image2)
        ImageView mSericeImage1;
        @Bind(R.id.serice_title1)
        TextView mSericeTitle;
        @Bind(R.id.serice_money1)
        TextView mSericeMoney;
        @Bind(R.id.serice_count1)
        TextView mSericeCount;
        @Bind(R.id.serice_layout1)
        LinearLayout mSericeLayout;
        @Bind(R.id.serice_moneys1)
        TextView mSericeMoneys;
        @Bind(R.id.help_layout1)
        RelativeLayout mHelpLayout;
        @Bind(R.id.button1)
        Button mButton;
        @Bind(R.id.button11)
        Button mButton2;
        @Bind(R.id.help_layout2)
        RelativeLayout mHelpLayout1;
        @Bind(R.id.wancheng_button)
        Button mWanchengButton;
        @Bind(R.id.help_wancheng)
        RelativeLayout mHelpWancheng;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
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
                        mData.get(position).setHelp_status("6");
                        notifyDataSetChanged();
                   }
                    if (flag == 1) {
                        // mData.get(position).setOrder_status("wait");
                        notifyDataSetChanged();
                    }
                    if (flag == 2) {
                        // mData.get(position).setOrder_status("working");
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
}

