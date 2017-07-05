package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.FuWuXiangQingActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.widget.NearByInfo;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/5/26 0026 09:08
 * 邮箱：995696826@qq.com
 */
public class FuwuSouShuoActivity extends HBaseAct {
    @Bind(R.id.sousuo_et_text)
    EditText mSousuoEtText;
    @Bind(R.id.fuwu_soushuo)
    LinearLayout mFuwuSoushuo;
    @Bind(R.id.sousuo_listview)
    MyListView mSousuoListview;

    @Override
    protected void onCreate(Bundle arg0) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(arg0);
        setContentView(R.layout.activity_sousuo);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final Context context = FuwuSouShuoActivity.this;
        final List<NearByInfo.DataBean> beans = (List<NearByInfo.DataBean>) intent.getSerializableExtra("FuWuInfo");
        KLog.e("beans", beans.size());
        mSousuoEtText.requestFocus();
        mSousuoEtText.addTextChangedListener(new TextWatcher() {
            @SuppressLint("WrongConstant")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String change = s + "";
                if (change.equals("")){
                    mSousuoListview.setVisibility(View.INVISIBLE);
                }else{
                    mSousuoListview.setVisibility(View.VISIBLE);
                    List<NearByInfo.DataBean> mDataBean = new ArrayList<>();
                    for (NearByInfo.DataBean bean : beans) {
                        if (bean.getTitle().indexOf(change)!=-1) {
                            mDataBean.add(bean);
                        }
                        mSousuoListview.setAdapter(new SouSuoAdapter(mDataBean, context));
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
    public void back_text_view(View view) {
        exitAct();
    }
}

class SouSuoAdapter extends BaseAdapter {
    List<NearByInfo.DataBean> mDataBean;
    Context context;

    public SouSuoAdapter(List<NearByInfo.DataBean> mDataBean, Context context) {
        this.mDataBean = mDataBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        KLog.e("mDataBean",mDataBean.size());
        return mDataBean.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_sousuo_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mSousuoTitle.setText(mDataBean.get(position).getTitle());
        holder.mSousuoContent.setText(mDataBean.get(position).getType());
        holder.mSousuoMoney.setText(mDataBean.get(position).getMoney());
        Picasso.with(context).load(mDataBean.get(position).getMedal()).fit().into(holder.mSousuoImage);
        holder.mSousuoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FuWuXiangQingActivity.class);
                //服务id
                intent.putExtra("sid", mDataBean.get(position).getId());
                //图片
                intent.putExtra("image", mDataBean.get(position).getUrl());
                //标题
                intent.putExtra("title", mDataBean.get(position).getTitle());
                //钱数
                intent.putExtra("money", mDataBean.get(position).getMoney());
                //头像图片
                intent.putExtra("useriamge", mDataBean.get(position).getMedal());
                //名称
                intent.putExtra("username", mDataBean.get(position).getName());
                //内容
                intent.putExtra("content", mDataBean.get(position).getType());
                //电话
                intent.putExtra("phone", mDataBean.get(position).getPhone());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.sousuo_image)
        CircleImageView mSousuoImage;
        @Bind(R.id.sousuo_title)
        TextView mSousuoTitle;
        @Bind(R.id.sousuo_content)
        TextView mSousuoContent;
        @Bind(R.id.sousuo_money)
        TextView mSousuoMoney;
        @Bind(R.id.sousuo_layout)
        LinearLayout mSousuoLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
