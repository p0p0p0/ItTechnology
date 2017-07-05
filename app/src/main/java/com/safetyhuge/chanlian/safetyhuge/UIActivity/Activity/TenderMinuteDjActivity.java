package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.TenderminuteDjBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/7/3 0003 11:19
 * 邮箱：995696826@qq.com
 */

public class TenderMinuteDjActivity extends HBaseAct {


    @Bind(R.id.tm_img)
    CircleImageView mTmImg;
    @Bind(R.id.tm_name)
    TextView mTmName;
    @Bind(R.id.dj_stauts)
    TextView mDjStauts;
    @Bind(R.id.tm_layout)
    RelativeLayout mTmLayout;
    @Bind(R.id.zhaobiao_jinqian)
    TextView mZhaobiaoJinqian;
    @Bind(R.id.zhaobiao_day)
    TextView mZhaobiaoDay;
    @Bind(R.id.zhaobiao_diqu)
    TextView mZhaobiaoDiqu;
    @Bind(R.id.zhaobiao_neirong)
    TextView mZhaobiaoNeirong;
    @Bind(R.id.zhaobiao_time)
    TextView mZhaobiaoTime;
    @Bind(R.id.yincang_layout)
    LinearLayout mYincangLayout;
    @Bind(R.id.tendermin_start_time1)
    TextView mTenderminStartTime;
    @Bind(R.id.tendermin_finish_time1)
    TextView mTenderminFinishTime;
    @Bind(R.id.tendermin_money1)
    TextView mTenderminMoney;
    @Bind(R.id.tendermin_plan1)
    TextView mTenderminPlan;
    @Bind(R.id.tender_dj_wancheng1)
    TextView mTenderDjWancheng1;
    @Bind(R.id.tender_dj_button1)
    Button mTenderDjButton1;
    @Bind(R.id.lan_stage1)
    LinearLayout mLanStage1;
    @Bind(R.id.tender_dj_wancheng2)
    TextView mTenderDjWancheng2;
    @Bind(R.id.tender_dj_button2)
    Button mTenderDjButton2;
    @Bind(R.id.tendermin_start_time2)
    TextView mTenderminStartTime2;
    @Bind(R.id.tendermin_finish_time2)
    TextView mTenderminFinishTime2;
    @Bind(R.id.tendermin_money2)
    TextView mTenderminMoney2;
    @Bind(R.id.tendermin_plan2)
    TextView mTenderminPlan2;
    @Bind(R.id.lan_stage2)
    LinearLayout mLanStage2;
    @Bind(R.id.tender_dj_wancheng3)
    TextView mTenderDjWancheng3;
    @Bind(R.id.tender_dj_button3)
    Button mTenderDjButton3;
    @Bind(R.id.tendermin_start_time3)
    TextView mTenderminStartTime3;
    @Bind(R.id.tendermin_finish_time3)
    TextView mTenderminFinishTime3;
    @Bind(R.id.tendermin_money3)
    TextView mTenderminMoney3;
    @Bind(R.id.tendermin_plan3)
    TextView mTenderminPlan3;
    @Bind(R.id.lan_stage3)
    LinearLayout mLanStage3;
    @Bind(R.id.tender_dj_wancheng4)
    TextView mTenderDjWancheng4;
    @Bind(R.id.tender_dj_button4)
    Button mTenderDjButton4;
    @Bind(R.id.tendermin_start_time4)
    TextView mTenderminStartTime4;
    @Bind(R.id.tendermin_finish_time4)
    TextView mTenderminFinishTime4;
    @Bind(R.id.tendermin_money4)
    TextView mTenderminMoney4;
    @Bind(R.id.tendermin_plan4)
    TextView mTenderminPlan4;
    @Bind(R.id.lan_stage4)
    LinearLayout mLanStage4;
    private List<TenderminuteDjBean.DataBean.WorkInfoBean.PlanBean> mPlanlist;
    private String mMtask_id;
    private String mUserid;
    private String mMuid;
    AlertDialog alert;
    private int mSize;
    private List<String> mStrings;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_tenderminutedj);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        InitView();
    }

    private void InitView() {
        Intent intent = getIntent();
        //mtask_id
        mMtask_id = intent.getStringExtra("mtask_id");
        mMuid = intent.getStringExtra("muid");
        String mUpic = intent.getStringExtra("mUpic");
        String mUsername = intent.getStringExtra("mUsername");
        String mQuote = intent.getStringExtra("mQuote");
        String mCycle = intent.getStringExtra("mCycle");
        String mArea = intent.getStringExtra("mArea");
        String mMessage = intent.getStringExtra("mMessage");
        String mBid_time = intent.getStringExtra("mBid_time");
        mPlanlist = (List<TenderminuteDjBean.DataBean.WorkInfoBean.PlanBean>) intent.getSerializableExtra("planlist");
        updateState();
        Picasso.with(this).load(RequestAddress.IMAGE1 + mUpic).fit().into(mTmImg);
        mTmName.setText(mUsername);
        mZhaobiaoJinqian.setText("¥" + mQuote);
        mZhaobiaoDay.setText(mCycle + "天");
        mZhaobiaoDiqu.setText(mArea);
        mZhaobiaoNeirong.setText(mMessage);
    }

    private void updateState() {
        mStrings = new ArrayList<>();
        mSize = mPlanlist.size();
        KLog.e("sss", mSize);
        if (mSize > 0 && mPlanlist != null) {
            for (TenderminuteDjBean.DataBean.WorkInfoBean.PlanBean planBean : mPlanlist) {
                if (planBean.getPlan_status().equals("2")) {
                    mStrings.add("00");
                }
            }
            mDjStauts.setText("完成度" + mStrings.size() + "/" + mSize);
            switch (mSize) {
                case 1:
                    //plan_status;  // 计划状态，0待完成，1待确认，2已完成
                    String plan_status = mPlanlist.get(0).getPlan_status();
                    switch (Integer.valueOf(plan_status)) {
                        case 0:
                            mTenderDjButton1.setClickable(true);
                            mTenderDjWancheng1.setText("第1阶段(待完成)");
                            mTenderDjButton1.setVisibility(View.VISIBLE);
                            mTenderDjButton1.setText("工作完成");
                            break;
                        case 1:
                            mTenderDjWancheng1.setText("第1阶段(待确认)");
                            mTenderDjButton1.setVisibility(View.GONE);
                            break;
                        case 2:
                            mTenderDjWancheng1.setText("第1阶段(已完成)");
                            mTenderDjButton1.setVisibility(View.GONE);
                            break;
                    }
                    mLanStage1.setVisibility(View.VISIBLE);
                    mTenderminStartTime.setText("开始时间: " + DateUtils.timesTwo(mPlanlist.get(0).getStart_time()));
                    mTenderminFinishTime.setText("结束时间: " + DateUtils.timesTwo(mPlanlist.get(0).getEnd_time()));
                    mTenderminMoney.setText(" " + mPlanlist.get(0).getPlan_amount());
                    mTenderminPlan.setText("工作计划: " + mPlanlist.get(0).getPlan_title());
                    break;
                case 2:
                    //plan_status;  // 计划状态，0待完成，1待确认，2已完成
                    String plan_status1 = mPlanlist.get(1).getPlan_status();
                    switch (Integer.valueOf(plan_status1)) {
                        case 0:
                            mTenderDjWancheng1.setText("第2阶段(待完成)");
                            mTenderDjButton1.setVisibility(View.VISIBLE);
                            mTenderDjButton1.setText("工作完成");
                            break;
                        case 1:
                            mTenderDjWancheng1.setText("第2阶段(待确认)");
                            mTenderDjButton1.setVisibility(View.GONE);
                            break;
                        case 2:
                            mTenderDjWancheng1.setText("第2阶段(已完成)");
                            mTenderDjButton1.setVisibility(View.GONE);
                            break;
                    }
                    mLanStage1.setVisibility(View.VISIBLE);
                    mLanStage2.setVisibility(View.VISIBLE);
                    mTenderminStartTime.setText("开始时间: " + DateUtils.timesTwo(mPlanlist.get(1).getStart_time()));
                    mTenderminFinishTime.setText("结束时间: " + DateUtils.timesTwo(mPlanlist.get(1).getEnd_time()));
                    mTenderminMoney.setText(" " + mPlanlist.get(1).getPlan_amount());
                    mTenderminPlan.setText("工作计划: " + mPlanlist.get(1).getPlan_title());
                    break;
                case 3:
                    //plan_status;  // 计划状态，0待完成，1待确认，2已完成
                    String plan_status2 = mPlanlist.get(2).getPlan_status();
                    switch (Integer.valueOf(plan_status2)) {
                        case 0:
                            mTenderDjWancheng1.setText("第3阶段(待完成)");
                            mTenderDjButton1.setVisibility(View.VISIBLE);
                            mTenderDjButton1.setText("工作完成");
                            break;
                        case 1:
                            mTenderDjWancheng1.setText("第3阶段(待确认)");
                            mTenderDjButton1.setVisibility(View.GONE);
                            break;
                        case 2:
                            mTenderDjWancheng1.setText("第3阶段(已完成)");
                            mTenderDjButton1.setVisibility(View.GONE);
                            break;
                    }
                    mLanStage1.setVisibility(View.VISIBLE);
                    mLanStage2.setVisibility(View.VISIBLE);
                    mLanStage3.setVisibility(View.VISIBLE);
                    mLanStage2.setVisibility(View.VISIBLE);
                    mTenderminStartTime.setText("开始时间: " + DateUtils.timesTwo(mPlanlist.get(2).getStart_time()));
                    mTenderminFinishTime.setText("结束时间: " + DateUtils.timesTwo(mPlanlist.get(2).getEnd_time()));
                    mTenderminMoney.setText(" " + mPlanlist.get(2).getPlan_amount());
                    mTenderminPlan.setText("工作计划: " + mPlanlist.get(2).getPlan_title());
                    break;
                case 4:
                    //plan_status;  // 计划状态，0待完成，1待确认，2已完成
                    String plan_status3 = mPlanlist.get(3).getPlan_status();
                    switch (Integer.valueOf(plan_status3)) {
                        case 0:
                            mTenderDjWancheng1.setText("第3阶段(待完成)");
                            mTenderDjButton1.setVisibility(View.VISIBLE);
                            mTenderDjButton1.setText("工作完成");
                            break;
                        case 1:
                            mTenderDjWancheng1.setText("第3阶段(待确认)");
                            mTenderDjButton1.setVisibility(View.GONE);
                            break;
                        case 2:
                            mTenderDjWancheng1.setText("第3阶段(已完成)");
                            mTenderDjButton1.setVisibility(View.GONE);
                            break;
                    }
                    mLanStage1.setVisibility(View.VISIBLE);
                    mLanStage2.setVisibility(View.VISIBLE);
                    mLanStage3.setVisibility(View.VISIBLE);
                    mLanStage4.setVisibility(View.VISIBLE);
                    mTenderminStartTime.setText("开始时间: " + DateUtils.timesTwo(mPlanlist.get(3).getStart_time()));
                    mTenderminFinishTime.setText("结束时间: " + DateUtils.timesTwo(mPlanlist.get(3).getEnd_time()));
                    mTenderminMoney.setText(" " + mPlanlist.get(3).getPlan_amount());
                    mTenderminPlan.setText("工作计划: " + mPlanlist.get(3).getPlan_title());
                    break;
            }
        }
    }

    private void complete(final int flag, String id, String userid, String mtaskId, String mtask_id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "PlanComplete");
        hashMap.put("task_id", id);
        hashMap.put("uid", userid);
        if (mMuid.equals(mUserid)) {
            hashMap.put("is_user", 1 + "");
        } else {
            hashMap.put("is_user", 2 + "");
        }
        hashMap.put("plan_step", mtaskId);
        hashMap.put("plan_id", mtask_id);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                HBaseAct.dismissProgressHUD();
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                if (code.equals("888")) {
                    if (flag == 1) {
                        if (mMuid.equals(mUserid)) {
                            mPlanlist.get(0).setPlan_status("2");
                            updateState();
                        } else {
                            mPlanlist.get(0).setPlan_status("1");
                            updateState();
                        }
                    }
                    if (flag == 2) {
                        if (mMuid.equals(mUserid)) {
                            mPlanlist.get(1).setPlan_status("2");
                            updateState();
                        } else {
                            mPlanlist.get(1).setPlan_status("1");
                            updateState();
                        }
                    }
                    if (flag == 3) {
                        if (mMuid.equals(mUserid)) {
                            mPlanlist.get(2).setPlan_status("2");
                            updateState();
                        } else {
                            mPlanlist.get(2).setPlan_status("1");
                            updateState();
                        }
                    }
                    if (flag == 4) {
                        if (mMuid.equals(mUserid)) {
                            mPlanlist.get(3).setPlan_status("2");
                            updateState();
                        } else {
                            mPlanlist.get(3).setPlan_status("1");
                            updateState();
                        }
                    }
                }
                KLog.json(s);
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick({R.id.tender_dj_button1, R.id.tender_dj_button2, R.id.tender_dj_button3, R.id.tender_dj_button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tender_dj_button1:
                final String plan_step = mPlanlist.get(0).getPlan_step();
                final String plan_id = mPlanlist.get(0).getPlan_id();
                alert = new AlertDialog.Builder(TenderMinuteDjActivity.this).create();
                alert.setTitle("提示");
                alert.setMessage("您确定要完成工作吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                        HBaseAct.showKProgressHUD("加载中.....", TenderMinuteDjActivity.this);
                        complete(1, mMtask_id, mUserid, plan_step, plan_id);
                    }
                });
                alert.show();
                break;
            case R.id.tender_dj_button2:
                final String plan_step1 = mPlanlist.get(1).getPlan_step();
                final String plan_id1 = mPlanlist.get(1).getPlan_id();
                alert = new AlertDialog.Builder(TenderMinuteDjActivity.this).create();
                alert.setTitle("提示");
                alert.setMessage("您确定要完成工作吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                        HBaseAct.showKProgressHUD("加载中.....", TenderMinuteDjActivity.this);
                        complete(2, mMtask_id, mUserid, plan_step1, plan_id1);
                    }
                });
                alert.show();
                break;
            case R.id.tender_dj_button3:
                final String plan_step2 = mPlanlist.get(2).getPlan_step();
                final String plan_id2 = mPlanlist.get(2).getPlan_id();
                alert = new AlertDialog.Builder(TenderMinuteDjActivity.this).create();
                alert.setTitle("提示");
                alert.setMessage("您确定要完成工作吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                        HBaseAct.showKProgressHUD("加载中.....", TenderMinuteDjActivity.this);
                        complete(3, mMtask_id, mUserid, plan_step2, plan_id2);
                    }
                });
                alert.show();
                break;
            case R.id.tender_dj_button4:
                final String plan_step3 = mPlanlist.get(3).getPlan_step();
                final String plan_id3 = mPlanlist.get(3).getPlan_id();
                alert = new AlertDialog.Builder(TenderMinuteDjActivity.this).create();
                alert.setTitle("提示");
                alert.setMessage("您确定要完成工作吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                        HBaseAct.showKProgressHUD("加载中.....", TenderMinuteDjActivity.this);
                        complete(4, mMtask_id, mUserid, plan_step3, plan_id3);
                    }
                });
                alert.show();
                break;
        }

    }
}
