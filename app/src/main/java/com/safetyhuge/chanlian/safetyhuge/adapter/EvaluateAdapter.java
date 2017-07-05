package com.safetyhuge.chanlian.safetyhuge.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.baseview.StarBarView;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/28 0028.
 */

public class EvaluateAdapter extends BaseAdapter {
    List<GoodsBean.DataBean.MarkInfoBean> mark_info;

    public EvaluateAdapter(List<GoodsBean.DataBean.MarkInfoBean> mark_info) {
        this.mark_info = mark_info;
    }

    @Override
    public int getCount() {
        KLog.e("mark_info", mark_info.size());
        return mark_info.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_pingjia, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mPingjiaTitle.setText(mark_info.get(position).getBy_username());
        //viewHolder.mPingjiaImage
        Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1+mark_info.get(position).getPic()).fit().into(viewHolder.mPingjiaImage);
        viewHolder.mPingjiaTime.setText(DateUtils.time(mark_info.get(position).getMark_time()));
        String mark_content = mark_info.get(position).getMark_content();
        if (mark_content.equals("")){
            viewHolder.mPingjiaContent.setText("该用户未填写评价");
        }else{
            viewHolder.mPingjiaContent.setText(mark_content);
        }
        String aid_star = mark_info.get(position).getAid_star();
        KLog.e("aid_star",aid_star);
        String[] strings = aid_star.split(",");
        float a0 = Float.parseFloat(strings[0]);
        viewHolder.mSbvStarbar1.setStarRating(a0);
        float a1 = Float.parseFloat(strings[1]);
        viewHolder.mSbvStarbar2.setStarRating(a1);
        float a2 = Float.parseFloat(strings[2]);
        viewHolder.mSbvStarbar3.setStarRating(a2);
        String mark_status = mark_info.get(position).getMark_status();
        if (mark_status!=null&&!mark_status.equals("")){
            if (mark_status.equals("1")){
                viewHolder.mPingjiaImage1.setImageResource(R.drawable.icon_haoping);
            }else if (mark_status.equals("2")){
                viewHolder.mPingjiaImage1.setImageResource(R.drawable.icon_zhongping);
            }else if (mark_status.equals("3")){
                viewHolder.mPingjiaImage1.setImageResource(R.drawable.icon_chaping);
            }
        }
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.pingjia_image)
        CircleImageView mPingjiaImage;
        @Bind(R.id.pingjia_title)
        TextView mPingjiaTitle;
        @Bind(R.id.pingjia_image1)
        ImageView mPingjiaImage1;
        @Bind(R.id.pingjia_time)
        TextView mPingjiaTime;
        @Bind(R.id.item_pingjia_layout)
        RelativeLayout mItemPingjiaLayout;
        @Bind(R.id.pingjia_content)
        TextView mPingjiaContent;
        @Bind(R.id.sbv_starbar1)
        StarBarView mSbvStarbar1;
        @Bind(R.id.sbv_starbar2)
        StarBarView mSbvStarbar2;
        @Bind(R.id.sbv_starbar3)
        StarBarView mSbvStarbar3;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
