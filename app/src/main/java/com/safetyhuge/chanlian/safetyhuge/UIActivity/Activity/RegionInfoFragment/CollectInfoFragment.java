package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.RegionInfoFragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.ChargeBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.TechnologyminuteActivity;
import com.safetyhuge.chanlian.safetyhuge.adapter.TechnologyTrainSAdapter;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**收费资源
 * 作者：王海宾 on 2017/6/20 0020 10:36
 * 邮箱：995696826@qq.com
 */

public class CollectInfoFragment extends Fragment{
    @Bind(R.id.carge_listview)
    ListView mCargeListview;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private KProgressHUD mKProgressHUD;
    private Activity mContext;
    private String mUserid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charge, null);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        ButterKnife.bind(this, view);
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mContext = getActivity();
        mCargeListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KLog.e("whb", position + "");
                Intent intent = new Intent(getActivity(), TechnologyminuteActivity.class);
                intent.putExtra("uid", mDataInfo.get(position).getService_id());
                intent.putExtra("flag", "0");
                getActivity().startActivity(intent);
            }
        });
        NetWork1(1);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private List<ChargeBean.DataBean> mData;
    private List<ChargeBean.DataBean> mDataInfo;

    public void NetWork1(int count) {
       /* action=GetGoodsInfo
        mid=14
        uid=
                page*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetGoodsInfo");
        hashMap.put("mid", "14");
        hashMap.put("uid", "");
        hashMap.put("page", count + "");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ChargeBean>() {
            @Override
            public void onSuccess(ChargeBean chargeBean, Call call, Response response) {
                mData = chargeBean.getData();
                mKProgressHUD.dismiss();
                if (!mData.isEmpty()) {
                    mDataInfo = new ArrayList<>();
                    for (ChargeBean.DataBean datum : mData) {
                        if (!datum.getPrice().equals("0.00")) {
                            mDataInfo.add(datum);
                        }
                    }
                    if (mDataInfo.size() == 0) {
                        mQuanbuImage.setVisibility(View.VISIBLE);
                        mCargeListview.setVisibility(View.GONE);
                    } else {
                        mQuanbuImage.setVisibility(View.GONE);
                        mCargeListview.setVisibility(View.VISIBLE);
                        mCargeListview.setAdapter(new TechnologyTrainSAdapter(mContext, mDataInfo,1));
                    }
                } else {
                    mQuanbuImage.setVisibility(View.VISIBLE);
                    mCargeListview.setVisibility(View.GONE);
                }
            }
        });
    }
}
