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

/**
 * 作者：王海宾 on 2017/5/18 0018 09:59
 * 邮箱：995696826@qq.com
 */

public class ServiceHelp2Activity extends HBaseAct {


    @Bind(R.id.qiuzhu1_flag)
    TextView mQiuzhu1Flag;
    @Bind(R.id.qiuzhu1_iamge)
    CircleImageView mQiuzhu1Iamge;
    @Bind(R.id.qiuzhu1_name)
    TextView mQiuzhu1Name;
    @Bind(R.id.qiuzhu1_phone)
    ImageButton mQiuzhu1Phone;
    @Bind(R.id.serviceindentminute_msg)
    ImageButton mServiceindentminuteMsg;
    @Bind(R.id.qiuzhu1_pic)
    ImageView mQiuzhu1Pic;
    @Bind(R.id.qiuzhu1_title)
    TextView mQiuzhu1Title;
    @Bind(R.id.qiuzhu1_money)
    TextView mQiuzhu1Money;
    @Bind(R.id.qiuzhu1_count)
    TextView mQiuzhu1Count;
    @Bind(R.id.serviceindentminute_layout)
    LinearLayout mServiceindentminuteLayout;
    @Bind(R.id.qiuzhu1_moneys)
    TextView mQiuzhu1Moneys;
    @Bind(R.id.qiuzhu1_time)
    TextView mQiuzhu1Time;
    @Bind(R.id.qiuzhu1_indent)
    TextView mQiuzhu1Indent;
    @Bind(R.id.serviceindentminute_goodsnum)
    TextView mServiceindentminuteGoodsnum;
    @Bind(R.id.qiuzhu1_layout1_button)
    Button mQiuzhu1Layout1Button;
    @Bind(R.id.qiuzhu1_layout1)
    RelativeLayout mQiuzhu1Layout1;
    @Bind(R.id.qiuzhu1_layout2_button1)
    Button mQiuzhu1Layout2Button1;
    @Bind(R.id.qiuzhu1_layout2_button2)
    Button mQiuzhu1Layout2Button2;
    @Bind(R.id.qiuzhu1_layout2)
    RelativeLayout mQiuzhu1Layout2;
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
    private String mPhone1;
    private int mItem;


    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiuzhu2);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mProgressHUD = KProgressHUD.create(ServiceHelp2Activity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
      //  mServiceinLayout.setVisibility(View.INVISIBLE);
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
                mQiuzhu1Time.setText(DateUtils.timet(order_time));

            }
        });

    }


    private void initView() {
        mProgressHUD.dismiss();
        final Intent intent = getIntent();
     /*   //id
        intent.putExtra("hid", mData.get(position).getHelp_id());
        //状态
        intent.putExtra("status", holder.mSericeState.getText().toString().trim());
        if (mData.get(position).getSeller_pic() != "" && !mData.get(position).getSeller_pic().equals("")) {
            //头像
            intent.putExtra("TxImage",mData.get(position).getSeller_pic());
        }else{
            intent.putExtra("TxImage","");

        }
        //用户名
        intent.putExtra("Username", mData.get(position).getUsername());
        //电话
        intent.putExtra("phone", mData.get(position).getPhone());
        if (mData.get(position).getPic() != null && !mData.get(position).getPic().equals("")) {
            //图片
            intent.putExtra("Bgimage", mData.get(position).getPic());
        }else{
            intent.putExtra("Bgimage", "");
        }
        //标题
        intent.putExtra("title", mData.get(position).getTitle());
        //钱数
        intent.putExtra("money", mData.get(position).getPrice());
        //时间
        intent.putExtra("time",mData.get(position).getHelp_time()+mData.get(position).getUnit_time());*/
        mOid = intent.getStringExtra("hid");
        String status = intent.getStringExtra("status");
        String tximage = intent.getStringExtra("TxImage");
        String tXname = intent.getStringExtra("Username");
        String phimage = intent.getStringExtra("Bgimage");
        final String title = intent.getStringExtra("title");
        final String price = intent.getStringExtra("money");
        String time = intent.getStringExtra("time");
        mItem = intent.getIntExtra("item", -1);
        mPhone1 = intent.getStringExtra("phone");
        if (status.equals("已完成")) {
            mQiuzhu1Layout1Button.setText("已完成");
        } else if (status.equals("待完成")) {
            mQiuzhu1Layout1Button.setText("等待对方完成帮助");
            mQiuzhu1Layout1Button.setClickable(false);
        }  else if (status.equals("待确认")) {
            mQiuzhu1Layout1Button.setText("确认完成");
            mQiuzhu1Layout1Button.setBackgroundResource(R.color.jue);
            mQiuzhu1Layout1Button.setTextColor(CommonUtil.getColor(R.color.white));
            mQiuzhu1Layout1Button.setOnClickListener(new View.OnClickListener() {
                //确认完成
                @Override
                public void onClick(View v) {
                    mMaterialDialog = new MaterialDialog(ServiceHelp2Activity.this)
                            .setTitle("提示")
                            .setMessage("您确定已完成帮助吗?")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mProgressHUD.show();
                                    mMaterialDialog.dismiss();
                                    JJJD( 0, "ConfirmCompleteSeekHelp");
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
            mQiuzhu1Layout1Button.setText("评价");
            mQiuzhu1Layout1Button.setBackgroundResource(R.color.jue);
            mQiuzhu1Layout1Button.setTextColor(CommonUtil.getColor(R.color.white));
            mQiuzhu1Layout1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ServiceHelp2Activity.this, PingjiaActivity.class);
                    intent.putExtra("goodsid", mOid);
                    startActivity(intent);
                }
            });
        } else if (status.equals("待接受")) {
            mQiuzhu1Layout1Button.setText("等待对方接受");
            mQiuzhu1Layout1Button.setClickable(false);
            mQiuzhu1Layout2.setVisibility(View.VISIBLE);
            mQiuzhu1Layout1Button.setVisibility(View.GONE);

        } else if (status.equals("待服务")) {
            mQiuzhu1Layout1Button.setText("开始服务");
            mQiuzhu1Layout1Button.setBackgroundResource(R.color.jue);
            mQiuzhu1Layout1Button.setTextColor(CommonUtil.getColor(R.color.white));
        }else if (status.equals("求助中")) {
            mQiuzhu1Layout1Button.setText("已拒绝求助");
        }else if (status.equals("已放弃")){
            mQiuzhu1Flag.setText("已放弃");
            mQiuzhu1Layout1Button.setVisibility(View.GONE);
        }

     /*   KLog.e("whb'", "mOid" + mOid + "status" + status + "tximage" + tximage
                + "tXname" + tXname + "phimage" + phimage + "title" + title + "price" + price );*/
        mQiuzhu1Flag.setText(status);
        if (!tximage.equals("")){
            Picasso.with(this).load(RequestAddress.IMAGE1 + tximage).fit().into(mQiuzhu1Iamge);
        }else{
            mQiuzhu1Iamge.setImageResource(R.drawable.icon_small_tx);
        }
        mQiuzhu1Name.setText(tXname);
        if (phimage != null && !phimage.equals("")) {
            Picasso.with(this).load(RequestAddress.IMAGE1 + phimage).fit().into(mQiuzhu1Pic);
        } else {
            mQiuzhu1Pic.setImageResource(R.drawable.img_fw_small);
        }
        mQiuzhu1Title.setText(title);
        mQiuzhu1Money.setText("¥" + price);
        mQiuzhu1Moneys.setText(price + "元");
        mServiceindentminuteGoodsnum.setText("20170103561236584625143");
        mQiuzhu1Time.setText(DateUtils.timet(time));
      //  NetWork(mUserid);
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.qiuzhu1_phone, R.id.qiuzhu1_layout1_button, R.id.qiuzhu1_layout2_button1, R.id.qiuzhu1_layout2_button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qiuzhu1_phone:
                if (mPhone1.isEmpty() || IsMobileNOUtils.isMobileNO(mPhone1) == false) {
                    Toast.makeText(this, "对方没有留下电话", Toast.LENGTH_SHORT).show();
                } else {
                    mMaterialDialog = new MaterialDialog(this)
                            .setTitle("提示")
                            .setMessage("拨打:" + mPhone1)
                            .setPositiveButton("呼叫", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + mPhone1));
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
            case R.id.qiuzhu1_layout2_button1:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("提示")
                        .setMessage("你确定拒绝接单?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                JJJD(1, "RefuseSeekHelp");
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
            case R.id.qiuzhu1_layout2_button2:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("提示")
                        .setMessage("你确定同意接单?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                JJJD(2, "ConfirmSeekHelp");
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
                String trim = mQiuzhu1Layout1Button.getText().toString().trim();
                if (trim.equals("开始服务")) {
                    mMaterialDialog = new MaterialDialog(this)
                            .setTitle("提示")
                            .setMessage("您确定要开始服务?")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mProgressHUD.show();
                                    mMaterialDialog.dismiss();
                                    JJJD(3, "RefuseSeekHelp");
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
                                    JJJD(4, "ConfirmCompleteSeekHelp");
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
                    Intent intent = new Intent(ServiceHelp2Activity.this, PingjiaActivity.class);
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

    private void sendBroadCastRefreshMainMeUniversity(String flag, int item) {
        Intent intent = new Intent();
        intent.setAction(flag);
        intent.putExtra("item",item);
        localBroadcastManager.sendBroadcast(intent);
    }

    public void JJJD(final int flag, String action) {
       HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", action);
        hashMap.put("uid", mUserid);
        hashMap.put("hid", mOid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mProgressHUD.dismiss();
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = (String) mapForJson.get("code");
                String secess = (String) mapForJson.get("secess");
                if (code.equals("888") && secess.equals("true")) {
                    if (flag == 0) {
                        mQiuzhu1Layout2.setVisibility(View.GONE);
                        mQiuzhu1Layout1Button.setVisibility(View.VISIBLE);
                        mQiuzhu1Flag.setText("已完成");
                        mQiuzhu1Layout1Button.setText("已完成");
                        mQiuzhu1Layout1Button.setBackgroundResource(R.color.white);
                        mQiuzhu1Layout1Button.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                        sendBroadCastRefreshMainMeUniversity("com.help2.wancheng",mItem);
                    }
                    if (flag == 1) {
                        mQiuzhu1Layout2.setVisibility(View.GONE);
                        mQiuzhu1Layout1Button.setVisibility(View.VISIBLE);
                        mQiuzhu1Flag.setText("求助中");
                        mQiuzhu1Layout1Button.setText("已拒绝求助");
                        mQiuzhu1Layout1Button.setBackgroundResource(R.color.white);
                        mQiuzhu1Layout1Button.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                        sendBroadCastRefreshMainMeUniversity("com.help2.jujue",mItem);
                    }
                    if (flag == 2) {
                        mQiuzhu1Layout2.setVisibility(View.GONE);
                        mQiuzhu1Layout1Button.setVisibility(View.VISIBLE);
                        mQiuzhu1Flag.setText("待完成");
                        mQiuzhu1Layout1Button.setText("等待对方完成帮助");
                        mQiuzhu1Layout1Button.setBackgroundResource(R.color.white);
                        mQiuzhu1Layout1Button.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                        sendBroadCastRefreshMainMeUniversity("com.help2.tongyi",mItem);
                    }
                    if (flag == 3) {
                        mQiuzhu1Layout2.setVisibility(View.GONE);
                        mQiuzhu1Layout1Button.setVisibility(View.VISIBLE);
                        mQiuzhu1Flag.setText("已完成");
                        mQiuzhu1Layout1Button.setText("已完成");
                        mQiuzhu1Layout1Button.setBackgroundResource(R.color.white);
                        mQiuzhu1Layout1Button.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                        sendBroadCastRefreshMainMeUniversity("com.help2.wancheng",mItem);
                    }
                } else {
                    Toast.makeText(ECApplication.context, "操作失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mProgressHUD.dismiss();
                Toast.makeText(ECApplication.context, "操作失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
