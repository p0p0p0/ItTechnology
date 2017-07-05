package com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.ChanpingBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.safetyhuge.chanlian.ECApplication.context;

/**
 * 作者：王海宾 on 2017/4/21 0021 16:48
 * 邮箱：995696826@qq.com
 */
public class ChanpingAdapter extends BaseAdapter {
    List<ChanpingBean.DataBean> dataBeen;
    FragmentActivity activity;
    public ChanpingAdapter(List<ChanpingBean.DataBean> dataBeen, FragmentActivity activity) {
        this.dataBeen=dataBeen;
        this.activity=activity;
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
            convertView = View.inflate(context, R.layout.item_chanping, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mGoodsDgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, GoodsMinuteActivity.class);
                intent.putExtra("goodsid",dataBeen.get(position).getService_id()+"");
                intent.putExtra("iamge",dataBeen.get(position).getPic());
                activity.startActivity(intent);
            }
        });
        String iamgeurl = RequestAddress.IMAGE1+dataBeen.get(position).getPic();
        //截取#之前的字符串
        String str = iamgeurl;
        if(str.indexOf(",")!=-1){
            String b = str.substring(0, str.indexOf(","));
            KLog.e("indexof",str.substring(0, str.indexOf(",")));
            Picasso.with(context).load(b).fit().into(viewHolder.mGoodsDgImage);
        }else{
            System.out.println(str);
            Picasso.with(context).load(str).fit().into(viewHolder.mGoodsDgImage);
        }
        KLog.e("iamge"+RequestAddress.IMAGE1+dataBeen.get(position).getPic());
        viewHolder.mGoodsDgTitle.setText(dataBeen.get(position).getTitle());
        viewHolder.mGoodsDgRegion.setText(dataBeen.get(position).getContent());
        viewHolder.mGoodsDgMoney.setText("￥ "+dataBeen.get(position).getPrice());
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
