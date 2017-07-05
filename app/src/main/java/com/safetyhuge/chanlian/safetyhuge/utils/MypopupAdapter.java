package com.safetyhuge.chanlian.safetyhuge.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;

import java.util.ArrayList;

/**
 * 作者：王海宾 on 2017/4/5 0005 17:39
 * 邮箱：995696826@qq.com
 */
public class MypopupAdapter extends BaseAdapter {
    private ArrayList<String> list;
    Context context;
    public MypopupAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        this.context= context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_popwoindow,null);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_select_input);
        textView.setText(list.get(position));
        return convertView;
    }
}
