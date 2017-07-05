package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.SchemesBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.SchemeDetailActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/29 0029.
 */

public class SchenesAdapter extends BaseAdapter {
    private Context context;
    private List<SchemesBean.DataBean> dataBeen;

    public SchenesAdapter(Context context, List<SchemesBean.DataBean> dataBeen) {
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_schenes, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mSchenesCounts.setText(position + 1 + "");
        viewHolder.mSchenesDomain.setText(dataBeen.get(position).getIndus_pname());
        viewHolder.mSchenesTitle.setText(dataBeen.get(position).getTitle());
        viewHolder.mSchenesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SchemeDetailActivity.class);
                intent.putExtra("schemeid",dataBeen.get(position).getService_id());
                context.startActivity(intent);
            }
        });
        String pic = dataBeen.get(position).getPic();
        if (pic!=null&&!pic.equals("")){
            Picasso.with(context).load(RequestAddress.IMAGE1+pic).into(viewHolder.mSchenesIamge);
        }else{
            viewHolder.mSchenesIamge.setImageResource(R.drawable.img_pru2);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.schenes_layout)
        LinearLayout mSchenesLayout;
        @Bind(R.id.schenes_counts)
        TextView mSchenesCounts;
        @Bind(R.id.schenes_iamge)
        ImageView mSchenesIamge;
        @Bind(R.id.schenes_title)
        TextView mSchenesTitle;
        @Bind(R.id.schenes_domain)
        TextView mSchenesDomain;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
