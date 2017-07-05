package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.YaoqingBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.InvitationAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 邀请竞标
 * 作者：王海宾 on 2017/4/14 0014 15:57
 * 邮箱：995696826@qq.com
 */
public class InvitationActivity extends HBaseAct implements InvitationAdapter.CheckInterface {

    @Bind(R.id.invitation_list)
    MyListView mInvitationList;
    @Bind(R.id.invitation_cx1)
    CheckBox mInvitationCx1;
    @Bind(R.id.invitation_text)
    TextView mInvitationText;
    @Bind(R.id.jiemian)
    LinearLayout mJiemian;
    @Bind(R.id.text111)
    TextView mText111;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Context mContext;
    private KProgressHUD mProgressHUD;
    private String mUserid;
    private String mSchemeid;
    private List<YaoqingBean.DataBean> mData;
    private ArrayList<YaoqingBean.DataBean> mDataBeen;
    private InvitationAdapter mAdapter;
    private int checkNum; // 记录选中的条目数量
    private TextView tv_show;// 用于显示选中的条目数量
    public int count = 0;
    private JSONObject mArray;
    private JSONObject mArray1;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        ButterKnife.bind(this);
        mContext = InvitationActivity.this;
        mProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        Intent intent = getIntent();
        mSchemeid = intent.getStringExtra("yaoqing");
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        KLog.e("mSchemeid", mSchemeid);
        mJiemian.setVisibility(View.GONE);
        NewWork2(mUserid);
    }


    public void NewWork2(String mUserid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfoByUser");
        hashMap.put("uid", mUserid);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<YaoqingBean>() {
            @Override
            public void onSuccess(YaoqingBean yaoqingBean, Call call, Response response) {
                mProgressHUD.dismiss();
                mData = yaoqingBean.getData();
                if (mData != null) {
                    if (mData.isEmpty()) {
                        mQuanbuImage.setVisibility(View.VISIBLE);
                        mText111.setText("您还没有招标中的项目");
                    } else {
                        mJiemian.setVisibility(View.VISIBLE);
                        mDataBeen = new ArrayList<YaoqingBean.DataBean>();
                        for (YaoqingBean.DataBean dataBean : mData) {
                            if (dataBean.getShow_status().equals("投稿中")) {
                                mDataBeen.add(dataBean);
                            }
                        }
                        if (mDataBeen.size() == 0) {
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("您还没有招标中的项目");
                        } else {
                            mAdapter = new InvitationAdapter(mContext, mDataBeen);
                            mAdapter.setCheckInterface(InvitationActivity.this);
                            mInvitationList.setAdapter(mAdapter);
                            initView();
                        }
                    }
                } else {
                    mQuanbuImage.setVisibility(View.VISIBLE);
                    mText111.setText("您还没有招标中的项目");
                }
            }

        });

    }

    public void back_text_view(View view) {
        exitAct();
    }
    StringBuilder mBuilder = new StringBuilder();
    StringBuilder mBuilder1= new StringBuilder();
    @OnClick(R.id.invitation_text)
    public void onClick() {
        if (mInvitationText.getText().toString().equals("邀请(0)")){
            Toast.makeText(mContext, "请选择项目", Toast.LENGTH_SHORT).show();
            return;
        }
        yaoqing();
        String substring = mBuilder.substring(0, mBuilder.length() - 1);
        String substring1 = mBuilder1.substring(0, mBuilder1.length() - 1);
        KLog.e("whb",substring );
        KLog.e("whb", substring1);
        NewWork1(mSchemeid, mUserid,substring,substring1);

    }
    private void NewWork1(String mUserid, final String mSchemeid, String substring, String substring1) {
      HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "SendBid");
        hashMap.put("uid", mUserid);
        hashMap.put("to_uid", mSchemeid);
        hashMap.put("checkbox", "["+substring+"]");
        hashMap.put("task_title","["+substring1+"]");
        hashMap.put("submit", "1");
        KLog.e("whb",hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String s1 = (String) mapForJson.get("secess");
                if (code.equals("888")&&s1.equals("true")){
                    exitAct();
                    Toast.makeText(mContext, "邀请成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "邀请失败", Toast.LENGTH_SHORT).show();

                }
              /*  exitAct();
                Toast.makeText(mContext, "邀请成功", Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    private void yaoqing() {
        mBuilder.setLength(0);
        mBuilder1.setLength(0);
        for (int i = 0; i < mDataBeen.size(); i++) {
            if (mDataBeen.get(i).isChoosed()) {
                String task_id = mDataBeen.get(i).getTask_id();
                mBuilder.append(i + "," + task_id + ",");
                mBuilder1.append(task_id + "," + mDataBeen.get(i).getTask_title() + ",");
            }
        }
    }

    public void initView() {
        KLog.e("whb", mAdapter);
        // 全选按钮的回调接口
        mInvitationCx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInvitationCx1.isChecked()) {
                    mBuilder.setLength(0);
                    mBuilder1.setLength(0);
                    for (YaoqingBean.DataBean dataBean : mDataBeen) {
                        dataBean.setChoosed(true);
                    }
                    dataChanged(mDataBeen.size());
                } else {
                    for (YaoqingBean.DataBean dataBean : mDataBeen) {
                        dataBean.setChoosed(false);
                    }
                    count = 0;
                    mBuilder.setLength(0);
                    mBuilder1.setLength(0);
                    mAdapter.notifyDataSetChanged();
                    dataChanged(count);
                }
            }
        });
    }

    // 刷新listview和TextView的显示
    private void dataChanged(int count) {
        // 通知listView刷新
        mAdapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        mInvitationText.setText("邀请(" + count + ")");
    }

    public void onResume() {
        super.onResume();
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void checkChild(int childPosition, boolean isChecked) {
        int count1 = 0;
        for (int i = 0; i < mDataBeen.size(); i++) {
            if (mDataBeen.get(i).isChoosed()){
                count1++;
            }
        }
        mInvitationText.setText("邀请(" + count1 + ")");
        mAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
