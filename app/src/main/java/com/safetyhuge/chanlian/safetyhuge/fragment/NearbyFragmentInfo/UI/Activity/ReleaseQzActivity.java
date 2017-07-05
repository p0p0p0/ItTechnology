package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.Activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.AreaBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProjectBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.PayActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.BottomPopupOption;
import com.safetyhuge.chanlian.safetyhuge.utils.ClipImageActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.MypopupAdapter;
import com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.XmEr;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.XmYi;
import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 发布求助
 * 作者：王海宾 on 2017/5/17 0017 09:36
 * 邮箱：995696826@qq.com
 */

public class ReleaseQzActivity extends HBaseAct implements LocationSource, AMapLocationListener {
    @Bind(R.id.release_tv_cp_yiji)
    TextView mReleaseTvCpYiji;
    @Bind(R.id.release_button_cp_one_layout)
    LinearLayout mReleaseButtonCpOneLayout;
    @Bind(R.id.release_tv_cp_erji)
    TextView mReleaseTvCpErji;
    @Bind(R.id.release_button_cp_two_layout)
    LinearLayout mReleaseButtonCpTwoLayout;
    @Bind(R.id.release_tv_dq_sheng)
    TextView mReleaseTvDqSheng;
    @Bind(R.id.release_button_dq_layout1)
    LinearLayout mReleaseButtonDqLayout1;
    @Bind(R.id.release_et_title)
    EditText mReleaseEtTitle;
    @Bind(R.id.release_money)
    EditText mReleaseMoney;
    @Bind(R.id.release_et_describe)
    EditText mReleaseEtDescribe;
    @Bind(R.id.release_button_image)
    ImageView mReleaseButtonImage;
    @Bind(R.id.release_butto_update)
    Button mReleaseButtoUpdate;
    @Bind(R.id.release_button)
    Button mReleaseButton;
    @Bind(R.id.release_tv_cp_tiem)
    EditText mReleaseTvCpTiem;
    @Bind(R.id.release_tv_cp_erji_time)
    TextView mReleaseTvCpErjiTime;
    @Bind(R.id.release_button_cp_two_time_layout)
    LinearLayout mReleaseButtonCpTwoTimeLayout;
    @Bind(R.id.release_et_phone)
    EditText mReleaseEtPhone;
    private PopupWindow typeSelectPopup;
    private String mShiId1;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    Context mContext;
    private String mArr1;
    private String mArr2;
    private ArrayList<String> mArrayList, mArrayLists, mArrayListId, mArrayListsId;
    private ArrayList<String> mRegionIdyList, mRegionyNameList;
    private ArrayList<String> mAreaNameList, mAreaIdList;
    KProgressHUD mKProgressHUD;
    private List<ProjectBean.DataBean.IndusBean> mIndus;
    private List<ProjectBean.DataBean.RegionBean> mRndus;
    private String mId1;
    private ArrayList<String> mArrayList1;
    private String mShengId;
    private List<AreaBean.DataBean> mData;
    private String mJingdu;
    private String mWeidu;
    private String mDizhi;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片临时文件
    private File tempFile;
    private String mCropImagePath;
    private Bitmap mBitMap;
    // 1: qq, 2: weixin
    private int type;
    private double mJing;
    private int mA;
    private double mWei;
    private String mUID;
    private String mUsername;
    private ArrayList<String> mPplisttime;
    private String mProvince;
    private String mCity;
    private String mDistrict;
    MaterialDialog mMaterialDialog;
    /**
     * 声明定位回调监听器
     */
    public AMapLocationListener mLocationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                    mJingdu = amapLocation.getLatitude()+"";
                    //获取经度
                    mWeidu = amapLocation.getLongitude()+"";
                    amapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());
                    df.format(date);//定位时间
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    mProvince = amapLocation.getProvince();
                    mCity = amapLocation.getCity();
                    mDistrict = amapLocation.getDistrict();
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    amapLocation.getAoiName();//获取当前定位点的AOI信息
                    String weizhi = amapLocation.getProvince() + amapLocation.getCity() + amapLocation.getDistrict() + amapLocation.getStreet() + amapLocation.getStreetNum();
                    KLog.e("pcw", "Country : " + amapLocation.getCountry() + " province : " + amapLocation.getProvince() + " City : " + amapLocation.getCity() + " District : " + amapLocation.getDistrict());
                    mReleaseTvDqSheng.setText(weizhi);
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
    private String mPhone;
    private File mFile;
    public static ReleaseQzActivity instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_layout_qz);
        ButterKnife.bind(this);
        instance = this;
        mContext = ReleaseQzActivity.this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("发布中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        //初始化定位
        mLocationClient = new AMapLocationClient(ECApplication.context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        alents();
        setUpMap();
        //创建拍照存储的临时文件
        createCameraTempFile(savedInstanceState);
        mUID = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mUsername = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        mPhone = (String) SharedPrefsUtil.get(ECApplication.context, "phone", "");
        mReleaseEtPhone.setText(mPhone);
        mPplisttime = new ArrayList<>();
        mPplisttime.add("小时");
        mPplisttime.add("天");
        mPplisttime.add("周");
        mPplisttime.add("月");
    }

    public void back_text_view(View view) {
        SharedPrefsUtil.remove(ECApplication.context, "Midid");
        SharedPrefsUtil.remove(ECApplication.context, "MMidid");
        SharedPrefsUtil.remove(ECApplication.context, "jingdu");
        SharedPrefsUtil.remove(ECApplication.context, "weidu");
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        SharedPrefsUtil.remove(ECApplication.context, "Midid");
        SharedPrefsUtil.remove(ECApplication.context, "MMidid");
        SharedPrefsUtil.remove(ECApplication.context, "jingdu");
        SharedPrefsUtil.remove(ECApplication.context, "weidu");
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.release_button_cp_two_time_layout, R.id.release_button_cp_one_layout, R.id.release_button_cp_two_layout, R.id.release_button_dq_layout1, R.id.release_butto_update, R.id.release_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.release_button_cp_one_layout:
                PopupUtils.initSelectPopup(mContext, mArrayList, mArrayListId, null, null, null, null, null, mReleaseTvCpYiji, mReleaseButtonCpOneLayout).showAsDropDown(mReleaseButtonCpOneLayout, 0, 10);
                mArr1 = (String) get(ECApplication.context, "Midid", "");
                KLog.e("项目一级", mArr1);
                break;
            case R.id.release_button_cp_two_layout:
                if (!mReleaseTvCpYiji.getText().toString().isEmpty()) {
                    int oneIndex = getTwoData();// get two data
                    KLog.e("oneIndex", oneIndex);
                    List<ProjectBean.DataBean.IndusBean.CdataBean> cdata = mIndus.get(oneIndex).getCdata();
                    mArrayList1 = new ArrayList<>();
                    for (ProjectBean.DataBean.IndusBean.CdataBean cdataBean : cdata) {
                        mArrayList1.add(cdataBean.getCname());
                    }
                    PopupUtils.initSelectPopup(mContext, mArrayList1, null, mArrayListsId, null, null, null, null, mReleaseTvCpErji, mReleaseButtonCpTwoLayout).showAsDropDown(mReleaseButtonCpTwoLayout, 0, 10);
                    mArr2 = (String) get(ECApplication.context, "MMidid", "");
                    KLog.e("项目二级", mArr2);
                } else {
                    Toast.makeText(mContext, "请选择第一项", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.release_button_cp_two_time_layout:
                initSelectPopup0(mContext, mPplisttime, mReleaseTvCpErjiTime, mReleaseButtonCpTwoTimeLayout);
                break;
            case R.id.release_button_dq_layout1:
                startActivity(new Intent(this, MapInfoActivity.class));
                break;
            case R.id.release_butto_update:
                type = 2;
                final BottomPopupOption bottomPopupOption = new BottomPopupOption(ReleaseQzActivity.this);
                bottomPopupOption.setItemText("拍照", "选择相册");
                // bottomPopupOption.setColors();//设置颜色
                bottomPopupOption.showPopupWindow();
                bottomPopupOption.setItemClickListener(new BottomPopupOption.onPopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        switch (position) {
                            case 0:
                                //权限判断
                                if (ContextCompat.checkSelfPermission(ReleaseQzActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请WRITE_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(ReleaseQzActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                                } else {
                                    //跳转到调用系统相机
                                    gotoCarema();
                                }
                                bottomPopupOption.dismiss();
                                break;
                            case 1:
                                //权限判断
                                if (ContextCompat.checkSelfPermission(ReleaseQzActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请READ_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(ReleaseQzActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                                } else {
                                    //跳转到调用系统图库
                                    gotoPhoto();
                                }
                                bottomPopupOption.dismiss();
                                break;
                        }
                    }
                });
                break;
            case R.id.release_button:
                //一级
                String TvCpYiji = mReleaseTvCpYiji.getText().toString().trim();
                //二级
                String TvCpErji = mReleaseTvCpErji.getText().toString().trim();
                //地址
                String DqSheng = mReleaseTvDqSheng.getText().toString().trim();
                //标题
                String EtTitle = mReleaseEtTitle.getText().toString().trim();
                //金钱
                String Money = mReleaseMoney.getText().toString().trim();
                //描述
                String Describe = mReleaseEtDescribe.getText().toString().trim();
                //数量
                String timeCount = mReleaseTvCpTiem.getText().toString().trim();
                //单位
                String timeText = mReleaseTvCpErjiTime.getText().toString().trim();
                //电话
                String Phone = mReleaseEtPhone.getText().toString().trim();
                //mReleaseEtDescribe 描述
                //图片mBitMap
                if (!TvCpYiji.isEmpty()) {
                    if (!TvCpErji.isEmpty()) {
                        if (!DqSheng.isEmpty()) {
                            if (!timeCount.isEmpty()) {
                                if (!timeText.isEmpty()) {
                                    if (!EtTitle.isEmpty()) {
                                        if (!Money.isEmpty()) {
                                            mA = Integer.parseInt(Money);
                                            if (mA >= 0) {
                                                if (!Describe.isEmpty()) {
                                                    if (!Phone.isEmpty()) {
                                                        //  if (mBitMap != null) {
                                                        KLog.e("mJingdu", mJingdu);
                                                        KLog.e("mWeidu", mWeidu);
                                                        //获取纬度
                                                        String s = mWei + "";
                                                        //获取经度
                                                        String s1 = mJing + "";
                                                        if (!s1.equals("") && !s.equals("")) {
                                                            KLog.e("mWei", s1 + "," + s);
                                                            KLog.e("mArr1", mArr1);
                                                            KLog.e("mArr2", mArr2);
                                                                 /*
       省
       mProvince = amapLocation.getProvince();
       //市
        mCity = amapLocation.getCity();
        //区
        mDistrict = amapLocation.getDistrict();*/
                                                                        mKProgressHUD.show();
                                                            NetWork1(mUID,mProvince,mCity,mDistrict,Phone,XmYi,XmEr,EtTitle,Describe,Money,"次",timeCount,timeText,mWeidu,mJingdu);
                                                            //      NewWork(EtTitle, mArr1, mArr2, Money, Describe, mUID, mUsername, s1, s);
                                                        } else {
                                                            KLog.e("mJingdu", mJingdu + "," + mWeidu);
                                                        }
                                                       /* } else {
                                                            Toast.makeText(mContext, "请添加图片", Toast.LENGTH_SHORT).show();
                                                        }*/
                                                    } else {
                                                        Toast.makeText(mContext, "请填写电话", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(mContext, "请填写描述", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(mContext, "请输入正确金额", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(mContext, "请输入标题", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(mContext, "请选择时间单位", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(mContext, "请填写天数", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(mContext, "请选择地址", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "请选择分类", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "请选择分类", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void NetWork1(String uid,
                         String province_name,
                         String city_name,
                         String area_name,
                         String phone,
                         String indus_pid,
                         String indus_id,
                         final String txt_title,
                         String tar_content,
                         final String txt_price,
                         String unite_price,
                         String service_time,
                         String unit_time,
                         String lat,
                         String lng
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "NextSeekHelpSept1");
        hashMap.put("uid", uid);
        hashMap.put("province_name", province_name);
        hashMap.put("city_name", city_name);
        hashMap.put("area_name", area_name);
        hashMap.put("phone", phone);
        hashMap.put("indus_pid", indus_pid);
        hashMap.put("indus_id", indus_id);
        hashMap.put("txt_title", txt_title);
        hashMap.put("tar_content", tar_content);
        hashMap.put("txt_price", txt_price);
        hashMap.put("unite_price", unite_price);
        hashMap.put("service_time", service_time);
        hashMap.put("unit_time", unit_time);
        hashMap.put("lng", lng);
        hashMap.put("lat", lat);
        KLog.e("hasmap", hashMap.toString());
        String file = "";
        if (mCropImagePath!=null){
            mFile = new File(mCropImagePath);
            OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).params("upload", mFile).execute(new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    KLog.json(s);
                    Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                    String a = (String) mapForJson.get("code");
                    final int help_id = (int) mapForJson.get("help_id");
                    if (a.equals("888")) {
                        Toasty.success(ReleaseQzActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                       // finish();
                        mMaterialDialog = new MaterialDialog(ReleaseQzActivity.this)
                                .setTitle("提示")
                                .setMessage("发布求助成功,请前往支付赏金")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mMaterialDialog.dismiss();
                                        Intent intent = new Intent(ReleaseQzActivity.this, PayActivity.class);
                                        intent.putExtra("money", txt_price);
                                        intent.putExtra("mTitle", txt_title);
                                        intent.putExtra("oid", help_id+"");
                                        intent.putExtra("mflag", "4");
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mMaterialDialog.dismiss();
                                    }
                                });

                        mMaterialDialog.show();
                    } else {
                        Toasty.error(ReleaseQzActivity.this, "发布失败", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }else{
            OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).params("upload", "").execute(new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    KLog.json(s);
                    Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                    String a = (String) mapForJson.get("code");
                    final int help_id = (int) mapForJson.get("help_id");
                    if (a.equals("888")) {
                       // Toasty.success(ReleaseQzActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                     //   finish();
                        mMaterialDialog = new MaterialDialog(ReleaseQzActivity.this)
                                .setTitle("提示")
                                .setMessage("发布求助成功,请前往支付赏金")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mMaterialDialog.dismiss();
                                        Intent intent = new Intent(ReleaseQzActivity.this, PayActivity.class);
                                        intent.putExtra("money", txt_price);
                                        intent.putExtra("mTitle", txt_title);
                                        intent.putExtra("oid", help_id+"");
                                        intent.putExtra("mflag", "4");
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mMaterialDialog.dismiss();
                                    }
                                });

                        mMaterialDialog.show();
                    } else {
                        Toasty.error(ReleaseQzActivity.this, "发布失败", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }


    }

    private void NewWork(String title, String indus_id, String indus_pid,
                         String price, String content,
                         String uid, String username,
                         String lng, String lat
    ) {
     /*   action	NextServiceSept1	必填
        uid	1	必填 用户ID
        province_name	浙江省	选填 省名
        city_name	台州市	选填 市名
        area_name	路桥区	选填 区名
        phone	13381186024	必填 手机号
        indus_pid	1	必填 分类ID
        indus_id	2	必填 小分类ID
        txt_title	七尾七尾七尾	必填 标题
        tar_content	阿斯达速度阿萨德七尾	必填 内容描述
        txt_price	100	必填 出售价格
        unite_price	次||个||台||份	必填 中文的 次、个、台、份
        service_time	10	必填 多久能完成
        unit_time	小时||天||周||月	必填 中文的 小时、天、周、月
        lat	111	必填 纬度
        lng	111	必填 经度
        upload	文件post	选填 图片*/

       /*
       省
       mProvince = amapLocation.getProvince();
       //市
        mCity = amapLocation.getCity();
        //区
        mDistrict = amapLocation.getDistrict();*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("title", title);
        hashMap.put("indus_id", indus_id);
        hashMap.put("indus_pid", indus_pid);
        hashMap.put("price", price);
        hashMap.put("content", content);
        hashMap.put("uid", uid);
        hashMap.put("username", username);
        hashMap.put("lng", lng);
        hashMap.put("lat", lat);
        KLog.e("hasmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + "fbfw.php").params(hashMap).params("pic", new File(mCropImagePath)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String s1 = (String) mapForJson.get("message");
                int a = (int) mapForJson.get("code");
                if (a == 200) {
                    if (s1.equals("发布成功")) {
                        Toasty.success(ECApplication.context, "发布成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toasty.error(ECApplication.context, "发布失败", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void alents() {
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params("action", "GetTaskTextInfo").execute(new JsonCallback<ProjectBean>() {
            @Override
            public void onSuccess(ProjectBean projectBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                ProjectBean.DataBean data = projectBean.getData();
                mRndus = data.getRegion();
                mIndus = data.getIndus();
                List<ProjectBean.DataBean.RegionBean> regionBeen = projectBean.getData().getRegion();
                List<ProjectBean.DataBean.IndusBean> dataBeen = projectBean.getData().getIndus();
                mArrayList = new ArrayList<String>();
                mArrayListId = new ArrayList<String>();
                mArrayLists = new ArrayList<String>();
                mArrayListsId = new ArrayList<String>();
                mRegionyNameList = new ArrayList<String>();
                mRegionIdyList = new ArrayList<String>();
                for (ProjectBean.DataBean.RegionBean regionBean : regionBeen) {
                    mRegionyNameList.add(regionBean.getPname());
                    mRegionIdyList.add(regionBean.getPid());
                }
                for (int i = 0; i < dataBeen.size(); i++) {
                    mArrayList.add(dataBeen.get(i).getPname());
                    mArrayListId.add(dataBeen.get(i).getPid());
                    for (int j = i; j < dataBeen.get(i).getCdata().size(); j++) {
                        mArrayLists.add(dataBeen.get(i).getCdata().get(j).getCname());
                        mArrayListsId.add(dataBeen.get(i).getCdata().get(j).getCid());
                    }
                }

            }
        });
    }

    public int getTwoData() {
        mId1 = (String) get(ECApplication.context, "Midid", "");
        for (int i = 0; i < mIndus.size(); i++) {
            String pid = mIndus.get(i).getPid();
            if (mId1.equals(pid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 跳转到照相机
     */
    private void gotoCarema() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 外部存储权限申请返回
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCarema();
            } else {
                // Permission Denied
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            } else {
                // Permission Denied
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    mCropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    mBitMap = BitmapFactory.decodeFile(mCropImagePath);
                    KLog.e("cropImagePath", mCropImagePath);
                    if (type == 1) {
                        mReleaseButtonImage.setImageBitmap(mBitMap);
                    } else {
                        mReleaseButtonImage.setImageBitmap(mBitMap);
                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }
    }


    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 配置定位参数
     */
    private void setUpMap() {
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mArr1 = (String) get(ECApplication.context, "Midid", "");
        mArr2 = (String) get(ECApplication.context, "MMidid", "");
        mJingdu = (String) SharedPrefsUtil.get(ECApplication.context, "jingdu", "");
        mWeidu = (String) SharedPrefsUtil.get(ECApplication.context, "weidu", "");
        mDizhi = (String) SharedPrefsUtil.get(ECApplication.context, "dizhi", "");
        KLog.e("mJingdu", mJingdu);
        KLog.e("mWeidu", mWeidu);
        if (mDizhi.equals("")) {

        } else {
            mReleaseTvDqSheng.setText(mDizhi);
        }
    }

    public PopupWindow initSelectPopup0(Context context, final ArrayList<String> lists,
                                        final TextView v, View v1) {
        ListView mTypeLv = new ListView(context);
        final MypopupAdapter mypopupAdapter = new MypopupAdapter(context, lists);
        mTypeLv.setAdapter(mypopupAdapter);
        // 设置ListView点击事件监听
        // 让垂直滚动条不可见
        mTypeLv.setDividerHeight(0);
        mTypeLv.setVerticalScrollBarEnabled(false);
        mTypeLv.setSelector(R.color.transparent);
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = lists.get(position);
                KLog.e("value", value);
                // 把选择的数据展示对应的TextView上
                v.setText(value);
                // 选择完后关闭popup窗口
                typeSelectPopup.dismiss();
                mypopupAdapter.notifyDataSetChanged();
            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, v1.getWidth(), 330, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bg_corner);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
        if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
            typeSelectPopup.showAsDropDown(v1, 0, 10);
        }
        return typeSelectPopup;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
        XmYi="";
        XmEr="";
    }
}
