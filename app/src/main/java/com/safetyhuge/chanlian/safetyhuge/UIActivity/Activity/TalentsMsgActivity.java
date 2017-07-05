package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProductBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.SchemesBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.ProductAcapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.SchenesAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.TaskAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.ECApplication.context;

/**
 * Created by Chinalink on 2017/3/29 0029.
 */

public class TalentsMsgActivity extends HBaseAct {
    @Bind(R.id.user_image)
    CircleImageView mUserImage;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.user_region)
    TextView mUserRegion;
    @Bind(R.id.user_territory)
    TextView mUserTerritory;
    @Bind(R.id.user_yaoqing)
    ImageButton mUserYaoqing;
    @Bind(R.id.user_task)
    Button mUserTask;
    @Bind(R.id.user_scheme)
    Button mUserScheme;
    @Bind(R.id.user_product)
    Button mUserProduct;
    @Bind(R.id.rencai_listview1)
    MyListView mRencaiListview1;
    @Bind(R.id.layout_talents1)
    RelativeLayout mLayoutTalents1;
    @Bind(R.id.rencai_listview2)
    MyListView mRencaiListview2;
    @Bind(R.id.layout_talents2)
    RelativeLayout mLayoutTalents2;
    @Bind(R.id.rencai_listview3)
    MyListView mRencaiListview3;
    @Bind(R.id.layout_talents3)
    RelativeLayout mLayoutTalents3;
    @Bind(R.id.tamtsg_layout_text)
    TextView mTamtsgLayoutText;
    @Bind(R.id.tamtsg_layout)
    LinearLayout mTamtsgLayout;
    @Bind(R.id.talents_dengji)
    ImageView mTalentsDengji;
    @Bind(R.id.talents_zhuangtai)
    TextView mTalentsZhuangtai;
    @Bind(R.id.talents_zhengshu)
    TextView mTalentsZhengshu;
    private String mImageUrl, mName, mRegion, mTerritory, mTalentsid;
    private Activity mContext;
    private KProgressHUD mKProgressHUD;
    private boolean mB, mB1, mB2;
    String mUserid;
    private List<TaskBean.DataBean> mDataBeen;
    private String mUid;
    private String mUid1;
    private int mDengji;
    private String mZhuangtai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talentsmsg);
        ButterKnife.bind(this);
        mContext = TalentsMsgActivity.this;
        mKProgressHUD = KProgressHUD.create(TalentsMsgActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        InitView();
        mUserid = (String) SharedPrefsUtil.get(context, "UserUid", "");
        mB = true;
        mB1 = true;
        mB2 = true;
        ECApplication.addActivity(TalentsMsgActivity.this);

    }

    public void InitView() {
        Intent intent = getIntent();
        //用户图片
        mImageUrl = intent.getStringExtra("ImageUrl");
        //用户名称
        mName = intent.getStringExtra("name");
        //地区
        mRegion = intent.getStringExtra("region");
        //领域
        mTerritory = intent.getStringExtra("territory");
        mUid1 = intent.getStringExtra("uid");
        //用户id
        mTalentsid = intent.getStringExtra("talentsid");
        //等级
        mDengji = intent.getIntExtra("dengji", 0);
        //状态
        mZhuangtai = intent.getStringExtra("zhuangtai");
        //证书
        String zhengshu = intent.getStringExtra("zhengshu");
        Task(mTalentsid);
        final ArrayList<String> imageUrl = intent.getStringArrayListExtra("imageUrl");
        mTalentsZhengshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUrl!=null){
                    Intent intent = new Intent(TalentsMsgActivity.this,CertificateActivity.class);
                    intent.putStringArrayListExtra("image",imageUrl);
                    startActivity(intent);
                }
            }
        });

        KLog.e("图片" + mImageUrl + "用户名" + mName + "地区" + mRegion + "领域" + mTerritory + "用户id" + mTalentsid);
        //用户头像
        Picasso.with(context).load(mImageUrl).fit().into(mUserImage);
        //名称
        mUserName.setText(mName);
        //地区
        mUserRegion.setText(mRegion);
        //领域
        if (mTerritory.equals("")){
            mUserTerritory.setText("项目领域: 暂无");
        }else{
            mUserTerritory.setText("项目领域: "+mTerritory);
        }
        //等级
        dengji(mDengji,mTalentsDengji);
        //状态
        if (mZhuangtai.equals("0")){
            //个人
            mTalentsZhuangtai.setText("自由职业");
            mTalentsZhuangtai.setBackgroundResource(R.drawable.occupation_ziyou);
        }else{
            mTalentsZhuangtai.setText("企业就职");
            mTalentsZhuangtai.setBackgroundResource(R.drawable.occupation_jiuzhi);
        }
        //证书
        if (zhengshu!=null){
            mTalentsZhengshu.setText("证书: "+zhengshu);
        }else{
            mTalentsZhengshu.setText("证书: 暂无");
        }

    }

    @OnClick({R.id.user_yaoqing, R.id.user_task, R.id.user_scheme, R.id.user_product})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_yaoqing:
                KLog.e("uid", mUid1);
                if (mUserid.equals(mUid1)) {
                    Toast.makeText(mContext, "不能邀请自己", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, InvitationActivity.class);
                    intent.putExtra("yaoqing", mUid1);
                    startActivity(intent);
                }
                break;
            case R.id.user_task:
                if (mB1 == true) {
                    mTamtsgLayout.setVisibility(View.GONE);
                    mRencaiListview1.setVisibility(View.GONE);
                    mLayoutTalents1.setVisibility(View.VISIBLE);
                    mLayoutTalents2.setVisibility(View.GONE);
                    mLayoutTalents3.setVisibility(View.GONE);
                    mKProgressHUD.show();
                    mUserTask.setBackgroundResource(R.color.white);
                    mUserScheme.setBackgroundResource(R.color.button);
                    mUserProduct.setBackgroundResource(R.color.button);
                    Task(mTalentsid);
                }
                mB1 = false;
                mB = true;
                mB2 = true;
                break;
            case R.id.user_scheme:
                if (mB == true) {
                    mTamtsgLayout.setVisibility(View.GONE);
                    KLog.e("user_scheme", mUserScheme.isClickable());
                    mRencaiListview2.setVisibility(View.GONE);
                    mLayoutTalents1.setVisibility(View.GONE);
                    mLayoutTalents2.setVisibility(View.VISIBLE);
                    mLayoutTalents3.setVisibility(View.GONE);
                    mKProgressHUD.show();
                    mUserTask.setBackgroundResource(R.color.button);
                    mUserScheme.setBackgroundResource(R.color.white);
                    mUserProduct.setBackgroundResource(R.color.button);
                    Schemes();
                }
                mB1 = true;
                mB2 = true;
                mB = false;
                break;
            case R.id.user_product:
                if (mB2 == true) {
                    mTamtsgLayout.setVisibility(View.GONE);
                    mRencaiListview3.setVisibility(View.GONE);
                    mLayoutTalents1.setVisibility(View.GONE);
                    mLayoutTalents2.setVisibility(View.GONE);
                    mLayoutTalents3.setVisibility(View.VISIBLE);
                    mKProgressHUD.show();
                    mUserTask.setBackgroundResource(R.color.button);
                    mUserScheme.setBackgroundResource(R.color.button);
                    mUserProduct.setBackgroundResource(R.color.white);
                    Product();
                }
                mB2 = false;
                mB1 = true;
                mB = true;
                break;
        }
    }

    public void Task(String userid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfoByUser");
        hashMap.put("uid", userid);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<TaskBean>() {
            @Override
            public void onSuccess(TaskBean taskBean, Call call, Response response) {
                mDataBeen = taskBean.getData();
                KLog.e("size", mDataBeen.size() + "");
                mKProgressHUD.dismiss();
                mB1 = false;
                if (mDataBeen.size() == 0) {
                    mTamtsgLayout.setVisibility(View.VISIBLE);
                    mTamtsgLayoutText.setText("TA还没发布过项目");
                } else {
                    mRencaiListview1.setVisibility(View.VISIBLE);
                    mRencaiListview1.setAdapter(new TaskAdapter(mContext, mDataBeen));
                }
            }
        });
    }

    public void Schemes() {
        HashMap<String, String> hashMap = new HashMap<>();
        //GoodsManagement
        hashMap.put("action", "GoodsManagement");
        hashMap.put("uid", mTalentsid);
        hashMap.put("mid", "13");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).
                params(hashMap).execute(new JsonCallback<SchemesBean>() {
            @Override
            public void onSuccess(SchemesBean schemesBean, Call call, Response response) {
                List<SchemesBean.DataBean> dataBeen = schemesBean.getData();
                mRencaiListview2.setVisibility(View.VISIBLE);
                mKProgressHUD.dismiss();
                if (dataBeen != null&&dataBeen.size()!=0) {
                    mRencaiListview2.setAdapter(new SchenesAdapter(mContext, dataBeen));
                } else {
                    mTamtsgLayout.setVisibility(View.VISIBLE);
                    mTamtsgLayoutText.setText("TA还没发布过方案");
                }
            }
        });

    }

    public void Product() {
        HashMap<String, String> hashMap = new HashMap<>();
        //action	GoodsManagement
        hashMap.put("action", "GoodsManagement");
        hashMap.put("uid", mTalentsid);
        hashMap.put("mid", "6");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ProductBean>() {
            @Override
            public void onSuccess(ProductBean productBean, Call call, Response response) {
                List<ProductBean.DataBean> dataBeen = productBean.getData();
                mRencaiListview3.setVisibility(View.VISIBLE);
                mKProgressHUD.dismiss();
                if (dataBeen != null) {
                    mRencaiListview3.setAdapter(new ProductAcapter(mContext, dataBeen));
                } else {
                    mTamtsgLayout.setVisibility(View.VISIBLE);
                    mTamtsgLayoutText.setText("TA还没发布过产品");
                }
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(context, "UserUid", "");
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
                        startActivity(new Intent(TalentsMsgActivity.this, LoginsActivity.class));
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

    public void dengji(int dengji, ImageView view) {
        switch (dengji) {
            case 0:
                break;
            case 1:
                view.setImageResource(R.drawable.n1);
                break;
            case 2:
                view.setImageResource(R.drawable.n2);
                break;
            case 3:
                view.setImageResource(R.drawable.n3);
                break;
            case 4:
                view.setImageResource(R.drawable.n4);
                break;
            case 5:
                view.setImageResource(R.drawable.n5);
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                view.setImageResource(R.drawable.n8);
                break;
            case 9:
                view.setImageResource(R.drawable.n9);
                break;
            case 10:
                view.setImageResource(R.drawable.n10);
                break;
            case 11:
                view.setImageResource(R.drawable.n11);
                break;
            case 12:
                view.setImageResource(R.drawable.n12);
                break;
            case 13:
                view.setImageResource(R.drawable.n13);
                break;
            case 14:
                view.setImageResource(R.drawable.n14);
                break;
            case 15:
                view.setImageResource(R.drawable.n15);
                break;
            case 16:
                view.setImageResource(R.drawable.n16);
                break;
            case 17:
                view.setImageResource(R.drawable.n17);
                break;
            case 18:
                view.setImageResource(R.drawable.n18);
                break;
            case 19:
                view.setImageResource(R.drawable.n19);
                break;
            case 20:
                view.setImageResource(R.drawable.n20);
                break;
            case 21:
                view.setImageResource(R.drawable.n21);
                break;

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
