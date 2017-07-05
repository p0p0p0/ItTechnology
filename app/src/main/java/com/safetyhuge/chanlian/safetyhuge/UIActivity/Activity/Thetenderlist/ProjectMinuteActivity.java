package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProjectminuteBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CompetitiveActivityInfo.CommonActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CompetitiveActivityInfo.CompetitiveActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CompetitiveActivityInfo.SubscriptionActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.FileActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.PostponeActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment.QuanbuBean;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.FragmentFactory;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.FragmentFactory1;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;
import com.zhl.cbdialog.CBDialogBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目详细
 * 作者：王海宾 on 2017/4/11 0011 10:29
 * 邮箱：995696826@qq.com
 */

public class ProjectMinuteActivity extends HBaseAct {

    @Bind(R.id.project_image)
    CircleImageView mProjectImage;
    @Bind(R.id.project_name)
    TextView mProjectName;
    @Bind(R.id.project_zhuangtai)
    TextView mProjectZhuangtai;
    @Bind(R.id.project_xuanshang)
    TextView mProjectXuanshang;
    @Bind(R.id.project_dizhi)
    TextView mProjectDizhi;
    @Bind(R.id.project_title1)
    TextView mProjectTitle1;
    @Bind(R.id.project_money1)
    TextView mProjectMoney1;
    @Bind(R.id.scheme_item)
    TextView mSchemeItem;
    @Bind(R.id.project_title2)
    WebView mProjectTitle2;
    @Bind(R.id.button_fujian)
    Button mButtonFujian;
    @Bind(R.id.button_yanqi)
    LinearLayout mButtonYanqi;
    @Bind(R.id.button_yanqi_text)
    Button button_yanqi_text;
    @Bind(R.id.button_biaoshuliebiao)
    Button mButtonBiaoshuliebiao;
    @Bind(R.id.project_layout)
    LinearLayout mSchemeLayout;
    @Bind(R.id.project_day)
    TextView mProjectDay;
    @Bind(R.id.project_hours)
    TextView mProjectHours;
    @Bind(R.id.project_minutes)
    TextView mProjectMinutes;
    @Bind(R.id.button_jingbiao)
    Button mButtonJingbiao;
    @Bind(R.id.project_fujian)
    LinearLayout mProjectFujian;
    @Bind(R.id.item_layout)
    LinearLayout mItemLayout;
    private String mSchemeid;
    private Context mContext;
    private KProgressHUD mProgressHUD;
    private String mModel;
    private List<ProjectminuteBean.DataBean> mData;
    private String mModel_id;
    private String mUserid;
    private String mS;
    private QuanbuBean.DataBean mData1;
    private String mHtml;
    private boolean mB;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        ButterKnife.bind(this);
        mSchemeLayout.setVisibility(View.INVISIBLE);
        mContext = ProjectMinuteActivity.this;
        mProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        Intent intent = getIntent();
        mSchemeid = intent.getStringExtra("layoutid");
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        KLog.e("mSchemeid", mSchemeid);
        KLog.e("mUserid", mUserid);
        mButtonBiaoshuliebiao.setBackgroundColor(Color.WHITE);
        mButtonBiaoshuliebiao.setTextColor(CommonUtil.getColor(R.color.text_brown));
        mB = false;
        NewWork(mUserid, mSchemeid);
    }

    private void NewWork(String uid, String mSchemeid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskInfoByTaskId");
        KLog.e("uid", uid);
        if (uid.equals("")) {
            hashMap.put("uid", "1");
        } else {
            hashMap.put("uid", uid);
        }
        hashMap.put("task_id", mSchemeid);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<ProjectminuteBean>() {
            @Override
            public void onSuccess(ProjectminuteBean projectminuteBean, Call call, Response response) {
                mProgressHUD.dismiss();
                mSchemeLayout.setVisibility(View.VISIBLE);
                mData = projectminuteBean.getData();
                boolean yanqi = mData.get(0).isDelay();
                mModel = "";
                mModel_id = mData.get(0).getModel_id();
                KLog.e("mModel_id", mModel_id);
                KLog.e("jingbiao", yanqi);
                if (mUserid != "") {
                    if (yanqi == true) {
                        mButtonBiaoshuliebiao.setBackgroundColor(CommonUtil.getColor(R.color.jue));
                        mButtonJingbiao.setVisibility(View.GONE);
                        mButtonYanqi.setVisibility(View.VISIBLE);
                        mButtonBiaoshuliebiao.setTextColor(Color.WHITE);
                        button_yanqi_text.setTextColor(CommonUtil.getColor(R.color.text_brown));

                    } else {
                        mButtonYanqi.setVisibility(View.GONE);
                    }
                    if (mModel_id.equals("1") || mModel_id.equals("3")) {
                        if (yanqi == false) {
                            mButtonYanqi.setVisibility(View.GONE);
                            mButtonJingbiao.setVisibility(View.VISIBLE);
                        }
                    } else if (mModel_id.equals("4") || mModel_id.equals("5")) {
                        if (mData.get(0).isWork_hand() == false && mData.get(0).getDelivery() == 0) {
                            mButtonYanqi.setVisibility(View.GONE);
                            mButtonBiaoshuliebiao.setBackgroundColor(CommonUtil.getColor(R.color.jue));
                            mButtonBiaoshuliebiao.setTextColor(Color.WHITE);
                            mButtonJingbiao.setVisibility(View.GONE);
                        }
                        KLog.e("workhand", mData.get(0).isWork_hand() + "");
                    }
                }
                //有无附件
                if (mData.get(0).getFile() != null) {
                    mProjectFujian.setVisibility(View.VISIBLE);
                }
                //头像
                Picasso.with(mContext).load(RequestAddress.IMAGE + "/" + mData.get(0).getUpic()).fit().into(mProjectImage);
                //名称
                mProjectName.setText(mData.get(0).getUsername());
                //状态
                mProjectZhuangtai.setText(mData.get(0).getShow_status());
                //悬赏
                if (mModel_id.equals("1")) {
                    mModel = "单人悬赏";
                } else if (mModel_id.equals("2")) {
                    mModel = "多人悬赏";
                } else if (mModel_id.equals("4")) {
                    mModel = "普通招标";
                } else if (mModel_id.equals("5")) {
                    mModel = "订金招标";
                }
                mProjectXuanshang.setText(mModel);
                //地址
                mProjectDizhi.setText("地点:" + mData.get(0).getProvince_name() + mData.get(0).getCity_name() + mData.get(0).getArea_name());
                mProjectTitle1.setText(mData.get(0).getTask_title());
                mProjectMoney1.setText(mData.get(0).getShow_cash());
                //返回来的值  拿到请求的数据 里面的值
                String task_desc = mData.get(0).getTask_desc();
                mHtml = Html.fromHtml(task_desc, null,null)+"";
                if (mB==false){
                    initWebView(mHtml);
                }
                String tiem = mData.get(0).getSub_time();
                KLog.e("tiem", tiem);
                mS = DateUtils.timesOne(tiem);
                KLog.e("xianzaitiem", getTime());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String str = formatter.format(curDate);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = df.parse(mS);
                    d2 = df.parse(str);
                    long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
                    long days = diff / (1000 * 60 * 60 * 24);
                    long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                    long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                    if (days >= 0) {
                        mProjectDay.setText(days + "");
                        mProjectHours.setText(hours + "");
                        mProjectMinutes.setText(minutes + "");
                    } else {
                        mItemLayout.setVisibility(View.GONE);
                        mProjectDay.setText("(已完成)");

                    }
                    KLog.e("" + days + "天" + hours + "小时" + minutes + "分");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick({R.id.button_fujian, R.id.button_yanqi_text, R.id.button_biaoshuliebiao, R.id.button_jingbiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_fujian:
                if (mUserid != "") {
                    List<ProjectminuteBean.DataBean.FileBean> file = mData.get(0).getFile();
                    //有无附件
                    if (mData.get(0).getFile() != null) {
                        Intent intent = new Intent(ProjectMinuteActivity.this, FileActivity.class);
                        intent.putExtra("uid", mData.get(0).getTask_id());
                        intent.putParcelableArrayListExtra("file", (ArrayList<? extends Parcelable>) file);
                        startActivity(intent);
                    } else {
                        Toast.makeText(mContext, "没有附件", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNormalDialog();
                }
                break;
            case R.id.button_yanqi_text:
                Intent intent = new Intent(ECApplication.context, PostponeActivity.class);
                intent.putExtra("username", mData.get(0).getUsername());
                intent.putExtra("show_cash", mData.get(0).getShow_cash());
                intent.putExtra("model", mModel);
                intent.putExtra("modelid", mSchemeid);
                intent.putExtra("item", mS);
                startActivity(intent);
                break;
            //表书列表
            case R.id.button_biaoshuliebiao:
                SharedPrefsUtil.put(this, "XmFbUid", mData.get(0).getUid());
                if (mUserid != "") {
                    KLog.e("mModel", mModel);
                    if (mModel.equals("单人悬赏")) {
                        FragmentFactory mFactory = new FragmentFactory(0);
                        mFactory.setFlage(true);
                        SharedPrefsUtil.put(ECApplication.context, "sote", 0);
                        SharedPrefsUtil.put(ECApplication.context, "xmid", mSchemeid);
                        startActivity(new Intent(ProjectMinuteActivity.this, SoloActivity.class
                        ));
                    } else if (mModel.equals("多人悬赏")) {
                        FragmentFactory mFactory = new FragmentFactory(1);
                        mFactory.setFlage(true);
                        SharedPrefsUtil.put(ECApplication.context, "sote", 1);
                        SharedPrefsUtil.put(ECApplication.context, "xmid", mSchemeid);
                        startActivity(new Intent(ProjectMinuteActivity.this, PeopleActivity.class
                        ));
                    } else if (mModel.equals("普通招标")) {
                        FragmentFactory1 mFactory = new FragmentFactory1(2);
                        mFactory.setFlage(true);
                        SharedPrefsUtil.put(ECApplication.context, "sote", 0);
                        SharedPrefsUtil.put(ECApplication.context, "xmid", mSchemeid);
                        startActivity(new Intent(ProjectMinuteActivity.this, CommonsActivity.class
                        ));

                    } else if (mModel.equals("订金招标")) {
                        FragmentFactory1 mFactory = new FragmentFactory1(3);
                        mFactory.setFlage(true);
                        SharedPrefsUtil.put(ECApplication.context, "sote", 0);
                        SharedPrefsUtil.put(ECApplication.context, "xmid", mSchemeid);
                        startActivity(new Intent(ProjectMinuteActivity.this, SubActivity.class
                        ));
                    }
                } else {
                    showNormalDialog();
                }
                break;
            //竞标
            case R.id.button_jingbiao:
                if (mUserid != "") {
                    if (mModel.equals("单人悬赏") || mModel.equals("多人悬赏")) {
                        Intent intent1 = new Intent(ProjectMinuteActivity.this, CompetitiveActivity.class);
                        intent1.putExtra("task_id", mSchemeid);
                        intent1.putExtra("userid", mUserid);
                        startActivity(intent1);
                    } else if (mModel.equals("普通招标")) {
                        Intent intent1 = new Intent(ProjectMinuteActivity.this, CommonActivity.class);
                        intent1.putExtra("task_id", mSchemeid);
                        intent1.putExtra("userid", mUserid);
                        startActivity(intent1);
                    } else {
                        Intent intent1 = new Intent(ProjectMinuteActivity.this, SubscriptionActivity.class);
                        intent1.putExtra("task_id", mSchemeid);
                        intent1.putExtra("userid", mUserid);
                        startActivity(intent1);
                    }
                } else {
                    showNormalDialog();
                }
                break;
        }
    }

    public void back_text_view(View view) {
        exitAct();
    }

    public long getTime() {
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        return time;
    }

    private void showNormalDialog() {
        new CBDialogBuilder(this)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle("提示")
                .setMessage("请先登录")
                .setConfirmButtonText("确定")
                .setCancelButtonText("取消").setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                switch (whichBtn) {
                    case BUTTON_CONFIRM:
                        startActivity(new Intent(ProjectMinuteActivity.this, LoginsActivity.class));
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

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        NewWork(mUserid, mSchemeid);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mB=true;
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
    private void initWebView(String task_desc){
        mProjectTitle2.getSettings().setJavaScriptEnabled(true);
        mProjectTitle2.getSettings().setBuiltInZoomControls(true);
        mProjectTitle2.getSettings().setDisplayZoomControls(false);
        mProjectTitle2.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果
        mProjectTitle2.getSettings().setDefaultTextEncodingName("UTF-8") ;
        mProjectTitle2.getSettings().setBlockNetworkImage(false);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            mProjectTitle2.getSettings().setMixedContentMode(mProjectTitle2.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
        }
        mProjectTitle2.loadDataWithBaseURL("",task_desc,"text/html", "UTF-8", null);
    }
}
