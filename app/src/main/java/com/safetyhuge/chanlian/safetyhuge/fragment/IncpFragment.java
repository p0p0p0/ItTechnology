package com.safetyhuge.chanlian.safetyhuge.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProjectBean;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.core.ClientUser;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.LauncherActivity;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.LoginsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.ProductSoppinggActivity;
import com.safetyhuge.chanlian.safetyhuge.adapter.MySubAdapter;
import com.safetyhuge.chanlian.safetyhuge.adapter.MySuperAdapter;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.yuntongxun.ecsdk.ECInitParams;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 询价
 * 徐艳昭
 * 15326057231@163.com
 * 2017/3/6
 **/

public class IncpFragment extends Fragment {
    @Bind(R.id.button_text)
    TextView mTextView;
    @Bind(R.id.superlistView)
    ListView mSuperListView;
    @Bind(R.id.subListView)
    ListView mSubListView;
    @Bind(R.id.button_im)
    ImageView mButtonIm;
    private Context mContext;
    private MySuperAdapter mSuperAdapter;
    private MySubAdapter mSubAdapter;
    private List<ProjectBean.DataBean.IndusBean> mIndus;
    private String mMid1;
    private ArrayList<String> mSuperArrayListName, mSuperArrayListId, mSubArrayListSName, mSubArrayListSId, mSubSArrayListSName, mSubSArrayListSId;
    private KProgressHUD mKProgressHUD;
    private String mSuperid;
    private String mSudid;
    ECInitParams.LoginAuthType mLoginAuthType = ECInitParams.LoginAuthType.NORMAL_AUTH;
    private String mUserid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        View view = inflater.inflate(R.layout.fragment_incp, null);
        mContext = getActivity();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        ButterKnife.bind(this, view);
        initData();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mTextView.setTextColor(CommonUtil.getColor(R.color.juse));
        mTextView.setBackgroundColor(CommonUtil.getColor(R.color.beijing));
        NetWork();
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.e("mTextView", mTextView);
                mTextView.setTextColor(CommonUtil.getColor(R.color.juse));
                mTextView.setBackgroundColor(CommonUtil.getColor(R.color.beijing));
                mSubAdapter = new MySubAdapter(mContext, mSubArrayListSName);
                mSubListView.setAdapter(mSubAdapter);
                mSuperAdapter.setSelectedPosition(-1);
                mSuperAdapter.notifyDataSetInvalidated();
                SubclassSetListView(mSubArrayListSName, mSubArrayListSId);
            }
        });
        SuperclassListView();
    }

    /**
     * 请求网络
     */
    public void NetWork() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsTextInfo");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ProjectBean>() {
            @Override
            public void onSuccess(ProjectBean projectBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mTextView.setVisibility(View.VISIBLE);
                ProjectBean.DataBean data = projectBean.getData();
                mIndus = data.getIndus();
                List<ProjectBean.DataBean.IndusBean> dataBeen = projectBean.getData().getIndus();
                mSuperArrayListName = new ArrayList<String>();
                mSuperArrayListId = new ArrayList<String>();
                mSubArrayListSName = new ArrayList<String>();
                mSubArrayListSId = new ArrayList<String>();
                for (int i = 0; i < dataBeen.size(); i++) {
                    mSuperArrayListName.add(dataBeen.get(i).getPname());
                    mSuperArrayListId.add(dataBeen.get(i).getPid());
                    for (int j = i; j < dataBeen.get(i).getCdata().size(); j++) {
                        mSubArrayListSName.add(dataBeen.get(i).getCdata().get(j).getCname());
                        mSubArrayListSId.add(dataBeen.get(i).getCdata().get(j).getCid());
                    }
                }
                mSuperAdapter = new MySuperAdapter(mContext, mSuperArrayListName);
                mSuperListView.setAdapter(mSuperAdapter);
                SubclassSetListView(mSubArrayListSName, mSubArrayListSId);
            }
        });

    }

    /**
     * 拿到选中的id
     *
     * @return
     */
    public int getTwoData() {
        for (int i = 0; i < mIndus.size(); i++) {
            String pid = mIndus.get(i).getPid();
            if (mMid1 != null) {
                if (mMid1.equals(pid)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 父类ListView
     */
    private void SuperclassListView() {
        mSuperListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                KLog.e("mLocation", position);
                mMid1 = mSuperArrayListId.get(position);
                mTextView.setTextColor(Color.BLACK);
                mTextView.setBackgroundColor(Color.WHITE);
                mSuperid = mSuperArrayListId.get(position);
                int oneIndex = getTwoData();// get two data
                KLog.e("oneIndex", oneIndex);
                List<ProjectBean.DataBean.IndusBean.CdataBean> cdata = mIndus.get(oneIndex).getCdata();
                mSubSArrayListSName = new ArrayList<>();
                mSubSArrayListSId = new ArrayList<>();
                for (ProjectBean.DataBean.IndusBean.CdataBean cdataBean : cdata) {
                    mSubSArrayListSName.add(cdataBean.getCname());
                    mSubSArrayListSId.add(cdataBean.getCid());
                }
                mSuperAdapter.setSelectedPosition(position);
                mSuperAdapter.notifyDataSetInvalidated();
                SubclassSetListView(mSubSArrayListSName, mSubSArrayListSId);
            }
        });
    }

    /**
     * 子类ListView
     */
    private void SubclassSetListView(final ArrayList<String> arrayList1, final ArrayList<String> arrayList2) {
        mSubAdapter = new MySubAdapter(mContext, arrayList1);
        mSubListView.setAdapter(mSubAdapter);
        mSubListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                mSubAdapter.setSelectedPosition(position);
                mSubAdapter.notifyDataSetInvalidated();
                KLog.e("position", position);
                //询价详细
                mSudid = arrayList2.get(position);
                Intent intent = new Intent(mContext, ProductSoppinggActivity.class);
                intent.putExtra("flag", 2);
                intent.putExtra("indus_pid", mSuperid);
                intent.putExtra("indus_id", mSudid);
                KLog.e("aaaaaaaa" + mSuperid + mSudid);
                mContext.startActivity(intent);
            }
        });
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    public void button_im() {
        if (mUserid != "") {
            login(mUserid);
        } else {
            showNormalDialog();
        }
    }

    //初始化信息后登陆im
    private void login(String selfId) {
        String appkey = FileAccessor.getAppKey();
        String token = FileAccessor.getAppToken();
        ClientUser clientUser = new ClientUser(selfId);
        clientUser.setAppKey(appkey);
        clientUser.setAppToken(token);
        clientUser.setLoginAuthType(mLoginAuthType);
        clientUser.setPassword("");
        CCPAppManager.setClientUser(clientUser);
        SDKCoreHelper.init(getActivity(), ECInitParams.LoginMode.FORCE_LOGIN);
        Intent intent = new Intent(getActivity(), LauncherActivity.class);
        intent.putExtra("launcher_from", 1);
        // 注册成功跳转
        startActivity(intent);
    }

    private void showNormalDialog() {
        new CBDialogBuilder(getActivity())
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
                        startActivity(new Intent(getActivity(), LoginsActivity.class));
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

    @OnClick(R.id.button_im)
    public void onClick() {
        button_im();
    }
}
