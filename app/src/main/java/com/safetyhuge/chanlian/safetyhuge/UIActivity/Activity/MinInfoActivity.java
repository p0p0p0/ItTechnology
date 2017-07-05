package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.MsgBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/23 0023 19:24
 * 邮箱：995696826@qq.com
 */

public class MinInfoActivity extends HBaseAct {
    @Bind(R.id.misg_listview)
    MyListView mMisgListview;
    KProgressHUD mKProgressHUD;
    Context mContext;
    private String mId;
    private List<MsgBean.DataBean> mData;

    @Override
    protected void onCreate(Bundle arg0) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(arg0);
        setContentView(R.layout.activity_mimsginfo);
        ButterKnife.bind(this);
        mContext = this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        NetWork(mId);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
    public void NetWork(String mUserid) {
        OkGo.post(RequestAddress.HOST + "xiaoxi.php").params("to_uid", mUserid).execute(new JsonCallback<MsgBean>() {
            @Override
            public void onSuccess(MsgBean msgBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mData = msgBean.getData();
                mMisgListview.setAdapter(new MinAdapter(mData));
            }
        });
    }
}

class MinAdapter extends BaseAdapter {
    List<MsgBean.DataBean> data;

    public MinAdapter(List<MsgBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_msg, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mMsgTime.setText(DateUtils.timet(data.get(position).getOn_time()));
        viewHolder.mMsgContent.setText(data.get(position).getContent());
        viewHolder.mMsgTitle.setText(data.get(position).getTitle());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.msg_time)
        TextView mMsgTime;
        @Bind(R.id.msg_title)
        TextView mMsgTitle;
        @Bind(R.id.msg_content)
        TextView mMsgContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
