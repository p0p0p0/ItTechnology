package com.safetyhuge.chanlian.safetyhuge.views;
import android.content.Context;
import android.os.Message;
/**
 * Created by Administrator on 2016/9/7 0007.
 * TODO视图实现接口，用于presenters更新视图
 */
public interface IViewUpdate {
    //设置显示View
    public void setViewShow(Message msg);
    //设置隐藏View
    public void setViewHide(Message msg);
    //设置View显示内容
    public void setViewContent(Message msg);
    //设置View数据改变（listview gridview�?
    public void setViewDataChange(Message msg);
    //进入下一界面
    public void viewGoNext(Message msg);
    //返回上一界面
    public void viewToBack(Message msg);
    //显示通知提示
    public void setToastShow(String msg, int Type);
    //	获取Context对象
    public Context getContext();
}
