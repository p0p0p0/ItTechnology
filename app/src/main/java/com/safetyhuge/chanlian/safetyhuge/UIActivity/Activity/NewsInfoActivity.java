package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/10 0010 19:04
 * 邮箱：995696826@qq.com
 */
public class NewsInfoActivity extends HBaseAct {
    @Bind(R.id.news_text)
    WebView mNewsText;
    @Bind(R.id.news_text11)
    TextView mNewsText11;
    @Bind(R.id.news_text_time)
    TextView mNewsTextTime;
    private String mTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String newsurl = intent.getStringExtra("newsurl");
        String newtitle = intent.getStringExtra("newtitle");
        mTime = intent.getStringExtra("time");
        setContentView(R.layout.news_particular);
        ButterKnife.bind(this);
      /*  Html html = (Html) Html.fromHtml(newsurl);
        KLog.e("html",html);*/
        KLog.e("newsurl", newsurl);
        LinearLayout.LayoutParams mWebViewLP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        mNewsText.setLayoutParams(mWebViewLP);
        mNewsText.setInitialScale(25);
        WebSettings settings = mNewsText.getSettings();
        settings.setTextSize(WebSettings.TextSize.LARGEST);
        //适应屏幕
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置WebView属性，能够执行Javascript脚本
        settings.setJavaScriptEnabled(true);
        //设置可以访问文件
        settings.setAllowFileAccess(true);
        //设置支持缩放
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        //加载需要显示的网页
        mNewsText.loadData(newsurl, "text/html; charset=UTF-8", null);
        //设置Web视图
        mNewsText.setWebViewClient(new webViewClient());
        mNewsText11.setText(newtitle);
        Spanned spanned = Html.fromHtml(newsurl);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        KLog.e("mtime",mTime);
        if (mTime != null) {
            mNewsTextTime.setText("发布时间: " + DateUtils.timesTwo(mTime));
        } else {
            mNewsTextTime.setText("发布时间: "+str);
        }
        KLog.e("spanned", spanned);
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
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
