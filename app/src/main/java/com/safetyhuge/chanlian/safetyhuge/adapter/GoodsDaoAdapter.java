package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsDaoBean;
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
public class GoodsDaoAdapter extends BaseAdapter {
    private List<GoodsDaoBean.DataBean> dataBeen;
    private Activity context;
    int aa;

    public GoodsDaoAdapter(Activity context, List<GoodsDaoBean.DataBean> dataBeen, int aa) {
        this.aa = aa;
        this.dataBeen = dataBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        KLog.e("whb",dataBeen.size());
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
            convertView = View.inflate(context, R.layout.item_goodsdg, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mGoodsDgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aa == 1) {
                    Intent intent = new Intent(context, GoodsMinuteActivity.class);
                    intent.putExtra("goodsid", dataBeen.get(position).getService_id() + "");
                    intent.putExtra("iamge", dataBeen.get(position).getFile_path());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, GoodsMinuteActivity.class);
                    intent.putExtra("goodsid", dataBeen.get(position).getService_id() + "");
                    context.startActivity(intent);
                }
            }
        });
       if (aa == 1) {
            String iamgeurl = RequestAddress.IMAGE1 + dataBeen.get(position).getPic();
            Picasso.with(context).load(iamgeurl).into(viewHolder.mGoodsDgImage);
        } else {
            String iamgeurl = RequestAddress.IMAGE1 + dataBeen.get(position).getPic();
            //截取#之前的字符串
            String str = iamgeurl;
            if (str.indexOf(",") != -1) {
                String b = str.substring(0, str.indexOf(","));
                Picasso.with(context).load(b).into(viewHolder.mGoodsDgImage);
            } else {
                Picasso.with(context).load(str).into(viewHolder.mGoodsDgImage);
            }
        }
        viewHolder.mGoodsDgTitle.setText(dataBeen.get(position).getTitle());
        viewHolder.mGoodsDgRegion.setText(dataBeen.get(position).getContent());
        viewHolder.mGoodsDgMoney.setText("￥ " + dataBeen.get(position).getPrice());
        viewHolder.mGoodsDgCount.setText("月销量: " + dataBeen.get(position).getSale_num());
   /*     viewHolder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                // ((SwipeMenuLayout) viewHolder.itemView).setIos(false).setLeftSwipe(position % 2 == 0 ? true : false).setSwipeEnable(false);
            }
        });*/

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
