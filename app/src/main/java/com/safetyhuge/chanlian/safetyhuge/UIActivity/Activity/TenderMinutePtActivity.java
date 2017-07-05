package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.StringUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/7/3 0003 11:19
 * 邮箱：995696826@qq.com
 */

public class TenderMinutePtActivity extends HBaseAct {

    @Bind(R.id.tm_img)
    CircleImageView mTmImg;
    @Bind(R.id.tm_name)
    TextView mTmName;
    @Bind(R.id.dj_stauts)
    TextView mDjStauts;
    @Bind(R.id.zhaobiao_jinqian)
    TextView mZhaobiaoJinqian;
    @Bind(R.id.zhaobiao_day)
    TextView mZhaobiaoDay;
    @Bind(R.id.zhaobiao_diqu)
    TextView mZhaobiaoDiqu;
    @Bind(R.id.zhaobiao_neirong)
    TextView mZhaobiaoNeirong;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_tenderminutept);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        Intent intent = getIntent();
        String mUpic = intent.getStringExtra("mUpic");
        String mUsername = intent.getStringExtra("mUsername");
        String mQuote = intent.getStringExtra("mQuote");
        String mCycle = intent.getStringExtra("mCycle");
        String mArea = intent.getStringExtra("mArea");
        String mMessage = intent.getStringExtra("mMessage");
        String mBid_status = intent.getStringExtra("mBid_status");
        String mBid_time = intent.getStringExtra("mBid_time");
        if (mUpic!=null&&!mUpic.equals("")){
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1+mUpic).fit().into(mTmImg);
        }
        mTmName.setText(mUsername);
        mZhaobiaoJinqian.setText(mQuote);
        mZhaobiaoDay.setText(mCycle+"天");
        mZhaobiaoDiqu.setText(mArea);
        mZhaobiaoNeirong.setText(StringUtils.Transition(mMessage));
    }
    public void back_text_view(View view) {
        exitAct();
    }
}
