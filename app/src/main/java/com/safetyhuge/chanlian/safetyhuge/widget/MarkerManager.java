package com.safetyhuge.chanlian.safetyhuge.widget;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.views.YuanView;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class MarkerManager implements AMap.OnMarkerClickListener {
    private AMap aMap;
    private ArrayList<Marker> markerOptions;
    private MarkerClickListener listener;
    private Marker clickedMarker;
    private List<NearByInfo.DataBean> datas;
    // 定义 Marker 点击事件监听
    private AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(Marker marker) {
            NearByInfo.DataBean data = (NearByInfo.DataBean) marker.getObject();
            addClickedMarker(marker);
            KLog.e("aaaa");
            if (listener != null) {
                listener.onClick(data);
            }
            return true;
        }
    };

    public MarkerManager(AMap aMap) {
        this.aMap = aMap;
        markerOptions = new ArrayList<>();
        datas = new ArrayList<>();
        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);
    }


    public void setMarkClickListener(MarkerClickListener listener) {
        this.listener = listener;
    }


    /**
     * 初始化点标记
     *
     * @param data
     */
    public void initMarkers(List<NearByInfo.DataBean> data) {
        if (clickedMarker != null) {
            clickedMarker.setVisible(false);
        }
        for (Marker marker : markerOptions) {
            marker.remove();
        }
        markerOptions.clear();
        for (NearByInfo.DataBean dataBean : data) {
            // setMarker(dataBean);
        }
    }

    /**
     * 初始化时只让特定的一级分类的marker显示
     *
     * @param data
     * @param fistTag
     */
    public void initSpecialClassMarkers(List<NearByInfo.DataBean> data, String fistTag, String secondTag) {
//        for (Marker marker:markerOptions){
//            marker.remove();
//        }
//        markerOptions.clear();
        List<NearByInfo.DataBean> l = new ArrayList<>();
        if (datas.size() == 0) {
            datas.addAll(data);
            l.addAll(data);
        } else {
            l.addAll(datas);
            l.addAll(data);
            l.removeAll(datas);
            datas.addAll(l);
        }
        for (NearByInfo.DataBean dataBean : l) {
            setMarker(dataBean, fistTag, secondTag);
        }
        if (clickedMarker != null) {
            clickedMarker.setVisible(false);
        }
    }

    //设置地图覆盖物
    private void setMarker(final NearByInfo.DataBean dataBean, final String firstTag, final String secoundTag) {
        final LatLng latLng = new LatLng(Double.parseDouble(dataBean.getLatitude()), Double.parseDouble(dataBean.getLongitude()));
        String imageUrl = dataBean.getUrl();
        Log.i("imageUrl", "setMarker: " + imageUrl);
        final View inflate = View.inflate(ECApplication.context, R.layout.head_normal, null);
        final YuanView img_pic = (YuanView) inflate.findViewById(R.id.img_pic);
        ImageLoader.getInstance().loadImage(imageUrl, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(latLng);
                markerOption.draggable(true);
                img_pic.setImageBitmap(loadedImage);
                markerOption.icon(BitmapDescriptorFactory.fromView(inflate));
                // 将Marker设置为贴地显示，可以双指下拉看效果
                markerOption.setFlat(true);
                if ((firstTag != null && firstTag.equals(dataBean.getClassX())) || (secoundTag != null && secoundTag.equals(dataBean.getType()))) {
                    markerOption.visible(true);
                } else {
                    markerOption.visible(false);
                }
                if (firstTag != null && firstTag.equals(dataBean.getClassX()) && secoundTag != null && secoundTag.equals("全部")) {
                    markerOption.visible(true);
                }
                if (firstTag != null && firstTag.equals("全部")) {
                    markerOption.visible(true);
                }

                Marker marker = aMap.addMarker(markerOption);
                marker.setObject(dataBean);
                markerOptions.add(marker);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
            }
        });

    }

    /**
     * 显示特定的一级分类的marker
     *
     * @param firsClass
     */
    public void setSpecialFirstClassVisible(String firsClass) {
        KLog.e("firsClass",firsClass);
        if (clickedMarker != null) {
            clickedMarker.setVisible(false);
        }
        if (firsClass.equals("0")) {
            for (Marker marker : markerOptions) {
                marker.setVisible(true);
            }
            return;
        }
        for (Marker marker : markerOptions) {
            NearByInfo.DataBean dataBean = (NearByInfo.DataBean) marker.getObject();
            String s1 = dataBean.getUrl();
            KLog.e("sss",s1);
            String s = dataBean.getClassX();
            KLog.e("sss",s);
            if (s == null) {
                marker.setVisible(false);
                continue;
            }
            if (s.equals(firsClass)) {
                marker.setVisible(true);
            } else {
                marker.setVisible(false);
            }
        }
    }

    /**
     * 显示特定的二级分类的marker
     *
     * @param secoundClass
     */
    public void setSpecialSecoundClassVisible(String firstClass, String secoundClass) {
        if (clickedMarker != null) {
            clickedMarker.setVisible(false);
        }
        for (Marker marker : markerOptions) {
            NearByInfo.DataBean dataBean = (NearByInfo.DataBean) marker.getObject();
            String s = dataBean.getType();
            if (s == null) {
                marker.setVisible(false);
                continue;
            }
            if (s.equals(secoundClass)) {
                marker.setVisible(true);
            } else {
                marker.setVisible(false);
            }
            if (firstClass != null && secoundClass.equals("全部") && dataBean.getClassX().equals(firstClass)) {
                marker.setVisible(true);
            }
        }
    }

    /**
     * 移动放大的图标的位置
     *
     * @param marker
     */
    private void addClickedMarker(final Marker marker) {
        if (clickedMarker == null) {
            final LatLng latLng = new LatLng(0, 0);
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(latLng);
            markerOption.draggable(true);
            // 将Marker设置为贴地显示，可以双指下拉看效果
            markerOption.setFlat(true);
            markerOption.visible(false);
            markerOption.displayLevel(1000);
            KLog.e("markerOption");
            clickedMarker = aMap.addMarker(markerOption);
        }
        String url = ((NearByInfo.DataBean) marker.getObject()).getMedal();
        final LatLng position = marker.getPosition();
        Picasso.with(ECApplication.context).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                clickedMarker.setIcon(null);
                View inflate = View.inflate(ECApplication.context, R.layout.head_big, null);
                YuanView img_pic = (YuanView) inflate.findViewById(R.id.img_pic);
                img_pic.setImageBitmap(bitmap);
                clickedMarker.setPosition(position);
                clickedMarker.setVisible(true);
                clickedMarker.setIcon(BitmapDescriptorFactory.fromView(inflate));
                clickedMarker.setObject(marker.getObject());
                clickedMarker.setToTop();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    public void initSpecialClassMarkers2(List<NearByInfo.DataBean> data, String flag) {
        KLog.e("data", data.size());
        List<NearByInfo.DataBean> l = new ArrayList<>();
        if (datas.size() == 0) {
            datas.addAll(data);
            l.addAll(data);
        } else {
            l.addAll(datas);
            l.addAll(data);
            l.removeAll(datas);
            datas.addAll(l);
        }
        for (NearByInfo.DataBean dataBean : l) {
            setMarker(dataBean, flag);
        }
        if (clickedMarker != null) {
            clickedMarker.setVisible(false);
        }
    }

    //设置地图覆盖物
    private void setMarker(final NearByInfo.DataBean dataBean, final String flag) {
        String imageUrl = dataBean.getMedal();
        Log.e("imageUrl", "setMarker: " + imageUrl);
        Log.e("flag", "flag: " + flag);
        Log.e("经度", "setMarker: " + dataBean.getLongitude());
        Log.e("纬度", "setMarker: " + dataBean.getLatitude());
        final LatLng latLng = new LatLng(Double.parseDouble(dataBean.getLatitude()), Double.parseDouble(dataBean.getLongitude()));
        final View inflate = View.inflate(ECApplication.context, R.layout.head_normal, null);
        final YuanView img_pic = (YuanView) inflate.findViewById(R.id.img_pic);
        Picasso.with(ECApplication.context).load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(latLng);
                markerOption.draggable(true);
                img_pic.setImageBitmap(bitmap);
                markerOption.icon(BitmapDescriptorFactory.fromView(inflate));
                // 将Marker设置为贴地显示，可以双指下拉看效果
                markerOption.setFlat(true);
                markerOption.visible(true);
                Marker marker = aMap.addMarker(markerOption);
                marker.setObject(dataBean);
                markerOptions.add(marker);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(ECApplication.context, "++++", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 监听marker的点击事件
     */
    public interface MarkerClickListener {
        void onClick(NearByInfo.DataBean data);
    }
}
