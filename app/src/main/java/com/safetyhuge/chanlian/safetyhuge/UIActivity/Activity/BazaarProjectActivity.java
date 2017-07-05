package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskList;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.RegionInfoFragment.ShengFragment;
import com.safetyhuge.chanlian.safetyhuge.adapter.TaskListAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.views.SwipeRefreshView;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.zhl.cbdialog.CBDialogBuilder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 项目市场页面
 * 徐艳昭
 * 15326057231@163.com
 * 2017/3/8
 **/
public class BazaarProjectActivity extends HBaseAct implements CallBack {
    @Bind(R.id.tv_img_back)
    ImageView mTvImgBack;
    @Bind(R.id.Login_Edt_PassWord)
    EditText mLoginEdtPassWord;
    @Bind(R.id.messge_img)
    ImageView mMessgeImg;
    @Bind(R.id.bazaar_listView)
    MyListView mBazaarListView;
    @Bind(R.id.project_item)
    LinearLayout mProjectItem;
    @Bind(R.id.project_yusuan)
    LinearLayout mProjectYusuan;
    @Bind(R.id.roject_item_iamge)
    ImageView mRojectItemIamge;
    @Bind(R.id.roject_yusuan_iamge)
    ImageView mRojectYusuanIamge;
    @Bind(R.id.roject_fabu_iamge)
    ImageView mRojectfabuIamge;
    @Bind(R.id.project_shaixuan)
    LinearLayout mProjectShaixuan;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.project_fabu)
    LinearLayout mProjectFabu;
    @Bind(R.id.nav_view)
    FrameLayout mNavView;
    @Bind(R.id.project)
    TextView mProject;
    @Bind(R.id.project1)
    TextView mProject1;
    @Bind(R.id.project2)
    TextView mProject2;
    @Bind(R.id.id_flowlayout)
    TagFlowLayout mIdFlowlayout;
    @Bind(R.id.id_flowlayout1)
    TagFlowLayout mIdFlowlayout1;
    @Bind(R.id.zuidi_money)
    EditText mZuidiMoney;
    @Bind(R.id.zuigao_money)
    EditText mZuigaoMoney;
    @Bind(R.id.diqu_button)
    TextView mDiquButton;
    @Bind(R.id.zhongzhi_button)
    Button mZhongzhiButton;
    @Bind(R.id.queding_button)
    Button mQuedingButton;
    /*  @Bind(R.id.scrollView_layout)
      ScrollView mBazaarListView;*/
    @Bind(R.id.text111)
    TextView mText111;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.swipe_layout)
    SwipeRefreshView mSwipeLayout;
    private ImageView tv_img_back;
    KProgressHUD mKProgressHUD;
    private Context mContext;
    private String mUserid;
    private boolean mFlag1 = true;
    private List<TaskList.DataBean> mTaskListData;
    private ArrayList<String> mRegionIdyList, mRegionyNameList;
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    private List<TaskList.DataBean> mMTaskListData1;
    private List<TaskList.DataBean> mMTaskListData2;
    private String mText;
    private List<String> mList, mList1, mLists, mList1s;
    private String mTrim;
    private String mTrim1;
    private Object mDiqu;
    private String mMlist1;
    private String mMlist;
    private int mCount = 0;
    ;
    private int mP;
    private int mPosition, mPosition1;
    private String mS1;
    private String mS11;
    private double mZuidi;
    private double mZuigao;
    private double mTask_cash1;
    private TaskListAdapter mListAdapter;
    private int count = 1;
    boolean flag = true;
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    TaskList(1);
                    KLog.e("handler");
                    break;
            }
        }
    };
    private int mIntExtra;
    private TagAdapter mAdapter;
    private TagAdapter mTagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(BazaarProjectActivity.this);
        super.onCreate(savedInstanceState);
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        mContext = BazaarProjectActivity.this;
        setContentView(R.layout.activity_bazaar_project);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mIntExtra = intent.getIntExtra("flag", -1);
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            elseView();
        }
        mKProgressHUD = KProgressHUD.create(BazaarProjectActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mRojectfabuIamge.setImageResource(R.drawable.dou_down);
        tv_img_back = (ImageView) findViewById(R.id.tv_img_back);
        tv_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitAct();//退出当前页面，并且销毁当亲Activiy
            }
        });
        TaskList(1);
        mLoginEdtPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                mText = s.toString();
                if (mText == "") {
                    KLog.e("whb", mText);
                    //搜索
                    search(mText);
                   /* Collections.sort(mMTaskListData1, new Comparator<TaskList.DataBean>() {
                        @Override
                        public int compare(TaskList.DataBean o1, TaskList.DataBean o2) {
                            String a = DateUtils.time(o1.getStart_time());
                            String a2 = DateUtils.time(o2.getStart_time());
                            KLog.e("aaaaa1", a);
                            KLog.e("aaaaa2", a2);
                            Date date1 = stringToDate(a);
                            Date date2 = stringToDate(a2);
                            KLog.e("data1", date1);
                            KLog.e("date2", date2);
                            // 对日期字段进行升序，如果欲降序可采用after方法
                            if (date1.before(date2)) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    count3 = 1;
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3));
                } else {
                    List<TaskList.DataBean> mMTaskListData3 = new ArrayList<>();
                    if (mMTaskListData1 != null) {
                        for (TaskList.DataBean dataBean : mMTaskListData1) {
                            if (dataBean.getTask_title().indexOf(mText) != -1) {
                                KLog.e("getTask_status", dataBean.getTask_title());
                                mMTaskListData3.add(dataBean);
                            }
                        }
                        KLog.e("whb", mMTaskListData3.size());
                        if (mMTaskListData3 != null && !mMTaskListData3.isEmpty() && mMTaskListData3.size() != 0) {
                            mSwipeLayout.setVisibility(View.VISIBLE);
                            mQuanbuImage.setVisibility(View.GONE);
                            mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData3, 3));
                        } else {
                            KLog.e("whb", "zoule");
                            mText111.setText("没有查询到内容");
                            mSwipeLayout.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                        }
                    }*/
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
             /*   KLog.e("whb", "zoule");
                mText111.setText("没有查询到内容");
                mSwipeLayout.setVisibility(View.GONE);
                mQuanbuImage.setVisibility(View.VISIBLE);*/
                //搜索
                KLog.e("whb", s);
                search(s + "");
            }
        });
        InitView();
    }

    private void search(String keyword) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfo");
        hashMap.put("uid", "1");
        hashMap.put("page", "1");
        hashMap.put("keyword", keyword);
        KLog.e("whb", hashMap.toString());
        OkGo.post(RequestAddress.HOST + "task.php").params(hashMap).execute(new JsonCallback<TaskList>() {
            @Override
            public void onSuccess(TaskList taskList, Call call, Response response) {
                List<TaskList.DataBean> data = taskList.getData();
                if (data != null && !data.isEmpty()) {
                    mSwipeLayout.setVisibility(View.VISIBLE);
                    mQuanbuImage.setVisibility(View.GONE);
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, data, 3));
                } else {
                    KLog.e("whb", "zoule");
                    mText111.setText("没有查询到内容");
                    mSwipeLayout.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                }
            }
        });
        //keyword
    }

    public void InitView() {
        mList = new ArrayList<>();
        mLists = new ArrayList<>();
        mList1 = new ArrayList<>();
        mList1s = new ArrayList<>();
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

        mList1.add("单人模式");
        mList1.add("多人模式");
        mList1.add("普通招标");
        mList1.add("订金招标");

        mList1s.add("1");
        mList1s.add("2");
        mList1s.add("4");
        mList1s.add("5");
        mTagAdapter = new TagAdapter(mList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ECApplication.context, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        };
        mIdFlowlayout.setAdapter(mTagAdapter);
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
        mAdapter = new TagAdapter(mList1) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(ECApplication.context, R.layout.tv, null);
                tv.setText(o + "");
                return tv;
            }
        };
        mIdFlowlayout1.setAdapter(mAdapter);
        mIdFlowlayout1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
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
                    mMlist1 = mList1s.get(mPosition1);
                    KLog.e("mMlist1mMlist1mMlist1", mMlist1);
                } else {
                    mMlist1 = "";
                }
            }
        });

        // 设置下拉加载更多
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Message msg = new Message();
                msg.arg1 = 0;
                handler.sendMessage(msg);
                mListAdapter.notifyDataSetChanged();
            }
        });
        //设置上拉加载更多
        mSwipeLayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                count++;
                if (flag == true) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TaskList1(count);
                        }
                    }, 2000);
                } else {
                    mSwipeLayout.setLoading(false);
                }
            }
        });
    }

    //网络请求
    public void TaskList(int flag) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfo");
        hashMap.put("uid", "1");
        hashMap.put("page", flag + "");
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<TaskList>() {
            @Override
            public void onSuccess(TaskList taskList, Call call, Response response) {
                mKProgressHUD.dismiss();
                //data
                mTaskListData = taskList.getData();
                mSwipeLayout.setRefreshing(false);
                //mSuperlayout1.setRefreshing(false);
                KLog.e("mTaskListData", mTaskListData.size());
                if (!mTaskListData.isEmpty()) {
                    mMTaskListData1 = new ArrayList<TaskList.DataBean>();
                    for (TaskList.DataBean dataBean : mTaskListData) {
                        if (dataBean.getTask_status().equals("2")) {
                            KLog.e("getTask_status");
                            mMTaskListData1.add(dataBean);
                        }
                    }
              /*  Collections.sort(mMTaskListData1, new Comparator<TaskList.DataBean>() {
                    @Override
                    public int compare(TaskList.DataBean o1, TaskList.DataBean o2) {
                        String a = DateUtils.time(o1.getStart_time());
                        String a2 = DateUtils.time(o2.getStart_time());
                        KLog.e("aaaaa1", a);
                        KLog.e("aaaaa2", a2);
                        Date date1 = stringToDate(a);
                        Date date2 = stringToDate(a2);
                        KLog.e("data1", date1);
                        KLog.e("date2", date2);
                        // 对日期字段进行升序，如果欲降序可采用after方法
                        if (date1.before(date2)) {
                            return 1;
                        }
                        return -1;
                    }
                });*/
                    count3 = 1;
                    mListAdapter = new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3);
                    mBazaarListView.setAdapter(mListAdapter);
                } else {
                    mText111.setText("没有结果");
                    mSwipeLayout.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //网络请求
    public void TaskList1(final int flag1) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfo");
        hashMap.put("uid", "1");
        hashMap.put("page", flag1 + "");
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<TaskList>() {
            @Override
            public void onSuccess(TaskList taskList, Call call, Response response) {
                mKProgressHUD.dismiss();
                mSwipeLayout.setLoading(false);
                List<TaskList.DataBean> data = taskList.getData();
                if (data.isEmpty()) {
                    flag = false;
                    mSwipeLayout.setLoading(false);
                    //展示
                    LinearLayout footView = (LinearLayout) View.inflate(BazaarProjectActivity.this, R.layout.item_foot, null);//得到尾部的布局
                    mBazaarListView.addFooterView(footView);
                    Toast.makeText(BazaarProjectActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                } else {
                    for (TaskList.DataBean datum : data) {
                        if (datum.getTask_status().equals("2")) {
                            KLog.e("getTask_status");
                            mMTaskListData1.add(datum);
                        }
                    }
                    mListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @OnClick({R.id.messge_img, R.id.project_fabu, R.id.project_item, R.id.project_yusuan, R.id.project_shaixuan, R.id.diqu_button, R.id.zhongzhi_button, R.id.queding_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.messge_img:
           /*     if (mUserid != "") {
                    startActivity(new Intent(BazaarProjectActivity.this, ReleaseProjectActivity.class
                    ));
                } else {
                    showNormalDialog();
                }*/
                Intent intent = new Intent(this, SearchInfoActivity.class);
                intent.putExtra("state", mIntExtra);
                startActivity(intent);
                break;
            //发布时间
            case R.id.project_fabu:
                mProject.setTextColor(CommonUtil.getColor(R.color.jue));
                mProject1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mProject2.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mRojectYusuanIamge.setImageResource(R.drawable.doublea);
                mRojectItemIamge.setImageResource(R.drawable.doublea);
                count1 = 0;
                count2 = 0;
                if (count3 == 0) {
                    mRojectfabuIamge.setImageResource(R.drawable.dou_down);
                    Collections.sort(mMTaskListData1, new Comparator<TaskList.DataBean>() {
                        @Override
                        public int compare(TaskList.DataBean o1, TaskList.DataBean o2) {
                            String a = DateUtils.time(o1.getStart_time());
                            String a2 = DateUtils.time(o2.getStart_time());
                            KLog.e("aaaaa1", a);
                            KLog.e("aaaaa2", a2);
                            Date date1 = stringToDate(a);
                            Date date2 = stringToDate(a2);
                            KLog.e("data1", date1);
                            KLog.e("date2", date2);
                            // 对日期字段进行升序，如果欲降序可采用after方法
                            if (date1.before(date2)) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3));
                    count3++;
                } else {
                    mRojectfabuIamge.setImageResource(R.drawable.dou_up);
                    Collections.sort(mMTaskListData1, new Comparator<TaskList.DataBean>() {
                        @Override
                        public int compare(TaskList.DataBean o1, TaskList.DataBean o2) {
                            String a = DateUtils.time(o1.getStart_time());
                            String a2 = DateUtils.time(o2.getStart_time());
                            KLog.e("aaaaa1", a);
                            KLog.e("aaaaa2", a2);
                            Date date1 = stringToDate(a);
                            Date date2 = stringToDate(a2);
                            KLog.e("data1", date1);
                            KLog.e("date2", date2);
                            // 对日期字段进行升序，如果欲降序可采用after方法
                            if (date1.after(date2)) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3));
                    count3 = 0;
                }
                break;
            //按照时间排序
            case R.id.project_item:
                mProject1.setTextColor(CommonUtil.getColor(R.color.jue));
                mProject.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mProject2.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mRojectfabuIamge.setImageResource(R.drawable.doublea);
                mRojectYusuanIamge.setImageResource(R.drawable.doublea);
                count2 = 0;
                count3 = 0;
                if (count1 == 0) {
                    mRojectItemIamge.setImageResource(R.drawable.dou_down);
                    Collections.sort(mMTaskListData1, new Comparator<TaskList.DataBean>() {
                        @Override
                        public int compare(TaskList.DataBean o1, TaskList.DataBean o2) {
                            String a = DateUtils.time(o1.getSub_time());
                            String a2 = DateUtils.time(o2.getSub_time());
                            KLog.e("aaaaa1", a);
                            KLog.e("aaaaa2", a2);
                            Date date1 = stringToDate(a);
                            Date date2 = stringToDate(a2);
                            KLog.e("data1", date1);
                            KLog.e("date2", date2);
                            // 对日期字段进行升序，如果欲降序可采用after方法
                            if (date1.after(date2)) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3));
                    count1++;
                } else {
                    mRojectItemIamge.setImageResource(R.drawable.dou_up);
                    Collections.sort(mMTaskListData1, new Comparator<TaskList.DataBean>() {
                        @Override
                        public int compare(TaskList.DataBean o1, TaskList.DataBean o2) {
                            String a = DateUtils.time(o1.getSub_time());
                            String a2 = DateUtils.time(o2.getSub_time());
                            KLog.e("aaaaa1", a);
                            KLog.e("aaaaa2", a2);
                            Date date1 = stringToDate(a);
                            Date date2 = stringToDate(a2);
                            KLog.e("data1", date1);
                            KLog.e("date2", date2);
                            // 对日期字段进行升序，如果欲降序可采用after方法
                            if (date1.before(date2)) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3));
                    count1 = 0;
                }
                break;
            //按照预算排序
            case R.id.project_yusuan:
                mProject2.setTextColor(CommonUtil.getColor(R.color.jue));
                mProject.setTextColor(CommonUtil.getColor(R.color.text_brown));
                mProject1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                count1 = 0;
                count3 = 0;
                mRojectfabuIamge.setImageResource(R.drawable.doublea);
                mRojectItemIamge.setImageResource(R.drawable.doublea);
                if (count2 == 0) {
                    mRojectYusuanIamge.setImageResource(R.drawable.dou_down);
                    Collections.sort(mMTaskListData1, new Comparator<TaskList.DataBean>() {
                        @Override
                        public int compare(TaskList.DataBean o1, TaskList.DataBean o2) {
                            String a1 = o1.getTask_cash();
                            double d = Double.valueOf(a1).doubleValue();
                            KLog.e("a11", d);
                            String a2 = o2.getTask_cash();
                            double d1 = Double.valueOf(a2).doubleValue();
                            Double obj1 = new Double(d);
                            Double obj2 = new Double(d1);
                            KLog.e("d1", d1);
                            if (obj1.compareTo(obj2) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3));
                    count2++;
                } else {
                    mRojectYusuanIamge.setImageResource(R.drawable.dou_up);
                    Collections.sort(mMTaskListData1, new Comparator<TaskList.DataBean>() {
                        @Override
                        public int compare(TaskList.DataBean o1, TaskList.DataBean o2) {
                            String a1 = o1.getTask_cash();
                            double d = Double.valueOf(a1).doubleValue();
                            KLog.e("a11", d);
                            String a2 = o2.getTask_cash();
                            double d1 = Double.valueOf(a2).doubleValue();
                            Double obj1 = new Double(d);
                            Double obj2 = new Double(d1);
                            KLog.e("d1", d1);
                            if (obj1.compareTo(obj2) < 0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3));
                    count2 = 0;
                }
                break;
            case R.id.project_shaixuan:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                mFlag1 = false;
                mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                    }
                });
                break;
            //选择地区
            case R.id.diqu_button:
                //把你的选择地区写在fragment里面
                ShengFragment fragment = new ShengFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_right_entry, R.anim.hold, R.anim.hold, R.anim.slide_right_exit);
                fragmentTransaction.add(R.id.nav_view, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            //重置
            case R.id.zhongzhi_button:
                mZuidiMoney.setHint("最低价格");
                mZuigaoMoney.setHint("最高价格");
                mDiquButton.setText("请选择地区");
                mMlist = "";
                mAdapter.notifyDataChanged();
                mMlist = "";
                mTagAdapter.notifyDataChanged();
                mDrawerLayout.closeDrawers();
                mFlag1 = true;
                break;
            //确定
            case R.id.queding_button:
                //
                mDrawerLayout.closeDrawers();
                mFlag1 = true;
                //项目领域
                KLog.e("22222222222222222", mMlist);
                if (mMlist != null && !mMlist.equals("")) {
                    KLog.e(mMlist);
                    List<TaskList.DataBean> xiangmulingyu = new ArrayList<TaskList.DataBean>();
                    for (TaskList.DataBean dataBean : mTaskListData) {
                        if (dataBean.getIndus_pid().equals(mMlist)) {
                            xiangmulingyu.add(dataBean);
                        }
                    }
                    KLog.e("xiangmulingyu",xiangmulingyu.size());
                    if (xiangmulingyu.size() > 0) {
                        mQuanbuImage.setVisibility(View.GONE);
                        mSwipeLayout.setVisibility(View.VISIBLE);
                        mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmulingyu, 3));
                    } else {
                        KLog.e("whb","走了");
                        mText111.setText("没有数据");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                        mSwipeLayout.setVisibility(View.GONE);
                    }
                } else {
                    mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, mMTaskListData1, 3));
                }
                //项目模式
                if (mMlist1 != null && !mMlist1.equals("")) {
                    KLog.e("mMlist", mMlist);
                    KLog.e("mMlist1", mMlist1);
                    List<TaskList.DataBean> xiangmumoshi = new ArrayList<TaskList.DataBean>();
                    if (mMlist != null && !mMlist.equals("")) {
                        for (TaskList.DataBean dataBean : mTaskListData) {
                            if (dataBean.getIndus_pid().equals(mMlist) && dataBean.getModel_id().equals(mMlist1)) {
                                xiangmumoshi.add(dataBean);
                            }
                        }
                        if (xiangmumoshi.size() > 0) {
                            mQuanbuImage.setVisibility(View.GONE);
                            mSwipeLayout.setVisibility(View.VISIBLE);
                            mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmumoshi, 3));
                        } else {
                            mText111.setText("没有数据");
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mSwipeLayout.setVisibility(View.GONE);
                        }
                    } else {
                        for (TaskList.DataBean dataBean : mTaskListData) {
                            if (dataBean.getModel_id().equals(mMlist1)) {
                                xiangmumoshi.add(dataBean);
                            }
                        }
                        if (xiangmumoshi.size() > 0) {
                            mQuanbuImage.setVisibility(View.GONE);
                            mSwipeLayout.setVisibility(View.VISIBLE);
                            mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmumoshi, 3));
                        } else {
                            mText111.setText("没有数据");
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mSwipeLayout.setVisibility(View.GONE);
                        }
                    }
                }
                //项目预算
                //最低价
                mTrim = mZuidiMoney.getText().toString().trim() + ".00";
                //最高价
                mTrim1 = mZuigaoMoney.getText().toString().trim() + ".00";
                List<TaskList.DataBean> xiangmuyusuan = new ArrayList<TaskList.DataBean>();
                //全选模式
                if (mMlist1 != null && !mMlist1.equals("")) {
                    if (mMlist != null && !mMlist.equals("")) {
                        if (mTrim != null && mTrim1 != null && !mTrim.equals(".00") && !mTrim1.equals(".00")) {
                            mZuidi = Double.valueOf(mTrim).doubleValue();
                            mZuigao = Double.valueOf(mTrim1).doubleValue();
                            for (TaskList.DataBean dataBean : mTaskListData) {
                                String task_cash = dataBean.getTask_cash();
                                mTask_cash1 = Double.valueOf(task_cash).doubleValue();
                                if (dataBean.getIndus_pid().equals(mMlist) && dataBean.getModel_id().equals(mMlist1) && mTask_cash1 >= mZuidi && mTask_cash1 <= mZuigao) {
                                    xiangmuyusuan.add(dataBean);
                                }
                            }
                            if (xiangmuyusuan.size() > 0) {
                                mQuanbuImage.setVisibility(View.GONE);
                                mSwipeLayout.setVisibility(View.VISIBLE);
                                mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmuyusuan, 3));
                            } else {
                                mText111.setText("没有数据");
                                mQuanbuImage.setVisibility(View.VISIBLE);
                                mSwipeLayout.setVisibility(View.GONE);
                            }
                        }
                    } else if (mTrim != null && mTrim1 != null) {
                        if (!mTrim.equals(".00") && !mTrim1.equals(".00")) {
                            double zuidi = Double.valueOf(mTrim).doubleValue();
                            double zuigao = Double.valueOf(mTrim1).doubleValue();
                            for (TaskList.DataBean dataBean : mTaskListData) {
                                String task_cash = dataBean.getTask_cash();
                                double task_cash1 = Double.valueOf(task_cash).doubleValue();
                                if (task_cash1 >= zuidi && task_cash1 <= zuigao) {
                                    xiangmuyusuan.add(dataBean);
                                }
                            }
                            if (xiangmuyusuan.size() > 0) {
                                mQuanbuImage.setVisibility(View.GONE);
                                mSwipeLayout.setVisibility(View.VISIBLE);
                                mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmuyusuan, 3));
                            } else {
                                mText111.setText("没有数据");
                                mQuanbuImage.setVisibility(View.VISIBLE);
                                mSwipeLayout.setVisibility(View.GONE);
                            }
                        }
                    }
                    //单选模式
                } else if (mTrim != null && mTrim1 != null) {
                    if (!mTrim.equals(".00") && !mTrim1.equals(".00")) {
                        double zuidi = Double.valueOf(mTrim).doubleValue();
                        double zuigao = Double.valueOf(mTrim1).doubleValue();
                        for (TaskList.DataBean dataBean : mTaskListData) {
                            String task_cash = dataBean.getTask_cash();
                            double task_cash1 = Double.valueOf(task_cash).doubleValue();
                            if (task_cash1 >= zuidi && task_cash1 <= zuigao) {
                                xiangmuyusuan.add(dataBean);
                            }
                        }
                        if (xiangmuyusuan.size() > 0) {
                            mQuanbuImage.setVisibility(View.GONE);
                            mSwipeLayout.setVisibility(View.VISIBLE);
                            mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmuyusuan, 3));
                        } else {
                            mText111.setText("没有数据");
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mSwipeLayout.setVisibility(View.GONE);
                        }
                    }
                }
                //地区选择
                List<TaskList.DataBean> xiangmudiqu = new ArrayList<TaskList.DataBean>();
                if (mMlist1 != null && !mMlist1.equals("")) {
                    if (mMlist != null && !mMlist.equals("")) {
                        if (mTrim != null && mTrim1 != null && !mTrim.equals(".00") && !mTrim1.equals(".00") && mDiqu != null) {
                            for (TaskList.DataBean dataBean : mTaskListData) {
                                //省
                                String province_name = dataBean.getProvince_name();
                                //市
                                String city_name = dataBean.getCity_name();
                                //街道
                                String area_name = dataBean.getArea_name();
                                //地址拼接
                                String Dq = province_name + city_name + area_name;
                                if (dataBean.getIndus_pid().equals(mMlist) && dataBean.getModel_id().equals(mMlist1) && mTask_cash1 >= mZuidi && mTask_cash1 <= mZuigao && Dq.equals(mDiqu)) {
                                    xiangmudiqu.add(dataBean);
                                }
                            }
                            if (xiangmudiqu.size() > 0) {
                                mQuanbuImage.setVisibility(View.GONE);
                                mSwipeLayout.setVisibility(View.VISIBLE);
                                mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmudiqu, 3));
                            } else {
                                mText111.setText("没有数据");
                                mQuanbuImage.setVisibility(View.VISIBLE);
                                mSwipeLayout.setVisibility(View.GONE);
                            }
                        } else if (mDiqu != null) {
                            for (TaskList.DataBean dataBean : mTaskListData) {
                                //省
                                String province_name = dataBean.getProvince_name();
                                //市
                                String city_name = dataBean.getCity_name();
                                //街道
                                String area_name = dataBean.getArea_name();
                                //地址拼接
                                String Dq = province_name + city_name + area_name;
                                if (Dq.equals(mDiqu)) {
                                    xiangmudiqu.add(dataBean);
                                }
                            }
                            if (xiangmudiqu.size() > 0) {
                                mQuanbuImage.setVisibility(View.GONE);
                                mSwipeLayout.setVisibility(View.VISIBLE);
                                mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmudiqu, 3));
                            } else {
                                mText111.setText("没有数据");
                                mQuanbuImage.setVisibility(View.VISIBLE);
                                mSwipeLayout.setVisibility(View.GONE);
                            }
                        }
                    } else if (mDiqu != null) {
                        for (TaskList.DataBean dataBean : mTaskListData) {
                            //省
                            String province_name = dataBean.getProvince_name();
                            //市
                            String city_name = dataBean.getCity_name();
                            //街道
                            String area_name = dataBean.getArea_name();
                            //地址拼接
                            String Dq = province_name + city_name + area_name;
                            if (Dq.equals(mDiqu)) {
                                xiangmudiqu.add(dataBean);
                            }
                        }
                        if (xiangmudiqu.size() > 0) {
                            mQuanbuImage.setVisibility(View.GONE);
                            mSwipeLayout.setVisibility(View.VISIBLE);
                            mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmudiqu, 3));
                        } else {
                            mText111.setText("没有数据");
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mSwipeLayout.setVisibility(View.GONE);
                        }
                    }
                } else if (mDiqu != null) {
                    for (TaskList.DataBean dataBean : mTaskListData) {
                        //省
                        String province_name = dataBean.getProvince_name();
                        //市
                        String city_name = dataBean.getCity_name();
                        //街道
                        String area_name = dataBean.getArea_name();
                        //地址拼接
                        String Dq = province_name + city_name + area_name;
                        if (Dq.equals(mDiqu)) {
                            xiangmudiqu.add(dataBean);
                        }
                    }
                    if (xiangmudiqu.size() > 0) {
                        mQuanbuImage.setVisibility(View.GONE);
                        mSwipeLayout.setVisibility(View.VISIBLE);
                        mBazaarListView.setAdapter(new TaskListAdapter(BazaarProjectActivity.this, xiangmudiqu, 3));
                    } else {
                        mText111.setText("没有数据");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                        mSwipeLayout.setVisibility(View.GONE);
                    }
                }
                //地区选择
                if (mDiqu != null) {
                }
                break;
        }
    }


    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
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
                        startActivity(new Intent(BazaarProjectActivity.this, LoginsActivity.class));
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

    public void onResume() {
        super.onResume();
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        KLog.e("onResume");
        MobclickAgent.onResume(this);
    //    TaskList(1);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        KLog.e("onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        KLog.e("onRestart");
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
            } else {
                mDrawerLayout.closeDrawers();
                mFlag1 = true;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
