package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.PayActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ArrdessActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/5/23 0023 21:35
 * 邮箱：995696826@qq.com
 */

public class LiJiYuYueActivity extends HBaseAct {
    @Bind(R.id.text_name)
    TextView mTextName;
    @Bind(R.id.text_tel)
    TextView mTextTel;
    @Bind(R.id.text_address)
    TextView mTextAddress;
    @Bind(R.id.ll_address)
    LinearLayout mLlAddress;
    @Bind(R.id.time_text)
    TextView mTimeText;
    @Bind(R.id.time_button)
    RelativeLayout mTimeButton;
    @Bind(R.id.yuyue_ed)
    EditText mYuyueEd;
    @Bind(R.id.yuyue_button)
    Button mYuyueButton;
    private Object mAddress_name;
    private Object mAddress_phone;
    private Object mAddress_addresses;
    private TimePickerView pvTime, pvCustomTime;
    private String mUserid;
    private String mPhone;
    private String mName;
    private String mPrcurl;
    private String mSid;
    private String mImage;
    private String mTitle;
    private String mMoney;
    private String mUseriamge;
    private String mUsername;
    private String mContent;
    private String mPhones;
    private String mStr;
    private KProgressHUD mProgressHUD;

    @Override
    protected void onCreate(Bundle arg0) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(arg0);
        setContentView(R.layout.activity_lijiyueyue);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mName = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        mPhone = (String) SharedPrefsUtil.get(ECApplication.context, "phone", "");
        mPrcurl = (String) SharedPrefsUtil.get(ECApplication.context, "prcurl", "");
        initView();
        mProgressHUD = KProgressHUD.create(LiJiYuYueActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("预约中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.ll_address, R.id.time_button, R.id.yuyue_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent = new Intent(LiJiYuYueActivity.this, ArrdessActivity.class);
                intent.putExtra("flag", "1");
                startActivity(intent);//跳转到个人设置
                break;
            case R.id.time_button:
                initCustomTimePicker();
                break;
            case R.id.yuyue_button:
                String name = mTextName.getText().toString().trim();
                String tel = mTextTel.getText().toString().trim();
                String address = mTextAddress.getText().toString().trim();
                String time = mTimeText.getText().toString().trim();
                String liuyan = mYuyueEd.getText().toString().trim();
                if (!name.isEmpty() && !tel.isEmpty() && !address.isEmpty()) {
                    if (!time.isEmpty()) {
                        if (!liuyan.isEmpty()) {
                            KLog.e("time", time);
               /*             String time1 = getTime(time + "00秒");
                            int i = Integer.valueOf(time1);
                            KLog.e("sss",(i-28800)+"");
                            String replace = tel.replace("电话:", "");*/
                            mProgressHUD.show();
                            netWork(mImage, mSid, mTitle, mMoney, mUseriamge, liuyan, mContent, mPhones, address, mStr);
                        } else {
                            Toast.makeText(this, "请填写留言", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请选择地址", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initCustomTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month;
        int date = t.monthDay;
        KLog.e("s", year + "-" + month + "-" + date);
        startDate.set(year, month, date);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date curDate = new Date(String.valueOf(date));//获取当前时间
                mStr = formatter.format(curDate);
                KLog.e("str", mStr);
                mTimeText.setText(mStr);
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)
                .setDividerColor(Color.RED)
                .build();
        pvCustomTime.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }


    private void initView() {
        mAddress_name = get(ECApplication.context, "address_name", "");
        mAddress_phone = get(ECApplication.context, "address_phone", "");
        mAddress_addresses = get(ECApplication.context, "address_addresses", "");
        mTextName.setText(mAddress_name + "");
        mTextTel.setText("电话:" + mAddress_phone + "");
        mTextAddress.setText(mAddress_addresses + "");
        Intent intent = getIntent();
        //服务id
        mSid = intent.getStringExtra("sid");
        //用户id
        mImage = intent.getStringExtra("uid");
        //服务标题
        mTitle = intent.getStringExtra("title");
        //商品小分类ID
        mMoney = intent.getStringExtra("useriamge");
        //商品分类ID
        mUseriamge = intent.getStringExtra("username");
        //content
        mUsername = intent.getStringExtra("content");
        //服务金钱
        mContent = intent.getStringExtra("money");
        //服务方电话
        mPhones = intent.getStringExtra("phone");
    }

    /*  action	CreatePayServiceOrder	必填
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
    public void netWork(String uid,
                        String sid,
                        String title,
                        String indus_id,
                        String indus_pid,
                        String content,
                        String price,
                        String phone,
                        String address,
                        String stime
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "CreatePayServiceOrder");
        //用户id
        hashMap.put("uid", uid);
        //服务id
        hashMap.put("sid", sid);
        //标题
        hashMap.put("title", title);
        //分类小id
        hashMap.put("indus_id", indus_id);
        //分类id
        hashMap.put("indus_pid", indus_pid);
        //留言
        hashMap.put("content", content);
        //价格
        hashMap.put("price", price);
        //电话
        hashMap.put("phone", phone);
        //地址
        hashMap.put("address", address);
        //时间
        hashMap.put("stime", stime);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + "goods.php").params(hashMap).execute(new StringCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                mProgressHUD.dismiss();
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String message = (String) mapForJson.get("secess");
                int data = (int) mapForJson.get("data");
                if (code.equals("888") && message.equals("true")) {
                    Toasty.success(ECApplication.context, "预约成功", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(LiJiYuYueActivity.this, PayActivity.class);
                    intent.putExtra("money", mContent);
                    intent.putExtra("mTitle", mTitle);
                    intent.putExtra("oid", data+"");
                    intent.putExtra("mflag", "3");
                    startActivity(intent);
                } else {
                    Toasty.error(ECApplication.context, "预约失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 将字符串转为时间戳
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (Exception e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return re_time;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
