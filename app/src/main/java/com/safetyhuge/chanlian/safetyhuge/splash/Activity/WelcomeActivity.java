package com.safetyhuge.chanlian.safetyhuge.splash.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferenceSettings;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferences;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.storage.ContactSqlManager;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.contact.ContactLogic;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.contact.ECContacts;
import com.safetyhuge.chanlian.safetyhuge.splash.util.SharedPreferencesUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.OnChatReceiveListener;
import com.yuntongxun.ecsdk.im.ECMessageNotify;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

import org.apache.http.params.HttpParams;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class WelcomeActivity extends Activity {

    /*@Bind(R.id.iv_entry)
    ImageView mIVEntry;
    @Bind(R.id.iv_entry1)
    ImageView mIVEntry1;
    @Bind(R.id.tv_group)
    TextView mTextView;*/

    private static final int ANIM_TIME = 1800;

    private static final float SCALE_END = 1.15F;
    private NetState mReceiver;
    private boolean mIsFirstOpen;
    private String mUrl;
    private HttpParams mParams;
    private String mIamge1;
    private Uri mUri;
    private boolean flag;
    private final static String ALBUM_PATH = Environment
            .getExternalStorageDirectory() + "/mypic_data/";
    private String mStr;
    ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        String   mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        // 判断是否是第一次开启应用
        mIsFirstOpen = SharedPreferencesUtil.getBoolean(this, SharedPreferencesUtil.FIRST_OPEN, true);
        // 如果是第一次启动，则先进入功能引导页
        if (mIsFirstOpen) {
            Intent intent1 = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent1);
            finish();
            return;
        } else {
            StartThread();
        }
        if (mUserid!=""){
            try {
                saveAccount(mUserid);
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
            login(mUserid);
        }
    }

    /**
     * 开启子线程更新ui
     */
    private void StartThread() {
        new Thread() {
            public void run() {
                SystemClock.sleep(400);
                CommonUtil.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(WelcomeActivity.this, Splash.class);
                        startActivity(intent);
                        WelcomeActivity.this.finish();
                    }
                });
            }
        }.start();
    }

    /**
     * 设置图片动画
     *
     * @param b
     */
    private void startMainActivity(boolean b) {
        if (b == true) {
            // mIVEntry.setVisibility(View.INVISIBLE);
            // mTextView.setVisibility(View.GONE);
            // mIVEntry1.setVisibility(View.VISIBLE);
            //   KLog.e("b1");
            //      Uri uri = Uri.parse(RequestAddress.IMAGE + mIamge1);
            //  mIVEntry1.setImageURI(uri);
            //  playAnimation(mIVEntry1);
        }
        if (b == false) {
            // KLog.e("b2");
            //   mIVEntry1.setVisibility(View.GONE);
            //  mIVEntry.setVisibility(View.VISIBLE);
            //  mTextView.setVisibility(View.VISIBLE);
            //  playAnimation(mIVEntry);
        }
    }

    /**
     * 动画
     *
     * @param v
     */
    private void playAnimation(final ImageView v) {
     /*   Observable.timer(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        KLog.e("b3");
                        startAnim(v);
                    }
                });*/
    }

    private void startAnim(ImageView imageView) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        // startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        WelcomeActivity.this.finish();
    }

    /**
     * 屏蔽物理返回按钮
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        onReceive1();
    }

    /**
     * 注册广播
     */
    private void networkMonitor() {
        mReceiver = new NetState();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);
    }

    /**
     * 注销广播
     */
    public void onReceive1() {
//        unregisterReceiver(mReceiver);
    }

    class NetState extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent arg1) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (!gprs.isConnected() && !wifi.isConnected()) {
                StartThread();
            } else {
                questNetwork();
            }
        }
    }

    /**
     * 请求网络
     */
    public void questNetwork() {
       /* KLog.e("questNetwork");
        OkGo.get(mUrl).params(mParams).execute(new JsonCallback<imageimfo>() {
            @Override
            public void onSuccess(imageimfo imageimfo, Call call, Response response) {
                KLog.e("请求成功");
                mIamge1 = imageimfo.getData().get(0).getImg_url();
                mUri = Uri.parse(RequestAddress.IMAGE1 + mIamge1);
                KLog.e("onSuccess" + mUri);
                saveImage();
                StartThread();
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                KLog.e("请求失败");
                StartThread();
            }
        });*/
    }

    /**
     * 把图片转换成位图
     */
    public void saveImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUri.toString());
                    String imageName = mUri.toString();
                    mStr = imageName.substring(imageName.indexOf("images/") + 14);
                    //  KLog.e("str++++++++" + mStr);
                    //  SPUtils.put(MyApplication.context,"imagename",mStr);
                    InputStream is = url.openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    saveFile(bitmap, mStr);
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 保存广告图指定保存的路径：
    public void saveFile(Bitmap bm, String fileName) throws IOException {
        File dirFile = new File(ALBUM_PATH);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(ALBUM_PATH + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    //初始化信息后登陆im
    private void login(String selfId) {
        ECDevice.initial(getApplicationContext(), new ECDevice.InitListener() {
            @Override
            public void onInitialized() {
                // SDK已经初始化成功
                KLog.e("whb","初始化SDK成功");
                //设置登录参数，可分为自定义方式和通讯账号验密方式，详情点此查看>>
                //设置通知回调监听包含登录状态监听，接收消息监听，呼叫事件回调监听和
                //设置接收来电事件通知Intent等，详情点此查看>>
                //验证参数是否正确，登陆SDK，详情点此查看>>\
                ECInitParams params = new ECInitParams();
                params.setOnChatReceiveListener(new OnChatReceiveListener() {
                    @Override
                    public void OnReceivedMessage(ECMessage ecMessage) {

                    }

                    @Override
                    public void onReceiveMessageNotify(ECMessageNotify ecMessageNotify) {

                    }

                    @Override
                    public void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage ecGroupNoticeMessage) {

                    }

                    @Override
                    public void onOfflineMessageCount(int i) {
                        KLog.e("whb",i);
                    }

                    @Override
                    public int onGetOfflineMessage() {
                        return 0;
                    }

                    @Override
                    public void onReceiveOfflineMessage(List<ECMessage> list) {

                    }

                    @Override
                    public void onReceiveOfflineMessageCompletion() {

                    }

                    @Override
                    public void onServicePersonVersion(int i) {

                    }

                    @Override
                    public void onReceiveDeskMessage(ECMessage ecMessage) {

                    }

                    @Override
                    public void onSoftVersion(String s, int i) {

                    }
                });
            }
            @Override
            public void onError(Exception exception) {
                //在初始化错误的方法中打印错误原因
                KLog.e("whb","初始化SDK失败"+exception.getMessage());
            }
        });
    }
    private void saveAccount(String  selfId) throws InvalidClassException {
        String appKey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser user = CCPAppManager.getClientUser();
        if (user == null) {
            user = new ClientUser(selfId);
        } else {
            user.setUserId(selfId);
        }
        user.setAppToken(token);
        user.setAppKey(appKey);
        user.setPassword("");
        user.setLoginAuthType(mLoginAuthType);
        CCPAppManager.setClientUser(user);
        ECPreferences.savePreference(ECPreferenceSettings.SETTINGS_REGIST_AUTO, user.toString(), true);
        // ContactSqlManager.insertContacts(contacts);
        ArrayList<ECContacts> objects = ContactLogic.initContacts();
        objects = ContactLogic.converContacts(objects);
        ContactSqlManager.insertContacts(objects);
    }
}
