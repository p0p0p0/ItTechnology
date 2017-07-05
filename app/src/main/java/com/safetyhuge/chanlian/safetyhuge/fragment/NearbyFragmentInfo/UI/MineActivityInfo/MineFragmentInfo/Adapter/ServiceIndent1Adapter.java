package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.PayActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.PingjiaActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceindentminuteActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.servuceIndentBean;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
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
 * 作者：王海宾 on 2017/5/18 0018 10:41
 * 邮箱：995696826@qq.com
 */
public class ServiceIndent1Adapter extends BaseAdapter {
    //创建接口
    public OnListener onListener;
    MaterialDialog mDialog;
    private int mP;
    String userid;



    //接口
    public interface OnListener {
        //回调方法
        void onItemClick(int position);
    }

    public void setListener(OnListener listener) {
        this.onListener = listener;
    }

    Activity context;
    List<servuceIndentBean.DataBean> mData;
    String f = new String();
    KProgressHUD     mKProgressHUD;
    MaterialDialog mMaterialDialog;

    public ServiceIndent1Adapter(Activity context, List<servuceIndentBean.DataBean> mData,String userid) {
        this.mData = mData;
        this.context = context;
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
            convertView = View.inflate(ECApplication.context, R.layout.item_serice_a, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        KLog.e("whb", mData.get(position).getOrder_status());
        KLog.e("whb", mData.get(position).getMark_status());
        //用户名
        mData.get(position).getSeller_username();
        //
        String status = mData.get(position).getOrder_status();
        KLog.e("whb",status);
        if (status != null && !status.equals("")) {
           /* seller_confirm待付款，
            ok已付款，
            wait已接单，
            close已拒绝
            working服务中，
            confirm_complete服务方已确认，
            complete买家已确认*/
            if (status.equals("seller_confirm")) {
                //待付款
                holder.mSericeState.setText("待付款");
                holder.mHelpLayout1.setVisibility(View.VISIBLE);
                holder.mHelpLayout.setVisibility(View.GONE);
                holder.mHelpLayout11.setVisibility(View.GONE);
            } else if (status.equals("ok")) {
                //ok已付款
                holder.mSericeState.setText("待接单");
                holder.mHelpLayout1.setVisibility(View.GONE);
                holder.mHelpLayout.setVisibility(View.GONE);
                holder.mHelpLayout11.setVisibility(View.GONE);
            } else if (status.equals("wait")) {
                //wait已接单
                holder.mSericeState.setText("待服务");
                holder.mHelpLayout1.setVisibility(View.GONE);
                holder.mHelpLayout.setVisibility(View.GONE);
                holder.mHelpLayout11.setVisibility(View.GONE);
            } else if (status.equals("close")) {
                //close已拒绝
                holder.mSericeState.setText("已关闭");
                holder.mHelpLayout1.setVisibility(View.GONE);
                holder.mHelpLayout.setVisibility(View.GONE);
                holder.mHelpLayout11.setVisibility(View.GONE);
            } else if (status.equals("working")) {
                //working服务中
                holder.mSericeState.setText("服务中");
                holder.mHelpLayout1.setVisibility(View.GONE);
                holder.mHelpLayout.setVisibility(View.GONE);
                holder.mHelpLayout11.setVisibility(View.GONE);
            } else if (status.equals("confirm_complete")) {
                //confirm_complete服务方已确认
                holder.mSericeState.setText("待确认");
                holder.mHelpLayout1.setVisibility(View.GONE);
                holder.mHelpLayout.setVisibility(View.GONE);
                holder.mHelpLayout11.setVisibility(View.VISIBLE);
                holder.mButton11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog = new MaterialDialog(context)
                                .setTitle("提示")
                                .setMessage("您确定服务已经完成?")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mKProgressHUD.show();
                                        mMaterialDialog.dismiss();
                                        ShouHuo(position,mData.get(position).getOrder_id());
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
            } else if (status.equals("confirm")) {
                String markStatus = mData.get(position).getMark_status();
                if (markStatus != null && !markStatus.equals("")) {
                    if (markStatus.equals("1")) {
                        //已评价
                        //complete买家已确认
                        holder.mSericeState.setText("服务完成");
                        holder.mHelpLayout.setVisibility(View.GONE);
                        holder.mHelpLayout1.setVisibility(View.GONE);
                        holder.mHelpLayout11.setVisibility(View.GONE);
                    }
                    if (markStatus.equals("0")) {
                        //没评价
                        holder.mSericeState.setText("待评价");
                        holder.mHelpLayout1.setVisibility(View.GONE);
                        holder.mHelpLayout.setVisibility(View.VISIBLE);
                        holder.mHelpLayout11.setVisibility(View.GONE);
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
        holder.mSericeName.setText(mData.get(position).getSeller_username());
        holder.mSericeTitle.setText(mData.get(position).getTitle());
        holder.mSericeMoney.setText("¥" + mData.get(position).getPrice() + "/次");
        holder.mSericeCount.setText("x" + "1"/*mData.get(position).getNum()*/);
        holder.mSericeMoneys.setText("¥" + mData.get(position).getPrice() + "元");
        holder.mSericeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ServiceindentminuteActivity.class);
                intent.putExtra("pingjiaFlag",0);
                intent.putExtra("flag", "0");
                intent.putExtra("oid", mData.get(position).getOrder_id());
                intent.putExtra("status", holder.mSericeState.getText().toString());
                intent.putExtra("Tximage", mData.get(position).getShow_pic());
                intent.putExtra("TXname", mData.get(position).getSeller_username());
                intent.putExtra("Phimage", mData.get(position).getPic());
                intent.putExtra("Title", mData.get(position).getTitle());
                intent.putExtra("Price", mData.get(position).getPrice());
                intent.putExtra("Phone", mData.get(position).getSeller_info().getMobile());
                intent.putExtra("mPosition", position);
                context.startActivity(intent);
            }
        });
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mP = position;
                Intent intent = new Intent(context, PingjiaActivity.class);
                intent.putExtra("pingjiaMark",1);
                intent.putExtra("goodsid", mData.get(position).getOrder_id());
                intent.putExtra("position", mP);
                context.startActivity(intent);
            }
        });
        holder.mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mP = position;
                Intent intent = new Intent(context, PayActivity.class);
                intent.putExtra("money", mData.get(position).getPrice());
                intent.putExtra("mTitle", mData.get(position).getTitle());
                intent.putExtra("oid", mData.get(position).getOrder_id());
                intent.putExtra("mflag", "3");
                intent.putExtra("flags", "1");
                intent.putExtra("position", mP+"");
                context.startActivity(intent);
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
        @Bind(R.id.button)
        Button mButton;
        @Bind(R.id.help_layout)
        RelativeLayout mHelpLayout;
        @Bind(R.id.button2)
        Button mButton2;
        @Bind(R.id.help_layout1)
        RelativeLayout mHelpLayout1;

        @Bind(R.id.help_layout11)
        RelativeLayout mHelpLayout11;
        @Bind(R.id.button11)
        Button mButton11;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public void ShouHuo(final int p, String oid){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("action","ConfirmReceipt");
        hashMap.put("uid",userid);
        hashMap.put("oid",oid);
        OkGo.post(RequestAddress.HOST+RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                mKProgressHUD.dismiss();
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                if (code.equals("888")&&secess.equals("true")){
                    mData.get(p).setOrder_status("confirm");
                    mData.get(p).setMark_status("0");
                    notifyDataSetChanged();
                }
            }
        });
    }
}
