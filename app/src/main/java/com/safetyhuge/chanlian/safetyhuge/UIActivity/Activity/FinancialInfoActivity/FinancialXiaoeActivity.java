package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.FinancialInfoActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 小额贷款
 * 作者：王海宾 on 2017/4/15 0015 10:42
 * 邮箱：995696826@qq.com
 */
public class FinancialXiaoeActivity extends HBaseAct {
    @Bind(R.id.textView1)
    TextView mTextView1;
    @Bind(R.id.textView2)
    TextView mTextView2;
    @Bind(R.id.baitiao)
    LinearLayout mBaitiao;
    @Bind(R.id.textView8)
    TextView mTextView8;
    @Bind(R.id.dingdan)
    LinearLayout mDingdan;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_xiaoe);
        mContext = FinancialXiaoeActivity.this;
        ButterKnife.bind(this);
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick({R.id.textView1, R.id.textView2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView1:
                mTextView2.setTextColor(CommonUtil.getColor(R.color.black));
                mTextView2.setBackgroundColor(Color.WHITE);

                mTextView1.setTextColor(Color.WHITE);
                mTextView1.setBackgroundColor(CommonUtil.getColor(R.color.lanse));
                mDingdan.setVisibility(View.VISIBLE);
                mBaitiao.setVisibility(View.GONE);
                break;
            case R.id.textView2:
                mTextView1.setTextColor(CommonUtil.getColor(R.color.black));
                mTextView1.setBackgroundColor(Color.WHITE);

                mTextView2.setTextColor(Color.WHITE);
                mTextView2.setBackgroundColor(CommonUtil.getColor(R.color.lanse));
                mBaitiao.setVisibility(View.VISIBLE);
                mDingdan.setVisibility(View.GONE);
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
