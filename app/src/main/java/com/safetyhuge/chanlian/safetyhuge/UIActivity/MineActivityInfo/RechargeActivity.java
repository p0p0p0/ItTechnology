package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Pay.util.OrderInfoUtil2_0;
import com.safetyhuge.chanlian.safetyhuge.Pay.util.PayDemoUtil;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 充值界面
 * 作者：王海宾 on 2017/4/28 0028 10:08
 * 邮箱：995696826@qq.com
 */
public class RechargeActivity extends HBaseAct {

    @Bind(R.id.recharge_money)
    EditText mRechargeMoney;
    @Bind(R.id.recharge_checkbox)
    CheckBox mRechargeCheckbox;
    @Bind(R.id.recharge_checkbox2)
    CheckBox mRechargeCheckbox2;
    @Bind(R.id.recharge_checkbox3)
    CheckBox mRechargeCheckbox3;
    @Bind(R.id.recharge_chongzhi)
    Button mRechargeChongzhi;
    private int mMark_status = -1;
    private String mUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        initView();
    }

    private void initView() {
        mRechargeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMark_status = 1;
                    mRechargeCheckbox.setClickable(false);
                    mRechargeCheckbox2.setClickable(true);
                    mRechargeCheckbox2.setChecked(false);
                    mRechargeCheckbox3.setClickable(true);
                    mRechargeCheckbox3.setChecked(false);
                }
            }
        });
        mRechargeCheckbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMark_status = 2;
                    mRechargeCheckbox2.setClickable(false);
                    mRechargeCheckbox.setClickable(true);
                    mRechargeCheckbox.setChecked(false);
                    mRechargeCheckbox3.setClickable(true);
                    mRechargeCheckbox3.setChecked(false);
                }
            }
        });
        mRechargeCheckbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMark_status = 3;
                    mRechargeCheckbox3.setClickable(false);
                    mRechargeCheckbox2.setClickable(true);
                    mRechargeCheckbox2.setChecked(false);
                    mRechargeCheckbox.setClickable(true);
                    mRechargeCheckbox.setChecked(false);
                }
            }
        });
    }

    @OnClick({R.id.recharge_chongzhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recharge_chongzhi:
                switch (mMark_status) {
                    case 1:
                        String trim = mRechargeMoney.getText().toString().trim();
                        int money = Integer.valueOf(trim);
                        if (!trim.equals("")) {
                            if (money >= 100) {
                                PayNetWork(money + "");
                            } else {
                                Toast.makeText(this, "充值金额不能小于100元", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "请输入充值金额", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        Toast.makeText(this, "暂未开放银联支付", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(this, "暂未开放微信支付", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
    }

    private void PayNetWork(final String money) {
  /*      action:CreateOrderCharge
        uid:用户id
        floatRecharge:充值金额*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "CreateOrderCharge");
        hashMap.put("uid", mUserid);
        hashMap.put("floatRecharge", money);
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
            /*    {
                    "code": "888",
                        "secess": "true",
                        "data": 68
                }*/
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                int data = (int) mapForJson.get("data");
                if (code.equals("888") && secess.equals("true") && data != 0) {
                    OrderInfoUtil2_0.SetMsg(getOutTradeNo()+data+"", "IT科技谷账户充值", money);
                    PayDemoUtil demoUtil = new PayDemoUtil(RechargeActivity.this);
                    demoUtil.payV2();
                }
            }
        });
    }
    private  String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        return key;
    }

    public void back_text_view(View view) {
        exitAct();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
