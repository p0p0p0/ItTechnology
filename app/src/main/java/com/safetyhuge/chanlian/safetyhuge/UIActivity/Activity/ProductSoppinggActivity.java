package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsDaoBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.HomeInfoBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.RegionInfoFragment.ShengFragment;
import com.safetyhuge.chanlian.safetyhuge.adapter.GoodsDaoAdapter;
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

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

public class ProductSoppinggActivity extends HBaseAct implements CallBack {

    @Bind(R.id.bazaar_listView)
    MyListView mBazaarListView;
    /* @Bind(R.id.text_dl)
     TextView mTextDl;*/
    @Bind(R.id.soppingg_image_2)
    ImageView mSoppinggImage2;
    @Bind(R.id.soppingg_2)
    LinearLayout mSoppingg2;
    @Bind(R.id.soppingg_text)
    TextView mSoppinggText;
    @Bind(R.id.soppingg)
    LinearLayout mSoppingg;
    @Bind(R.id.soppingg_text1)
    TextView mSoppinggText1;
    @Bind(R.id.soppingg_text2)
    TextView mSoppinggText2;
    @Bind(R.id.Login_Edt_PassWord)
    EditText mLoginEdtPassWord;
    @Bind(R.id.goods_soppingg)
    FrameLayout mGoodsSoppingg;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.goods_shaixuan)
    LinearLayout mGoodsShaixuan;
    @Bind(R.id.id_flowlayout_goods1)
    TagFlowLayout mIdFlowlayoutGoods1;
    @Bind(R.id.id_flowlayout_goods2)
    TagFlowLayout mIdFlowlayoutGoods2;
    @Bind(R.id.id_flowlayout_goods3)
    TagFlowLayout mIdFlowlayoutGoods3;
    @Bind(R.id.diqu_button)
    TextView mDiquButton;
    @Bind(R.id.zhongzhi_button)
    Button mZhongzhiButton;
    @Bind(R.id.queding_button)
    Button mQuedingButton;
    /* @Bind(R.id.scrollView_goods_layout)
     ScrollView mScrollViewGoodsLayout;*/
    @Bind(R.id.text_title)
    TextView mTextTitle;
    @Bind(R.id.messge_img)
    ImageView mMessgeImg;
    @Bind(R.id.shanxuan)
    LinearLayout mShanxuan;
    @Bind(R.id.tv_img_back)
    ImageView mTvImgBack;
    @Bind(R.id.goods_soushuo)
    LinearLayout mGoodsSoushuo;
    @Bind(R.id.soppingg_image_1)
    ImageView mSoppinggImage1;
    @Bind(R.id.soppingg_1)
    LinearLayout mSoppingg1;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.sopping_swlayout)
    SwipeRefreshView mSoppingSwlayout;
    int count = 1;
    @Bind(R.id.soppingg_sousuo)
    TextView mSoppinggSousuo;
    private ArrayList<String> mPplistName, mPplistId, mFllistName, mFllistId;
    private Object mDiqu;
    private Context mContext;
    KProgressHUD mKProgressHUD;
    private HashMap<String, String> mHashMap;
    private ArrayList<String> mListid;
    private ArrayList<String> mListname;
    private Intent mIntent;
    private int mFlag;
    private String mIndus_pid;
    private int mPosition, mPosition1, mPosition2;
    private String mIndus_id;
    private String mUserid;
    private int count1 = 0;
    private int count2 = 0;
    private GoodsDaoAdapter mGoodsDaoAdapter;
    private String mText;
    private List<GoodsDaoBean.DataBean> mDataBeen;
    private String mYjId;
    private String mEjId;
    private String mPpId;
    private TagAdapter<String> mAdapter;
    private boolean mFlag1 = true;
    private boolean mB;
    private boolean mB1;
    private ArrayList<GoodsDaoBean.DataBean> mMErji;
    private String mMShengid;
    private String mShengid;
    private String mShengname;
    private String mShengId;
    private String mShiId;
    private String mJiedaoId;
    boolean bl = true;
    boolean flag = true;
    String soushuo = new String();
    private TagAdapter mTagAdapter;
    private TagAdapter mTagAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(ProductSoppinggActivity.this);
        super.onCreate(savedInstanceState);
        mContext = ProductSoppinggActivity.this;
        setContentView(R.layout.activity_product_soppingg);
        mIntent = getIntent();
        //用户id
        mUserid = (String) get(ProductSoppinggActivity.this, "UserUid", "1");
        //标记
        mFlag = mIntent.getIntExtra("flag", -1);
        //大分类id
        mIndus_pid = mIntent.getStringExtra("indus_pid");
        //小分类id
        mIndus_id = mIntent.getStringExtra("indus_id");
        mKProgressHUD = KProgressHUD.create(ProductSoppinggActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        ButterKnife.bind(this);
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            elseView();
        }
        flag(mFlag);
       /* mLoginEdtPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mText = s.toString();
                KLog.e("mText", mText);
                if (mText == "") {
                    mBazaarListView.setAdapter(new GoodsDaoAdapter(ProductSoppinggActivity.this, mDataBeen, 0));
                } else {
                    List<GoodsDaoBean.DataBean> mMTaskListData2 = new ArrayList<GoodsDaoBean.DataBean>();
                    if (mDataBeen != null) {
                        for (GoodsDaoBean.DataBean dataBean : mDataBeen) {
                            if (dataBean.getTitle().indexOf(mText) != -1) {
                                KLog.e("getTask_status");
                                mMTaskListData2.add(dataBean);
                            }
                        }
                        if (!mMTaskListData2.isEmpty()) {
                            mBazaarListView.setAdapter(new GoodsDaoAdapter(ProductSoppinggActivity.this, mMTaskListData2, 0));
                        } else {
                            Toast.makeText(ProductSoppinggActivity.this, "暂无查询结果", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        InitData();
        KLog.e("mYjId", mYjId);
    }

    public void back_text_view(View view) {
        exitAct();
    }

    public void flag(int f) {
        switch (f) {
            case 1:
                mTextTitle.setText("产品导购");
                NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "","");
                break;
            case 2:
                mTextTitle.setText("询价详细");
                mShanxuan.setVisibility(View.GONE);
                mMessgeImg.setVisibility(View.GONE);
                NetWork(mUserid, "1", "6", "", "", mIndus_pid, mIndus_id, "", "", "", "","");
                break;
            case 3:
                mTextTitle.setText("促销专区");
                NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "2","");
                break;
            case 4:
                mTextTitle.setText("二手市场");
                NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "3","");
                break;
            case 5:
                mTextTitle.setText("设备租赁");
                NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "6","");
                break;
        }
    }

    /**
     * @param uid       用户id
     * @param page      分页
     * @param order     人气
     * @param brand_id  品牌id
     * @param indus_pid 大分类id
     * @param indus_id  小分类id
     * @param province  省
     * @param city      市
     * @param area      镇
     * @param sales     产品
     */
    public void NetWork(String uid, String page, String mid,
                        String order, String brand_id,
                        String indus_pid, String indus_id,
                        String province, String city,
                        String area, String sales,String keyword
    ) {
        mHashMap = new HashMap<>();
        mHashMap.put("action", "GetGoodsInfo");
        //用户id
        mHashMap.put("uid", uid);
        mHashMap.put("mid", mid);
        //分页
        mHashMap.put("page", page);
        //人气
        mHashMap.put("order", order);
        //品牌id
        mHashMap.put("brand_id", brand_id);
        //大分类id
        mHashMap.put("indus_pid", indus_pid);
        //小分类id
        mHashMap.put("indus_id", indus_id);
        //省
        mHashMap.put("province", province);
        //市
        mHashMap.put("city", city);
        //镇
        mHashMap.put("area", area);
        //产品
        mHashMap.put("sales", sales);
        //关键字
        mHashMap.put("keyword", keyword);
        KLog.e("hashmap", mHashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(mHashMap).execute(new JsonCallback<GoodsDaoBean>() {
            @Override
            public void onSuccess(GoodsDaoBean goodsDaoBean, Call call, Response response) {
                KLog.e("请求成功");
                mSoppingSwlayout.setRefreshing(false);
                mB = true;
                mDataBeen = goodsDaoBean.getData();
                mKProgressHUD.dismiss();
                if (!mDataBeen.isEmpty()) {
                    mQuanbuImage.setVisibility(View.GONE);
                    mSoppingSwlayout.setVisibility(View.VISIBLE);
                    mGoodsDaoAdapter = new GoodsDaoAdapter(ProductSoppinggActivity.this, mDataBeen, 0);
                    mGoodsDaoAdapter.notifyDataSetChanged();
                    mBazaarListView.setAdapter(mGoodsDaoAdapter);
                } else {
                    mQuanbuImage.setVisibility(View.VISIBLE);
                    mSoppingSwlayout.setVisibility(View.GONE);
                }

            }
        });

    }

    public void NetWork1(String uid, String page, String mid,
                         String order, String brand_id,
                         String indus_pid, String indus_id,
                         String province, String city,
                         String area, String sales
    ) {
        mHashMap = new HashMap<>();
        mHashMap.put("action", "GetGoodsInfo");
        //用户id
        mHashMap.put("uid", uid);
        mHashMap.put("mid", mid);
        //分页
        mHashMap.put("page", page);
        //人气
        mHashMap.put("order", order);
        //品牌id
        mHashMap.put("brand_id", brand_id);
        //大分类id
        mHashMap.put("indus_pid", indus_pid);
        //小分类id
        mHashMap.put("indus_id", indus_id);
        //省
        mHashMap.put("province", province);
        //市
        mHashMap.put("city", city);
        //镇
        mHashMap.put("area", area);
        //产品
        mHashMap.put("sales", sales);
        KLog.e("hashmap", mHashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(mHashMap).execute(new JsonCallback<GoodsDaoBean>() {
            @Override
            public void onSuccess(GoodsDaoBean goodsDaoBean, Call call, Response response) {
                mSoppingSwlayout.setLoading(false);
                List<GoodsDaoBean.DataBean> data = goodsDaoBean.getData();
                if (data.isEmpty()) {
                    flag = false;
                    mSoppingSwlayout.setLoading(false);
                    //展示
                    LinearLayout footView = (LinearLayout) View.inflate(ProductSoppinggActivity.this, R.layout.item_foot, null);//得到尾部的布局
                    mBazaarListView.addFooterView(footView);
                    Toast.makeText(ProductSoppinggActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                } else {
                    for (GoodsDaoBean.DataBean dataBean : data) {
                        mDataBeen.add(dataBean);
                    }
                    mGoodsDaoAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.soppingg_sousuo,R.id.messge_img, R.id.soppingg_1, R.id.soppingg_2, R.id.soppingg, R.id.goods_shaixuan, R.id.diqu_button, R.id.zhongzhi_button, R.id.queding_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.soppingg_1:
                bl = false;
                count = 1;
                mDataBeen.clear();
                mKProgressHUD.show();
                mSoppinggText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggText2.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggText1.setTextColor(CommonUtil.getColor(R.color.jue));
                mSoppinggImage2.setImageResource(R.drawable.doublea);
                switch (mFlag) {
                    case 1:
                        //产品导购
                        if (count1 == 0) {
                            mSoppinggImage1.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "1", "", "", "", "", "", "", "","");
                            count1++;
                        } else {
                            mSoppinggImage1.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "2", "", "", "", "", "", "", "","");
                            count1 = 0;
                        }
                        break;
                    case 2:
                        //mTextDl.setText("询价详细");
                        if (count1 == 0) {
                            mSoppinggImage1.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "1", "", mIndus_pid, mIndus_id, "", "", "", "","");
                            count1++;
                        } else {
                            mSoppinggImage1.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "2", "", mIndus_pid, mIndus_id, "", "", "", "","");
                            count1 = 0;
                        }
                        break;
                    case 3:
                        //mTextDl.setText("促销专区");
                        if (count1 == 0) {
                            mSoppinggImage1.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "1", "", "", "", "", "", "", "2","");
                            count1++;
                        } else {
                            mSoppinggImage1.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "2", "", "", "", "", "", "", "2","");
                            count1 = 0;
                        }
                        break;
                    case 4:
                        //mTextDl.setText("二手市场");
                        if (count1 == 0) {
                            mSoppinggImage1.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "1", "", "", "", "", "", "", "3","");
                            count1++;
                        } else {
                            mSoppinggImage1.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "2", "", "", "", "", "", "", "3","");
                            count1 = 0;
                        }
                        break;
                    case 5:
                        //mTextDl.setText("设备租赁");
                        if (count1 == 0) {
                            mSoppinggImage1.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "1", "", "", "", "", "", "", "6","");
                            count1++;
                        } else {
                            mSoppinggImage1.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "2", "", "", "", "", "", "", "6","");
                            count1 = 0;
                        }
                        break;
                }
                break;
            case R.id.soppingg_2:
                bl = false;
                count = 1;
                mDataBeen.clear();
                mSoppinggText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggText2.setTextColor(CommonUtil.getColor(R.color.jue));
                mSoppinggText1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mKProgressHUD.show();
                mSoppinggImage1.setImageResource(R.drawable.doublea);
                switch (mFlag) {
                    case 1:
                        if (count2 == 0) {
                            mSoppinggImage2.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "4", "", "", "", "", "", "", "","");
                            count2++;
                        } else {
                            mSoppinggImage2.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "3", "", "", "", "", "", "", "","");
                            count2 = 0;
                        }
                        break;
                    case 2:
                        if (count2 == 0) {
                            mSoppinggImage2.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "4", "", mIndus_pid, mIndus_id, "", "", "", "","");
                            count2++;
                        } else {
                            mSoppinggImage2.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "3", "", mIndus_pid, mIndus_id, "", "", "", "","");
                            count2 = 0;
                        }
                        break;
                    case 3:
                        if (count2 == 0) {
                            mSoppinggImage2.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "4", "", "", "", "", "", "", "2","");
                            count2++;
                        } else {
                            mSoppinggImage2.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "3", "", "", "", "", "", "", "2","");
                            count2 = 0;
                        }
                        break;
                    case 4:
                        if (count2 == 0) {
                            mSoppinggImage2.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "4", "", "", "", "", "", "", "3","");
                            count2++;
                        } else {
                            mSoppinggImage2.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "3", "", "", "", "", "", "", "3","");
                            count2 = 0;
                        }
                        break;
                    case 5:
                        if (count2 == 0) {
                            mSoppinggImage2.setImageResource(R.drawable.dou_down);
                            NetWork(mUserid, "1", "6", "4", "", "", "", "", "", "", "6","");
                            count2++;
                        } else {
                            mSoppinggImage2.setImageResource(R.drawable.dou_up);
                            NetWork(mUserid, "1", "6", "3", "", "", "", "", "", "", "6","");
                            count2 = 0;
                        }
                        break;
                }
                break;
            case R.id.soppingg:
                bl = false;
                count = 1;
                mDataBeen.clear();
                mSoppinggText.setTextColor(CommonUtil.getColor(R.color.jue));
                mSoppinggText2.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggText1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggImage2.setImageResource(R.drawable.doublea);
                mSoppinggImage1.setImageResource(R.drawable.doublea);
                mKProgressHUD.show();
                switch (mFlag) {
                    case 1:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "","");
                        break;
                    case 2:
                        NetWork(mUserid, "1", "6", "", "", mIndus_pid, mIndus_id, "", "", "", "","");
                        break;
                    case 3:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "2","");

                        break;
                    case 4:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "3","");
                        break;
                    case 5:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "6","");
                        break;
                }
                break;
            //筛选
            case R.id.goods_shaixuan:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                // mFlag1 = false;
                break;
            //地区
            case R.id.diqu_button:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                //把你的选择地区写在fragment里面
                ShengFragment fragment = new ShengFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_right_entry, R.anim.hold, R.anim.hold, R.anim.slide_right_exit);
                fragmentTransaction.add(R.id.goods_soppingg, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            //重置
            case R.id.zhongzhi_button:
                mYjId = "";   mEjId = "";  mPpId = "";
                mTagAdapter1.notifyDataChanged();
                mTagAdapter.notifyDataChanged();
                mAdapter.notifyDataChanged();
                mDrawerLayout.closeDrawers();
                mFlag1 = true;
                break;
            //确定
            case R.id.queding_button:
                mDrawerLayout.closeDrawers();
                KLog.e("一级", mYjId);
                KLog.e("二级", mEjId);
                KLog.e("品牌", mPpId);
                KLog.e("地区", mDiqu);
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
                if (mYjId == null || mYjId.equals("")) {
                    if (mEjId == null || mEjId.equals("")) {
                        if (mPpId == null || mPpId.equals("")) {
                            if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                            } else {
                                if (mYjId == null || mYjId.equals("")) {
                                    if (mEjId == null || mEjId.equals("")) {
                                        if (mPpId == null || mPpId.equals("")) {
                                            if (mJiedaoId.equals("0")) {
                                                mJiedaoId = "";
                                            }
                                            if (mShiId.equals("0")) {
                                                mShiId = "";
                                            }
                                            NetWork(mUserid, "1", "6", "", "", "", "", mShengId, mShiId, mJiedaoId, "","");
                                        } else {
                                            if (mJiedaoId.equals("0")) {
                                                mJiedaoId = "";
                                            }
                                            if (mShiId.equals("0")) {
                                                mShiId = "";
                                            }
                                            NetWork(mUserid, "1", "6", "", "", mEjId, mPpId, mShengId, mShiId, mJiedaoId, mYjId,"");
                                        }
                                    } else {
                                        if (mJiedaoId.equals("0")) {
                                            mJiedaoId = "";
                                        }
                                        if (mShiId.equals("0")) {
                                            mShiId = "";
                                        }
                                        NetWork(mUserid, "1", "6", "", "", mEjId, "", mShengId, mShiId, mJiedaoId, mYjId,"");
                                    }
                                } else {
                                    if (mJiedaoId.equals("0")) {
                                        mJiedaoId = "";
                                    }
                                    if (mShiId.equals("0")) {
                                        mShiId = "";
                                    }
                                    NetWork(mUserid, "1", "6", "", "", mEjId, "", mShengId, mShiId, mJiedaoId, mYjId,"");
                                }
                            }
                        } else {
                            if (mYjId == null || mYjId.equals("")) {
                                if (mEjId == null || mEjId.equals("")) {
                                    if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                        NetWork(mUserid, "1", "6", "", mPpId, "", "", "", "", "", "","");
                                    } else {
                                        if (mJiedaoId.equals("0")) {
                                            mJiedaoId = "";
                                        }
                                        if (mShiId.equals("0")) {
                                            mShiId = "";
                                        }
                                        NetWork(mUserid, "1", "6", "", "", mPpId, "", mShengId, mShiId, mJiedaoId, "","");
                                    }
                                    KLog.e("mppid", mPpId);
                                } else {
                                    KLog.e("mppid", mPpId);
                                    NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", "", "", "", mYjId,"");
                                }
                            } else {
                                KLog.e("mMShengid", mMShengid);
                                NetWork(mUserid, "1", "6", "", "", mEjId, "", "", "", "", mYjId,"");
                            }
                        }
                    } else {
                        if (mYjId == null || mYjId.equals("")) {
                            if (mPpId == null || mPpId.equals("")) {
                                if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                    // NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", "", "", "", mYjId);
                                    // NetWork(mUserid, "1", "6", "", "", mEjId, "", "", "", "", "");
                                    NetWork(mUserid, "1", "6", "", mEjId, "", "", "", "", "", "","");
                                } else {
                                    KLog.e("mShengId+++", mShengId);
                                    KLog.e("mShiId+++", mShiId);
                                    KLog.e("mJiedaoId+++", mJiedaoId);
                                    if (mJiedaoId.equals("0")) {
                                        mJiedaoId = "";
                                    }
                                    if (mShiId.equals("0")) {
                                        mShiId = "";
                                    }
                                    NetWork(mUserid, "1", "6", "", "", mEjId, "", mShengId, mShiId, mJiedaoId, "","");
                                    // NetWork(mUserid, "1", "6", "", "", mEjId, "", mMShengid, "", "", "");
                                }
                                KLog.e("mMShengid", mMShengid);
                            } else {
                                if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                    NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", "", "", "", mYjId,"");
                                    // NetWork(mUserid, "1", "6", "", "", mEjId, "", "", "", "", "");
                                } else {
                                    if (mJiedaoId.equals("0")) {
                                        mJiedaoId = "";
                                    }
                                    if (mShiId.equals("0")) {
                                        mShiId = "";
                                    }
                                    NetWork(mUserid, "1", "6", "", "", mEjId, "", mShengId, mShiId, mJiedaoId, "","");
                                }
                               /* KLog.e("mppid", mPpId);
                                NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", "", "", "", mYjId);*/
                            }
                        } else {
                            KLog.e("mMShengid", mMShengid);
                            NetWork(mUserid, "1", "6", "", "", mEjId, "", "", "", "", mYjId,"");
                        }
                    }
                } else {
                    if (mEjId == null || mEjId.equals("")) {
                        if (mPpId == null || mPpId.equals("")) {
                            if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                KLog.e("mMShengid", mMShengid);
                                NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", mYjId,"");
                            } else {
                                if (mJiedaoId.equals("0")) {
                                    mJiedaoId = "";
                                }
                                if (mShiId.equals("0")) {
                                    mShiId = "";
                                }
                                NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", mShengId, mShiId, mJiedaoId, mYjId,"");
                            }
                            KLog.e("mMShengid", mMShengid);
                        } else {
                            KLog.e("mMShengid", mMShengid);
                            //地址
                            //mMShengid
                            if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                KLog.e("mMShengid", mMShengid);
                                NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", "", "", "", mYjId,"");
                            } else {
                                KLog.e("mMShengid", mMShengid);
                                if (mJiedaoId.equals("0")) {
                                    mJiedaoId = "";
                                }
                                if (mShiId.equals("0")) {
                                    mShiId = "";
                                }
                                NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", mShengId, mShiId, mJiedaoId, mYjId,"");
                            }
                        }
                    } else {
                        if (mPpId == null || mPpId.equals("")) {
                            KLog.e("mMShengid", mMShengid);
                            if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                NetWork(mUserid, "1", "6", "", "", mEjId, "", "", "", "", mYjId,"");
                            } else {
                                if (mJiedaoId.equals("0")) {
                                    mJiedaoId = "";
                                }
                                if (mShiId.equals("0")) {
                                    mShiId = "";
                                }
                                NetWork(mUserid, "1", "6", "", "", mEjId, "", mMShengid, mShiId, mJiedaoId, mYjId,"");
                            }
                        } else {
                            if (mShengId == null || mShengId.equals("") || mShiId == null || mShiId.equals("") || mJiedaoId == null || mJiedaoId.equals("")) {
                                KLog.e("走了1111111111111");
                                NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", "", "", "", mYjId,"");
                            } else {
                                KLog.e("mShengId+++", mShengId);
                                KLog.e("mShiId+++", mShiId);
                                KLog.e("mJiedaoId+++", mJiedaoId);
                                if (mJiedaoId.equals("0")) {
                                    mJiedaoId = "";
                                }
                                if (mShiId.equals("0")) {
                                    mShiId = "";
                                }
                                NetWork(mUserid, "1", "6", "", mPpId, mEjId, "", mShengId, mShiId, mJiedaoId, mYjId,"");
                            }
                        }
                    }
                }
                break;
            case R.id.messge_img:
                Intent intent = new Intent(this,SearchInfoActivity.class);
                intent.putExtra("state",mFlag);
                startActivity(intent);
            /*SearchFragment mSearchFragment = SearchFragment.newInstance();
             mSearchFragment.show(getSupportFragmentManager(),SearchFragment.TAG);
         mSearchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                soushuo = keyword;
                //这里处理逻辑
                //    Toast.makeText(SearchInfoActivity.this, keyword, Toast.LENGTH_SHORT).show();
              switch (mFlag) {
                    case 1:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "",keyword);
                        break;
                    case 2:
                        NetWork(mUserid, "1", "6", "", "", mIndus_pid, mIndus_id, "", "", "", "",keyword);
                        break;
                    case 3:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "2",keyword);
                        break;
                    case 4:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "3",keyword);
                        break;
                    case 5:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "6",keyword);
                        break;
                }
            }
        });*/
                break;
                /*   mTextTitle.setVisibility(View.GONE);
                mMessgeImg.setVisibility(View.INVISIBLE);
                mGoodsSoushuo.setVisibility(View.VISIBLE);*/
          /*      //搜索
            case R.id.soppingg_sousuo:
                String s = mLoginEdtPassWord.getText().toString();
                switch (mFlag) {
                    case 1:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "",s);
                        break;
                    case 2:
                        NetWork(mUserid, "1", "6", "", "", mIndus_pid, mIndus_id, "", "", "", "",s);
                        break;
                    case 3:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "2",s);
                        break;
                    case 4:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "3",s);
                        break;
                    case 5:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "6",s);
                        break;
                }
                break;*/

        }
    }

    //初始化数据
    public void InitData() {
        NetData();
    }

    //请求网络获取数据
    public void NetData() {
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params("action", "GetGoodsTextInfo").execute(new JsonCallback<HomeInfoBean>() {
            @Override
            public void onSuccess(HomeInfoBean homeInfoBean, Call call, Response response) {
                HomeInfoBean.DataBean data = homeInfoBean.getData();
                //品牌
                List<HomeInfoBean.DataBean.BrandBean> brand = data.getBrand();
                //分类
                List<HomeInfoBean.DataBean.IndusBean> indus = data.getIndus();
                //分类
                mFllistName = new ArrayList<String>();
                mFllistId = new ArrayList<String>();
                for (HomeInfoBean.DataBean.IndusBean indu : indus) {
                    mFllistName.add(indu.getPname());
                    mFllistId.add(indu.getPid());
                }
                //品牌 名称 id
                mPplistName = new ArrayList<String>();
                mPplistId = new ArrayList<String>();
                for (HomeInfoBean.DataBean.BrandBean brandBean : brand) {
                    mPplistName.add(brandBean.getBname());
                    mPplistId.add(brandBean.getBid());
                }
                InitView();
            }
        });
    }

    //初始化界面
    public void InitView() {
        //下拉刷新
        mSoppingSwlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = 1;
                mDataBeen.clear();
                switch (mFlag) {
                    case 1:
                        if (bl == true) {
                            NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "","");
                        } else {
                            if (count1 == 0) {
                                NetWork(mUserid, count + "", "6", "2", "", "", "", "", "", "", "","");
                            } else {
                                NetWork(mUserid, count + "", "6", "1", "", "", "", "", "", "", "","");
                            }
                        }
                        break;
                    case 2:
                        if (bl == true) {
                            NetWork(mUserid, "1", "6", "", "", mIndus_pid, mIndus_id, "", "", "", "","");
                        } else {
                            if (count1 == 0) {
                                NetWork(mUserid, "1", "6", "2", "", mIndus_pid, mIndus_id, "", "", "", "","");
                            } else {
                                NetWork(mUserid, "1", "6", "1", "", mIndus_pid, mIndus_id, "", "", "", "","");
                            }
                        }
                        break;
                    case 3:
                        if (bl == true) {
                            NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "2","");
                        } else {
                            if (count1 == 0) {
                                NetWork(mUserid, "1", "6", "2", "", "", "", "", "", "", "2","");
                            } else {
                                NetWork(mUserid, "1", "6", "1", "", "", "", "", "", "", "2","");
                            }
                        }

                        break;
                    case 4:
                        if (bl == true) {
                            NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "3","");
                        } else {
                            if (count1 == 0) {
                                NetWork(mUserid, "1", "6", "2", "", "", "", "", "", "", "3","");
                            } else {
                                NetWork(mUserid, "1", "6", "1", "", "", "", "", "", "", "3","");
                            }
                        }
                        break;
                    case 5:
                        if (bl == true) {
                            NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "6","");
                        } else {
                            if (count1 == 0) {
                                NetWork(mUserid, "1", "6", "2", "", "", "", "", "", "", "6","");
                            } else {
                                NetWork(mUserid, "1", "6", "1", "", "", "", "", "", "", "6","");
                            }
                        }
                        break;
                }
            }
        });
        //加载更多
        mSoppingSwlayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                count++;
                KLog.e("count", count + "");
                if (flag == true) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 加载完数据设置为不刷新状态，将下拉进度收起来
                            switch (mFlag) {
                                case 1:
                                    if (bl == true) {
                                        NetWork1(mUserid, count + "", "6", "", "", "", "", "", "", "", "");
                                    } else {
                                        if (count1 == 0) {
                                            NetWork1(mUserid, count + "", "6", "2", "", "", "", "", "", "", "");
                                        } else {
                                            NetWork1(mUserid, count + "", "6", "1", "", "", "", "", "", "", "");
                                        }
                                    }
                                    break;
                                case 2:
                                    NetWork1(mUserid, count + "", "6", "", "", mIndus_pid, mIndus_id, "", "", "", "");
                                    break;
                                case 3:
                                    NetWork1(mUserid, count + "", "6", "", "", "", "", "", "", "", "2");
                                    break;
                                case 4:
                                    NetWork1(mUserid, count + "", "6", "", "", "", "", "", "", "", "3");
                                    break;
                                case 5:
                                    NetWork1(mUserid, count + "", "6", "", "", "", "", "", "", "", "6");
                                    break;
                            }
                        }
                    }, 2002);
                } else {
                    mSoppingSwlayout.setLoading(false);
                }

            }
        });
        //一级分类名称
        mListname = new ArrayList<>();
        mListname.add("普通产品");
        mListname.add("促销产品");
        mListname.add("二手产品");
        mListname.add("清积压");
        mListname.add("技术转让");
        mListname.add("设备租借");
        //一级分类id
        mListid = new ArrayList<>();
        mListid.add("1");
        mListid.add("2");
        mListid.add("3");
        mListid.add("4");
        mListid.add("5");
        mListid.add("6");
        switch (mFlag) {
            case 1:
                shaixuan();
                if (mAdapter != null) {
                    mAdapter.setSelectedList(0);
                }
                mYjId = "1";
                break;
            case 2:
                shaixuan();
                if (mAdapter != null) {
                    mAdapter.setSelectedList(0);
                }
                mYjId = "1";
                break;
            case 3:
                shaixuan();
                if (mAdapter != null) {
                    mAdapter.setSelectedList(1);
                }
                mYjId = "2";
                break;

            case 4:
                shaixuan();
                if (mAdapter != null) {
                    mAdapter.setSelectedList(2);
                }
                mYjId = "3";
                break;

            case 5:
                shaixuan();
                if (mAdapter != null) {
                    mAdapter.setSelectedList(4);
                }
                mYjId = "6";
                break;
        }
    }

    private void shaixuan() {
        //一级分类
        mIdFlowlayoutGoods1.setAdapter(mAdapter = new TagAdapter(mListname) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ProductSoppinggActivity.this, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        });
        mIdFlowlayoutGoods1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
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
                    mYjId = mListid.get(mPosition);
                    KLog.e("mYjId", mYjId);
                } else {
                    mYjId = "";
                    //
                }
            }
        });
        mTagAdapter = new TagAdapter(mFllistName) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ProductSoppinggActivity.this, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        };
        //二级分类
        mIdFlowlayoutGoods2.setAdapter(mTagAdapter);
        mIdFlowlayoutGoods2.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
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
                    mEjId = mFllistId.get(mPosition1);
                    KLog.e("mEjId", mEjId);
                } else {
                    mEjId = "";
                }
            }
        });
        //品牌
        mTagAdapter1 = new TagAdapter(mPplistName) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ProductSoppinggActivity.this, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        };
        mIdFlowlayoutGoods3.setAdapter(mTagAdapter1);
        mIdFlowlayoutGoods3.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                Set<Integer> selectPosSet1 = selectPosSet;
                String s = selectPosSet1.toString();
                for (Integer integer : selectPosSet1) {
                    KLog.e("integer", integer);
                    Integer A = new Integer(integer);
                    mPosition2 = A.intValue();
                }
                if (s.length() > 2) {
                    mPpId = mPplistId.get(mPosition2);
                    KLog.e("mPpId", mPpId);
                } else {
                    mPpId = "";
                }
            }
        });
    }

    protected void elseView() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
            mDrawerLayout.setClipToPadding(false);
        }
    }

    @Override
    public void call(Bundle arg) {
        mDiqu = arg.get("name");
        //省市id
        mShengid = (String) SharedPrefsUtil.get(ProductSoppinggActivity.this, "shengid", "");
        KLog.e("ididi", mShengid);
        //省市名称
        mShengname = (String) SharedPrefsUtil.get(ProductSoppinggActivity.this, "shengname", "");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mFlag1 == true) {
                finish();
                SharedPrefsUtil.remove(ProductSoppinggActivity.this, "shengid");
                SharedPrefsUtil.remove(ProductSoppinggActivity.this, "shengname");
            } else {
                mDrawerLayout.closeDrawers();
                mFlag1 = true;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }

    @OnClick(R.id.soppingg_sousuo)
    public void onClick() {
    }
}
