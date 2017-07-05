package com.safetyhuge.chanlian.safetyhuge.adapter.BaseAdapter;

import android.view.View;

/**
 * Created by F-57 on 2017/3/21.
 */

public abstract class MyBaseHolder<T> {
    private View mRootView;// item的布局对象
    private T data;// item对应的数据

    public MyBaseHolder() {
        mRootView = initView();// 初始化布局
        mRootView.setTag(this);// 给view设置tag
    }

    // 初始化布局的方法必须由子类实现
    public abstract View initView();

    // 返回布局对象
    public View getRootView() {
        return mRootView;
    };

    // 设置数据
    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    // 获取数据
    public T getData() {
        return data;
    }

    // 刷新界面,更新数据,子类必须实现
    public abstract void refreshView(T data);
}
