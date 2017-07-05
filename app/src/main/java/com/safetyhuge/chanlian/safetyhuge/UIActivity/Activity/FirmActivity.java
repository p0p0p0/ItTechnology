package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.FirmBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.BottomPopupOption;
import com.safetyhuge.chanlian.safetyhuge.utils.ClipImageActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * Created by Chinalink on 2017/3/25 0025.
 */
public class FirmActivity extends HBaseAct {
    @Bind(R.id.firm_qiyename)
    EditText mFirmQiyename;
    @Bind(R.id.firm_faren)
    EditText mFirmFaren;
    @Bind(R.id.firm_num)
    EditText mFirmNum;
    @Bind(R.id.firm_iamge)
    ImageView mFirmIamge;
    @Bind(R.id.issueproject_butto_update)
    Button mIssueprojectButtoUpdate;
    @Bind(R.id.issueproject_button)
    Button mIssueprojectButton;
    @Bind(R.id.firm_username)
    TextView mFirmUsername;
    @Bind(R.id.frim_layout)
    LinearLayout mFrimLayout;
    private Intent intent;
    private String mName;
    private String mUserid;
    //调用照相机返回图片临时文件
    private File tempFile;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    // 1: qq, 2: weixin
    private int type;
    private Bitmap mBitMap;
    private String mCropImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm);
        ButterKnife.bind(this);
        intent = new Intent();
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        mName = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        mFirmUsername.setText(mName);
        //创建拍照存储的临时文件
        createCameraTempFile(savedInstanceState);
        HBaseAct.showKProgressHUD("加载中....", this);
        NetWork1(mUserid);
    }

    private void NetWork1(String userid) {
        OkGo.post(RequestAddress.HOST + "qyrz2.php").params("uid", userid).execute(new JsonCallback<FirmBean>() {
            @Override
            public void onSuccess(FirmBean firmBean, Call call, Response response) {
                HBaseAct.dismissProgressHUD();
                List<FirmBean.DataBean> data = firmBean.getData();
                String auth_status = data.get(0).getAuth_status();
                String company = data.get(0).getCompany();
                String legal = data.get(0).getLegal();
                String licen_num = data.get(0).getLicen_num();
                String licen_pic = data.get(0).getLicen_pic();
                if (auth_status.equals("1")) {
                    mFrimLayout.setVisibility(View.GONE);
                    mFirmQiyename.setText(company);
                    mFirmFaren.setText(legal);
                    mFirmNum.setText(licen_num);
                    Picasso.with(FirmActivity.this).load(RequestAddress.IMAGE1 + licen_pic).into(mFirmIamge);
                }
            }
        });
    }

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

    @SuppressLint("WrongConstant")
    @OnClick({R.id.issueproject_butto_update, R.id.issueproject_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.issueproject_button:
                String name = mFirmQiyename.getText().toString().trim();
                String faren = mFirmFaren.getText().toString().trim();
                String num = mFirmNum.getText().toString().trim();
                if (!name.isEmpty()) {
                    if (!faren.isEmpty()) {
                        if (!num.isEmpty()) {
                            if (mBitMap != null) {
                                NetWork(mUserid, name, num, faren);
                            } else {
                                Toast.makeText(this, "请上传营业执照", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "请输入登记注册号码", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "请输入法人代表", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入企业名", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.issueproject_butto_update:
                type = 2;
                final BottomPopupOption bottomPopupOption = new BottomPopupOption(FirmActivity.this);
                bottomPopupOption.setItemText("拍照", "选择相册");
                // bottomPopupOption.setColors();//设置颜色
                bottomPopupOption.showPopupWindow();
                bottomPopupOption.setItemClickListener(new BottomPopupOption.onPopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        switch (position) {
                            case 0:
                                //权限判断
                                if (ContextCompat.checkSelfPermission(FirmActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请WRITE_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(FirmActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                                } else {
                                    //跳转到调用系统相机
                                    gotoCarema();
                                }
                                bottomPopupOption.dismiss();
                                break;
                            case 1:
                                //权限判断
                                if (ContextCompat.checkSelfPermission(FirmActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请READ_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(FirmActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                                } else {
                                    //跳转到调用系统图库
                                    gotoPhoto();
                                }
                                bottomPopupOption.dismiss();
                                break;
                        }
                    }
                });
                break;
        }
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 跳转到照相机
     */
    private void gotoCarema() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 外部存储权限申请返回
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCarema();
            } else {
                // Permission Denied
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            } else {
                // Permission Denied
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    mCropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    mBitMap = BitmapFactory.decodeFile(mCropImagePath);
                    KLog.e("cropImagePath", mCropImagePath);
                    mFirmIamge.setImageBitmap(mBitMap);
                }
                break;
        }
    }


    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private void NetWork(String userid, String company, String licen_num, String legal) {
        /*返回企业认证
        http://192.168.6.201/app/qyrz2.php
        参数	参数说明
        company	企业名称（传递参数）
        licen_num	营业执照号码（传递参数）
        legal	法人代表（传递参数）
        licen_pic	上传营业执照（传递参数）
        auth_status	认证状态：0认证中，1已认证（传递参数）
        uid	用户id（传递参数）*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("company", company);
        hashMap.put("licen_num", licen_num);
        hashMap.put("legal", legal);
        hashMap.put("auth_status", "0");
        hashMap.put("uid", userid);
        KLog.e("hashmap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + "app/qyrz2.php").params(hashMap).params("licen_pic", new File(mCropImagePath)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                //exitAct();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
