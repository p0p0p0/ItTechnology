package com.safetyhuge.chanlian.safetyhuge.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.socks.library.KLog;

import java.util.ArrayList;

/**
 * 作者：王海宾 on 2017/4/5 0005 16:22
 * 邮箱：995696826@qq.com
 */

public class PopupUtils {
    private static PopupWindow typeSelectPopup;
    public static String XmYi ="";
    public static String XmEr ="";
    public static String DqYi ="";
    public static String DqEr ="";
    public static String DqSan ="";
    public static String QJid ="";
    public static PopupWindow initSelectPopup(Context context, final ArrayList<String> list, final ArrayList<String> list1,
                                              final ArrayList<String> list2,
                                              final ArrayList<String> list3,
                                              final ArrayList<String> list4,
                                              final ArrayList<String> list5,
                                              final ArrayList<String> list6,
                                              final TextView v, View v1) {
        ListView mTypeLv = new ListView(context);
        final MypopupAdapter mypopupAdapter = new MypopupAdapter(context, list);
        mTypeLv.setAdapter(mypopupAdapter);
        // 设置ListView点击事件监听
        // 让垂直滚动条不可见
        mTypeLv.setDividerHeight(0);
        mTypeLv.setVerticalScrollBarEnabled(false);
        mTypeLv.setSelector(R.color.transparent);
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = list.get(position);
                if (list1 != null) {
                    String mVid = list1.get(position);
                    XmYi = mVid;
                    KLog.e("项目一级", mVid);
                    SharedPrefsUtil.put(ECApplication.context, "Midid", mVid);
                }
                if (list2 != null) {
                    String mVid = list2.get(position);
                    KLog.e("项目二级", mVid);
                    XmEr = mVid;
                    SharedPrefsUtil.put(ECApplication.context, "MMidid", mVid);
                }
                if (list3 != null) {
                    String mVid = list3.get(position);
                    KLog.e("省", mVid);
                    DqYi = mVid;
                    SharedPrefsUtil.put(ECApplication.context, "MMMidid", mVid);
                }
                if (list4 != null) {
                    String mVid = list4.get(position);
                    KLog.e("市", mVid);
                    DqEr = mVid;
                    SharedPrefsUtil.put(ECApplication.context, "MMMMidid", mVid);
                }
                if (list5 != null) {
                    String mVid = list5.get(position);
                    KLog.e("街道", mVid);
                    DqSan = mVid;
                    SharedPrefsUtil.put(ECApplication.context, "MMMMMidid", mVid);
                }
                if (list6 != null) {
                    String mVid1 = list6.get(position);
                    KLog.e("区间id", mVid1);
                    QJid = mVid1;
                    SharedPrefsUtil.put(ECApplication.context, "aMidid", mVid1);

                }

                KLog.e("value", value);
                // 把选择的数据展示对应的TextView上
                v.setText(value);
                // 选择完后关闭popup窗口
                typeSelectPopup.dismiss();
                mypopupAdapter.notifyDataSetChanged();
            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, v1.getWidth(), 330, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bg_corner);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
        if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
            typeSelectPopup.showAsDropDown(v1, 0, 10);
        }
        return typeSelectPopup;
    }


    public static PopupWindow initSelectPopup2(Context context, final ArrayList<String> list,final ArrayList<String> list1,
                                               final TextView v, View v1) {
        ListView mTypeLv = new ListView(context);
        final MypopupAdapter mypopupAdapter = new MypopupAdapter(context, list);
        mTypeLv.setAdapter(mypopupAdapter);
        // 设置ListView点击事件监听
        // 让垂直滚动条不可见
        mTypeLv.setDividerHeight(0);
        mTypeLv.setVerticalScrollBarEnabled(false);
        mTypeLv.setSelector(R.color.transparent);
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = list.get(position);
                if (list1 != null) {
                    String mVid = list1.get(position);
                    KLog.e("产品分类", mVid);
                    SharedPrefsUtil.put(ECApplication.context, "ppfl", mVid);
                }
                KLog.e("value", value);
                // 把选择的数据展示对应的TextView上
                v.setText(value);
                // 选择完后关闭popup窗口
                typeSelectPopup.dismiss();
                mypopupAdapter.notifyDataSetChanged();
            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, v1.getWidth(), 330, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bg_corner);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
        if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
            typeSelectPopup.showAsDropDown(v1, 0, 10);
        }
        return typeSelectPopup;
    }
}
