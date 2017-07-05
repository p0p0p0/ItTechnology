package com.safetyhuge.chanlian.safetyhuge.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.FormGoodsBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.MyBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.PersonageBean;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.LauncherActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.EJanActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.SettingActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ArrdessActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ChanpingFragmentInfo.ChanpinGLActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ChanpingFragmentInfo.ChanpingFragmentFactory;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.CollectFragmentInfo.CollectFragmentFactory;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.CollectFragmentInfo.MineCollectActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.FananFragmentInfo.FananFragmentFactory;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.FananFragmentInfo.FananGlActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.FileGltActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.GoodsFragmentInfo.GoodsFragmentFactory;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.GoodsFragmentInfo.GoodsInfoActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.MineWalletActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.SaleGoodsFragmentInfo.SaleGoodsFragmentFactory;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.SaleGoodsFragmentInfo.SaleGoodsInfoActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ToubiaoFragmentInfo.ToubiaoFragmentFactory;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ToubiaoFragmentInfo.ToubiaoGlActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.TradingActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.XiangmuFragmentInfo.XiangmGLActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.XiangmuFragmentInfo.XiangmuFragmentFactory;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.yuntongxun.ecsdk.ECInitParams;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by F57 on 2017/6/18.
 */

public class MyFragment extends Fragment {
    @Bind(R.id.iv_set)
    ImageView mIvSet;
    @Bind(R.id.wode)
    ImageView mWode;
    @Bind(R.id.my_text)
    TextView mMyText;
    @Bind(R.id.my_viewpager)
    ViewPager mMyViewpager;
    @Bind(R.id.zoule1_button)
    ImageView mZoule1Button;
    @Bind(R.id.zoule2_button)
    ImageView mZoule2Button;
    @Bind(R.id.tx_img)
    CircleImageView mTxImg;
    @Bind(R.id.main_tx_image)
    ImageView mMainTxImage;
    @Bind(R.id.main_name)
    TextView mMainName;
    @Bind(R.id.guanli_dizhi)
    LinearLayout mGuanliDizhi;
    @Bind(R.id.guanli_shouchang)
    LinearLayout mGuanliShouchang;
    @Bind(R.id.guanli_qianbao)
    LinearLayout mGuanliQianbao;
    @Bind(R.id.guanli_jilu)
    LinearLayout mGuanliJilu;
    @Bind(R.id.guanli_fangan)
    LinearLayout mGuanliFangan;
    @Bind(R.id.guanli_xiangmu)
    LinearLayout mGuanliXiangmu;
    @Bind(R.id.guanli_toubiao)
    LinearLayout mGuanliToubiao;
    @Bind(R.id.guanli_chanping)
    LinearLayout mGuanliChanping;
    @Bind(R.id.guanli_wenjian)
    LinearLayout mGuanliWenjian;
    @Bind(R.id.guanli_fankui)
    LinearLayout mGuanliFankui;
    private ArrayList<View> mViews;
    private View mView1;
    private View mView0;
    private String mUserid;
    Intent intent;
    private Context mContext;
    private List<FormGoodsBean.DataBean> mWork_info;
    private List<FormGoodsBean.DataBean> mWork_info1;
    private List<FormGoodsBean.DataBean> mWork_info2;
    private List<FormGoodsBean.DataBean> mWork_info3;
    private List<FormGoodsBean.DataBean> mWork_info4;

