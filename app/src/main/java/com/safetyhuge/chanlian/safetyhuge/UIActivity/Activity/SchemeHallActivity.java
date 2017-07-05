package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.SchemeBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.SchemeHallAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
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

public class SchemeHallActivity extends HBaseAct {

    @Bind(R.id.tv_img_back)
    ImageView mTvImgBack;
    @Bind(R.id.Login_Edt_PassWord)
    EditText mLoginEdtPassWord;
    @Bind(R.id.messge_img)
    ImageView mMessgeImg;
    @Bind(R.id.soppingg_text)
    TextView mSoppinggText;
    @Bind(R.id.soppingg)
    LinearLayout mSoppingg;
    @Bind(R.id.soppingg_text1)
    TextView mSoppinggText1;
    @Bind(R.id.soppingg_image_1)
    ImageView mSoppinggImage1;
    @Bind(R.id.soppingg_1)
    LinearLayout mSoppingg1;
    @Bind(R.id.soppingg_text2)
    TextView mSoppinggText2;
    @Bind(R.id.soppingg_image_2)
    ImageView mSoppinggImage2;
    @Bind(R.id.soppingg_2)
    LinearLayout mSoppingg2;
    @Bind(R.id.schemehall_listview)
    MyListView mSchemehallListview;
    @Bind(R.id.superlayout)
    SwipeRefreshView mSuperlayout;
    @Bind(R.id.id_flowlayout)
    TagFlowLayout mIdFlowlayout;
    @Bind(R.id.zhongzhi_button)
    Button mZhongzhiButton;
    @Bind(R.id.queding_button)
    Button mQuedingButton;
    @Bind(R.id.scheme_hall)
    FrameLayout mSchemeHall;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.scheme_shaixuan)
    LinearLayout mSchemeShaixuan;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Context mContext;
    KProgressHUD MKProgressHUD;
    private String mUserid;
    private HashMap<String, String> mHashMap;
    private int count1 = 0;
    private int count2 = 0;
    private String mText;
    private List<String> mList, mLists;
    private List<SchemeBean.DataBean> mDataBeen, mDataBeen1;
    private List<SchemeBean.DataBean> mMTaskListData2;
    int count = 2;
    private boolean mFlag1 = true;
    private int mPosition;
    private String mMlist;
    private SchemeHallAdapter mSchemeHallAdapter;
    private boolean b = true;
    private int mIntExtra;
    private TagAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_hall);
        ButterKnife.bind(this);
        mContext = SchemeHallActivity.this;  //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "1");
        MKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        Intent intent = getIntent();
        mIntExtra = intent.getIntExtra("flag", -1);
        scheme(mUserid, "1", "13", "", "", "", "", "", "", "", "","");
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            elseView();
        }
       /* mLoginEdtPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mText = s.toString();
                KLog.e("mText", mText);
                if (mText == "") {
                    mSchemehallListview.setAdapter(new SchemeHallAdapter(SchemeHallActivity.this, mDataBeen));
                } else {
                    mMTaskListData2 = new ArrayList<SchemeBean.DataBean>();
                    if (mDataBeen != null) {
                        for (SchemeBean.DataBean dataBean : mDataBeen) {
                            if (dataBean.getTitle().indexOf(mText) != -1) {
                                KLog.e("getTask_status");
                                mMTaskListData2.add(dataBean);
                            }
                        }
                        if (!mMTaskListData2.isEmpty()) {
                            mSchemehallListview.setAdapter(new SchemeHallAdapter(SchemeHallActivity.this, mMTaskListData2));
                        } else {
                            Toast.makeText(mContext, "暂无查询结果", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        InitView();
    }

    public void InitView() {
        mList = new ArrayList<>();
        mList.add("网络安全");
        mList.add("系统集成");
        mList.add("信息化安全");
        mList.add("物联网");
        mList.add("云存储");
        mList.add("云计算");
        mList.add("大数据分析");
        mList.add("安全相关服务");
        mLists = new ArrayList<>();
        mLists.add("2");
        mLists.add("3");
        mLists.add("121");
        mLists.add("160");
        mLists.add("192");
        mLists.add("201");
        mLists.add("211");
        mLists.add("218");

        mSuperlayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSuperlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSchemeHallAdapter.notifyDataSetChanged();
                scheme(mUserid, "1", "13", "", "", "", "", "", "", "", "","");
            }
        });
        mSuperlayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                if (b==true){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fenye(mUserid, "" + count++, "13", "", "", "", "", "", "", "", "");
                            mSuperlayout.setLoading(false);
                            // 加载完数据设置为不刷新状态，将下拉进度收起来
                        }
                    }, 1200);
                }else{
                    mSuperlayout.setLoading(false);
                }
            }
        });
        //设置流式布局数据
        mAdapter = new TagAdapter(mList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ECApplication.context, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        };
        mIdFlowlayout.setAdapter(/*new TagAdapter(mList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ECApplication.context, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        }*/
                mAdapter);
        mIdFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
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
    }

    //方案大厅
    public void scheme(String uid, String page, String mid,
                       String order, String brand_id,
                       String indus_pid, String indus_id,
                       String province, String city,
                       String area, String sales,String keyword) {
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
        //关键词
        mHashMap.put("keyword",keyword);
        KLog.e("hashmap", mHashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(mHashMap).execute(new JsonCallback<SchemeBean>() {
            @Override
            public void onSuccess(SchemeBean scheme, Call call, Response response) {
                MKProgressHUD.dismiss();
                mSuperlayout.setRefreshing(false);
                // mSuperlayout.setLoading(false);
                mDataBeen = scheme.getData();
                mSchemeHallAdapter = new SchemeHallAdapter(SchemeHallActivity.this, mDataBeen);
                mSchemehallListview.setAdapter(mSchemeHallAdapter);
            }
        });
    }

    //方案大厅
    public void fenye(String uid, String page, String mid,
                      String order, String brand_id,
                      String indus_pid, String indus_id,
                      String province, String city,
                      String area, String sales) {
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
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(mHashMap).execute(new JsonCallback<SchemeBean>() {
            @Override
            public void onSuccess(SchemeBean scheme, Call call, Response response) {
                MKProgressHUD.dismiss();
                mSuperlayout.setRefreshing(false);
                mDataBeen1 = scheme.getData();
                if (mDataBeen1.isEmpty()) {
                    b=false;
                    LinearLayout footView = (LinearLayout) View.inflate(ECApplication.context, R.layout.item_foot, null);//得到尾部的布局
                    mSchemehallListview.addFooterView(footView);
                    Toast.makeText(mContext, "没有数据了", Toast.LENGTH_SHORT).show();
                } else {
                    for (SchemeBean.DataBean dataBean : mDataBeen1) {
                        mDataBeen.add(dataBean);
                    }
                }
                mSchemeHallAdapter.notifyDataSetChanged();
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }
/*
    @OnClick({R.id.image_fabu, R.id.Login_Edt_PassWord})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_fabu:
                break;
            case R.id.Login_Edt_PassWord:
                break;
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @OnClick({R.id.messge_img,R.id.soppingg_1, R.id.soppingg_2, R.id.soppingg, R.id.scheme_shaixuan, R.id.zhongzhi_button, R.id.queding_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.soppingg_1:
                MKProgressHUD.show();
                mSoppinggText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggText2.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggText1.setTextColor(CommonUtil.getColor(R.color.jue));
                mSoppinggImage2.setImageResource(R.drawable.doublea);
                if (count1 == 0) {
                    mSoppinggImage1.setImageResource(R.drawable.dou_down);
                    scheme(mUserid, "1", "13", "1", "", "", "", "", "", "", "","");
                    count1++;
                } else {
                    mSoppinggImage1.setImageResource(R.drawable.dou_up);
                    scheme(mUserid, "1", "13", "2", "", "", "", "", "", "", "","");
                    count1 = 0;
                }
                break;
            case R.id.soppingg_2:
                mSoppinggText.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggText2.setTextColor(CommonUtil.getColor(R.color.jue));
                mSoppinggText1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                MKProgressHUD.show();
                mSoppinggImage1.setImageResource(R.drawable.doublea);
                if (count2 == 0) {
                    mSoppinggImage2.setImageResource(R.drawable.dou_down);
                    scheme(mUserid, "1", "13", "4", "", "", "", "", "", "", "","");
                    count2++;
                } else {
                    mSoppinggImage2.setImageResource(R.drawable.dou_up);
                    scheme(mUserid, "1", "13", "3", "", "", "", "", "", "", "","");
                    count2 = 0;
                }
                break;
            case R.id.soppingg:
                mSoppinggText.setTextColor(CommonUtil.getColor(R.color.jue));
                mSoppinggText2.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggText1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mSoppinggImage2.setImageResource(R.drawable.doublea);
                mSoppinggImage1.setImageResource(R.drawable.doublea);
                MKProgressHUD.show();
                scheme(mUserid, "1", "13", "", "", "", "", "", "", "", "","");
                break;
            //筛选按钮
            case R.id.scheme_shaixuan:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                mFlag1 = false;
                break;
            //重置
            case R.id.zhongzhi_button:
             //   mIdFlowlayout.
                mMlist="";
                mAdapter.notifyDataChanged();
                mDrawerLayout.closeDrawers();
                mFlag1 = true;
                break;
            //确定
            case R.id.queding_button:
                mDrawerLayout.closeDrawers();
                List<SchemeBean.DataBean> FaZl = new ArrayList<SchemeBean.DataBean>();
                if (mMlist != null && !mMlist.equals("")) {
                    KLog.e(mMlist);
                    for (SchemeBean.DataBean dataBean : mDataBeen) {
                        if (dataBean.getIndus_pid().equals(mMlist)) {
                            FaZl.add(dataBean);
                        }
                    }
                    KLog.e("FaZl",FaZl.size());
                    if (FaZl.size() > 0) {
                        mQuanbuImage.setVisibility(View.GONE);
                        mSuperlayout.setVisibility(View.VISIBLE);
                        mSchemehallListview.setAdapter(new SchemeHallAdapter(SchemeHallActivity.this, FaZl));
                    } else {
                        mQuanbuImage.setVisibility(View.VISIBLE);
                        mSuperlayout.setVisibility(View.GONE);
                    }
                } else {
                    mSchemehallListview.setAdapter(new SchemeHallAdapter(SchemeHallActivity.this, mDataBeen));
                }
                break;
            case R.id.messge_img:
                Intent intent = new Intent(this,SearchInfoActivity.class);
                intent.putExtra("state",mIntExtra);
                startActivity(intent);
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

    protected void elseView() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
            mDrawerLayout.setClipToPadding(false);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
