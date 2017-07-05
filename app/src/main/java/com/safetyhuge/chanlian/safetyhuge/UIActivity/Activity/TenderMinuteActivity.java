package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.TenderMinuteBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.TenderminuteDjBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.StringUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/7/3 0003 10:05
 * 邮箱：995696826@qq.com
 */

public class TenderMinuteActivity extends HBaseAct {
    @Bind(R.id.tender_listview)
    ListView mTenderListview;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private String mUserid;
    private String mLayoutZt;
    private int mFlag;
    private int mFlagss;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_tenderminute);
        ButterKnife.bind(this);
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        Intent intent = getIntent();
        String layoutid = intent.getStringExtra("layoutid");
        HBaseAct.showKProgressHUD("加载中.....", this);
        mFlag = intent.getIntExtra("flag", -1);
        mFlagss = intent.getIntExtra("flagss", -1);
        netWork(layoutid);
    }

    private void netWork(String layoutid) {
        HashMap<String, String> hashMap = new HashMap<>();
        //mUserid
        hashMap.put("action", "GetDeliveryInfo");
        hashMap.put("uid", mUserid);
        hashMap.put("task_id", layoutid);
        final List<TenderMinuteBean.DataBean.WorkInfoBean> infoBeans = new ArrayList<>();
        final List<TenderminuteDjBean.DataBean.WorkInfoBean> infoBeans1 = new ArrayList<>();
        if (mFlag == 0) {
            OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<TenderMinuteBean>() {
                @Override
                public void onSuccess(TenderMinuteBean tenderMinute, Call call, Response response) {
                    HBaseAct.dismissProgressHUD();
                    TenderMinuteBean.DataBean data = tenderMinute.getData();
                    List<TenderMinuteBean.DataBean.WorkInfoBean> work_info = data.getWork_info();
                    for (TenderMinuteBean.DataBean.WorkInfoBean workInfoBean : work_info) {
                        if (mFlagss==1){
                            infoBeans.add(workInfoBean);
                        }else{
                            if (workInfoBean.getUid().equals(mUserid)) {
                                infoBeans.add(workInfoBean);
                                KLog.e("work_desc", workInfoBean.getWork_desc());
                            }
                        }
                    }
                    if (infoBeans.size() != 0 && !infoBeans.isEmpty()) {
                        mTenderListview.setAdapter(new TenderminuteAdapter(TenderMinuteActivity.this, null, infoBeans, mFlag));
                    } else {
                        mTenderListview.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
            });
        } else if (mFlag == 2||mFlag==1) {
            OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<TenderminuteDjBean>() {
                @Override
                public void onSuccess(TenderminuteDjBean tenderminuteDjBean, Call call, Response response) {
                    HBaseAct.dismissProgressHUD();
                    TenderminuteDjBean.DataBean data = tenderminuteDjBean.getData();
                    List<TenderminuteDjBean.DataBean.WorkInfoBean> work_info = data.getWork_info();
                    for (TenderminuteDjBean.DataBean.WorkInfoBean workInfoBean : work_info) {
                        if (mFlagss==1){
                            infoBeans1.add(workInfoBean);
                        }else{
                            if (workInfoBean.getUid().equals(mUserid)) {
                                infoBeans1.add(workInfoBean);
                            }
                        }
                    }
                    if (infoBeans1.size() != 0 && !infoBeans1.isEmpty()) {
                        mTenderListview.setAdapter(new TenderminuteAdapter(TenderMinuteActivity.this, infoBeans1, null, mFlag));
                    } else {
                        mTenderListview.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public void back_text_view(View view) {
        finish();
    }
}

class TenderminuteAdapter extends BaseAdapter {
    List<TenderMinuteBean.DataBean.WorkInfoBean> infoBeans;
    TenderMinuteActivity tenderMinuteActivity;
    int flag;
    List<TenderminuteDjBean.DataBean.WorkInfoBean> infoBeans1;
    private String mUpic;
    private String mUsername;
    private String mQuote;
    private String mCycle;
    private String mArea;
    private String mMessage;
    private String mBid_status;
    private String mBid_time;

    public TenderminuteAdapter(TenderMinuteActivity tenderMinuteActivity, List<TenderminuteDjBean.DataBean.WorkInfoBean> infoBeans1, List<TenderMinuteBean.DataBean.WorkInfoBean> infoBeans, int flag) {
        this.infoBeans = infoBeans;
        this.tenderMinuteActivity = tenderMinuteActivity;
        this.flag = flag;
        this.infoBeans1 = infoBeans1;
    }


    @Override
    public int getCount() {
        if (flag == 0) {
            return infoBeans.size();
        } else {
            return infoBeans1.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (flag == 0) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(tenderMinuteActivity, R.layout.item_tenderminute, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String upic = infoBeans.get(position).getUpic();
            if (upic != null && !upic.equals("")) {
                Picasso.with(tenderMinuteActivity).load(RequestAddress.IMAGE1 + upic).fit().into(viewHolder.mTmImg);
            }
            String username = infoBeans.get(position).getUsername();
            viewHolder.mTmName.setText("投标者:" + username);
            String work_desc = infoBeans.get(position).getWork_desc();
            viewHolder.mTmContent.setText(StringUtils.Transition(work_desc));
            String work_status = infoBeans.get(position).getWork_status();
            if (work_status != null && !work_status.equals("")) {
                Integer integer = Integer.valueOf(work_status);
                switch (integer) {
                    case 0:
                        viewHolder.mTmState.setText("");
                        break;
                    case 1:
                        viewHolder.mTmState.setText("一等奖");
                        break;
                    case 2:
                        viewHolder.mTmState.setText("二等奖");
                        break;
                    case 3:
                        viewHolder.mTmState.setText("三等奖");
                        break;
                    case 4:
                        viewHolder.mTmState.setText("中标");
                        break;
                    case 5:
                        viewHolder.mTmState.setText("入围");
                        break;
                    case 7:
                        viewHolder.mTmState.setText("淘汰");
                        break;
                }
            }
            String work_time = infoBeans.get(position).getWork_time();
            viewHolder.mTmTime.setText("投标时间:" + DateUtils.timesOne(work_time));
            viewHolder.mTmFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(tenderMinuteActivity, "没有附件", Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.mtm_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(tenderMinuteActivity, TenderMinuteSActivity.class);
                    intent1.putExtra("image", infoBeans.get(position).getUpic());
                    intent1.putExtra("username", infoBeans.get(position).getUsername());
                    intent1.putExtra("time", infoBeans.get(position).getWork_time());
                    intent1.putExtra("status", infoBeans.get(position).getWork_status());
                    intent1.putExtra("content", infoBeans.get(position).getWork_desc());
                    tenderMinuteActivity.startActivity(intent1);
                }
            });
        }
        if (flag == 1) {
            ViewHolderDj holderDj;
            if (convertView==null){
                convertView = View.inflate(tenderMinuteActivity, R.layout.item_tenderminutedj, null);
                holderDj = new ViewHolderDj(convertView);
                convertView.setTag(holderDj);
            }else{
                holderDj = (ViewHolderDj) convertView.getTag();
            }
            mUpic = infoBeans1.get(position).getUpic();
            if (mUpic !=null&&!mUpic.equals("")){
                Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1+ mUpic).fit().into(holderDj.mTmImg);
            }
            mUsername = infoBeans1.get(position).getUsername();
            holderDj.mTmName.setText(mUsername);
            mQuote = infoBeans1.get(position).getQuote();
            holderDj.mZhaobiaoJinqian.setText(mQuote);
            mCycle = infoBeans1.get(position).getCycle();
            holderDj.mZhaobiaoDay.setText(mCycle +"天");
            mArea = infoBeans1.get(position).getArea();
            holderDj.mZhaobiaoDiqu.setText(mArea);
            mMessage = infoBeans1.get(position).getMessage();
            holderDj.mZhaobiaoNeirong.setText(mMessage);
            mBid_status = infoBeans1.get(position).getBid_status();
            mBid_time = infoBeans1.get(position).getBid_time();
            holderDj.mZhaobiaoTime.setText(DateUtils.time(mBid_time));
            if (mBid_status !=null&&!mBid_status.equals("")){
                Integer integer = Integer.valueOf(mBid_status);
                switch (integer){
                    case 0:
                        holderDj.mdj_stauts.setText("");
                        break;
                    case 1:
                        holderDj.mdj_stauts.setText("一等奖");
                        break;
                    case 2:
                        holderDj.mdj_stauts.setText("二等奖");
                        break;
                    case 3:
                        holderDj.mdj_stauts.setText("三等奖");
                        break;
                    case 4:
                        holderDj.mdj_stauts.setText("中标");
                        break;
                    case 5:
                        holderDj.mdj_stauts.setText("入围");
                        break;
                    case 7:
                        holderDj.mdj_stauts.setText("淘汰");
                        break;
                }
            }
            holderDj.mYincangLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(tenderMinuteActivity, TenderMinutePtActivity.class);
                    intent.putExtra("mUpic",mUpic);
                    intent.putExtra("mQuote",mQuote);
                    intent.putExtra("mCycle",mCycle);
                    intent.putExtra("mArea",mArea);
                    intent.putExtra("mMessage",mMessage);
                    intent.putExtra("mBid_status",mBid_status);
                    intent.putExtra("mBid_time",mBid_time);
                    intent.putExtra("mUsername",mUsername);
                    tenderMinuteActivity.startActivity(intent);
                }
            });
        }
        if (flag == 2) {
            ViewHolderDj holderDj;
            if (convertView==null){
                convertView = View.inflate(tenderMinuteActivity, R.layout.item_tenderminutedj, null);
                holderDj = new ViewHolderDj(convertView);
                convertView.setTag(holderDj);
            }else{
                holderDj = (ViewHolderDj) convertView.getTag();
            }
            mUpic = infoBeans1.get(position).getUpic();
            if (mUpic !=null&&!mUpic.equals("")){
                Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1+ mUpic).fit().into(holderDj.mTmImg);
            }
            mUsername = infoBeans1.get(position).getUsername();
            holderDj.mTmName.setText(mUsername);
            mQuote = infoBeans1.get(position).getQuote();
            holderDj.mZhaobiaoJinqian.setText("¥"+mQuote);
            mCycle = infoBeans1.get(position).getCycle();
            holderDj.mZhaobiaoDay.setText(mCycle +"天");
            mArea = infoBeans1.get(position).getArea();
            holderDj.mZhaobiaoDiqu.setText(mArea);
            mMessage = infoBeans1.get(position).getMessage();
            holderDj.mZhaobiaoNeirong.setText(mMessage);
            mBid_status = infoBeans1.get(position).getBid_status();
            mBid_time = infoBeans1.get(position).getBid_time();
            holderDj.mZhaobiaoTime.setText(DateUtils.time(mBid_time));
            if (mBid_status !=null&&!mBid_status.equals("")){
                Integer integer = Integer.valueOf(mBid_status);
                switch (integer){
                    case 0:
                        holderDj.mdj_stauts.setText("");
                        break;
                    case 1:
                        holderDj.mdj_stauts.setText("一等奖");
                        break;
                    case 2:
                        holderDj.mdj_stauts.setText("二等奖");
                        break;
                    case 3:
                        holderDj.mdj_stauts.setText("三等奖");
                        break;
                    case 4:
                        holderDj.mdj_stauts.setText("中标");
                        break;
                    case 5:
                        holderDj.mdj_stauts.setText("入围");
                        break;
                    case 7:
                        holderDj.mdj_stauts.setText("淘汰");
                        break;
                }
            }
            holderDj.mYincangLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(tenderMinuteActivity, TenderMinuteDjActivity.class);
                    intent.putExtra("mtask_id",infoBeans1.get(position).getTask_id());
                    intent.putExtra("muid",infoBeans1.get(position).getUid());
                    intent.putExtra("mUpic",mUpic);
                    intent.putExtra("mUsername",mUsername);
                    intent.putExtra("mQuote",mQuote);
                    intent.putExtra("mCycle",mCycle);
                    intent.putExtra("mArea",mArea);
                    intent.putExtra("mMessage",mMessage);
                    intent.putExtra("mBid_status",mBid_status);
                    intent.putExtra("mBid_time",mBid_time);
                    intent.putParcelableArrayListExtra("planlist", (ArrayList<? extends Parcelable>) infoBeans1.get(position).getPlan());
                    tenderMinuteActivity.startActivity(intent);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tm_img)
        CircleImageView mTmImg;
        @Bind(R.id.tm_name)
        TextView mTmName;
        @Bind(R.id.tm_content)
        TextView mTmContent;
        @Bind(R.id.tm_state)
        TextView mTmState;
        @Bind(R.id.tm_time)
        TextView mTmTime;
        @Bind(R.id.tm_file)
        Button mTmFile;
        @Bind(R.id.tm_layout)
        RelativeLayout mtm_layout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolderDj {
        @Bind(R.id.tm_img)
        CircleImageView mTmImg;
        @Bind(R.id.tm_name)
        TextView mTmName;
        @Bind(R.id.tm_layout)
        RelativeLayout mTmLayout;
        @Bind(R.id.zhaobiao_jinqian)
        TextView mZhaobiaoJinqian;
        @Bind(R.id.zhaobiao_day)
        TextView mZhaobiaoDay;
        @Bind(R.id.zhaobiao_diqu)
        TextView mZhaobiaoDiqu;
        @Bind(R.id.zhaobiao_neirong)
        TextView mZhaobiaoNeirong;
        @Bind(R.id.zhaobiao_time)
        TextView mZhaobiaoTime;
        @Bind(R.id.dj_stauts)
        TextView mdj_stauts;
        @Bind(R.id.yincang_layout)
        LinearLayout mYincangLayout;

        ViewHolderDj(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
