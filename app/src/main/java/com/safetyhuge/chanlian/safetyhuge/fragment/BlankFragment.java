package com.safetyhuge.chanlian.safetyhuge.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.zhouwei.library.CustomPopWindow;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.BannernrBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.ChargeBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.LikeBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.SchemeBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.TalentsBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskList;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.LauncherActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.BazaarProjectActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.BlankSSActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.FinancialInfoActivity.FinancialActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LeaseEquipmentActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.ProductSoppinggActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.ReleaseProjectActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.SchemeHallActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.TalentsHallActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.TechnologyTrainActivity;
import com.safetyhuge.chanlian.safetyhuge.adapter.LikeAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.SchemesAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.TalentsAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.TaskListAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.TechnologyAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyGridView;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyScrollview;
import com.safetyhuge.chanlian.safetyhuge.baseview.view.MultipleItem;
import com.safetyhuge.chanlian.safetyhuge.baseview.view.MultipleItemQuickAdapter;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.BannerUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.FrescoImageLoader;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.sunsky.marqueeview.MarqueeView;
import com.umeng.analytics.MobclickAgent;
import com.yazhi.superswiperefreshlayout.SuperSwipeRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.yuntongxun.ecsdk.ECInitParams;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * 徐艳昭
 * 15326057231@163.com
 * 2017/3/6
 **/
