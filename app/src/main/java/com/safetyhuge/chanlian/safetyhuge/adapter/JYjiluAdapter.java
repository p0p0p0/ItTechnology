package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.JYjiluBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.safetyhuge.chanlian.safetyhuge.R.drawable.icon_shou;

/**
 * 作者：王海宾 on 2017/4/21 0021 19:33
 * 邮箱：995696826@qq.com
 */

public class JYjiluAdapter extends BaseAdapter {
    List<JYjiluBean.DataBean> data;

    public JYjiluAdapter(List<JYjiluBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_jilu, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (data.get(position).getFina_type().equals("out")){
            viewHolder.mImageText.setImageResource(R.drawable.icon_chu);
            viewHolder.mJiluQian.setText("-"+data.get(position).getFina_cash());
        }else{
            viewHolder.mImageText.setImageResource(icon_shou);
            viewHolder.mJiluQian.setText("+"+data.get(position).getFina_cash());
        }
        String time = data.get(position).getFina_time();
        String s = DateUtils.timesOne(time);
        viewHolder.mJiluItem.setText(s);
        viewHolder.mJiluNeirong.setText(data.get(position).getFina_mem());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.jilu_item)
        TextView mJiluItem;
        @Bind(R.id.image_text)
        ImageView mImageText;
        @Bind(R.id.jilu_qian)
        TextView mJiluQian;
        @Bind(R.id.jilu_neirong)
        TextView mJiluNeirong;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
