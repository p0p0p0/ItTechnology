package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：王海宾 on 2017/4/17 0017 21:45
 * 邮箱：995696826@qq.com
 */

public class MineWalletActivity extends HBaseAct {
    @Bind(R.id.minewall_money)
    TextView mMinewallMoney;
    @Bind(R.id.minewall_button1)
    LinearLayout mMinewallButton1;
    @Bind(R.id.minewall_button2)
    LinearLayout mMinewallButton2;
    private Intent intent;
    private String mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minewall);
        ButterKnife.bind(this);
        intent = getIntent();
        mIntent = this.intent.getStringExtra("intent");
        mMinewallMoney.setText(mIntent);
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick({R.id.minewall_button1, R.id.minewall_button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.minewall_button1:
                startActivity(new Intent(this,RechargeActivity.class));
                break;
            case R.id.minewall_button2:
                Intent intent = new Intent(this,WithdrawActivity.class);
                intent.putExtra("money",mIntent);
                startActivity(intent);
              //  Toast.makeText(this, "功能开发中....", Toast.LENGTH_SHORT).show();
                break;
        }
    }@Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
