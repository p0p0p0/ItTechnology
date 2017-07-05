package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Intent;
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
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Chinalink on 2017/3/25 0025.
 */
public class PaypasswordActivity extends HBaseAct {
    private Intent intent;
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
        setContentView(R.layout.activity_paypassword);
        intent = new Intent();
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
                            if (newPassword.equals(newsPw)) {
                                if (!str.equals(newsPw)) {
                                    //  if (str.equals(mPassword)) {
                                    NetWork(mUid, str, newsPw, newsPw);
                                    // } else {
                                    //  Toast.makeText(this, "旧密码错误,请重新输入", Toast.LENGTH_SHORT).show();
                                    // }
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

    public void NetWork(String uid, String oldPassword, String newPassword, String confirm_code) {
        HashMap<String, String> hashMap = new HashMap<>();
        //UpdateSecCode
        hashMap.put("action", "UpdateSecCode");
        hashMap.put("uid", uid);
        hashMap.put("old_code", oldPassword);
        hashMap.put("new_code", newPassword);
        hashMap.put("confirm_code", confirm_code);
        KLog.e("hashMap", hashMap.toString()
        );
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                Object code = mapForJson.get("code");
                Object secess = mapForJson.get("secess");
                Object error = mapForJson.get("error");
                if (code.equals("888") && secess.equals("true")) {
                    Toast.makeText(ECApplication.context, "修改支付密码成功", Toast.LENGTH_SHORT).show();
                    exitAct();
                }else{
                    Toast.makeText(ECApplication.context, "修改支付密码失败,请检查输入密码是否正确", Toast.LENGTH_SHORT).show();
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
