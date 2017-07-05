package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;

/**
 * 作者：王海宾 on 2017/4/15 0015 11:19
 * 邮箱：995696826@qq.com
 */
public class ZhongchouAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 20;
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
        if (convertView==null){
            convertView = View.inflate(ECApplication.context, R.layout.item_zhongchou,null);
        }
        return convertView;
    }
}
