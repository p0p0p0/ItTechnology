package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.FinancialInfoActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**金库
 * 作者：王海宾 on 2017/4/15 0015 10:42
 * 邮箱：995696826@qq.com
 */
public class FinancialJinKuActivity extends HBaseAct{

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_jinku);
        mContext = FinancialJinKuActivity.this;
        ButterKnife.bind(this);
    }
    public void back_text_view(View view) {
        exitAct();
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
