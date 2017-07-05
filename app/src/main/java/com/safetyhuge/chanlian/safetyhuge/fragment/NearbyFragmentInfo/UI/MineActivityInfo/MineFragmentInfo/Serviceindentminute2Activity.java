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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ServiceMinuteBean1;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.PingjiaActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ServiceIndentPingjiaActivity;
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

import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceIndent2.ACTION_NAME_SHOUCHU_FWWC;
import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceIndent2.ACTION_NAME_SHOUCHU_JJJD;
import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceIndent2.ACTION_NAME_SHOUCHU_KSFW;
import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceIndent2.ACTION_NAME_SHOUCHU_TYJD;

/**
 * 作者：王海宾 on 2017/5/18 0018 09:59
 * 邮箱：995696826@qq.com
 */

public class Serviceindentminute2Activity extends HBaseAct {


    @Bind(R.id.serviceindentminute_flag)
    TextView mServiceindentminuteFlag;
    @Bind(R.id.serviceindentminute_image)
    CircleImageView mServiceindentminuteImage;
    @Bind(R.id.serviceindentminute_name)
    TextView mServiceindentminuteName;
    @Bind(R.id.serviceindentminute_phone)
    ImageButton mServiceindentminutePhone;
    @Bind(R.id.serviceindentminute_msg)
    ImageButton mServiceindentminuteMsg;
    @Bind(R.id.serviceindentminute_dizhi)
    TextView mServiceindentminuteDizhi;
    @Bind(R.id.serviceindentminute_time)
    TextView mServiceindentminuteTime;
    @Bind(R.id.serviceindentminute_context)
    TextView mServiceindentminuteContext;
    @Bind(R.id.dizhi_layout)
    LinearLayout mDizhiLayout;
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
    @Bind(R.id.serviceshouchu_layout1_text)
    TextView mServiceshouchuLayout1Text;
    @Bind(R.id.serviceshouchu_layout1_button)
    Button mServiceshouchuLayout1Button;
    @Bind(R.id.serviceshouchu_layout1)
    RelativeLayout mServiceshouchuLayout1;
    @Bind(R.id.serviceshouchu_layout2_button1)
    Button mServiceshouchuLayout2Button1;
    @Bind(R.id.serviceshouchu_layout2_button2)
    Button mServiceshouchuLayout2Button2;
    @Bind(R.id.serviceshouchu_layout2)
    RelativeLayout mServiceshouchuLayout2;
    @Bind(R.id.servicein_layout)
    LinearLayout mServiceinLayout;
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
    private String mPhone;
    MaterialDialog mMaterialDialog;
    private int mWhb_flag;
    private int mPingjiaFlag;


    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceshouchu);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mProgressHUD = KProgressHUD.create(Serviceindentminute2Activity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mServiceinLayout.setVisibility(View.INVISIBLE);
        KLog.e("zoule");
        initView();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
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
                //电话
                mPhone = data.getY_photo();
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
        mOid = intent.getStringExtra("oid");
        mPingjiaFlag = intent.getIntExtra("pingjiaFlag", -1);
        String status = intent.getStringExtra("status");
        String tximage = intent.getStringExtra("Tximage");
        String tXname = intent.getStringExtra("TXname");
        String phimage = intent.getStringExtra("Phimage");
        final String title = intent.getStringExtra("Title");
        final String price = intent.getStringExtra("Price");
        mWhb_flag = intent.getIntExtra("whb_flag", -1);
        if (status.equals("已关闭")) {
            KLog.e("whb", "已关闭");
            mServiceshouchuLayout1Button.setText("已拒绝");
        } else if (status.equals("待付款")) {
            mServiceshouchuLayout1Button.setText("等待买家付款");
            mServiceshouchuLayout1Button.setClickable(false);
        } else if (status.equals("待确认")) {
            mServiceshouchuLayout1Button.setText("等待买家确认完成");
            //  mServiceshouchuLayout1Button.setClickable(false);
        } else if (status.equals("服务中")) {
            mServiceshouchuLayout1Button.setText("确认完成");
            mServiceshouchuLayout1Button.setBackgroundResource(R.color.jue);
            mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.white));
            mServiceshouchuLayout1Button.setOnClickListener(new View.OnClickListener() {
                //确认完成
                @Override
                public void onClick(View v) {
                    mMaterialDialog = new MaterialDialog(Serviceindentminute2Activity.this)
                            .setTitle("提示")
                            .setMessage("您确定完成服务吗?")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mProgressHUD.show();
                                    mMaterialDialog.dismiss();
                                    JJJD(0, "SellerCompleteWork");
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
        } else if (status.equals("待评价")) {
            mServiceshouchuLayout1Button.setText("评价");
            mServiceshouchuLayout1Button.setBackgroundResource(R.color.jue);
            mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.white));
            mServiceshouchuLayout1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPingjiaFlag==1){
                        Intent intent = new Intent(Serviceindentminute2Activity.this, ServiceIndentPingjiaActivity.class);
                        intent.putExtra("goodsid", mOid);
                        intent.putExtra("position",mWhb_flag);
                        startActivity(intent);
                    }else if (mPingjiaFlag==0){
                        Intent intent = new Intent(Serviceindentminute2Activity.this, PingjiaActivity.class);
                        intent.putExtra("goodsid", mOid);
                        intent.putExtra("position", mWhb_flag);
                        intent.putExtra("flag", 0);
                        startActivity(intent);
                    }
                }
            });
        } else if (status.equals("待接单")) {
            mServiceshouchuLayout1.setVisibility(View.GONE);
            mServiceshouchuLayout2.setVisibility(View.VISIBLE);
        } else if (status.equals("待服务")) {
            mServiceshouchuLayout1Button.setText("开始服务");
            mServiceshouchuLayout1Button.setBackgroundResource(R.color.jue);
            mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.white));
        }

     /*   KLog.e("whb'", "mOid" + mOid + "status" + status + "tximage" + tximage
                + "tXname" + tXname + "phimage" + phimage + "title" + title + "price" + price );*/
        mServiceindentminuteFlag.setText(status);
        Picasso.with(this).load(RequestAddress.IMAGE1 + tximage).fit().into(mServiceindentminuteImage);
        mServiceindentminuteName.setText(tXname);
        if (phimage != null && !phimage.equals("")) {
            Picasso.with(this).load(RequestAddress.IMAGE1 + phimage).fit().into(mServiceindentminuteImage1);
        } else {
            mServiceindentminuteImage1.setImageResource(R.drawable.img_fw_small);
        }
        mServiceindentminuteTitle.setText(title);
        mServiceindentminuteMoney.setText("¥" + price);
        mServiceindentminuteMoneys.setText(price + "元");
        mServiceindentminuteGoodsnum.setText("20170103561236584625143");
        NetWork(mUserid);
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.serviceshouchu_layout1_button, R.id.serviceindentminute_phone, R.id.serviceshouchu_layout2_button1, R.id.serviceshouchu_layout2_button2})
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
            //拒绝接单
            case R.id.serviceshouchu_layout2_button1:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("提示")
                        .setMessage("你确定拒绝接单?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                JJJD(1, "SellerRefuseOrder");
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();
                break;
            //同意接单
            case R.id.serviceshouchu_layout2_button2:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("提示")
                        .setMessage("你确定同意接单?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                               JJJD(2, "SellerAcceptOrder");
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();
                break;
            case R.id.serviceshouchu_layout1_button:
                String trim = mServiceshouchuLayout1Button.getText().toString().trim();
                if (trim.equals("开始服务")) {
                    mMaterialDialog = new MaterialDialog(this)
                            .setTitle("提示")
                            .setMessage("您确定要开始服务?")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mProgressHUD.show();
                                    mMaterialDialog.dismiss();
                                    JJJD(3, "SellerStartWorking");
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            });

                    mMaterialDialog.show();
                } else if (trim.equals("服务完成")) {
                    mMaterialDialog = new MaterialDialog(this)
                            .setTitle("提示")
                            .setMessage("您确定服务完成?")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mProgressHUD.show();
                                    mMaterialDialog.dismiss();
                               //     JJJD(4, "SellerCompleteWork");
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            });

                    mMaterialDialog.show();
                } else if (trim.equals("评价")) {
                    Intent intent = new Intent(Serviceindentminute2Activity.this, PingjiaActivity.class);
                    intent.putExtra("goodsid", mOid);
                    startActivity(intent);
                } else if (trim.equals("等待买家确认完成")) {

                }
                break;

        }
    }

    public void back_text_view(View view) {
        exitAct();
    }

    private void sendBroadCastRefreshMainMeUniversity(String flag,int whb_flag) {
        Intent intent = new Intent();
        intent.setAction(flag);
        intent.putExtra("whb_flag",whb_flag);
        KLog.e("whb",whb_flag);
        KLog.e("whb",flag);
        localBroadcastManager.sendBroadcast(intent);
    }

    public void JJJD(final int flag, String action) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", action);
        hashMap.put("uid", mUserid);
        hashMap.put("oid", mOid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mProgressHUD.dismiss();
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                String error = (String) mapForJson.get("error");
                if (code.equals("888") && secess.equals("true")) {
                    if (flag == 0) {
                        //待确认  等待买家确认完成
                        mServiceindentminuteFlag.setText("待确认");
                        mServiceshouchuLayout1Button.setText("等待买家确认完成");
                        mServiceshouchuLayout1Button.setBackgroundResource(R.color.white);
                        mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                        sendBroadCastRefreshMainMeUniversity(ACTION_NAME_SHOUCHU_FWWC,mWhb_flag);
                    }
                    if (flag == 1) {
                        mServiceshouchuLayout1.setVisibility(View.VISIBLE);
                        mServiceshouchuLayout2.setVisibility(View.GONE);
                        mServiceindentminuteFlag.setText("已关闭");
                        mServiceshouchuLayout1Button.setText("已拒绝");
                        mServiceshouchuLayout1Button.setBackgroundResource(R.color.white);
                        mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                       sendBroadCastRefreshMainMeUniversity(ACTION_NAME_SHOUCHU_JJJD,mWhb_flag);
                    }
                    if (flag == 2) {
                        mServiceshouchuLayout1.setVisibility(View.VISIBLE);
                        mServiceshouchuLayout2.setVisibility(View.GONE);
                        mServiceindentminuteFlag.setText("待服务");
                        mServiceshouchuLayout1Button.setText("开始服务");
                        mServiceshouchuLayout1Button.setBackgroundResource(R.color.jue);
                        mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.white));
                      sendBroadCastRefreshMainMeUniversity(ACTION_NAME_SHOUCHU_TYJD,mWhb_flag);
                    }
                    if (flag == 3) {
                        mServiceindentminuteFlag.setText("服务中");
                        mServiceshouchuLayout1Button.setText("服务完成");
                        mServiceshouchuLayout1Button.setBackgroundResource(R.color.jue);
                        mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.white));
                       sendBroadCastRefreshMainMeUniversity(ACTION_NAME_SHOUCHU_KSFW,mWhb_flag);
                    }
                    if (flag == 4) {
                        mServiceindentminuteFlag.setText("待评价");
                        mServiceshouchuLayout1Button.setText("评价");
                        mServiceshouchuLayout1Button.setBackgroundResource(R.color.jue);
                        mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.white));
                    }
                } else {
                    Toast.makeText(ECApplication.context, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mProgressHUD.dismiss();
                Toast.makeText(ECApplication.context, "操作失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Xiugai() {
        mServiceshouchuLayout1Button.setClickable(false);
        mServiceindentminuteFlag.setText("已评价");
        mServiceshouchuLayout1Button.setText("已评价");
        mServiceshouchuLayout1Button.setBackgroundResource(R.color.jue);
        mServiceshouchuLayout1Button.setTextColor(CommonUtil.getColor(R.color.white));
    }
}
