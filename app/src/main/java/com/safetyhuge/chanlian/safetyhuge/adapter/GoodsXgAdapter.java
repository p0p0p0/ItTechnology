package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsXgBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/28 0028.
 */
public class GoodsXgAdapter extends BaseAdapter {
    private List<GoodsXgBean.DataBean> dataBeen;
    private Context context;
      public GoodsXgAdapter(Context context,List<GoodsXgBean.DataBean> dataBeen) {
          this.dataBeen = dataBeen;
          this.context = context;
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
            convertView = View.inflate(context, R.layout.item_goodsxg, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mGoodsxgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsMinuteActivity.class);
                intent.putExtra("goodsid",dataBeen.get(position).getService_id()+"");
                context.startActivity(intent);
            }
        });
        String iamgeurl = RequestAddress.IMAGE1+dataBeen.get(position).getPic();
        //截取#之前的字符串
        String str = iamgeurl;
        if(str.indexOf(",")!=-1){
            String b = str.substring(0, str.indexOf(","));
            KLog.e("indexof",str.substring(0, str.indexOf(",")));
            Picasso.with(context).load(b).fit().into(viewHolder.mGoodsxgImage);
        }else{
            System.out.println(str);
            Picasso.with(context).load(str).fit().into(viewHolder.mGoodsxgImage);
        }
        viewHolder.mGoodsxgTitle.setText(dataBeen.get(position).getTitle());
        viewHolder.mGoodsxgMoney.setText("￥ "+dataBeen.get(position).getPrice());
        viewHolder.mGoodsxgCount.setText(dataBeen.get(position).getSale_num()+"人已买");
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.goodsxg_image)
        ImageView mGoodsxgImage;
        @Bind(R.id.goodsxg_title)
        TextView mGoodsxgTitle;
        @Bind(R.id.goodsxg_money)
        TextView mGoodsxgMoney;
        @Bind(R.id.goodsxg_count)
        TextView mGoodsxgCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
