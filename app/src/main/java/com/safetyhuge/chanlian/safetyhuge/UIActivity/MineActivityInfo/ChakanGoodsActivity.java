package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.PayActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/25 0025 15:03
 * 邮箱：995696826@qq.com
 */
public class ChakanGoodsActivity extends HBaseAct {

    @Bind(R.id.chakan_tubiao)
    ImageView mChakanTubiao;
    @Bind(R.id.chakan_name)
    TextView mChakanName;
    @Bind(R.id.chakan_flag)
    TextView chakan_flag;
    @Bind(R.id.chakan_arrdess)
    TextView mChakanArrdess;
    @Bind(R.id.chakan_name1)
    TextView mChakanName1;
    @Bind(R.id.chakan_iamge)
    ImageView mChakanIamge;
    @Bind(R.id.chakan_title)
    TextView mChakanTitle;
    @Bind(R.id.chankan_num)
    TextView mChankanNum;
    @Bind(R.id.chakan_layout)
    LinearLayout mChakanLayout;
    @Bind(R.id.chankan_jiage)
    TextView mChankanJiage;
    @Bind(R.id.chakan_time)
    TextView mChakanTime;
    @Bind(R.id.chakan_goodsnum)
    TextView mChakanGoodsnum;
    @Bind(R.id.chankan_shouhuo)
    Button mChankanShouhuo;
    @Bind(R.id.chakan_button1)
    RelativeLayout mChakanButton1;
    @Bind(R.id.chankan_pingjia)
    Button mChankanPingjia;
    @Bind(R.id.chakan_button2)
    RelativeLayout mChakanButton2;
    @Bind(R.id.chankan_shanchu)
    Button mChankanShanchu;
    @Bind(R.id.chankan_fukuan)
    Button mChankanFukuan;
    @Bind(R.id.chakan_button3)
    RelativeLayout mChakanButton3;
    private String mTitle;
    private String mFlag;
    private String mName;
    private String mNum, mimage;
    private String mMoney, mMoney1;
    private String mTime;
    private String mUserid;
    private String mGoodsid;
    public static ChakanGoodsActivity instance = null;
    private MaterialDialog mDialog;
    private int mInt;
    private KProgressHUD mKProgressHUD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chakan);
        ButterKnife.bind(this);
        InitView();
        instance = this;
        registerBoradcastReceiver();
        mKProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }


    public void back_text_view(View view) {
        exitAct();
    }

    public void InitView() {
        Intent intent = getIntent();
        //商品名称
        mTitle = intent.getStringExtra("title");
        //判断标识
        mFlag = intent.getStringExtra("flag");
        //卖家名称
        mName = intent.getStringExtra("name");
        //产品数量
        mNum = intent.getStringExtra("num");
        //产品价钱
        mMoney = intent.getStringExtra("money");
        mMoney1 = intent.getStringExtra("money1");
        //时间
        mTime = intent.getStringExtra("time");
        //用户id
        mUserid = intent.getStringExtra("userid");
        //产品id
        mGoodsid = intent.getStringExtra("goodsid");
        //图片
        mimage = intent.getStringExtra("image");
        //标识
        mInt = intent.getIntExtra("mposition", -1);
        chakan_flag.setText(mFlag);
        mChakanName1.setText(mName);
        mChakanTitle.setText(mTitle);
        mChankanNum.setText("x" + mNum);
        if (mMoney.isEmpty()) {
            KLog.e("钱数");
            mChankanJiage.setText("共计" + mNum + "产品" + " 合计 " + mMoney + " 元");
        } else {
            mChankanJiage.setText("共计" + mNum + "产品" + " 合计 " + mMoney1 + " 元");
        }
        //创建订单时间:
        mChakanTime.setText("创建时间:" + DateUtils.timet(mTime));
        mChakanGoodsnum.setText("    订单号:" + mTime);
        if (mFlag.equals("待付款")) {
            mChakanButton3.setVisibility(View.VISIBLE);
            mChakanButton1.setVisibility(View.GONE);
            mChakanButton2.setVisibility(View.GONE);
        } else if (mFlag.equals("待评价")) {
            mChakanButton2.setVisibility(View.VISIBLE);
            mChakanButton3.setVisibility(View.GONE);
            mChakanButton1.setVisibility(View.GONE);
        } else if (mFlag.equals("待收货")) {
            mChakanButton1.setVisibility(View.VISIBLE);
            mChakanButton2.setVisibility(View.GONE);
            mChakanButton3.setVisibility(View.GONE);
        } else if (mFlag.equals("交易完成") || mFlag.equals("交易仲裁")) {
            mChakanButton1.setVisibility(View.GONE);
            mChakanButton2.setVisibility(View.GONE);
            mChakanButton3.setVisibility(View.GONE);
        }
        if (!mimage.isEmpty()) {
            String iamgeurl = RequestAddress.IMAGE1 + mimage;
            //截取#之前的字符串
            String str = iamgeurl;
            if (str.indexOf(",") != -1) {
                String b = str.substring(0, str.indexOf(","));
                KLog.e("indexof", str.substring(0, str.indexOf(",")));
                Picasso.with(this).load(b).into(mChakanIamge);
            } else {
                System.out.println(str);
                Picasso.with(this).load(str).into(mChakanIamge);
            }
        } else {
            KLog.e("空白");
            mChakanIamge.setImageResource(R.drawable.img_pru7);
        }
    }

    @OnClick({R.id.chakan_layout, R.id.chankan_shouhuo, R.id.chankan_pingjia, R.id.chankan_shanchu, R.id.chankan_fukuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chakan_layout:
                break;
            case R.id.chankan_shouhuo:
                mDialog = new MaterialDialog(this).setTitle("提示").setMessage("您确定收到产品了吗?").setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        mKProgressHUD.show();
                        shouhuo(mUserid, mGoodsid);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
                break;
            case R.id.chankan_pingjia:
                //评价
                Intent intent = new Intent(ChakanGoodsActivity.this, PingjiaActivity.class);
                intent.putExtra("muserid", mUserid);
                intent.putExtra("goodsid", mGoodsid);
                startActivity(intent);
                break;
            case R.id.chankan_shanchu:
                mDialog = new MaterialDialog(this).setTitle("提示").setMessage("您确定删除订单吗?").setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        mKProgressHUD.show();
                        shanchu(mUserid, mGoodsid);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
                break;
            case R.id.chankan_fukuan:
                //开启支付页面
                Intent intent1 = new Intent(ChakanGoodsActivity.this, PayActivity.class);
                intent1.putExtra("oid", mGoodsid);
                intent1.putExtra("mflag", "5");
                intent1.putExtra("money", mMoney);
                startActivity(intent1);
                break;
        }
    }

    /**
     * 删除接口
     */
    public void shanchu(String uid, String oid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "DelGoodsOrder");
        hashMap.put("uid", uid);
        hashMap.put("oid", oid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                mKProgressHUD.dismiss();
                HashMap<String, Object> map = (HashMap<String, Object>) JSONUtil.getMapForJson(s);
                String msg = (String) map.get("code");
                if (msg.equals("888")) {
                    Toast.makeText(ECApplication.context, "删除成功", Toast.LENGTH_SHORT).show();
                    sendBroadCast("com.goods.shanchu",mInt);
                    exitAct();
                } else {
                    Toast.makeText(ECApplication.context, "删除失败", Toast.LENGTH_SHORT).show();
                    sendBroadCast("com.goods.shanchu",mInt);
                }
            }
        });
    }

    /**
     * 收货接口
     */
    public void shouhuo(String uid, String oid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "ConfirmReceipt");
        hashMap.put("uid", uid);
        hashMap.put("oid", oid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mKProgressHUD.dismiss();
                HashMap<String, Object> map = (HashMap<String, Object>) JSONUtil.getMapForJson(s);
                String msg = (String) map.get("code");
                if (msg.equals("888")) {
                    Toast.makeText(ECApplication.context, "收货成功", Toast.LENGTH_SHORT).show();
                    sendBroadCast("com.goods.shouhuo",mInt);
                    exitAct();
                } else {
                    Toast.makeText(ECApplication.context, "收货失败", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            KLog.e("whb","接收到了");
        }
    }
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    public void registerBoradcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.goods.zhifu");
        intentFilter.addAction("com.goods.pingjia");
        //com.goods.pingjia
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("com.goods.zhifu")){
                    mChakanButton1.setVisibility(View.VISIBLE);
                    mChakanButton2.setVisibility(View.GONE);
                    mChakanButton3.setVisibility(View.GONE);
                    chakan_flag.setText("待收货");
                }
                if (action.equals("com.goods.pingjia")){
                    mChakanButton1.setVisibility(View.GONE);
                    mChakanButton2.setVisibility(View.GONE);
                    mChakanButton3.setVisibility(View.GONE);
                    chakan_flag.setText("交易完成");
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }
    private void sendBroadCast(String  a,int a1) {
        Intent intent = new Intent();
        intent.setAction(a);
        intent.putExtra("whb_flag",a1);
        localBroadcastManager.sendBroadcast(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBroadcastManager.unregisterReceiver(mReceiver);
        ActivitiesCollector.removeActivity(this);
    }
}
