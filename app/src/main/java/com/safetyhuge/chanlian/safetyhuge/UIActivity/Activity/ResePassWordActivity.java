package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.IsMobileNOUtils.isMobileNO;

/**
 * 重置密码
 * 作者：王海宾 on 2017/5/8 0008 11:08
 * 邮箱：995696826@qq.com
 */

public class ResePassWordActivity extends HBaseAct {
    @Bind(R.id.rese_name)
    EditText mReseName;
    @Bind(R.id.rese_password)
    EditText mResePassword;
    @Bind(R.id.rese_chakan)
    ImageView mReseChakan;
    @Bind(R.id.rese_phone)
    EditText mResePhone;
    @Bind(R.id.rese_verify)
    EditText mReseVerify;
    @Bind(R.id.rese_verify_button)
    TextView mReseVerifyButton;
    @Bind(R.id.rese_chongzhi)
    Button mReseChongzhi;
    @Bind(R.id.layout_zhuce)
    LinearLayout mLayoutZhuce;
    private int number1 = 0;//判断密码是否显示
    private KProgressHUD mProgressHUD;
    private int recLen = 60;
    final Handler handler1 = new Handler() {
        public void handleMessage(Message msg) { // handle message
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                // 短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                 /*   Toast.makeText(getApplicationContext(), "验证成功",
                            Toast.LENGTH_SHORT).show();*/
                    KLog.e("EVENT_SUBMIT_VERIFICATION_CODE", "验证成功");
                    //访问网络
                    zhuce(mReseName.getText().toString().trim(), mResePassword.getText().toString().trim());
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //验证码已发送
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
            if (result == SMSSDK.RESULT_ERROR) {
                try {
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");//错误描述
                    int status = object.optInt("status");//错误代码
                    if (status > 0 && !TextUtils.isEmpty(des)) {
                        if (des.equals("需要校验的验证码错误") && status == 468 || des.equals("Invalid validation code") && status == 468) {
                            Toast.makeText(getApplicationContext(), "验证码错误", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                } catch (Exception e) {
                }
            }
            switch (msg.what) {
                case 1:
                    recLen--;
                    mReseVerifyButton.setClickable(false);
                    mReseVerifyButton.setText("" + recLen + "s");
                    if (recLen > 0) {
                        Message message = handler1.obtainMessage(1);
                        handler1.sendMessageDelayed(message, 1000); // send message
                    } else {
                        mReseVerifyButton.setText("验证");
                        mReseVerifyButton.setClickable(true);
                        recLen = 60;
                    }
            }
            super.handleMessage(msg);
        }
    };
    private EventHandler mEh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activyt_resepassword);
        ButterKnife.bind(this);
        initView2();
        initSDK();
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick({R.id.rese_chakan, R.id.rese_verify_button, R.id.rese_chongzhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rese_chakan:
                Toast.makeText(this, "走了", Toast.LENGTH_SHORT).show();
                if (number1 == 0) {
                    mReseChakan.setImageResource(R.mipmap.view);
                    mResePassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    number1 = 1;
                } else {
                    mReseChakan.setImageResource(R.mipmap.view_off);
                    mResePassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    number1 = 0;
                }
                break;
            case R.id.rese_verify_button:
                ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
                boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
                boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
                if (wifi | internet) {
                    //执行相关操作
                    String trim = mResePhone.getText().toString().toString().trim();
                    if (isMobileNO(trim) == false) {
                        Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                    } else {
                        Yanzheng(mReseName.getText().toString().trim(), mResePhone.getText().toString().trim());
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "请检查网络", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.rese_chongzhi:
                ConnectivityManager con1 = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
                boolean wifi1 = con1.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
                boolean internet1 = con1.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
                if (wifi1 | internet1) {
                    //执行相关操作
                    SMSSDK.submitVerificationCode("86", mResePhone.getText().toString(), mReseVerify.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(),
                            "请检查网络", Toast.LENGTH_LONG)
                            .show();
                }
                break;
        }
    }

    private void initView2() {
        //用户名
        mReseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mResePassword.getText().toString().trim();
                String trim1 = mResePhone.getText().toString().trim();
                String trim2 = mReseVerify.getText().toString().trim();

                if (mName1.equals("")) {
                    mReseChongzhi.setClickable(false);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("") && trim1 != null && !trim1.equals("") && trim2 != null && !trim2.equals("")) {
                    mReseChongzhi.setClickable(true);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login);
                } else {
                    mReseChongzhi.setClickable(false);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //密码
        mResePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mReseName.getText().toString().trim();
                String trim1 = mResePhone.getText().toString().trim();
                String trim2 = mReseVerify.getText().toString().trim();

                if (mName1.equals("")) {
                    mReseChongzhi.setClickable(false);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("") && trim1 != null && !trim1.equals("") && trim2 != null && !trim2.equals("")) {
                    mReseChongzhi.setClickable(true);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login);
                } else {
                    mReseChongzhi.setClickable(false);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //手机号
        mResePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mReseName.getText().toString().trim();
                String trim1 = mResePassword.getText().toString().trim();
                String trim2 = mReseVerify.getText().toString().trim();

                if (mName1.equals("")) {
                    mReseChongzhi.setClickable(false);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("") && trim1 != null && !trim1.equals("") && trim2 != null && !trim2.equals("")) {
                    mReseChongzhi.setClickable(true);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login);
                } else {
                    mReseChongzhi.setClickable(false);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //验证码
        mReseVerify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mReseName.getText().toString().trim();
                String trim1 = mResePhone.getText().toString().trim();
                String trim2 = mResePassword.getText().toString().trim();
                if (mName1.equals("")) {
                    mReseChongzhi.setClickable(false);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("") && trim1 != null && !trim1.equals("") && trim2 != null && !trim2.equals("")) {
                    mReseChongzhi.setClickable(true);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login);
                } else {
                    mReseChongzhi.setClickable(false);
                    mReseChongzhi.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //验证手机号是否注册
    public void Yanzheng(String uid, String mobile) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "VerifyUserMobile");
        hashMap.put("username", uid);
        hashMap.put("mobile", mobile);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                KLog.json(s);
                Object code = mapForJson.get("code");
                String error = (String) mapForJson.get("error");
                String secess = (String) mapForJson.get("secess");
                if (secess != null) {
                    if (secess.equals("true")) {
                        Message message = handler1.obtainMessage(1); // Message
                        handler1.sendMessageDelayed(message, 1000);
                        SMSSDK.getVerificationCode("86", mResePhone.getText().toString());
                    }
                }
             if (error != null) {
                    if (error.equals("用户名与手机号不匹配")) {
                        Toast.makeText(ResePassWordActivity.this, "用户名与手机号不匹配", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //注册
    public void zhuce(String user_name, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", user_name);
        hashMap.put("password", password);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.XGDDMM).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                KLog.json(s);
                String message = (String) mapForJson.get("message");
                int code = (int) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                //secess
                if (message.equals("修改成功") && code==200) {
                    exitAct();
                    Toast.makeText(ECApplication.context, "重置密码成功,请登录", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ECApplication.context, "重置密码失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //初始化SMSSDK
    private void initSDK() {
        SMSSDK.registerEventHandler(new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                super.afterEvent(event, result, data);
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                KLog.e("event",event);
                KLog.e("result",result);
                KLog.e("data",data+"");
                handler1.sendMessage(msg);
                KLog.e("whb","走了222");
            }
        }); //注册短信回调
    }
    @Override
    protected void onStop() {
        super.onStop();
        SMSSDK.unregisterAllEventHandler();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
