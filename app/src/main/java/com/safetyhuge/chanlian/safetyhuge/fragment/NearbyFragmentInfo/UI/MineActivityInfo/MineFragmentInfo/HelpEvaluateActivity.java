package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/5/18 0018 16:41
 * 邮箱：995696826@qq.com
 */

public class HelpEvaluateActivity extends HBaseAct {
    @Bind(R.id.pingjia_ed)
    EditText mPingjiaEd;
    @Bind(R.id.all_chekbox1)
    CheckBox mAllChekbox1;
    @Bind(R.id.all_chekbox2)
    CheckBox mAllChekbox2;
    @Bind(R.id.all_chekbox3)
    CheckBox mAllChekbox3;
    @Bind(R.id.pingjia_button)
    Button mPingjiaButton;
    private String mId;
    private String mMark_status = null;
    private String mUserid;
    private String mUsername;
    private int mFlag;
    private String mUsername1;
    private String mUid;
    private String mService_id;
    private String mDd_id;
    private String mBy_uid;
    private String mBy_username;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpevaluate);
        ButterKnife.bind(this);
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        mUsername = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
       /* intent.putExtra("service_id",mData.get(position).getService_id());
        intent.putExtra("dd_id",mData.get(position).getDd_id());
        intent.putExtra("by_uid",mData.get(position).getFw_uid());
        intent.putExtra("by_username",mData.get(position).getFw_username());*/
        mService_id = intent.getStringExtra("service_id");
        mDd_id = intent.getStringExtra("dd_id");
        mBy_uid = intent.getStringExtra("by_uid");
        mBy_username = intent.getStringExtra("by_username");

        mId = intent.getStringExtra("id");
        mFlag = intent.getIntExtra("flag", -1);
        mUid = intent.getStringExtra("uid");
        mUsername1 = intent.getStringExtra("username");
        mAllChekbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMark_status = "1";
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
                    mMark_status = "2";
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
                if (isChecked) {
                    mMark_status = "3";
                    mAllChekbox3.setClickable(false);
                    mAllChekbox2.setClickable(true);
                    mAllChekbox2.setChecked(false);
                    mAllChekbox1.setClickable(true);
                    mAllChekbox1.setChecked(false);
                }
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @SuppressLint("WrongConstant")
    @OnClick(R.id.pingjia_button)
    public void onClick() {
        String context = mPingjiaEd.getText().toString().trim();
        if (!context.isEmpty()) {
            if (mMark_status != null) {
                switch (mFlag) {
                    case 0:
                        // 评价服务订单（买家发布）
                        netWork0(mService_id,mDd_id,mUserid,mUsername,mBy_uid,mBy_username,mMark_status,context);
                        break;
                    case 1:
                        // 评价服务订单（服务方发布）
                        netWork1(mService_id,mDd_id,mUserid,mUsername,mBy_uid,mBy_username,mMark_status,context);
                        break;
                    case 2:
                        //netWork2(mId, mUserid, mUsername, mMark_status, context);
                        break;
                    case 3:
                      //  netWork3(mId, mUid, mUsername1, mMark_status, context);
                        break;
                }
            } else {
                Toast.makeText(this, "请选择评价", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
        }
    }

    // 评价服务订单（买家发布）
    private void netWork0(String service_id,String dd_id, String by_uid, String by_username,String  uid,String  username, String mark_status, String mark_contents) {
        /*id	求助id（参数传递）
        by_uid	发布者id（参数传递）
        by_username	发布者（参数传递）
        mark_status	评价状态：0未评价1好2中3差（参数传递）
        mark_contents	评价内容（参数传递）*/
     /*   service_id
                by_uid
        by_username*/

        HashMap<String, String> hashMap = new HashMap<>();
        /*
        service_id
        dd_id
        by_uid
        by_username
        uid
        username
        mark_content
        mark_status*/
        hashMap.put("service_id", service_id);
        hashMap.put("dd_id", dd_id);
        hashMap.put("by_uid", by_uid);
        hashMap.put("by_username", by_username);
        hashMap.put("uid", uid);
        hashMap.put("username", username);
        hashMap.put("mark_status", mark_status);
        hashMap.put("mark_contents", mark_contents);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + "fuwufbpl.php").params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                Object message = mapForJson.get("message");
                if (message.equals("评论成功")) {
                    exitAct();
                    Toasty.success(ECApplication.context, "评论成功").show();
                }
            }
        });
    }

    // 评价服务订单（服务方发布）
    private void netWork1(String service_id,String dd_id, String by_uid, String by_username,String  uid,String  username, String mark_status, String mark_contents) {
        /*id	求助id（参数传递）
        by_uid	发布者id（参数传递）
        by_username	发布者（参数传递）
        mark_status	评价状态：0未评价1好2中3差（参数传递）
        mark_contents	评价内容（参数传递）
        service_id
uid
username
        */
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("service_id", service_id);
        hashMap.put("dd_id", dd_id);
        hashMap.put("by_uid", by_uid);
        hashMap.put("by_username", by_username);
        hashMap.put("uid", uid);
        hashMap.put("username", username);
        hashMap.put("mark_status", mark_status);
        hashMap.put("mark_contents", mark_contents);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.FBPJQZ).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                Object message = mapForJson.get("message");
                if (message.equals("评论成功")) {
                    exitAct();
                    Toasty.success(ECApplication.context, "评论成功").show();
                }
            }
        });
    }

    //评价服务订单（服务方发布）
    private void netWork2(String id, String uid, String uname, String status, String contents) {
        /*id	求助id（参数传递）
        by_uid	发布者id（参数传递）
        by_username	发布者（参数传递）
        mark_status	评价状态：0未评价1好2中3差（参数传递）
        mark_contents	评价内容（参数传递）*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("service_id", id);
        hashMap.put("dd_id", id);
        hashMap.put("by_uid", uid);
        hashMap.put("by_username", uname);
        hashMap.put("mark_status", status);
        hashMap.put("mark_contents", contents);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + "fuwufbpl.php").params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                Object message = mapForJson.get("message");
                if (message.equals("评论成功")) {
                    exitAct();
                    Toasty.success(ECApplication.context, "评论成功").show();
                }
            }
        });
    }

    // //评价求助订单（求助者评价）
    private void netWork3(String id, String uid, String uname, String status, String contents) {
        /*id	求助id（参数传递）
        by_uid	发布者id（参数传递）
        by_username	发布者（参数传递）
        mark_status	评价状态：0未评价1好2中3差（参数传递）
        mark_contents	评价内容（参数传递）*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("uid", uid);
        hashMap.put("username", uname);
        hashMap.put("mark_status", status);
        hashMap.put("mark_contents", contents);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + "fuwufbpl2.php").params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                Object message = mapForJson.get("message");
                if (message.equals("评论成功")) {
                    exitAct();
                    Toasty.success(ECApplication.context, "评论成功").show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
