package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.FinancialInfoActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinancialActivity extends HBaseAct {

    @Bind(R.id.financial_jinku)
    LinearLayout mFinancialJinku;
    @Bind(R.id.financial_zhongchou)
    LinearLayout mFinancialZhongchou;
    @Bind(R.id.financial_xiaoe)
    LinearLayout mFinancialXiaoe;
    @Bind(R.id.financial_rongzi)
    LinearLayout mFinancialRongzi;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        mContext = FinancialActivity.this;
        ButterKnife.bind(this);
    }

    @OnClick({R.id.financial_jinku, R.id.financial_zhongchou, R.id.financial_xiaoe,R.id.financial_rongzi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.financial_jinku:
                Intent intent = new Intent(mContext, FinancialJinKuActivity.class);
                startActivity(intent);
                exitAct();
                break;
            case R.id.financial_zhongchou:
                Intent intent1 = new Intent(mContext, FinancialZhongChouActivity.class);
                startActivity(intent1);
                exitAct();
                break;
            case R.id.financial_xiaoe:
                Intent intent2 = new Intent(mContext, FinancialXiaoeActivity.class);
                startActivity(intent2);
                exitAct();
                break;
            case R.id.financial_rongzi:
                Intent intent3 = new Intent(mContext, FinancialRongzActivity.class);
                startActivity(intent3);
                exitAct();
                break;
        }
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
