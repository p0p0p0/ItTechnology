package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.MyBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.RechargeActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.ReleaseActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.Activity.ReleaseQzActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceindentminuteActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceIndent1.ACTION_NAME_Zf;
import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;
import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/4/7 0007 14:46
 * 邮箱：995696826@qq.com
 */

public class PayActivity extends HBaseAct {
    KProgressHUD MKProgressHUD;
    Intent intent;
    @Bind(R.id.pay_title)
    TextView mPayTitle;
    @Bind(R.id.pay_jine)
    TextView mPayJine;
    @Bind(R.id.pay_yue)
    TextView mPayYue;
    @Bind(R.id.pay_zhifu)
    LinearLayout mPayZhifu;
    @Bind(R.id.pay_layout)
    LinearLayout mPayLayout;
    private String mMoney;
    private String mTitle;
    private String mMoneyses;
    private String mUserid;
    private String mTask_id;
    private String mMid;
    private Object mBalance;
    private String mMflag;
    private String mJiekou;
    private String mOid;
    private int mPosition;
    private String mFlags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        MKProgressHUD = KProgressHUD.create(PayActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        intent = getIntent();
        mMoney = intent.getStringExtra("money");
        mTitle = intent.getStringExtra("mTitle");
        mMid = intent.getStringExtra("mid");
        mMflag = intent.getStringExtra("mflag");
        mOid = intent.getStringExtra("oid");
        //flags
        mFlags = intent.getStringExtra("flags");
        //position
        mPosition = intent.getIntExtra("position", -1);
        KLog.e("mMflag", mMflag);
        KLog.e("mMoney", mMoney);
        KLog.e("mTitle", mTitle);
        KLog.e("mMoneyses", mMoney);

        if (mMflag != null) {
            if (mMflag.equals("1") || mMflag.equals("2") || mMflag.equals("5")) {
                mPayLayout.setVisibility(View.GONE);
            }
        }
        mBalance = SharedPrefsUtil.get(ECApplication.context, "mBalance", "");
        initView();
        NetWork(mUserid);
    }

    public void back_text_view(View view) {
        if (mMflag != null) {
            if (mMflag.equals("-1")) {
                finish();
                IssueProjectActivity.instance.finish();
                ReleaseProjectActivity.instance.finish();
            } else if (mMflag.equals("0")) {
                //方案管理展示
                exitAct();
            } else if (mMflag.equals("1")) {
                //订单支付
                exitAct();
                IndentActivity.instance.finish();
            } else if (mMflag.equals("2")) {
                exitAct();
            } else if (mMflag.equals("3")) {
                exitAct();
            } else if (mMflag.equals("4")) {
                exitAct();
            } else if (mMflag.equals("5")) {
                exitAct();
            }
        }
    }

    public void initView() {
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        mPayTitle.setText(mTitle);
        mPayJine.setText(mMoney);
    }

    @OnClick(R.id.pay_zhifu)
    public void onClick() {
        Double cny0 = Double.parseDouble(mBalance.toString()); //6.20
        Double cny1 = Double.parseDouble(mMoney); //6.20
        if (cny0 > cny1) {
            showAlertDialog();
        } else {
            duihua();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mMflag != null) {
                if (mMflag.equals("-1")) {
                    finish();
                    IssueProjectActivity.instance.finish();
                    ReleaseProjectActivity.instance.finish();
                } else if (mMflag.equals("0")) {
                    //方案管理展示
                    finish();
                } else if (mMflag.equals("1")) {
                    //订单支付
                    finish();
                    IndentActivity.instance.finish();
                } else if (mMflag.equals("2")) {
                    finish();
                } else if (mMflag.equals("3")) {
                    finish();
                } else if (mMflag.equals("5")) {
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showAlertDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.alert_dialogs, null));
        dialog.show();
        Button btnPositive = (Button) dialog.findViewById(R.id.btn_add);
        Button btnNegative = (Button) dialog.findViewById(R.id.btn_cancel);
        final EditText etContent = (EditText) dialog.findViewById(R.id.et_content);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String str = etContent.getText().toString();
                if (isNullEmptyBlank(str)) {
                    Toast.makeText(PayActivity.this, "请输入支付密码", Toast.LENGTH_SHORT).show();
                } else {
                    MKProgressHUD.show();
                    NetWork(str, dialog);
                }
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }

