package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 建议反馈
 * 徐艳昭
 * 1532657231@163.com
 * 2017/3/8
 **/
public class EJanActivity extends HBaseAct {
    @Bind(R.id.et_opinion)
    EditText mEtOpinion;
    @Bind(R.id.commit_tv)
    TextView mCommitTv;
    private String mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(EJanActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejan);
        ButterKnife.bind(this);
        mUid = (String) SharedPrefsUtil.get(ECApplication.context,"UserUid","");
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick(R.id.commit_tv)
    public void onClick() {
        String desc = mEtOpinion.getText().toString();
        if (!desc.isEmpty()){
            NetWork(mUid,desc);
            exitAct();
            Toast.makeText(this, "感谢您的宝贵意见", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
        }
    }
    public void NetWork(String uid,String desc){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("pro_title","");
        hashMap.put("pro_desc",desc);
        OkGo.post(RequestAddress.HOST+RequestAddress.YJFK).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.e("s",s);
            }
        });
    }
    public void onResume() {
        super.onResume();
        mUid = (String) get(ECApplication.context, "UserUid", "");
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
