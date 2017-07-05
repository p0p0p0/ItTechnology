package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.TalentsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.RegionInfoFragment.ShengFragment;
import com.safetyhuge.chanlian.safetyhuge.adapter.TalentsAdapters;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.views.SwipeRefreshView;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class TalentsHallActivity extends HBaseAct implements CallBack {


    @Bind(R.id.talents_listview)
    MyListView mTalentsListview;
    @Bind(R.id.hall_dengji_text)
    TextView mHallDengjiText;
    @Bind(R.id.hall_dengji_iamge)
    ImageView mHallDengjiIamge;
    @Bind(R.id.talents_hall_dengji)
    LinearLayout mTalentsHallDengji;
    @Bind(R.id.hall_haoping_text)
    TextView mHallHaopingText;
    @Bind(R.id.hall_haoping_image)
    ImageView mHallHaopingImage;
    @Bind(R.id.talents_hall_haoping)
    LinearLayout mTalentsHallHaoping;
    @Bind(R.id.hall_shouru_text)
    TextView mHallShouruText;
    @Bind(R.id.hall_shouru_image)
    ImageView mHallShouruImage;
    @Bind(R.id.talents_hall_shouru)
    LinearLayout mTalentsHallShouru;
    @Bind(R.id.hall_shaixuan_text)
    TextView mHallShaixuanText;
    @Bind(R.id.talents_hall_shaixuan)
    LinearLayout mTalentsHallShaixuan;
    @Bind(R.id.id_flowlayout_xmly)
    TagFlowLayout mIdFlowlayoutXmly;
    @Bind(R.id.id_flowlayout_rcsf)
    TagFlowLayout mIdFlowlayoutRcsf;
    @Bind(R.id.diqu_button)
    TextView mDiquButton;
    @Bind(R.id.chongzhi_button)
    Button mChongzhiButton;
    @Bind(R.id.queding_button)
    Button mQuedingButton;
    @Bind(R.id.goods_soppingg)
    FrameLayout mTalentsShaixuan;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.swipe_layout)
    SwipeRefreshView mSwipeLayout;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.cx_onclick)
    TextView mCxOnclick;
    @Bind(R.id.tv_img_back)
    ImageView mTvImgBack;
    @Bind(R.id.Login_Edt_PassWord)
    EditText mLoginEdtPassWord;
    @Bind(R.id.talents_hall_soushuo)
    LinearLayout mTalentsHallSoushuo;
    @Bind(R.id.hall_soushuo)
    ImageView mHallSoushuo;
    @Bind(R.id.messge_img)
    ImageView mMessgeImg;
    @Bind(R.id.hall_biaozhi)
    LinearLayout mHallBiaozhi;
    @Bind(R.id.shanxuan)
    LinearLayout mShanxuan;
    private Activity mContext;
    KProgressHUD mKProgressHUD;
    private TalentsAdapters mAdapters;
    private String mUserid;
    private Object mDiqu;
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    private boolean mFlag1 = true;
    private List<String> mList, mLists;
    private int mPosition, mPosition1;
    private String mMlist1;
    private String mMlist;
    private String mShengid;
    private String mShengname;
    boolean flag = true;
    boolean flag1 = true;
    boolean DjFlag = true;
    boolean HpFlag = true;
    boolean SrFlag = true;
    int count = 1;
    private List<TalentsBean.DataBean> mDataBeen;
    boolean b = true;
    private String mShengId;
    private String mShiId;
    private String mJiedaoId;
    private int mIntExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents_hall);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        KLog.e("mUseridmUserid", mUserid);
        mContext = TalentsHallActivity.this;
        Intent intent = getIntent();
        mIntExtra = intent.getIntExtra("flag", -1);
        mKProgressHUD = KProgressHUD.create(TalentsHallActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        alents("", "1", "", "", "", "", "", "", "1");
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        mList = new ArrayList<>();
        mLists = new ArrayList<>();
        mList.add("网络安全");
        mList.add("系统集成");
        mList.add("信息化安全");
        mList.add("物联网");
        mList.add("云存储");
        mList.add("云计算");
        mList.add("大数据分析");
        mList.add("安全相关服务");
        mLists.add("2");
        mLists.add("3");
        mLists.add("121");
        mLists.add("160");
        mLists.add("192");
        mLists.add("201");
        mLists.add("211");
        mLists.add("218");
        //0 个人 1 企业
        ArrayList<String> list = new ArrayList<>();
        final ArrayList<String> list1 = new ArrayList<>();
        list.add("个人");
        list.add("企业");
        list1.add("0");
        list1.add("1");
        mIdFlowlayoutXmly.setAdapter(new TagAdapter(mList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ECApplication.context, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        });
        mIdFlowlayoutXmly.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                Set<Integer> selectPosSet1 = selectPosSet;
                String s = selectPosSet1.toString();
                for (Integer integer : selectPosSet1) {
                    KLog.e("integer", integer);
                    Integer A = new Integer(integer);
                    mPosition = A.intValue();
                }
                if (s.length() > 2) {
                    mMlist = mLists.get(mPosition);
                    KLog.e("mMlistmMlistmMlist", mMlist);
                } else {
                    mMlist = "";
                }
            }
        });
        //人才身份
        mIdFlowlayoutRcsf.setAdapter(new TagAdapter(list) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ECApplication.context, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        });
        mIdFlowlayoutRcsf.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                Set<Integer> selectPosSet1 = selectPosSet;
                String s = selectPosSet1.toString();
                for (Integer integer : selectPosSet1) {
                    KLog.e("integer", integer);
                    Integer A = new Integer(integer);
                    mPosition1 = A.intValue();
                }
                if (s.length() > 2) {
                    mMlist1 = list1.get(mPosition1);
                    KLog.e("mMlistmMlistmMlist", mMlist1);
                } else {
                    mMlist1 = "";
                }
            }
        });
        // 设置下拉加载更多
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = 1;
                mDataBeen.clear();
                if (flag == true) {
                    alents("", "1", "", "", "", "", "", "", "1");
                } else {
                    if (DjFlag != true) {
                        if (count1 == 0) {
                            alents("", "1", "", "", "", "", "", "", "2");
                        } else {
                            alents("", "1", "", "", "", "", "", "", "1");
                        }
                    }
                    if (HpFlag != true) {
                        if (count2 == 0) {
                            alents("", "1", "", "", "", "", "", "", "4");
                        } else {
                            alents("", "1", "", "", "", "", "", "", "3");
                        }
                    }
                    if (SrFlag != true) {
                        if (count3 == 0) {
                            alents("", "1", "", "", "", "", "", "", "6");
                        } else {
                            alents("", "1", "", "", "", "", "", "", "5");
                        }
                    }
                }
            }
        });
        //设置上拉加载更多
        mSwipeLayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                count++;
                if (b == true) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (flag == true) {
                                alents1("", count + "", "", "", "", "", "", "", "1");
                            } else {
                                if (DjFlag != true) {
                                    if (count1 == 0) {
                                        alents1("", count + "", "", "", "", "", "", "", "2");
                                    } else {
                                        alents1("", count + "", "", "", "", "", "", "", "1");
                                    }
                                }
                                if (HpFlag != true) {
                                    if (count2 == 0) {
                                        alents1("", count + "", "", "", "", "", "", "", "4");
                                    } else {
                                        alents1("", count + "", "", "", "", "", "", "", "3");
                                    }
                                }
                                if (SrFlag != true) {
                                    if (count3 == 0) {
                                        alents1("", count + "", "", "", "", "", "", "", "6");
                                    } else {
                                        alents1("", count + "", "", "", "", "", "", "", "5");
                                    }
                                }
                            }
                        }
                    }, 2000);
                } else {
                    mSwipeLayout.setLoading(false);
                }

            }
        });
    }

    //人才大厅
    public void alents(String keyword, String page, String indus_pid, String indus_id, String type, String province,
                       String city, String area,
                       String order
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
    /*    参数名	参数值	备注
        action	GetUserInfoList	必填
        keyword	管理员	选填 查询关键词
        page	1	选填 默认为1 当前页
        indus_pid	1	选填 分类ID
        indus_id	2	选填 小分类ID
        type	1	选填 身份类型
        province	1	选填 省ID
        city	2	选填 市ID
        area	3	选填 村ID
        order	1	选填 排序
        1.2 等级排序
        3 4 好评排序
        5 6 收入排序
                不传参默认排序*/

        hashMap.put("action", "GetUserInfoList");
        hashMap.put("keyword", keyword);
        hashMap.put("page", page);
        hashMap.put("indus_pid", indus_pid);
        hashMap.put("indus_id", indus_id);
        hashMap.put("type", type);
        hashMap.put("province", province);
        hashMap.put("city", city);
        hashMap.put("area", area);
        hashMap.put("order", order);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new JsonCallback<TalentsBean>() {
            @Override
            public void onSuccess(TalentsBean talentsBean, Call call, Response response) {
                mDataBeen = talentsBean.getData();
                mKProgressHUD.dismiss();
                mSwipeLayout.setRefreshing(false);
                if (!mDataBeen.isEmpty()) {
                    mSwipeLayout.setVisibility(View.VISIBLE);
                    mQuanbuImage.setVisibility(View.GONE);
                    mAdapters = new TalentsAdapters(mContext, mDataBeen, 2);
                    if (mAdapters != null) {
                        mAdapters.setUid(mUserid);
                        KLog.e("mUseridmUserid", mUserid);
                    }
                    mTalentsListview.setAdapter(mAdapters);
                } else {
                    mSwipeLayout.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mKProgressHUD.dismiss();
            }
        });
    }

    //人才大厅
    public void alents1(String keyword, String page, String indus_pid, String indus_id, String type, String province,
                        String city, String area,
                        String order
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
    /*    参数名	参数值	备注
        action	GetUserInfoList	必填
        keyword	管理员	选填 查询关键词
        page	1	选填 默认为1 当前页
        indus_pid	1	选填 分类ID
        indus_id	2	选填 小分类ID
        type	1	选填 身份类型
        province	1	选填 省ID
        city	2	选填 市ID
        area	3	选填 村ID
        order	1	选填 排序
        1.2 等级排序
        3 4 好评排序
        5 6 收入排序
                不传参默认排序*/

        hashMap.put("action", "GetUserInfoList");
        hashMap.put("keyword", keyword);
        hashMap.put("page", page);
        hashMap.put("indus_pid", indus_pid);
        hashMap.put("indus_id", indus_id);
        hashMap.put("type", type);
        hashMap.put("province", province);
        hashMap.put("city", city);
        hashMap.put("area", area);
        hashMap.put("order", order);
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new JsonCallback<TalentsBean>() {
            @Override
            public void onSuccess(TalentsBean talentsBean, Call call, Response response) {
                List<TalentsBean.DataBean> dataBeen = talentsBean.getData();
                mKProgressHUD.dismiss();
                mSwipeLayout.setLoading(false);
                if (dataBeen.isEmpty()) {
                    b = false;
                    LinearLayout footView = (LinearLayout) View.inflate(ECApplication.context, R.layout.item_foot, null);//得到尾部的布局
                    mTalentsListview.addFooterView(footView);
                } else {
                    for (TalentsBean.DataBean dataBean : dataBeen) {
                        mDataBeen.add(dataBean);
                    }
                    mAdapters.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mKProgressHUD.dismiss();
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        if (mAdapters != null) {
            mAdapters.setUid(mUserid);
        }
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @OnClick({R.id.hall_soushuo, R.id.messge_img,R.id.talents_hall_dengji, R.id.talents_hall_haoping, R.id.talents_hall_shouru, R.id.talents_hall_shaixuan, R.id.chongzhi_button, R.id.queding_button, R.id.diqu_button})
    public void onClick(View view) {
        switch (view.getId()) {
            //等级
            case R.id.talents_hall_dengji:
                count = 1;
                mDataBeen.clear();
                DjFlag = false;
                mKProgressHUD.show();
                mHallHaopingImage.setImageResource(R.drawable.doublea);
                mHallHaopingText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mHallShouruImage.setImageResource(R.drawable.doublea);
                mHallShouruText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mHallDengjiText.setTextColor(CommonUtil.getColor(R.color.jue));
                if (flag == true) {
                    mHallDengjiIamge.setImageResource(R.drawable.dou_down);
                    alents("", "1", "", "", "", "", "", "", "2");
                    count1 = 0;
                    flag1 = true;
                    flag = false;
                    KLog.e("dou_up1");
                } else {
                    if (count1 == 0) {
                        mHallDengjiIamge.setImageResource(R.drawable.dou_up);
                        alents("", "1", "", "", "", "", "", "", "1");
                        count1++;
                        KLog.e("dou_up");
                    } else {
                        mHallDengjiIamge.setImageResource(R.drawable.dou_down);
                        alents("", "1", "", "", "", "", "", "", "2");
                        count1 = 0;
                        flag1 = true;
                        KLog.e("dou_up1");
                  /*  mHallDengjiIamge.setImageResource(R.drawable.dou_down);
                    alents("", "1", "", "", "", "", "", "", "2");*/
                    }
                }
                count2 = 0;
                count3 = 0;
                break;
            //好评
            case R.id.talents_hall_haoping:
                count = 1;
                mDataBeen.clear();
                HpFlag = false;
                flag1 = false;
                flag = false;
                mKProgressHUD.show();
                mHallDengjiText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mHallDengjiIamge.setImageResource(R.drawable.doublea);

                mHallShouruText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mHallShouruImage.setImageResource(R.drawable.doublea);

                mHallHaopingText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mHallHaopingText.setTextColor(CommonUtil.getColor(R.color.jue));
                if (count2 == 0) {
                    mHallHaopingImage.setImageResource(R.drawable.dou_up);
                    alents("", "1", "", "", "", "", "", "", "3");
                    count2++;
                } else {
                    mHallHaopingImage.setImageResource(R.drawable.dou_down);
                    alents("", "1", "", "", "", "", "", "", "4");
                    count2 = 0;
                }
                count1 = 0;
                count3 = 0;
                break;
            //收入
            case R.id.talents_hall_shouru:
                count = 1;
                mDataBeen.clear();
                SrFlag = false;
                flag1 = false;
                flag = false;
                mKProgressHUD.show();
                mHallDengjiText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mHallDengjiIamge.setImageResource(R.drawable.doublea);
                mHallHaopingText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mHallHaopingImage.setImageResource(R.drawable.doublea);
                mHallShouruText.setTextColor(CommonUtil.getColor(R.color.jue));
                if (count3 == 0) {
                    mHallShouruImage.setImageResource(R.drawable.dou_up);
                    alents("", "1", "", "", "", "", "", "", "5");
                    count3++;
                } else {
                    mHallShouruImage.setImageResource(R.drawable.dou_down);
                    alents("", "1", "", "", "", "", "", "", "6");
                    count3 = 0;
                }
                count1 = 0;
                count2 = 0;
                break;
            //筛选
            case R.id.talents_hall_shaixuan:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                mFlag1 = false;
                break;
            case R.id.diqu_button:
                //把你的选择地区写在fragment里面
                ShengFragment fragment = new ShengFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_right_entry, R.anim.hold, R.anim.hold, R.anim.slide_right_exit);
                fragmentTransaction.add(R.id.goods_soppingg, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            //重置
            case R.id.chongzhi_button:
                mDrawerLayout.closeDrawers();
                mFlag1 = true;
                break;
            //确定
            case R.id.queding_button:
                //  mDrawerLayout.closeDrawers();
                mKProgressHUD.show();
                if (mShengid != null) {
                    KLog.e("总的id", mShengid);
                    if (mShengid.indexOf(",") != -1) {
                        String[] split = mShengid.split(",");
                        for (int i = 0; i < split.length; i++) {

                        }
                        mShengId = split[0];
                        KLog.e("省id", mShengId);
                        mShiId = split[1];
                        KLog.e("市id", mShiId);
                        mJiedaoId = split[2];
                        KLog.e("街道id", mJiedaoId);
                    } else {
                        mShengId = mShengid;
                    }
                }
                if (mMlist == null || mMlist.equals("")) {
                    if (mMlist1 == null || mMlist1.equals("")) {
                        if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                            Toast.makeText(mContext, "请选择类型", Toast.LENGTH_SHORT).show();
                        } else {
                            mDrawerLayout.closeDrawers();
                        }
                    } else {
                        if (mMlist == null || mMlist.equals("")) {
                            if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                alents("", "1", "", "", mMlist1, "", "", "", "");
                                mDrawerLayout.closeDrawers();
                            } else {
                                if (mJiedaoId.equals("0")) {
                                    mJiedaoId = "";
                                }
                                if (mShiId.equals("0")) {
                                    mShiId = "";
                                }
                                alents("", "1", "", "", mMlist1, mShengId, mShiId, mJiedaoId, "");
                                mDrawerLayout.closeDrawers();
                            }
                        } else {
                            if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                alents("", "1", mMlist, "", mMlist1, "", "", "", "");
                                mDrawerLayout.closeDrawers();
                            } else {
                                if (mJiedaoId.equals("0")) {
                                    mJiedaoId = "";
                                }
                                if (mShiId.equals("0")) {
                                    mShiId = "";
                                }
                                alents("", "1", mMlist, "", mMlist1, mShengId, mShiId, mJiedaoId, "");
                                mDrawerLayout.closeDrawers();
                            }
                        }
                    }
                } else {
                    if (mMlist1 == null || mMlist1.equals("")) {
                        if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                            alents("", "1", mMlist, "", "", "", "", "", "");
                            mDrawerLayout.closeDrawers();
                        } else {
                            if (mJiedaoId.equals("0")) {
                                mJiedaoId = "";
                            }
                            if (mShiId.equals("0")) {
                                mShiId = "";
                            }
                            alents("", "1", mMlist, "", "", mShengId, mShiId, mJiedaoId, "");
                            mDrawerLayout.closeDrawers();
                        }
                    } else {
                        if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                            alents("", "1", mMlist, "", mMlist1, "", "", "", "");
                            mDrawerLayout.closeDrawers();
                        } else {
                            if (mJiedaoId.equals("0")) {
                                mJiedaoId = "";
                            }
                            if (mShiId.equals("0")) {
                                mShiId = "";
                            }
                            alents("", "1", mMlist, "", mMlist1, mShengId, mShiId, mJiedaoId, "");
                            mDrawerLayout.closeDrawers();
                        }
                    }
                }
                KLog.e("mMlist", mMlist);
                KLog.e("mMlist1", mMlist1);

                break;
            //搜索
            case R.id.hall_soushuo:
         /*       mTalentsHallSoushuo.setVisibility(View.VISIBLE);
                mHallBiaozhi.setVisibility(View.GONE);
                mLoginEdtPassWord.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String result = s.toString();
                        alents(result, "1", "", "", "", "", "", "", "1");
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });*/
                Intent intent = new Intent(this,SearchInfoActivity.class);
                intent.putExtra("state",mIntExtra);
                startActivity(intent);
                break;
            //我的消息
            case R.id.messge_img:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mFlag1 == true) {
                finish();
            } else {
                mDrawerLayout.closeDrawers();
                mFlag1 = true;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void call(Bundle arg) {
        mDiqu = arg.get("name");
        //省市id
        mShengid = (String) SharedPrefsUtil.get(ECApplication.context, "shengid", "");
        KLog.e("ididi", mShengid);
        //省市名称
        mShengname = (String) SharedPrefsUtil.get(ECApplication.context, "shengname", "");
        if (mShengid != null && !mShengid.equals("")) {
            mDiquButton.setText(mShengname + "");
        }
        //全部id
        if (mDiqu != null) {
        }
        Object diqu1 = arg.get("name1");
        if (diqu1 != null) {
            mDiquButton.setText(diqu1 + "");
        }
    }

    @OnClick(R.id.cx_onclick)
    public void onClick() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.cx_popwindow, null));
        dialog.show();
        ImageView viewById = (ImageView) dialog.findViewById(R.id.cx_image);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
