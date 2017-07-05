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
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ServiceIndentPingjiaActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.ServuceIndent2Bean;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceIndentActivity;
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
 * 我售出的
 * 作者：王海宾 on 2017/5/17 0017 21:02
 * 邮箱：995696826@qq.com
 */

@SuppressLint("ValidFragment")
public class ServiceIndent2 extends Fragment{

    @Bind(R.id.serviceindent_list)
    ListView mServiceIndentList;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Activity mContext;
    private KProgressHUD mKProgressHUD;
    private String mUserid;
    ServiceIndentActivity serviceIndentActivity;
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    int p = -1;
    public static final String ACTION_NAME_SHOUCHU_PJ = "com.shouchu.pj";
    public static final String ACTION_NAME_SHOUCHU_KSFW = "com.shouchu.ksfw";
    public static final String ACTION_NAME_SHOUCHU_FWWC = "com.shouchu.fwwc";
    public static final String ACTION_NAME_SHOUCHU_JJJD = "com.shouchu.jjjd";
    public static final String ACTION_NAME_SHOUCHU_TYJD = "com.shouchu.tyjd";
    public String[] mStrings = {ACTION_NAME_SHOUCHU_PJ, ACTION_NAME_SHOUCHU_KSFW, ACTION_NAME_SHOUCHU_FWWC
            , ACTION_NAME_SHOUCHU_JJJD, ACTION_NAME_SHOUCHU_TYJD
    };
    private ServiceIndent2Adapter mAdapter;
    private List<ServuceIndent2Bean.DataBean> mData;

