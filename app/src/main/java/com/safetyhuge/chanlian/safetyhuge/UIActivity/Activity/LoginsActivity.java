package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferenceSettings;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferences;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.storage.ContactSqlManager;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.LauncherActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.contact.ContactLogic;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.contact.ECContacts;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.IsMobileNOUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.yuntongxun.ecsdk.ECInitParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;

/**
 * 作者：王海宾 on 2017/5/6 0006 13:16
 * 邮箱：995696826@qq.com
 */

public class LoginsActivity extends HBaseAct {
    @Bind(R.id.button_denglv)
    Button mButtonDenglv;
    @Bind(R.id.button_zhuce)
    Button mButtonZhuce;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.recharge_chongzhi)
    Button mRechargeChongzhi;
    @Bind(R.id.layout_denglu)
    LinearLayout mLayoutDenglu;
    @Bind(R.id.recharge_chongzhi1)
    Button mRechargeChongzhi1;
    @Bind(R.id.layout_zhuce)
    LinearLayout mLayoutZhuce;
    @Bind(R.id.content_main)
    LinearLayout mContentMain;
    @Bind(R.id.name2)
    EditText mName2;
    @Bind(R.id.password2)
    EditText mPassword2;
    @Bind(R.id.phone2)
    EditText mPhone2;
    @Bind(R.id.verify2)
    EditText mVerify2;
    @Bind(R.id.verify_button)
    TextView mVerifyButton;
    @Bind(R.id.iv_show_member)
    ImageView mIvShowMember;
    @Bind(R.id.iv_show_member1)
    ImageView mIvShowMember1;
    @Bind(R.id.delv_forget_password)
    TextView mDelvForgetPassword;
    private int number = 0;//判断密码是否显示
    private int number1 = 0;//判断密码是否显示
    private KProgressHUD mProgressHUD;
    private int recLen = 60;
    ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
    private EventHandler mEh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        ButterKnife.bind(this);
        mProgressHUD = KProgressHUD.create(LoginsActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("登录中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        KLog.e("whb","走了");
        initSDK();
        initView1();
        mLayoutDenglu.setVisibility(View.VISIBLE);
    }
    //初始化SMSSDK
    private void initSDK() {
        KLog.e("whb","走了111");
       /* SMSSDK.registerEventHandler(new EventHandler(){
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
                handler.sendMessage(msg);
                KLog.e("whb","走了222");
            }
        }); //注册短信回调*/
        //3.0版本之后的初始化看这里（包括3.0）
        EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {
/*
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        KLog.e("whb","1111");
                        //提交验证码成功
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        KLog.e("whb","2222");
                        Toast.makeText(LoginsActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                        KLog.e("whb","3333");
                    }
                }else{
                    ((Throwable)data).printStackTrace();
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
                }*/
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                KLog.e("event",event);
                KLog.e("result",result);
                KLog.e("data",data+"");
                handler.sendMessage(msg);
                KLog.e("whb","走了222");
            }
        };
        SMSSDK.registerEventHandler(eh);
    }
    private  Handler handler = new Handler() {
        public void handleMessage(Message msg) { // handle message
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                // 短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                    KLog.e("EVENT_SUBMIT_VERIFICATION_CODE", "验证成功");
                    //访问网络
                    zhuce(mName2.getText().toString().trim(), mPhone2.getText().toString().trim(), mPassword2.getText().toString().trim());
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    KLog.e("EVENT_SUBMIT_VERIFICATION_CODE", "验证码已经发送");
                    Toast.makeText(getApplicationContext(), "验证码已经发送",
                            Toast.LENGTH_SHORT).show();
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
                    mVerifyButton.setClickable(false);
                    mVerifyButton.setText("" + recLen + "s");
                    if (recLen > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000); // send message
                    } else {
                        mVerifyButton.setText("验证");
                        mVerifyButton.setClickable(true);
                        recLen = 60;
                    }
            }
        }
    };


    @OnClick({R.id.verify_button, R.id.button_denglv, R.id.button_zhuce, R.id.recharge_chongzhi, R.id.recharge_chongzhi1})
    public void onClick(View view) {
        switch (view.getId()) {
            //登录
            case R.id.button_denglv:
                mLayoutDenglu.setVisibility(View.VISIBLE);
                mLayoutZhuce.setVisibility(View.GONE);
                mButtonDenglv.setTextColor(Color.WHITE);
                mButtonDenglv.setBackgroundColor(CommonUtil.getColor(R.color.home));

                mButtonZhuce.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mButtonZhuce.setBackgroundColor(CommonUtil.getColor(R.color.zhuceColor));
                initView1();
                break;
            //注册
            case R.id.button_zhuce:
                mLayoutDenglu.setVisibility(View.GONE);
                mLayoutZhuce.setVisibility(View.VISIBLE);
                mButtonZhuce.setTextColor(Color.WHITE);
                mButtonZhuce.setBackgroundColor(CommonUtil.getColor(R.color.home));
                mButtonDenglv.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                mButtonDenglv.setBackgroundColor(CommonUtil.getColor(R.color.zhuceColor));
                initView2();
                break;
            //登录
            case R.id.recharge_chongzhi:
                mProgressHUD.show();
                String name = mName.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                denglv(name, password);
                break;
            //注册
            case R.id.recharge_chongzhi1:
                ConnectivityManager con1 = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
                boolean wifi1 = con1.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
                boolean internet1 = con1.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
                if (wifi1 | internet1) {
                    //执行相关操作
                    SMSSDK.submitVerificationCode("86", mPhone2.getText().toString(), mVerify2.getText().toString());
                //    zhuce(mName2.getText().toString().trim(), mPhone2.getText().toString().trim(), mPassword2.getText().toString().trim());
                } else {
                    Toast.makeText(getApplicationContext(),
                            "请检查网络", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            //验证
            case R.id.verify_button:
                ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
                boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
                boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
                if (wifi | internet) {
                    //执行相关操作
                    String trim = mPhone2.getText().toString().toString().trim();
                    if (IsMobileNOUtils.isMobileNO(trim) == false) {
                        Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                    } else {
                        Yanzheng("", mPhone2.getText().toString().trim());
                    }
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
        mName2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mPassword2.getText().toString().trim();
                String trim1 = mPhone2.getText().toString().trim();
                String trim2 = mVerify2.getText().toString().trim();

                if (mName1.equals("")) {
                    mRechargeChongzhi1.setClickable(false);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("") && trim1 != null && !trim1.equals("") && trim2 != null && !trim2.equals("")) {
                    mRechargeChongzhi1.setClickable(true);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login);
                } else {
                    mRechargeChongzhi1.setClickable(false);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //密码
        mPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mName2.getText().toString().trim();
                String trim1 = mPhone2.getText().toString().trim();
                String trim2 = mVerify2.getText().toString().trim();

                if (mName1.equals("")) {
                    mRechargeChongzhi1.setClickable(false);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("") && trim1 != null && !trim1.equals("") && trim2 != null && !trim2.equals("")) {
                    mRechargeChongzhi1.setClickable(true);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login);
                } else {
                    mRechargeChongzhi1.setClickable(false);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //手机号
        mPhone2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mName2.getText().toString().trim();
                String trim1 = mPassword2.getText().toString().trim();
                String trim2 = mVerify2.getText().toString().trim();

                if (mName1.equals("")) {
                    mRechargeChongzhi1.setClickable(false);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("") && trim1 != null && !trim1.equals("") && trim2 != null && !trim2.equals("")) {
                    mRechargeChongzhi1.setClickable(true);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login);
                } else {
                    mRechargeChongzhi1.setClickable(false);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //验证码
        mVerify2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mName2.getText().toString().trim();
                String trim1 = mPhone2.getText().toString().trim();
                String trim2 = mPassword2.getText().toString().trim();
                if (mName1.equals("")) {
                    mRechargeChongzhi1.setClickable(false);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("") && trim1 != null && !trim1.equals("") && trim2 != null && !trim2.equals("")) {
                    mRechargeChongzhi1.setClickable(true);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login);
                } else {
                    mRechargeChongzhi1.setClickable(false);
                    mRechargeChongzhi1.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void initView1() {

        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mName1 = s.toString();
                String trim = mPassword.getText().toString().trim();
                if (mName1.equals("")) {
                    mRechargeChongzhi.setClickable(false);
                    mRechargeChongzhi.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("")) {
                    mRechargeChongzhi.setClickable(true);
                    mRechargeChongzhi.setBackgroundResource(R.drawable.background_login);
                } else {
                    mRechargeChongzhi.setClickable(false);
                    mRechargeChongzhi.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mPassword1 = s.toString();
                String trim = mName.getText().toString().trim();
                if (mPassword1.equals("")) {
                    mRechargeChongzhi.setClickable(false);
                    mRechargeChongzhi.setBackgroundResource(R.drawable.background_login1);
                } else if (trim != null && !trim.equals("")) {
                    mRechargeChongzhi.setClickable(true);
                    mRechargeChongzhi.setBackgroundResource(R.drawable.background_login);
                } else {
                    mRechargeChongzhi.setClickable(false);
                    mRechargeChongzhi.setBackgroundResource(R.drawable.background_login1);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void click_show_member(View view) {
        if (number == 0) {
            mIvShowMember.setImageResource(R.mipmap.view);
            mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            number = 1;
        } else {
            mIvShowMember.setImageResource(R.mipmap.view_off);
            mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            number = 0;
        }
    }

    public void click_show_members(View view) {
        if (number1 == 0) {
            mIvShowMember1.setImageResource(R.mipmap.view);
            mPassword2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            number1 = 1;
        } else {
            mIvShowMember1.setImageResource(R.mipmap.view_off);
            mPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            number1 = 0;
        }
    }

    //登录
    public void denglv(String name, final String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", name);
        hashMap.put("password", password);
        OkGo.post(RequestAddress.HOST + RequestAddress.LOGIN)
                .params(hashMap)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Map<String, Object> map = getMapForJson(s);
                        String msg = (String) map.get("message");
                        KLog.e("msg", msg);
                        if (msg.equals("登陆失败")) {
                            mProgressHUD.dismiss();
                            Toasty.error(ECApplication.context, "登陆失败", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            JSONObject data = (JSONObject) map.get("data");
                            KLog.e("用户信息", s);
                            if (msg.equals("登陆成功")) {
                                mProgressHUD.dismiss();
                                Toasty.success(LoginsActivity.this, "登陆成功!", Toast.LENGTH_SHORT, true).show();
                                SharedPrefsUtil sharedPrefsUtil = new SharedPrefsUtil();
                                sharedPrefsUtil.put(ECApplication.context,"log",true);
                                exitAct();
                                try {
                                    KLog.e("用户密码", data.get("password"));
                                    KLog.e("用户id", data.get("uid"));
                                    initSelf(data.get("uid")+"");
                                    try {
                                        saveAccount(data.get("uid")+"");
                                    } catch (InvalidClassException e) {
                                        e.printStackTrace();
                                    }
                                    SharedPrefsUtil.put(ECApplication.context, "name", data.get("username"));
                                    SharedPrefsUtil.put(LoginsActivity.this, "UserUid", data.get("uid"));
                                    SharedPrefsUtil.put(LoginsActivity.this, "UserPassword", password);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toasty.error(LoginsActivity.this, "登陆失败,请检查账号是否正确", Toast.LENGTH_SHORT, true).show();
                            }
                            try {
                                KLog.e("s", data.get("uid"));
                                SharedPrefsUtil.put(LoginsActivity.this, "UserUid", data.get("uid"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mProgressHUD.dismiss();
                        Toast.makeText(LoginsActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //注册
    public void zhuce(String user_name, String mobile, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "RegisterUser");
        hashMap.put("user_name", user_name);
        hashMap.put("mobile", mobile);
        hashMap.put("password", password);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                KLog.json(s);
                String code = (String) mapForJson.get("code");
                String error = (String) mapForJson.get("error");
                String secess = (String) mapForJson.get("secess");
                //secess
                if (code.equals("888") && secess.equals("true")) {
                    Toast.makeText(LoginsActivity.this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
                    mLayoutDenglu.setVisibility(View.VISIBLE);
                    mLayoutZhuce.setVisibility(View.GONE);
                    mButtonDenglv.setTextColor(Color.WHITE);
                    mButtonDenglv.setBackgroundColor(CommonUtil.getColor(R.color.home));
                    mButtonZhuce.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                    mButtonZhuce.setBackgroundColor(CommonUtil.getColor(R.color.zhuceColor));
                    initView1();
                } else {
                    Toast.makeText(LoginsActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //验证手机号是否注册
    public void Yanzheng(String uid, String mobile) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "VerifyMobile");
        hashMap.put("uid", uid);
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
                        Message message = handler.obtainMessage(1); // Message
                        handler.sendMessageDelayed(message, 1000);
                        SMSSDK.getVerificationCode("86", mPhone2.getText().toString());
                    }
                }
                if (error != null) {
                    if (error.equals("该手机号码已经认证过")) {
                        Toast.makeText(LoginsActivity.this, "手机号已经被注册", Toast.LENGTH_SHORT).show();
                    }
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



    public void back_text_view(View view) {
        exitAct();
    }
    //忘记密码
    @OnClick(R.id.delv_forget_password)
    public void onClick() {
        Intent intent = new Intent(this,ResePassWordActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SMSSDK.unregisterAllEventHandler();
    }
    //初始化信息后登陆im
    private void login(String  selfId) {
        String appkey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appkey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(mLoginAuthType);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);
       // SDKCoreHelper.init(this, ECInitParams.LoginMode.FORCE_LOGIN);
        Intent intent = new Intent(this, LauncherActivity.class);
        intent.putExtra("launcher_from", 1);
        // 注册成功跳转
        startActivity(intent);
        finish();
    }
    //初始化自己登陆信息
    private void initSelf(String selfId) {
        //im登陆
        String appKey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appKey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);//初始化用户自己的信息并且把数据放到contact数据库当中
        SDKCoreHelper.init(ECApplication.getInstance().getApplicationContext(), ECInitParams.LoginMode.FORCE_LOGIN);//初始化SDK
    }
    private void saveAccount(String selfId) throws InvalidClassException {
        String appKey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser user = CCPAppManager.getClientUser();
        if (user == null) {
            user = new ClientUser(selfId);
        } else {
            user.setUserId(selfId);
        }
        user.setAppToken(token);
        user.setAppKey(appKey);
        user.setPassword("");
        user.setLoginAuthType(mLoginAuthType);
        CCPAppManager.setClientUser(user);
        ECPreferences.savePreference(ECPreferenceSettings.SETTINGS_REGIST_AUTO, user.toString(), true);
        // ContactSqlManager.insertContacts(contacts);
        ArrayList<ECContacts> objects = ContactLogic.initContacts();
        objects = ContactLogic.converContacts(objects);
        ContactSqlManager.insertContacts(objects);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
        ActivitiesCollector.removeActivity(this);
    }
}
