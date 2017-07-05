package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;

/**
 * 作者：王海宾 on 2017/4/11 0011 13:59
 * 邮箱：995696826@qq.com
 */
public class PostponeActivity extends HBaseAct {

    @Bind(R.id.postpone_title)
    TextView mPostponeTitle;
    @Bind(R.id.postpone_money)
    TextView mPostponeMoney;
    @Bind(R.id.postpone_zhuangtai)
    TextView mPostponeZhuangtai;
    @Bind(R.id.postpone_checkbox1)
    CheckBox mPostponeCheckbox1;
    @Bind(R.id.postpone_checkbox2)
    CheckBox mPostponeCheckbox2;
    @Bind(R.id.postpone_et1)
    EditText mPostponeEt1;
    @Bind(R.id.postpone_et2)
    EditText mPostponeEt2;
    @Bind(R.id.postpone_bt)
    Button mPostponeBt;
    @Bind(R.id.postpone_layout)
    LinearLayout mPostponeLayout;
    private KProgressHUD mProgressHUD;
    private String mUsername;
    private String mShow_cash;
    private String mModel;
    private String mUserid;
    private int a;
    private String mEt2;
    private String mEt1;
    private String mModelid;
    private String mItem;
    private int mA21;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_postpone);
        ButterKnife.bind(this);
        mProgressHUD = KProgressHUD.create(PostponeActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        Intent intent = getIntent();
        initData(intent);
        initView();
    }

    private void initData(Intent intent) {
        mUsername = intent.getStringExtra("username");
        mShow_cash = intent.getStringExtra("show_cash");
        mModel = intent.getStringExtra("model");
        mModelid = intent.getStringExtra("modelid");
        mItem = intent.getStringExtra("item");
    }

    public void initView() {
        mPostponeTitle.setText(mUsername);
        mPostponeMoney.setText(mShow_cash);
        mPostponeZhuangtai.setText(mModel);
        mPostponeCheckbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPostponeLayout.setVisibility(View.GONE);
                    mPostponeCheckbox1.setClickable(false);
                    mPostponeCheckbox2.setClickable(true);
                    mPostponeCheckbox2.setChecked(false);
                }
            }
        });
        mPostponeCheckbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPostponeLayout.setVisibility(View.VISIBLE);
                    mPostponeCheckbox1.setChecked(false);
                    mPostponeCheckbox1.setClickable(true);
                    mPostponeCheckbox2.setClickable(false);
                }
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick({R.id.postpone_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.postpone_bt:
                mEt1 = mPostponeEt1.getText().toString();
                if (!mEt1.equals("")) {
                    a = Integer.parseInt(mEt1);
                }
                mEt2 = mPostponeEt2.getText().toString();
                if (!mEt1.equals("")) {
                    if (a >= 100) {
                        if (mPostponeLayout.getVisibility() == View.VISIBLE) {
                            if (!mEt2.isEmpty()) {
                                if (!mEt2.equals("")) {
                                    mA21 = Integer.parseInt(mEt2);
                                    if (mA21 > 0) {
                                        String day = getDateStr(mItem, mA21);
                                        KLog.e("mItem", mItem);
                                        KLog.e("day",day);
                                        NewWork(mUserid, mModelid,mA21+"", mEt1);
                                    } else {
                                        Toast.makeText(this, "天数不能小于0", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(this, "日期不能为空", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            NewWork(mUserid, mModelid, "", mEt1);
                            Toast.makeText(this, "请求网络", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "预算大于100元", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "预算不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void NewWork(String mUserid, String mSchemeid, String day, String cash) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "DelayTask");
        if (mUserid.equals("")) {
            hashMap.put("uid", "1");
        } else {
            hashMap.put("uid", mUserid);
        }
        hashMap.put("task_id", mSchemeid);
        hashMap.put("delay_day", day);
        hashMap.put("delay_cash", cash);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                String error = (String) map.get("error");
                if (code.equals("888")){
                    Toast.makeText(PostponeActivity.this, "延期成功", Toast.LENGTH_SHORT).show();
                    exitAct();
                }else{
                    Toast.makeText(PostponeActivity.this, error, Toast.LENGTH_SHORT).show();
                }
                KLog.json(s);
            }
        });
    }

    public static String getDateStr(String day, int dayAddNum) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date newDate2 = new Date(nowDate.getTime() + dayAddNum * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
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
