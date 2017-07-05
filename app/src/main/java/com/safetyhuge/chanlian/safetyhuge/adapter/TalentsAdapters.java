package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.safetyhuge.chanlian.safetyhuge.Bean.TalentsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CertificateActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.InvitationActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.TalentsMsgActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/23 0023.
 */

public class TalentsAdapters extends BaseAdapter {
    private List<TalentsBean.DataBean> dataBeen;
    private Bitmap mBitmap;
    private ViewHolder mViewHolder;
    private int a;
    private Activity context;
    private String mUrl;
    String id;
    String id1;
    int count = 0;
    private List<TalentsBean.DataBean.CertificateInfoBean> mCertificate_info;
    private StringBuffer mStringBuffer;

    public TalentsAdapters(Activity context, List<TalentsBean.DataBean> dataBeen, int a) {
        this.dataBeen = dataBeen;
        this.a = a;
        this.context = context;
    }


    @Override
    public int getCount() {
        if (a == 1) {
            return 3;
        } else {
            return dataBeen.size();
        }
    }

    public void setUid(String id) {
        this.id = id;
    }

    public String getUid() {
        return id1;
    }

    @Override
    public Object getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        count++;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_rcdats, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        if (count < 3) {
            KLog.e("count1111", count);
            mViewHolder.mTvCount.setTextColor(Color.RED);
        } else {
            mViewHolder.mTvCount.setTextColor(CommonUtil.getColor(R.color.text_brown));
        }
        //星级
        int level = dataBeen.get(position).getCreditLevel().getLevel();
        //状态
        String user_type = dataBeen.get(position).getWork_status();
        if (user_type.equals("0")) {
            //个人
            mViewHolder.mRcdatsZhuangtai.setText("自由职业");
            mViewHolder.mRcdatsZhuangtai.setBackgroundResource(R.drawable.occupation_ziyou);
        } else {
            mViewHolder.mRcdatsZhuangtai.setText("企业就职");
            mViewHolder.mRcdatsZhuangtai.setBackgroundResource(R.drawable.occupation_jiuzhi);
        }
        dengji(level, mViewHolder.mRcdatsDengji);
        //证书
        if (!dataBeen.get(position).getCertificate_info().isEmpty()) {
            mCertificate_info = dataBeen.get(position).getCertificate_info();
            mStringBuffer = new StringBuffer();
            for (TalentsBean.DataBean.CertificateInfoBean certificateInfoBean : mCertificate_info) {
                mStringBuffer.append(certificateInfoBean.getName());
            }
            mViewHolder.mRcdatsZhengshu.setText("证书: " + mStringBuffer);
        } else {
            mViewHolder.mRcdatsZhengshu.setText("证书: 暂无");
        }
        SharedPrefsUtil.put(context, "yaoqingid" + position, dataBeen.get(position).getUid());
        mViewHolder.mTvCount.setText(position + 1 + "");
        String ly = dataBeen.get(position).getSkill_ids();
        String indus_pname = dataBeen.get(position).getIndus_pname();
        String indus_name = dataBeen.get(position).getIndus_name();
        if (indus_pname!=null&&!indus_pname.equals("")){
            mViewHolder.mTvBehavior.setText("项目领域: " + indus_pname);
            if (indus_name!=null&&!indus_name.equals("")){
                mViewHolder.mTvBehavior.setText("项目领域: " + indus_pname+"|"+indus_name);
            }
        }
  /*      if (!ly.equals("")) {
            mViewHolder.mTvBehavior.setText("项目领域: " + ly);
        } else {
            mViewHolder.mTvBehavior.setText("项目领域:  无");
        }*/
        String imagheUrl = dataBeen.get(position).getShow_pic();
    /*    if (imagheUrl != null) {
            KLog.e("imageUrl", imagheUrl);
            String str = imagheUrl.substring(2, imagheUrl.length());
        }*/
        mUrl = RequestAddress.IMAGE1 + imagheUrl;
//        KLog.e("imageUrl", mUrl);
        Picasso.with(ECApplication.context).load(mUrl).fit().into(mViewHolder.mImageHead);
        mViewHolder.mTextName.setText(dataBeen.get(position).getUsername());
        mViewHolder.mImageHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        String cx = dataBeen.get(position).getCx_status();
//        KLog.e("cx",cx);
        if (cx.equals("1")){
            mViewHolder.mCxYonghu.setVisibility(View.VISIBLE);
        }
        //邀请竞标点击事件
        mViewHolder.mBtInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = dataBeen.get(position).getUid();
                KLog.e("dididi", s);
                if (id.equals(s)) {
                    Toast.makeText(context, "不能邀请自己", Toast.LENGTH_SHORT).show();
                    return;
                } else if (id != "") {
                    id1 = dataBeen.get(position).getUid();
                    Intent intent = new Intent(context, InvitationActivity.class);
                    intent.putExtra("yaoqing", dataBeen.get(position).getUid());
                    context.startActivity(intent);
                } else {
                    showNormalDialog();
                }
            }
        });
        mViewHolder.mLayoutRcxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imagheUrl = dataBeen.get(position).getShow_pic();
                KLog.e("imageUrl", imagheUrl);
                mUrl = RequestAddress.IMAGE1 + imagheUrl;
                Intent intent = new Intent(context, TalentsMsgActivity.class);
                intent.putExtra("ImageUrl", mUrl);
                intent.putExtra("name", dataBeen.get(position).getUsername());
                intent.putExtra("region", dataBeen.get(position).getUsername());
                String indus_pname = dataBeen.get(position).getIndus_pname();
                String indus_name = dataBeen.get(position).getIndus_name();
                if (indus_pname!=null&&!indus_pname.equals("")){
                    intent.putExtra("territory", indus_pname);
                    if (indus_name!=null&&!indus_name.equals("")){
                        intent.putExtra("territory", indus_pname+"|"+indus_name);
                    }
                }
                intent.putExtra("uid", dataBeen.get(position).getUid());
                intent.putExtra("talentsid", dataBeen.get(position).getUid());
                //等级
                //状态
                intent.putExtra("dengji", dataBeen.get(position).getCreditLevel().getLevel());
                intent.putExtra("zhuangtai", dataBeen.get(position).getWork_status());
                KLog.e("whb",dataBeen.get(position).getCertificate_info().size());
             if (!dataBeen.get(position).getCertificate_info().isEmpty()) {
                    intent.putExtra("zhengshu", mStringBuffer+"");
               //     KLog.e("zhengshu11111", dataBeen.get(position).getCertificate_info().get(position).getName());
                }
                ArrayList<String> stringList = new ArrayList<String>();
                List<TalentsBean.DataBean.CertificateInfoBean> certificate_info = dataBeen.get(position).getCertificate_info();
                if (!certificate_info.isEmpty()) {
                    for (TalentsBean.DataBean.CertificateInfoBean certificateInfoBean : certificate_info) {
                        stringList.add(RequestAddress.IMAGE1+certificateInfoBean.getPic());
                    }
                    intent.putStringArrayListExtra("imageUrl", stringList);
                    KLog.e("imageUrl11111", stringList.size());
                }
                KLog.e("whb",mUrl);
                KLog.e("name",dataBeen.get(position).getUsername());
                KLog.e("region",dataBeen.get(position).getUsername());
                KLog.e("territory",dataBeen.get(position).getSkill_ids());
                KLog.e("talentsid",dataBeen.get(position).getUid());
                KLog.e("dengji",dataBeen.get(position).getCreditLevel().getLevel());
                KLog.e("zhuangtai",dataBeen.get(position).getWork_status());
                context.startActivity(intent);
            }
        });
        //查看证书
        mViewHolder.rcdats_dianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CertificateActivity.class);
                ArrayList<String> stringList = new ArrayList<String>();
                List<TalentsBean.DataBean.CertificateInfoBean> certificate_info = dataBeen.get(position).getCertificate_info();
                if (!certificate_info.isEmpty()) {
                    for (TalentsBean.DataBean.CertificateInfoBean certificateInfoBean : certificate_info) {
                        stringList.add(RequestAddress.IMAGE1+certificateInfoBean.getPic());
                    }
                    intent.putStringArrayListExtra("image", stringList);
                    context.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_count)
        TextView mTvCount;
        @Bind(R.id.image_head)
        ImageView mImageHead;
        @Bind(R.id.text_name)
        TextView mTextName;
        @Bind(R.id.tv_behavior)
        TextView mTvBehavior;
        @Bind(R.id.bt_invite)
        Button mBtInvite;
        @Bind(R.id.cx_yonghu)
        ImageView mCxYonghu;
        @Bind(R.id.layout_rcxinxi)
        LinearLayout mLayoutRcxinxi;
        @Bind(R.id.rcdats_dianji)
        LinearLayout rcdats_dianji;
        @Bind(R.id.rcdats_dengji)
        ImageView mRcdatsDengji;
        @Bind(R.id.rcdats_zhuangtai)
        TextView mRcdatsZhuangtai;
        @Bind(R.id.rcdats_zhengshu)
        TextView mRcdatsZhengshu;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void showNormalDialog() {
        new CBDialogBuilder(context)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle("提示")
                .setMessage("请先登录")
                .setConfirmButtonText("确定")
                .setCancelButtonText("取消").setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                switch (whichBtn) {
                    case BUTTON_CONFIRM:
                        context.startActivity(new Intent(context, LoginsActivity.class));
                        break;
                    case BUTTON_CANCEL:
                        break;
                    default:
                        break;
                }
            }
        })
                .setDialogAnimation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                .create()
                .show();
    }

    public void dengji(int dengji, ImageView view) {
        switch (dengji) {
            case 0:
                break;
            case 1:
                view.setImageResource(R.drawable.n1);
                break;
            case 2:
                view.setImageResource(R.drawable.n2);
                break;
            case 3:
                view.setImageResource(R.drawable.n3);
                break;
            case 4:
                view.setImageResource(R.drawable.n4);
                break;
            case 5:
                view.setImageResource(R.drawable.n5);
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                view.setImageResource(R.drawable.n8);
                break;
            case 9:
                view.setImageResource(R.drawable.n9);
                break;
            case 10:
                view.setImageResource(R.drawable.n10);
                break;
            case 11:
                view.setImageResource(R.drawable.n11);
                break;
            case 12:
                view.setImageResource(R.drawable.n12);
                break;
            case 13:
                view.setImageResource(R.drawable.n13);
                break;
            case 14:
                view.setImageResource(R.drawable.n14);
                break;
            case 15:
                view.setImageResource(R.drawable.n15);
                break;
            case 16:
                view.setImageResource(R.drawable.n16);
                break;
            case 17:
                view.setImageResource(R.drawable.n17);
                break;
            case 18:
                view.setImageResource(R.drawable.n18);
                break;
            case 19:
                view.setImageResource(R.drawable.n19);
                break;
            case 20:
                view.setImageResource(R.drawable.n20);
                break;
            case 21:
                view.setImageResource(R.drawable.n21);
                break;

        }
    }
}
