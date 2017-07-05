package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ServiceMinuteBean1;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.PayActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.PingjiaActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.IsMobileNOUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
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

import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceIndent1.ACTION_NAME_Qr;

/**
 * 作者：王海宾 on 2017/5/18 0018 09:59
 * 邮箱：995696826@qq.com
 */

public class ServiceindentminuteActivity extends HBaseAct {


    @Bind(R.id.serviceindentminute_tubiao)
    ImageView mServiceindentminuteTubiao;
    @Bind(R.id.serviceindentminute_flag)
    TextView mServiceindentminuteFlag;
    @Bind(R.id.serviceindentminute_dizhi)
    TextView mServiceindentminuteDizhi;
    @Bind(R.id.serviceindentminute_time)
    TextView mServiceindentminuteTime;
    @Bind(R.id.serviceindentminute_context)
    TextView mServiceindentminuteContext;
    @Bind(R.id.serviceindentminute_image)
    CircleImageView mServiceindentminuteImage;
    @Bind(R.id.serviceindentminute_name)
    TextView mServiceindentminuteName;
    @Bind(R.id.serviceindentminute_phone)
    ImageButton mServiceindentminutePhone;
    @Bind(R.id.serviceindentminute_msg)
    ImageButton mServiceindentminuteMsg;
    @Bind(R.id.serviceindentminute_image1)
    ImageView mServiceindentminuteImage1;
    @Bind(R.id.serviceindentminute_title)
    TextView mServiceindentminuteTitle;
    @Bind(R.id.serviceindentminute_money)
    TextView mServiceindentminuteMoney;
    @Bind(R.id.serviceindentminute_count)
    TextView mServiceindentminuteCount;
    @Bind(R.id.serviceindentminute_layout)
    LinearLayout mServiceindentminuteLayout;
    @Bind(R.id.serviceindentminute_moneys)
    TextView mServiceindentminuteMoneys;
    @Bind(R.id.serviceindentminute_time1)
    TextView mServiceindentminuteTime1;
    @Bind(R.id.serviceindentminute_goodsnum)
    TextView mServiceindentminuteGoodsnum;
    @Bind(R.id.serviceindentminute_button)
    Button mServiceindentminuteButton;
    @Bind(R.id.dizhi_layout)
    LinearLayout mDizhiLayout;
    @Bind(R.id.qiuzhu_button1)
    Button mQiuzhuButton1;
    @Bind(R.id.qiuzhu_button2)
    Button mQiuzhuButton2;
    @Bind(R.id.qiuzhu_button)
    LinearLayout mQiuzhuButton;
    @Bind(R.id.qiuzhu_button3)
    Button mQiuzhuButton3;
    @Bind(R.id.qiuzhu_button4)
    Button mQiuzhuButton4;
    @Bind(R.id.qiuzhu_layout)
    LinearLayout mQiuzhuLayout;
    @Bind(R.id.servicein_layout)
    LinearLayout mServiceinLayout;
    private String mPhone;
    MaterialDialog mMaterialDialog;
    private int mFlag;
    private String mZhuangtai1;
    private String mId;
    private String mUsername;
    private String mUid;
    MaterialDialog mDialog;
    private KProgressHUD mProgressHUD;
    private String mService_id;
    private String mDd_id;
    private String mBy_uid;
    private String mBy_username;
    private String mOid;
    private String mUserid;
    private int mPosition;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceindentminute);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mProgressHUD = KProgressHUD.create(ServiceindentminuteActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mServiceinLayout.setVisibility(View.INVISIBLE);
        //  InitView();
        initView();
    }

    private void NetWork(String mUserid) {
       /* action	GetOrderDetails	必填
        uid	1	必填 用户id
        oid	1	必填 订单ID*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetOrderDetails");
        hashMap.put("uid", mUserid);
        hashMap.put("oid", mOid);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ServiceMinuteBean1>() {
            @Override
            public void onSuccess(ServiceMinuteBean1 serviceMinuteBean1, Call call, Response response) {
                mProgressHUD.dismiss();
                mServiceinLayout.setVisibility(View.VISIBLE);
                ServiceMinuteBean1.DataBean data = serviceMinuteBean1.getData();
                //创建时间
                String order_time = data.getOrder_time();
                mServiceindentminuteTime1.setText(DateUtils.timet(order_time));
                //地址
                String y_address = data.getY_address();
                if (!y_address.equals("")) {
                    mServiceindentminuteDizhi.setText(y_address);
                } else {
                    mServiceindentminuteDizhi.setText("买家未选择服务地址");
                }
                //提交时间
                String ys_start_time = data.getYs_start_time();
                if (!ys_start_time.equals("")) {
                    mServiceindentminuteTime.setText(DateUtils.timet(ys_start_time));
                } else {
                    mServiceindentminuteTime.setText("买家未选择服务时间");
                }
                //内容
                String content = data.getServiceOrderInfo().getContent();
                if (!content.equals("")) {
                    mServiceindentminuteContext.setText(content);
                } else {
                    mServiceindentminuteContext.setText("买家未填写服务内容");
                }
            }
        });

    }


    private void initView() {
        final Intent intent = getIntent();
        String flag = intent.getStringExtra("flag");
        mOid = intent.getStringExtra("oid");
        String status = intent.getStringExtra("status");
        String tximage = intent.getStringExtra("Tximage");
        String tXname = intent.getStringExtra("TXname");
        String phimage = intent.getStringExtra("Phimage");
        final String title = intent.getStringExtra("Title");
        final String price = intent.getStringExtra("Price");
        mPosition = intent.getIntExtra("mPosition", -1);
        if (status.equals("待接单")) {
            mServiceindentminuteButton.setText("等待服务方接单");
            mServiceindentminuteButton.setClickable(false);
        } else if (status.equals("已关闭")) {
            mServiceindentminuteButton.setText("服务方已拒绝服务");
            mServiceindentminuteButton.setClickable(false);
        } else if (status.equals("待服务")) {
            mServiceindentminuteButton.setText("等待服务方开始服务");
            mServiceindentminuteButton.setClickable(false);
        } else if (status.equals("服务中")) {
            mServiceindentminuteButton.setText("等待服务方完成服务");
            mServiceindentminuteButton.setClickable(false);
        } else if (status.equals("待评价")) {
            mServiceindentminuteButton.setText("评价");
            mServiceindentminuteButton.setBackgroundResource(R.color.jue);
            mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
            mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ServiceindentminuteActivity.this, PingjiaActivity.class);
                    intent.putExtra("goodsid", mOid);
                    intent.putExtra("position", mPosition);
                    intent.putExtra("flag", 1);
                    startActivity(intent);
                }
            });
        } else if (status.equals("待确认")) {
            mServiceindentminuteButton.setText("确认完成");
            mServiceindentminuteButton.setBackgroundResource(R.color.jue);
            mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
            mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mServiceindentminuteFlag.getText().toString().equals("待确认")) {
                        //确完成
                        mMaterialDialog = new MaterialDialog(ServiceindentminuteActivity.this)
                                .setTitle("提示")
                                .setMessage("您确定任务已完成")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mMaterialDialog.dismiss();
                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("action","ConfirmReceipt");
                                        hashMap.put("uid",mUserid);
                                        hashMap.put("oid",mOid);
                                        OkGo.post(RequestAddress.HOST+RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(String s, Call call, Response response) {
                                                KLog.json(s);
                                                mProgressHUD.dismiss();
                                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                                String code = (String) mapForJson.get("code");
                                                String secess = (String) mapForJson.get("secess");
                                                if (code.equals("888")&&secess.equals("true")){
                                                    sendBroadCastRefreshMainMeUniversity(mPosition);
                                                    //请求接口 确实完成 成功 显示评价 设置待评价
                                                    mServiceindentminuteFlag.setText("待评价");
                                                    mServiceindentminuteButton.setText("评价");
                                                    mServiceindentminuteButton.setBackgroundResource(R.color.jue);
                                                    mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
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
                    } else if (mServiceindentminuteButton.getText().toString().equals("评价")) {
                        Intent intent = new Intent(ServiceindentminuteActivity.this, PingjiaActivity.class);
                        intent.putExtra("goodsid", mOid);
                        intent.putExtra("position", mPosition);
                        startActivity(intent);
                    }
                }
            });
        }else  if (status.equals("待付款")){
            mServiceindentminuteButton.setText("待付款");
            mServiceindentminuteButton.setBackgroundResource(R.color.jue);
            mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
            mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(ServiceindentminuteActivity.this, PayActivity.class);
                    intent1.putExtra("money", price);
                    intent1.putExtra("mTitle", title);
                    intent1.putExtra("oid", mOid);
                    intent1.putExtra("mflag", "3");
                    intent1.putExtra("position", mPosition);
                    startActivity(intent1);
                }
            });

        }

        mPhone = intent.getStringExtra("Phone");
        KLog.e("whb'", "mOid" + mOid + "status" + status + "tximage" + tximage
                + "tXname" + tXname + "phimage" + phimage + "title" + title + "price" + price + "mPhone" + mPhone);
        mServiceindentminuteFlag.setText(status);
        Picasso.with(this).load(RequestAddress.IMAGE1 + tximage).fit().into(mServiceindentminuteImage);
        mServiceindentminuteName.setText(tXname);
        if (!phimage.equals("")) {
            Picasso.with(this).load(RequestAddress.IMAGE1 + phimage).fit().into(mServiceindentminuteImage1);
        } else {
            mServiceindentminuteImage1.setImageResource(R.drawable.img_fw_small);
        }
        mServiceindentminuteTitle.setText(title);
        mServiceindentminuteMoney.setText("¥" + price);
        mServiceindentminuteMoneys.setText(price + "元");
        // mServiceindentminuteTime1.setText(DateUtils.timesTwo(time));
        mServiceindentminuteGoodsnum.setText("20170103561236584625143");
 /*       intent.putExtra("flag", 0);
        intent.putExtra("oid", mData.get(position).getObj_id());
        intent.putExtra("status", holder.mSericeState.getText().toString());
        intent.putExtra("Tximage", mData.get(position).getSeller_info().getAddress());
        intent.putExtra("TXname", mData.get(position).getSeller_username());
        intent.putExtra("Phimage", mData.get(position).getOrder_amount());
        intent.putExtra("Title", mData.get(position).getTitle());
        intent.putExtra("Price", mData.get(position).getPrice());
        intent.putExtra("Num", mData.get(position).getNum());*/
        NetWork(mUserid);
    }

    @SuppressLint("WrongConstant")
    private void InitView() {
        Intent intent = getIntent();
        mFlag = intent.getIntExtra("flag", -1);
        mId = intent.getStringExtra("id");
        mUid = intent.getStringExtra("uid");
        mUsername = intent.getStringExtra("username");
        KLog.e("mFlag", mFlag);
        if (mFlag == 3 || mFlag == 4) {
            mDizhiLayout.setVisibility(View.GONE);
        }
        //状态
        String zhuangtai = intent.getStringExtra("zhuangtai");
        //状态
        mZhuangtai1 = intent.getStringExtra("zhuangtai1");
      /*  if (mZhuangtai1 != null) {
            if (mZhuangtai1.equals("待评价")) {
                mServiceindentminuteButton.setBackgroundResource(R.color.jue);
                mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
                mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ServiceindentminuteActivity.this, HelpEvaluateActivity.class);
                        intent.putExtra("flag", mFlag);
                        intent.putExtra("service_id", mService_id);
                        intent.putExtra("dd_id", mDd_id);
                        intent.putExtra("by_uid", mBy_uid);
                        intent.putExtra("by_username", mBy_username);
                        startActivity(intent);
                        startActivity(intent);
                    }
                });
            }
        }*/
        int b = Integer.valueOf(zhuangtai);
        switch (b) {
            //0：待接单
//1：已关闭（拒绝接单/取消订单）
//2：待完成（此时可以取消订单）
//3：待确认取消（买家申请取消订单）
//4：待完成（此时无法取消订单）
//5：待评价
//6：已完成
            case 0:
                if (mFlag == 3 || mFlag == 4) {
                    if (mZhuangtai1.equals("待应答")) {
                        mServiceindentminuteFlag.setText("待应答");
                        mQiuzhuButton.setVisibility(View.VISIBLE);
                        mServiceindentminuteButton.setVisibility(View.GONE);
                    }
                } else {
                    KLog.e("111111111111");
                    mServiceindentminuteFlag.setText("待接单");
                    mServiceindentminuteButton.setText("待接单");
                    mServiceindentminuteButton.setBackgroundResource(R.color.jue);
                    mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
                    mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog = new MaterialDialog(ServiceindentminuteActivity.this).setTitle("提示").setMessage("您确定订单已完成吗?").setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //
                                    OkGo.post(RequestAddress.HOST + "").params("", "").execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            KLog.e("22222222222");
                                            KLog.json(s);
                                            Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                            mServiceindentminuteFlag.setText("待评价");
                                            mServiceindentminuteButton.setText("评价");
                                            mServiceindentminuteButton.setBackgroundResource(R.color.jue);
                                            mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
                                            mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(ServiceindentminuteActivity.this, HelpEvaluateActivity.class);
                                                    intent.putExtra("flag", mFlag);
                                                    intent.putExtra("service_id", mService_id);
                                                    intent.putExtra("dd_id", mDd_id);
                                                    intent.putExtra("by_uid", mBy_uid);
                                                    intent.putExtra("by_username", mBy_username);
                                                    startActivity(intent);
                                                }
                                            });
                                        }
                                    });
                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mDialog.dismiss();
                                    ;
                                }
                            });
                        }
                    });
                }
                break;
            case 1:
                mServiceindentminuteFlag.setText("已关闭");
                mServiceindentminuteButton.setText("已关闭");
                break;
            case 2:
                mServiceindentminuteFlag.setText("待完成");
                mServiceindentminuteButton.setText("待完成");
                break;
            case 3:
                mServiceindentminuteFlag.setText("待确认取消");
                mServiceindentminuteButton.setText("待确认取消");
                break;
            case 4:
                mServiceindentminuteFlag.setText("待完成");
                mServiceindentminuteButton.setText("待完成");
                break;
            case 5:
                mServiceindentminuteFlag.setText("待评价");
                mServiceindentminuteButton.setText("评价");
               /* mServiceindentminuteButton.setBackgroundResource(R.color.jue);
                mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
                mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ServiceindentminuteActivity.this, HelpEvaluateActivity.class);
                        intent.putExtra("flag", mFlag);
                        intent.putExtra("service_id", mService_id);
                        intent.putExtra("dd_id", mDd_id);
                        intent.putExtra("by_uid", mBy_uid);
                        intent.putExtra("by_username", mBy_username);
                        startActivity(intent);
                    }
                });*/
                break;
            case 6:
                mServiceindentminuteFlag.setText("待评价");
                mServiceindentminuteButton.setText("评价");
                mServiceindentminuteButton.setBackgroundResource(R.color.jue);
                mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
                mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KLog.e("33333333333333");
                        Intent intent = new Intent(ServiceindentminuteActivity.this, HelpEvaluateActivity.class);
                        intent.putExtra("flag", mFlag);
                        intent.putExtra("service_id", mService_id);
                        intent.putExtra("dd_id", mDd_id);
                        intent.putExtra("by_uid", mBy_uid);
                        intent.putExtra("by_username", mBy_username);
                        startActivity(intent);
                    }
                });
                break;
            case 7:
                mServiceindentminuteFlag.setText("已完成");
                mServiceindentminuteButton.setText("已完成");
                break;
        }
        //地址
        String dizhi = intent.getStringExtra("dizhi");
        mServiceindentminuteDizhi.setText(dizhi);
        //时间
        String shijian = intent.getStringExtra("shijian");
        if (!shijian.equals("")) {
            mServiceindentminuteTime.setText(DateUtils.timesTwo(shijian));
        }
        //内容
        String neirong = intent.getStringExtra("neirong");
        mServiceindentminuteContext.setText(neirong);
        //头像
        String touxiang = intent.getStringExtra("touxiang");
        Picasso.with(ECApplication.context).load(touxiang).fit().into(mServiceindentminuteImage);
        //名字
        String name = intent.getStringExtra("name");
        mServiceindentminuteName.setText(name);
        //电话
        mPhone = intent.getStringExtra("phone");
        KLog.e("mPhone", mPhone);
        //信息
        String msg = intent.getStringExtra("msg");
        //图片
        String image = intent.getStringExtra("image");
        Picasso.with(ECApplication.context).load(image).into(mServiceindentminuteImage1);
        //标题
        String title = intent.getStringExtra("title");
        mServiceindentminuteTitle.setText(title);
        //金钱
        String jinqian = intent.getStringExtra("jinqian");
        mServiceindentminuteMoney.setText(jinqian);
        //个数
        String num = intent.getStringExtra("num");
        mServiceindentminuteCount.setText(num);
        //合计
        String heji = intent.getStringExtra("heji");
        mServiceindentminuteMoneys.setText(heji);
        //创建时间
        String time = intent.getStringExtra("time");
        mServiceindentminuteTime1.setText(DateUtils.timesTwo(time));

        //单号
        String danhao = intent.getStringExtra("danhao");
        mServiceindentminuteGoodsnum.setText("20170103561236584625143");
        //评价
        mService_id = intent.getStringExtra("service_id");
        mDd_id = intent.getStringExtra("dd_id");
        mBy_uid = intent.getStringExtra("by_uid");
        mBy_username = intent.getStringExtra("by_username");
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.qiuzhu_button3, R.id.qiuzhu_button4, R.id.serviceindentminute_phone, R.id.serviceindentminute_msg, R.id.qiuzhu_button1, R.id.qiuzhu_button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.serviceindentminute_phone:
                if (mPhone.isEmpty() || IsMobileNOUtils.isMobileNO(mPhone) == false) {
                    Toast.makeText(this, "对方没有留下电话", Toast.LENGTH_SHORT).show();
                } else {
                    mMaterialDialog = new MaterialDialog(this)
                            .setTitle("提示")
                            .setMessage("拨打:" + mPhone)
                            .setPositiveButton("呼叫", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + mPhone));
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
                }
                break;
            case R.id.serviceindentminute_msg:
                break;
            //拒绝帮助
            case R.id.qiuzhu_button1:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("提示")
                        .setMessage("您确定要拒绝帮助吗?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mProgressHUD.show();
                                OkGo.post(RequestAddress.HOST + "").params("", "").execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        KLog.json(s);
                                        Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                        mProgressHUD.show();
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
                break;
            //接受帮助
            case R.id.qiuzhu_button2:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("提示")
                        .setMessage("您确定要接受帮助吗?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mProgressHUD.show();
                                OkGo.post(RequestAddress.HOST + "").params("", "").execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        KLog.json(s);
                                        Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                        mProgressHUD.show();
                                        //隐藏拒绝
                                        mQiuzhuButton.setVisibility(View.GONE);
                                        //隐藏已完成
                                        mServiceindentminuteButton.setVisibility(View.GONE);
                                        //确认取消订单
                                        mQiuzhuLayout.setVisibility(View.VISIBLE);
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
                break;
            //取消订单
            case R.id.qiuzhu_button3:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("提示")
                        .setMessage("您确定要取消订单吗?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mProgressHUD.show();
                                OkGo.post(RequestAddress.HOST + "").params("", "").execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        KLog.json(s);
                                        Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                        mProgressHUD.show();
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
                break;
            //确定完成
            case R.id.qiuzhu_button4:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("提示")
                        .setMessage("")
                        .setPositiveButton("您确定完成吗?", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mProgressHUD.show();
                                OkGo.post(RequestAddress.HOST + "").params("", "").execute(new StringCallback() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        KLog.json(s);
                                        Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                        mProgressHUD.show();
                                        //去评价
                                        //隐藏拒绝
                                        mQiuzhuButton.setVisibility(View.GONE);
                                        //隐藏已完成
                                        mServiceindentminuteButton.setVisibility(View.GONE);
                                        //确认取消订单
                                        mQiuzhuLayout.setVisibility(View.GONE);
                                        mServiceindentminuteButton.setText("评价");
                                        mServiceindentminuteFlag.setText("待评价");
                                        mServiceindentminuteButton.setBackgroundResource(R.color.jue);
                                        mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
                                        mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(ServiceindentminuteActivity.this, HelpEvaluateActivity.class);
                                                intent.putExtra("flag", 3);
                                                intent.putExtra("id", mFlag);
                                                intent.putExtra("uid", mId);
                                                intent.putExtra("username", mUid);
                                                startActivity(intent);
                                            }
                                        });

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
                break;
        }
    }

    public void back_text_view(View view) {
        exitAct();
    }

    public void Xiugai() {
        mServiceindentminuteButton.setClickable(false);
        mServiceindentminuteFlag.setText("已评价");
        mServiceindentminuteButton.setText("已评价");
        mServiceindentminuteButton.setBackgroundResource(R.color.jue);
        mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
    }
    public void Xiugai1() {
        mServiceindentminuteButton.setClickable(false);
        mServiceindentminuteFlag.setText("待接单");
        mServiceindentminuteButton.setText("等待服务方接单");
        mServiceindentminuteButton.setBackgroundResource(R.color.jue);
        mServiceindentminuteButton.setTextColor(CommonUtil.getColor(R.color.white));
    }
    private void sendBroadCastRefreshMainMeUniversity(int a1) {
        Intent intent = new Intent();
        intent.setAction(ACTION_NAME_Qr);
        intent.putExtra("mPosition",a1);
        localBroadcastManager.sendBroadcast(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
