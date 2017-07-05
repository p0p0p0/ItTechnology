package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.ProductBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/29 0029.
 */

public class ProductAcapter extends BaseAdapter {
    private Context context;
    private List<ProductBean.DataBean> dataBeen;

    public ProductAcapter(Context context, List<ProductBean.DataBean> dataBeen) {
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
            convertView = View.inflate(context, R.layout.item_product, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mLayoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsMinuteActivity.class);
                intent.putExtra("goodsid",dataBeen.get(position).getService_id()+"");
                context.startActivity(intent);
            }
        });
        Picasso.with(context).load(RequestAddress.IMAGE1+dataBeen.get(position).getPic()).fit().
                into(viewHolder.mProductIamge);
        viewHolder.mProductTitle.setText(dataBeen.get(position).getTitle());
        viewHolder.mProductMoney.setText(dataBeen.get(position).getPrice());
        viewHolder.mProductCount.setText("月销量:  "+dataBeen.get(position).getSale_num());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.product_iamge)
        ImageView mProductIamge;
        @Bind(R.id.product_title)
        TextView mProductTitle;
        @Bind(R.id.product_money)
        TextView mProductMoney;
        @Bind(R.id.product_count)
        TextView mProductCount;
        @Bind(R.id.layout_product)
        LinearLayout mLayoutProduct;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
