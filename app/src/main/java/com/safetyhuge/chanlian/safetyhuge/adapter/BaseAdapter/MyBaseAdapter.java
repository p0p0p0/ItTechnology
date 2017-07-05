package com.safetyhuge.chanlian.safetyhuge.adapter.BaseAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 数据适配器的基类
 * 展示的数据类型不确定，故使用泛型！
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private List<T> list;
    private int a;
    public MyBaseAdapter(List<T> list, int a) {
        this.list = list;
        this.a = a;
    }

    @Override
    public int getCount() {
        if (a==3){
            return 3;
        }
       return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyBaseHolder<T> holder = null;
        if (convertView == null) {
            // 在初始化holder的同时,已经对布局进行了加载,也给view设置了tag
            holder = getHolder(position);
        } else {
            holder = (MyBaseHolder<T>) convertView.getTag();
        }
        // 刷新界面,更新数据
        holder.setData(getItem(position));
        return holder.getRootView();
    }

    // 返回BaseHolder的子类,必须实现
    public abstract MyBaseHolder<T> getHolder(int position);
}

