package com.safetyhuge.chanlian.safetyhuge.IM.ui.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferenceSettings;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferences;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.storage.ContactSqlManager;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.LauncherActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.ChattingActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting.ChattingFragment;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.contact.ContactLogic;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.contact.ECContacts;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.yuntongxun.ecsdk.ECInitParams;

import java.io.InvalidClassException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewLauncherActivity extends Activity {

    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.et_self_id)
    EditText etSelfId;
    @Bind(R.id.et_contact_id)
    EditText etContactId;
    ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
    @Bind(R.id.huihua)
    Button mHuihua;
    @Bind(R.id.huihua1)
    Button mHuihua1;

    //登陆id,相当于自己用户名
    private String selfId;

    //联系人id，相当于对方用户名
    private String contactId;
    private EditText mEtSelfId;
    private EditText mEtContactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_launcher);
        ButterKnife.bind(this);
        Button denglv = (Button) findViewById(R.id.btnLogin);
        Button huihua = (Button) findViewById(R.id.huihua);
        Button duihua = (Button) findViewById(R.id.huihua1);
        denglv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewLauncherActivity.this, "走了", Toast.LENGTH_SHORT).show();
                selfId = mEtSelfId.getText().toString().trim();
                try {
                    saveAccount();
                } catch (InvalidClassException e) {
                    e.printStackTrace();
                }
            }
        });
        huihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewLauncherActivity.this, "走了111", Toast.LENGTH_SHORT).show();
                login();
            }
        });
        duihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewLauncherActivity.this, "走了1111", Toast.LENGTH_SHORT).show();
                selfId = mEtSelfId.getText().toString().trim();
                contactId = mEtContactId.getText().toString().trim();
                initSelf();
                initContact();
            }
        });
        mEtSelfId = (EditText) findViewById(R.id.et_self_id);
        mEtContactId = (EditText) findViewById(R.id.et_contact_id);

    }

    //初始化自己登陆信息
    private void initSelf() {
        //im登陆
        String appKey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appKey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);//初始化用户自己的信息并且把数据放到contact数据库当中
        SDKCoreHelper.init(ECApplication.getInstance().getApplicationContext(), ECInitParams.LoginMode.FORCE_LOGIN);//初始化SDK
    }

    //初始化联系人信息 并把信息传到聊天界面
    private void initContact() {
        Intent intent = new Intent(this, ChattingActivity.class);
        intent.putExtra(ChattingFragment.RECIPIENTS, contactId);//相当于对方用户名
        intent.putExtra(ChattingFragment.CONTACT_USER, "55555");//相当于对方昵称
        intent.putExtra(ChattingFragment.CUSTOMER_SERVICE, false);
        startActivity(intent);
    }


    //初始化信息后登陆im
    private void login() {

       /* initSelf();
        initContact();*/
        String appkey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appkey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(mLoginAuthType);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);
        SDKCoreHelper.init(this, ECInitParams.LoginMode.FORCE_LOGIN);
        Intent intent = new Intent(this, LauncherActivity.class);
        intent.putExtra("launcher_from", 1);
        // 注册成功跳转
        startActivity(intent);
        finish();
    }


   /* //登陆按钮的点击事件监听
    @OnClick({R.id.huihua, R.id.btnLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.huihua:
                Toast.makeText(this, "走了", Toast.LENGTH_SHORT).show();
                Log.e("huiua","huiua");
                login();
                break;
            case R.id.btnLogin:
                Toast.makeText(this, "走了1", Toast.LENGTH_SHORT).show();
                Log.e("btnLogin","btnLogin");
                selfId = etSelfId.getText().toString().trim();
                if (selfId.isEmpty()) {
                    ToastUtil.showMessage("请输入您自己登陆的id");
                }
                try {
                    saveAccount();
                } catch (InvalidClassException e) {
                    e.printStackTrace();
                }
                break;
        }

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        initConfig();
    }

    private void initConfig() {
    }

    private void saveAccount() throws InvalidClassException {
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

   /* @OnClick(R.id.huihua1)
    public void onClick() {
        Log.e("huihua1","huihua1");
        Toast.makeText(this, "走了111", Toast.LENGTH_SHORT).show();
        selfId = etSelfId.getText().toString().trim();
        contactId = etContactId.getText().toString().trim();
        initSelf();
        initContact();
    }*/
}