public class BlankFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.banner)
    Banner mBanner;
    private BannerUtils util;
    private Context context;
    //网络安全 防火墙 交换机 负载平衡 云计算 系统安全加固 信锐无线AC控制器 桌面虚拟化 桌面安全管理
    private RelativeLayout wlaq_rel, fhq_rel, jhj_rel, fzph_rel, yjs_rel, aqjgxt_rel, rxwl_rel, zmxnh_rel, zmglaq_rel;
    private MyListView my_listViews;
    private Intent intent;
    private CustomPopWindow mCustomPopWindow;
    private RecyclerView recyclerView;
    private List<MultipleItem> data;
    private RelativeLayout head_search_rr;
    private SuperSwipeRefreshLayout refreshLayout;
    private int mDistanceY;
    private View mView;
    private ImageView mTv_img_yj;
    private ImageView mMessge_img;
    private ArrayList<String> mList;
    private MyScrollview mScrollView;
    private MyGridView mRecyclerView;
    private GridView mMyGridView;
    private MyListView mMy_listViewsRc;
    private MyGridView mMMy_ecyclerView_px;
    String mUserid;
    private ImageView mImageView;
    ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
    KProgressHUD mProgressHUD;
    private int curStr;
    List<String> dataInfo = new ArrayList<>();

    private MarqueeView marqueeView1;
    List<View> views1 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
            KLog.e("id", mUserid);
            mView = inflater.inflate(R.layout.fragment_blank, null);
            context = getActivity();
            intent = new Intent();
            //显示view
            InitView(mView);
            //网络请求
            NetWork();
            //搜索栏
            InitUI();
            InitRefresh();
            initViews();
            initdata();
            initView();
            initViewMore(mView);
            ButterKnife.bind(this, mView);
        }
        ViewGroup viewGroup = (ViewGroup) mView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(mView);
        }
        mProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("开店中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);

        return mView;
    }

    private void initViewMore(View  view) {
        //产品导购
        ImageView chanping = (ImageView) view.findViewById(R.id.more_chanping);
        chanping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("flag", 1);
                intent.setClass(getActivity(), ProductSoppinggActivity.class);//跳转到产品导购页面
                startActivity(intent);
            }
        });
        //项目市场
        ImageView xiangmu = (ImageView) view.findViewById(R.id.more_xiangmu);
        xiangmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("flag", 0);
                intent.setClass(getActivity(), BazaarProjectActivity.class);//跳转到项目市场页面
                startActivity(intent);
            }
        });
        //方案大厅
        ImageView fangan = (ImageView) view.findViewById(R.id.more_fangan);
        fangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("flag", 11);
                intent.setClass(getActivity(), SchemeHallActivity.class);//跳转到方案大厅页面
                startActivity(intent);
            }
        });
        //人才大厅
        ImageView rencai = (ImageView) view.findViewById(R.id.more_rencai);
        rencai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("flag", 12);
                intent.setClass(getActivity(), TalentsHallActivity.class);//跳转到人才大厅页面
                startActivity(intent);
            }
        });
        //特价促销
        ImageView tejia = (ImageView) view.findViewById(R.id.more_tejia);
        tejia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("flag", 3);
                intent.setClass(getActivity(), ProductSoppinggActivity.class);//跳转到产品导购页面
                startActivity(intent);
            }
        });
        //技术培训
        ImageView peixun = (ImageView) view.findViewById(R.id.more_peixun);
        peixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), TechnologyTrainActivity.class);//跳转到技术培训页面
                startActivity(intent);
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initdata() {
        dataInfo = new ArrayList<>();
        dataInfo.add("Apple Pay将支持个人转账集成在iMessage");
        dataInfo.add("微信小程序新动作 注册认证不要材料了");
        dataInfo.add("谷歌登陆古巴，成当地第一家互联网外企");
        dataInfo.add("IPv6协议漏洞将威胁核心路由器安全");
        dataInfo.add("微视酷三步走战略打造中国VR教育新领地");
        dataInfo.add("从虚拟到实体 衍生品也许是VR的下一个方向");
        dataInfo.add("海尔欲引爆物联网 携物联网行业首个操作系统亮相CES");
    }
    /**
     * 初始化界面程序
     */
    private void initView() {
       setViewTwoLines();
        marqueeView1.setViews(views1);
    }


    @Override
    public void onStop() {
        super.onStop();
        marqueeView1.stopFlipping();
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     * 条数为奇数时做了些处理：奇数时最后一个没有内容 将第一个拼接到最后显示
     */
    private void setViewTwoLines() {
        views1.clear();//记得加这句话，不然可能会产生重影现象
        for (int i = 0; i < dataInfo.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(getActivity(), ContFragmentActivity.class);
                    intent1.putExtra("flags", "0");
                    context.startActivity(intent1);
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(getActivity(), ContFragmentActivity.class);
                    intent1.putExtra("flags", "0");
                    context.startActivity(intent1);
                }
            });
            tv1.setText(dataInfo.get(i).toString());
            if (dataInfo.size() > i + 1) {//奇数条
                tv2.setText(dataInfo.get(i + 1).toString());
            } else {//偶数条
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                //moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
                //修改了最后一个没有 将第一个拼接到最后显示
                tv2.setText(dataInfo.get(0).toString());
            }

            //添加到循环滚动数组里面去
            views1.add(moreView);
        }
    }
    private void InitView(View view) {
        //项目市场
        my_listViews = (MyListView) view.findViewById(R.id.my_listViews);
        my_listViews.setFocusable(false);
        //发布
        mImageView = (ImageView) view.findViewById(R.id.tv_img_yj);
        //方案大厅
        mRecyclerView = (MyGridView) view.findViewById(R.id.my_ecyclerView);
        mRecyclerView.setFocusable(false);
        //猜你喜欢
        mMyGridView = (GridView) view.findViewById(R.id.my_gridview_like);
        mMyGridView.setFocusable(false);
        //人才大厅
        mMy_listViewsRc = (MyListView) view.findViewById(R.id.my_gridview_rc);
        mMy_listViewsRc.setFocusable(false);
        //技术培训
        mMMy_ecyclerView_px = (MyGridView) view.findViewById(R.id.my_ecyclerView_px);
        mMMy_ecyclerView_px.setFocusable(false);
        //新闻滚动条
        //mTextView = (TextSwitcher) view.findViewById(R.id.textSwitcher_title);
        ImageView jishu_layout = (ImageView) view.findViewById(R.id.jishu_layout);
        jishu_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), LeaseEquipmentActivity.class);//跳转到设备租赁页面
                context.startActivity(intent);
            }
        });
        //首页搜索
        //blank_sousuo
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.blank_sousuo);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BlankSSActivity.class);
                context.startActivity(intent);
            }
        });
       /* mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), ContFragmentActivity.class);
                intent1.putExtra("flags", "0");
                context.startActivity(intent1);
            }
        });*/
        marqueeView1 = (MarqueeView) view.findViewById(R.id.upview1);
       /* mTextView.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                final TextView tv = new TextView(getActivity());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv.setPadding(20, 50, 20, 50);
                tv.setMaxEms(1);
                // tv.setGravity(Gravity.CENTER);
                return tv;
            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(titles[curStr++ % titles.length]);
                handler.postDelayed(this, 2200);
            }
        }, 2200);*/
    }

    public void initViews() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    showPopMenu();
                } else {
                    showNormalDialog();
                }
            }
        });
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (util != null) {
            util.stop();
            util = null;
        }
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.wlaq_relxyz, R.id.xmsc_rel, R.id.cpdg_rel, R.id.fadt_rel, R.id.rcdt_rel, R.id.jspx_rel, R.id.cxzq_rel, R.id.rssc_rel, R.id.sbzl_rel, R.id.jrzq_rel, R.id.fujinfuwu, R.id.fhq_rel1, R.id.aqjgxt_rel, R.id.rxwl_rel, R.id.zmxnh_rel, R.id.zmglaq_rel})
    public void onClick(View view) {
        switch (view.getId()) {
            //项目市场，产品导购，方案大厅，人才大厅，技术培训，促销专区，二手市场，设备租赁，金融专区，附近服务
            case R.id.xmsc_rel: //项目市场 的点击事件
                intent.putExtra("flag", 0);
                intent.setClass(getActivity(), BazaarProjectActivity.class);//跳转到

                // 页面
                break;
            case R.id.cpdg_rel: //产品导购 的点击事件
                // getActivity().startActivity(new Intent(getActivity(), ProductSoppinggActivity.class));
                //  context.startActivity(new Intent(getActivity(), ProductSoppinggActivity.class));
                intent.putExtra("flag", 1);
                intent.setClass(getActivity(), ProductSoppinggActivity.class);//跳转到产品导购页面
                break;
            case R.id.fadt_rel: //方案大厅 的点击事件
                intent.putExtra("flag", 11);
                intent.setClass(getActivity(), SchemeHallActivity.class);//跳转到方案大厅页面
                break;
            case R.id.rcdt_rel: //人才大厅 的点击事件
                intent.putExtra("flag", 12);
                intent.setClass(getActivity(), TalentsHallActivity.class);//跳转到人才大厅页面
                break;
            case R.id.jspx_rel: //技术培训 的点击事件
                intent.setClass(getActivity(), TechnologyTrainActivity.class);//跳转到技术培训页面
                break;
            case R.id.cxzq_rel: //促销专区 的点击事件
                intent.putExtra("flag", 3);
                intent.setClass(getActivity(), ProductSoppinggActivity.class);//跳转到产品导购页面
                //  intent.setClass(getActivity(), SalesPromotionActivity.class);//跳转到促销专区页面
                break;
            case R.id.rssc_rel: //二手市场 的点击事件
                intent.putExtra("flag", 4);
                intent.setClass(getActivity(), ProductSoppinggActivity.class);//跳转到产品导购页面
                // intent.setClass(getActivity(), SecondHandActivity.class);//跳转到二手市场页面
                break;
            case R.id.sbzl_rel: //设备租赁 的点击事件
                intent.putExtra("flag", 5);
                intent.setClass(getActivity(), ProductSoppinggActivity.class);//跳转到产品导购页面
                //intent.setClass(getActivity(), LeaseEquipmentActivity.class);//跳转到设备租赁页面
                break;
            case R.id.jrzq_rel: //金融专区 的点击事件
                intent.setClass(getActivity(), FinancialActivity.class);//跳转到金融专区页面
                break;
            case R.id.fujinfuwu: //附近服务 的点击事件
                intent.setClass(getActivity(), LeaseEquipmentActivity.class);//跳转到设备租赁页面
                //intent.setClass(getActivity(), NearbyActivity.class);//跳转到附近服务页面
                break;
            case R.id.wlaq_relxyz:
                //intent.setClass(getActivity(), NetworkSecurityActivity.class);//跳转到网络安全页面
            case R.id.fhq_rel1:
            case R.id.aqjgxt_rel:
                //intent.setClass(getActivity(), SystemSReinforceActivity.class);//跳转到系统安全加固页面
                //break;
            case R.id.rxwl_rel:
                //intent.setClass(getActivity(), WirelessACActivity.class);//跳转到信锐无线AC控制器页面
                // break;
            case R.id.zmxnh_rel:
                //intent.setClass(getActivity(), DesktopVirtualizationActivity.class);//跳转到桌面虚拟化页面
                //break;
            case R.id.zmglaq_rel:
                intent.putExtra("flag", 1);
                intent.setClass(getActivity(), ProductSoppinggActivity.class);//跳转到产品导购页面
                // intent.setClass(getActivity(), DesktopSecurityActivity.class);//跳转到桌面安全管理页面
                break;
        }
        startActivity(intent);
    }


    private void InitUI() {
        //  recyclerView = (RecyclerView) mView.findViewById(R.id.my_ecyclerView);
        mScrollView = (MyScrollview) mView.findViewById(R.id.my_scrollView);
        head_search_rr = (RelativeLayout) mView.findViewById(R.id.head_search_rr);
        mTv_img_yj = (ImageView) mView.findViewById(R.id.tv_img_yj);
        mMessge_img = (ImageView) mView.findViewById(R.id.messge_img);
        mScrollView.setOnScollChangedListener(new MyScrollview.OnScollChangedListener() {
            @Override
            public void onScrollChanged(MyScrollview scrollView, int x, int y, int oldx, int oldy) {
                //滑动的距离
                mDistanceY = y;
                //toolbar的高度
                int toolbarHeight = head_search_rr.getBottom();
           /*     KLog.e("mDistanceY",mDistanceY);
               */
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY<=0){
                    return;
                }
                KLog.e("toolbarHeight",toolbarHeight);
                KLog.e("whb",mScrollView.getScrollY());
                if (mScrollView.getScrollY() <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 250;
                    head_search_rr.setBackgroundColor(CommonUtil.getColor(R.color.touming));
                    mTv_img_yj.setImageResource(R.drawable.icon_fabiao_w);
                    mMessge_img.setImageResource(R.drawable.icon_message);
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    mTv_img_yj.setImageResource(R.drawable.icon_fabiao_w);
                    mMessge_img.setImageResource(R.drawable.icon_message);
                    head_search_rr.setBackgroundResource(R.drawable.bg_gradient);
                }
            }
        });
        mMessge_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserid != "") {
                    login(mUserid);
                } else {
                    showNormalDialog();
                }
            }
        });
    }

    //初始化信息后登陆im
    private void login(String selfId) {
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

    private void InitRefresh() {
        refreshLayout = (SuperSwipeRefreshLayout) mView.findViewById(R.id.superlayout);
        refreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int i) {
                switch (i) {
                    case 0:  //type = 0：进入界面时加载的操作 refresh when activity was created

                        //TODO

                        //业务执行完毕要关闭启动加载刷新动画  finish animation
                        break;

                    case 1:  //type = 1：下拉刷新 pull down to refresh
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                refreshLayout.setRefreshing(false);
                                // 加载完数据设置为不刷新状态，将下拉进度收起来
                            }
                        }, 1200);
                        //TODO
                        //业务执行完毕要关闭下拉拉刷新动画  finish animation
                        break;

                    case 2: //type =2: 上拉刷新 pull up to refresh

                        //TODO

                        //业务执行完毕要关闭上拉刷新动画  finish animation
                        break;

                    default:
                        break;
                }
            }
        });
    }

    private void Init() {
        data = getMultipleItemData();
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(data, getActivity());
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), MultipleItem.TYPE_SPAN_SIZE_20);
        recyclerView.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(multipleItemAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
            }

        });
    }


    private List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> data = new ArrayList<>();
        //头信息
        data.add(new MultipleItem(MultipleItem.TYPE_0, mList, MultipleItem.TYPE_SPAN_SIZE_20));
        return data;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public void NetWork() {
        Sylubo();
    }


    //首页轮播图
    public void Sylubo() {
        OkGo.post(RequestAddress.HOST + RequestAddress.BANNERNR).execute(new JsonCallback<BannernrBean>() {
            @Override
            public void onSuccess(BannernrBean bannernrBean, Call call, Response response) {
                KLog.e("走到这里了");
                List<BannernrBean.DataBean> dataBeen = bannernrBean.getData();
                mList = new ArrayList<>();
                for (int i = 0; i < dataBeen.size(); i++) {
                    String imagheUrl = dataBeen.get(i).getPic();
                    String str = imagheUrl.substring(2, imagheUrl.length());
                    mList.add(RequestAddress.IMAGE1 + str);
                    KLog.e(RequestAddress.IMAGE1 + str);
                }
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR).
                        setImageLoader(new FrescoImageLoader()).
                        setImages(mList).
                        setBannerAnimation(Transformer.DepthPage).
                        setIndicatorGravity(BannerConfig.CENTER).
                        setOnBannerClickListener(new OnBannerClickListener() {
                            @Override
                            public void OnBannerClick(int position) {
                            }
                        }).
                        start();
                TaskList();

            }
        });
    }

    //项目市场
    public void TaskList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfo");
        if (mUserid.isEmpty()) {
            KLog.e("0000000000");
            hashMap.put("uid", "0");
        } else {
            KLog.e("mUseridmUserid");
            hashMap.put("uid", mUserid);
        }
        KLog.e("mUserid", mUserid);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<TaskList>() {
            @Override
            public void onSuccess(TaskList taskList, Call call, Response response) {
                KLog.e("项目市场");
                List<TaskList.DataBean> taskListData = taskList.getData();
                if (!taskListData.isEmpty() && taskListData.size() > 3) {
                    TaskListAdapter taskListAdapter = new TaskListAdapter(getActivity(), taskListData, 2);
                    my_listViews.setAdapter(taskListAdapter);
                    scheme(mUserid, "1", "13", "", "", "", "", "", "", "", "");

                } else {
                    TaskListAdapter taskListAdapter = new TaskListAdapter(getActivity(), taskListData, 1);
                    my_listViews.setAdapter(taskListAdapter);
                    scheme(mUserid, "1", "13", "", "", "", "", "", "", "", "");

                }

            }
        });
    }

    //方案大厅
    public void scheme(String uid, String page, String mid,
                       String order, String brand_id,
                       String indus_pid, String indus_id,
                       String province, String city,
                       String area, String sales) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsInfo");
        //用户id
        hashMap.put("uid", "1");
        hashMap.put("mid", "13");
        //分页
        hashMap.put("page", page);
        //人气
        hashMap.put("order", order);
        //品牌id
        hashMap.put("brand_id", brand_id);
        //大分类id
        hashMap.put("indus_pid", indus_pid);
        //小分类id
        hashMap.put("indus_id", indus_id);
        //省
        hashMap.put("province", province);
        //市
        hashMap.put("city", city);
        //镇
        hashMap.put("area", area);
        //产品
        hashMap.put("sales", sales);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<SchemeBean>() {
            @Override
            public void onSuccess(SchemeBean scheme, Call call, Response response) {
                KLog.e("方案大厅");
                List<SchemeBean.DataBean> dataBeen = scheme.getData();
                KLog.e("dataBeendataBeen", dataBeen.size());
                if (!dataBeen.isEmpty() && dataBeen.size() > 6) {
                    SchemesAdapter schemeAdapter = new SchemesAdapter(context, dataBeen);
                    mRecyclerView.setAdapter(schemeAdapter);
                    alents();
                } else {
                    alents();
                }
            }
        });
    }

    //人才大厅
    public void alents() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetUserInfoList");
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new JsonCallback<TalentsBean>() {
            @Override
            public void onSuccess(TalentsBean talentsBean, Call call, Response response) {
                KLog.e("人才大厅");
                List<TalentsBean.DataBean> dataBeen = talentsBean.getData();
                if (!dataBeen.isEmpty() && dataBeen.size() > 3) {
                    mMy_listViewsRc.setAdapter(new TalentsAdapter(context, dataBeen, 1));
                    setListViewHeightBasedOnChildren(mMy_listViewsRc);
                    technology();
                } else {
                    technology();
                }
            }
        });
    }

    //技术培训
    private void technology() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsInfo");
        hashMap.put("mid", "14");
        hashMap.put("uid", "");
        hashMap.put("page", "1");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ChargeBean>() {
            @Override
            public void onSuccess(ChargeBean technologyBean, Call call, Response response) {
                KLog.e("技术培训");
                List<ChargeBean.DataBean> dataBeen = technologyBean.getData();
                if (!dataBeen.isEmpty()) {
                    mMMy_ecyclerView_px.setAdapter(new TechnologyAdapter(context, dataBeen));
                    CLike();
                } else {
                    CLike();
                }

            }
        });
    }

    //猜你喜欢
    private void CLike() {
        OkGo.get(RequestAddress.HOST + RequestAddress.CNXH).execute(new JsonCallback<LikeBean>() {
            @Override
            public void onSuccess(LikeBean likeBean, Call call, Response response) {
                KLog.e("猜你喜欢");
                List<LikeBean.DataBean> dataBeen = likeBean.getData();
                if (!dataBeen.isEmpty()) {
                    mMyGridView.setAdapter(new LikeAdapter(context, dataBeen));
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        KLog.e("onStart");
    }

    public void onResume() {
        super.onResume();
        marqueeView1.startFlipping();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        MobclickAgent.onResume(getActivity());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    private void showPopMenu() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .create()
                .showAsDropDown(mImageView, 0, 0);
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
                        startActivity(new Intent(getActivity(), ReleaseProjectActivity.class));
                        break;
                    case R.id.menu2:
                        NetShop();
                        break;
                    case R.id.menu3:
                        Toast.makeText(context, "请前往web端发布", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
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
                    Intent intent1 = new Intent(getActivity(), FaBuCpActivity.class);
                    startActivity(intent1);
               /*     ChanpingFragmentFactory mFactory = new ChanpingFragmentFactory(0);
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
                    Toast.makeText(getActivity(), "开店成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "开店失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
