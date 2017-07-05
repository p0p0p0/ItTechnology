package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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
import com.zhl.cbdialog.CBDialogBuilder;

import java.io.File;
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
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.XmEr;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.XmYi;
import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/4/5 0005 14:45
 * 邮箱：995696826@qq.com
 */

public class IssueProjectActivity extends HBaseAct {
    Context mContext;
    KProgressHUD mKProgressHUD;
    @Bind(R.id.issueproject_tv_cp_yiji)
    TextView mIssueprojectTvCpYiji;
    @Bind(R.id.issueproject_cp_one_layout)
    LinearLayout mIssueprojectCpOneLayout;
    @Bind(R.id.issueproject_tv_cp_erji)
    TextView mIssueprojectTvCpErji;
    @Bind(R.id.issueproject_cp_two_layout)
    LinearLayout mIssueprojectCpTwoLayout;
    @Bind(R.id.issueproject_tv_dq_sheng)
    TextView mIssueprojectTvDqSheng;
    @Bind(R.id.issueproject_dq_layout1)
    LinearLayout mIssueprojectDqLayout1;
    @Bind(R.id.issueproject_tv_dq_shi)
    TextView mIssueprojectTvDqShi;
    @Bind(R.id.issueproject_dq_layout2)
    LinearLayout mIssueprojectDqLayout2;
    @Bind(R.id.issueproject_tv_dq_jiedao)
    TextView mIssueprojectTvDqJiedao;
    @Bind(R.id.issueproject_dq_layout3)
    LinearLayout mIssueprojectDqLayout3;
    @Bind(R.id.issueproject_et_title)
    EditText mIssueprojectEtTitle;
    @Bind(R.id.issueproject_et_tlephone)
    EditText mIssueprojectEtTlephone;
    @Bind(R.id.issueproject_butto_update)
    Button mIssueprojectButtoUpdate;
    @Bind(R.id.issueproject_button)
    Button mIssueprojectButton;
    @Bind(R.id.issueproject_et_describe)
    EditText mIssueprojectEtDescribe;
    @Bind(R.id.file_size)
    TextView mFileSize;
    @Bind(R.id.file_layout)
    LinearLayout mFileLayout;
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
    private int mFlag;
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
    private String mPhone;
    private boolean mB1 = true;
    private boolean mB2 = true;
    private boolean mB3 = true;
    private String mFujian;
    public static IssueProjectActivity instance = null;
    private List<String> mStringList;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issueproject);
        mStringList = new ArrayList<>();
        Intent intent = getIntent();
        //预算
        mMoney = intent.getStringExtra("yusuan");
        KLog.e("money", mMoney);
        //时间
        mTime = intent.getStringExtra("time");
        KLog.e("time", mTime);
        //一等奖 赏金
        mTxt_prize1_cash = intent.getStringExtra("txt_prize1_cash");
        //一等奖 名次
        mTxt_prize1_num = intent.getStringExtra("txt_prize1_num");
        KLog.e("txt_prize1_cash", mTxt_prize1_cash + "-----" + mTxt_prize1_num);

        //二等奖 赏金
        mTxt_prize2_cash = intent.getStringExtra("txt_prize2_cash");
        //二等奖 名次
        mTxt_prize2_num = intent.getStringExtra("txt_prize2_num");
        KLog.e("txt_prize2_cash", mTxt_prize2_cash + "-----" + mTxt_prize2_num);

        //三等奖 赏金
        mTxt_prize3_cash = intent.getStringExtra("txt_prize3_cash");
        //三等奖 名次
        mTxt_prize3_num = intent.getStringExtra("txt_prize3_num");
        KLog.e("txt_prize3_cash", mTxt_prize3_cash + "-----" + mTxt_prize3_num);

        //预算区间id
        mTask_cash_cove = intent.getStringExtra("task_cash_cove");
        KLog.e("task_cash_cove", mTask_cash_cove);
        //有无明确预算
        mBudget_radio = intent.getStringExtra("budget_radio");
        KLog.e("budget_radio", mBudget_radio);
        //下一步
        mCaseI = intent.getStringExtra("action");
        KLog.e("caseI", mCaseI);

        //下一步
        mFlag = intent.getIntExtra("flag", 0);
        KLog.e("flag", mFlag + "");
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        //用户名称
        mUsername = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");

        mContext = IssueProjectActivity.this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        alents();
        ButterKnife.bind(this);
        instance = this;
    }


    @OnClick({R.id.issueproject_cp_one_layout, R.id.issueproject_cp_two_layout, R.id.issueproject_dq_layout1, R.id.issueproject_dq_layout2, R.id.issueproject_dq_layout3, R.id.issueproject_butto_update, R.id.issueproject_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.issueproject_cp_one_layout:
                PopupUtils.initSelectPopup(mContext, mArrayList, mArrayListId, null, null, null, null, null, mIssueprojectTvCpYiji, mIssueprojectCpOneLayout).showAsDropDown(mIssueprojectCpOneLayout, 0, 10);
             //   mArr1 = (String) get(ECApplication.context, "Midid", "");
                KLog.e("项目一级", mArr1);
                break;
            //这个是二级选项
            case R.id.issueproject_cp_two_layout:
                if (!mIssueprojectTvCpYiji.getText().toString().isEmpty()) {
                    int oneIndex = getTwoData();// get two data
                    KLog.e("oneIndex", oneIndex);
                    List<ProjectBean.DataBean.IndusBean.CdataBean> cdata = mIndus.get(oneIndex).getCdata();
                    mArrayList1 = new ArrayList<>();
                    for (ProjectBean.DataBean.IndusBean.CdataBean cdataBean : cdata) {
                        mArrayList1.add(cdataBean.getCname());
                    }
                    PopupUtils.initSelectPopup(mContext, mArrayList1, null, mArrayListsId, null, null, null, null, mIssueprojectTvCpErji, mIssueprojectCpTwoLayout).showAsDropDown(mIssueprojectCpOneLayout, 0, 10);
                  //  mArr2 = (String) get(ECApplication.context, "MMidid", "");
                    KLog.e("项目二级", mArr2);
                } else {
                    Toast.makeText(mContext, "请选择第一项", Toast.LENGTH_SHORT).show();
                }
                break;
            //选择地区 省
            case R.id.issueproject_dq_layout1:
                PopupUtils.initSelectPopup(mContext, mRegionyNameList, null, null, mRegionIdyList, null, null, null, mIssueprojectTvDqSheng, mIssueprojectDqLayout1).showAsDropDown(mIssueprojectCpOneLayout, 0, 10);
              //  mShengId = (String) get(ECApplication.context, "MMMidid", "");
                KLog.e("省级id", mShengId);
                if (mAreaNameList != null) {
                    mAreaNameList = null;
                }

                break;
            //市
            case R.id.issueproject_dq_layout2:
                if (!mIssueprojectTvDqSheng.getText().toString().isEmpty()) {
                    String shengId1 = (String) get(ECApplication.context, "MMMidid", "");
                    if (mAreaNameList != null) {
                        PopupUtils.initSelectPopup(mContext, mAreaNameList, null, null, null, mAreaIdList, null, null, mIssueprojectTvDqShi, mIssueprojectDqLayout2).showAsDropDown(mIssueprojectCpOneLayout, 0, 10);
                      //  mShiId1 = (String) get(ECApplication.context, "MMMMidid", "");
                        KLog.e("市级", mShiId1);
                    } else {
                        town(shengId1, "");
                    }
                } else {
                    Toast.makeText(mContext, "请选择省份", Toast.LENGTH_SHORT).show();
                }

                break;
            //街道
            case R.id.issueproject_dq_layout3:

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
             //       mJiedaoId = (String) get(ECApplication.context, "MMMMMidid", "");
                    KLog.e("街道", mJiedaoId);
                } else {
                    Toast.makeText(mContext, "请选择市区", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.issueproject_butto_update:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
                break;
            case R.id.issueproject_button:
                mFujian = "";
                mTitle = mIssueprojectEtTitle.getText().toString();
                mDescride = mIssueprojectEtDescribe.getText().toString();
                mPhone = mIssueprojectEtTlephone.getText().toString();
                if (!mIssueprojectTvCpYiji.getText().toString().isEmpty()) {
                    if (!mIssueprojectTvCpErji.getText().toString().isEmpty()) {
                        if (!mIssueprojectTvDqSheng.getText().toString().isEmpty()) {
                            if (!mIssueprojectTvDqShi.getText().toString().isEmpty()) {
                                if (!mIssueprojectTvDqJiedao.getText().toString().isEmpty()) {
                                    if (!mTitle.isEmpty()) {
                                        if (!mDescride.isEmpty()) {
                                            if (!mPhone.isEmpty()) {
                                                if (!mStringList.isEmpty()) {
                                                    mKProgressHUD.show();
                                                    MethodInfo(mFlag);
                                                } else {
                                                    showNormalDialog();
                                                }
                                            } else {
                                                Toast.makeText(mContext, "请填写手机号", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(mContext, "描述不能为空", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(mContext, "标题不能为空", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(mContext, "请选择二级分类", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "请选择分类", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showNormalDialog() {
        new CBDialogBuilder(this)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle("提示")
                .setMessage("您确定不上传附件吗?")
                .setConfirmButtonText("确认")
                .setCancelButtonText("取消").setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                switch (whichBtn) {
                    case BUTTON_CONFIRM:
                        mKProgressHUD.show();
                        MethodInfo(mFlag);
                        break;
                    case BUTTON_CANCEL:
                        break;
                    default:
                        break;
                }
            }
        })
                .setDialogAnimation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                .create()
                .show();
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
                PopupUtils.initSelectPopup(mContext, mAreaNameList, null, null, null, mAreaIdList, null, null, mIssueprojectTvDqShi, mIssueprojectDqLayout2).showAsDropDown(mIssueprojectCpOneLayout, 0, 10);
                mShiId1 = (String) get(ECApplication.context, "MMMMidid", "");
                KLog.e("市级", mShiId1);
            }
        });
    }

    public void back_text_view(View view) {
        XmYi="";
        XmEr="";
        DqYi="";
        DqEr="";
        DqSan="";
        exitAct();
    }

    public int getTwoData() {
        mId1 = (String) get(ECApplication.context, "Midid", "");
        for (int i = 0; i < mIndus.size(); i++) {
            String pid = mIndus.get(i).getPid();
            if (mId1.equals(pid)) {
                return i;
            }
        }
        return -1;
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

    public void MethodInfo(int flag) {
        KLog.e("mArr1",XmYi);
        KLog.e("mArr2",XmEr);
        KLog.e("mShengId",DqYi);
        KLog.e("mShiId1",DqEr);
        KLog.e("mJiedaoId",DqSan);
        switch (flag) {
            case 1:
                //单人
                danren(mCaseI,
                        mUserid,
                        mUsername,
                        mMoney,
                        mTime,
                        XmYi,
                        XmEr,
                        DqYi,
                        DqEr,
                        DqSan,
                        mTitle,
                        mDescride,
                        mPhone,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                );
                break;
            case 2:
                //多人
                danren(mCaseI,
                        mUserid,
                        mUsername,
                        mMoney,
                        mTime,
                        XmYi,
                        XmEr,
                        DqYi,
                        DqEr,
                        DqSan,
                        mTitle,
                        mDescride,
                        mPhone,
                        mTxt_prize1_num,
                        mTxt_prize1_cash,
                        mTxt_prize2_num,
                        mTxt_prize2_cash,
                        mTxt_prize3_num,
                        mTxt_prize3_cash,
                        "",
                        "",
                        ""
                );
                break;
            case 3:
                //普通
                danren(mCaseI,
                        mUserid,
                        mUsername,
                        mMoney,
                        mTime,
                        XmYi,
                        XmEr,
                        DqYi,
                        DqEr,
                        DqSan,
                        mTitle,
                        mDescride,
                        mPhone,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        mTask_cash_cove,
                        mBudget_radio,
                        mMoney
                );
                break;
            case 4:
              //订金
                danren(mCaseI,
                        mUserid,
                        mUsername,
                        mMoney,
                        mTime,
                        XmYi,
                        XmEr,
                        DqYi,
                        DqEr,
                        DqSan,
                        mTitle,
                        mDescride,
                        mPhone,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        mTask_cash_cove,
                        mBudget_radio,
                        mMoney
                );
                break;
        }
    }

    public void danren(String action,
                       String uid,
                       String username,
                       String cash,
                       String day,
                       String pid,
                       String dis,
                       String shengid,
                       String shiid,
                       String jiedaoid,
                       String title,
                       String content,
                       String mobile,
                       String num1,
                       String cash1,
                       String num2,
                       String cash2,
                       String num3,
                       String cash3,
                       String cove,
                       String radio,
                       String cash11

    ) {
        ArrayList<File> files = new ArrayList<>();
        for (String s : mStringList) {
            files.add(new File(s));
            KLog.e("whb",s);
        }
        HashMap<String, String> hashMap = new HashMap<>();
        //参数
        hashMap.put("action", action);
        KLog.e("action", action);
        //用户id
        hashMap.put("uid", uid);
        KLog.e("uid", uid);
        //用户名称
        hashMap.put("username", username);
        KLog.e("username", username);
        //预算
        hashMap.put("txt_task_cash", cash);
        KLog.e("txt_task_cash", cash);
        //日期
        hashMap.put("txt_task_day", day);
        KLog.e("txt_task_day", day);
        //大分类id
        hashMap.put("indus_pid", pid);
        KLog.e("indus_pid", pid);
        //小分类id
        hashMap.put("indus_id", dis);
        KLog.e("indus_id", dis);
        //省id
        hashMap.put("province", shengid);
        KLog.e("province", shengid);
        //市id
        hashMap.put("city", shiid);
        KLog.e("city", shiid);
        //街道id
        hashMap.put("area", jiedaoid);
        KLog.e("area", jiedaoid);
        //标题
        hashMap.put("txt_title", title);
        KLog.e("txt_title", title);
        //内容
        hashMap.put("tar_content", content);
        KLog.e("tar_content", content);
        //手机号
        hashMap.put("txt_mobile", mobile);
        KLog.e("txt_mobile", mobile);

        //一等奖名次
        hashMap.put("txt_prize1_num", num1);
        KLog.e("txt_prize1_num", num1);
        //一等奖赏金
        hashMap.put("txt_prize1_cash", cash1);
        KLog.e("txt_prize1_cash", cash1);
        //二等奖名次
        hashMap.put("txt_prize2_num", num2);
        KLog.e("txt_prize2_num", num2);
        //二等奖赏金
        hashMap.put("txt_prize2_cash", cash2);
        KLog.e("txt_prize2_cash", cash2);
        //三等奖名次
        hashMap.put("txt_prize3_num", num3);
        KLog.e("txt_prize3_num", num3);
        //三等奖赏金
        hashMap.put("txt_prize3_cash", cash3);
        KLog.e("txt_prize3_cash", cash3);
        //区间id
        hashMap.put("task_cash_cove", cove);
        KLog.e("task_cash_cove", cove);
        //有无预算
        hashMap.put("budget_radio", radio);
        KLog.e("budget_radio", radio);
        //预算
        hashMap.put("txt_budget", cash11);
        KLog.e("idid", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).addFileParams("file", files).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mKProgressHUD.dismiss();
                KLog.json("ssss", s);
                final Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                final String balance = (String) map.get("balance");
                final Integer idid = (Integer) map.get("taskid");
                //cash
                if (code.equals("888")) {
                    new CBDialogBuilder(IssueProjectActivity.this)
                            .setTouchOutSideCancelable(true)
                            .showCancelButton(true).setCustomIcon(R.drawable.duihao)
                            .setTitle("发布成功")
                            .setMessage("是否前往支付?")
                            .setConfirmButtonText("确定")
                            .setCancelButtonText("取消").setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                        @Override
                        public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                            switch (whichBtn) {
                                case BUTTON_CONFIRM:
                                    //开启支付页面
                                    Intent intent = new Intent(IssueProjectActivity.this, PayActivity.class);
                                  /*  intent.putExtra("money", mMoney);
                                    intent.putExtra("mTitle", mTitle);
                                    intent.putExtra("moneys", balance);
                                    intent.putExtra("mid", idid + "");
                                    intent.putExtra("mflag", "-1");*/
                                  if (mFlag==3||mFlag==4){
                                      final int cash4 = (int) map.get("cash");
                                      intent.putExtra("money", cash4+"");
                                      intent.putExtra("mTitle", mTitle);
                                      intent.putExtra("mid", idid+"");
                                      intent.putExtra("mflag", "-1");
                                  }
                                  if (!mMoney.equals("")){
                                      intent.putExtra("money", mMoney);
                                      intent.putExtra("mTitle", mTitle);
                                      intent.putExtra("mid", idid+"");
                                      intent.putExtra("mflag", "-1");
                                  }
                                    startActivity(intent);
                                    break;
                                case BUTTON_CANCEL:
                                    IssueProjectActivity.instance.finish();
                                    ReleaseProjectActivity.instance.finish();
                                    break;
                                default:
                                    break;
                            }
                        }
                    })
                            .setDialogAnimation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                            .create()
                            .show();
                } else {
                    Toasty.error(ECApplication.context, "发布失败", 0, true).show();
                }
            }
        });
    }

    public void onResume() {
        super.onResume();
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        KLog.e("onResume");
        MobclickAgent.onResume(this);
        if (!mStringList.isEmpty()){
            mFileLayout.setVisibility(View.VISIBLE);
            mFileSize.setText("已添加"+mStringList.size()+"附件");
        }
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        KLog.e("onActivityResult");
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                mStringList.add(getRealFilePathFromUri(this, uri));
            }
        }
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        SharedPrefsUtil.remove(ECApplication.context, "Midid");
        SharedPrefsUtil.remove(ECApplication.context, "MMidid");
        SharedPrefsUtil.remove(ECApplication.context, "MMMidid");
        SharedPrefsUtil.remove(ECApplication.context, "MmMMidid");
        SharedPrefsUtil.remove(ECApplication.context, "MMMMMidid");
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
        KLog.e("zoule");
    }
}
