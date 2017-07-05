package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.SchemeDetailBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.StringUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;

/**
 * Created by Chinalink on 2017/3/30 0030.
 */

public class SchemeDetailActivity extends HBaseAct {
    @Bind(R.id.scheme_image)
    CircleImageView mSchemeImage;
    @Bind(R.id.scheme_name)
    TextView mSchemeName;
    @Bind(R.id.scheme_field)
    TextView mSchemeField;
    @Bind(R.id.scheme_item)
    TextView mSchemeItem;
    @Bind(R.id.scheme_down)
    TextView mSchemeDown;
    @Bind(R.id.scheme_content)
    TextView mSchemeContent;
    @Bind(R.id.scheme_collect)
    LinearLayout mSchemeCollect;
    @Bind(R.id.scheme_download)
    TextView mSchemeDownload;
    @Bind(R.id.scheme_layout)
    LinearLayout mSchemeLayout;
    @Bind(R.id.shoucang)
    ImageView mShoucang;
    @Bind(R.id.scheme_shouchang)
    TextView mSchemeShouchang;
    private Context mContext;
    KProgressHUD MKProgressHUD;
    private String mSchemeid;
    private String mUserid;
    public boolean b = true;
    public boolean b1 = true;
    private boolean mFlag;
    private SchemeDetailBean.DataBean mData;
    private String mMoney;
    private String mFile_path;
    private ArrayList<String> mStringList;
    private String mTitle;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_detail);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        KLog.e("uid", mUserid);
        mContext = SchemeDetailActivity.this;
        MKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        Intent intent = getIntent();
        mSchemeid = intent.getStringExtra("schemeid");
        KLog.e("mSchemeid", mSchemeid);
        mSchemeLayout.setVisibility(View.INVISIBLE);
        NewWork(mUserid, mSchemeid);
    }

    public void NewWork(String uid, String sid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsDetails");
        hashMap.put("uid", uid);
        hashMap.put("sid", sid);
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<SchemeDetailBean>() {
            @Override
            public void onSuccess(SchemeDetailBean schemeDetailBean, Call call, Response response) {
                MKProgressHUD.dismiss();
                mSchemeLayout.setVisibility(View.VISIBLE);
                mData = schemeDetailBean.getData();
                String username = mData.getUsername();
                mTitle = mData.getTitle();
                String time = mData.getOn_time();
                String pic = mData.getSeller_pic();
                KLog.e("pic", pic);
                mFlag = mData.isFavorite();
                String s = DateUtils.timesTwo(time);
                String content = mData.getContent();
                mMoney = mData.getPrice();
                String s1 = mData.getSale_num();
                mFile_path = mData.getFile_path();
                KLog.e("mFile_path", mFile_path);
                mStringList = new ArrayList<String>();
                if (mFile_path.indexOf("|")!=-1){
                    String[] c = mFile_path.split("|");
                    for (String s2 : c) {
                        mStringList.add(s2);
                    }
                }else{
                    mStringList.add(mFile_path);
                }
                if (mFlag != false) {
                    mShoucang.setImageResource(R.drawable.icon_shoucang_sel);
                    mSchemeShouchang.setText("已收藏");
                } else {
                    mShoucang.setImageResource(R.drawable.icon_shoucang_nor);
                }
                Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + pic).fit().into(mSchemeImage);
                mSchemeName.setText(username);
                mSchemeField.setText(mTitle);
                mSchemeItem.setText(s);
                mSchemeDown.setText(s1);
                mSchemeContent.setText(StringUtils.Transition(content));
                if (mMoney.equals("0.00")) {
                    mSchemeDownload.setText("下载方案(" + "免费" + ")");
                } else {
                    boolean pay_flag1 = mData.isPay_flag();
                    if (pay_flag1!=true){
                        mSchemeDownload.setText("下载方案(￥" + mMoney + "元)");
                    }else{
                        mSchemeDownload.setText("下载方案");
                    }
                }
            }
        });
    }

    @OnClick({R.id.scheme_collect, R.id.scheme_download})
    public void onClick(View view) {
        switch (view.getId()) {
            //收藏
            case R.id.scheme_collect:
                if (mFlag == false) {
                    if (b == true) {
                        if (mUserid != "") {
                            shoucang(mUserid, mSchemeid);
                        } else {
                            showNormalDialog();
                        }
                    } else {
                        quxiaoshoucang(mUserid, mSchemeid);
                    }
                    b = false;
                } else {
                    Toast.makeText(mContext, "您已收藏过", Toast.LENGTH_SHORT).show();
                }
                break;
            //下载
            case R.id.scheme_download:
                if (mUserid != "") {
                    if (mMoney.equals("0.00")) {
                        Intent intent = new Intent(this, SchemeFilesInfoActivity.class);
                        intent.putExtra("uid",mData.getService_id());
                        intent.putStringArrayListExtra("scheme",mStringList);
                        startActivity(intent);
                    } else {
                        boolean pay_flag = mData.isPay_flag();
                        if (pay_flag!=true){
                            //开启支付页面
                            Intent intent = new Intent(SchemeDetailActivity.this, PayActivity.class);
                            intent.putExtra("money", mMoney);
                            intent.putExtra("mTitle", mTitle);
                            intent.putExtra("mid", mData.getService_id());
                            intent.putExtra("mflag", "0");
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(this, SchemeFilesInfoActivity.class);
                            intent.putExtra("uid",mData.getService_id());
                            intent.putStringArrayListExtra("scheme",mStringList);
                            startActivity(intent);
                        }
                    }
                } else {
                    showNormalDialog();
                }
                break;
        }
    }

    public void shoucang(String uid, String sid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "AddFavorite");
        hashMap.put("uid", uid);
        hashMap.put("sid", sid);
        hashMap.put("type", "service");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json("shoucang", s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                if (code.equals("888")) {
                    Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
                    mSchemeShouchang.setText("已收藏");
                    mShoucang.setImageResource(R.drawable.icon_shoucang_sel);
                }
            }
        });
    }

    public void quxiaoshoucang(String uid, String sid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "DelFavorite");
        hashMap.put("uid", uid);
        hashMap.put("sid", sid);
        hashMap.put("type", "service");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json("shoucang", s);
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                if (code.equals("888")) {
                    Toast.makeText(mContext, "取消收藏成功", Toast.LENGTH_SHORT).show();
                    mShoucang.setImageResource(R.drawable.icon_shoucang_nor);
                }
            }
        });
    }

    private void showNormalDialog() {
        new CBDialogBuilder(this)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle("提示")
                .setMessage("请先登录")
                .setConfirmButtonText("确定")
                .setCancelButtonText("取消").setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                switch (whichBtn) {
                    case BUTTON_CONFIRM:
                        startActivity(new Intent(SchemeDetailActivity.this, LoginsActivity.class));
                        break;
                    case BUTTON_CANCEL:
                        break;
                    default:
                        break;
                }
            }
        })
                .setDialogAnimation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                .create()
                .show();
    }

    public void back_text_view(View view) {
        exitAct();
    }

    public void huoqu(String sid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "DownFile");
        hashMap.put("uid", "1");
        hashMap.put("sid", sid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        MobclickAgent.onResume(this);
        MKProgressHUD.show();
        NewWork(mUserid, mSchemeid);
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
