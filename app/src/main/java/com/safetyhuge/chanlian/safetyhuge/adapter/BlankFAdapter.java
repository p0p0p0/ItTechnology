package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;

import java.util.List;

/**
 * Created by XuYanZhao.
 */
public class BlankFAdapter extends BaseAdapter {
    public List list;
    public Context context;
    public LayoutInflater inflater;

    public BlankFAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (holder == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.my_list_view_blank, null);
            holder.item_title = (TextView) view.findViewById(R.id.item_title);
            holder.item_content = (TextView) view.findViewById(R.id.item_content);
            holder.item_pic = (TextView) view.findViewById(R.id.item_pic);
            holder.item_listview_time = (TextView) view.findViewById(R.id.text_views_rq);
            holder.item_addth = (TextView) view.findViewById(R.id.item_addth);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
//            holder.item_title.setText(list.get(i).getTitle());
        }
        return view;
    }

    private static class ViewHolder {
        //标题,内容，价格，时间，地址
        TextView item_title, item_content, item_pic, item_listview_time, item_addth;
    }
}
