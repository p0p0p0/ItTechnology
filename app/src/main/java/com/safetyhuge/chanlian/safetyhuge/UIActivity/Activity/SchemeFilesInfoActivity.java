package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProjectminuteBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/28 0028 15:08
 * 邮箱：995696826@qq.com
 */
public class SchemeFilesInfoActivity extends HBaseAct{
    private static final int REQUEST_CODE_PERMISSION_SD = 100;
    @Bind(R.id.product_file)
    ListView mProductFile;

    private List<ProjectminuteBean.DataBean.FileBean> mFileBeen;
    private KProgressHUD mProgressHUD;
    private String mSDPATH;
    private Context mContext;
    private String mUid;
    private ArrayList<String> mScheme;
    private String mUid1;
    private String mUserid;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_file);
        mSDPATH = Environment.getExternalStorageDirectory() + "/";
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        Intent mIntent = getIntent();
        mUid1 = mIntent.getStringExtra("uid");
        mScheme = mIntent.getStringArrayListExtra("scheme");
        KLog.e("mScheme",mScheme.size()+"");
        mContext = SchemeFilesInfoActivity.this;
        mProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_SD)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .send();
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_SD)
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .send();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (REQUEST_CODE_PERMISSION_SD == 100) {
                mProductFile.setAdapter(new SchemeFileAdapter(mContext,mScheme,mUserid,mUid1));
            } else if (requestCode == 101) {
                Toast.makeText(mContext, "权限不足查看附件,设置-应用-应用权限 打开权限", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
        }
    };
    public void back_text_view(View view) {
        exitAct();
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
