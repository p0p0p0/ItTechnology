package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.ChattingActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.ChattingFragment;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.views.YuanView;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/25 0025 18:38
 * 邮箱：995696826@qq.com
 */

public class QuBangMangActivity extends HBaseAct {
    @Bind(R.id.image_1)
    ImageView mImage1;
    @Bind(R.id.qz_image)
    CircleImageView mQzImage;
    @Bind(R.id.qz_name)
    TextView mQzName;
    @Bind(R.id.iamge_view)
    LinearLayout mIamgeView;
    @Bind(R.id.qz_title)
    TextView mQzTitle;
    @Bind(R.id.qz_money)
    TextView mQzMoney;
    @Bind(R.id.qz_day)
    TextView mQzDay;
    @Bind(R.id.qz_content)
    TextView mQzContent;
    @Bind(R.id.qz_maps)
    TextureMapView mQzMaps;
    @Bind(R.id.fwxiangxi_im_button)
    ImageButton mFwxiangxiImButton;
    @Bind(R.id.fwxiangxi_yy_button)
    Button mFwxiangxiYyButton;
    @Bind(R.id.qzxiangxi_layout)
    RelativeLayout mQzxiangxiLayout;
    @Bind(R.id.fwxiangxi_fangqi)
    Button mFwxiangxiFangqi;
    @Bind(R.id.qzxiangxi_layout_fangqi)
    RelativeLayout mQzxiangxiLayoutFangqi;
    private AMap aMap;
    private String mUseriamge;
    private String mJingdu;
    private String mWeidu;
    //地图放大级别
    private float zoomlevel = 15f;
    private String mSid;
    private String mTitle;
    private String mMoney;
    private String mUsername;
    private String mContent;
    private String mUserid;
    private String mPhone, mPhones;
    private String mName;
    private String mPrcurl;
    private String mImage;
    private KProgressHUD mProgressHUD;
    MaterialDialog mMaterialDialog;
    private String mUid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qzxiangxis);
        ButterKnife.bind(this);
        mQzMaps.onCreate(savedInstanceState); // 此方法必须重写
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mName = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        mPhones = (String) SharedPrefsUtil.get(ECApplication.context, "phone", "");
        mPrcurl = (String) SharedPrefsUtil.get(ECApplication.context, "prcurl", "");
        InitView();
        setmap();
        mProgressHUD = KProgressHUD.create(QuBangMangActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }

    private void setmap() {
        if (aMap == null) {
            aMap = mQzMaps.getMap();
            setUpMap();
        }
    }

    private void setUpMap() {
        UiSettings settings = aMap.getUiSettings();
        settings.setMyLocationButtonEnabled(false);
        settings.setZoomControlsEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(mJingdu), Double.parseDouble(mWeidu)), zoomlevel));
        final ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        final MarkerOptions markerOption = new MarkerOptions();
        KLog.e("mIconname", mUseriamge);
        ImageLoader.getInstance().loadImage(mUseriamge,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri,
                                                  View view2, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view2,
                                loadedImage);
                        LatLng latLng = new LatLng(Double.parseDouble(mJingdu), Double.parseDouble(mWeidu));
                        View view = LayoutInflater.from(QuBangMangActivity.this).inflate(
                                R.layout.head_normal, null);
                        YuanView imageView = (YuanView) view
                                .findViewById(R.id.img_pic);
                        imageView.setImageBitmap(loadedImage);
                        markerOption.position(latLng);
                        markerOption.icon(BitmapDescriptorFactory
                                .fromView(view));
                        aMap.addMarker(markerOption);
                    }
                });
    }

    public void InitView() {
   /*
                intent.putExtra("hid",data.getId());
                intent.putExtra("iamge",data.getMedal());
                intent.putExtra("name",data.getName());
                intent.putExtra("title",data.getTitle());
                intent.putExtra("money",data.getMoney());
                intent.putExtra("time",data.getType());
                intent.putExtra("cotent",data.getDetail());
                intent.putExtra("lng",data.getLng());
                intent.putExtra("lat",data.getLat());*/
        Intent intent = getIntent();
        mUid = intent.getStringExtra("uid");
        if (mUserid.equals(mUid)) {
            mQzxiangxiLayoutFangqi.setVisibility(View.VISIBLE);
            mFwxiangxiFangqi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMaterialDialog = new MaterialDialog(QuBangMangActivity.this)
                            .setTitle("提示")
                            .setMessage("您确定要放弃求助吗?" + "\n" + "放弃后将无法恢复")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                    mProgressHUD.show();
                                  /*  action	DelSeekHelp
                                    uid	102
                                    hid	22*/

                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("action", "DelSeekHelp");
                                    hashMap.put("uid", mUserid);
                                    hashMap.put("hid", mSid);
                                    OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            mProgressHUD.dismiss();
                                            KLog.json(s);
                                            /*"code": "888",
                                               "secess": "true"*/
                                            Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                            String code = (String) mapForJson.get("code");
                                            String secess = (String) mapForJson.get("secess");
                                            if (code.equals("888") && secess.equals("true")) {
                                                mFwxiangxiFangqi.setText("已放弃");
                                                mFwxiangxiFangqi.setBackgroundColor(CommonUtil.getColor(R.color.white));
                                                mFwxiangxiFangqi.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                                                mFwxiangxiFangqi.setClickable(false);
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
            });
        } else {
            mQzxiangxiLayout.setVisibility(View.VISIBLE);
        }
        //服务id uid
        mSid = intent.getStringExtra("hid");
        //标题
        mTitle = intent.getStringExtra("title");
        mQzTitle.setText(mTitle);
        //金钱
        mMoney = intent.getStringExtra("money");
        mQzMoney.setText("赏 " + mMoney + "元");
        //头像iamge
        mUseriamge = intent.getStringExtra("image");
        if (mUseriamge != null && !mUseriamge.equals("")) {
            Picasso.with(ECApplication.context).load(mUseriamge).fit().into(mQzImage);
        }
        //图片
        String pic = intent.getStringExtra("pic");
        if (pic.equals("")) {

        } else {
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + pic).into(mImage1);
            mImage1.setVisibility(View.VISIBLE);
        }
        //用户名
        mUsername = intent.getStringExtra("name");
        mQzName.setText(mUsername);
        //内容
        mContent = intent.getStringExtra("cotent");
        mQzContent.setText(mContent);
        //经度
        mJingdu = intent.getStringExtra("lat");
        //纬度
        mWeidu = intent.getStringExtra("lng");
        String time = intent.getStringExtra("time");
        //天数
        mQzDay.setText("期望完成周期" + time);

    }

    @OnClick({R.id.fwxiangxi_im_button, R.id.fwxiangxi_yy_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fwxiangxi_im_button:
                if (mUserid != "") {
                    //im
                    initContact(mUid, mUsername);
                } else {
                    showNormalDialog();
                }
                break;
            case R.id.fwxiangxi_yy_button:
                if (mUserid!=null){
                    mMaterialDialog = new MaterialDialog(QuBangMangActivity.this)
                            .setTitle("提示")
                            .setMessage("您确定要帮助TA吗?")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mProgressHUD.show();
                                    mMaterialDialog.dismiss();
                                    //立即帮忙
                                    netWork(mUserid, mSid);
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            });

                    mMaterialDialog.show();
                }else{
                    showNormalDialog();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mName = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        mPhones = (String) SharedPrefsUtil.get(ECApplication.context, "phone", "");
        mPrcurl = (String) SharedPrefsUtil.get(ECApplication.context, "prcurl", "");
    }

    private void showNormalDialog() {
        new CBDialogBuilder(QuBangMangActivity.this)
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
                        startActivity(new Intent(QuBangMangActivity.this, LoginsActivity.class));
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

    private void initContact(String contactId, String name) {
   /*     *//** 消息发送者name *//*
        private String Sender_NickName = "";
        *//** 消息发送者头像地址 *//*
        private String Sender_ImageUrl = "";
        *//** 消息接收者name *//*
        private String Receiver_NickName = "";
        *//** 消息接收者头像地址 *//*
        private String Receiver_ImageUrl = "";*/
        Intent intent = new Intent(QuBangMangActivity.this, ChattingActivity.class);
        intent.putExtra(ChattingFragment.RECIPIENTS, contactId);//相当于对方用户名
        intent.putExtra(ChattingFragment.CONTACT_USER, name);//相当于对方昵称
        intent.putExtra(ChattingFragment.CUSTOMER_SERVICE, false);
        //消息发送者name
        intent.putExtra(ChattingFragment.Sender_NickName, mName);
        //消息发送者头像地址
        intent.putExtra(ChattingFragment.Sender_ImageUrl, mPrcurl);
        //消息接收者name
        intent.putExtra(ChattingFragment.Receiver_NickName, mUsername);
        //消息接收者头像地址
        intent.putExtra(ChattingFragment.Receiver_ImageUrl, mUseriamge);
        startActivity(intent);
    }

    /*  action	AcceptSeekHelp	必填
uid	102	必填 用户id
hid	810	必填 求助id

*/
    public void netWork(String uid, String hid
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "AcceptSeekHelp");
        //
        hashMap.put("uid", uid);
        //
        hashMap.put("hid", hid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String message = (String) mapForJson.get("secess");
                mProgressHUD.dismiss();
                if (code.equals("888") && message.equals("true")) {
                    Toast.makeText(QuBangMangActivity.this, "确认完成,等待对方回应....", Toast.LENGTH_SHORT).show();
                    exitAct();
                } else {
                    Toast.makeText(QuBangMangActivity.this, "帮助失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
