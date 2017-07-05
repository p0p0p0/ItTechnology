package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/7 0007 19:07
 * 邮箱：995696826@qq.com
 */

public class IncpAdapter extends BaseAdapter {
    private ArrayList<String> arrayListName;
    private ArrayList<String> arrayListSId;

    public IncpAdapter(ArrayList<String> arrayListName, ArrayList<String> arrayListSId) {
        this.arrayListName = arrayListName;
        this.arrayListSId = arrayListSId;
    }

    @Override
    public int getCount() {
        return arrayListName.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_incp, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int b = position;
        String s = String.valueOf(b);
        viewHolder.mTextIncp.setText(arrayListName.get(position));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.text_incp)
        TextView mTextIncp;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
