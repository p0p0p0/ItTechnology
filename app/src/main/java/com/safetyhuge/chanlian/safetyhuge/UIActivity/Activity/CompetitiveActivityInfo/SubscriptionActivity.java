package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CompetitiveActivityInfo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.AreaBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProjectBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.DqEr;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.DqSan;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.DqYi;
import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 订金投标
 * 作者：王海宾 on 2017/4/13 0013 10:32
 * 邮箱：995696826@qq.com
 */

public class SubscriptionActivity extends HBaseAct {
    @Bind(R.id.issueproject_tv_dq_sheng2)
    TextView mIssueprojectTvDqSheng;
    @Bind(R.id.issueproject_dq_layout101)
    LinearLayout mIssueprojectDqLayout1;
    @Bind(R.id.issueproject_tv_dq_shi2)
    TextView mIssueprojectTvDqShi;
    @Bind(R.id.issueproject_dq_layout22)
    LinearLayout mIssueprojectDqLayout2;
    @Bind(R.id.issueproject_tv_dq_jiedao2)
    TextView mIssueprojectTvDqJiedao;
    @Bind(R.id.issueproject_dq_layout32)
    LinearLayout mIssueprojectDqLayout3;
    @Bind(R.id.issueproject_et_title2)
    EditText mIssueprojectEtTitle;
    @Bind(R.id.issueproject_et_money1)
    EditText mIssueprojectEtmoney;
    @Bind(R.id.issueproject_et_describe2)
    EditText mIssueprojectEtDescribe;
    //新增工作计划
    @Bind(R.id.issueproject_button12)
    Button mIssueprojectButton;
    @Bind(R.id.layout_sub1_money)
    EditText mLayoutSub1Money;
    @Bind(R.id.layout_sub1_text1)
    TextView mLayoutSub1Text1;
    @Bind(R.id.layout_sub1_layout1)
    LinearLayout mLayoutSub1Layout1;
    @Bind(R.id.layout_sub1_text2)
    TextView mLayoutSub1Text2;
    @Bind(R.id.layout_sub1_layout2)
    LinearLayout mLayoutSub1Layout2;
    @Bind(R.id.layout_sub1_et)
    EditText mLayoutSub1Et;
    @Bind(R.id.layout_sub1_button)
    Button mLayoutSub1Button;
    @Bind(R.id.layout_sub1)
    LinearLayout mLayoutSub1;
    @Bind(R.id.layout_sub2_money)
    EditText mLayoutSub2Money;
    @Bind(R.id.layout_sub2_text1)
    TextView mLayoutSub2Text1;
    @Bind(R.id.layout_sub2_layout1)
    LinearLayout mLayoutSub2Layout1;
    @Bind(R.id.layout_sub2_text2)
    TextView mLayoutSub2Text2;
    @Bind(R.id.layout_sub2_layout2)
    LinearLayout mLayoutSub2Layout2;
    @Bind(R.id.layout_sub2_et)
    EditText mLayoutSub2Et;
    @Bind(R.id.layout_sub2_button)
    Button mLayoutSub2Button;
    @Bind(R.id.layout_sub2)
    LinearLayout mLayoutSub2;
    @Bind(R.id.layout_sub3_money)
    EditText mLayoutSub3Money;
    @Bind(R.id.layout_sub3_text1)
    TextView mLayoutSub3Text1;
    @Bind(R.id.layout_sub3_layout1)
    LinearLayout mLayoutSub3Layout1;
    @Bind(R.id.layout_sub3_text2)
    TextView mLayoutSub3Text2;
    @Bind(R.id.layout_sub3_layout2)
    LinearLayout mLayoutSub3Layout2;
    @Bind(R.id.layout_sub3_et)
    EditText mLayoutSub3Et;
    @Bind(R.id.layout_sub3_button)
    Button mLayoutSub3Button;
    @Bind(R.id.layout_sub3)
    LinearLayout mLayoutSub3;
    @Bind(R.id.layout_sub4_money)
    EditText mLayoutSub4Money;
    @Bind(R.id.layout_sub4_text1)
    TextView mLayoutSub4Text1;
    @Bind(R.id.layout_sub4_layout1)
    LinearLayout mLayoutSub4Layout1;
    @Bind(R.id.layout_sub4_text2)
    TextView mLayoutSub4Text2;
    @Bind(R.id.layout_sub4_layout2)
    LinearLayout mLayoutSub4Layout2;
    @Bind(R.id.layout_sub4_et)
    EditText mLayoutSub4Et;
    @Bind(R.id.layout_sub4_button)
    Button mLayoutSub4Button;
    @Bind(R.id.layout_sub4)
    LinearLayout mLayoutSub4;
    @Bind(R.id.layout_sub5_money)
    EditText mLayoutSub5Money;
    @Bind(R.id.layout_sub5_text1)
    TextView mLayoutSub5Text1;
    @Bind(R.id.layout_sub5_layout1)
    LinearLayout mLayoutSub5Layout1;
    @Bind(R.id.layout_sub5_text2)
    TextView mLayoutSub5Text2;
    @Bind(R.id.layout_sub5_layout2)
    LinearLayout mLayoutSub5Layout2;
    @Bind(R.id.layout_sub5_et)
    EditText mLayoutSub5Et;
    @Bind(R.id.layout_sub5_button)
    Button mLayoutSub5Button;
    @Bind(R.id.layout_sub5)
    LinearLayout mLayoutSub5;
    private Context mContext;
    private KProgressHUD mKProgressHUD;
    private ArrayList<String> mArrayList, mArrayLists, mArrayListId, mArrayListsId;
    private String mId;
    private List<ProjectBean.DataBean.IndusBean> mIndus;
    private List<AreaBean.DataBean> mAndus;
    private List<ProjectBean.DataBean.RegionBean> mRndus;
    private String mId1;
    private ArrayList<String> mArrayList1;
    private ArrayList<String> mRegionIdyList, mRegionyNameList;
    private ArrayList<String> mAreaNameList, mAreaIdList;
    private boolean mB;
    private List<AreaBean.DataBean> mData;
    private ArrayList<String> mStringNames, mStringIds;
    private String mCaseI;
    private String mUserid;
    private String mUsername;
    private String mMoney;
    private String mTime;
    private String mTxt_prize1_cash;
    private String mTxt_prize1_num;
    private String mTxt_prize2_cash;
    private String mTxt_prize2_num;
    private String mTxt_prize3_cash;
    private String mTxt_prize3_num;
    private String mTask_cash_cove;
    private String mBudget_radio;
    private String mArr1;
    private String mArr2;
    private String mShengId;
    private String mShiId1;
    private String mJiedaoId;
    private String mTitle;
    private String mDescride;
    private String mFlag;
    private boolean mB1 = true;
    private boolean mB2 = true;
    private boolean mB3 = true;
    public static CommonActivity instance = null;
    private String mMUserid;
    private String mTask_id;
    private String mMoney1;
    private String mHidden;
    private int mCount = 0;
    private String mTime1;
    private String mMTask_id;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mUserid = intent.getStringExtra("userid");
        mMTask_id = intent.getStringExtra("task_id");
        mContext = SubscriptionActivity.this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        alentss(mMUserid, mMTask_id);
        alents();
        mLayoutSub2.setVisibility(View.GONE);
        mLayoutSub3.setVisibility(View.GONE);
        mLayoutSub4.setVisibility(View.GONE);
        mLayoutSub5.setVisibility(View.GONE);
    }

    @OnClick({R.id.issueproject_dq_layout101, R.id.issueproject_dq_layout22,
            R.id.issueproject_dq_layout32, R.id.issueproject_button12, R.id.issueproject_button2,
            R.id.layout_sub1_layout1, R.id.layout_sub1_layout2, R.id.layout_sub1_button,
            R.id.layout_sub2_layout1, R.id.layout_sub2_layout2, R.id.layout_sub2_button,
            R.id.layout_sub3_layout1, R.id.layout_sub3_layout2, R.id.layout_sub3_button,
            R.id.layout_sub4_layout1, R.id.layout_sub4_layout2, R.id.layout_sub4_button,
            R.id.layout_sub5_layout1, R.id.layout_sub5_layout2, R.id.layout_sub5_button
    })
    public void onClick(View view) {
        switch (view.getId()) {
            //选择地区 省
            case R.id.issueproject_dq_layout101:
                PopupUtils.initSelectPopup(mContext, mRegionyNameList, null, null, mRegionIdyList, null, null, null, mIssueprojectTvDqSheng, mIssueprojectDqLayout1).showAsDropDown(mIssueprojectDqLayout1, 0, 10);
                KLog.e("省级id", mShengId);
                if (mAreaNameList != null) {
                    mAreaNameList = null;
                }
                break;
            //市
            case R.id.issueproject_dq_layout22:
                if (!mIssueprojectTvDqSheng.getText().toString().isEmpty()) {
                    if (mAreaNameList != null) {
                        PopupUtils.initSelectPopup(mContext, mAreaNameList, null, null, null, mAreaIdList, null, null, mIssueprojectTvDqShi, mIssueprojectDqLayout2).showAsDropDown(mIssueprojectDqLayout1, 0, 10);
                        KLog.e("市级", mShiId1);
                    } else {
                        town(DqYi, "");
                    }
                } else {
                    Toast.makeText(mContext, "请选择省份", Toast.LENGTH_SHORT).show();
                }

                break;
            //街道
            case R.id.issueproject_dq_layout32:

                if (!mIssueprojectTvDqShi.getText().toString().isEmpty()) {
                    int id = getTData();
                    List<AreaBean.DataBean.CcdataBean> ccdataBeen = mData.get(id).getCcdata();
                    mStringNames = new ArrayList<>();
                    mStringIds = new ArrayList<>();
                    if (ccdataBeen != null) {
                        for (AreaBean.DataBean.CcdataBean ccdataBean : ccdataBeen) {
                            if (ccdataBean != null) {
                                mStringNames.add(ccdataBean.getCcname());
                                mStringIds.add(ccdataBean.getCcid());
                            }
                        }
                    }
                    PopupUtils.initSelectPopup(mContext, mStringNames, null, null, null, null, mStringIds, null, mIssueprojectTvDqJiedao, mIssueprojectDqLayout3).showAsDropDown(mIssueprojectDqLayout3, 0, 10);
                    KLog.e("街道", mJiedaoId);
                } else {
                    Toast.makeText(mContext, "请选择市区", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.issueproject_button12:
                if (mCount!=4){
                    AlertDialog alert1 = new AlertDialog.Builder(this).create();
                    alert1.setTitle("操作提示");
                    alert1.setMessage("新增工作计划");
                    alert1.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });
                    alert1.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mCount++;
                                    if (mCount == 1) {
                                        mLayoutSub2.setVisibility(View.VISIBLE);
                                    } else if (mCount == 2) {
                                        mLayoutSub3.setVisibility(View.VISIBLE);
                                    } else if (mCount == 3) {
                                        mLayoutSub4.setVisibility(View.VISIBLE);
                                    } else if (mCount == 4) {
                                        mLayoutSub5.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                    alert1.show();
                }else{
                    Toast.makeText(mContext, "最多只能添加5个计划", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.issueproject_button2:
                //金额
                mMoney1 = mIssueprojectEtmoney.getText().toString();
                //工作周期
                mTitle = mIssueprojectEtTitle.getText().toString();
                //描述
                mDescride = mIssueprojectEtDescribe.getText().toString();
                if (!mMoney1.isEmpty()) {
                    if (!mTitle.isEmpty()) {
                        if (!mIssueprojectTvDqSheng.getText().toString().isEmpty()) {
                            if (!mIssueprojectTvDqShi.getText().toString().isEmpty()) {
                                if (!mIssueprojectTvDqJiedao.getText().toString().isEmpty()) {
                                    if (!mDescride.isEmpty()) {
                                        //金额
                                        String money = mLayoutSub1Money.getText().toString();
                                        //开始时间
                                        String sub1Time1 = mLayoutSub1Text1.getText().toString();
                                        //结束时间
                                        String sub1Time2 = mLayoutSub1Text2.getText().toString();
                                        //工作目标
                                        String sub1et = mLayoutSub1Et.getText().toString();
                                        if (!money.isEmpty() && !sub1Time1.isEmpty() && !sub1Time2.isEmpty() && !sub1et.isEmpty()) {
                                            //2
                                            if (mLayoutSub2.getVisibility() == View.VISIBLE) {
                                                String money1 = mLayoutSub2Money.getText().toString();
                                                //开始时间
                                                String sub1Time11 = mLayoutSub2Text1.getText().toString();
                                                //结束时间
                                                String sub1Time22 = mLayoutSub2Text2.getText().toString();
                                                //工作目标
                                                String sub1et1 = mLayoutSub2Et.getText().toString();
                                                if (!money1.isEmpty() && !sub1Time11.isEmpty() && !sub1Time22.isEmpty() && !sub1et1.isEmpty()) {
                                                    //3
                                                    if (mLayoutSub3.getVisibility() == View.VISIBLE) {
                                                        String money111 = mLayoutSub3Money.getText().toString();
                                                        //开始时间
                                                        String sub1Time111 = mLayoutSub3Text1.getText().toString();
                                                        //结束时间
                                                        String sub1Time222 = mLayoutSub3Text2.getText().toString();
                                                        //工作目标
                                                        String sub1et11 = mLayoutSub3Et.getText().toString();
                                                        if (!money111.isEmpty() && !sub1Time111.isEmpty() && !sub1Time222.isEmpty() && !sub1et11.isEmpty()) {
                                                            if (mLayoutSub4.getVisibility() == View.VISIBLE) {
                                                                String money1111 = mLayoutSub4Money.getText().toString();
                                                                //开始时间
                                                                String sub1Time1111 = mLayoutSub4Text1.getText().toString();
                                                                //结束时间
                                                                String sub1Time2222 = mLayoutSub4Text2.getText().toString();
                                                                //工作目标
                                                                String sub1et111 = mLayoutSub4Et.getText().toString();
                                                                if (!money1111.isEmpty() && !sub1Time1111.isEmpty() && !sub1Time2222.isEmpty() && !sub1et111.isEmpty()) {
                                                                    if (mLayoutSub5.getVisibility() == View.VISIBLE) {
                                                                        String money11111 = mLayoutSub4Money.getText().toString();
                                                                        //开始时间
                                                                        String sub1Time11111 = mLayoutSub4Text1.getText().toString();
                                                                        //结束时间
                                                                        String sub1Time22222 = mLayoutSub4Text2.getText().toString();
                                                                        //工作目标
                                                                        String sub1et1111 = mLayoutSub4Et.getText().toString();
                                                                        if (!money11111.isEmpty() && !sub1Time11111.isEmpty() && !sub1Time22222.isEmpty() && !sub1et1111.isEmpty()) {
                                                                            KLog.e("mMoney1", mMoney1);
                                                                            String[] plan_amount = {money, money1, money111, money1111, money11111};
                                                                            String[] start_time = {sub1Time1, sub1Time11, sub1Time111, sub1Time1111, sub1Time11111};
                                                                            String[] end_time = {sub1Time2, sub1Time22, sub1Time222, sub1Time2222, sub1Time22222};
                                                                            String[] plan_title = {sub1et, sub1et1, sub1et11, sub1et111, sub1et1111};
                                                                            mKProgressHUD.show();
                                                                            danren(mUserid, mMTask_id, mDescride, "0", DqYi, DqEr, DqSan, mMoney1, mTitle,
                                                                                    plan_amount, start_time, end_time, plan_title
                                                                            );
                                                                            KLog.e("whb","请求网络5");
                                                                        }
                                                                    } else {
                                                                        String[] plan_amount = {money, money1, money111, money1111};
                                                                        String[] start_time = {sub1Time1, sub1Time11, sub1Time111, sub1Time1111};
                                                                        String[] end_time = {sub1Time2, sub1Time22, sub1Time222, sub1Time2222};
                                                                        String[] plan_title = {sub1et, sub1et1, sub1et11, sub1et111};
                                                                        KLog.e("mMoney1", mMoney1);
                                                                        mKProgressHUD.show();
                                                                        danren(mUserid, mMTask_id, mDescride, "0", DqYi, DqEr, DqSan, mMoney1, mTitle,
                                                                                plan_amount, start_time, end_time, plan_title
                                                                        );
                                                                        //danren();
                                                                        KLog.e("whb","请求网络4");
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请填写完整信息", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                            String[] plan_amount = {money, money1, money111};
                                                            String[] start_time = {sub1Time1, sub1Time11, sub1Time111};
                                                            String[] end_time = {sub1Time2, sub1Time22, sub1Time222};
                                                            String[] plan_title = {sub1et, sub1et1, sub1et11};
                                                            KLog.e("mMoney1", mMoney1);
                                                            mKProgressHUD.show();
                                                            danren(mUserid, mMTask_id, mDescride, "0", DqYi, DqEr, DqSan, mMoney1, mTitle,
                                                                    plan_amount, start_time, end_time, plan_title
                                                            );
                                                            // danren();
                                                            KLog.e("whb","请求网络3");
                                                        } else {
                                                            Toast.makeText(mContext, "请填写完整信息", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        String[] plan_amount = {money, money1};
                                                        String[] start_time = {sub1Time1, sub1Time11};
                                                        String[] end_time = {sub1Time2, sub1Time22};
                                                        String[] plan_title = {sub1et, sub1et1};
                                                        KLog.e("mMoney1", mMoney1);
                                                        mKProgressHUD.show();
                                                        danren(mUserid, mMTask_id, mDescride, "0", DqYi, DqEr, DqSan, mMoney1, mTitle,
                                                                plan_amount, start_time, end_time, plan_title
                                                        );
                                                        // danren();
                                                        KLog.e("whb","请求网络2");
                                                    }
                                                } else {
                                                    Toast.makeText(mContext, "请填写完整信息", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                String[] plan_amount = {money};
                                                String[] start_time = {sub1Time1};
                                                String[] end_time = {sub1Time2};
                                                String[] plan_title = {sub1et};
                                                KLog.e("mMoney1", mMoney1);
                                                mKProgressHUD.show();
                                                danren(mUserid, mMTask_id, mDescride, "0", DqYi, DqEr, DqSan, mMoney1, mTitle,
                                                        plan_amount, start_time, end_time, plan_title
                                                );
                                            }
                                        } else {
                                            Toast.makeText(mContext, "请填写完整信息", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(mContext, "描述不能为空", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(mContext, "请选择街道", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(mContext, "请选择市", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(mContext, "请选择省", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "天数不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "请填写金额", Toast.LENGTH_SHORT).show();
                }
                break;
            //工作计划1
            case R.id.layout_sub1_layout1:
                item(mLayoutSub1Text1, view);
                break;
            case R.id.layout_sub1_layout2:
                item(mLayoutSub1Text2, view);
                break;
            //工作计划2
            case R.id.layout_sub2_layout1:
                item(mLayoutSub2Text1, view);
                break;
            case R.id.layout_sub2_layout2:
                item(mLayoutSub2Text2, view);
                break;
            case R.id.layout_sub2_button:
                mCount = 0;
                mLayoutSub2.setVisibility(View.GONE);
                break;
            //工作计划3
            case R.id.layout_sub3_layout1:
                item(mLayoutSub3Text1, view);
                break;
            case R.id.layout_sub3_layout2:
                item(mLayoutSub3Text2, view);
                break;
            case R.id.layout_sub3_button:
                mCount = 1;
                mLayoutSub3.setVisibility(View.GONE);
                break;
            //工作计划4
            case R.id.layout_sub4_layout1:
                item(mLayoutSub4Text1, view);
                break;
            case R.id.layout_sub4_layout2:
                item(mLayoutSub4Text2, view);
                break;
            case R.id.layout_sub4_button:
                mCount = 2;
                mLayoutSub4.setVisibility(View.GONE);
                break;
            //工作计划5
            case R.id.layout_sub5_layout1:
                item(mLayoutSub5Text1, view);
                break;
            case R.id.layout_sub5_layout2:
                item(mLayoutSub5Text2, view);
                break;
            case R.id.layout_sub5_button:
                mCount = 3;
                mLayoutSub5.setVisibility(View.GONE);
                break;
        }
    }

    private void alents() {
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params("action", "GetTaskTextInfo").execute(new JsonCallback<ProjectBean>() {
            @Override
            public void onSuccess(ProjectBean projectBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                ProjectBean.DataBean data = projectBean.getData();
                mRndus = data.getRegion();
                mIndus = data.getIndus();
                List<ProjectBean.DataBean.RegionBean> regionBeen = projectBean.getData().getRegion();
                List<ProjectBean.DataBean.IndusBean> dataBeen = projectBean.getData().getIndus();
                mArrayList = new ArrayList<String>();
                mArrayListId = new ArrayList<String>();
                mArrayLists = new ArrayList<String>();
                mArrayListsId = new ArrayList<String>();
                mRegionyNameList = new ArrayList<String>();
                mRegionIdyList = new ArrayList<String>();
                for (ProjectBean.DataBean.RegionBean regionBean : regionBeen) {
                    mRegionyNameList.add(regionBean.getPname());
                    mRegionIdyList.add(regionBean.getPid());
                }
                for (int i = 0; i < dataBeen.size(); i++) {
                    mArrayList.add(dataBeen.get(i).getPname());
                    mArrayListId.add(dataBeen.get(i).getPid());
                    for (int j = i; j < dataBeen.get(i).getCdata().size(); j++) {
                        mArrayLists.add(dataBeen.get(i).getCdata().get(j).getCname());
                        mArrayListsId.add(dataBeen.get(i).getCdata().get(j).getCid());
                    }
                }

            }
        });
    }

    public void town(String pid, String cid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskRegionByPid");
        hashMap.put("pid", pid);
        hashMap.put("cid", cid);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<AreaBean>() {
            @Override
            public void onSuccess(AreaBean areaBean, Call call, Response response) {
                mData = areaBean.getData();
                mAreaNameList = new ArrayList<>();
                mAreaIdList = new ArrayList<>();
                for (AreaBean.DataBean dataBean : mData) {
                    mAreaNameList.add(dataBean.getCname());
                    mAreaIdList.add(dataBean.getCid());
                }
                PopupUtils.initSelectPopup(mContext, mAreaNameList, null, null, null, mAreaIdList, null, null, mIssueprojectTvDqShi, mIssueprojectDqLayout2).showAsDropDown(mIssueprojectDqLayout1, 0, 10);
                mShiId1 = (String) get(ECApplication.context, "MMMMidid", "");
                KLog.e("市级", mShiId1);
            }
        });
    }

    public void back_text_view(View view) {
        SharedPrefsUtil.remove(ECApplication.context, "MMMidid");
        SharedPrefsUtil.remove(ECApplication.context, "MmMMidid");
        SharedPrefsUtil.remove(ECApplication.context, "MMMMMidid");
        exitAct();
    }

    public int getTData() {
        String mId1 = (String) get(ECApplication.context, "MMMMidid", "");
        KLog.e("mid1", mId1);
        for (int i = 0; i < mData.size(); i++) {
            String pid = mData.get(i).getCid();
            KLog.e("pid", pid);
            if (mId1.equals(pid)) {
                return i;
            }
        }
        return -1;
    }


    public void danren(String uid, String task_id, String message,
                       String work_hidden, String province, String city, String area, String quote,
                       String cycle, String[] plan_amount, String[] start_time, String[] end_time, String[] plan_title
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
        //参数
        try {
            hashMap.put("action", "BidTask");
            //用户id
            hashMap.put("uid", uid);
            //项目id
            hashMap.put("task_id", task_id);
            hashMap.put("message", message);
            //是否隐藏稿件
            hashMap.put("work_hidden", work_hidden);
            hashMap.put("submit", "1");
            //省id
            hashMap.put("province", province);
            //市id
            hashMap.put("city", city);
            //街道id
            hashMap.put("area", area);
            //金额
            hashMap.put("quote", quote);
            //天数
            hashMap.put("cycle", cycle);
            //报价金额
            StringBuilder planamount = new StringBuilder();
            for (int i = 0; i < plan_amount.length; i++) {
                if (i == 0) {
                    planamount.append(plan_amount[i]);
                } else {
                    planamount.append("," + plan_amount[i]);
                }
            }
            KLog.e("planamount.toString()", planamount.toString());
            hashMap.put("plan_amount[]", planamount.toString());
            //计划开始时间
            StringBuilder starttime = new StringBuilder();
            for (int i = 0; i < start_time.length; i++) {
                if (i == 0) {
                    starttime.append(start_time[i]);
                } else {
                    starttime.append("," + start_time[i]);
                }
            }
            KLog.e("starttime.toString()", starttime.toString());
            hashMap.put("start_time[]", starttime.toString());
            //计划结束时间
            StringBuilder endtime = new StringBuilder();
            for (int i = 0; i < end_time.length; i++) {
                if (i == 0) {
                    endtime.append(end_time[i]);
                } else {
                    endtime.append("," + end_time[i]);
                }
            }
            KLog.e("endtime.toString()", endtime.toString());
            hashMap.put("end_time[]", endtime.toString());
            //计划目标
            StringBuilder plantitle = new StringBuilder();
            for (int i = 0; i < plan_title.length; i++) {
                if (i == 0) {
                    plantitle.append(plan_title[i]);
                } else {
                    plantitle.append("," + plan_title[i]);
                }
            }
            KLog.e("plantitle.toString()", plantitle.toString());
            hashMap.put("plan_title[]", plantitle.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        KLog.e("toString", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json("ssss", s);
                mKProgressHUD.dismiss();
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                String error = (String) map.get("error");
                if (code.equals("888")) {
                    Toasty.success(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                    exitAct();
                } else {
                    Toasty.error(mContext, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                Toasty.success(mContext, "网络请求失败,请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void alentss(String uid, String task_id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "BidTask");
        if (mMUserid != "") {
            hashMap.put("uid", uid);
        } else {

            hashMap.put("uid", "1");
        }
        hashMap.put("task_id", task_id);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mKProgressHUD.dismiss();
                Map<String, Object> map = getMapForJson(s);
                String msg = (String) map.get("code");
                if (msg.equals("888")) {
                }
            }
        });
    }

    public void intiView() {
    }

    public void item(final TextView textView, View view) {
        InputMethodManager imm = (InputMethodManager) ECApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(SubscriptionActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date1, View v) {//选中事件回调
                KLog.e("date", date1);
                // 转换为24小时制式的字串
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String mTime1 = df.format(date1);
                textView.setText(mTime1);
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setLabel("年", "月", "日", "", "", "")
                .setCancelText("取消")
                .setSubmitText("确认")
                .build();
        pvTime.show();
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
