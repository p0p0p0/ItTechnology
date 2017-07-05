package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.FananFragmentInfo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.FananYsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.SchemeDetailActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/21 0021 20:59
 * 邮箱：995696826@qq.com
 */
public class FananYsAdapter extends BaseAdapter {
    List<FananYsBean.DataBean> data;
    FragmentActivity activity;
    int flag;

    public FananYsAdapter(List<FananYsBean.DataBean> data, FragmentActivity activity, int flag) {
        this.data = data;
        this.activity = activity;
        this.flag = flag;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.item_fananys, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (flag==1){
            viewHolder.mSchemehallDomain1.setText("付款人数: " + "");
        }else{
            String timedate = DateUtils.timedate(data.get(position).getOrder_time());
            viewHolder.mSchemehallTitle.setText("购买时间: "+timedate);
        }
        viewHolder.mSchemehallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SchemeDetailActivity.class);
                intent.putExtra("schemeid", data.get(position).getService_id());
                activity.startActivity(intent);
            }
        });
        viewHolder.mSchemehallTitle1.setText(data.get(position).getTitle());
        String url = data.get(position).getPic();
        KLog.e("iamgeuuuuu" + url);
        if (url.equals("0")) {
            KLog.e("uri0", url);
        } else if (url.isEmpty()) {
            KLog.e("uri空", url);
        } else {
            KLog.e("uri", url);
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + url).fit().into(viewHolder.mSchemehallImage1);
        }
        return convertView;
    }

    static  class ViewHolder {
        @Bind(R.id.schemehall_image1)
        ImageView mSchemehallImage1;
        @Bind(R.id.schemehall_title1)
        TextView mSchemehallTitle1;
        @Bind(R.id.schemehall_title)
        TextView mSchemehallTitle;
        @Bind(R.id.schemehall_domain1)
        TextView mSchemehallDomain1;
        @Bind(R.id.schemehall_layout)
        LinearLayout mSchemehallLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
