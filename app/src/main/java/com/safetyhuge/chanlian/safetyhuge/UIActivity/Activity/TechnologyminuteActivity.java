package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.JiShuBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerControl;
import com.xiao.nicevideoplayer.NiceVideoPlayerController;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/20 0020 10:42
 * 邮箱：995696826@qq.com
 */
public class TechnologyminuteActivity extends HBaseAct {
    KProgressHUD mKProgressHUD;
    @Bind(R.id.technology_title)
    TextView mTechnologyTitle;
    @Bind(R.id.minute_layout)
    RelativeLayout mMinuteLayout;
    @Bind(R.id.minute_xiazai)
    Button mMinuteXiazai;
    @Bind(R.id.video_view)
    NiceVideoPlayer mVideoView;
    @Bind(R.id.miuute_layout)
    LinearLayout mMiuuteLayout;
    private String mFid;
    private ArrayList<String> mStringList;
    private String mUserid;
    private NiceVideoPlayerControl mNiceVideoPlayer;
    private NiceVideoPlayerController mController;
    private String mFile_path;
    private boolean mPay_flag;
    private String mFlag;
    private JiShuBean.DataBean mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        setContentView(R.layout.activity_technology_minute);
        ButterKnife.bind(this);
        mMiuuteLayout.setVisibility(View.INVISIBLE);
        mKProgressHUD = KProgressHUD.create(TechnologyminuteActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        Intent intent = getIntent();
        mFid = intent.getStringExtra("uid");
        mFlag = intent.getStringExtra("flag");
        mVideoView = (NiceVideoPlayer) this.findViewById(R.id.video_view);
        mNiceVideoPlayer = new NiceVideoPlayer(this);
        mController = new NiceVideoPlayerController(TechnologyminuteActivity.this);
        NetWork();
        registerBoradcastReceiver();
    }

    private void NetWork() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsDetails");
        hashMap.put("uid", mUserid);
        hashMap.put("sid", mFid);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<JiShuBean>() {
            @Override
            public void onSuccess(JiShuBean jiShuBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mMiuuteLayout.setVisibility(View.VISIBLE);
                mData = jiShuBean.getData();
                mPay_flag = mData.isPay_flag();
                String price = mData.getPrice();
                mController.setFlag(mPay_flag);
                mTechnologyTitle.setText(mData.getTitle());
                if (mPay_flag == true && mFlag.equals("0")) {
                    mMinuteXiazai.setText("下载附件(免费)");
                } else if (mPay_flag == true && mFlag.equals("1")) {
                    mMinuteXiazai.setText("下载附件(已购买)");
                }
                if (mPay_flag==false){
                    mMinuteXiazai.setText("下载附件("+price+"元"+")");
                }
                mFile_path = mData.getFile_path();
                Uri uri = Uri.parse(RequestAddress.IMAGE1 + mFile_path);
                mVideoView.setPlayerType(NiceVideoPlayer.PLAYER_TINY_WINDOW); // or NiceVideoPlayer.PLAYER_NATIVE
                mVideoView.setUp(uri+ "", null);
                mController.setTitle(mData.getTitle());
                mController.setImage(RequestAddress.IMAGE1 + mData.getPic());
                mVideoView.setController(mController);
            }
        });
    }
    public void back_text_view(View view) {
        exitAct();
    }

   /* public void initView() {
        if (mXiazai != null) {
            if (mXiazai.equals("xiazai")) {
                mTechnologyWebview.setVisibility(View.GONE);
                mMinuteLayout.setVisibility(View.VISIBLE);
                KLog.e("xiazai",mXiazai);
            }
        } else {
            mTechnologyWebview.setVisibility(View.VISIBLE);
            mMinuteLayout.setVisibility(View.GONE);
        }
        //调用
        if (mContent != null) {
            mTextView.setText(Html.fromHtml(mContent));
        }
        mTechnologyTitle.setText(mTitle);
        WebSettings webSettings = mTechnologyWebview.getSettings();
        // 设置默认缩放
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);// 这个很关键
        webSettings.setUseWideViewPort(true);
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setTextSize(WebSettings.TextSize.LARGEST);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //加载需要显示的网页
        if (mContent != null) {
            mTechnologyWebview.loadData(mContent, "text/html; charset=UTF-8", null);
            //设置Web视图
            mTechnologyWebview.setWebViewClient(new
                    webViewClient()
            );
            Spanned spanned = Html.fromHtml(mContent);
            KLog.e("spanned", spanned);
        }
    }*/

    @OnClick(R.id.minute_xiazai)
    public void onClick() {
        if (mPay_flag==false){
            //开启支付页面
            Intent intent = new Intent(TechnologyminuteActivity.this, PayActivity.class);
            intent.putExtra("money", mData.getPrice());
            intent.putExtra("mTitle", mData.getTitle());
            intent.putExtra("mid", mData.getService_id());
            intent.putExtra("mflag", "0");
            startActivity(intent);
        }else{
            if (mFile_path != null) {
                mStringList = new ArrayList<String>();
                mStringList.add(mFile_path);
                Intent intent = new Intent(this, SchemeFilesInfoActivity.class);
                intent.putStringArrayListExtra("scheme", mStringList);
                intent.putExtra("uid",mData.getService_id());
                startActivity(intent);
            }
        }
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
        mVideoView.pause();

    }
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;

    public void registerBoradcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(TechnologyminuteActivity.this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zhifu");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("com.zhifu")) {
                    mPay_flag=true;
                    KLog.e("whb", "我接收到了");
                    mController.setFlag(true);
                    mMinuteXiazai.setText("下载附件(已购买)");
                }

            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }
}