    private List<FormGoodsBean.DataBean> mWork_info0;
    private List<FormGoodsBean.DataBean> mWork_info11;
    private List<FormGoodsBean.DataBean> mWork_info22;
    private List<FormGoodsBean.DataBean> mWork_info33;
    private List<FormGoodsBean.DataBean> mWork_info44;
    private TextView mFukuan;
    private TextView mShouhuo;
    private TextView mPingjia;
    private TextView mZhongcai;
    private String mBalance;
    private TextView mFukuan1;
    private TextView mShouhuo1;
    private TextView mPingjia1;
    private TextView mZhongcai1;
    KProgressHUD mProgressHUD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_main, null);
        ButterKnife.bind(this, view);
        mViews = new ArrayList<View>();
        mViews.add(NewS0());
        mViews.add(NewS1());
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        intent = new Intent();
        mContext = getActivity();
        InitView();
        if (mUserid != null) {
            NetWork1(mUserid);
            NetWork2(mUserid);
        }
         mProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("开店中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        return view;
    }

    private void InitView() {
        mMyViewpager.setAdapter(pagerAdapter);
        mMyViewpager.setPageTransformer(true, new DepthPageTransformer());
        mMyViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                KLog.e("当前页面", position);
                switch (position) {
                    case 0:
                        mMyText.setText("购买订单");
                        mZoule1Button.setImageResource(R.drawable.icon_update_point);
                        mZoule2Button.setImageResource(R.mipmap.point2);
                     /*   if (mData0==null){
                            initData0();
                        }*/
                        break;
                    case 1:
                        mMyText.setText("售出订单");
                       /* if (mData1==null){
                            MKProgressHUD.show();
                            initData1();
                        }*/
                        mZoule1Button.setImageResource(R.mipmap.point2);
                        mZoule2Button.setImageResource(R.drawable.icon_update_point);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void NetWork1(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "BuyerGoodsOrder");
        hashMap.put("uid", id);
        hashMap.put("mid", "6");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<FormGoodsBean>() {
            @Override
            public void onSuccess(FormGoodsBean taskList, Call call, Response response) {
                mWork_info = taskList.getData();
                KLog.e("mWork_info", mWork_info);
                mWork_info1 = new ArrayList<FormGoodsBean.DataBean>();
                mWork_info2 = new ArrayList<FormGoodsBean.DataBean>();
                mWork_info3 = new ArrayList<FormGoodsBean.DataBean>();
                mWork_info4 = new ArrayList<FormGoodsBean.DataBean>();
                //待付款
                if (mWork_info != null) {
                    for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                        String str = workInfoBean.getOrder_status();
                        if (str.equals("wait")) {
                            mWork_info1.add(workInfoBean);
                        }
                    }
                    mFukuan.setText(mWork_info1.size() + "");
                } else {
                    KLog.e("走了这里");

                }
                //待发货
                if (mWork_info != null) {
                    for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                        String str = workInfoBean.getOrder_status();
                        if (str.equals("ok")) {
                            mWork_info2.add(workInfoBean);
                        }
                    }
                    mShouhuo.setText(mWork_info2.size() + "");
                }
                //待评价
                if (mWork_info != null) {
                    for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                        Object str = workInfoBean.getMark_status();
                        KLog.e("getMark_status", str + "");
                        if (str != null) {
                            if (str.equals("0")) {
                                mWork_info3.add(workInfoBean);
                            }
                        }
                    }
                    mPingjia.setText(mWork_info3.size() + "");

                }
                //仲裁
                if (mWork_info != null) {
                    for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                        String str = workInfoBean.getOrder_status();
                        if (str.equals("arbitral")) {
                            mWork_info4.add(workInfoBean);
                        }
                    }
                    mZhongcai.setText(mWork_info4.size() + "");

                }
            }
        });
    }

    public void NetWork2(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "SellerGoodsOrder");
        hashMap.put("uid", id);
        hashMap.put("mid", "6");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<FormGoodsBean>() {
            @Override
            public void onSuccess(FormGoodsBean taskList, Call call, Response response) {
                mWork_info0 = taskList.getData();
                KLog.e("mWork_info", mWork_info0);
                mWork_info11 = new ArrayList<FormGoodsBean.DataBean>();
                mWork_info22 = new ArrayList<FormGoodsBean.DataBean>();
                mWork_info33 = new ArrayList<FormGoodsBean.DataBean>();
                mWork_info44 = new ArrayList<FormGoodsBean.DataBean>();
                //待付款
                if (mWork_info0 != null) {
                    for (FormGoodsBean.DataBean workInfoBean : mWork_info0) {
                        String str = workInfoBean.getOrder_status();
                        if (str.equals("wait")) {
                            mWork_info11.add(workInfoBean);
                        }
                    }
                    mFukuan1.setText(mWork_info11.size() + "");
                } else {
                    KLog.e("走了这里");

                }
                //待发货
                if (mWork_info0 != null) {
                    for (FormGoodsBean.DataBean workInfoBean : mWork_info0) {
                        String str = workInfoBean.getOrder_status();
                        if (str.equals("ok")) {
                            mWork_info22.add(workInfoBean);
                        }
                    }
                    mShouhuo1.setText(mWork_info22.size() + "");
                }
                //待评价
                if (mWork_info0 != null) {
                    for (FormGoodsBean.DataBean workInfoBean : mWork_info0) {
                        Object str = workInfoBean.getMark_status();
                        KLog.e("getMark_status", str + "");
                        if (str != null) {
                            if (str.equals("0")) {
                                mWork_info33.add(workInfoBean);
                            }
                        }
                    }
                    mPingjia1.setText(mWork_info33.size() + "");

                }
                //仲裁
                if (mWork_info0 != null) {
                    for (FormGoodsBean.DataBean workInfoBean : mWork_info0) {
                        String str = workInfoBean.getOrder_status();
                        if (str.equals("arbitral")) {
                            mWork_info4.add(workInfoBean);
                        }
                    }
                    mZhongcai1.setText(mWork_info44.size() + "");

                }
            }
        });
    }

    public View NewS0() {
        mView0 = LayoutInflater.from(getActivity()).inflate(R.layout.main_pager1, null);
        mFukuan = (TextView) mView0.findViewById(R.id.pager_fukuan1);
        mShouhuo = (TextView) mView0.findViewById(R.id.pager_shouhuo1);
        mPingjia = (TextView) mView0.findViewById(R.id.pager_pingjia1);
        mZhongcai = (TextView) mView0.findViewById(R.id.pager_zhongcai1);
        LinearLayout layoutfukuan = (LinearLayout) mView0.findViewById(R.id.layout_fukuan);
        layoutfukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    StartIntent(1);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LinearLayout layoutshouhuo = (LinearLayout) mView0.findViewById(R.id.layout_shouhuo);
        layoutshouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    StartIntent(2);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LinearLayout layoutpingjia = (LinearLayout) mView0.findViewById(R.id.layout_pingjia);
        layoutpingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    StartIntent(3);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LinearLayout layoutzhongcai = (LinearLayout) mView0.findViewById(R.id.layout_zhongcai);
        layoutzhongcai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    StartIntent(4);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LinearLayout layoutquanbu = (LinearLayout) mView0.findViewById(R.id.layout_quanbu);
        layoutquanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    GoodsFragmentFactory mFactory = new GoodsFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "goods", 0);
                    startActivity(intent.setClass(getActivity(), GoodsInfoActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mView0;
    }

    public View NewS1() {
        mView1 = LayoutInflater.from(getActivity()).inflate(R.layout.main_pager2, null);
        mFukuan1 = (TextView) mView1.findViewById(R.id.pager_fukuan2);
        mShouhuo1 = (TextView) mView1.findViewById(R.id.pager_shouhuo2);
        mPingjia1 = (TextView) mView1.findViewById(R.id.pager_pingjia2);
        mZhongcai1 = (TextView) mView1.findViewById(R.id.pager_zhongcai2);
        LinearLayout layoutfukuan = (LinearLayout) mView1.findViewById(R.id.layout_fukuan1);
        LinearLayout layoutshouhuo = (LinearLayout) mView1.findViewById(R.id.layout_shouhuo1);
        LinearLayout layoutpingjia = (LinearLayout) mView1.findViewById(R.id.layout_pingjia1);
        LinearLayout layoutzhongcai = (LinearLayout) mView1.findViewById(R.id.layout_zhongcai1);
        LinearLayout layoutquanbu = (LinearLayout) mView1.findViewById(R.id.layout_quanbu1);
        //待付款
        layoutfukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    StartIntent1(1);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //待收货
        layoutshouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    StartIntent1(2);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //待评价
        layoutpingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    StartIntent1(3);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //交易仲裁
        layoutzhongcai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    StartIntent1(4);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //全部订单
        layoutquanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    SaleGoodsFragmentFactory mFactory = new SaleGoodsFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "goods", 0);
                    startActivity(intent.setClass(getActivity(), SaleGoodsInfoActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return mView1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mViews.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(mViews.get(position));


            return mViews.get(position);
        }
    };

    @OnClick({R.id.iv_set, R.id.main_tx_image, R.id.guanli_dizhi, R.id.guanli_shouchang, R.id.guanli_qianbao, R.id.guanli_jilu, R.id.guanli_fangan, R.id.guanli_xiangmu, R.id.guanli_toubiao, R.id.guanli_chanping, R.id.guanli_wenjian, R.id.guanli_fankui})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tx_image:
                startActivity(intent.setClass(getActivity(), LoginsActivity.class));//跳转到一件反馈页面
                break;
            case R.id.iv_set:
                startActivity(intent.setClass(getActivity(), SettingActivity.class));//跳转到个人设置
                break;
            case R.id.guanli_dizhi:
                if (mUserid != "") {
                    Intent intent = new Intent(getActivity(), ArrdessActivity.class);
                    intent.putExtra("flag", "0");
                    startActivity(intent);//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanli_shouchang:
                if (mUserid != "") {
                    CollectFragmentFactory mFactory = new CollectFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "shouchang", 0);
                    startActivity(intent.setClass(getActivity(), MineCollectActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanli_qianbao:
                if (mUserid != "") {
                    Intent intent = new Intent(getActivity(), MineWalletActivity.class);
                    intent.putExtra("intent", mBalance);
                    startActivity(intent);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("money", mBalance);
                break;
            case R.id.guanli_jilu:
                if (mUserid != "") {
                    startActivity(intent.setClass(getActivity(), TradingActivity.class));
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanli_fangan:
                if (mUserid != "") {
                    FananFragmentFactory mFactory = new FananFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "fanan", 0);
                    startActivity(intent.setClass(getActivity(), FananGlActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanli_xiangmu:
                if (mUserid != "") {
                    XiangmuFragmentFactory mFactory = new XiangmuFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "xiangmu", 0);
                    startActivity(intent.setClass(getActivity(), XiangmGLActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanli_toubiao:
                if (mUserid != "") {
                    ToubiaoFragmentFactory mFactory = new ToubiaoFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "toubiao", 0);
                    startActivity(intent.setClass(getActivity(), ToubiaoGlActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanli_chanping:
                if (mUserid != "") {
                    NetShop();
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanli_wenjian:
                if (mUserid != "") {
                    startActivity(intent.setClass(getActivity(), FileGltActivity.class));
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanli_fankui:
                if (mUserid != "") {
                    startActivity(intent.setClass(getActivity(), EJanActivity.class));//跳转到一件反馈页面
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
    MaterialDialog mMaterialDialog;
    private void NetShop() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "IsOpenShop");
        hashMap.put("uid", mUserid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                ResponseBody body = response.body();
                KLog.e("whb", body);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                KLog.e("whb", s);
                Object code = mapForJson.get("code");
                if (code.equals("888")) {
                    ChanpingFragmentFactory mFactory = new ChanpingFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "chanpin", 0);
                    startActivity(intent.setClass(getActivity(), ChanpinGLActivity.class));//跳转到个人设置*/
                }else{
                    mMaterialDialog = new MaterialDialog(getActivity())
                            .setTitle("提示")
                            .setMessage("您还没有开启店铺。是否开店？")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mProgressHUD.show();
                                    mMaterialDialog.dismiss();
                                    OpenShop();
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            });

                    mMaterialDialog.show();
                }
            }
        });
    }

    private void OpenShop() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "OpenShop");
        hashMap.put("uid", mUserid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                ResponseBody body = response.body();
                KLog.e("whb", body);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                KLog.e("whb", s);
                Object code = mapForJson.get("code");
                if (code.equals("888")) {
                    mProgressHUD.dismiss();
                    Toast.makeText(mContext, "开店成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "开店失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        MobclickAgent.onResume(getActivity());
        KLog.e("userid", mUserid);
        if (mUserid != "") {
            NetWork(mUserid);
            NetWork1(mUserid);
            NetWork2(mUserid);
            mMainTxImage.setVisibility(View.GONE);
            mTxImg.setVisibility(View.VISIBLE);
        } else {
            mMainTxImage.setVisibility(View.VISIBLE);
            mTxImg.setVisibility(View.GONE);
            mMainName.setText("点击头像进行登录");
            mFukuan.setText("0");
            mShouhuo.setText("0");
            mPingjia.setText("0");
            mZhongcai.setText("0");
            mFukuan1.setText("0");
            mShouhuo1.setText("0");
            mPingjia1.setText("0");
            mZhongcai1.setText("0");
            KLog.e("走了走了");
        }
    }

    public void NetWork(String userid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetLoginUserInfo");
        hashMap.put("uid", userid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new JsonCallback<MyBean>() {
            @Override
            public void onSuccess(MyBean myBean, Call call, Response response) {
                MyBean.DataBean data = myBean.getData();
                PersonageBean mPersonageBean = new PersonageBean();
                String name = data.getUsername();
                String uid = data.getUid();
                /*if (name != "") {
                    if (chengxin != null) {
                        chengxin.setVisibility(View.VISIBLE);
                    }
                }*/
                String email = data.getEmail();
                Object qq1 = data.getQq();
                Object phone = data.getMobile();
                Object address = data.getAddress();
                mBalance = data.getBalance();
                Object pic = data.getShow_pic();
                ImageLoader.getInstance().loadImage(RequestAddress.IMAGE1 + data.getShow_pic(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        KLog.e("whb", "加载图片了+");
                        mTxImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
                // Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + data.getShow_pic()).fit().into(mTxImg);
                mMainName.setText(name);
                KLog.e("name", name);
                KLog.e("email", email);
                KLog.e("qq1", qq1);
                KLog.e("phone", phone);
                KLog.e("address", address);
                //data/avatar/000/00/00/84_avatar_big.jpg  data/avatar/000/00/00/84_avatar_big.jpg
                KLog.e("pic", pic);
                SharedPrefsUtil.put(ECApplication.context, "UserUid", uid);
                SharedPrefsUtil.put(ECApplication.context, "name", name);
                if (mBalance != null) {
                    SharedPrefsUtil.put(ECApplication.context, "mBalance", mBalance);
                }
                if (phone != null) {
                    SharedPrefsUtil.put(ECApplication.context, "phone", phone);
                }
                if (address != null) {
                    SharedPrefsUtil.put(ECApplication.context, "address", address);
                }
                if (email != null) {
                    SharedPrefsUtil.put(ECApplication.context, "email", email);
                }
                if (pic != null) {
                    SharedPrefsUtil.put(ECApplication.context, "prcurl", pic);
                }
                KLog.e(mPersonageBean.toString()
                );

            }
        });
    }

    public void StartIntent(int item) {
        Intent intent = new Intent(getContext(), GoodsInfoActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    public void StartIntent1(int item) {
        Intent intent = new Intent(getContext(), SaleGoodsInfoActivity.class);
        intent.putExtra("item", item);
        KLog.e("whb", item + "");
        startActivity(intent);
    }

    @OnClick(R.id.wode)
    public void onClick() {
        if (mUserid != "") {
            login(mUserid);
        } else {
            showNormalDialog();
        }
    }

    private void showNormalDialog() {
        new CBDialogBuilder(getActivity())
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
                        startActivity(new Intent(getActivity(), LoginsActivity.class));
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
        SDKCoreHelper.init(getActivity(), ECInitParams.LoginMode.FORCE_LOGIN);
        Intent intent = new Intent(getActivity(), LauncherActivity.class);
        intent.putExtra("launcher_from", 1);
        // 注册成功跳转
        startActivity(intent);
    }
}

class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}





