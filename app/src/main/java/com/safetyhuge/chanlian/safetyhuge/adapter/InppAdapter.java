package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.socks.library.KLog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/7 0007 19:13
 * 邮箱：995696826@qq.com
 */

public class InppAdapter extends BaseAdapter {
    private ArrayList<String> mArrayList1;
    public InppAdapter(ArrayList<String> mArrayList1) {
        this.mArrayList1=mArrayList1;
    }
    public void notif(){
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mArrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolders viewHolder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_incp1, null);
            viewHolder = new ViewHolders(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolders) convertView.getTag();
        }
        viewHolder.mTextIncp1.setText(mArrayList1.get(position));
        KLog.e("mTextIncp1",mArrayList1.get(position));
        return convertView;
    }

    static class ViewHolders {
        @Bind(R.id.text_incp1)
        TextView mTextIncp1;

        ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
