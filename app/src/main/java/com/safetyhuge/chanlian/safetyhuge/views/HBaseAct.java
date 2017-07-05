package com.safetyhuge.chanlian.safetyhuge.views;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.IM.common.dialog.ECAlertDialog;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECNotificationManager;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.IMChattingHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.SdkErrorCode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 毕少杰 on 2016/9/7 0007.
 * 本应用中所有Activity的基类
 */
public abstract class HBaseAct extends AppCompatActivity {
    public static  KProgressHUD mKProgress;
    public static final String ACT_FINISHED_ACTION = "com.yuntongxun.Intent_ACTION_KICK_OFF";
    private InternalReceiver internalReceiver;
    // 广播，当收到动作时，结束activity
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACT_FINISHED_ACTION.equals(intent.getAction())) {// 结束
                Toast.makeText(context, "接收到了广播", Toast.LENGTH_SHORT).show();
                return;
            }
            onBaseReceive(intent);
        }
    };


    /**
     * 隐藏软键盘 hideSoftInputView
     */
    public void hideSoftInputView() {
        try {
            InputMethodManager manager = ((InputMethodManager) this
                    .getSystemService(Activity.INPUT_METHOD_SERVICE));
            if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
                if (getCurrentFocus() != null)
                    manager.hideSoftInputFromWindow(getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  void showKProgressHUD(String name,Activity activity){
        mKProgress = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(name)
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
    }
    public static  void dismissProgressHUD(){
        mKProgress.dismiss();
    }
    // 隐藏软键盘
    public void OtherhideSoftInputView() {
        try {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(this.getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 检查是否存在SDCard
     */
    public boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 800) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            if (options <= 0)
                options = 0;
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 处理广播消息
     *
     * @param intent
     */
    public void onBaseReceive(Intent intent) {

    }

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        ECDevice.setOnDeviceConnectListener(new ECDevice.OnECDeviceConnectListener() {
            @Override
            public void onConnect() {

            }

            @Override
            public void onDisconnect(ECError ecError) {

            }

            @Override
            public void onConnectState(ECDevice.ECConnectState ecConnectState, ECError ecError) {
                if (ecConnectState == ECDevice.ECConnectState.CONNECT_FAILED) {
                    if (ecError.errorCode == SdkErrorCode.SDK_KICKED_OFF) {
                        KLog.e("whb", "==帐号异地登陆");
                        handlerKickOff(ecError.errorMsg);
                    } else {
                        KLog.e("", "==其他登录失败,错误码：" + ecError.errorCode);
                    }
                    return;
                } else if (ecConnectState == ECDevice.ECConnectState.CONNECT_SUCCESS) {
                    KLog.e("", "==登陆成功");
                }
            }
        });

    }
    //异地登录
    public void handlerKickOff(String kickoffText) {
        if (isFinishing()) {
            return;
        }
        ECAlertDialog buildAlert = ECAlertDialog.buildAlert(this, kickoffText,
                getString(R.string.dialog_btn_confim),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ECNotificationManager.getInstance()
                                .forceCancelNotification();
                        restartAPP();
                    }
                });
        buildAlert.setTitle("异地登陆");
        buildAlert.setCanceledOnTouchOutside(false);
        buildAlert.setCancelable(false);
        buildAlert.show();
    }
    public void restartAPP() {
       // sendBroadCastRefreshMainMeUniversity("com.xiaxian");
        ActivitiesCollector.finishAll();
        /*ECDevice.unInitial();
        KLog.e("whb", "restartAPP");
        SDKCoreHelper.logout(false);*/
        ECDevice.logout(new ECDevice.OnLogoutListener() {
            @Override
            public void onLogout() {
                ECDevice.unInitial();
                SDKCoreHelper.release();
            }
        });
        SharedPrefsUtil.clear(ECApplication.getInstance());
    }
    public LocalBroadcastManager localBroadcastManager;
    private void sendBroadCastRefreshMainMeUniversity(String  a) {
        Intent intent = new Intent();
        intent.setAction(a);
        localBroadcastManager.sendBroadcast(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != localBroadcastManager) {
            localBroadcastManager.unregisterReceiver(broadcastReceiver);
        }
        if (internalReceiver != null) {
            unregisterReceiver(internalReceiver);
        }
    }

    public void registerReceiver1(String[] action) {
        if (null != localBroadcastManager) {
            IntentFilter filter = new IntentFilter();
            filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
            for (int i = 0; i < action.length; i++) {
                filter.addAction(action[i]);
            }
            filter.addAction(ACT_FINISHED_ACTION);
            localBroadcastManager.registerReceiver(broadcastReceiver, filter);
        }
    }

    /**
     * @param @param intent 参数
     * @return void 返回类型
     * @throws
     * @Title: startActAnim
     * @Description: 启动其他活动时带默认动画
     */
    public void startActAnim(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.act_dync_in_from_right,
                R.anim.act_dync_out_to_left);
    }

    /**
     * @param inanim
     * @param outanim
     * @param intent  参数
     * @return void 返回类型
     * @throws
     * @Title: startActAnim
     * @Description: 启动其他活动时带自定义动�?
     */
    public void startActAnim(Intent intent, int inanim, int outanim) {
        startActivity(intent);
        overridePendingTransition(inanim, outanim);
    }

    /**
     * 启动activity并返回结果，默认动画
     *
     * @param intent
     * @param requestCode
     */
    public void startActivityForResultAnim(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.act_dync_in_from_right,
                R.anim.act_dync_out_to_left);
    }

    /**
     * 启动activity并返回结�?,自定义动�?
     *
     * @param intent
     * @param requestCode
     */
    public void startActivityForResultAnim(Intent intent, int requestCode,
                                           int inanim, int outanim) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(inanim, outanim);
    }

    /**
     * @return void 返回类型
     * @throws
     * @Title: exitAct 默认动画
     * @Description: �?出activity方法�?,处理善后数据.子类可重�?
     */
    public void exitAct() {
        finish();
        overridePendingTransition(R.anim.act_dync_in_from_left,
                R.anim.act_dync_out_to_right);
    }

    /**
     * @return void 返回类型
     * @throws
     * @Title: exitAct 自定义动�?
     * @Description: �?出activity方法�?,处理善后数据.子类可重�?
     */
    public void exitAct(int inanim, int outanim) {
        finish();
        overridePendingTransition(inanim, outanim);
    }

    @Override
    protected void onResume() {
        super.onResume();
      /*  // 注册第一次登陆同步消息
        registerReceiver(new String[] {
                IMChattingHelper.INTENT_ACTION_SYNC_MESSAGE,
                SDKCoreHelper.ACTION_SDK_CONNECT,
                SDKCoreHelper.ACTION_KICK_OFF
        });*///设置登录回调监听
}

    /**
     * 注册广播
     *
     * @param actionArray
     */
    public void registerReceiver(String[] actionArray) {
        if (actionArray == null) {
            return;
        }
        IntentFilter intentfilter = new IntentFilter();
        for (String action : actionArray) {
            intentfilter.addAction(action);
        }
        if (internalReceiver == null) {
            internalReceiver = new InternalReceiver();
        }
        registerReceiver(internalReceiver, intentfilter);
    }


    private class InternalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        KLog.e("whb", intent.getAction());
        // LogUtil.d(TAG, "[onReceive] action:" + intent.getAction());
        if (IMChattingHelper.INTENT_ACTION_SYNC_MESSAGE.equals(intent
                .getAction())) {
            KLog.e("whb", "INTENT_ACTION_SYNC_MESSAGE");

            // disPostingLoading();
        } else if (SDKCoreHelper.ACTION_SDK_CONNECT.equals(intent
                .getAction())) {
            KLog.e("whb", "ACTION_SDK_CONNECT");

            //   doInitAction();
        } else if (SDKCoreHelper.ACTION_KICK_OFF.equals(intent.getAction())) {
            String kickoffText = intent.getStringExtra("kickoffText");
            KLog.e("whb", "ACTION_KICK_OFF");
            //  handlerKickOff(kickoffText);
            Toast.makeText(context, kickoffText, Toast.LENGTH_SHORT).show();
        }
    }
}
}
