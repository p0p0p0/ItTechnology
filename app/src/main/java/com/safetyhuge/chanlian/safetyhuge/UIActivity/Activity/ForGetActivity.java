package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.ToastUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.umeng.analytics.MobclickAgent;

/**
 * 修改密码页面
 * 徐艳昭
 * 15326057231@163.com
 * 17.3.7
 * **/
public class ForGetActivity extends HBaseAct  {
private EditText Edt_PassWord_J,Edt_PassWord_X,Edt_PassWord_CFX;
    private TextView Txt_Reg_ZC;
    private ImageView back_text_view_forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_get);
        Edt_PassWord_J = (EditText) findViewById(R.id.Edt_PassWord_J);
        Edt_PassWord_X = (EditText) findViewById(R.id.Edt_PassWord_X);
        Edt_PassWord_CFX = (EditText) findViewById(R.id.Edt_PassWord_CFX);
        Txt_Reg_ZC = (TextView) findViewById(R.id.Txt_Reg_ZC);
        back_text_view_forget = (ImageView) findViewById(R.id.back_text_view_forget);
        back_text_view_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitAct();//退出当前页面，并且销毁，返回上一级页面
            }
        });
        Txt_Reg_ZC.setOnClickListener(new View.OnClickListener() {//点击提交按钮
            @Override
            public void onClick(View view) {
                if (!Edt_PassWord_J.getText().toString().equals("")){
                    if (!Edt_PassWord_X.getText().toString().equals("")){
                        if (!Edt_PassWord_CFX.getText().toString().equals("")){
                            //这边需要些咱们的网络获取数据组件去获取数据
                            String s = Edt_PassWord_J.getText().toString();
                            String s1 = Edt_PassWord_X.getText().toString();
                            String s2 = Edt_PassWord_CFX.getText().toString();
                            exitAct();//销毁当前页面，进入上一页面

                        }else{
                            ToastUtils.ShowError(ForGetActivity.this,"请再次输入你新密码",0);
                        }
                    }else{
                        ToastUtils.ShowError(ForGetActivity.this,"请输入你新密码",0);
                    }
                }else{
                    ToastUtils.ShowError(ForGetActivity.this,"请输入你的旧密码",0);
                }

            }
        });
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
