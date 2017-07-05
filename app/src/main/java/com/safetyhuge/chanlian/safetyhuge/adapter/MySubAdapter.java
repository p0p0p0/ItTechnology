package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;

import java.util.ArrayList;

public class MySubAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> mArrayList1;
    private int selectedPosition = -1;

    public MySubAdapter(Context context, ArrayList<String> mArrayList1) {
        this.context = context;
        this.mArrayList1 = mArrayList1;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mArrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        final int location = position;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.sublist_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView
                    .findViewById(R.id.textview1);
            viewHolder.layout = (LinearLayout) convertView
                    .findViewById(R.id.layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mArrayList1.get(position));
        viewHolder.textView.setTextColor(Color.BLACK);
        if (selectedPosition == position) {
            viewHolder.textView.setTextColor(CommonUtil.getColor(R.color.juse));
        } else {
            viewHolder.layout.setBackgroundColor(CommonUtil.getColor(R.color.beijing));
            viewHolder.textView.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public LinearLayout layout;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

}
