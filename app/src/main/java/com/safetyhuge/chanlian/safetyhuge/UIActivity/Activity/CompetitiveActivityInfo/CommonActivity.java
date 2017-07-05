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

import java.util.ArrayList;
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
 * 普通投标
 * 作者：王海宾 on 2017/4/13 0013 10:32
 * 邮箱：995696826@qq.com
 */

public class CommonActivity extends HBaseAct {
    Context mContext;
    KProgressHUD mKProgressHUD;
    @Bind(R.id.issueproject_tv_dq_sheng1)
    TextView mIssueprojectTvDqSheng;
    @Bind(R.id.issueproject_dq_layout11)
    LinearLayout mIssueprojectDqLayout1;
    @Bind(R.id.issueproject_tv_dq_shi1)
    TextView mIssueprojectTvDqShi;
    @Bind(R.id.issueproject_dq_layout21)
    LinearLayout mIssueprojectDqLayout2;
    @Bind(R.id.issueproject_tv_dq_jiedao1)
    TextView mIssueprojectTvDqJiedao;
    @Bind(R.id.issueproject_dq_layout31)
    LinearLayout mIssueprojectDqLayout3;
    @Bind(R.id.issueproject_et_title1)
    EditText mIssueprojectEtTitle;
    @Bind(R.id.issueproject_et_money)
    EditText mIssueprojectEtmoney;
    @Bind(R.id.issueproject_et_describe1)
    EditText mIssueprojectEtDescribe;
    @Bind(R.id.issueproject_button1)
    Button mIssueprojectButton;
    @Bind(R.id.hidden_layout1)
    LinearLayout mHiddenLayout;
    @Bind(R.id.comperttove_cx11)
    CheckBox mComperttoveCx1;
    @Bind(R.id.comperttove_cx22)
    CheckBox mComperttoveCx2;
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


    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mMUserid = intent.getStringExtra("userid");
        mTask_id = intent.getStringExtra("task_id");
        mContext = CommonActivity.this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        alentss(mMUserid, mTask_id);
        alents();
        intiView();

        instance = this;
    }


    @OnClick({R.id.issueproject_dq_layout11, R.id.issueproject_dq_layout21, R.id.issueproject_dq_layout31, R.id.issueproject_butto_update, R.id.issueproject_button1})
    public void onClick(View view) {
        switch (view.getId()) {
            //选择地区 省
            case R.id.issueproject_dq_layout11:
                PopupUtils.initSelectPopup(mContext, mRegionyNameList, null, null, mRegionIdyList, null, null, null, mIssueprojectTvDqSheng, mIssueprojectDqLayout1).showAsDropDown(mIssueprojectDqLayout1, 0, 10);
                KLog.e("省级id", mShengId);
                if (mAreaNameList != null) {
                    mAreaNameList = null;
                }

                break;
            //市
            case R.id.issueproject_dq_layout21:
                if (!mIssueprojectTvDqSheng.getText().toString().isEmpty()) {
                    if (mAreaNameList != null) {
                        PopupUtils.initSelectPopup(mContext, mAreaNameList, null, null, null, mAreaIdList, null, null, mIssueprojectTvDqShi, mIssueprojectDqLayout2).showAsDropDown(mIssueprojectDqLayout1, 0, 10);
                    } else {
                        town(DqYi, "");
                    }
                } else {
                    Toast.makeText(mContext, "请选择省份", Toast.LENGTH_SHORT).show();
                }

                break;
            //街道
            case R.id.issueproject_dq_layout31:
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
            case R.id.issueproject_butto_update:
                break;
            case R.id.issueproject_button1:
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
                                        mKProgressHUD.show();
                                        danren(mMUserid, mTask_id, mDescride, DqYi, DqEr, DqSan, mMoney1, mTitle, mFlag);
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


    public void danren(String uid, String task_id, String tar_content,
                       String shengid, String shiid, String jiedaoid, String price, String cycle, String work_hidden) {
        HashMap<String, String> hashMap = new HashMap<>();
        //参数
        hashMap.put("action", "BidTask");
        //用户id
        hashMap.put("uid", uid);
        KLog.e("uid", uid);
        //项目id
        hashMap.put("task_id", task_id);
        //是否隐藏稿件
        hashMap.put("tar_content", tar_content);
        hashMap.put("submit", "1");
        //省id
        hashMap.put("province", shengid);
        KLog.e("province", shengid);
        //市id
        hashMap.put("city", shiid);
        KLog.e("city", shiid);
        //街道id
        hashMap.put("area", jiedaoid);
        KLog.e("area", jiedaoid);
        //金额
        hashMap.put("price", price);
        //天数
        hashMap.put("cycle", cycle);
        //是否隐藏
        hashMap.put("work_hidden", work_hidden);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json("ssss", s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                String error = (String) map.get("error");
                //error
                if (code.equals("888")) {
                    Toasty.success(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                    exitAct();
                } else {
                    Toasty.error(mContext, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                mKProgressHUD.dismiss();
                Map<String, Object> map = getMapForJson(s);
                String msg = (String) map.get("code");
                if (msg.equals("888")) {
                    mHidden = (String) map.get("work_hidden");
                    KLog.e("mHidden", mHidden);
                    if (mHidden.equals("1")) {
                        mHiddenLayout.setVisibility(View.VISIBLE);
                    } else {
                        mHiddenLayout.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    public void intiView() {
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
        DqYi = "";
        DqEr = "";
        DqSan = "";
    }
}
