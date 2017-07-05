package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.XiangmuFragmentInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskList;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.TaskListAdapter;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/21 0021 16:17
 * 邮箱：995696826@qq.com
 */@SuppressLint("ValidFragment")
public class XiangmuFragment extends Fragment {
    private List<TaskList.DataBean> mWork_info;
    private List<TaskList.DataBean> mWork_info1;
    @Bind(R.id.text111)
    TextView mText111;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.quanbu_list1)
    MyListView mQuanbuList1;
    private String mUserid;
    private String mId;
    private Context mContext;
    private int a;
    private KProgressHUD mKProgressHUD;
    private TaskListAdapter mQuanAdapter;

    public XiangmuFragment(int a) {
        this.a = a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_xiangmufragment, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        KLog.e("走了走了");
        NetWork(mUserid);
        return view;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }
    public void NetWork(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskListInfoByUser");
        hashMap.put("uid", id);
        KLog.e("hashMap",hashMap);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<TaskList>() {
            @Override
            public void onSuccess(TaskList taskList, Call call, Response response) {
                mKProgressHUD.dismiss();
                mWork_info = taskList.getData();
                mWork_info1 = new ArrayList<TaskList.DataBean>();
                if (a == 0) {
                    if (mWork_info!=null&&!mWork_info.isEmpty()){
                        mQuanAdapter = new TaskListAdapter(getActivity(), mWork_info,0);
                        mQuanbuList1.setAdapter(mQuanAdapter);
                        mQuanbuImage.setVisibility(View.GONE);
                    }else{
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //单人悬赏
                } else if (a == 1) {
                    if (mWork_info!=null){
                        for (TaskList.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getModel_name();
                            if (str.equals("单人悬赏")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size()!=0){
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanbuList1.setAdapter(new TaskListAdapter(getActivity(), mWork_info1,0));

                        }else{
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无单人悬赏订单");
                        }
                    }else{
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }

                    //多人悬赏
                } else if (a == 2) {
                    if (mWork_info!=null){
                        for (TaskList.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getModel_name();
                            if (str.equals("多人悬赏")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size() != 0) {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanbuList1.setAdapter(new TaskListAdapter(getActivity(), mWork_info1,0));
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无多人悬赏订单");
                        }
                    }else{
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //普通招标
                } else if (a == 3) {
                    if (mWork_info!=null){
                        for (TaskList.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getModel_name();
                            if (str.equals("普通招标")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        KLog.e("mWork_info1", mWork_info1.size());
                        if (mWork_info1.size() != 0) {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanbuList1.setAdapter(new TaskListAdapter(getActivity(), mWork_info1,0));
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无普通招标订单");
                        }
                    }else{
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //订金招标
                } else if (a == 4) {
                    if (mWork_info!=null){
                        for (TaskList.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getModel_name();
                            if (str.equals("订金招标")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        if (mWork_info1.size() != 0) {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.VISIBLE);
                            mQuanbuList1.setAdapter(new TaskListAdapter(getActivity(), mWork_info1,0));
                        } else {
                            mKProgressHUD.dismiss();
                            mQuanbuList1.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无订金招标订单");
                        }
                    }else{
                        mKProgressHUD.dismiss();
                        mQuanbuList1.setVisibility(View.GONE);
                        mText111.setText("暂无人投标");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                super.onError(call, response, e);
            }
        });
    }
}