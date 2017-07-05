package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.SchemeBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.SchemeDetailActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/22 0022 16:40
 * 邮箱：995696826@qq.com
 */
public class SchemesAdapter extends BaseAdapter {
    Context context;
    List<SchemeBean.DataBean> dataBeen;

    public SchemesAdapter(Context context, List<SchemeBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return 6;
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
            convertView = View.inflate(context, R.layout.item_schemehas, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position).getPic()).into(viewHolder.mImagea1);
        viewHolder.mName1.setText( dataBeen.get(position).getTitle());
        viewHolder.mSchemehasLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SchemeDetailActivity.class);
                intent.putExtra("schemeid",dataBeen.get(position).getService_id());
                context.startActivity(intent);
            }
        });
        viewHolder.time.setText(DateUtils.timesTwo(dataBeen.get(position).getOn_time()));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.imagea1)
        ImageView mImagea1;
        @Bind(R.id.name1)
        TextView mName1;
        @Bind(R.id.scheme_time)
        TextView time;
        @Bind(R.id.schemehas_layout)
        LinearLayout mSchemehasLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
