package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CompetitiveActivityInfo;

import android.content.Context;
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
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;

/**
 * 多人单人投标
 * 作者：王海宾 on 2017/4/13 0013 09:43
 * 邮箱：995696826@qq.com
 */

public class CompetitiveActivity extends HBaseAct {

    @Bind(R.id.comperttove_et)
    EditText mComperttoveEt;
    @Bind(R.id.textView7)
    TextView mTextView7;
    @Bind(R.id.comperttove_cx1)
    CheckBox mComperttoveCx1;
    @Bind(R.id.comperttove_cx2)
    CheckBox mComperttoveCx2;
    @Bind(R.id.comperttove_button)
    Button mComperttoveButton;
    @Bind(R.id.hidden_layout)
    LinearLayout mHiddenLayout;
    private Context mContext;
    private KProgressHUD mKProgressHUD;
    private String mFlag;
    private String mMUserid;
    private String mTask_id;
    private String mHidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comperttove);
        mContext = CompetitiveActivity.this;
        Intent intent = getIntent();
        mMUserid = intent.getStringExtra("userid");
        mTask_id = intent.getStringExtra("task_id");
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        ButterKnife.bind(this);
        intiView();
        alentss(mMUserid, mTask_id);
    }

    public void alentss(String uid, String task_id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "Delivery");
        if (mMUserid != "") {
            hashMap.put("uid", uid);
        } else {
            hashMap.put("uid", "1");
        }
        hashMap.put("task_id", task_id);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Map<String, Object> map = getMapForJson(s);
                String msg = (String) map.get("code");
                if (msg.equals("888")) {
                    mHidden = (String) map.get("work_hidden");
                    KLog.e("mHidden",mHidden);
                    if (mHidden.equals("1")) {
                        mHiddenLayout.setVisibility(View.VISIBLE);
                    }else{
                        mHiddenLayout.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
    public  void  intiView(){
        mComperttoveCx1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFlag = "1";
                    mComperttoveCx1.setClickable(false);
                    mComperttoveCx2.setClickable(true);
                    mComperttoveCx2.setChecked(false);
                }
            }
        });
        mComperttoveCx2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFlag = "0";
                    mComperttoveCx1.setChecked(false);
                    mComperttoveCx1.setClickable(true);
                    mComperttoveCx2.setClickable(false);
                }
            }
        });
    }
    private void alents(String uid, String task_id, String tar_content, String work_hidden) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "Delivery");
        if (mMUserid != "") {
            hashMap.put("uid", uid);
        } else {
            hashMap.put("uid", "1");
        }
        hashMap.put("task_id", task_id);
        hashMap.put("tar_content", tar_content);
        if (work_hidden==null){
            hashMap.put("work_hidden", "0");
        }else{
            hashMap.put("work_hidden", work_hidden);
        }
        hashMap.put("file", "");
        hashMap.put("submit", "1");
        KLog.e("hashMap",hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                String error = (String) map.get("error");
                if (code.equals("888")){
                    Toasty.success(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                    exitAct();
                }else{
                    Toasty.error(mContext, error, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick(R.id.comperttove_button)
    public void onClick() {
        if (!mComperttoveEt.getText().toString().isEmpty()) {
            alents(mMUserid, mTask_id, mComperttoveEt.getText().toString(), mFlag);

        } else {
            Toast.makeText(mContext, "描述内容不能为空", Toast.LENGTH_SHORT).show();
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
