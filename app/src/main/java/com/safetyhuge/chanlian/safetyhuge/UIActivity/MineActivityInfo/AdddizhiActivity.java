package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.JsonBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.ButtonUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.GetJsonDataUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.IsMobileNOUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/25 0025 22:02
 * 邮箱：995696826@qq.com
 */
public class AdddizhiActivity extends HBaseAct {

    @Bind(R.id.image1)
    ImageView mImage1;
    @Bind(R.id.ll_layout3)
    LinearLayout mLlLayout3;
    @Bind(R.id.EditText1)
    TextView mEditText1;
    @Bind(R.id.EditText2)
    EditText mEditText2;
    @Bind(R.id.TextView3)
    TextView mTextView3;
    @Bind(R.id.image2)
    ImageView mImage2;
    @Bind(R.id.ll_view1)
    LinearLayout mLlView1;
    @Bind(R.id.showNumberListToggle1)
    RelativeLayout mShowNumberListToggle1;
    @Bind(R.id.EditText3)
    EditText mEditText3;
    @Bind(R.id.scrollView1)
    ScrollView mScrollView1;
    @Bind(R.id.button_save)
    Button mButtonSave;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private boolean isLoaded = false;
    private String mUid;
    private String mFlag, mFlags;
    private String mName;
    private String mPhone;
    private String mAddress;
    private String mAddresser;
    private String mId;
    private String mTx;
    KProgressHUD mProgressHUD;
    private String mMName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        mUid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mMName = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        initView();
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        mEditText1.setText(mMName);
        mProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }

    private void initView() {
        Intent intent = getIntent();
        mFlag = intent.getStringExtra("flags");
        mName = intent.getStringExtra("name");
        mPhone = intent.getStringExtra("phone");
        mAddress = intent.getStringExtra("address");
        mAddresser = intent.getStringExtra("addresser");
        mId = intent.getStringExtra("id");
        mEditText2.setText(mPhone);
        mTextView3.setText(mAddress);
        mEditText3.setText(mAddresser);
    }

    private void NetWrk(String y_name, String mUid, String y_phone,
                        String y_address, String y_addresses, String y_youbian
            , String lng, String lat
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("y_name", y_name);
        hashMap.put("uid", mUid);
        hashMap.put("y_phone", y_phone);
        hashMap.put("y_address", y_address);
        hashMap.put("y_addresses", y_addresses);
        hashMap.put("y_youbian", y_youbian);
        hashMap.put("lng", lng);
        hashMap.put("lat", lat);
        KLog.e("hash", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.SHDZ).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.e(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String msg = (String) mapForJson.get("message");
                int code = (int) mapForJson.get("code");
                if (code == 200) {
                    if (msg.equals("添加成功")) {
                        mProgressHUD.dismiss();
                        Toast.makeText(AdddizhiActivity.this, msg, Toast.LENGTH_SHORT).show();
                        exitAct();
                    }
                } else {
                    Toast.makeText(AdddizhiActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void NetWrkxiugai(String y_name, String mUid, String y_id, String y_phone,
                              String y_address, String y_addresses, String y_youbian
            , String lng, String lat
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("y_name", y_name);
        hashMap.put("uid", mUid);
        hashMap.put("Y_id", y_id);
        hashMap.put("y_phone", y_phone);
        hashMap.put("y_address", y_address);
        hashMap.put("y_addresses", y_addresses);
        hashMap.put("y_youbian", y_youbian);
        hashMap.put("lng", lng);
        hashMap.put("lat", lat);
        KLog.e("hash", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.DZGL).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.e(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String msg = (String) mapForJson.get("message");
                int code = (int) mapForJson.get("code");
                if (code == 200) {
                        mProgressHUD.dismiss();
                        Toast.makeText(AdddizhiActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        exitAct();
                } else {
                    Toast.makeText(AdddizhiActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        KLog.e("开始解析数据");
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    KLog.e("解析数据成功");
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    KLog.e("解析数据失败");
                    break;

            }
        }
    };

    @OnClick({R.id.showNumberListToggle1, R.id.button_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showNumberListToggle1:
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(AdddizhiActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                ShowPickerView();
                break;
            case R.id.button_save:
                if (ButtonUtils.isFastDoubleClick()){
                    return;
                }
                //姓名
                String trim = mEditText1.getText().toString().trim();
                //手机
                String trim2 = mEditText2.getText().toString().trim();
                //地区
                String trim3 = mTextView3.getText().toString().trim();
                //详细地址
                String trim4 = mEditText3.getText().toString().trim();
                if (!trim.isEmpty()) {
                    if (!trim2.isEmpty()) {
                        if (!trim3.isEmpty()) {
                            if (!trim4.isEmpty()) {
                                if (mFlag.equals("0")) {
                                    if (IsMobileNOUtils.isMobileNO(trim2)){
                                        mProgressHUD.show();
                                        NetWrk(trim, mUid, trim2, mTx, trim4, "266400", "0", "0");
                                    }else{
                                        Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    if (IsMobileNOUtils.isMobileNO(trim2)){
                                        mProgressHUD.show();
                                        NetWrkxiugai(mMName, mUid, mId, trim2, trim3, trim4, "266400", "0", "0");
                                    }else{
                                        Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "请选择地区", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "请输入电话", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void ShowPickerView() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mTx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                mTextView3.setText(options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get(options2)  + options3Items.get(options1).get(options2).get(options3));
            }
        })
                .setTitleText("城市选择")
                .setTextColorCenter(CommonUtil.getColor(R.color.text_gray)) //设置选中项文字颜色
                .setContentTextSize(16)
                .setOutSideCancelable(false)// default is true
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