    private void NetWork(String str, final AlertDialog dialog) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (mMflag != null) {
            if (mMflag.equals("-1")) {
                hashMap.put("action", "PayItem");
                mJiekou = RequestAddress.WCYDRW;
            } else if (mMflag.equals("0") ) {
                mJiekou = "programme.php";
                hashMap.put("action", "PayItem");
            } else if (mMflag.equals("1") || mMflag.equals("2") || mMflag.equals("5")) {
                mJiekou = "goods.php";
                hashMap.put("action", "PayOrderItem");
            } else if (mMflag.equals("3")) {
                mJiekou = "goods.php";
                hashMap.put("action", "PayServiceOrderItem");
            } else if (mMflag.equals("4")) {
                mJiekou = "goods.php";
                hashMap.put("action", "PayHelpOrderItem");
            }
        } else {
            hashMap.put("action", "PayItem");
            mJiekou = RequestAddress.WCYDRW;
        }
        hashMap.put("uid", mUserid);
        if (mMflag != null) {
            if (mMflag.equals("-1")) {
                hashMap.put("task_id", mMid);
            } else if (mMflag.equals("0")) {
                hashMap.put("sid", mMid);
            } else if (mMflag.equals("1") || mMflag.equals("2") || mMflag.equals("3") || mMflag.equals("5")) {
                hashMap.put("oid", mOid);
            } else if (mMflag.equals("4")) {
                hashMap.put("hid", mOid);
            }
        } else {
            hashMap.put("task_id", mMid);
        }
        hashMap.put("password", str);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + mJiekou).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                MKProgressHUD.dismiss();
                KLog.json(s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                String error = (String) map.get("error");
                String secess = (String) map.get("secess");
                if (code.equals("888")) {
                    if (secess.equals("true")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        if (mMflag != null && !mMflag.equals("")) {
                            KLog.e("whb",mMflag);
                            if (mMflag.equals("-1")) {
                                KLog.e("whb","zoule");
                                finish();
                                IssueProjectActivity.instance.finish();
                                ReleaseProjectActivity.instance.finish();
                            } else if (mMflag.equals("0")) {
                                //方案管理展示
                                finish();
                                sendBroadCastRefreshMainMeUniversity(ACTION_NAME_Zf);
                            } else if (mMflag.equals("1")) {
                                //订单支付
                                finish();
                                IndentActivity.instance.finish();
                                //    startActivityForResult(new Intent(PayActivity.this, ChakanGoodsActivity.class),  1);
                            } else if (mMflag.equals("2") || mMflag.equals("3")) {
                                //订单支付
                                finish();
                                sendBroadCastRefreshMainMeUniversity(ACTION_NAME_Zf);
                                if (mFlags != null) {
                                    if (mFlags.equals("1")) {
                                        ServiceindentminuteActivity activity = new ServiceindentminuteActivity();
                                        activity.Xiugai1();
                                    }
                                }
                            } else if (mMflag.equals("4")) {
                                finish();
                                ReleaseQzActivity.instance.finish();
                                ReleaseActivity.instance.finish();
                                SharedPrefsUtil prefsUtil = new SharedPrefsUtil();
                                prefsUtil.put(ECApplication.context, "release", false);
                            } else if (mMflag.equals("5")) {
                                finish();
                                sendBroadCastRefreshMainMeUniversity("com.goods.zhifu");
                            }
                        }
                    }
                } else {
                    Toast.makeText(PayActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static boolean isNullEmptyBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return true;
        return false;
    }

    public void duihua() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//设置对话框图标，可以使用自己的图片，Android本身也提供了一些图标供我们使用
        builder.setIcon(android.R.drawable.ic_dialog_alert);
//设置对话框标题
        builder.setTitle("提示");
//设置对话框内的文本
        builder.setMessage("余额不足请充值");
//设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 执行点击确定按钮的业务逻辑
                startActivity(new Intent(PayActivity.this, RechargeActivity.class));
            }
        });
//设置取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 执行点击取消按钮的业务逻辑
            }
        });
//使用builder创建出对话框对象
        AlertDialog dialog = builder.create();
//显示对话框
        dialog.show();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void NetWork(String userid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetLoginUserInfo");
        hashMap.put("uid", userid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new JsonCallback<MyBean>() {
            @Override
            public void onSuccess(MyBean myBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                MyBean.DataBean data = myBean.getData();
                mBalance = data.getBalance();
                mPayYue.setText(mBalance.toString());
            }
        });
    }

    private void sendBroadCastRefreshMainMeUniversity(String a) {
        Intent intent = new Intent();
        intent.setAction(a);
        intent.putExtra("mPosition", mPosition);
        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
