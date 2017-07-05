package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.Manifest;
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
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.PersonageBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.BottomPopupOption;
import com.safetyhuge.chanlian.safetyhuge.utils.ClipImageActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.IsMobileNOUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * Created by Chinalink on 2017/3/25 0025.
 */
public class PersonageActivity extends HBaseAct {
    @Bind(R.id.tx_img1)
    CircleImageView mTxImg;
    @Bind(R.id.tv_uname)
    TextView mTvUsername;
    @Bind(R.id.et_address)
    EditText mEtAddress;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.et_mail)
    EditText mEtMail;
    @Bind(R.id.button_infosave)
    Button mButtonInfosave;
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

    private Intent intent;
    PersonageBean bean = new PersonageBean();
    private String mPric;
    private String mCropImagePath;
    private String mUserid;
    private String mName;
    private KProgressHUD mProgressHUD;
    boolean falg = true;
    boolean falg1 = false;
    boolean falg2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personage);
        ButterKnife.bind(this);
        intent = new Intent();
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        mName = (String) get(ECApplication.context, "name", "");
      /*  KLog.e(bean.toString()
        );*/
        InitView();
        mProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        //创建拍照存储的临时文件
        createCameraTempFile(savedInstanceState);
    }

    public void back_text_view(View view) {
        exitAct();
    }

    /* @OnClick({R.id.iv_address, R.id.iv_phone, R.id.iv_mailbox})
     public void onClick(View view) {
         switch (view.getId()) {

         }
     }*/
    public void InitView() {
        mPric = (String) SharedPrefsUtil.get(ECApplication.context, "prcurl", "");
        KLog.e("prcurl", mPric);
        mTvUsername.setText((String) SharedPrefsUtil.get(ECApplication.context, "name", ""));
        mEtPhone.setText((String) SharedPrefsUtil.get(ECApplication.context, "phone", ""));
        mEtAddress.setText((String) SharedPrefsUtil.get(ECApplication.context, "address", ""));
        SharedPrefsUtil.get(ECApplication.context, "name", "");
        SharedPrefsUtil.get(ECApplication.context, "email", "");
        mEtMail.setText((String) SharedPrefsUtil.get(ECApplication.context, "email", ""));
        Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1  + mPric).fit().into(mTxImg);
       /* ImageLoader.getInstance().loadImage(RequestAddress.IMAGE1 + mPric, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                mTxImg.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });*/
    }

    public void NetWork(String email, String qq, String mobile, String address, String username, String uid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("qq", qq);
        hashMap.put("mobile", mobile);
        hashMap.put("address", address);
        hashMap.put("username", username);
        hashMap.put("uid", uid);
        OkGo.post(RequestAddress.HOST + RequestAddress.XGGRZL).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Toast.makeText(PersonageActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
            }
        });
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @OnClick({R.id.button_infosave, R.id.tx_img1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_infosave:
                String eaddress = mEtAddress.getText().toString();
                String ephone = mEtPhone.getText().toString();
                String email = mEtMail.getText().toString();
                if (!eaddress.isEmpty()) {
                    if (!ephone.isEmpty()) {
                        if (!email.isEmpty()) {
                            if (IsMobileNOUtils.isMobileNO(ephone)) {
                                    if (IsMobileNOUtils.isEmail(email)){
                                        mProgressHUD.show();
                                        if (mCropImagePath!=null){
                                            saveMsg(mUserid,email,ephone,eaddress);
                                        }else{
                                            NetWork(email,"",ephone,eaddress, mName, mUserid);
                                        }
                                    }else{
                                        Toast.makeText(this, "请输入正确邮箱地址", Toast.LENGTH_SHORT).show();
                                    }
                            } else {
                                Toast.makeText(this, "请输入正确电话号码", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入地址", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tx_img1:
                type = 2;
                final BottomPopupOption bottomPopupOption = new BottomPopupOption(PersonageActivity.this);
                bottomPopupOption.setItemText("拍照", "选择相册");
                // bottomPopupOption.setColors();//设置颜色
                bottomPopupOption.showPopupWindow();
                bottomPopupOption.setItemClickListener(new BottomPopupOption.onPopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        switch (position) {
                            case 0:
                                //权限判断
                                if (ContextCompat.checkSelfPermission(PersonageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请WRITE_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(PersonageActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                                } else {
                                    //跳转到调用系统相机
                                    gotoCarema();
                                }
                                bottomPopupOption.dismiss();
                                break;
                            case 1:
                                //权限判断
                                if (ContextCompat.checkSelfPermission(PersonageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请READ_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(PersonageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
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
                    mTxImg.setImageBitmap(mBitMap);
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

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

    private void saveMsg(String uid, final String email, final String  ephone, final String eaddress) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "UploadUserAvatar");
        hashMap.put("uid", uid);
        if (mCropImagePath != null) {
            OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).params("Filedata", new File(mCropImagePath)).execute(new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    KLog.e("mCropImagePath", mCropImagePath);
                    KLog.json(s);
                    Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                    //"code": "888",
                    //"secess": "true",
                    Object code = mapForJson.get("code");
                    Object secess = mapForJson.get("secess");
                    if (code.equals("888")&&secess.equals("true")){
                        NetWork(email,"",ephone,eaddress, mName, mUserid);
                    }
                }
            });
        } else {
            falg=false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
