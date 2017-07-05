package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.safetyhuge.Bean.MyBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.PersonageBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ArrdessActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceCollectActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceEvaluateActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceHelpActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceIndentActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.ServiceIssueActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/16 0016 17:13
 * 邮箱：995696826@qq.com
 */

public class MinesFragment extends Fragment {
    @Bind(R.id.tx_img)
    CircleImageView mTxImg;
    @Bind(R.id.layout_grzx_fuwu)
    LinearLayout mLayoutGrzxFuwu;
    @Bind(R.id.layout_grzx_qiuzhu)
    LinearLayout mLayoutGrzxQiuzhu;
    @Bind(R.id.layout_grzx_fabu)
    LinearLayout mLayoutGrzxFabu;
    @Bind(R.id.layout_grzx_shoucang)
    LinearLayout mLayoutGrzxShoucang;
    @Bind(R.id.layout_grzx_dizhi)
    LinearLayout mLayoutGrzxDizhi;
    @Bind(R.id.layout_grzx_pingjia)
    LinearLayout mLayoutGrzxPingjia;
    @Bind(R.id.user_name)
    TextView mUserName;
    private String mUserid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_relesase_gerenzx, container, false);
        ButterKnife.bind(this, view);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        NetWork(mUserid);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.layout_grzx_fuwu, R.id.layout_grzx_qiuzhu, R.id.layout_grzx_fabu, R.id.layout_grzx_shoucang, R.id.layout_grzx_dizhi, R.id.layout_grzx_pingjia})
    public void onClick(View view) {
        switch (view.getId()) {
            //服务订单
            case R.id.layout_grzx_fuwu:
                startActivity(new Intent(getActivity(), ServiceIndentActivity.class));
                break;
            //求助订单
            case R.id.layout_grzx_qiuzhu:
                startActivity(new Intent(getActivity(), ServiceHelpActivity.class));
                break;
            //我发布的
            case R.id.layout_grzx_fabu:
                startActivity(new Intent(getActivity(), ServiceIssueActivity.class));
                break;
            //我的收藏
            case R.id.layout_grzx_shoucang:
                startActivity(new Intent(getActivity(), ServiceCollectActivity.class));
                break;
            //地址
            case R.id.layout_grzx_dizhi:
                Intent intent = new Intent(getActivity(), ArrdessActivity.class);
                intent.putExtra("flag","0");
                startActivity(intent);//跳转到个人设置
                break;
            //我评价的
            case R.id.layout_grzx_pingjia:
                startActivity(new Intent(getActivity(), ServiceEvaluateActivity.class));
                break;
        }
    }

    public void NetWork(String userid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetLoginUserInfo");
        hashMap.put("uid", userid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GRZL).params(hashMap).execute(new JsonCallback<MyBean>() {
            @Override
            public void onSuccess(MyBean myBean, Call call, Response response) {
                MyBean.DataBean data = myBean.getData();
                PersonageBean mPersonageBean = new PersonageBean();
                String name = data.getUsername();
                String uid = data.getUid();
                /*if (name != "") {
                    if (chengxin != null) {
                        chengxin.setVisibility(View.VISIBLE);
                    }
                }*/
                String email = data.getEmail();
                Object qq1 = data.getQq();
                Object phone = data.getMobile();
                Object address = data.getAddress();
                Object pic = data.getShow_pic();
                Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + data.getShow_pic()).fit().into(mTxImg);
                mUserName.setText(name);
                KLog.e("name", name);
                KLog.e("email", email);
                KLog.e("qq1", qq1);
                KLog.e("phone", phone);
                KLog.e("address", address);
                KLog.e("pic", pic);
                KLog.e(mPersonageBean.toString()
                );

            }
        });
    }
}

