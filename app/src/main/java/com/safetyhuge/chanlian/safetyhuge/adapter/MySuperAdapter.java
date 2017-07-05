package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.socks.library.KLog;

import java.util.ArrayList;

public class MySuperAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String[] foods;
    int last_item;
    int[] images;
    ArrayList<String> arrayListName;
    private int selectedPosition = -2;
    private int selectedPositions ;

    public MySuperAdapter(Context context, ArrayList<String> arrayListName) {
        this.context = context;
        this.arrayListName = arrayListName;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return arrayListName.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mylist_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.textview);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.colorlayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (selectedPositions==position){
            KLog.e("走了1");
            holder.textView.setTextColor(Color.BLACK);
            holder.layout.setBackgroundColor(Color.WHITE);
        }
        if (selectedPosition == position) {
            KLog.e("position+++++++",position);
            holder.textView.setTextColor(CommonUtil.getColor(R.color.juse));
            holder.layout.setBackgroundColor(CommonUtil.getColor(R.color.beijing));
        } else {
            KLog.e("position---------",position);
            holder.textView.setTextColor(Color.BLACK);
            holder.layout.setBackgroundColor(Color.WHITE);
        }
        holder.textView.setText(arrayListName.get(position));

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public LinearLayout layout;
    }

    public void setSelectedPosition(int position) {
        KLog.e("stSelectedPosition+++++",position);
        selectedPosition = position;
    }
    public void setSelectedPositions(int s) {
        KLog.e("setSelectedPositions",s);
        selectedPositions = s;
    }

}
