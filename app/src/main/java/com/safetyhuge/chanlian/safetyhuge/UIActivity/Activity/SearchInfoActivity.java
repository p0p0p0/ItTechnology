package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsDaoBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.SchemeBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.TalentsBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskList;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.GoodsDaoAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.SchemeHallAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.TalentsAdapters;
import com.safetyhuge.chanlian.safetyhuge.adapter.TaskListAdapter;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.views.SwipeRefreshView;
import com.socks.library.KLog;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;
import com.wyt.searchedittext.SearchEditText;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/6/27 0027 13:56
 * 邮箱：995696826@qq.com
 */

public class SearchInfoActivity extends HBaseAct {
    Intent mIntent;
    @Bind(R.id.text_title)
    TextView mTextTitle;
    @Bind(R.id.messge_img)
    ImageView mMessgeImg;
    @Bind(R.id.sopping_swlayout)
    SwipeRefreshView mSoppingSwlayout;
    @Bind(R.id.searchEditText)
    SearchEditText mSearchEditText;
    @Bind(R.id.bazaar_listView)
    ListView mBazaarListView;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private int mState;
    private String mUserid;
    private List<GoodsDaoBean.DataBean> mDataBeen;
    private KProgressHUD mKProgressHUD;
    String str;
    int  count = 1;
    boolean flag;
    boolean flag1=true;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_searchinfo);
        ButterKnife.bind(this);
        mIntent = getIntent();
        mUserid = (String) get(SearchInfoActivity.this, "UserUid", "1");
        mState = mIntent.getIntExtra("state", -1);
        mKProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("搜索中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        switch (mState) {
            case 0:
                mTextTitle.setText("项目市场");
                break;
            case 1:
                mTextTitle.setText("产品导购");
                break;
            case 3:
                mTextTitle.setText("促销专区");
                break;
            case 4:
                mTextTitle.setText("二手市场");
                break;
            case 5:
                mTextTitle.setText("设备租赁");
                break;
            //方案大厅
            case 11:
                mTextTitle.setText("方案大厅");
                break;
            case 12:
                mTextTitle.setText("人才大厅");
                break;
        }
        soushuo();
        initView();
    }
    private void soushuo() {
        SearchFragment mSearchFragment = SearchFragment.newInstance();
        mSearchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
        mSearchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                str = keyword;
                flag = true;
                flag1 = true;
                mKProgressHUD.show();
                switch (mState) {
                    case 0:
                        search("1",keyword);
                        break;
                    case 1:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "", keyword);
                        break;
                    case 3:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "2", keyword);
                        break;
                    case 4:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "3", keyword);
                        break;
                    case 5:
                        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "6", keyword);
                        break;
                        //方案大厅
                    case 11:
                        scheme(mUserid, "1", "13", "", "", "", "", "", "", "", "",keyword);
                        break;
                    case 12:
                        alents(keyword,"1", "", "", "", "", "", "", "1");
                        break;
                }
            }
        });
    }

    private void initView() {
        mSoppingSwlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSoppingSwlayout.setRefreshing(false);
                    }},2000);
            }
        });
        mSoppingSwlayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                flag1 = false;
                count++;
                if (flag == true) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            switch (mState) {
                                case 0:
                                    search(count+"",str);
                                    break;
                                case 1:
                                    NetWork(mUserid, count+"", "6", "", "", "", "", "", "", "", "", str);
                                    break;
                                case 3:
                                    NetWork(mUserid, count+"", "6", "", "", "", "", "", "", "", "2", str);
                                    break;
                                case 4:
                                    NetWork(mUserid, count+"", "6", "", "", "", "", "", "", "", "3", str);
                                    break;
                                case 5:
                                    NetWork(mUserid, count+"", "6", "", "", "", "", "", "", "", "6", str);
                                    break;
                                case 11:
                                    scheme(mUserid, count+"", "13", "", "", "", "", "", "", "", "",str);
                                    break;
                                case 12:
                                    alents(str,count+"", "", "", "", "", "", "", "1");
                                    break;
                            }
                        }},2000);
                }else{
                    mSoppingSwlayout.setLoading(false);
                }
            }
        });
    }


    /**
     * 产品导购
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
                        String area, String sales, String keyword
    ) {
        HashMap<String, String> mHashMap = new HashMap<>();
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
                mDataBeen = goodsDaoBean.getData();
                mKProgressHUD.dismiss();
                if (!mDataBeen.isEmpty()) {
                    mQuanbuImage.setVisibility(View.GONE);
                    mSoppingSwlayout.setVisibility(View.VISIBLE);
                    GoodsDaoAdapter mGoodsDaoAdapter = new GoodsDaoAdapter(SearchInfoActivity.this, mDataBeen, 0);
                    mGoodsDaoAdapter.notifyDataSetChanged();
                    mBazaarListView.setAdapter(mGoodsDaoAdapter);
                } else {
                    if (flag1==true){
                        mQuanbuImage.setVisibility(View.VISIBLE);
                        mSoppingSwlayout.setVisibility(View.GONE);
                    }else{
                        flag = false;
                        mSoppingSwlayout.setLoading(false);
                        Toast.makeText(SearchInfoActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /**
     * 项目市场
     */
    private void search(String  page,String keyword) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfo");
        hashMap.put("uid", "1");
        hashMap.put("page", page);
        hashMap.put("keyword", keyword);
        KLog.e("whb",hashMap.toString());
        OkGo.post(RequestAddress.HOST+"task.php").params(hashMap).execute(new JsonCallback<TaskList>() {
            @Override
            public void onSuccess(TaskList taskList, Call call, Response response) {
                List<TaskList.DataBean> data = taskList.getData();
                KLog.e("请求成功");
                mSoppingSwlayout.setRefreshing(false);
                mKProgressHUD.dismiss();
                if (data!=null&&!data.isEmpty()){
                    mSoppingSwlayout.setVisibility(View.VISIBLE);
                    mQuanbuImage.setVisibility(View.GONE);
                    mBazaarListView.setAdapter(new TaskListAdapter(SearchInfoActivity.this, data, 3));
                }else{
                    if (flag1==true){
                        mSoppingSwlayout.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }else{
                        flag = false;
                        mSoppingSwlayout.setLoading(false);
                        Toast.makeText(SearchInfoActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //keyword
    }
    //方案大厅
    public void scheme(String uid, String page, String mid,
                       String order, String brand_id,
                       String indus_pid, String indus_id,
                       String province, String city,
                       String area, String sales,String keyword) {
        HashMap<String,String> mHashMap = new HashMap<>();
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
                KLog.e("请求成功");
                List<SchemeBean.DataBean> data = scheme.getData();
                mSoppingSwlayout.setRefreshing(false);
                mKProgressHUD.dismiss();
                if (data!=null&&!data.isEmpty()){
                    mSoppingSwlayout.setVisibility(View.VISIBLE);
                    mQuanbuImage.setVisibility(View.GONE);
                    SchemeHallAdapter   mSchemeHallAdapter = new SchemeHallAdapter(SearchInfoActivity.this, data);
                    mBazaarListView.setAdapter(mSchemeHallAdapter);
                }else{
                    if (flag1==true){
                        mSoppingSwlayout.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }else{
                        flag = false;
                        mSoppingSwlayout.setLoading(false);
                        Toast.makeText(SearchInfoActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                    }
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
                List<TalentsBean.DataBean> data = talentsBean.getData();
                mSoppingSwlayout.setRefreshing(false);
                mKProgressHUD.dismiss();
                if (data!=null&&!data.isEmpty()){
                    mSoppingSwlayout.setVisibility(View.VISIBLE);
                    mQuanbuImage.setVisibility(View.GONE);
                    TalentsAdapters mAdapters = new TalentsAdapters(SearchInfoActivity.this, data, 2);
                    mBazaarListView.setAdapter(mAdapters);
                }else{
                    if (flag1==true){
                        mSoppingSwlayout.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }else{
                        flag = false;
                        mSoppingSwlayout.setLoading(false);
                        Toast.makeText(SearchInfoActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick(R.id.messge_img)
    public void onClick() {
        soushuo();
    }
}
