package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.views.YuanView;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/19 0019 20:13
 * 邮箱：995696826@qq.com
 */

public class QZXiangXiActivity extends HBaseAct {

    @Bind(R.id.qz_image)
    CircleImageView mQzImage;
    @Bind(R.id.qz_name)
    TextView mQzName;
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
    @Bind(R.id.qz_button)
    Button mQzButton;
    @Bind(R.id.image_1)
    ImageView mImage1;
    @Bind(R.id.iamge_view)
    LinearLayout mIamgeView;
    private String mIconname;
    private String mName;
    private String mTitle;
    private String mMoney;
    //地图放大级别
    private float zoomlevel = 15f;
    private String mDay;
    private String mNeirong;
    private String mJingdu;
    private String mWeidu;
    private AMap aMap;
    private String mZhuangtai;
    private String mIamge;
    private String mUserid;
    private String mHid;
    KProgressHUD mProgressHUD;
    int mMposition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");

        setContentView(R.layout.activity_qzxiangxi);
        ButterKnife.bind(this);
        mQzMaps.onCreate(savedInstanceState); // 此方法必须重写
        initView();
        setmap();
        mProgressHUD = KProgressHUD.create(this)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }

    private void setUpMap() {
        UiSettings settings = aMap.getUiSettings();
        settings.setMyLocationButtonEnabled(false);
        settings.setZoomControlsEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(mWeidu), Double.parseDouble(mJingdu)), zoomlevel));
        final ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        final MarkerOptions markerOption = new MarkerOptions();
        KLog.e("mIconname", mIconname);
        ImageLoader.getInstance().loadImage(RequestAddress.IMAGE1 + mIconname,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri,
                                                  View view2, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view2,
                                loadedImage);
                        LatLng latLng = new LatLng(Double.parseDouble(mWeidu), Double.parseDouble(mJingdu));
                        View view = LayoutInflater.from(QZXiangXiActivity.this).inflate(
                                R.layout.head_normal, null);
                        YuanView imageView = (YuanView) view
                                .findViewById(R.id.img_pic);
                        imageView.setImageBitmap(loadedImage);
                        KLog.e("bbbb", "走了");
                        markerOption.position(latLng);
                        markerOption.icon(BitmapDescriptorFactory
                                .fromView(view));
                        aMap.addMarker(markerOption);
                    }
                });
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        Intent intent = getIntent();
        //hid
        mHid = intent.getStringExtra("hid");
        //position
        mMposition = intent.getIntExtra("mposition", -1);
        //头像
        mIconname = intent.getStringExtra("user_pic");
        KLog.e("mIconname", mIconname);
        if (mIconname.equals("")) {
            mQzImage.setBackgroundResource(R.drawable.icon_small_tx);
        } else {
            Picasso.with(this).load(RequestAddress.IMAGE1 + mIconname).into(mQzImage);
        }
        //图片
        mIamge = intent.getStringExtra("pic");
        KLog.e("mIamge", mIamge);
        if (mIamge.equals("")) {
            mImage1.setVisibility(View.GONE);
        } else {
            mImage1.setVisibility(View.VISIBLE);
            Picasso.with(this).load(RequestAddress.IMAGE1 + mIamge).into(mImage1);
        }
        //name
        mName = intent.getStringExtra("username");
        mQzName.setText(mName);
        //标题
        mTitle = intent.getStringExtra("title");
        mQzTitle.setText(mTitle);
        //金钱
        mMoney = intent.getStringExtra("price");
        mQzMoney.setText("赏金:¥ " + mMoney);
        //天数
        mDay = intent.getStringExtra("time");
        mQzDay.setText("期望完成周期:" + mDay);
        //内容
        mNeirong = intent.getStringExtra("content");
        mQzContent.setText(mNeirong);
        //经度
        mJingdu = intent.getStringExtra("lng");
        KLog.e("mJingdu", mJingdu);
        //纬度
        mWeidu = intent.getStringExtra("lat");
        KLog.e("mWeidu", mWeidu);
        //状态
        mZhuangtai = intent.getStringExtra("zhuangtai");
        Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + mIconname).fit().into(mQzImage);
        mQzName.setText(mName);
        mQzTitle.setText(mTitle);
        mQzMoney.setText("赏" + mMoney + "元");
        mQzContent.setText(mNeirong);
        if (mZhuangtai.equals("求助中")) {
            mQzButton.setText("放弃");
            mQzButton.setClickable(true);
            mQzButton.setBackgroundColor(CommonUtil.getColor(R.color.jue));
            mQzButton.setTextColor(CommonUtil.getColor(R.color.white));
            return;
        }
        mQzButton.setText(mZhuangtai);
    }

    public void back_text_view(View view) {
        exitAct();
    }

    MaterialDialog mMaterialDialog;

    @OnClick(R.id.qz_button)
    public void onClick() {
        mMaterialDialog = new MaterialDialog(this)
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
                        hashMap.put("hid", mHid);
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                mProgressHUD.dismiss();
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                String secess = (String) mapForJson.get("secess");
                                if (code.equals("888") && secess.equals("true")) {
                                    mQzButton.setText("已放弃");
                                    mQzButton.setClickable(false);
                                    mQzButton.setBackgroundColor(CommonUtil.getColor(R.color.white));
                                    mQzButton.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                                    RefreshMainMeUniversity(mMposition);
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
    private void RefreshMainMeUniversity(int a1) {
        Intent intent = new Intent();
        intent.setAction("com.my.qiuzhu");
        intent.putExtra("mPosition",a1);
        localBroadcastManager.sendBroadcast(intent);
    }
}
