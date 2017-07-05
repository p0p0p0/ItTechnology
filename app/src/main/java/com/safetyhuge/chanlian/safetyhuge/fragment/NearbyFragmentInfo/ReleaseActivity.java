package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CallBack;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.Activity.ReleaseFwActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.Activity.ReleaseQzActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.zhl.cbdialog.CBDialogBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/5/16 0016 17:12
 * 邮箱：995696826@qq.com
 */

public class ReleaseActivity extends Activity {
    @Bind(R.id.layout_fabu)
    LinearLayout mLayoutFabu;
    @Bind(R.id.layout_qiuzhu)
    LinearLayout mLayoutQiuzhu;
    @Bind(R.id.layout_guanbi)
    ImageView mLayoutGuanbi;
    CallBack callBack = null;
    private String mUsername;
    private String mUserid;
    public static ReleaseActivity instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_layout);
        ButterKnife.bind(this);
        instance = this;
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
    }

    @OnClick({R.id.layout_fabu, R.id.layout_qiuzhu, R.id.layout_guanbi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_fabu:
                if (mUserid != "") {
                    finish();
                    startActivity(new Intent(this, ReleaseFwActivity.class));
                } else {
                    showNormalDialog();
                }

                break;
            case R.id.layout_qiuzhu:
                if (mUserid != "") {
                    finish();
                    startActivity(new Intent(this, ReleaseQzActivity.class));
                } else {
                    showNormalDialog();
                }

                break;
            case R.id.layout_guanbi:
                SharedPrefsUtil prefsUtil = new SharedPrefsUtil();
                prefsUtil.put(ECApplication.context,"release",false);
                finish();
                break;
        }
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
                        startActivity(new Intent(ReleaseActivity.this, LoginsActivity.class));
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

    @Override
    protected void onResume() {
        super.onResume();
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }


}
