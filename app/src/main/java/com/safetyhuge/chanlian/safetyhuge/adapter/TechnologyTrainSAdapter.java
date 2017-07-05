package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.ChargeBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/30 0030.
 */
public class TechnologyTrainSAdapter extends BaseAdapter {
    private Context context;
    List<ChargeBean.DataBean> dataBeen;
    int flag;

    public TechnologyTrainSAdapter(Context context, List<ChargeBean.DataBean> dataBeen, int flag) {
        this.context = context;
        this.dataBeen = dataBeen;
        this.flag = flag;
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
            convertView = View.inflate(context, R.layout.item_technolog, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       /* String imageUrl = dataBeen.get(position).getPicurl();
        KLog.e("imageUrl",imageUrl);
        if (!imageUrl.isEmpty()) {
            if (imageUrl.startsWith("article")){
                Picasso.with(context).load(RequestAddress.IMAGE2
                        + imageUrl).fit().into(viewHolder.mTechnologIamge);
            }else{
                Picasso.with(context).load(imageUrl).into(viewHolder.mTechnologIamge);
            }
        }
        viewHolder.mTechnologFname.setText("防火墙新技术");
        viewHolder.mTechnologName.setText(dataBeen.get(position).getTitle());
        viewHolder.mTechnologLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TechnologyminuteActivity.class);
                intent.putExtra("title",dataBeen.get(position).getTitle());
                intent.putExtra("content",dataBeen.get(position).getContent());
                context.startActivity(intent);
            }
        });*/
        String pic = dataBeen.get(position).getPic();
        if (pic != null && !pic.equals("")) {
            Picasso.with(context).load(RequestAddress.IMAGE1 + pic).into(viewHolder.mPlayImage);
        }
        viewHolder.mPlayTitle.setText(dataBeen.get(position).getTitle());
        viewHolder.mPlayXiazai.setText(" "+dataBeen.get(position).getIndus_pname());
        viewHolder.mPlayCount.setText(dataBeen.get(position).getViews()+"次播放");
        if (flag==0){
            viewHolder.mPlayMoney.setVisibility(View.GONE);
        }else{
            viewHolder.mPlayMoney.setText("¥"+dataBeen.get(position).getPrice());
            viewHolder.mPlayMoney.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.play_image)
        ImageView mPlayImage;
        @Bind(R.id.play_title)
        TextView mPlayTitle;
        @Bind(R.id.play_xiazai)
        TextView mPlayXiazai;
        @Bind(R.id.play_money)
        TextView mPlayMoney;
        @Bind(R.id.play_count)
        TextView mPlayCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