    @SuppressLint("ValidFragment")
    public ServiceIndent2(ServiceIndentActivity serviceIndentActivity) {
        this.serviceIndentActivity = serviceIndentActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serviceindenta, null);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        ButterKnife.bind(this, view);
        NetWork();
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mContext = serviceIndentActivity;
        mServiceIndentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p = position;
            }
        });
        registerBoradcastReceiver();
        return view;
    }

    private void NetWork() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "SellerGoodsOrder");
        hashMap.put("uid", mUserid);
        hashMap.put("mid", "7");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ServuceIndent2Bean>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(ServuceIndent2Bean servuceIndentBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mData = servuceIndentBean.getData();
                if (mData != null && !mData.isEmpty()) {
                    mServiceIndentList.setVisibility(View.VISIBLE);
                    mQuanbuImage.setVisibility(View.GONE);
                    mAdapter = new ServiceIndent2Adapter(mContext, mData, mUserid);
                    mServiceIndentList.setAdapter(mAdapter);
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBroadcastManager.unregisterReceiver(mReceiver);
    }

    public void registerBoradcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        IntentFilter intentFilter = new IntentFilter();
        for (String string : mStrings) {
            intentFilter.addAction(string);
        }
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                KLog.e("whb","我接收到了");
                String action = intent.getAction();
                int whb_flag = intent.getIntExtra("whb_flag", -1);
                if (action.equals(ACTION_NAME_SHOUCHU_PJ)) {
                    KLog.e("whb","我接收到了");
                    KLog.e("whb",mAdapter);
                    mData.get(whb_flag).setOrder_status("confirm");
                    mData.get(whb_flag).setMark_status("1");
                    mAdapter.notifyDataSetInvalidated();
                }
                if (action.equals(ACTION_NAME_SHOUCHU_JJJD)) {
                    KLog.e("whb","我接收到了");
                    KLog.e("whb",mAdapter);
                    mData.get(whb_flag).setOrder_status("close");
                    mAdapter.notifyDataSetInvalidated();
                }
                if (action.equals(ACTION_NAME_SHOUCHU_TYJD)){
                    KLog.e("whb","我接收到了");
                    mData.get(whb_flag).setOrder_status("wait");
                    mAdapter.notifyDataSetInvalidated();
                }
                //ACTION_NAME_SHOUCHU_FWWC
                if (action.equals(ACTION_NAME_SHOUCHU_KSFW)){
                    KLog.e("whb","我接收到了");
                    mData.get(whb_flag).setOrder_status("working");
                    mAdapter.notifyDataSetInvalidated();
                }
                if (action.equals(ACTION_NAME_SHOUCHU_FWWC)){
                    KLog.e("whb","我接收到了");
                    mData.get(whb_flag).setOrder_status("complete");
                    mData.get(whb_flag).setMark_status("0");
                    mAdapter.notifyDataSetInvalidated();
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}

class ServiceIndent2Adapter extends BaseAdapter {
    Activity context;
    List<ServuceIndent2Bean.DataBean> mData;
    String f = new String();
    MaterialDialog mMaterialDialog;
    KProgressHUD mKProgressHUD;
    String userid;

    public ServiceIndent2Adapter(Activity context, List<ServuceIndent2Bean.DataBean> mData, String userid) {
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
            convertView = View.inflate(ECApplication.context, R.layout.item_serice_b, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String s = mData.get(position).getOrder_status();
        KLog.e("whb",s);
        String str = (String) mData.get(position).getMark_status();
    /*    order_status; // 状态：seller_confirm待付款，
    ok已付款，wait已接单
    ，close已拒绝，working服务中
    ，confirm_complete服务方已确认，
    complete买家已确认
        order_status为complete时，根据mark_status字段判断是否已评价*/

        if (!s.equals("") && s != null) {
            //待付款
            if (s.equals("seller_confirm")) {
                //显示待付款 不显示按钮
                holder.mSericeState.setText("待付款");
                //评价
                holder.mIndent2Layout1.setVisibility(View.GONE);
                //服务开始
                holder.mIndent2Layout2.setVisibility(View.GONE);
                //服务完成
                holder.mIndent2Layout3.setVisibility(View.GONE);
                //同意拒绝
                holder.mIndent2Layout4.setVisibility(View.GONE);
                //ok已付款
            } else if (s.equals("ok")) {
                //显示同意接单 拒绝接单
                holder.mSericeState.setText("待接单");
                //评价
                holder.mIndent2Layout1.setVisibility(View.GONE);
                //服务开始
                holder.mIndent2Layout2.setVisibility(View.GONE);
                //服务完成
                holder.mIndent2Layout3.setVisibility(View.GONE);
                //同意拒绝
                holder.mIndent2Layout4.setVisibility(View.VISIBLE);
                //拒绝接单
                holder.mIndent2Layout41.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog = new MaterialDialog(context)
                                .setTitle("提示")
                                .setMessage("您确定要拒绝接单?")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mKProgressHUD.show();
                                        mMaterialDialog.dismiss();
                                        JJJD(position, 0, "SellerRefuseOrder", holder.mIndent2Layout4, holder.mSericeState,null);
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
                //同意接单
                holder.mIndent2Layout42.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog = new MaterialDialog(context)
                                .setTitle("提示")
                                .setMessage("您确定要同意接单?")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mKProgressHUD.show();
                                        mMaterialDialog.dismiss();
                                        JJJD(position, 1, "SellerAcceptOrder", holder.mIndent2Layout4, holder.mSericeState,null);
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
                //wait已接单
            } else if (s.equals("wait")) {
                //显示待服务  开始服务按钮
                holder.mSericeState.setText("待服务");
                //评价
                holder.mIndent2Layout1.setVisibility(View.GONE);
                //服务开始
                holder.mIndent2Layout2.setVisibility(View.VISIBLE);
                holder.mIndent2Layout21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog = new MaterialDialog(context)
                                .setTitle("提示")
                                .setMessage("您确定要开始服务?")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mKProgressHUD.show();
                                        mMaterialDialog.dismiss();
                                        JJJD(position, 2, "SellerStartWorking", holder.mIndent2Layout4, holder.mSericeState,null);
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
                //服务完成
                holder.mIndent2Layout3.setVisibility(View.GONE);
                //同意拒绝
                holder.mIndent2Layout4.setVisibility(View.GONE);
                //close已拒绝
            } else if (s.equals("close")) {
                holder.mSericeState.setText("已关闭");
                //显示已关闭 隐藏所有按钮
                holder.mIndent2Layout1.setVisibility(View.GONE);
                //服务开始
                holder.mIndent2Layout2.setVisibility(View.GONE);
                //服务完成
                holder.mIndent2Layout3.setVisibility(View.GONE);
                //同意拒绝
                holder.mIndent2Layout4.setVisibility(View.GONE);
                //working服务中
            } else if (s.equals("working")) {
                holder.mSericeState.setText("服务中");
                //confirm_complete服务方已确认
                holder.mIndent2Layout1.setVisibility(View.GONE);
                //服务开始
                holder.mIndent2Layout2.setVisibility(View.GONE);
                //服务完成
                holder.mIndent2Layout3.setVisibility(View.VISIBLE);
                //服务完成
                holder.mIndent2Layout31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog = new MaterialDialog(context)
                                .setTitle("提示")
                                .setMessage("您确定完成服务吗?")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mKProgressHUD.show();
                                        mMaterialDialog.dismiss();
                                        JJJD(position, 3, "SellerCompleteWork", holder.mIndent2Layout4, holder.mSericeState,null);
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
                //同意拒绝
                holder.mIndent2Layout4.setVisibility(View.GONE);
            } else if (s.equals("confirm_complete")) {
                holder.mSericeState.setText("待确认");
                //显示已关闭 隐藏所有按钮
                holder.mIndent2Layout1.setVisibility(View.GONE);
                //服务开始
                holder.mIndent2Layout2.setVisibility(View.GONE);
                //服务完成
                holder.mIndent2Layout3.setVisibility(View.GONE);
                //同意拒绝
                holder.mIndent2Layout4.setVisibility(View.GONE);
                //complete买家已确认
            } else if (s.equals("complete")) {
                if (str != null && !str.equals("")) {
                    if (str.equals("1")) {
                        holder.mSericeState.setText("服务完成");
                        //已经评价  显示服务完成
                        holder.mIndent2Layout1.setVisibility(View.GONE);
                        //服务开始
                        holder.mIndent2Layout2.setVisibility(View.GONE);
                        //服务完成
                        holder.mIndent2Layout3.setVisibility(View.GONE);
                        //同意拒绝
                        holder.mIndent2Layout4.setVisibility(View.GONE);
                    } else if (str.equals("0")) {
                        holder.mSericeState.setText("待评价");
                        //没有评价 显示待买家评价
                        holder.mIndent2Layout1.setVisibility(View.VISIBLE);
                        holder.mIndent2Layout11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, ServiceIndentPingjiaActivity.class);
                                intent.putExtra("goodsid", mData.get(position).getOrder_id());
                                intent.putExtra("position",position);
                                context.startActivity(intent);
                            }
                        });
                        //服务开始
                        holder.mIndent2Layout2.setVisibility(View.GONE);
                        //服务完成
                        holder.mIndent2Layout3.setVisibility(View.GONE);
                        //同意拒绝
                        holder.mIndent2Layout4.setVisibility(View.GONE);
                    }
                }
            }
        }
        String show_pic = mData.get(position).getShow_pic();
        String pic = mData.get(position).getPic();
        if (show_pic != null && !show_pic.equals("")) {
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + show_pic).fit().into(holder.mSericeImage);
        } else {
            holder.mSericeImage.setImageResource(R.drawable.icon_small_tx);
        }
        if (pic != null && !pic.equals("")) {
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + pic).fit().into(holder.mSericeImage1);
        } else {
            holder.mSericeImage1.setImageResource(R.drawable.img_fw_small);
        }
        holder.mSericeName.setText(mData.get(position).getOrder_username());
        holder.mSericeTitle.setText(mData.get(position).getTitle());
        holder.mSericeMoney.setText("¥" + mData.get(position).getPrice() + "/次");
        holder.mSericeCount.setText("x" + "1"/*mData.get(position).getNum()*/);
        holder.mSericeMoneys.setText("¥" + mData.get(position).getPrice() + "元");
        holder.mSericeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Serviceindentminute2Activity.class);
                intent.putExtra("pingjiaFlag",1);
                intent.putExtra("flag", "0");
                intent.putExtra("oid", mData.get(position).getOrder_id());
                KLog.e("oid",mData.get(position).getOrder_id());
                intent.putExtra("status", holder.mSericeState.getText().toString());
                KLog.e("status",holder.mSericeState.getText().toString());
                intent.putExtra("Tximage", mData.get(position).getShow_pic());
                KLog.e("Tximage",mData.get(position).getShow_pic());
                intent.putExtra("TXname", mData.get(position).getSeller_username());
                KLog.e("TXname", mData.get(position).getSeller_username());
                if (!mData.get(position).getPic().equals("")&&mData.get(position).getPic()!=null){
                    intent.putExtra("Phimage", mData.get(position).getPic());
                }
                KLog.e("Phimage",mData.get(position).getPic()+"");
                intent.putExtra("Title", mData.get(position).getTitle());
                KLog.e("Title",mData.get(position).getTitle());
                intent.putExtra("Price", mData.get(position).getPrice());
                intent.putExtra("whb_flag", position);
                KLog.e("Price",mData.get(position).getPrice());
                context.startActivity(intent);
               KLog.e("aaaa");
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.serice_image)
        CircleImageView mSericeImage;
        @Bind(R.id.serice_name)
        TextView mSericeName;
        @Bind(R.id.serice_state)
        TextView mSericeState;
        @Bind(R.id.serice_image1)
        ImageView mSericeImage1;
        @Bind(R.id.serice_title)
        TextView mSericeTitle;
        @Bind(R.id.serice_money)
        TextView mSericeMoney;
        @Bind(R.id.serice_count)
        TextView mSericeCount;
        @Bind(R.id.serice_layout)
        LinearLayout mSericeLayout;
        @Bind(R.id.serice_moneys)
        TextView mSericeMoneys;
        //评价
        @Bind(R.id.indent2_layout1)
        RelativeLayout mIndent2Layout1;
        //评价按钮
        @Bind(R.id.indent2_layout1_1)
        Button mIndent2Layout11;
        //开始服务
        @Bind(R.id.indent2_layout2)
        RelativeLayout mIndent2Layout2;
        //开始服务按钮
        @Bind(R.id.indent2_layout2_1)
        Button mIndent2Layout21;

        //服务完成
        @Bind(R.id.indent2_layout3)
        RelativeLayout mIndent2Layout3;
        //服务完成按钮
        @Bind(R.id.indent2_layout3_1)
        Button mIndent2Layout31;
        //拒绝   同意
        @Bind(R.id.indent2_layout4)
        RelativeLayout mIndent2Layout4;
        //拒绝按钮
        @Bind(R.id.indent2_layout4_1)
        Button mIndent2Layout41;
        //同意按钮
        @Bind(R.id.indent2_layout4_2)
        Button mIndent2Layout42;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void JJJD(final int position, final int flag, String action, final RelativeLayout layout, final TextView view, final RelativeLayout layout1) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", action);
        hashMap.put("uid", userid);
        hashMap.put("oid", mData.get(position).getOrder_id());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mKProgressHUD.dismiss();
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                if (code.equals("888")&&secess.equals("true")){
                    if (flag == 0) {
                        mData.get(position).setOrder_status("close");
                        notifyDataSetChanged();
                    }
                    if (flag == 1) {
                        mData.get(position).setOrder_status("wait");
                        notifyDataSetChanged();
                    }
                    if (flag==2){
                        mData.get(position).setOrder_status("working");
                        notifyDataSetChanged();
                    }
                    if (flag==3){
                        mData.get(position).setOrder_status("confirm_complete");
                        notifyDataSetChanged();
                    }
                }else{
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
