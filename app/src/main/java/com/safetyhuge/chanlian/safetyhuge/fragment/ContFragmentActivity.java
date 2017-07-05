package com.safetyhuge.chanlian.safetyhuge.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.NewsBean;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.LauncherActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.NewsInfoActivity;
import com.safetyhuge.chanlian.safetyhuge.adapter.ExamplePagerAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.NewsAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.fragment.titles.ColorFlipPagerTitleView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;
import com.yuntongxun.ecsdk.ECInitParams;
import com.zhl.cbdialog.CBDialogBuilder;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.ECApplication.context;

/**
 * 作者：王海宾 on 2017/4/28 0028 11:51
 * 邮箱：995696826@qq.com
 */

public class ContFragmentActivity extends FragmentActivity {
    private static final String[] CHANNELS = new String[]{"行业动态", "产品发布会", "Z解密", "科技风向标", "品牌资讯", "行业交流会", "产品评测", "消费观察"};
    @Bind(R.id.tv_img_back)
    ImageView mTvImgBack;
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Bind(R.id.indicator)
    MagicIndicator mIndicator;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    private ExamplePagerAdapter mExamplePagerAdapter;
    private List<NewsBean.DataBean> mData0;
    private List<NewsBean.DataBean> mData1;
    private List<NewsBean.DataBean> mData2;
    private List<NewsBean.DataBean> mData3;
    private List<NewsBean.DataBean> mData4;
    private List<NewsBean.DataBean> mData5;
    private List<NewsBean.DataBean> mData6;
    private List<NewsBean.DataBean> mData7;
    private Context mContext;
    private ArrayList<View> mViews;
    KProgressHUD MKProgressHUD;
    private MyListView mMyListView0, mMyListView1, mMyListView2, mMyListView3, mMyListView4, mMyListView5, mMyListView6, mMyListView7;
    private View mView1;
    private ImageView mImageView1;
    private TextView mTextView1;
    private View mView0;
    private ImageView mImageView;
    private TextView mTextView;
    private ImageView mImageView2;
    private TextView mTextView2;
    private View mView2;
    private View mView3;
    private ImageView mImageView3;
    private TextView mTextView3;
    private View mView4;
    private ImageView mImageView4;
    private TextView mTextView4;
    private View mView5;
    private ImageView mImageView5;
    private TextView mTextView5;
    private View mView6, mView7;
    private ImageView mImageView7;
    private TextView mTextView7;
    private ImageView mImageView6;
    private TextView mTextView6;
    private String mUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cont);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String flags = intent.getStringExtra("flags");
        if (flags.equals("0")) {
            mTvImgBack.setVisibility(View.VISIBLE);
        }
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mContext = this;
        MKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        if (mData0 == null) {
            initData0();
        }
        initMagicIndicator7();
        MKProgressHUD.show();
    }


    public void initView() {
        mViews = new ArrayList<View>();
        mViews.add(NewS0());
        mViews.add(NewS1());
        mViews.add(NewS2());
        mViews.add(NewS3());
        mViews.add(NewS4());
        mViews.add(NewS5());
        mViews.add(NewS6());
        mViews.add(NewS7());
        mViewpager.setAdapter(new ExamplePagerAdapter(mDataList, mViews));
    }


    private void initMagicIndicator7() {
        mIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator7 = new CommonNavigator(this);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#666666"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#ff732e"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewpager.setCurrentItem(index);
                        KLog.e("index", index + "");
                        if (index == 2) {
                        }
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#ff732e"));
                return indicator;
            }
        });
        mIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mIndicator, mViewpager);
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                KLog.e("当前页面", position);
                switch (position) {
                    case 0:
                        if (mData0 == null) {
                            initData0();
                        }
                        break;
                    case 1:
                        if (mData1 == null) {
                            MKProgressHUD.show();
                            initData1();
                        }
                        break;
                    case 2:
                        if (mData2 == null) {
                            MKProgressHUD.show();

                            initData2();
                        }
                        break;
                    case 3:
                        if (mData3 == null) {
                            MKProgressHUD.show();

                            initData3();
                        }
                        break;
                    case 4:
                        if (mData4 == null) {
                            MKProgressHUD.show();

                            initData4();
                        }
                        break;
                    case 5:
                        if (mData5 == null) {
                            MKProgressHUD.show();

                            initData5();
                        }
                        break;
                    case 6:
                        if (mData6 == null) {
                            MKProgressHUD.show();

                            initData6();
                        }
                        break;
                    case 7:
                        if (mData7 == null) {
                            MKProgressHUD.show();

                            initData7();
                        }
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void initData0() {
        OkGo.post(RequestAddress.HOST + RequestAddress.HYDT).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                mData0 = newsBean.getData();
                KLog.e("size", mData0.size());
                KLog.e("initData0");
                initView();
                RelativeLayout layout = (RelativeLayout) mView0.findViewById(R.id.view_item_layout);
                layout.setVisibility(View.VISIBLE);
                mImageView = (ImageView) mView0.findViewById(R.id.news_iamge);
                Picasso.with(mContext).load(RequestAddress.IMAGE2 + mData0.get(0).getPicurl()).into(mImageView);
                mTextView = (TextView) mView0.findViewById(R.id.view_pager_item_textview);
                mTextView.setText(mData0.get(0).getTitle());
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mData0.get(0).getContent();
                        Intent intent = new Intent(context, NewsInfoActivity.class);
                        intent.putExtra("newsurl", url);
                        intent.putExtra("newtitle", mData0.get(0).getTitle());
                        intent.putExtra("time", mData0.get(0).getPosttime());

                        mContext.startActivity(intent);
                    }
                });
                mMyListView0 = (MyListView) mView0.findViewById(R.id.news_listview);
                mMyListView0.setFocusable(false);
                mMyListView0.setVisibility(View.VISIBLE);
                mMyListView0.setAdapter(new NewsAdapter(mData0, mContext));
            }
        });
    }

    public void initData1() {
        OkGo.post(RequestAddress.HOST + RequestAddress.CPFBH).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean, Call call, Response response) {
                KLog.e("initData1");
                mData1 = newsBean.getData();
                RelativeLayout layout = (RelativeLayout) mView1.findViewById(R.id.view_item_layout);
                layout.setVisibility(View.VISIBLE);
                mImageView1 = (ImageView) mView1.findViewById(R.id.news_iamge);
                Picasso.with(mContext).load(RequestAddress.IMAGE2 + mData1.get(0).getPicurl()).into(mImageView1);
                mTextView1 = (TextView) mView1.findViewById(R.id.view_pager_item_textview);
                mTextView1.setText(mData1.get(0).getTitle());
                mImageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mData1.get(0).getContent();
                        Intent intent = new Intent(context, NewsInfoActivity.class);
                        intent.putExtra("newsurl", url);
                        intent.putExtra("newtitle", mData1.get(0).getTitle());
                        intent.putExtra("time", mData1.get(0).getPosttime());

                        mContext.startActivity(intent);
                    }
                });
                mMyListView1 = (MyListView) mView1.findViewById(R.id.news_listview);
                mMyListView1.setFocusable(false);
                mMyListView1.setVisibility(View.VISIBLE);
                MKProgressHUD.dismiss();
                mMyListView1.setAdapter(new NewsAdapter(mData1, mContext));
            }
        });
    }

    /**
     * z解密
     */
    public void initData2() {
        OkGo.post(RequestAddress.HOST + RequestAddress.CPZJM).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                mData2 = newsBean.getData();
                RelativeLayout layout = (RelativeLayout) mView2.findViewById(R.id.view_item_layout);
                layout.setVisibility(View.VISIBLE);
                KLog.e("size", mData2.size());
                mImageView2 = (ImageView) mView2.findViewById(R.id.news_iamge);
                Picasso.with(mContext).load(RequestAddress.IMAGE2 + mData2.get(0).getPicurl()).into(mImageView2);
                mTextView2 = (TextView) mView2.findViewById(R.id.view_pager_item_textview);
                mTextView2.setText(mData2.get(0).getTitle());
                mImageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mData2.get(0).getContent();
                        Intent intent = new Intent(context, NewsInfoActivity.class);
                        intent.putExtra("newsurl", url);
                        intent.putExtra("newtitle", mData2.get(0).getTitle());
                        intent.putExtra("time", mData2.get(0).getPosttime());

                        mContext.startActivity(intent);
                    }
                });
                mMyListView2 = (MyListView) mView2.findViewById(R.id.news_listview);
                mMyListView2.setFocusable(false);
                KLog.e("initData2");
                mMyListView2.setVisibility(View.VISIBLE);
                mMyListView2.setAdapter(new NewsAdapter(mData2, mContext));
            }
        });
    }

    /**
     * 科技风向标
     */
    public void initData3() {
        OkGo.post(RequestAddress.HOST + RequestAddress.XKJJS).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                mData3 = newsBean.getData();
                RelativeLayout layout = (RelativeLayout) mView3.findViewById(R.id.view_item_layout);
                layout.setVisibility(View.VISIBLE);
                KLog.e("initData3");
                mImageView3 = (ImageView) mView3.findViewById(R.id.news_iamge);
                Picasso.with(mContext).load(RequestAddress.IMAGE2 + mData3.get(0).getPicurl()).into(mImageView3);
                mTextView3 = (TextView) mView3.findViewById(R.id.view_pager_item_textview);
                mTextView3.setText(mData3.get(0).getTitle());
                mImageView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mData3.get(0).getContent();
                        Intent intent = new Intent(context, NewsInfoActivity.class);
                        intent.putExtra("newsurl", url);
                        intent.putExtra("newtitle", mData3.get(0).getTitle());
                        intent.putExtra("time", mData3.get(0).getPosttime());

                        mContext.startActivity(intent);
                    }
                });
                mMyListView3 = (MyListView) mView3.findViewById(R.id.news_listview);
                mMyListView3.setFocusable(false);
                mMyListView3.setVisibility(View.VISIBLE);
                mMyListView3.setAdapter(new NewsAdapter(mData3, mContext));
            }
        });
    }

    /**
     * 品牌咨询
     */
    public void initData4() {
        OkGo.post(RequestAddress.HOST + RequestAddress.PPZX).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                mData4 = newsBean.getData();
                RelativeLayout layout = (RelativeLayout) mView4.findViewById(R.id.view_item_layout);
                layout.setVisibility(View.VISIBLE);
                KLog.e("initData4");
                mImageView4 = (ImageView) mView4.findViewById(R.id.news_iamge);
                Picasso.with(mContext).load(RequestAddress.IMAGE2 + mData4.get(0).getPicurl()).into(mImageView4);
                mTextView4 = (TextView) mView4.findViewById(R.id.view_pager_item_textview);
                mTextView4.setText(mData4.get(0).getTitle());
                mImageView4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mData4.get(0).getContent();
                        Intent intent = new Intent(context, NewsInfoActivity.class);
                        intent.putExtra("newsurl", url);
                        intent.putExtra("newtitle", mData4.get(0).getTitle());
                        intent.putExtra("time", mData4.get(0).getPosttime());
                        mContext.startActivity(intent);
                    }
                });
                mMyListView4 = (MyListView) mView4.findViewById(R.id.news_listview);
                mMyListView4.setFocusable(false);
                mMyListView4.setVisibility(View.VISIBLE);
                mMyListView4.setAdapter(new NewsAdapter(mData4, mContext));
            }
        });
    }

    /**
     * 行业交流会
     */
    public void initData5() {
        OkGo.post(RequestAddress.HOST + RequestAddress.HYJLH).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                mData5 = newsBean.getData();
                RelativeLayout layout = (RelativeLayout) mView5.findViewById(R.id.view_item_layout);
                layout.setVisibility(View.VISIBLE);
                KLog.e("initData5");
                mImageView5 = (ImageView) mView5.findViewById(R.id.news_iamge);
                Picasso.with(mContext).load(RequestAddress.IMAGE2 + mData5.get(0).getPicurl()).into(mImageView5);
                mTextView5 = (TextView) mView5.findViewById(R.id.view_pager_item_textview);
                mTextView5.setText(mData5.get(0).getTitle());
                mImageView5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mData5.get(0).getContent();
                        Intent intent = new Intent(context, NewsInfoActivity.class);
                        intent.putExtra("newsurl", url);
                        intent.putExtra("newtitle", mData5.get(0).getTitle());
                        intent.putExtra("time", mData5.get(0).getPosttime());

                        mContext.startActivity(intent);
                    }
                });
                mMyListView5 = (MyListView) mView5.findViewById(R.id.news_listview);
                mMyListView5.setFocusable(false);
                mMyListView5.setVisibility(View.VISIBLE);
                mMyListView5.setAdapter(new NewsAdapter(mData5, mContext));
            }
        });
    }

    /**
     * 产品评测
     */
    public void initData6() {
        OkGo.post(RequestAddress.HOST + RequestAddress.CPPC).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                mData6 = newsBean.getData();
                RelativeLayout layout = (RelativeLayout) mView6.findViewById(R.id.view_item_layout);
                layout.setVisibility(View.VISIBLE);
                KLog.e("initData6");
                mImageView6 = (ImageView) mView6.findViewById(R.id.news_iamge);
                Picasso.with(mContext).load(RequestAddress.IMAGE2 + mData6.get(0).getPicurl()).into(mImageView6);
                mTextView6 = (TextView) mView6.findViewById(R.id.view_pager_item_textview);
                mTextView6.setText(mData6.get(0).getTitle());
                mImageView6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mData6.get(0).getContent();
                        Intent intent = new Intent(context, NewsInfoActivity.class);
                        intent.putExtra("newsurl", url);
                        intent.putExtra("newtitle", mData6.get(0).getTitle());
                        intent.putExtra("time", mData6.get(0).getPosttime());
                        mContext.startActivity(intent);
                    }
                });
                mMyListView6 = (MyListView) mView6.findViewById(R.id.news_listview);
                mMyListView6.setFocusable(false);
                mMyListView6.setVisibility(View.VISIBLE);
                mMyListView6.setAdapter(new NewsAdapter(mData6, mContext));
            }
        });
    }

    /**
     * 消费观察
     */
    public void initData7() {
        OkGo.post(RequestAddress.HOST + RequestAddress.XFGC).execute(new JsonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                mData7 = newsBean.getData();
                RelativeLayout layout = (RelativeLayout) mView7.findViewById(R.id.view_item_layout);
                layout.setVisibility(View.VISIBLE);
                KLog.e("initData7");
                mImageView7 = (ImageView) mView7.findViewById(R.id.news_iamge);
                Picasso.with(mContext).load(RequestAddress.IMAGE2 + mData7.get(0).getPicurl()).into(mImageView6);
                mTextView7 = (TextView) mView7.findViewById(R.id.view_pager_item_textview);
                mTextView7.setText(mData7.get(0).getTitle());
                mImageView7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mData7.get(0).getContent();
                        Intent intent = new Intent(context, NewsInfoActivity.class);
                        intent.putExtra("newsurl", url);
                        intent.putExtra("newtitle", mData7.get(0).getTitle());
                        intent.putExtra("time", mData7.get(0).getPosttime());
                        mContext.startActivity(intent);
                    }
                });
                mMyListView7 = (MyListView) mView7.findViewById(R.id.news_listview);
                mMyListView7.setFocusable(false);
                mMyListView7.setVisibility(View.VISIBLE);
                mMyListView7.setAdapter(new NewsAdapter(mData7, mContext));
            }
        });
    }

    public View NewS0() {
        mView0 = LayoutInflater.from(mContext).inflate(R.layout.viewadapter_item_layout, null);
        return mView0;
    }

    public View NewS1() {
        mView1 = LayoutInflater.from(mContext).inflate(R.layout.viewadapter_item_layout, null);
        return mView1;
    }

    public View NewS2() {
        mView2 = LayoutInflater.from(mContext).inflate(R.layout.viewadapter_item_layout, null);
        return mView2;
    }

    public View NewS3() {
        mView3 = LayoutInflater.from(mContext).inflate(R.layout.viewadapter_item_layout, null);
        return mView3;
    }

    public View NewS4() {
        mView4 = LayoutInflater.from(mContext).inflate(R.layout.viewadapter_item_layout, null);
        return mView4;
    }

    public View NewS5() {
        mView5 = LayoutInflater.from(mContext).inflate(R.layout.viewadapter_item_layout, null);
        return mView5;
    }

    public View NewS6() {
        mView6 = LayoutInflater.from(mContext).inflate(R.layout.viewadapter_item_layout, null);
        return mView6;
    }

    public View NewS7() {
        mView7 = LayoutInflater.from(mContext).inflate(R.layout.viewadapter_item_layout, null);
        return mView7;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    public void back_text_view(View view) {
        finish();
    }
    @OnClick(R.id.button_im)
    public void onClick() {
        button_im();
    }
    public void button_im() {
        if (mUserid != "") {
            login(mUserid);
        } else {
            showNormalDialog();
        }
    }

    //初始化信息后登陆im
    private void login(String selfId) {
        ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
        String appkey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appkey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(mLoginAuthType);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);
        SDKCoreHelper.init(ContFragmentActivity.this, ECInitParams.LoginMode.FORCE_LOGIN);
        Intent intent = new Intent(ContFragmentActivity.this, LauncherActivity.class);
        intent.putExtra("launcher_from", 1);
        // 注册成功跳转
        startActivity(intent);
    }

    private void showNormalDialog() {
        new CBDialogBuilder(ContFragmentActivity.this)
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
                        startActivity(new Intent(ContFragmentActivity.this, LoginsActivity.class));
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
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
