package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.baseview.StarBarView;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.Serviceindentminute2Activity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceindentminuteActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.ServiceIndent1.ACTION_NAME_PJ;

/**
 * 作者：王海宾 on 2017/4/25 0025 18:35
 * 邮箱：995696826@qq.com
 */
public class PingjiaActivity extends HBaseAct {

    @Bind(R.id.all_chekbox1)
    CheckBox mAllChekbox1;
    @Bind(R.id.all_chekbox2)
    CheckBox mAllChekbox2;
    @Bind(R.id.all_chekbox3)
    CheckBox mAllChekbox3;
    @Bind(R.id.pingjia_ed)
    EditText mPingjiaEd;
    @Bind(R.id.sbv_starbar)
    StarBarView mSbvStarbar;
    @Bind(R.id.sbv_starbar2)
    StarBarView mSbvStarbar2;
    @Bind(R.id.sbv_starbar3)
    StarBarView mSbvStarbar3;
    @Bind(R.id.pingjia_button)
    Button mPingjiaButton;
    private String mMuserid;
    private String mGoodsid;
    private String mMark_status = null;
    private String mUserid;
    private int mPosition,flag;
    private int mMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingjia);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        Intent intent = getIntent();
        mMark = intent.getIntExtra("pingjiaMark", -1);
        mMuserid = intent.getStringExtra("muserid");
        mGoodsid = intent.getStringExtra("goodsid");
        mPosition = intent.getIntExtra("position", -1);
        flag = intent.getIntExtra("flag", -1);
        initView();
    }

    private void initView() {
        mAllChekbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMark_status = "1";
                    mAllChekbox1.setClickable(false);
                    mAllChekbox2.setClickable(true);
                    mAllChekbox2.setChecked(false);
                    mAllChekbox3.setClickable(true);
                    mAllChekbox3.setChecked(false);
                }
            }
        });
        mAllChekbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMark_status = "2";
                    mAllChekbox2.setClickable(false);
                    mAllChekbox1.setClickable(true);
                    mAllChekbox1.setChecked(false);
                    mAllChekbox3.setClickable(true);
                    mAllChekbox3.setChecked(false);
                }
            }
        });
        mAllChekbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMark_status = "3";
                    mAllChekbox3.setClickable(false);
                    mAllChekbox2.setClickable(true);
                    mAllChekbox2.setChecked(false);
                    mAllChekbox1.setClickable(true);
                    mAllChekbox1.setChecked(false);
                }
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    @OnClick(R.id.pingjia_button)
    public void onClick() {
        String context = mPingjiaEd.getText().toString().trim();
        String xing1 = mSbvStarbar.getStarRating() + "";
        String xing2 = mSbvStarbar2.getStarRating() + "";
        String xing3 = mSbvStarbar3.getStarRating() + "";
        if (mMark_status != null) {
            if (!context.isEmpty()) {
                if (!xing1.equals("0.0") && !xing2.equals("0.0") && !xing3.equals("0.0")) {
                    NetWork(mUserid, mGoodsid, context, "1,2,3", xing1 + "," + xing2 + "," + xing3, mMark_status);
                } else {
                    Toast.makeText(this, "请选择星级", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请选择评价", Toast.LENGTH_SHORT).show();
        }
    }

    public void NetWork(String uid, String oid, String tar_content, String aid, String aid_star, String mark_status) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (mMark==1){
            hashMap.put("action", "MarkService");
        }else{
            hashMap.put("action", "Mark");
        }
        hashMap.put("uid", uid);
        hashMap.put("oid", oid);
        //内容
        hashMap.put("tar_content", tar_content);
        //评价
        hashMap.put("aid", aid);
        //aid_star 分数
        hashMap.put("aid_star", aid_star);
        //mark_status 总体评价
        hashMap.put("mark_status", mark_status);
        KLog.e("hash", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                HashMap<String, Object> map = (HashMap<String, Object>) JSONUtil.getMapForJson(s);
                String msg = (String) map.get("code");
                String error = (String) map.get("error");
                if (msg.equals("888")) {
                    KLog.e("whb","评价成功");
                    Toast.makeText(ECApplication.context, "评价成功", Toast.LENGTH_SHORT).show();
                  /*  if (ChakanGoodsActivity.instance != null) {
                        ChakanGoodsActivity.instance.finish();
                    }*/
                    sendBroadCastRefreshMainMeUniversity(ACTION_NAME_PJ);
               //     sendBroadCast(ACTION_NAME_SHOUCHU_PJ, mPosition);
                    //18454315250
                    sendBroadCast("com.goods.pingjia", mPosition);
                    if (flag==0){
                        Serviceindentminute2Activity serviceindentminute2Activity = new Serviceindentminute2Activity();
                        if (serviceindentminute2Activity != null) {
                            serviceindentminute2Activity.Xiugai();
                        }
                    }
                    if (flag==1){
                        ServiceindentminuteActivity activity = new ServiceindentminuteActivity();
                        if (activity != null) {
                            activity.Xiugai();
                        }
                    }
                    exitAct();
                } else {
                    if (error.equals("已经评价过了")) {
                        Toast.makeText(ECApplication.context, "已经评价过了", Toast.LENGTH_SHORT).show();
                        finish();
                        if (onListener != null) {
                            onListener.onItemClick(mPosition);
                        }
                        // sendBroadCast(ACTION_NAME_SHOUCHU_PJ,mPosition);
                        // sendBroadCastRefreshMainMeUniversity(ACTION_NAME_PJ);
                    } else {
                        Toast.makeText(ECApplication.context, "评价失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //创建接口
    public OnListener onListener;

    //接口
    public interface OnListener {
        //回调方法
        void onItemClick(int position);
    }

    //Adapter调用的方法
    public void setListener(OnListener listener) {
        this.onListener = listener;
    }

    private void sendBroadCastRefreshMainMeUniversity(String a) {
        Intent intent = new Intent();
        intent.setAction(a);
        intent.putExtra("mPosition", mPosition);
        localBroadcastManager.sendBroadcast(intent);
    }

    private void sendBroadCast(String a, int a1) {
        Intent intent = new Intent();
        intent.setAction(a);
        intent.putExtra("whb_flag", a1);
        localBroadcastManager.sendBroadcast(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
