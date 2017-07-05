package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.safetyhuge.chanlian.safetyhuge.Bean.ServiceDetailsBean;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.ChattingActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.ChattingFragment;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.MiInformationActivity;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.PingJiaBean1;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/23 0023 16:13
 * 邮箱：995696826@qq.com
 */

public class FuWuXiangQingActivity extends HBaseAct {
    KProgressHUD mKProgressHUD;
    Context mContext;
    @Bind(R.id.fwxiangxi_image)
    ImageView mFwxiangxiImage;
    @Bind(R.id.fwxiangxi_title)
    TextView mFwxiangxiTitle;
    @Bind(R.id.fwxiangxi_money)
    TextView mFwxiangxiMoney;
    @Bind(R.id.fwxiangxi_image1)
    CircleImageView mFwxiangxiImage1;
    @Bind(R.id.fwxiangxi_name)
    TextView mFwxiangxiName;
    @Bind(R.id.technology_button1)
    Button mTechnologyButton1;
    @Bind(R.id.view_1)
    View mView1;
    @Bind(R.id.technology_button2)
    Button mTechnologyButton2;
    @Bind(R.id.view_2)
    View mView2;
    @Bind(R.id.serviceindent)
    TextView mServiceindent;
    @Bind(R.id.list_view)
    MyListView mListView;
    @Bind(R.id.text111)
    TextView mText111;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.so)
    ScrollView mSo;
    @Bind(R.id.fwxiangxi_im_button)
    ImageButton mFwxiangxiImButton;
    @Bind(R.id.fwxiangxi_sc_button)
    ImageButton mFwxiangxiScButton;
    @Bind(R.id.fwxiangxi_yy_button)
    Button mFwxiangxiYyButton;
    @Bind(R.id.wode)
    ImageView mWode;
    boolean b = true;
    @Bind(R.id.zhuangtai_layout)
    RelativeLayout mZhuangtaiLayout;
    @Bind(R.id.zhuangtai_layout1)
    RelativeLayout mZhuangtaiLayout1;
    @Bind(R.id.fwxiangxi_yy_button1)
    Button mFwxiangxiYyButton1;
    @Bind(R.id.fuxuxiangqing_layout)
    LinearLayout mFuxuxiangqingLayout;
    private int count = 0;
    private int count2 = 0;
    private String mSid;
    private List<PingJiaBean1.DataBean> mData1;
    private String mUserid;
    private String mName;
    private String mPrcurl;
    private String mUid;
    private ServiceDetailsBean.DataBean mData;
    private String mFwuid;
    MaterialDialog mMaterialDialog;
    private int mMposition;
    private boolean mFavorite;
    private boolean mB1;


    @Override
    protected void onCreate(Bundle arg0) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(arg0);
        setContentView(R.layout.activity_fuwuxiangqing);
        ButterKnife.bind(this);
        mContext = this;
        mUserid = (String) SharedPrefsUtil.get(FuWuXiangQingActivity.this, "UserUid", "");
        mName = (String) SharedPrefsUtil.get(FuWuXiangQingActivity.this, "name", "");
        mPrcurl = (String) SharedPrefsUtil.get(FuWuXiangQingActivity.this, "prcurl", "");
        mKProgressHUD = KProgressHUD.create(FuWuXiangQingActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        Initview();
    }

    public void back_text_view(View view) {
        exitAct();
    }


    @SuppressLint("WrongConstant")
    private void Initview() {
        Intent intent = getIntent();
        //服务id
        mSid = intent.getStringExtra("sid");
        //fwuid
        mFwuid = intent.getStringExtra("fwuid");
        mMposition = intent.getIntExtra("mposition", -1);
        zhuangtai(mFwuid);
        NetWork();
    }

    private void zhuangtai(String username) {
        if (username != null) {
            if (username.equals(mUserid)) {
                mZhuangtaiLayout.setVisibility(View.GONE);
                mZhuangtaiLayout1.setVisibility(View.VISIBLE);
                mFwxiangxiYyButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mFwxiangxiYyButton1.getText().toString().trim().equals("审核中")) {
                            mFwxiangxiYyButton1.setClickable(false);
                        } else if (mFwxiangxiYyButton1.getText().toString().trim().equals("暂停服务")) {
                            Stop();
                        } else if (mFwxiangxiYyButton1.getText().toString().trim().equals("恢复服务")) {
                            Start();
                        } else if (mFwxiangxiYyButton1.getText().toString().trim().equals("审核失败")) {
                            mFwxiangxiYyButton1.setClickable(false);
                        }
                    }
                });
            } else {
                mZhuangtaiLayout.setVisibility(View.VISIBLE);
                mZhuangtaiLayout1.setVisibility(View.GONE);
            }
        }
    }


    public void Stop() {
        mMaterialDialog = new MaterialDialog(FuWuXiangQingActivity.this)
                .setTitle("提示")
                .setMessage("你确定要暂停服务吗?暂停期间,您的服务不会被展示,也无法再被预约.但不会影响已经接单的服务")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mKProgressHUD.show();
                        mMaterialDialog.dismiss();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "Undercarriage");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", mSid);
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                mKProgressHUD.dismiss();
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    KLog.e("whb", code);
                                    mFwxiangxiYyButton1.setText("恢复服务");
                                    Toast.makeText(ECApplication.context, "暂停成功", Toast.LENGTH_SHORT).show();
                                    sendBroadCastRefreshMainMeUniversity("com.serviceissue.zanting", mMposition);
                                }
                            }
                        });
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

    //恢复服务
    public void Start() {
        mMaterialDialog = new MaterialDialog(FuWuXiangQingActivity.this)
                .setTitle("提示")
                .setMessage("你确定要恢复服务吗?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mKProgressHUD.show();
                        mMaterialDialog.dismiss();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "Grounding");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", mSid);
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    mKProgressHUD.dismiss();
                                    KLog.e("whb", code);
                                    mFwxiangxiYyButton1.setBackgroundColor(CommonUtil.getColor(R.color.white));
                                    mFwxiangxiYyButton1.setText("审核中");
                                    mFwxiangxiYyButton1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                                    Toast.makeText(ECApplication.context, "恢复成功", Toast.LENGTH_SHORT).show();
                                    sendBroadCastRefreshMainMeUniversity("com.serviceissue.huifu", mMposition);
                                }
                            }
                        });
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

    private void NetWork() {
       /* action	GetGoodsDetails	必填
        uid	1	选填 用户id
        sid	1	必填 商品id*/

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsDetails");
        hashMap.put("uid", mUserid);
        hashMap.put("sid", mSid);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ServiceDetailsBean>() {
            @Override
            public void onSuccess(ServiceDetailsBean serviceDetailsBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mData = serviceDetailsBean.getData();
                String pic = mData.getPic();
                String mUsername = mData.getUid();
                KLog.e("whb", mUsername);
                KLog.e("pic", pic);
                String service_status = mData.getService_status();
                mFavorite = mData.isFavorite();
                if (mFavorite ==true){
                    mFwxiangxiScButton.setImageResource(R.drawable.icon_map_collect_sel);
                }else if (mFavorite ==false){
                    mFwxiangxiScButton.setImageResource(R.drawable.icon_map_collect);
                }
                //审核中
                if (service_status.equals("1")) {
                    //白色背景
                    //黑色字体
                    mFwxiangxiYyButton1.setBackgroundColor(CommonUtil.getColor(R.color.white));
                    mFwxiangxiYyButton1.setText("审核中");
                    mFwxiangxiYyButton1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                    //暂停服务
                } else if (service_status.equals("2")) {
                    //橙色背景
                    //白色字
                    mFwxiangxiYyButton1.setBackgroundColor(CommonUtil.getColor(R.color.jue));
                    mFwxiangxiYyButton1.setText("暂停服务");
                    mFwxiangxiYyButton1.setTextColor(CommonUtil.getColor(R.color.white));
                    //恢复服务
                } else if (service_status.equals("3")) {
                    mFwxiangxiYyButton1.setBackgroundColor(CommonUtil.getColor(R.color.jue));
                    mFwxiangxiYyButton1.setText("恢复服务");
                    mFwxiangxiYyButton1.setTextColor(CommonUtil.getColor(R.color.white));
                    //审核失败
                } else if (service_status.equals("4")) {
                    mFwxiangxiYyButton1.setBackgroundColor(CommonUtil.getColor(R.color.white));
                    mFwxiangxiYyButton1.setText("审核失败");
                    mFwxiangxiYyButton1.setTextColor(CommonUtil.getColor(R.color.text_brown));
                }
                if (pic != null && !pic.equals("")) {
                    Picasso.with(FuWuXiangQingActivity.this).load(RequestAddress.IMAGE1 + pic).into(mFwxiangxiImage);
                    mFwxiangxiImage.setVisibility(View.VISIBLE);
                } else {
                    mFwxiangxiImage.setVisibility(View.GONE);
                }
                //标题
                mFwxiangxiTitle.setText(mData.getTitle());
                //金钱
                mFwxiangxiMoney.setText("¥" + mData.getPrice());
                //头像
                if (mData.getSeller_pic() != null) {
                    Picasso.with(FuWuXiangQingActivity.this).load(RequestAddress.IMAGE1 + mData.getSeller_pic()).fit().into(mFwxiangxiImage1);
                }
                //名称
                mFwxiangxiName.setText(mData.getShop_name());
                //内容
                mServiceindent.setText(mData.getContent());
                mFuxuxiangqingLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.technology_button1, R.id.technology_button2, R.id.fwxiangxi_im_button, R.id.fwxiangxi_sc_button, R.id.fwxiangxi_yy_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.technology_button1:
                mView2.setVisibility(View.INVISIBLE);
                mView1.setVisibility(View.VISIBLE);
                if (count == 0) {
                    mServiceindent.setVisibility(View.VISIBLE);
                    mListView.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.GONE);
                }
                count++;
                count2 = 0;
                break;
            case R.id.technology_button2:
                mView1.setVisibility(View.INVISIBLE);
                mView2.setVisibility(View.VISIBLE);
                if (count2 == 0) {
                    mKProgressHUD.show();
                    mServiceindent.setVisibility(View.GONE);
                    mListView.setVisibility(View.VISIBLE);
                    NewWork1(mSid);
                }
                count2++;
                count = 0;
                break;
            case R.id.fwxiangxi_im_button:
                if (mUserid != "") {
                    //im
                    initContact(mFwuid, mData.getUsername());
                } else {
                    showNormalDialog();
                }
                break;
            case R.id.fwxiangxi_sc_button:
                if (mUserid != "") {
                    mFavorite = mData.isFavorite();
                    if (mFavorite==true){
                        mKProgressHUD.show();
                        QuXiaoShouCang();
                    }else if (mFavorite==false){
                        mKProgressHUD.show();
                        ShouCang();
                    }
                } else {
                    showNormalDialog();
                }
                break;
            //立即预约
            case R.id.fwxiangxi_yy_button:
                Intent intent = new Intent(FuWuXiangQingActivity.this, LiJiYuYueActivity.class);
            /*
            action	CreatePayServiceOrder	必填
uid	102	必填 用户id
sid	810	必填 商品id
title	牙膏售后服务	必填 商品名
indus_id	29	必填 商品小分类ID
indus_pid	2	必填 商品分类ID
content	阿达岁的阿萨德阿斯达岁的	必填 留言
price	100	必填 服务价格
phone	13388888888	必填 联系方式 手机号 必须数字
address	阿斯达斯达斯dad撒	必填 服务地址
stime	2017-06-05 13:41	必填 服务时间

                */
                //服务id
                intent.putExtra("sid", mSid);
                //用户id
                intent.putExtra("uid", mUserid);
                //服务标题
                intent.putExtra("title", mData.getTitle());
                //商品小分类ID
                intent.putExtra("useriamge", mData.getIndus_id());
                //商品分类ID
                intent.putExtra("username", mData.getIndus_pid());
                //content
                intent.putExtra("content", mData.getContent());
                //服务金钱
                intent.putExtra("money", mData.getPrice());
                //服务方电话
                intent.putExtra("phone", mData.getPhone());
                if (mUserid != "") {
                    startActivity(intent);
                } else {
                    showNormalDialog();
                }
                break;
        }
    }

    private void ShouCang() {
     /*   服务收藏
        action	AddFavorite
uid	1
sid	1
type	service
*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "AddFavorite");
        hashMap.put("uid", mUserid);
        hashMap.put("sid", mSid);
        hashMap.put("type", "service");
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                mKProgressHUD.dismiss();
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                if (code.equals("888") && secess.equals("true")) {
                    mData.setFavorite(true);
                    mFwxiangxiScButton.setImageResource(R.drawable.icon_map_collect_sel);
                    Toast.makeText(FuWuXiangQingActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    mB1 = false;
                } else {
                    Toast.makeText(FuWuXiangQingActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();
                }
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                Toast.makeText(FuWuXiangQingActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void QuXiaoShouCang() {
     /*   服务收藏
        action	AddFavorite
uid	1
sid	1
type	service
*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "DelFavorite");
        hashMap.put("uid", mUserid);
        hashMap.put("sid", mSid);
        hashMap.put("type", "service");
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                mKProgressHUD.dismiss();
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                if (code.equals("888") && secess.equals("true")) {
                    mData.setFavorite(false);
                    mFwxiangxiScButton.setImageResource(R.drawable.icon_map_collect);
                    Toast.makeText(FuWuXiangQingActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                    mB1 = true;
                } else {
                    Toast.makeText(FuWuXiangQingActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();
                }
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                Toast.makeText(FuWuXiangQingActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void NewWork1(String id) {
        OkGo.post(RequestAddress.HOST + "pinglunsp.php").params("service_id", id).execute(new JsonCallback<PingJiaBean1>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(PingJiaBean1 pingJiaBean1, Call call, Response response) {
                mData1 = pingJiaBean1.getData();
                mKProgressHUD.dismiss();
                if (mData1 != null) {
                    if (!mData1.isEmpty()) {
                        mListView.setVisibility(View.VISIBLE);
                        mQuanbuImage.setVisibility(View.GONE);
                        mListView.setAdapter(new FwXiangXiPj(mData1));
                        b = false;
                    } else {
                        mListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                } else {
                    mListView.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick(R.id.wode)
    public void onClick() {
        if (mUserid != "") {
            startActivity(new Intent(FuWuXiangQingActivity.this, MiInformationActivity.class));
        } else {
            showNormalDialog();
        }
    }

    private void showNormalDialog() {
        new CBDialogBuilder(FuWuXiangQingActivity.this)
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
                        startActivity(new Intent(FuWuXiangQingActivity.this, LoginsActivity.class));
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
    protected void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(FuWuXiangQingActivity.this, "UserUid", "");
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
        Intent intent = new Intent(FuWuXiangQingActivity.this, ChattingActivity.class);
        intent.putExtra(ChattingFragment.RECIPIENTS, contactId);//相当于对方用户名
        intent.putExtra(ChattingFragment.CONTACT_USER, name);//相当于对方昵称
        intent.putExtra(ChattingFragment.CUSTOMER_SERVICE, false);
        //消息发送者name
        intent.putExtra(ChattingFragment.Sender_NickName, mName);
        //消息发送者头像地址
        intent.putExtra(ChattingFragment.Sender_ImageUrl, mPrcurl);
        //消息接收者name
        intent.putExtra(ChattingFragment.Receiver_NickName, mData.getUsername());
        //消息接收者头像地址
        intent.putExtra(ChattingFragment.Receiver_ImageUrl, mData.getPic());
        startActivity(intent);
    }

    private void sendBroadCastRefreshMainMeUniversity(String a, int a1) {
        Intent intent = new Intent();
        intent.setAction(a);
        intent.putExtra("mPosition", a1);
        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
        if (mB1==true){
            sendBroadCastRefreshMainMeUniversity("com.fuwu.shouchang", mMposition);
        }
    }
}
