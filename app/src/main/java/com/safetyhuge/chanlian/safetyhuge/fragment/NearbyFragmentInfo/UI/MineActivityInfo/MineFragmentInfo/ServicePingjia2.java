package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.baseview.StarBarView;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.PIngjiaBean;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/18 0018 22:18
 * 邮箱：995696826@qq.com
 */

public class ServicePingjia2 extends Fragment {
    @Bind(R.id.serviceindent_list)
    ListView mServiceIndentList;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Context mContext;
    private KProgressHUD mKProgressHUD;
    private String mUserid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serviceindenta, null);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        ButterKnife.bind(this, view);
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mContext = getActivity();
        NetWork();
        return view;
    }

    private void NetWork() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "ByDoMarkByService");
        hashMap.put("uid", mUserid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<PIngjiaBean>() {
            private List<PIngjiaBean.DataBean> mData;

            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(PIngjiaBean pIngjiaBean, Call call, Response response) {
                mData = pIngjiaBean.getData();
                mKProgressHUD.dismiss();
                if (mData != null) {
                    if (!mData.isEmpty()) {
                        mServiceIndentList.setVisibility(View.VISIBLE);
                        mQuanbuImage.setVisibility(View.GONE);
                        mServiceIndentList.setAdapter(new ServicePingjiaAdapter2(mData));
                    } else {
                        mServiceIndentList.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                } else {
                    mServiceIndentList.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}

class ServicePingjiaAdapter2 extends BaseAdapter {
    List<PIngjiaBean.DataBean> data;

    public ServicePingjiaAdapter2(List<PIngjiaBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_fuwu_shoucang, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String by_marker_pic = data.get(position).getMarker_pic();
        if (by_marker_pic != null && !by_marker_pic.equals("")) {
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + by_marker_pic).into(viewHolder.mPingjiaImage);
        } else {
            viewHolder.mPingjiaImage.setImageResource(R.drawable.icon_small_tx);
        }
        viewHolder.mPingjiaTitle.setText(data.get(position).getBy_username());
        String mark_status = data.get(position).getMark_status();
        if (mark_status != null && !mark_status.equals("")) {
            if (mark_status.equals("1")) {
                viewHolder.mPingjiaImage1.setImageResource(R.drawable.icon_haoping);
            } else if (mark_status.equals("2")) {
                viewHolder.mPingjiaImage1.setImageResource(R.drawable.icon_zhongping);
            } else if (mark_status.equals("3")) {
                viewHolder.mPingjiaImage1.setImageResource(R.drawable.icon_chaping);
            }
        }
        String mark_time = data.get(position).getMark_time();
        viewHolder.mPingjiaTime.setText(DateUtils.timesOne(mark_time));
        String mark_content = data.get(position).getMark_content();
        if (mark_content != null && !mark_content.equals("")) {
            viewHolder.mPingjiaContent.setText(mark_content);
        } else {
            viewHolder.mPingjiaContent.setText("该用户未填写评价");
        }
        String aid_star = data.get(position).getAid_star();
        if (aid_star.length()==11){
            String[] strings = aid_star.split(",");
            viewHolder.mPingjiaInfo.setVisibility(View.VISIBLE);
            viewHolder.mPingjiaInfo1.setVisibility(View.GONE);
            float a0 = Float.parseFloat(strings[0]);
            viewHolder.mSbvStarbar1.setStarRating(a0);
            float a1 = Float.parseFloat(strings[1]);
            viewHolder.mSbvStarbar2.setStarRating(a1);
            float a2 = Float.parseFloat(strings[2]);
            viewHolder.mSbvStarbar3.setStarRating(a2);
        }else{
            String[] strings = aid_star.split(",");
            float a0 = Float.parseFloat(strings[0]);
            viewHolder.mSbvFukuan1.setStarRating(a0);
            float a1 = Float.parseFloat(strings[1]);
            viewHolder.mSbvHezuo2.setStarRating(a1);
            viewHolder.mPingjiaInfo1.setVisibility(View.VISIBLE);
            viewHolder.mPingjiaInfo.setVisibility(View.GONE);
        }
        KLog.e("aid_star", aid_star.length());
        //viewHolder.mSbvStarbar1.setStarMaxNumber();
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.pingjia_image)
        CircleImageView mPingjiaImage;
        @Bind(R.id.pingjia_title)
        TextView mPingjiaTitle;
        @Bind(R.id.pingjia_image1)
        ImageView mPingjiaImage1;
        @Bind(R.id.pingjia_time)
        TextView mPingjiaTime;
        @Bind(R.id.item_pingjia_layout)
        RelativeLayout mItemPingjiaLayout;
        @Bind(R.id.pingjia_content)
        TextView mPingjiaContent;
        @Bind(R.id.shouchang_sudu)
        TextView mShouchangSudu;
        @Bind(R.id.sbv_starbar1)
        StarBarView mSbvStarbar1;
        @Bind(R.id.shouchang_zhiliang)
        TextView mShouchangZhiliang;
        @Bind(R.id.sbv_starbar2)
        StarBarView mSbvStarbar2;
        @Bind(R.id.shouchang_taidu)
        TextView mShouchangTaidu;
        @Bind(R.id.sbv_starbar3)
        StarBarView mSbvStarbar3;
        @Bind(R.id.pingjia_info)
        LinearLayout mPingjiaInfo;
        @Bind(R.id.shouchang_fukuan)
        TextView mShouchangFukuan;
        @Bind(R.id.sbv_fukuan1)
        StarBarView mSbvFukuan1;
        @Bind(R.id.shouchang_hezuo)
        TextView mShouchangHezuo;
        @Bind(R.id.sbv_hezuo2)
        StarBarView mSbvHezuo2;
        @Bind(R.id.pingjia_info1)
        LinearLayout mPingjiaInfo1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
