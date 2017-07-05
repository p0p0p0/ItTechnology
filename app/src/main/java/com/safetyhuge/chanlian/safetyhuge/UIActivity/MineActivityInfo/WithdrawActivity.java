package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：王海宾 on 2017/6/22 0022 17:55
 * 邮箱：995696826@qq.com
 */

public class WithdrawActivity extends HBaseAct {
    @Bind(R.id.tixian_money)
    EditText mTixianMoney;
    @Bind(R.id.recharge_tixian)
    Button mRechargeTixian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String money = intent.getStringExtra("money");
        mTixianMoney.setHint("本次最多可提现"+money+"元");
    }

    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick(R.id.recharge_tixian)
    public void onClick() {
        Toast.makeText(this, "提现到银行卡功能开发中....", Toast.LENGTH_SHORT).show();
    }
}
