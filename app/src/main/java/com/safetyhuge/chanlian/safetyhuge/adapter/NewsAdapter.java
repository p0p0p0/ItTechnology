package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.NewsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.NewsInfoActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/10 0010 18:02
 * 邮箱：995696826@qq.com
 */
public class NewsAdapter extends BaseAdapter {
    List<NewsBean.DataBean> data;
    Context context;
    public NewsAdapter(List<NewsBean.DataBean> data, Context context) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size()/2-1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_goodsdg, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String time = data.get(position+1).getPosttime();
        String s = DateUtils.timesTwo(time);
        String image = data.get(position+1).getPicurl();
        Picasso.with(ECApplication.context).load(RequestAddress.IMAGE2+image).into(viewHolder.mGoodsDgImage
        );
//        KLog.e("RequestAddress.IMAGE2+image" +RequestAddress.IMAGE2+image);
        viewHolder.mGoodsDgTitle.setText(data.get(position+1).getTitle());
        viewHolder.mGoodsDgCount.setText(s);
        viewHolder.mGoodsDgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = data.get(position+1).getContent();
                Intent intent = new Intent(context,NewsInfoActivity.class);
                intent.putExtra("newsurl",url);
                intent.putExtra("newtitle",data.get(position+1).getTitle());
                intent.putExtra("time", data.get(position+1).getPosttime());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.goods_dg_image)
        ImageView mGoodsDgImage;
        @Bind(R.id.goods_dg_title)
        TextView mGoodsDgTitle;
        @Bind(R.id.goods_dg_region)
        TextView mGoodsDgRegion;
        @Bind(R.id.goods_dg_money)
        TextView mGoodsDgMoney;
        @Bind(R.id.goods_dg_count)
        TextView mGoodsDgCount;
        @Bind(R.id.goods_dg_click)
        LinearLayout mGoodsDgClick;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
