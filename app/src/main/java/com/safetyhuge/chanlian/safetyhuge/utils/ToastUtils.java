package com.safetyhuge.chanlian.safetyhuge.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.safetyhuge.chanlian.safetyhuge.R;
public class ToastUtils {

    public static void ShowError(Context context,String Message,int Type){
        Toast toast = Toast.makeText(context, Message,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 150);
        LinearLayout toastView = (LinearLayout) toast.getView();
        toastView.setOrientation(LinearLayout.HORIZONTAL);
        toastView.setGravity(Gravity.CENTER_VERTICAL);
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setPadding(10, 0, 10, 0);
        if(Type == 0){
            //提示性消息
            imageCodeProject.setImageResource(R.mipmap.info);
        }else if(Type == 1){
            //错误消息
            imageCodeProject.setImageResource(R.mipmap.error);
        }else if(Type == 2){
            //成功消息
            imageCodeProject.setImageResource(R.mipmap.success);
        }

        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    public static void ShowErrorTop(Context context, String Message, int Type){
        Toast toast = Toast.makeText(context,
                Message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 180);
        LinearLayout toastView = (LinearLayout) toast.getView();
        toastView.setOrientation(LinearLayout.HORIZONTAL);
        toastView.setGravity(Gravity.CENTER_VERTICAL);
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setPadding(10, 0, 10, 0);
        if(Type == 0){
            //提示性消息
            imageCodeProject.setImageResource(R.mipmap.info);
        }else if(Type == 1){
            //错误消息
            imageCodeProject.setImageResource(R.mipmap.error);
        }else if(Type == 2){
            //成功消息
            imageCodeProject.setImageResource(R.mipmap.success);
        }

        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

}