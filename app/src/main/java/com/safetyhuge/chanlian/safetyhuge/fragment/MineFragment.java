package com.safetyhuge.chanlian.safetyhuge.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
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
import com.squareup.picasso.Picasso;
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
import q.rorbin.badgeview.QBadgeView;

import static com.safetyhuge.chanlian.safetyhuge.R.id.mine_daipingjia;


/**
 * 徐艳昭
 * 15326057231@163.com
 * 2017/3/6
 **/
public class MineFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.wode)
    ImageView mWode;
    private List<FormGoodsBean.DataBean> mWork_info;
    private List<FormGoodsBean.DataBean> mWork_info1;
    private List<FormGoodsBean.DataBean> mWork_info2;
    private List<FormGoodsBean.DataBean> mWork_info3;
    private List<FormGoodsBean.DataBean> mWork_info4;
    @Bind(R.id.register)
    Button mRegister;
    @Bind(R.id.iv_set)
    ImageView mIvSet;
    @Bind(R.id.tx_imgs)
    ImageView mTxImgs;
    @Bind(R.id.tx_img)
    CircleImageView mTxImg;
    @Bind(R.id.tv_username)
    TextView mTvUsername;
    @Bind(R.id.layout_username)
    LinearLayout mLayoutUsername;
    TextView mTextView4;
    @Bind(R.id.chengxin)
    ImageView chengxin;
    @Bind(R.id.yijian_lin)
    LinearLayout mYijianLin;
    @Bind(R.id.layout_integral)
    LinearLayout mLayoutIntegral;
    @Bind(R.id.mine_chakandingdan)
    TextView mMineChakandingdan;
    @Bind(R.id.mine_daifukuan)
    RelativeLayout mMineDaifukuan;
    @Bind(R.id.mine_daishouhuo)
    RelativeLayout mMineDaishouhuo;
    @Bind(R.id.mine_daifahuo)
    RelativeLayout mMineDaifahuo;
    @Bind(R.id.mine_daipingjia)
    RelativeLayout mMineDaipingjia;
    @Bind(R.id.mine_tuikuan)
    RelativeLayout mMineTuikuan;
    @Bind(R.id.mine_xiaoxi)
    LinearLayout mMineXiaoxi;
    @Bind(R.id.mine_shoucang)
    LinearLayout mMineShoucang;
    @Bind(R.id.mine_qianbao)
    LinearLayout mMineQianbao;
    @Bind(R.id.mine_jilu)
    LinearLayout mMineJilu;
    @Bind(R.id.mine_fananguanli)
    LinearLayout mMineFananguanli;
    @Bind(R.id.mine_xiangmuguanli)
    LinearLayout mMineXiangmuguanli;
    @Bind(R.id.mine_chanpin)
    LinearLayout mMineChanpin;
    @Bind(R.id.mine_toubiao)
    LinearLayout mMineToubiao;
    @Bind(R.id.mine_wenjian)
    LinearLayout mMineWenjian;
    @Bind(R.id.textView3)
    TextView mTextView3;
    @Bind(R.id.imageView2)
    ImageView mImageView2;
    @Bind(R.id.img_fk)
    ImageView mImgFk;
    @Bind(R.id.img_sh)
    ImageView mImgSh;
    @Bind(R.id.img_dfh)
    ImageView mImgDfh;
    @Bind(R.id.img_pj)
    ImageView mImgPj;
    @Bind(R.id.img_tk)
    ImageView mImgTk;
    @Bind(R.id.textView2)
    TextView mTextView2;
    private LinearLayout yijian_lin;
    private Intent intent;
    private String mUserid;
    private Context mContext;
    private String mBalance;
    private String mBalance1;
    private QBadgeView mQBadgeView, mQBadgeView1, mQBadgeView2, mQBadgeView3;
    ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
    KProgressHUD       mProgressHUD;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        intent = new Intent();
        initView(view);
        mContext = getActivity();
        mQBadgeView = new QBadgeView(getActivity());
        mQBadgeView1 = new QBadgeView(getActivity());
        mQBadgeView2 = new QBadgeView(getActivity());
        mQBadgeView3 = new QBadgeView(getActivity());
        ButterKnife.bind(this, view);
        if (mUserid != null) {
            NetWork1(mUserid);
        }
               mProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("开店中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        return view;
    }

    private void initView(View view) {
        yijian_lin = (LinearLayout) view.findViewById(R.id.yijian_lin);
        yijian_lin.setOnClickListener(this);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
                Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + data.getShow_pic()).fit().into(mTxImg);
                mTvUsername.setText(name);
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

    @OnClick({R.id.tx_imgs, R.id.register, R.id.yijian_lin, R.id.iv_set, R.id.mine_chakandingdan, R.id.mine_daifukuan, R.id.mine_daishouhuo, R.id.mine_daifahuo, mine_daipingjia, R.id.mine_tuikuan, R.id.mine_xiaoxi, R.id.mine_shoucang, R.id.mine_qianbao, R.id.mine_jilu, R.id.mine_fananguanli, R.id.mine_xiangmuguanli, R.id.mine_chanpin, R.id.mine_toubiao, R.id.mine_wenjian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tx_imgs:
                startActivity(intent.setClass(getActivity(), LoginsActivity.class));//跳转到一件反馈页面
                break;
            case R.id.register:
                startActivity(intent.setClass(getActivity(), LoginsActivity.class));//跳转到一件反馈页面
                break;
            case R.id.iv_set:
                startActivity(intent.setClass(getActivity(), SettingActivity.class));//跳转到个人设置
                break;
            //查看全部订单
            case R.id.mine_daifukuan:
                if (mUserid != "") {
                    GoodsFragmentFactory mFactory = new GoodsFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "goods", 0);
                    startActivity(intent.setClass(getActivity(), GoodsInfoActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //待付款
            case R.id.mine_daishouhuo:
                if (mUserid != "") {
                    StartIntent(1);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //待收货
            case R.id.mine_daifahuo:
                if (mUserid != "") {
                    StartIntent(2);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //待评价
            case mine_daipingjia:
                if (mUserid != "") {
                    StartIntent(3);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //交易仲裁
            case R.id.mine_tuikuan:
                if (mUserid != "") {
                    StartIntent(4);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //地址管理
            case R.id.mine_xiaoxi:
                if (mUserid != "") {
                    Intent intent = new Intent(getActivity(), ArrdessActivity.class);
                    intent.putExtra("flag", "0");
                    startActivity(intent);//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //我的收藏
            case R.id.mine_shoucang:
                if (mUserid != "") {
                    CollectFragmentFactory mFactory = new CollectFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "shouchang", 0);
                    startActivity(intent.setClass(getActivity(), MineCollectActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //我的钱包
            case R.id.mine_qianbao:
                if (mUserid != "") {
                    Intent intent = new Intent(getActivity(), MineWalletActivity.class);
                    intent.putExtra("intent", mBalance);
                    startActivity(intent);
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("money", mBalance);
                break;
            //交易记录
            case R.id.mine_jilu:
                if (mUserid != "") {
                    startActivity(intent.setClass(getActivity(), TradingActivity.class));
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
            //方案管理
            case R.id.mine_fananguanli:
                if (mUserid != "") {
                    FananFragmentFactory mFactory = new FananFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "fanan", 0);
                    startActivity(intent.setClass(getActivity(), FananGlActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //项目管理
            case R.id.mine_xiangmuguanli:
                if (mUserid != "") {
                    XiangmuFragmentFactory mFactory = new XiangmuFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "xiangmu", 0);
                    startActivity(intent.setClass(getActivity(), XiangmGLActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //产品管理
            case R.id.mine_chanpin:
                if (mUserid != "") {
                    NetShop();
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                if (mUserid != "") {
                    ChanpingFragmentFactory mFactory = new ChanpingFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "chanpin", 0);
                    startActivity(intent.setClass(getActivity(), ChanpinGLActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //投标管理
            case R.id.mine_toubiao:
                if (mUserid != "") {
                    ToubiaoFragmentFactory mFactory = new ToubiaoFragmentFactory(0);
                    mFactory.setFlage(true);
                    SharedPrefsUtil.put(ECApplication.context, "toubiao", 0);
                    startActivity(intent.setClass(getActivity(), ToubiaoGlActivity.class));//跳转到个人设置
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //文件管理
            case R.id.mine_wenjian:
                if (mUserid != "") {
                    startActivity(intent.setClass(getActivity(), FileGltActivity.class));
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            //意见反馈
            case R.id.yijian_lin:
                if (mUserid != "") {
                    startActivity(intent.setClass(getActivity(), EJanActivity.class));//跳转到一件反馈页面
                } else {
                    Toasty.error(ECApplication.context, "请先登录", Toast.LENGTH_SHORT).show();

                }
                //   startActivity(intent.setClass(getActivity(), yanzhengma.class));//跳转到一件反馈页面
                break;
        }
    }

    public void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        MobclickAgent.onResume(getActivity());
        KLog.e("userid", mUserid);
        if (mUserid != "") {
            NetWork(mUserid);
            NetWork1(mUserid);
            mLayoutIntegral.setVisibility(View.GONE);
            mTxImg.setVisibility(View.VISIBLE);
            mTxImgs.setVisibility(View.GONE);
            mRegister.setVisibility(View.GONE);
            mLayoutUsername.setVisibility(View.VISIBLE);
        } else {
            KLog.e("走了走了");
            mLayoutIntegral.setVisibility(View.GONE);
            mQBadgeView.bindTarget(mMineDaishouhuo).setBadgeNumber(0);
            mQBadgeView1.bindTarget(mMineDaishouhuo).setBadgeNumber(0);
            mQBadgeView2.bindTarget(mMineDaishouhuo).setBadgeNumber(0);
            mQBadgeView3.bindTarget(mMineDaishouhuo).setBadgeNumber(0);
            mTxImg.setVisibility(View.GONE);
            mTxImgs.setVisibility(View.VISIBLE);
            mRegister.setVisibility(View.VISIBLE);
            mLayoutUsername.setVisibility(View.GONE);
        }
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    public void StartIntent(int item) {
        Intent intent = new Intent(getContext(), GoodsInfoActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
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
                    mQBadgeView.bindTarget(mMineDaishouhuo).setBadgeNumber(mWork_info1.size());
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
                    mQBadgeView1.bindTarget(mMineDaifahuo).setBadgeNumber(mWork_info2.size());
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
                        mQBadgeView2.bindTarget(mMineDaipingjia).setBadgeNumber(mWork_info3.size());
                    }
                    //仲裁
                    if (mWork_info != null) {
                        for (FormGoodsBean.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getOrder_status();
                            if (str.equals("arbitral")) {
                                mWork_info4.add(workInfoBean);
                            }
                        }
                        mQBadgeView3.bindTarget(mMineTuikuan).setBadgeNumber(mWork_info4.size());
                    }
                }
            }
        });
    }

    @OnClick(R.id.wode)
    public void onClick() {
        if (mUserid != "") {
         /*   Intent intent = new Intent(getActivity(), LauncherActivity.class);
            intent.putExtra("launcher_from", 1);
            // 注册成功跳转
            startActivity(intent);*/
            login(mUserid);
          //  startActivity(new Intent(getActivity(),MiInformationActivity.class));
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
    private void login(String  selfId) {
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

}
