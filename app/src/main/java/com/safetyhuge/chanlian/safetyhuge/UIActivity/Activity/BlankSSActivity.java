package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhouwei.library.CustomPopWindow;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsDaoBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.SchemeBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskList;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.GoodsDaoAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.SchemeHallAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.TaskListAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.views.SwipeRefreshView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/6/5 0005 14:58
 * 邮箱：995696826@qq.com
 */

public class BlankSSActivity extends HBaseAct {
    @Bind(R.id.blank_sousuo_item)
    TextView mBlankSousuoItem;
    @Bind(R.id.blank_sousuo_et)
    EditText mBlankSousuoEt;
    @Bind(R.id.fuwu_soushuo)
    LinearLayout mFuwuSoushuo;
    @Bind(R.id.sousuo_listView)
    MyListView mSousuoListView;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.sopping_swlayout)
    SwipeRefreshView mSoppingSwlayout;
    @Bind(R.id.sousuo_button)
    Button mSousuoButton;
    private CustomPopWindow mCustomPopWindow;
    private KProgressHUD mKProgressHUD;
    private List<TaskList.DataBean> mTaskListData;
    private List<GoodsDaoBean.DataBean> mDaoBeanData;
    int count = 1;
    private String mUserid;
    private String mChange;
    private GoodsDaoAdapter mGoodsDaoAdapter;
    private List<SchemeBean.DataBean> schemeData;
    List<GoodsDaoBean.DataBean> mDataBean = new ArrayList<>();
    private String mS;


    @Override
    protected void onCreate(Bundle arg0) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(arg0);
        setContentView(R.layout.activity_blank_search);
        ButterKnife.bind(this);
        mBlankSousuoEt.requestFocus();
        mKProgressHUD = KProgressHUD.create(BlankSSActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "1");
      /*  TaskList();
        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "");*/
      //  initView();
       // RequestAddress.HOST
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
    //项目市场
    public void TaskList(final String change) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfo");
        hashMap.put("uid", "0");
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<TaskList>() {
            @Override
            public void onSuccess(TaskList taskList, Call call, Response response) {
                KLog.e("项目市场");
                mTaskListData = taskList.getData();
                mKProgressHUD.dismiss();
                if (change.equals("")) {
                    mSousuoListView.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                } else {
                    mSousuoListView.setVisibility(View.VISIBLE);
                    List<TaskList.DataBean> mDataBean = new ArrayList<>();
                    for (TaskList.DataBean bean : mTaskListData) {
                        if (bean.getTask_title().indexOf(change) != -1) {
                            mDataBean.add(bean);
                        }
                    }
                    if (mDataBean.isEmpty()) {
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    } else {
                        mQuanbuImage.setVisibility(View.GONE);
                        mSousuoListView.setAdapter(new TaskListAdapter(BlankSSActivity.this, mDataBean, 3));
                    }
                }
            }
        });
    }


    public void NetWork(String uid, final String page, String mid,
                        String order, String brand_id,
                        String indus_pid, String indus_id,
                        String province, String city,
                        String area, String sales,
                        final String trim) {
        HashMap mHashMap = new HashMap<>();
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
                KLog.e("请求成功");
                mDaoBeanData = goodsDaoBean.getData();
                mKProgressHUD.dismiss();
                //    mSoppingSwlayout.setLoading(false);
                KLog.e("whb", page);
                KLog.e("whb", mDaoBeanData.size());
                if (trim.equals("")) {
                    mSousuoListView.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                } else {
                    mSousuoListView.setVisibility(View.VISIBLE);
                    List<GoodsDaoBean.DataBean> beans = new ArrayList<>();
                    for (GoodsDaoBean.DataBean bean : mDaoBeanData) {
                        if (bean.getTitle().indexOf(trim) != -1) {
                            mDataBean.add(bean);
                            beans.add(bean);
                        }
                    }
                    if (beans.isEmpty()) {
                        Toast.makeText(BlankSSActivity.this, "没有数据了", Toast.LENGTH_SHORT).show();
                    }
                    if (mDataBean.isEmpty()) {
                        mSoppingSwlayout.setRefreshing(false);
                        mSoppingSwlayout.setLoading(false);
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    } else {
                        mQuanbuImage.setVisibility(View.GONE);
                        mGoodsDaoAdapter = new GoodsDaoAdapter(BlankSSActivity.this, mDataBean, 0);
                        mSousuoListView.setAdapter(mGoodsDaoAdapter);
                    }
                }
            }
        });
    }


    public void initView() {
        //下拉刷新
        mSoppingSwlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mSoppingSwlayout.setRefreshing(false);
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                    }
                }, 1200);
            }
        });
        mSoppingSwlayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                count++;
                mS = mBlankSousuoItem.getText().toString();
                String trim = mBlankSousuoEt.getText().toString().trim();
                if (mS.equals("项目  ")) {
                    TaskList(trim);
                } else if (mS.equals("产品  ")) {
                    NetWork(mUserid, count + "", "6", "", "", "", "", "", "", "", "", trim);
                } else if (mS.equals("方案  ")) {

                }
                KLog.e("whbxount", count + "");

            }
        });
    }

    private void showPopMenu() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu_item, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(mFuwuSoushuo, 0, 0);
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.menu1:
                        mBlankSousuoItem.setText("项目  ");
                        mBlankSousuoEt.setText("");
                        mBlankSousuoEt.setHint("请输入项目关键字");
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.GONE);
                        break;
                    case R.id.menu2:
                        mBlankSousuoItem.setText("产品  ");
                        mBlankSousuoEt.setText("");
                        mBlankSousuoEt.setHint("请输入产品关键字");
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.GONE);
                        break;
                    case R.id.menu3:
                        mBlankSousuoItem.setText("方案  ");
                        mBlankSousuoEt.setText("");
                        mBlankSousuoEt.setHint("请输入方案关键字");
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.GONE);
                        break;
                }
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
    }


    @OnClick({R.id.sousuo_button, R.id.blank_sousuo_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blank_sousuo_item:
                showPopMenu();
                break;
            case R.id.sousuo_button:
                mKProgressHUD.show();
                String s = mBlankSousuoItem.getText().toString();
                String trim = mBlankSousuoEt.getText().toString().trim();
                if (s.equals("项目  ")) {
                    TaskList(trim);
                } else if (s.equals("产品  ")) {
                    mDataBean.clear();
                    NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "", trim);
                } else if (s.equals("方案  ")) {
                    scheme(mUserid, "1", "13", "", "", "", "", "", "", "", "", trim);
                }
                break;
        }
    }

    public void back_text_view(View view) {
        finish();
    }

    //方案大厅
    public void scheme(String uid, String page, String mid,
                       String order, String brand_id,
                       String indus_pid, String indus_id,
                       String province, String city,
                       String area, String sales, final String change) {
        HashMap mHashMap = new HashMap<>();
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
                mKProgressHUD.dismiss();
                // mSuperlayout.setLoading(false);
                schemeData = scheme.getData();
                if (change.equals("")) {
                    mSousuoListView.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                } else {
                    mSousuoListView.setVisibility(View.VISIBLE);
                    List<SchemeBean.DataBean> mDataBean = new ArrayList<>();
                    for (SchemeBean.DataBean bean : schemeData) {
                        if (bean.getTitle().indexOf(change) != -1) {
                            mDataBean.add(bean);
                        }
                    }
                    if (mDataBean.isEmpty()) {
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    } else {
                        mQuanbuImage.setVisibility(View.GONE);
                        mSousuoListView.setAdapter(new SchemeHallAdapter(BlankSSActivity.this, mDataBean));
                    }
                }
            }
        });
    }
}

