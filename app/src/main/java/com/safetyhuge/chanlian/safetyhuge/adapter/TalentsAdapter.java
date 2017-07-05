package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.TalentsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.InvitationActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.TalentsMsgActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
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

public class TalentsAdapter extends BaseAdapter {

    private List<TalentsBean.DataBean> dataBeen;
    private Bitmap mBitmap;
    private ViewHolder mViewHolder;
    private int a;
    Context context;
    private String mUserid;

    public TalentsAdapter(Context context, List<TalentsBean.DataBean> dataBeen, int a) {
        this.dataBeen = dataBeen;
        this.context = context;
        this.a = a;
    }

    @Override
    public int getCount() {
        if (a == 1) {
            return 3;
        } else {
            return dataBeen.size() - 1;
        }
    }

    @Override
    public Object getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private StringBuffer mStringBuffer;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_rcdat, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        String imagheUrl = dataBeen.get(position).getShow_pic();
       /* KLog.e("imageUrl", imagheUrl);
        String str = imagheUrl.substring(2, imagheUrl.length());*/
        String url = RequestAddress.IMAGE1 + imagheUrl;
        KLog.e("url", url);
        Picasso.with(context).load(url).fit().into(mViewHolder.mImageHead);
        mViewHolder.mTextName.setText(dataBeen.get(position).getUsername());
        mViewHolder.mLayoutBehavior.setText(dataBeen.get(position).getSkill_ids());
        mViewHolder.mRencaiCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String imagheUrl = dataBeen.get(position).getShow_pic();
                String mUrl = RequestAddress.IMAGE1 + imagheUrl;
                KLog.e("mUrl", mUrl);
                Intent intent = new Intent(context, TalentsMsgActivity.class);
                intent.putExtra("ImageUrl", mUrl);
                intent.putExtra("name", dataBeen.get(position).getUsername());
                intent.putExtra("region", dataBeen.get(position).getUsername());
                intent.putExtra("uid", dataBeen.get(position).getUid());
                intent.putExtra("territory", dataBeen.get(position).getSkill_ids());
                intent.putExtra("talentsid", dataBeen.get(position).getUid());
                KLog.e("idid", dataBeen.get(position).getUid());
                context.startActivity(intent);*/
                String imagheUrl = dataBeen.get(position).getShow_pic();
                KLog.e("imageUrl", imagheUrl);
                String  mUrl = RequestAddress.IMAGE1 + imagheUrl;
                Intent intent = new Intent(context, TalentsMsgActivity.class);
                intent.putExtra("ImageUrl", mUrl);
                intent.putExtra("name", dataBeen.get(position).getUsername());
                intent.putExtra("region", dataBeen.get(position).getUsername());
                intent.putExtra("territory", dataBeen.get(position).getSkill_ids());
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
       mViewHolder.mRcdatButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String s = dataBeen.get(position).getUid();
               KLog.e("dididi", s);
               if (mUserid.equals(s)) {
                   Toast.makeText(context, "不能邀请自己", Toast.LENGTH_SHORT).show();
                   return;
               } else if (mUserid != "") {
                   String id1 = dataBeen.get(position).getUid();
                   Intent intent = new Intent(context, InvitationActivity.class);
                   intent.putExtra("yaoqing", dataBeen.get(position).getUid());
                   context.startActivity(intent);
               } else {
                   showNormalDialog();
               }
           }
       });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.image_head)
        CircleImageView mImageHead;
        @Bind(R.id.text_name)
        TextView mTextName;
        @Bind(R.id.layout_behavior)
        TextView mLayoutBehavior;
        @Bind(R.id.rencai_cl)
        LinearLayout mRencaiCl;
        @Bind(R.id.rcdat_button)
        Button mRcdatButton;

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
}
