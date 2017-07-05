package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ChargeBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.TechnologyminuteActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/24 0024.
 */

public class TechnologyAdapter extends BaseAdapter {
    private Context context;
    private List<ChargeBean.DataBean> dataBeen;

    public TechnologyAdapter(Context context, List<ChargeBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(ECApplication.context, R.layout.item_technologs, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String price = dataBeen.get(position).getPic();
        if (price!=null&&!price.equals("")){
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 +price ).into(viewHolder.mPlayImage);
        }else{
            viewHolder.mPlayImage.setImageResource(R.drawable.img_pru7);
        }
        viewHolder.mTechnologText.setText(dataBeen.get(position).getTitle());
        viewHolder.mTechnologsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TechnologyminuteActivity.class);
                intent.putExtra("uid", dataBeen.get(position).getService_id());
                intent.putExtra("flag", "0");
                context.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHolder {
        @Bind(R.id.play_image)
        ImageView mPlayImage;
        @Bind(R.id.technolog_text)
        TextView mTechnologText;
        @Bind(R.id.technologs_layout)
        LinearLayout mTechnologsLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


       /* if (holder instanceof OneViewHolder) {
            ((OneViewHolder) holder).mTitle1.setText(dataBeen.get(position).getTitle());
            ((OneViewHolder) holder).mTitle2.setText(dataBeen.get(position+1).getTitle());
            ((OneViewHolder) holder).mTitle3.setText(dataBeen.get(position+2).getTitle());
            String imageUrl = dataBeen.get(position).getPicurl();
            KLog.e("imageUrl",imageUrl);
            if (!imageUrl.isEmpty()) {
                if (imageUrl.startsWith("article")){
                    Picasso.with(context).load(RequestAddress.IMAGE2
                            + imageUrl).fit().into(((OneViewHolder) holder).mImageb1);
                }else{
                    Picasso.with(context).load(imageUrl).into(((OneViewHolder) holder).mImageb1);
                }
            }
            String imageUrl1 = dataBeen.get(position+1).getPicurl();
            KLog.e("imageUrl",imageUrl);
            if (!imageUrl1.isEmpty()) {
                if (imageUrl.startsWith("article")){
                    Picasso.with(context).load(RequestAddress.IMAGE2
                            + imageUrl1).fit().into(((OneViewHolder) holder).mImageb2);
                }else{
                    Picasso.with(context).load(imageUrl).into(((OneViewHolder) holder).mImageb2);
                }
            }
            String imageUrl2 = dataBeen.get(position+2).getPicurl();
            KLog.e("imageUrl",imageUrl);
            if (!imageUrl2.isEmpty()) {
                if (imageUrl.startsWith("article")){
                    Picasso.with(context).load(RequestAddress.IMAGE2
                            + imageUrl2).fit().into(((OneViewHolder) holder).mImageb3);
                }else{
                    Picasso.with(context).load(imageUrl).into(((OneViewHolder) holder).mImageb3);
                }
            }
            ((OneViewHolder) holder).layout.setVisibility(View.GONE);
            ((OneViewHolder) holder).layout2.setVisibility(View.GONE);
            ((OneViewHolder) holder).layout3.setVisibility(View.GONE);
        }*/


}
