package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsFragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsXgBean;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.ChattingActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.ChattingFragment;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.IndentActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.adapter.GoodsXgAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyGridView;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.GoodsCartBean.LookCart;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.FrescoImageLoader;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.StringUtils;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhl.cbdialog.CBDialogBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;

/**
 * 商品详情
 * 作者：王海宾 on 2017/5/3 0003 14:50
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class GoodsDetailsFragment extends Fragment {
    String goodsid;
    @Bind(R.id.goods_images)
    Banner mBanner;
    @Bind(R.id.goods_title)
    TextView mGoodsTitle;
    @Bind(R.id.goods_share)
    RelativeLayout mGoodsShare;
    @Bind(R.id.goods_money)
    TextView mGoodsMoney;
    @Bind(R.id.goods_volume)
    TextView mGoodsVolume;
    @Bind(R.id.goods_evaluate_count)
    TextView mGoodsEvaluateCount;
    @Bind(R.id.web_view)
    TextView mWebView;
    @Bind(R.id.goods_mygv)
    MyGridView mGoodsMygv;
    @Bind(R.id.sc_goods)
    ScrollView mScGoods;
    @Bind(R.id.shoucang_image)
    ImageView mShoucangImage;
    @Bind(R.id.viewpager_shouchang)
    TextView mViewpagerShouchang;
    @Bind(R.id.goods_collect)
    LinearLayout mGoodsCollect;
    @Bind(R.id.goods_report)
    LinearLayout mGoodsReport;
    @Bind(R.id.goods_cart)
    Button mGoodsCart;
    @Bind(R.id.goods_purchase)
    Button mGoodsPurchase;
    @Bind(R.id.layout_gather)
    LinearLayout mLayoutGather;
    @Bind(R.id.item1_iamge)
    CircleImageView mItem1Iamge;
    @Bind(R.id.item1_name)
    TextView mItem1Name;
    @Bind(R.id.item1_time)
    TextView mItem1Time;
    @Bind(R.id.item_pingjia_layout)
    RelativeLayout mItemPingjiaLayout;
    @Bind(R.id.item1_content)
    TextView mItem1Content;
    @Bind(R.id.item1_button)
    Button mItem1Button;
    private boolean mFlag;
    private String mUserid;
    private KProgressHUD mKProgressHUD;
    private String mUsername;
    public boolean b = true;
    private List<GoodsBean.DataBean.MarkInfoBean.AidinfoBean> mAidinfo;
    GoodsBean.DataBean data;
    List<GoodsBean.DataBean.MarkInfoBean> mMarkInfo;
    private int mNum;
    GoodsMinuteActivity goodsMinuteActivity;
    private String mName;
    private String mPrcurl;

    public GoodsDetailsFragment(String goodsid, GoodsBean.DataBean data, List<GoodsBean.DataBean.MarkInfoBean> mMarkInfo, GoodsMinuteActivity goodsMinuteActivity) {
        this.goodsid = goodsid;
        this.data = data;
        this.mMarkInfo = mMarkInfo;
        this.goodsMinuteActivity = goodsMinuteActivity;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mName = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        mPrcurl = (String) SharedPrefsUtil.get(ECApplication.context, "prcurl", "");

        KLog.e("muserid", mUserid);
        View view = inflater.inflate(R.layout.viewpager_item1, container, false);
        ButterKnife.bind(this, view);
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        InitData(data);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.goods_cart, R.id.goods_purchase, R.id.goods_collect, R.id.goods_report, R.id.item1_button})
    public void onClick(View view) {
        switch (view.getId()) {
            //加入购物车
            case R.id.goods_cart:
                if (mUserid != "") {
                    if (mNum >= 1) {
                        jiaruGoods(mUserid, goodsid, "1");
                    } else {
                        Toast.makeText(ECApplication.context, "库存不足", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNormalDialog();
                }
                break;
            //立即购买
            case R.id.goods_purchase:
                KLog.e("mNum", mNum);
                if (mUserid != "") {
                    if (mNum > 1) {
                        List<LookCart.DataBean> checkoutShops = new ArrayList<>();
                        List<LookCart.DataBean.DetailsBean> checkoutProducts = new ArrayList<>();
                        LookCart.DataBean dataBean = new LookCart.DataBean();
                        dataBean.setUsername(data.getUsername());
                        LookCart.DataBean.DetailsBean detailsBean = new LookCart.DataBean.DetailsBean();
                        //商品标题
                        detailsBean.setTitle(data.getTitle());
                        detailsBean.setInvoice(data.getInvoice());
                        //商品数量
                        detailsBean.setAmount("1");
                        //商品价格
                        detailsBean.setPrice(data.getPrice());
                        //商品图片
                        detailsBean.setPic(data.getPic());
                        checkoutProducts.add(detailsBean);
                        checkoutShops.add(dataBean);
                        dataBean.setDetails(checkoutProducts);
                        Intent intent = new Intent(goodsMinuteActivity, IndentActivity.class);
                        intent.putExtra("checkoutShops", (Serializable) checkoutShops);
                        intent.putExtra("checkoutProductsList", (Serializable) checkoutProducts);
                        intent.putExtra("money", data.getPrice() + "");
                        intent.putExtra("mBuilder1", "1" + ",");
                        intent.putExtra("flag", "1");
                        intent.putExtra("mBuilderId", data.getService_id() + ",");
                        intent.putExtra("mBuilderSId", data.getShop_id() + ",");
                        KLog.e("checkoutShops", checkoutShops.size());
                        KLog.e("money", data.getPrice());
                        KLog.e("mBuilderId", data.getService_id());
                        KLog.e("mBuilderSId", data.getShop_id());
                        KLog.e("getInvoice", data.getInvoice());
                        // data.getInvoice();
                        goodsMinuteActivity.startActivity(intent);
                    } else {
                        Toast.makeText(ECApplication.context, "库存不足", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNormalDialog();
                }
                break;
            case R.id.goods_collect:
                if (mFlag != false) {
                    quxiaoshoucang(mUserid, goodsid);
                } else {
                    if (b == true) {
                        if (mUserid != "") {
                            shoucang(mUserid, goodsid);
                        } else {
                            showNormalDialog();
                        }
                    } else {
                        quxiaoshoucang(mUserid, goodsid);
                    }
                    b = false;
                }
                break;
            //联系卖家
            case R.id.goods_report:
                if (mUserid != "") {
                    if (data.getUid().equals(mUserid)){
                        Toast.makeText(goodsMinuteActivity, "不能联系自己", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        //im
                        initContact(data.getUid(), data.getUsername());
                        KLog.e("whb",data.getUsername()+","+data.getPic());
                        KLog.e("whb",data.getUid()+","+data.getUsername());
                    }
                } else {
                    showNormalDialog();
                }
                break;
            case R.id.item1_button:
                GoodsMinuteActivity activity = (GoodsMinuteActivity) getActivity();
                activity.TiHuan();
                break;
        }
    }

    @SuppressLint("WrongConstant")
    public void aa() {
        if (!mMarkInfo.isEmpty()) {
            mAidinfo = mMarkInfo.get(0).getAidinfo();
            if (!mAidinfo.isEmpty()) {
                mItemPingjiaLayout.setVisibility(View.VISIBLE);
                mGoodsEvaluateCount.setText("(" + mMarkInfo.size() + ")");
                String pic = mMarkInfo.get(0).getPic();
                Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + pic).fit().into(mItem1Iamge);
                mItem1Name.setText(mMarkInfo.get(0).getBy_username());
                String mark_time = mMarkInfo.get(0).getMark_time();
                mItem1Time.setText(DateUtils.time(mark_time));
                String mark_content = mMarkInfo.get(0).getMark_content();
                if (mark_content.equals("")) {
                    mItem1Content.setText("该用户未填写评价");
                } else {
                    mItem1Content.setText(mark_content);
                }
            }
        } else {
            mItemPingjiaLayout.setVisibility(View.INVISIBLE);
            mItem1Content.setText("该产品还未收到过评价");
        }
    }

    @SuppressLint("WrongConstant")
    public void InitData(GoodsBean.DataBean dataBeen) {
        mGoodsMygv.setFocusable(false);
        mKProgressHUD.dismiss();
        aa();
        mScGoods.setVisibility(View.VISIBLE);
        mLayoutGather.setVisibility(View.VISIBLE);
        ArrayList<String> mList = new ArrayList<>();
        List<String> show_pic = dataBeen.getShow_pic();
        mFlag = dataBeen.isFavorite();
        KLog.e("收藏状态", mFlag);
        mGoodsTitle.setText(dataBeen.getTitle());
        for (int i = 0; i < dataBeen.getShow_pic().size(); i++) {
            String imagheUrl = dataBeen.getShow_pic().get(i);
            mList.add(RequestAddress.IMAGE1 + "/" + imagheUrl);
            KLog.e("iamgeimage", RequestAddress.IMAGE1 + imagheUrl);
        }
        if (mFlag != false) {
            mViewpagerShouchang.setText("已收藏");
            mShoucangImage.setImageResource(R.drawable.icon_shoucang_sel);
        }
        mNum = dataBeen.getNum();
        mGoodsMoney.setText("￥" + dataBeen.getPrice() + "");
        mGoodsVolume.setText("销量" + dataBeen.getSale_num());
        String s = dataBeen.getContent();
        //需要html转换格式
        mWebView.setText(StringUtils.Transition(s));
        KLog.e("html", s.toString());
        mUsername = dataBeen.getUsername();
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
        correlation(mUsername);
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
    }

    //相关产品
    public void correlation(String name) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", name);
        KLog.e("name", name);
        OkGo.post(RequestAddress.HOST + RequestAddress.XGCP).params(hashMap).execute(new JsonCallback<GoodsXgBean>() {
            @Override
            public void onSuccess(GoodsXgBean goodsXgBean, Call call, Response response) {
                List<GoodsXgBean.DataBean> dataBeen = goodsXgBean.getData();
                if (mGoodsMygv != null) {
                    mGoodsMygv.setAdapter(new GoodsXgAdapter(getActivity(), dataBeen));
                }
            }
        });
    }

    //取消收藏
    public void quxiaoshoucang(String uid, String sid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "DelFavorite");
        hashMap.put("uid", uid);
        hashMap.put("sid", sid);
        hashMap.put("type", "service");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json("shoucang", s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                if (code.equals("888")) {
                    mViewpagerShouchang.setText("收藏");
                    Toast.makeText(ECApplication.context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                    mShoucangImage.setImageResource(R.drawable.icon_shoucang_nor);
                }
            }
        });
    }

    //收藏
    public void shoucang(String uid, String sid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "AddFavorite");
        hashMap.put("uid", uid);
        hashMap.put("sid", sid);
        hashMap.put("type", "service");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json("shoucang", s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                if (code.equals("888")) {
                    Toast.makeText(ECApplication.context, "收藏成功", Toast.LENGTH_SHORT).show();
                    mShoucangImage.setImageResource(R.drawable.icon_shoucang_sel);
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

    //加入购物车
    public void jiaruGoods(String uid, String sid, String buy_num) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "AddDesired");
        hashMap.put("uid", uid);
        hashMap.put("sid", sid);
        hashMap.put("type", "service");
        hashMap.put("buy_num", buy_num);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json("shoucang", s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                String error = (String) map.get("error");
                if (code.equals("888")) {
                    Toast.makeText(ECApplication.context, "加入购物车成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ECApplication.context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //初始化联系人信息 并把信息传到聊天界面
    private void initContact(String contactId, String name) {
   /*     *//** 消息发送者name *//*
        private String Sender_NickName = "";
        *//** 消息发送者头像地址 *//*
        private String Sender_ImageUrl = "";
        *//** 消息接收者name *//*
        private String Receiver_NickName = "";
        *//** 消息接收者头像地址 *//*
        private String Receiver_ImageUrl = "";*/
        Intent intent = new Intent(getActivity(), ChattingActivity.class);
        intent.putExtra(ChattingFragment.RECIPIENTS, contactId);//相当于对方用户名
        intent.putExtra(ChattingFragment.CONTACT_USER, name);//相当于对方昵称
        intent.putExtra(ChattingFragment.CUSTOMER_SERVICE, false);
        //消息发送者name
        intent.putExtra(ChattingFragment.Sender_NickName,mName);
        //消息发送者头像地址
        intent.putExtra(ChattingFragment.Sender_ImageUrl, mPrcurl);
        //消息接收者name
        intent.putExtra(ChattingFragment.Receiver_NickName, data.getUsername());
        //消息接收者头像地址
        intent.putExtra(ChattingFragment.Receiver_ImageUrl, data.getPic());
        KLog.e("whb",data.getUsername()+",,"+data.getPic());
        getActivity().startActivity(intent);
    }
}
