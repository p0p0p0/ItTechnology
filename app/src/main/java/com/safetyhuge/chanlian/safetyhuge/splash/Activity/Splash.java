package com.safetyhuge.chanlian.safetyhuge.splash.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.MainActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferenceSettings;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferences;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.storage.ContactSqlManager;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.contact.ContactLogic;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.contact.ECContacts;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.yuntongxun.ecsdk.ECInitParams;

import java.io.File;
import java.io.InvalidClassException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Splash extends Activity {
    @Bind(R.id.splash_image)
    ImageView mSplashImage;
    // 声明控件对象
    private TextView textView;
    private Animation animation;
    private boolean flag = true;
    private int mNum;
    ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*String ALBUM_PATH = Environment
                .getExternalStorageDirectory() + "/mypic_data/" +
                str;*/
        setContentView(R.layout.activity_splash);
        String mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        ButterKnife.bind(this);
        // 初始化控件对象
        textView = (TextView) findViewById(R.id.tv_number_splash);
        animation = AnimationUtils.loadAnimation(this, R.anim.splashaa);
        String text = (String) textView.getText();
        mNum = Integer.parseInt(text);
        KLog.e("num" + mNum);
        Intent intent = getIntent();
        String url = intent.getStringExtra("uri");
        KLog.e("url" + url);
       // mSplashImage.setImageBitmap(getDiskBitmap(ALBUM_PATH));
        mDownTimer.start();
        if (mUserid!=""){
            try {
                saveAccount(mUserid);
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
                KLog.e("高度" + bitmap.getHeight());
            }
        } catch (Exception e) {
        }
        return bitmap;
    }

    private void getCount() {
        mNum--;
        if (flag == true) {
            if (mNum == 0) {
                startActivity();
            }
        } else {
            startActivity();
        }
    }

    private void startActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.splash_image, R.id.skip})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skip:
                mDownTimer.cancel();
                startActivity();
                break;
        }
    }

    CountDownTimer mDownTimer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            KLog.e("走了+++++", millisUntilFinished);
            mNum--;
            textView.setText(mNum + "");
            animation.reset();
            textView.startAnimation(animation);
            Long a = millisUntilFinished / 1000;
            int b = a.intValue();
            KLog.e("b++", b);
            if (b - 1 == 0) {
                startActivity();
            }
        }

        @Override
        public void onFinish() {
        }
    };
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
    //初始化信息后登陆im
    private void login(String  selfId) {
        String appkey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appkey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(mLoginAuthType);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);
    }
    private void saveAccount(String selfId) throws InvalidClassException {
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
