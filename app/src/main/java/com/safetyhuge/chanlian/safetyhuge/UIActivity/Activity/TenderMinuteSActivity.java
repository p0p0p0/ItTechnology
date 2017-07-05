package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.StringUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/7/3 0003 11:19
 * 邮箱：995696826@qq.com
 */

public class TenderMinuteSActivity extends HBaseAct {
    @Bind(R.id.tm_img)
    CircleImageView mTmImg;
    @Bind(R.id.tm_name)
    TextView mTmName;
    @Bind(R.id.tm_content)
    TextView mTmContent;
    @Bind(R.id.tm_state)
    TextView mTmState;
    @Bind(R.id.tender_content)
    TextView mTenderContent;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_tenderminutexx);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String username = intent.getStringExtra("username");
        String time = intent.getStringExtra("time");
        String status = intent.getStringExtra("status");
        String content = intent.getStringExtra("content");
        if (image != null && !image.equals("")) {
            Picasso.with(this).load(RequestAddress.IMAGE1 + image).fit().into(mTmImg);
        }
        mTmName.setText("投标者:" + username);
        mTmContent.setText("投标时间:" + DateUtils.timesOne(time));
        Integer integer = Integer.valueOf(status);
        if (integer != null && !integer.equals("")) {
            switch (integer) {
                case 0:
                    mTmState.setText("");
                    break;
                case 1:
                    mTmState.setText("一等奖");
                    break;
                case 2:
                    mTmState.setText("二等奖");
                    break;
                case 3:
                    mTmState.setText("三等奖");
                    break;
                case 4:
                    mTmState.setText("中标");
                    break;
                case 5:
                    mTmState.setText("入围");
                    break;
                case 7:
                    mTmState.setText("淘汰");
                    break;
            }
        }
        mTenderContent.setText(StringUtils.Transition(content));
    }
    public void back_text_view(View view) {
        exitAct();
    }
}
