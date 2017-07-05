package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.LikeBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/24 0024.
 */

public class LikeAdapter extends BaseAdapter {
    private Context context;
    private List<LikeBean.DataBean> dataBeen;

    public LikeAdapter(Context context, List<LikeBean.DataBean> dataBeen) {
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
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_like, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String iamgeurl = RequestAddress.IMAGE1+dataBeen.get(position).getPic();
        //截取#之前的字符串
        String str = iamgeurl;
        if(str.indexOf(",")!=-1){
            String b = str.substring(0, str.indexOf(","));
            KLog.e("indexof",str.substring(0, str.indexOf(",")));
            Picasso.with(context).load(b).into(viewHolder.mGoodsImage);
        }else{
            System.out.println(str);
            Picasso.with(context).load(str).into(viewHolder.mGoodsImage);
        }
      // str.substring(0, str.indexOf(","));
       // KLog.e("iamgeurl",str.substring(0, str.indexOf(",")));
        viewHolder.mGoodsName.setText(dataBeen.get(position).getTitle());
        viewHolder.mGoodsPice.setText(dataBeen.get(position).getPrice());
        viewHolder.goods_particular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsMinuteActivity.class);
                intent.putExtra("goodsid",dataBeen.get(position).getService_id()+"");
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.goods_image)
        ImageView mGoodsImage;
        @Bind(R.id.goods_particular)
        LinearLayout goods_particular;
        @Bind(R.id.goods_name)
        TextView mGoodsName;
        @Bind(R.id.goods_pice)
        TextView mGoodsPice;
        @Bind(R.id.goods_volume)
        TextView mGoodsVolume;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
