package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.FuWuBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.ServiceBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LeaseEquipmentActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.FuWuXiangQingActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.widget.MarkerManager;
import com.safetyhuge.chanlian.safetyhuge.widget.NearByInfo;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 找服务
 * 作者：王海宾 on 2017/5/16 0016 16:55
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class ServeFragment extends Fragment
        implements LocationSource, AMapLocationListener, MarkerManager.MarkerClickListener, AMap.OnCameraChangeListener {
    TextureMapView mMap;
    @Bind(R.id.iamge)
    ImageView mIamge;
    @Bind(R.id.scroll_view)
    HorizontalScrollView mScrollView;
    @Bind(R.id.map_layout)
    LinearLayout mMapLayout;
    @Bind(R.id.image_marker_pop)
    RelativeLayout mImageMarkerPop;
    @Bind(R.id.pw_lauot)
    RelativeLayout mPwLauot;
    @Bind(R.id.pop_image)
    ImageView mPopImage;
    @Bind(R.id.pow_titles)
    TextView mPowTitles;
    @Bind(R.id.pow_contents)
    TextView mPowContents;
    @Bind(R.id.pow_moneys)
    TextView mPowMoneys;
    @Bind(R.id.pow_layout)
    LinearLayout mPowLayout;

    private AMapLocationClient mlocationClient;
    private AMap aMap;  //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    //声明mListener对象，定位监听器
    //地图放大级别
    private float zoomlevel = 17f;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double lat;
    private double lon;
    private BottomSheetBehavior behavior;
    private OnLocationChangedListener mListener;
    private List<String> mMDatas;
    private MarkerManager mManager;
    private UiSettings mSettings;
    private ArrayList mLists;
    private List<NearByInfo.DataBean> mDataBean;
    private List<FuWuBean.DataBean> mData;
    //创建接口
    public  OnListener onListener;
    LeaseEquipmentActivity leaseEquipmentActivity;
    private int mInt = 0;
    private String mUserid;
    /*//目标经度
        hashMap.put("lng",lon+"");
    //目标纬度
        hashMap.put("lat",lat+"");*/
    public static String jingdu = "";
    public static String weidu = "";

    public ServeFragment(LeaseEquipmentActivity leaseEquipmentActivity) {
        this.leaseEquipmentActivity = leaseEquipmentActivity;
    }

    //接口
    public interface OnListener {
        //回调方法
        void onItemClick(BottomSheetBehavior behavior);
    }
    @SuppressLint("ValidFragment")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_map, container, false);
        mMap = (TextureMapView) view.findViewById(R.id.map);
        ButterKnife.bind(this, view);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");

        mMap.onCreate(savedInstanceState);//必须要写
        //初始化定位
        mLocationClient = new AMapLocationClient(ECApplication.context);
        //设置定位回调监听
        //  mLocationClient.setLocationListener(mLocationListener);
        init();
        //设置适配器
        mMDatas = new ArrayList<>();
        mLists = new ArrayList<>();
        mMDatas.add("全部");
        mMDatas.add("网络安全");
        mMDatas.add("系统集成");
        mMDatas.add("信息化安全");
        mMDatas.add("物联网");
        mMDatas.add("云存储");
        mMDatas.add("云计算");
        mMDatas.add("大数据分析");
        mMDatas.add("安全相关服务");
        mLists.add("0");
        mLists.add("2");
        mLists.add("3");
        mLists.add("121");
        mLists.add("160");
        mLists.add("192");
        mLists.add("201");
        mLists.add("211");
        mLists.add("218");
        inintent();
        behavior = BottomSheetBehavior.from(mPwLauot);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        return view;
    }
    public void setListener(OnListener listener) {
        this.onListener = listener;
    }
    private void inintent() {
        //开始添加数据
        for (int x = 0; x < mMDatas.size(); x++) {
            //寻找行布局，第一个参数为行布局ID，第二个参数为这个行布局需要放到那个容器上
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_index_gallery_item, mMapLayout, false);
            //通过View寻找ID实例化控件
            //实例化TextView控件
            final TextView tv = (TextView) view.findViewById(R.id.text_view_item);
            //将int数组中的数据放到ImageView中
            //给TextView添加文字
            tv.setText(mMDatas.get(x) + "");
            final int finalX = x;
            tv.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onClick(View v) {
                    switch (finalX) {
                        case 0:
                            mManager.setSpecialFirstClassVisible(mLists.get(0) + "");
                            break;
                        case 1:
                            mManager.setSpecialFirstClassVisible(mLists.get(1) + "");
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            break;
                        case 2:
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            mManager.setSpecialFirstClassVisible(mLists.get(2) + "");
                            break;
                        case 3:
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            mManager.setSpecialFirstClassVisible(mLists.get(3) + "");
                            break;
                        case 4:
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            mManager.setSpecialFirstClassVisible(mLists.get(4) + "");
                            break;
                        case 5:
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            mManager.setSpecialFirstClassVisible(mLists.get(5) + "");
                            break;
                        case 6:
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            mManager.setSpecialFirstClassVisible(mLists.get(6) + "");
                            break;
                        case 7:
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            mManager.setSpecialFirstClassVisible(mLists.get(7) + "");
                            break;
                        case 8:
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            mManager.setSpecialFirstClassVisible(mLists.get(8) + "");
                            break;
                    }
                }
            });
            //把行布局放到linear里
            mMapLayout.addView(view);
        }
    }

    public void NewWork() {
        mDataBean = new ArrayList<>();
        OkGo.post(RequestAddress.HOST + "goodsfuwu.php").execute(new JsonCallback<FuWuBean>() {
            @Override
            public void onSuccess(FuWuBean fuWuBean, Call call, Response response) {
                mData = fuWuBean.getData();
                for (FuWuBean.DataBean datum : mData) {
                    final NearByInfo.DataBean dataBean = new NearByInfo.DataBean();
                    if (datum.getLat() != null && datum.getLng() != null && datum.getUser_pic() != null) {
                        dataBean.setLatitude(datum.getLat());
                        dataBean.setLongitude(datum.getLng());
                        if (datum.getPhone() != null && !datum.getPhone().equals("") && !datum.getPhone().equals("0")) {
                            //电话
                            dataBean.setPhone(datum.getPhone());
                        }
                        //头像
                        dataBean.setMedal(RequestAddress.IMAGE1 + datum.getUser_pic().toString().replace("../", ""));
                        //图片
                        if (datum.getPic()!=null){
                            dataBean.setUrl(RequestAddress.IMAGE1 + datum.getPic().replace("../", ""));
                        }
                        dataBean.setClassX(datum.getIndus_id());
                        //标题
                        dataBean.setTitle(datum.getTitle());
                        //id
                        dataBean.setId(datum.getService_id());
                        //内容
                        dataBean.setType(datum.getContent());
                        //金钱
                        dataBean.setMoney(datum.getPrice());
                        //名字
                        dataBean.setName(datum.getUsername());
                        //uid
                        dataBean.setUid(datum.getUid());
                       /* //服务id
                        intent.putExtra("sid", data.getId());
                        //图片
                        intent.putExtra("image", data.getUrl());
                        //标题
                        intent.putExtra("title", data.getTitle());
                        //钱数
                        intent.putExtra("money", data.getMoney());
                        //头像图片
                        intent.putExtra("useriamge", data.getMedal());
                        //名称
                        intent.putExtra("username", data.getName());
                        //内容
                        intent.putExtra("content", data.getType());
                        //电话
                        intent.putExtra("phone", data.getPhone());*/
                        mDataBean.add(dataBean);
                    }
                }
                KLog.e("size", mDataBean.size());
                mManager.initSpecialClassMarkers2(mDataBean, "全部");
            }
        });
    }

    /**
     * * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mMap.getMap();
        }
        mManager = new MarkerManager(aMap);
      //  NewWork();
        if (mInt<1){
            NetWork1(1);
        }else{
            NetWork1(mInt);
        }
        mManager.setMarkClickListener(this);
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        setMyLocationIcon();
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        //设置显示定位按钮 并且可以点击
        mSettings = aMap.getUiSettings();
        aMap.setLocationSource(this);//设置了定位的监听,这里要实现LocationSource接口
        // 是否显示定位按钮
        mSettings.setMyLocationButtonEnabled(false);
        mSettings.setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);
        aMap.setOnCameraChangeListener(this);
        aMap.getScalePerPixel();KLog.e("whb",aMap.getScalePerPixel());
        //手势滑动
        mSettings.setZoomGesturesEnabled(true);
        //设置logo位置，左下，底部居中，右下
        mSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
        //设置显示地图的默认比例尺
        mSettings.setScaleControlsEnabled(true);
        mSettings.getZoomPosition();
        //每像素代表几米
//            float scale = aMap.getScalePerPixel();
        final MarkerOptions mark = new MarkerOptions();
        mark.draggable(true);
        // 设置定位监听
        aMap.setLocationSource(this);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        mSettings.setTiltGesturesEnabled(false);//将3d效果禁用掉
        mSettings.setZoomControlsEnabled(false);
        mSettings.setRotateGesturesEnabled(false);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mMap.onResume();
       mManager.initSpecialClassMarkers2(mDataBean, "全部");
    }

    //自定义地图定位小蓝点
    private void setMyLocationIcon() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);// 设置圆形的填充颜色,可以去掉小圆点的外圈
// myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(0.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
    }

    //让地图平滑的滑动
    private void smoothScroll(LatLng location) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                location,//新的中心点坐标
                15, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        ));
        aMap.moveCamera(cameraUpdate);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mMap.onPause();
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void onStop() {
        super.onStop();
        mLocationClient.stopLocation();//停止定位
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMap.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMap.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        KLog.e("走了111");
        //       behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                lat = aMapLocation.getLatitude();
                lon = aMapLocation.getLongitude();
                jingdu = lon+"";
                weidu = lat+"";
                mlocationClient.stopLocation();
                KLog.e("whb","lat"+lat+"----lon"+lon);
                smoothScroll(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
               /* if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(zoomlevel));
                    //将地图移动到定位点
                  //  aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    isFirstLoc = false;
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }*/
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
                Toast.makeText(leaseEquipmentActivity, "定位失败,请查看定位是否开启", Toast.LENGTH_SHORT).show();
                //  Toast.makeText(ECApplication.context, "定位失败,请检查定位服务是否开启", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void NetWork1(int anInt){
        mDataBean = new ArrayList<>();
        /*接口地址:http://192.168.6.201/app/goods.php
        传递参数：
        参数名	参数值	备注
        action	GetServiceInfo	必填
        uid	1	选填 用户id
        lng	164.1564	必填 目标经度
        lat	1.23741	必填 目标纬度
        distance	10	必填 目标范围 单位 公里
        keyword	关键词	选填 关键词查询
*/
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("action","GetServiceInfo");
        //目标经度
        hashMap.put("lng",lon+"");
        //目标纬度
        hashMap.put("lat",lat+"");
        //目标范围
        hashMap.put("distance",anInt+"");
        KLog.e("whb",hashMap.toString());
        OkGo.post(RequestAddress.HOST+RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ServiceBean>() {
            @Override
            public void onSuccess(ServiceBean serviceBean, Call call, Response response) {
                List<ServiceBean.DataBean> data = serviceBean.getData();
                for (ServiceBean.DataBean datum : data) {
                    KLog.e("whbname", datum.getUsername());
                    final NearByInfo.DataBean dataBean = new NearByInfo.DataBean();
                    if (datum.getLat() != null && datum.getLng() != null && datum.getUser_pic() != null) {
                        dataBean.setLatitude(datum.getLat());
                        dataBean.setLongitude(datum.getLng());
                        if (datum.getPhone() != null && !datum.getPhone().equals("") && !datum.getPhone().equals("0")) {
                            //电话
                            dataBean.setPhone(datum.getPhone());
                        }
                        //头像
                        dataBean.setMedal(RequestAddress.IMAGE1 + datum.getUser_pic().toString().replace("../", ""));
                        //图片
                        if (datum.getPic() != null) {
                            dataBean.setUrl(RequestAddress.IMAGE1 + datum.getPic().replace("../", ""));
                        }
                        dataBean.setClassX(datum.getIndus_id());
                        //标题
                        dataBean.setTitle(datum.getTitle());
                        //id
                        dataBean.setId(datum.getService_id());
                        //内容
                        dataBean.setType(datum.getContent());
                        //金钱
                        dataBean.setMoney(datum.getPrice());
                        //名字
                        dataBean.setName(datum.getUsername());
                        //uid
                        dataBean.setUid(datum.getUid());
                       /* //服务id
                        intent.putExtra("sid", data.getId());
                        //图片
                        intent.putExtra("image", data.getUrl());
                        //标题
                        intent.putExtra("title", data.getTitle());
                        //钱数
                        intent.putExtra("money", data.getMoney());
                        //头像图片
                        intent.putExtra("useriamge", data.getMedal());
                        //名称
                        intent.putExtra("username", data.getName());
                        //内容
                        intent.putExtra("content", data.getType());
                        //电话
                        intent.putExtra("phone", data.getPhone());*/
                        mDataBean.add(dataBean);
                        //状态
                        dataBean.setService_status(datum.getService_status());
                    }
                }
                mManager.initSpecialClassMarkers2(mDataBean, "全部");
            }
        });

    }
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        smoothScroll(new LatLng(39.908692, 116.397477));
        KLog.e("走了222");
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。sdk默认采用连续定位，也可以进行单次定位
            mLocationOption.setInterval(2000);
            //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
            mLocationOption.setHttpTimeOut(20000);
            //关闭缓存机制
            mLocationOption.setLocationCacheEnable(false);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }


    private void startLocation() {
        if (mLocationClient != null) {
            //19f代表地图放大的级别
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), zoomlevel));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.pow_layout, R.id.iamge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iamge:
                startLocation();
                break;
            case R.id.pow_layout:
               Intent intent = new Intent(leaseEquipmentActivity, FuWuXiangQingActivity.class);
                NearByInfo.DataBean data = (NearByInfo.DataBean) mPowLayout.getTag();
                intent.putExtra("uid",mUserid);
                //服务id
                intent.putExtra("sid", data.getId());
                //服务uid
                intent.putExtra("fwuid", data.getUid());
                leaseEquipmentActivity.startActivity(intent);
                break;
        }
        startLocation();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(NearByInfo.DataBean data) {
        if (onListener != null) {
            onListener.onItemClick(behavior);
        }
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        Picasso.with(ECApplication.context).load(data.getMedal()).into(mPopImage);
        mPowTitles.setText(data.getTitle());
        mPowContents.setText(data.getType());
        mPowMoneys.setText("¥" + data.getMoney());
        mPowLayout.setTag(data);
    }

    /**
     * 当地图移动时的操作
     *
     * @param cameraPosition
     */
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        int[] location = new int[2];
        mMap.getLocationInWindow(location);
        float ltx = location[0];
        float lty = location[1];
        Point pointLeftTop = new Point((int) ltx + 10, (int) lty + 10);
        float rbx = ltx + mMap.getWidth() - 10;
        float rby = lty + mMap.getHeight() - 10;
        Point pointRightBottom = new Point((int) rbx, (int) rby);
        LatLng plt = aMap.getProjection().fromScreenLocation(pointLeftTop);
        LatLng prb = aMap.getProjection().fromScreenLocation(pointRightBottom);
       float distance = AMapUtils.calculateLineDistance(plt, prb);
        mInt = (int)distance/1000;
        KLog.e("whb",(int)distance/1000);
       KLog.e("whb",distance+"");
    }

    /**
     * 当地图移动结束的操作
     *
     * @param cameraPosition
     */
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        KLog.e("whb","走了333");
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        //initSpecialClassMarkers2
        mManager.initSpecialClassMarkers2(mDataBean, "全部");
        KLog.e("whbsss",mInt);
        if (mInt<1){
            NetWork1(1);
        }else{
            NetWork1(mInt);
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
}
