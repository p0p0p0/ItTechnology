package com.safetyhuge.chanlian.safetyhuge.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.SchemeBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.SchemeDetailActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Chinalink on 2017/3/23 0023.
 */

public class SchemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SchemeBean.DataBean> dataBeen;
    private int mB;

    public SchemeAdapter(Context context, List<SchemeBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getItemCount() {
        KLog.e("dataBeen",dataBeen.size());
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(ECApplication.context);
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                View v1 = mInflater.inflate(R.layout.item_linear, parent, false);
                holder = new TwoViewHolder(v1);
                break;
            case 1:
                View v2 = mInflater.inflate(R.layout.item_two, parent, false);
                holder = new OneViewHolder(v2);
                break;
            case 2:
                View v3 = mInflater.inflate(R.layout.item_linear1, parent, false);
                holder = new TwoViewHolder2(v3);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TwoViewHolder) {
            ((TwoViewHolder) holder).mName1.setText(dataBeen.get(position).getTitle());
            KLog.e("getTitle",dataBeen.get(position).getTitle());
            ((TwoViewHolder) holder).imagea1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SchemeDetailActivity.class);
                    intent.putExtra("schemeid",dataBeen.get(position).getService_id());
                    context.startActivity(intent);
                }
            });
            Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position).getPic()).fit().into(((TwoViewHolder) holder).imagea1);
            ((TwoViewHolder) holder).mName2.setText(dataBeen.get(position + 1).getTitle());
            Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position + 1).getPic()).fit().into(((TwoViewHolder) holder).imagea2);
            ((TwoViewHolder) holder).imagea2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SchemeDetailActivity.class);
                    intent.putExtra("schemeid",dataBeen.get(position+ 1).getService_id());
                    context.startActivity(intent);
                }
            });
        }
        if (holder instanceof OneViewHolder) {
            ((OneViewHolder) holder).mTitle1.setText(dataBeen.get(position + 1).getTitle());
            Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position + 1).getPic()).fit().into(((OneViewHolder) holder).mImageb1);
            KLog.e("imageurlsss",dataBeen.get(position+ 1).getPic());

            ((OneViewHolder) holder).mImageb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SchemeDetailActivity.class);
                    intent.putExtra("schemeid",dataBeen.get(position+ 1).getService_id());
                    KLog.e("schemeidschemeid",dataBeen.get(position+ 1).getService_id());
                    context.startActivity(intent);
                }
            });
            ((OneViewHolder) holder).mTitle2.setText(dataBeen.get(position + 2).getTitle());
            Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position + 2).getPic()).fit().into(((OneViewHolder) holder).mImageb2);
            ((OneViewHolder) holder).mImageb2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SchemeDetailActivity.class);
                    intent.putExtra("schemeid",dataBeen.get(position+ 2).getService_id());
                    context.startActivity(intent);
                }
            });
            ((OneViewHolder) holder).mTitle3.setText(dataBeen.get(position + 3).getTitle());
            Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position + 3).getPic()).fit().into(((OneViewHolder) holder).mImageb3);
            ((OneViewHolder) holder).mImageb3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SchemeDetailActivity.class);
                    intent.putExtra("schemeid",dataBeen.get(position+ 3).getService_id());
                    context.startActivity(intent);
                }
            });
        }
        if (holder instanceof TwoViewHolder2) {
            ((TwoViewHolder2) holder).mName3.setText(dataBeen.get(position + 3).getTitle());
            Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position + 3).getPic()).fit().into(((TwoViewHolder2) holder).imagec1);
            ((TwoViewHolder2) holder).imagec1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SchemeDetailActivity.class);
                    intent.putExtra("schemeid",dataBeen.get(position+ 3).getService_id());
                    context.startActivity(intent);
                }
            });
            ((TwoViewHolder2) holder).mName4.setText(dataBeen.get(position + 4).getTitle());
            Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position + 4).getPic()).fit().into(((TwoViewHolder2) holder).imagec2);
            ((TwoViewHolder2) holder).imagec2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SchemeDetailActivity.class);
                    intent.putExtra("schemeid",dataBeen.get(position+ 4).getService_id());
                    context.startActivity(intent);
                }
            });
        }
    }


    class TwoViewHolder extends RecyclerView.ViewHolder {

        private TextView mName1, mName2;
        private ImageView imagea1, imagea2;

        public TwoViewHolder(View itemView) {
            super(itemView);
            mName1 = (TextView) itemView.findViewById(R.id.name1);
            mName2 = (TextView) itemView.findViewById(R.id.name2);
            imagea1 = (ImageView) itemView.findViewById(R.id.imagea1);
            imagea2 = (ImageView) itemView.findViewById(R.id.imagea2);
        }
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle1, mTitle2, mTitle3;
        private ImageView mImageb1, mImageb2, mImageb3;

        public OneViewHolder(View itemView) {
            super(itemView);
            mTitle1 = (TextView) itemView.findViewById(R.id.title1);
            mTitle2 = (TextView) itemView.findViewById(R.id.title2);
            mTitle3 = (TextView) itemView.findViewById(R.id.title3);
            mImageb1 = (ImageView) itemView.findViewById(R.id.imageb1);
            mImageb2 = (ImageView) itemView.findViewById(R.id.imageb2);
            mImageb3 = (ImageView) itemView.findViewById(R.id.imageb3);
        }
    }

    class TwoViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView mName3, mName4;
        private final ImageView imagec1, imagec2;

        public TwoViewHolder2(View itemView) {
            super(itemView);
            mName3 = (TextView) itemView.findViewById(R.id.name3);
            mName4 = (TextView) itemView.findViewById(R.id.name4);
            imagec1 = (ImageView) itemView.findViewById(R.id.imagec1);
            imagec2 = (ImageView) itemView.findViewById(R.id.imagec2);
        }
    }
}
