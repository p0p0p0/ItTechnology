package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.AddressBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ArrdessActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.AffirmGoodsAdapter;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.GoodsCartBean.LookCart;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 确定订单
 * 作者：王海宾 on 2017/4/20 0020 15:10
 * 邮箱：995696826@qq.com
 * <p>
 * Bundle bundle = getIntent().getExtras();
 * SerializableMap serializableMap = (SerializableMap) bundle
 * .get("orderinfo");
 */
public class IndentActivity extends HBaseAct implements AffirmGoodsAdapter.Map {
    @Bind(R.id.exListView)
    ListView mNearby;
    @Bind(R.id.text_name)
    TextView mTextName;
    @Bind(R.id.text_tel)
    TextView mTextTel;
    @Bind(R.id.text_address)
    TextView mTextAddress;
    @Bind(R.id.ll_address)
    LinearLayout mLlAddress;
    @Bind(R.id.tv_total_price1)
    TextView mTvTotalPrice;
    @Bind(R.id.tv_go_to_pay)
    TextView mTvGoToPay;
    private Object mAddress_name;
    private Object mAddress_phone;
    private Object mAddress_addresses;
    private List<AddressBean.DataBean> mData;
    private List<LookCart.DataBean> checkoutShops;
    private List<List<LookCart.DataBean.DetailsBean>> checkoutProductsList;
    private String mMoney;
    private String mSid, mSid1;
    private String mBuilder1;
    private AffirmGoodsAdapter mGoodsAdapter;
    private String mUserid;
    private String mString;
    public static IndentActivity instance = null;
    private String mFlag;
    String text = new String();
    private HashMap<String, String> mHashMap, mHashMap1;
    private ArrayList<String> mSidinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        instance = this;
        mHashMap = new HashMap<>();
        mHashMap1 = new HashMap<>();
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        //全部一级数据
        checkoutShops = (List<LookCart.DataBean>) intent.getSerializableExtra("checkoutShops");
        checkoutProductsList = (List<List<LookCart.DataBean.DetailsBean>>) intent.getSerializableExtra
                ("checkoutProductsList");
        String mBuilderId = intent.getStringExtra("mBuilderId");
        String mBuilderSid = intent.getStringExtra("mBuilderSId");
        //标识
        mFlag = intent.getStringExtra("flag");
        //商品钱数
        mMoney = intent.getStringExtra("money");
        //商品id
        mSid = mBuilderId.substring(0, mBuilderId.length() - 1);
        //商品数量
        mBuilder1 = intent.getStringExtra("mBuilder1");
        mSid1 = mBuilder1.substring(0, mBuilder1.length() - 1);
        //店铺id
        if (mBuilderSid != null) {
            mString = mBuilderSid.substring(0, mBuilderSid.length() - 1);
        }
        KLog.e("mUserid", mUserid);
        KLog.e("checkoutShops", checkoutShops);
        KLog.e("checkoutProductsList", checkoutProductsList);
        KLog.e("mBuilderId", mBuilderId);
        KLog.e("mBuilderSid", mBuilderSid);
        KLog.e("mFlag", mFlag);
        KLog.e("mMoney", mMoney);
        KLog.e("mSid", mSid);
        KLog.e("mBuilder1", mBuilder1);
        KLog.e("checkoutShops", checkoutShops.size());
       initView();
        initData();
    }

    public void initData() {
        mGoodsAdapter = new AffirmGoodsAdapter(this, checkoutShops, checkoutProductsList, "0", mNearby);
        mNearby.setAdapter(mGoodsAdapter);

    }

    public void initView() {
        mTvTotalPrice.setText("合计: ￥" + mMoney);
        mAddress_name = get(ECApplication.context, "address_name", "");
        mAddress_phone = get(ECApplication.context, "address_phone", "");
        mAddress_addresses = get(ECApplication.context, "address_addresses", "");
        mTextName.setText(mAddress_name + "");
        mTextTel.setText(mAddress_phone + "");
        mTextAddress.setText(mAddress_addresses + "");
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    public void back_text_view(View view) {
        exitAct();
    }


    @SuppressLint("WrongConstant")
    @OnClick({R.id.ll_address, R.id.tv_go_to_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent = new Intent(IndentActivity.this, ArrdessActivity.class);
                intent.putExtra("flag", "1");
                startActivity(intent);//跳转到个人设置
                break;
            case R.id.tv_go_to_pay:
                String trim2 = mTextName.getText().toString().trim();
                String trim1 = mTextTel.getText().toString().trim();
                String trim = mTextAddress.getText().toString().trim();
                if (trim != "" && trim1 != "" && trim2 != "") {
                 //   Toast.makeText(this, "支付订单", Toast.LENGTH_SHORT).show();
                    if (mGoodsAdapter != null) {
                        if (mString != null) {
                            String[] split = mString.split(",");
                            HashMap<Integer, String> map = mGoodsAdapter.getMap();
                            HashMap<String, String> hashMap = new HashMap<>();
                            for (int i = 0; i < checkoutShops.size(); i++) {
                                String s = map.get(i);
                                KLog.e("sssss", s);
                                hashMap.put(split[i], s);
                            }
                            //请求网络
                            NetWork(mUserid, mSid, mSid1, mAddress_addresses + "", hashMap, mAddress_phone + "", mHashMap, mHashMap1);
                            KLog.e("mm", mHashMap);
                            if (mHashMap.isEmpty()) {
                                KLog.e("whb","留言为空");
                            }
                            KLog.e("mm1", mHashMap1);
                        }
                    } else {

                    }
                } else {
                    Toast.makeText(this, "请填写完整地址", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        KLog.e("onResume");
           initView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        KLog.e("onPause");
        // checkoutShops.clear();
//        checkoutProductsList.clear();
        if (mSidinfo != null) {
            mSidinfo.clear();
        }
    }

    public void NetWork(String uid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        OkGo.post(RequestAddress.HOST + RequestAddress.DZGL).params(hashMap).execute(new JsonCallback<AddressBean>() {
            @Override
            public void onSuccess(AddressBean addressBean, Call call, Response response) {
                mData = addressBean.getData();
                if (mData != null) {
                } else {

                }
            }
        });
    }

    public void NetWork(String uid, String did, String num, String address, HashMap tar_content, String mobile
            , HashMap invoice_type, HashMap invoice_name
    ) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (mFlag.equals("0")) {
            hashMap.put("action", "CreateOrder");
            hashMap.put("did", did);
        } else if (mFlag.equals("1")){
            hashMap.put("action", "CreatePayOrder");
            hashMap.put("sid", did);
        }
        hashMap.put("uid", uid);
        hashMap.put("num", num);
        hashMap.put("address", address);
        hashMap.put("tar_content", tar_content + "");
        hashMap.put("mobile", mobile);
        hashMap.put("invoice_type", invoice_type + "");
        hashMap.put("invoice_name", invoice_name + "");
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                //  {"code":"888","secess":"true","data":"961"}
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                Object data =  mapForJson.get("data");
                String error = (String) mapForJson.get("error");
                if (code.equals("888") && secess.equals("true")) {
                    Intent intent = new Intent(IndentActivity.this, PayActivity.class);
                    intent.putExtra("oid", data+"");
                    intent.putExtra("mflag", "1");
                    intent.putExtra("money", mMoney);
                    startActivity(intent);
                } else {
                    Toast.makeText(IndentActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void mapInfo(HashMap<Integer, String> map) {
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        KLog.e("resultCode", resultCode + "");
        if (data != null) {
            if (resultCode == 1) {
                if (data != null) {
                    mSidinfo = data.getStringArrayListExtra("sidinfo");
                    KLog.e("sidinfo.size();", mSidinfo.size());
                    String type = data.getStringExtra("type");
                    String flag = data.getStringExtra("flag");
                    String info = data.getStringExtra("info");
                    KLog.e("info", info);
                    if (info != null && !info.equals("0")) {
                        for (String s : mSidinfo) {
                            mHashMap.put(s, flag);
                            mHashMap1.put(s, info);
                        }
                        KLog.e("mHashMap", mHashMap.toString());
                        KLog.e("mHashMap1", mHashMap1.toString());
                        if (flag.equals("1")) {
                            text = "办公用品-" + info;
                        }
                        if (flag.equals("2")) {
                            text = "网络设备-" + info;
                        }
                        if (flag.equals("3")) {
                            text = "办公用品-" + info;
                        }
                        mGoodsAdapter.setString(text);
                        mGoodsAdapter.notifyDataSetChanged();
                    }
                }

            } else {
                mHashMap.clear();
                mHashMap1.clear();
                KLog.e("mHashMap", mHashMap.toString());
                KLog.e("mHashMap1", mHashMap1.toString());
                mGoodsAdapter.setString("0");
                mGoodsAdapter.notifyDataSetChanged();
            }
            KLog.e("data不等于null");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
