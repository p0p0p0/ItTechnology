package com.safetyhuge.chanlian.safetyhuge.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.AreaBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.HomeInfoBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProjectBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.BottomPopupOption;
import com.safetyhuge.chanlian.safetyhuge.utils.ClipImageActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.MypopupAdapter;
import com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.zhl.cbdialog.CBDialogBuilder;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import static android.R.attr.path;
import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.DqEr;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.DqSan;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.DqYi;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.XmEr;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.XmYi;
import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 发布项目
 * 作者：王海宾 on 2017/4/26 0026 20:17
 * 邮箱：995696826@qq.com
 */
public class FaBuCpActivity extends HBaseAct {
    Context mContext;
    @Bind(R.id.recharge_checkbox1)
    CheckBox mRechargeCheckbox1;
    @Bind(R.id.fabucp_fapiao1)
    LinearLayout mFabucpFapiao1;
    @Bind(R.id.recharge_checkbox2)
    CheckBox mRechargeCheckbox2;
    @Bind(R.id.fabucp_fapiao2)
    LinearLayout mFabucpFapiao2;
    @Bind(R.id.fapiao_bangong_checkbox)
    CheckBox mFapiaoBangongCheckbox;
    @Bind(R.id.fapiao_wangluo_layout_chechbox)
    CheckBox mFapiaoWangluoLayoutChechbox;
    @Bind(R.id.fapiao_caigou_checkbox)
    CheckBox mFapiaoCaigouCheckbox;
    @Bind(R.id.layout_checkbox1)
    LinearLayout mLayoutCheckbox1;
    @Bind(R.id.fapiao_kind)
    LinearLayout mFapiaoKind;
    private PopupWindow typeSelectPopup;
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
    @Bind(R.id.issueproject_butto_update)
    Button mIssueprojectButtoUpdate;
    @Bind(R.id.issueproject_button)
    Button mIssueprojectButton;
    @Bind(R.id.fabuxm_money)
    EditText mFabuxmMoney;
    @Bind(R.id.fabuxm_item_text)
    TextView mFabuxmItemText;
    @Bind(R.id.fabuxm_item_finder)
    LinearLayout mFabuxmItemFinder;
    @Bind(R.id.fabucp_cpfl_text)
    TextView mFabucpCpflText;
    @Bind(R.id.fabucp_cpfl)
    LinearLayout mFabucpCpfl;
    @Bind(R.id.fabucp_ppfl_text)
    TextView mFabucpPpflText;
    @Bind(R.id.fabucp_ppfl)
    LinearLayout mFabucpPpfl;
    @Bind(R.id.issueproject_et_describe)
    EditText mIssueprojectEtDescribe;
    @Bind(R.id.fabuxm_kucun)
    EditText mFabuxmKucun;
    @Bind(R.id.fabucp_jiage)
    LinearLayout mFabucpJiage;
    @Bind(R.id.fabucp_xj)
    LinearLayout mFabucpXj;
    @Bind(R.id.fabucp_yj)
    LinearLayout mFabucpYj;
    @Bind(R.id.fabucp_goumairiqi)
    LinearLayout mFabucpGoumairiqi;
    @Bind(R.id.fabucp_shijian)
    LinearLayout mFabucpShijian;
    @Bind(R.id.fabucp_zulingjiage)
    LinearLayout mFabucpZulingjiage;
    @Bind(R.id.fabucp_zhuanrangjiage)
    LinearLayout mFabucpZhuanrangjiage;
    @Bind(R.id.fabucp_zhuanrangjiage_text1)
    EditText mFabucpZhuanrangjiageText1;
    @Bind(R.id.fabucp_zhuanrangjiage_text2)
    TextView mFabucpZhuanrangjiageText2;
    @Bind(R.id.fabucp_zhuanrangjiage_text)
    EditText mFabucpZhuanrangjiageText;
    @Bind(R.id.fabucp_yuanjia_text)
    EditText mFabucpYuanjiaText;
    @Bind(R.id.fabucp_xianjia_text)
    EditText mFabucpXianjiaText;
    @Bind(R.id.fabucp_kucun)
    LinearLayout mFabucpKucun;
    @Bind(R.id.headImage1)
    ImageView mHeadImage1;
    private ArrayList<String> mArrayList, mArrayLists, mArrayListId, mArrayListsId;
    private String mId;
    private List<ProjectBean.DataBean.IndusBean> mIndus;
    private List<ProjectBean.DataBean.RegionBean> mRndus;
    private List<AreaBean.DataBean> mAndus;
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
    private String mArr1;
    private String mArr2;
    private String mShengId;
    private String mShiId1;
    private String mJiedaoId;
    private String mTitle;
    private String mDescride;
    private String mFujian;
    public static FaBuCpActivity instance = null;
    private String mString;
    private String mString1;
    private ArrayList<String> mListid;
    private ArrayList<String> mListname;
    private List<HomeInfoBean.DataBean> mData1;
    private ArrayList<String> mPplistName, mPplistId, mPplisttime;
    private String mString2;
    private String mS;
    private Object mPpfl;
    private Object mCpfl;
    private String mYuanjia;
    private String xinajia;
    private String mZhuanrangjaige;
    private String mZjjiage;
    private String mZjshijian;
    private String mKucun;
    //调用照相机返回图片临时文件
    private File tempFile;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    // 1: qq, 2: weixin
    private int type;
    private Bitmap mBitMap;
    private String mShengid;
    private String mShiId;
    private String mJiedaoId1;
    private String mOneId;
    private String mTwoId;
    private String mCropImagePath;
    static String GoodsId = "1";
    String flag = "0";
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    StringBuilder builder = new StringBuilder();
    StringBuilder builder1 = new StringBuilder();
    StringBuilder builder2 = new StringBuilder();
    private String mSubstring = "[]";

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabucp);
        Intent intent = getIntent();
        //预算
        mMoney = intent.getStringExtra("yusuan");
        KLog.e("money", mMoney);
        //时间
        mTime = intent.getStringExtra("time");
        KLog.e("time", mTime);
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        //用户名称
        mUsername = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");

        mContext = FaBuCpActivity.this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        alents();
        netWork();
        ButterKnife.bind(this);
        instance = this;
        mListname = new ArrayList<>();
        mListname.add("普通产品");
        mListname.add("促销产品");
        mListname.add("二手产品");
        mListname.add("清积压");
        mListname.add("技术转让");
        mListname.add("设备租借");
        mListid = new ArrayList<>();
        mListid.add("1");
        mListid.add("2");
        mListid.add("3");
        mListid.add("4");
        mListid.add("5");
        mListid.add("6");
        mPplisttime = new ArrayList<>();
        mPplisttime.add("小时");
        mPplisttime.add("天");
        mPplisttime.add("月");
        mPplisttime.add("年");
        //创建拍照存储的临时文件
        createCameraTempFile(savedInstanceState);
        mFabucpJiage.setVisibility(View.VISIBLE);
        mFabucpXj.setVisibility(View.GONE);
        mFabucpYj.setVisibility(View.GONE);
        mFabucpGoumairiqi.setVisibility(View.GONE);
        mFabucpZulingjiage.setVisibility(View.GONE);
        mFabucpZhuanrangjiage.setVisibility(View.GONE);
        //不允许开发票
        mRechargeCheckbox2.setChecked(true);
        mRechargeCheckbox2.setClickable(false);
        mRechargeCheckbox1.setClickable(true);
        mRechargeCheckbox1.setChecked(false);
    }


    @OnClick({R.id.fapiao_bangong_checkbox, R.id.fapiao_wangluo_layout_chechbox, R.id.fapiao_caigou_checkbox, R.id.recharge_checkbox1, R.id.recharge_checkbox2, R.id.fabucp_shijian, R.id.fabucp_cpfl, R.id.fabucp_ppfl, R.id.fabuxm_item_finder, R.id.issueproject_cp_one_layout, R.id.issueproject_cp_two_layout, R.id.issueproject_dq_layout1, R.id.issueproject_dq_layout2, R.id.issueproject_dq_layout3, R.id.issueproject_butto_update, R.id.issueproject_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fapiao_bangong_checkbox:
                if (count1 == 0) {
                    builder.append("0" + ",");
                    builder.append("1" + ",");
                    count1++;
                } else {
                    builder.setLength(0);
                    count1 = 0;
                }
                break;
            case R.id.fapiao_wangluo_layout_chechbox:
                if (count2 == 0) {
                    builder1.append("1" + ",");
                    builder1.append("2" + ",");
                    count2++;
                } else {
                    builder1.setLength(0);
                    count2 = 0;
                }
                break;
            case R.id.fapiao_caigou_checkbox:
             /*   builder.setLength(0);
                builder1.setLength(0);
                builder2.setLength(0);*/
                if (count3 == 0) {
                    builder2.append("2" + ",");
                    builder2.append("3" + ",");
                    count3++;
                } else {
                    builder2.setLength(0);
                    count3 = 0;
                }
                break;
            case R.id.recharge_checkbox1:
                count3 = 1;
                count2 = 1;
                count1 = 1;
                mFapiaoBangongCheckbox.setChecked(true);
                mFapiaoCaigouCheckbox.setChecked(true);
                mFapiaoWangluoLayoutChechbox.setChecked(true);
                builder.append("0" + ",");
                builder.append("1" + ",");
                builder1.append("1" + ",");
                builder1.append("2" + ",");
                builder2.append("2" + ",");
                builder2.append("3" + ",");
                mFapiaoKind.setVisibility(View.VISIBLE);
                flag = "1";
                mRechargeCheckbox1.setChecked(true);
                mRechargeCheckbox1.setClickable(false);
                mRechargeCheckbox2.setClickable(true);
                mRechargeCheckbox2.setChecked(false);
                break;
            case R.id.recharge_checkbox2:
                builder.setLength(0);
                builder1.setLength(0);
                builder2.setLength(0);
                mFapiaoKind.setVisibility(View.GONE);
                flag = "0";
                mRechargeCheckbox2.setChecked(true);
                mRechargeCheckbox2.setClickable(false);
                mRechargeCheckbox1.setClickable(true);
                mRechargeCheckbox1.setChecked(false);
                break;
            case R.id.fabucp_shijian:
                KLog.e("mPplisttime", mPplisttime.size());
                initSelectPopup0(mContext, mPplisttime, mFabucpZhuanrangjiageText2, mFabucpShijian);
                break;
            //产品分类
            case R.id.fabucp_cpfl:
                initSelectPopup1(mContext, mListname, mListid, mFabucpCpflText, mFabucpCpfl);
                mCpfl = get(ECApplication.context, "cpfl", "");
                break;
            //品牌分类
            case R.id.fabucp_ppfl:
                PopupUtils.initSelectPopup2(mContext, mPplistName, mPplistId, mFabucpPpflText, mFabucpPpfl);
                mPpfl = get(ECApplication.context, "ppfl", "");
                break;
            case R.id.issueproject_cp_one_layout:
             /*   public static String XmYi ="";
                public static String XmEr ="";
                public static String DqYi ="";
                public static String DqEr ="";
                public static String DqSan ="";*/
                PopupUtils.initSelectPopup(mContext, mArrayList, mArrayListId, null, null, null, null, null, mIssueprojectTvCpYiji, mIssueprojectCpOneLayout).showAsDropDown(mIssueprojectCpOneLayout, 0, 10);
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
                    KLog.e("项目二级", mArr2);
                } else {
                    Toast.makeText(mContext, "请选择第一项", Toast.LENGTH_SHORT).show();
                }
                break;
            //选择地区 省
            case R.id.issueproject_dq_layout1:
                PopupUtils.initSelectPopup(mContext, mRegionyNameList, null, null, mRegionIdyList, null, null, null, mIssueprojectTvDqSheng, mIssueprojectDqLayout1).showAsDropDown(mIssueprojectCpOneLayout, 0, 10);
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
                    KLog.e("街道", mJiedaoId);
                } else {
                    Toast.makeText(mContext, "请选择市区", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.issueproject_butto_update:
                type = 2;
                final BottomPopupOption bottomPopupOption = new BottomPopupOption(FaBuCpActivity.this);
                bottomPopupOption.setItemText("拍照", "选择相册");
                // bottomPopupOption.setColors();//设置颜色
                bottomPopupOption.showPopupWindow();
                bottomPopupOption.setItemClickListener(new BottomPopupOption.onPopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        switch (position) {
                            case 0:
                                //权限判断
                                if (ContextCompat.checkSelfPermission(FaBuCpActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请WRITE_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(FaBuCpActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                                } else {
                                    //跳转到调用系统相机
                                    gotoCarema();
                                }
                                bottomPopupOption.dismiss();
                                break;
                            case 1:
                                //权限判断
                                if (ContextCompat.checkSelfPermission(FaBuCpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请READ_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(FaBuCpActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                                } else {
                                    //跳转到调用系统图库
                                    gotoPhoto();
                                }
                                bottomPopupOption.dismiss();
                                break;
                        }
                    }
                });
                break;
            case R.id.fabuxm_item_finder:
                InputMethodManager imm = (InputMethodManager) ECApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date1, View v) {//选中事件回调
                        KLog.e("date", date1);
                        // 转换为24小时制式的字串
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mTime = df.format(date1);
                        mFabuxmItemText.setText(mTime);
                    }
                }).setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                        .setLabel("年", "月", "日", "", "", "")
                        .setCancelText("取消")
                        .setSubmitText("确认")
                        .build();
                pvTime.show();
                break;
            case R.id.issueproject_button:
                KLog.e("whb1", builder);
                KLog.e("whb2", builder1);
                KLog.e("whb3", builder2);
                if (builder.length() == 0) {
                    KLog.e("whb", "走了");
                }
                if (builder.length() != 0 || builder1.length() != 0 || builder2.length() != 0) {
                    String s = builder + "" + builder1 + builder2;
                    mSubstring = "[" + s.substring(0, s.length() - 1) + "]";
                } else {
                    mSubstring = "";
                }
                mArr1 = (String) get(ECApplication.context, "Midid", "");
                mArr2 = (String) get(ECApplication.context, "MMidid", "");
                mShengId = (String) get(ECApplication.context, "MMMidid", "");
                mShiId1 = (String) get(ECApplication.context, "MMMMidid", "");
                mJiedaoId = (String) get(ECApplication.context, "MMMMMidid", "");
                //原价
                mYuanjia = mFabucpYuanjiaText.getText().toString();
                //现价
                xinajia = mFabucpXianjiaText.getText().toString();
                //产品种类
                mString2 = mFabucpCpflText.getText().toString();
                //转让价格
                mZhuanrangjaige = mFabucpZhuanrangjiageText.getText().toString();
                //租借价格
                mZjjiage = mFabucpZhuanrangjiageText1.getText().toString();
                //租借时间
                mZjshijian = mFabucpZhuanrangjiageText2.getText().toString();
                //标题
                mTitle = mIssueprojectEtTitle.getText().toString();
                //价格
                mString = mFabuxmMoney.getText().toString();
                //描述
                mDescride = mIssueprojectEtDescribe.getText().toString();
                //购买日期
                mString1 = mFabuxmItemText.getText().toString();
                //库存
                mKucun = mFabuxmKucun.getText().toString().trim();
                //品牌
                mS = mFabucpPpflText.getText().toString();
                //省id
                mShengid = mIssueprojectTvDqSheng.getText().toString();
                //市id
                mShiId = mIssueprojectTvDqShi.getText().toString();
                //街道id
                mJiedaoId1 = mIssueprojectTvDqJiedao.getText().toString();
                //大分类id
                mOneId = mIssueprojectTvCpYiji.getText().toString();
                //小分类id
                mTwoId = mIssueprojectTvCpErji.getText().toString();
                if (!mString2.isEmpty()) {
                    if (mString2.equals("普通产品")) {
                        if (!mOneId.isEmpty()) {
                            if (!mTwoId.isEmpty()) {
                                if (!mShengid.isEmpty()) {
                                    if (!mShiId.isEmpty()) {
                                        if (!mJiedaoId1.isEmpty()) {
                                            if (!mTitle.isEmpty()) {
                                                if (!mS.isEmpty()) {
                                                    if (!mString.isEmpty()) {
                                                        if (!mFabuxmKucun.getText().toString().isEmpty()) {
                                                            if (flag.equals("0")) {
                                                                if (!mDescride.isEmpty()) {
                                                                    if (mBitMap != null) {
                                                                        mKProgressHUD.show();
                                                                        danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "1", mTitle, mPpfl + "", mDescride, "", mString, "", "个", mKucun, flag, mSubstring);
                                                                    } else {
                                                                        Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else if (mSubstring.length() != 0) {
                                                                if (!mDescride.isEmpty()) {
                                                                    if (mBitMap != null) {
                                                                        mKProgressHUD.show();
                                                                        danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "1", mTitle, mPpfl + "", mDescride, "", mString, "", "个", mKucun, flag, mSubstring);
                                                                    } else {
                                                                        Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(mContext, "请选择发票类型", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(mContext, "请填写库存", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(mContext, "请填写价格", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(mContext, "请选择品牌", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(mContext, "请填写产品名称", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mContext, "请选择产品分类", Toast.LENGTH_SHORT).show();
                        }
                    } else if (mString2.equals("促销产品")) {
                        if (!mOneId.isEmpty()) {
                            if (!mTwoId.isEmpty()) {
                                if (!mShengid.isEmpty()) {
                                    if (!mShiId.isEmpty()) {
                                        if (!mJiedaoId1.isEmpty()) {
                                            if (!mTitle.isEmpty()) {
                                                if (!mS.isEmpty()) {
                                                    if (!mYuanjia.isEmpty()) {
                                                        if (!xinajia.isEmpty()) {
                                                            if (!mFabuxmKucun.getText().toString().isEmpty()) {
                                                                if (flag.equals("0")) {
                                                                    if (!mDescride.isEmpty()) {
                                                                        if (mBitMap != null) {
                                                                            mKProgressHUD.show();
                                                                            danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "2", mTitle, mPpfl + "", mDescride, mYuanjia, xinajia, "", "个", mKucun, flag, mSubstring);
                                                                        } else {
                                                                            mKProgressHUD.show();
                                                                            Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    } else {
                                                                        Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else if (mSubstring.length() != 0) {
                                                                    if (!mDescride.isEmpty()) {
                                                                        if (mBitMap != null) {
                                                                            danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "2", mTitle, mPpfl + "", mDescride, mYuanjia, xinajia, "", "个", mKucun, flag, mSubstring);
                                                                        } else {
                                                                            Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    } else {
                                                                        Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请选择发票类型", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(mContext, "请填写库存", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(mContext, "请填写现价", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(mContext, "请填写原价", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(mContext, "请选择品牌", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(mContext, "请填写产品名称", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mContext, "请选择产品分类", Toast.LENGTH_SHORT).show();
                        }
                    } else if (mString2.equals("二手产品")) {
                        if (!mOneId.isEmpty()) {
                            if (!mTwoId.isEmpty()) {
                                if (!mShengid.isEmpty()) {
                                    if (!mShiId.isEmpty()) {
                                        if (!mJiedaoId1.isEmpty()) {
                                            if (!mTitle.isEmpty()) {
                                                if (!mS.isEmpty()) {
                                                    if (!mString1.isEmpty()) {
                                                        if (!mString.isEmpty()) {
                                                            if (flag.equals("0")) {
                                                                if (!mDescride.isEmpty()) {
                                                                    if (mBitMap != null) {
                                                                        mKProgressHUD.show();
                                                                        danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "3", mTitle, mPpfl + "", mDescride, "", mString, mString1, "个", mKucun, flag, mSubstring);
                                                                    } else {
                                                                        Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else if (mSubstring.length() != 0) {
                                                                if (!mDescride.isEmpty()) {
                                                                    if (mBitMap != null) {
                                                                        mKProgressHUD.show();
                                                                        danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "3", mTitle, mPpfl + "", mDescride, "", mString, mString1, "个", mKucun, flag, mSubstring);
                                                                    } else {
                                                                        Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(mContext, "请选择发票类型", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(mContext, "请填写价格", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(mContext, "请填写时间", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(mContext, "请选择品牌", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(mContext, "请填写产品名称", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mContext, "请选择产品分类", Toast.LENGTH_SHORT).show();
                        }
                    } else if (mString2.equals("清积压")) {
                        if (!mOneId.isEmpty()) {
                            if (!mTwoId.isEmpty()) {
                                if (!mShengid.isEmpty()) {
                                    if (!mShiId.isEmpty()) {
                                        if (!mJiedaoId1.isEmpty()) {
                                            if (!mTitle.isEmpty()) {
                                                if (!mS.isEmpty()) {
                                                    if (!mFabuxmKucun.getText().toString().isEmpty()) {
                                                        if (flag.equals("0")) {
                                                            if (!mDescride.isEmpty()) {
                                                                if (mBitMap != null) {
                                                                    mKProgressHUD.show();
                                                                    danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "4", mTitle, mPpfl + "", mDescride, "", mString, "", "个", mKucun, flag, mSubstring);
                                                                } else {
                                                                    Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else if (!mString.isEmpty()) {
                                                            if (mSubstring.length() != 0) {
                                                                if (!mDescride.isEmpty()) {
                                                                    if (mBitMap != null) {
                                                                        mKProgressHUD.show();
                                                                        danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "4", mTitle, mPpfl + "", mDescride, "", mString, "", "个", mKucun, flag, mSubstring);
                                                                    } else {
                                                                        Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(mContext, "请选择发票类型", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(mContext, "请填写库存", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(mContext, "请填写价格", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(mContext, "请选择品牌", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(mContext, "请填写产品名称", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mContext, "请选择产品分类", Toast.LENGTH_SHORT).show();
                        }
                    } else if (mString2.equals("技术转让")) {
                        if (!mOneId.isEmpty()) {
                            if (!mTwoId.isEmpty()) {
                                if (!mShengid.isEmpty()) {
                                    if (!mShiId.isEmpty()) {
                                        if (!mJiedaoId1.isEmpty()) {
                                            if (!mTitle.isEmpty()) {
                                                if (!mS.isEmpty()) {
                                                    if (!mZhuanrangjaige.isEmpty()) {
                                                        if (!mFabuxmKucun.getText().toString().isEmpty()) {
                                                            if (flag.equals("0")) {
                                                                if (!mDescride.isEmpty()) {
                                                                    if (mBitMap != null) {
                                                                        mKProgressHUD.show();
                                                                        danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "5", mTitle, mPpfl + "", mDescride, "", mZhuanrangjaige, "", "个", mKucun, flag, mSubstring);
                                                                    } else {
                                                                        Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else if (mSubstring.length() != 0) {
                                                                if (!mDescride.isEmpty()) {
                                                                    if (mBitMap != null) {
                                                                        mKProgressHUD.show();
                                                                        danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "5", mTitle, mPpfl + "", mDescride, "", mZhuanrangjaige, "", "个", mKucun, flag, mSubstring);
                                                                    } else {
                                                                        Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(mContext, "请选择发票类型", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(mContext, "请填写库存", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(mContext, "请填写价格", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(mContext, "请选择品牌", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(mContext, "请填写产品名称", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mContext, "请选择产品分类", Toast.LENGTH_SHORT).show();
                        }
                    } else if (mString2.equals("设备租借")) {
                        if (!mOneId.isEmpty()) {
                            if (!mTwoId.isEmpty()) {
                                if (!mShengid.isEmpty()) {
                                    if (!mShiId.isEmpty()) {
                                        if (!mJiedaoId1.isEmpty()) {
                                            if (!mTitle.isEmpty()) {
                                                if (!mS.isEmpty()) {
                                                    if (!mZjjiage.isEmpty()) {
                                                        if (!mZjshijian.isEmpty()) {
                                                            if (!mFabuxmKucun.getText().toString().isEmpty()) {
                                                                if (flag.equals("0")) {
                                                                    if (!mDescride.isEmpty()) {
                                                                        if (mBitMap != null) {
                                                                            mKProgressHUD.show();
                                                                            danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "6", mTitle, mPpfl + "", mDescride, "", mString, "", mZjshijian, mKucun, flag, mSubstring);
                                                                        } else {
                                                                            Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    } else {
                                                                        Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else if (mSubstring.length() != 0) {
                                                                    if (!mDescride.isEmpty()) {
                                                                        if (mBitMap != null) {
                                                                            mKProgressHUD.show();
                                                                            danren(mUserid, DqYi, DqEr, DqSan, XmYi, XmEr, "6", mTitle, mPpfl + "", mDescride, "", mString, "", mZjshijian, mKucun, flag, mSubstring);
                                                                        } else {
                                                                            Toast.makeText(mContext, "请上传图片", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    } else {
                                                                        Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(mContext, "请选择发票类型", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(mContext, "请填写库存", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(mContext, "请填写租借时间", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(mContext, "请填写租借价格", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(mContext, "请选择品牌", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(mContext, "产品名称不能为空", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mContext, "请选择产品分类", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(mContext, "请选择产品种类", Toast.LENGTH_SHORT).show();
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
        SharedPrefsUtil.remove(ECApplication.context, "Midid");
        SharedPrefsUtil.remove(ECApplication.context, "MMidid");
        SharedPrefsUtil.remove(ECApplication.context, "MMMidid");
        SharedPrefsUtil.remove(ECApplication.context, "MmMMidid");
        SharedPrefsUtil.remove(ECApplication.context, "MMMMMidid");
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


    public void danren(
            String uid,
            String province,
            String city,
            String area,
            String indus_pid,
            String indus_id,
            String sales,
            String txt_title,
            String brand_id,
            String tar_content,
            String txt_old_price,
            String txt_price,
            String pay_day,
            String unite_price,
            String num, String invoice,
            String invoice_type

    ) {
        HashMap<String, String> hashMap = new HashMap<>();
        //参数
        hashMap.put("action", "NextGoodsSept1");
        //用户id
        hashMap.put("uid", uid);
        KLog.e("uid", uid);
        //省id
        hashMap.put("province", province);
        KLog.e("province", province);
        //市id
        hashMap.put("city", city);
        KLog.e("city", city);
        //街道id
        hashMap.put("area", area);
        KLog.e("area", area);
        //产品种类
        hashMap.put("sales", sales);
        KLog.e("sales", sales);
        //分类id
        hashMap.put("indus_pid", indus_pid);
        KLog.e("indus_pid", indus_pid);
        //小分类id
        hashMap.put("indus_id", indus_id);
        KLog.e("indus_id", indus_id);
        //标题
        hashMap.put("txt_title", txt_title);
        KLog.e("txt_title", txt_title);
        //品牌id
        hashMap.put("brand_id", brand_id);
        KLog.e("brand_id", brand_id);
        //内容描述
        hashMap.put("tar_content", tar_content);
        KLog.e("tar_content", tar_content);
        //原价
        hashMap.put("txt_old_price", txt_old_price);
        KLog.e("txt_old_price", txt_old_price);
        //出售价格
        hashMap.put("txt_price", txt_price);
        KLog.e("txt_price", txt_price);
        //购买日期
        hashMap.put("pay_day", pay_day);
        KLog.e("pay_day", pay_day);
        //单价
        hashMap.put("unite_price", unite_price);
        KLog.e("unite_price", unite_price);
        //数量
        hashMap.put("num", num);
        KLog.e("num", num);
        //是否开发票
        hashMap.put("invoice", invoice);
        //发票类型
        hashMap.put("invoice_type", "[" + invoice_type + "]");
        KLog.e("hashmap", hashMap.toString());

        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).params("upload", new File(mCropImagePath)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mKProgressHUD.dismiss();
                KLog.json("ssss", s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                if (code.equals("888")) {
                    Toast.makeText(mContext, "发布成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toasty.error(ECApplication.context, "发布失败", 0, true).show();
                }
            }
        });
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

    @OnClick(R.id.fabuxm_item_finder)
    public void onClick() {
    }

    public void netWork() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsTextInfo");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<HomeInfoBean>() {
            @Override
            public void onSuccess(HomeInfoBean homeInfoBean, Call call, Response response) {
                HomeInfoBean.DataBean data = homeInfoBean.getData();
                List<HomeInfoBean.DataBean.BrandBean> brand = data.getBrand();
                mPplistName = new ArrayList<String>();
                mPplistId = new ArrayList<String>();
                for (HomeInfoBean.DataBean.BrandBean brandBean : brand) {
                    mPplistName.add(brandBean.getBname());
                    mPplistId.add(brandBean.getBid());
                }
            }
        });
    }

    public PopupWindow initSelectPopup1(Context context, final ArrayList<String> list, final ArrayList<String> list1,
                                        final TextView v, View v1) {
        ListView mTypeLv = new ListView(context);
        final MypopupAdapter mypopupAdapter = new MypopupAdapter(context, list);
        mTypeLv.setAdapter(mypopupAdapter);
        // 设置ListView点击事件监听
        // 让垂直滚动条不可见
        mTypeLv.setDividerHeight(0);
        mTypeLv.setVerticalScrollBarEnabled(false);
        mTypeLv.setSelector(R.color.transparent);
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = list.get(position);
                if (list1 != null) {
                    String mVid = list1.get(position);
                    KLog.e("产品分类", mVid);
                    GoodsId = mVid;
                }
                KLog.e("value", value);
                // 把选择的数据展示对应的TextView上
                v.setText(value);
                if (value.equals("普通产品")) {
                    mFabucpJiage.setVisibility(View.VISIBLE);
                    mFabucpXj.setVisibility(View.GONE);
                    mFabucpYj.setVisibility(View.GONE);
                    mFabuxmKucun.setText("");
                    mFabuxmKucun.setEnabled(true);
                    mFabucpGoumairiqi.setVisibility(View.GONE);
                    mFabucpZulingjiage.setVisibility(View.GONE);
                    mFabucpZhuanrangjiage.setVisibility(View.GONE);
                } else if (value.equals("促销产品")) {
                    mFabuxmKucun.setText("");
                    mFabuxmKucun.setEnabled(true);
                    mFabucpJiage.setVisibility(View.GONE);
                    mFabucpXj.setVisibility(View.VISIBLE);
                    mFabucpYj.setVisibility(View.VISIBLE);
                    mFabucpGoumairiqi.setVisibility(View.GONE);
                } else if (value.equals("二手产品")) {
                    mFabuxmKucun.setText("1");
                    mFabuxmKucun.setEnabled(false);
                    mFabucpGoumairiqi.setVisibility(View.VISIBLE);
                    mFabucpJiage.setVisibility(View.VISIBLE);
                    mFabucpXj.setVisibility(View.GONE);
                    mFabucpYj.setVisibility(View.GONE);
                } else if (value.equals("技术转让")) {
                    mFabuxmKucun.setText("1");
                    mFabuxmKucun.setEnabled(false);
                    mFabucpZhuanrangjiage.setVisibility(View.VISIBLE);
                    mFabucpJiage.setVisibility(View.GONE);
                    mFabucpXj.setVisibility(View.GONE);
                    mFabucpYj.setVisibility(View.GONE);
                    mFabucpGoumairiqi.setVisibility(View.GONE);
                    mFabucpZulingjiage.setVisibility(View.GONE);
                } else if (value.equals("清积压")) {
                    mFabuxmKucun.setText("");
                    mFabuxmKucun.setEnabled(true);
                    mFabucpJiage.setVisibility(View.VISIBLE);
                    mFabucpXj.setVisibility(View.GONE);
                    mFabucpYj.setVisibility(View.GONE);
                    mFabucpGoumairiqi.setVisibility(View.GONE);
                    mFabucpZulingjiage.setVisibility(View.GONE);
                    mFabucpZhuanrangjiage.setVisibility(View.GONE);
                } else if (value.equals("设备租借")) {
                    mFabuxmKucun.setText("1");
                    mFabuxmKucun.setEnabled(false);
                    mFabucpZulingjiage.setVisibility(View.VISIBLE);
                    mFabucpJiage.setVisibility(View.GONE);
                    mFabucpXj.setVisibility(View.GONE);
                    mFabucpYj.setVisibility(View.GONE);
                    mFabucpGoumairiqi.setVisibility(View.GONE);
                    mFabucpZhuanrangjiage.setVisibility(View.GONE);
                }
                // 选择完后关闭popup窗口
                typeSelectPopup.dismiss();
                mypopupAdapter.notifyDataSetChanged();
            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, v1.getWidth(), 330, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bg_corner);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
        if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
            typeSelectPopup.showAsDropDown(v1, 0, 10);
        }
        return typeSelectPopup;
    }

    public PopupWindow initSelectPopup0(Context context, final ArrayList<String> lists,
                                        final TextView v, View v1) {
        ListView mTypeLv = new ListView(context);
        final MypopupAdapter mypopupAdapter = new MypopupAdapter(context, lists);
        mTypeLv.setAdapter(mypopupAdapter);
        // 设置ListView点击事件监听
        // 让垂直滚动条不可见
        mTypeLv.setDividerHeight(0);
        mTypeLv.setVerticalScrollBarEnabled(false);
        mTypeLv.setSelector(R.color.transparent);
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = lists.get(position);
                KLog.e("value", value);
                // 把选择的数据展示对应的TextView上
                v.setText(value);
                // 选择完后关闭popup窗口
                typeSelectPopup.dismiss();
                mypopupAdapter.notifyDataSetChanged();
            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, v1.getWidth(), 330, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bg_corner);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
        if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
            typeSelectPopup.showAsDropDown(v1, 0, 10);
        }
        return typeSelectPopup;
    }

    /**
     * 跳转到照相机
     */
    private void gotoCarema() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }

    /**
     * 外部存储权限申请返回
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCarema();
            } else {
                // Permission Denied
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            } else {
                // Permission Denied
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    mCropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    mBitMap = BitmapFactory.decodeFile(mCropImagePath);
                    KLog.e("cropImagePath", mCropImagePath);
                    if (type == 1) {
                        mHeadImage1.setImageBitmap(mBitMap);
                    } else {
                        mHeadImage1.setImageBitmap(mBitMap);
                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }
    }


    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
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

    public byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    String fileUrl = Environment.getExternalStorageDirectory() + "/pic/";

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public void saveFile(Bitmap bm, String fileName) throws IOException {
        File dirFile = new File(fileUrl);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
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
        XmYi = "";
        XmEr = "";
        DqYi = "";
        DqEr = "";
        DqSan = "";
    }

}

