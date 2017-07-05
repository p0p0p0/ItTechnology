package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Chinalink on 2017/3/27 0027.
 */

public class AmendasswordActivity extends HBaseAct {
    @Bind(R.id.editText_worn_password)
    EditText mEditTextWornPassword;
    @Bind(R.id.et_new_password)
    EditText mEtNewPassword;
    @Bind(R.id.et_news_password)
    EditText mEtNewsPassword;
    @Bind(R.id.bt_save)
    Button mBtSave;
    private String mPassword;
    private String mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amendpassword);
        ButterKnife.bind(this);
        mPassword = (String) SharedPrefsUtil.get(ECApplication.context, "UserPassword", "");
        mUid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        KLog.e("用户密码", mPassword);
    }

    @OnClick(R.id.bt_save)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                String str = mEditTextWornPassword.getText().toString();
                String newPassword = mEtNewPassword.getText().toString();
                String newsPw = mEtNewsPassword.getText().toString();
                //加密
                //String strs = MD5Utils.getMd5Value(str);
                if (!str.isEmpty()) {
                    if (!newPassword.isEmpty()) {
                        if (!newsPw.isEmpty()) {
                            if (newPassword .equals(newsPw)) {
                                if (!str.equals(newsPw)) {
                                   if (str.equals(mPassword)) {
                                        Toast.makeText(this, "保存密码成功", Toast.LENGTH_SHORT).show();
                                        NetWork(mUid, mPassword, newsPw);
                                        exitAct();
                                    } else {
                                        Toast.makeText(this, "旧密码错误,请重新输入", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "旧密码和新密码不能重复", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                //保存密码
                                Toast.makeText(this, "新密码和重复密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "重复密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "旧密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void back_text_view(View view) {
        exitAct();
    }

    public void NetWork(String uid, String oldPassword, String newPassword) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("oldpassword", oldPassword);
        hashMap.put("password", newPassword);
        KLog.e("hashMap",hashMap.toString()
        );
        OkGo.post(RequestAddress.HOST + RequestAddress.XGMM).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.e("password", s);
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
