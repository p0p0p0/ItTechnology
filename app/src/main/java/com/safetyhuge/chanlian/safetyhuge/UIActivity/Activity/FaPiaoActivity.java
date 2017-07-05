package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：王海宾 on 2017/5/26 0026 17:17
 * 邮箱：995696826@qq.com
 */

public class FaPiaoActivity extends HBaseAct {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.fapiao_bukai)
    TextView mFapiaoBukai;
    @Bind(R.id.fapiao_zhizhi)
    TextView mFapiaoZhizhi;
    @Bind(R.id.fapiao_content)
    EditText mFapiaoContent;
    @Bind(R.id.recharge_checkbox1)
    CheckBox mAllChekbox1;
    @Bind(R.id.recharge_checkbox2)
    CheckBox mAllChekbox2;
    @Bind(R.id.recharge_checkbox3)
    CheckBox mAllChekbox3;
    @Bind(R.id.bt_quit)
    Button mBtQuit;
    @Bind(R.id.layout_checkbox1)
    LinearLayout mLayoutCheckbox1;
    @Bind(R.id.layout_checkbox2)
    LinearLayout mLayoutCheckbox2;
    @Bind(R.id.layout_checkbox3)
    LinearLayout mLayoutCheckbox3;
    private String mSid;
    String flag = "0";
    String type = "-1";
    private Intent mIntent;
    private ArrayList<String> mSidinfo;

    @Override
    protected void onCreate(Bundle arg0) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(arg0);
        setContentView(R.layout.activity_fapiao);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        //关闭
        mIntent = new Intent();
        mSid = intent.getStringExtra("sid");
        mSidinfo = intent.getStringArrayListExtra("sidinfo");

        mFapiaoContent.setEnabled(false);
        mAllChekbox1.setClickable(false);
        mAllChekbox2.setClickable(false);
        mAllChekbox3.setClickable(false);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }

    private void initView() {
        mFapiaoBukai.setSelected(true);
        //抬头
        mIntent.putExtra("info", "0");
        setResult(2, mIntent);
        type = "0";
      /*  mAllChekbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag = "1";
                    mAllChekbox1.setClickable(false);
                    mAllChekbox2.setClickable(true);
                    mAllChekbox2.setChecked(false);
                    mAllChekbox3.setClickable(true);
                    mAllChekbox3.setChecked(false);
                }
            }
        });
        mAllChekbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag = "2";
                    mAllChekbox2.setClickable(false);
                    mAllChekbox1.setClickable(true);
                    mAllChekbox1.setChecked(false);
                    mAllChekbox3.setClickable(true);
                    mAllChekbox3.setChecked(false);
                }
            }
        });
        mAllChekbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mAllChekbox3.isChecked()) {
                    flag = "3";
                    mAllChekbox3.setClickable(false);
                    mAllChekbox2.setClickable(true);
                    mAllChekbox2.setChecked(false);
                    mAllChekbox1.setClickable(true);
                    mAllChekbox1.setChecked(false);
                }
            }
        });*/
    }

    public void back_text_view(View view) {
        finish();
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.layout_checkbox1, R.id.layout_checkbox2, R.id.layout_checkbox3, R.id.fapiao_bukai, R.id.fapiao_zhizhi, R.id.recharge_checkbox1, R.id.recharge_checkbox2, R.id.recharge_checkbox3, R.id.bt_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fapiao_bukai:
                type = "0";
                mFapiaoContent.setEnabled(false);
                mAllChekbox1.setClickable(false);
                mAllChekbox2.setClickable(false);
                mAllChekbox3.setClickable(false);
                mFapiaoBukai.setSelected(true);
                mFapiaoZhizhi.setSelected(false);
                break;
            case R.id.fapiao_zhizhi:
                mFapiaoBukai.setSelected(false);
                mFapiaoZhizhi.setSelected(true);
                type = "1";
                mFapiaoContent.setEnabled(true);
                mAllChekbox1.setClickable(true);
                mAllChekbox2.setClickable(true);
                mAllChekbox3.setClickable(true);
                break;
            case R.id.recharge_checkbox1:
                flag = "1";
                break;
            case R.id.recharge_checkbox2:
                flag = "2";
                break;
            case R.id.recharge_checkbox3:
                flag = "3";
                break;
            case R.id.bt_quit:
                String trim = mFapiaoContent.getText().toString().trim();
                if (type.equals("1")) {
                    if (!trim.isEmpty()) {
                        if (flag.equals("0")) {
                            Toast.makeText(this, "请选择发票类型", Toast.LENGTH_SHORT).show();
                        } else {
                            KLog.e("whb", flag);
                            //开不开发票
                            mIntent.putExtra("type", type);
                            //发票类型
                            mIntent.putExtra("flag", flag);
                            //抬头
                            mIntent.putExtra("info", trim);
                            mIntent.putStringArrayListExtra("sidinfo", mSidinfo);
                            setResult(1, mIntent);
                            exitAct();
                        }
                    } else {
                        Toast.makeText(this, "请填写发票抬头", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //抬头
                    mIntent.putExtra("info", "0");
                    setResult(2, mIntent);
                    exitAct();
                    mFapiaoContent.setEnabled(false);
                    mAllChekbox1.setChecked(false);
                    mAllChekbox2.setChecked(false);
                    mAllChekbox3.setChecked(false);
                }
                break;
            case R.id.layout_checkbox1:
                flag = "1";
                mAllChekbox1.setChecked(true);
                mAllChekbox1.setClickable(false);
                mAllChekbox2.setClickable(true);
                mAllChekbox2.setChecked(false);
                mAllChekbox3.setClickable(true);
                mAllChekbox3.setChecked(false);
                break;
            case R.id.layout_checkbox2:
                flag = "2";
                mAllChekbox2.setChecked(true);
                mAllChekbox2.setClickable(false);
                mAllChekbox1.setClickable(true);
                mAllChekbox1.setChecked(false);
                mAllChekbox3.setClickable(true);
                mAllChekbox3.setChecked(false);
                break;
            case R.id.layout_checkbox3:
                flag = "3";
                mAllChekbox3.setChecked(true);
                mAllChekbox3.setClickable(false);
                mAllChekbox2.setClickable(true);
                mAllChekbox2.setChecked(false);
                mAllChekbox1.setClickable(true);
                mAllChekbox1.setChecked(false);
                break;
        }
    }
}
