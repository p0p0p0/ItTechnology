package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.app.Activity;
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
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/30 0030.
 */
public class SchemeHallAdapter extends BaseAdapter {
    private Activity context;
    private List<SchemeBean.DataBean> dataBeen;

    public SchemeHallAdapter(Activity context, List<SchemeBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_schemehall, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mSchemehallCount.setText(position + 1 + "");
        viewHolder.mSchemehallDomain.setText(dataBeen.get(position).getIndus_pname()+" | "+dataBeen.get(position).getIndus_name());
        viewHolder.mSchemehallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SchemeDetailActivity.class);
                intent.putExtra("schemeid",dataBeen.get(position).getService_id());
                context.startActivity(intent);
            }
        });
        viewHolder.mSchemehallTitle.setText(dataBeen.get(position).getTitle());
        String url = dataBeen.get(position).getPic();
//        KLog.e("iamgeuuuuu"+url);
        if (url.equals("0")) {
            KLog.e("uri0", url);
        }else if(url.isEmpty()){
            KLog.e("uri空", url);
        }else{
//            KLog.e("uri", url);
            Picasso.with(context).load(RequestAddress.IMAGE1+url).into(viewHolder.mSchemehallImage);
        }
        String type = dataBeen.get(position).getPrice();
        Double cny0 = Double.parseDouble(type); //6.20
        if (cny0 > 0.00) {
            viewHolder.mSchemehallImageType.setImageResource(R.drawable.icon_sf);
        } else {
            viewHolder.mSchemehallImageType.setImageResource(R.drawable.icon_mf);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.schemehall_count)
        TextView mSchemehallCount;
        @Bind(R.id.schemehall_image)
        ImageView mSchemehallImage;
        @Bind(R.id.schemehall_title)
        TextView mSchemehallTitle;
        @Bind(R.id.schemehall_domain)
        TextView mSchemehallDomain;
        @Bind(R.id.schemehall_layout)
        LinearLayout mSchemehallLayout;
        @Bind(R.id.schemehall_image_type)
        ImageView mSchemehallImageType;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
