package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsFragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.EvaluateAdapter;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 商品评价
 * 作者：王海宾 on 2017/5/3 0003 14:51
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class GoodsEvaluateFragment extends Fragment {
    @Bind(R.id.list_pingjia)
    MyListView mListPingjia;
    KProgressHUD mKProgressHUD;
    String goodsid;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.item2_scroll)
    ScrollView mItem2Scroll;
    @Bind(R.id.text111)
    TextView mText111;
    private GoodsBean.DataBean mData;
    private String mUserid;
    List<GoodsBean.DataBean.MarkInfoBean> mark_info;

    public GoodsEvaluateFragment(String goodsid, List<GoodsBean.DataBean.MarkInfoBean> mark_info) {
        this.goodsid = goodsid;
        this.mark_info = mark_info;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_item2, container, false);
        ButterKnife.bind(this, view);
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        InitView();
        return view;
    }

    @SuppressLint("WrongConstant")
    private void InitView() {
        mKProgressHUD.dismiss();
        if (!mark_info.isEmpty()) {
            mListPingjia.setAdapter(new EvaluateAdapter(mark_info));
            KLog.e("mark_info", mark_info.size());
        } else {
            //没有数据
            mItem2Scroll.setVisibility(View.GONE);
            mQuanbuImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
