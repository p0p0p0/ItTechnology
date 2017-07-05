package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.ShoucangBean;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 我的收藏
 * 作者：王海宾 on 2017/5/17 0017 20:43
 * 邮箱：995696826@qq.com
 */
public class ServiceCollectActivity extends HBaseAct {

    @Bind(R.id.serviceindent_list)
    ListView mServiceIndentList;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    private Context mContext;
    private KProgressHUD mKProgressHUD;
    private String mUserid;
    private servicecollectAdapter mAdapter;
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    private List<ShoucangBean.DataBean> mData;

    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicecollect_layout);
        Intent intent = getIntent();
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "");
        //用户名称
        String mUsername = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        mKProgressHUD = KProgressHUD.create(ServiceCollectActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        mContext = this;
        ButterKnife.bind(this);
        netWork();
        registerBoradcastReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
    }
    public void registerBoradcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.fuwu.shouchang");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                KLog.e("whb","我接收到了");
                String action = intent.getAction();
                int whb_flag = intent.getIntExtra("mPosition", -1);
                if (action.equals("com.fuwu.shouchang")) {
                    KLog.e("whb","我接收到了");
                    KLog.e("whb",mAdapter);
                    mData.remove(whb_flag);
                    mAdapter.notifyDataSetInvalidated();
                    if (mData.isEmpty()){
                        mServiceIndentList.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }


    private void netWork() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetCollectInfo");
        hashMap.put("uid", mUserid);
        hashMap.put("type", "1");
        hashMap.put("mid", "7");
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ShoucangBean>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(ShoucangBean shoucangBean, Call call, Response response) {
                mData = shoucangBean.getData();
                mKProgressHUD.dismiss();
                if (mData != null) {
                    if (!mData.isEmpty()) {
                        mServiceIndentList.setVisibility(View.VISIBLE);
                        mQuanbuImage.setVisibility(View.GONE);
                        mAdapter = new servicecollectAdapter(ServiceCollectActivity.this, mData,mUserid);
                        mServiceIndentList.setAdapter(mAdapter);
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

    public void back_text_view(View view) {
        exitAct();
    }

}

class servicecollectAdapter extends BaseAdapter {
    ServiceCollectActivity Activity;
    List<ShoucangBean.DataBean> data;
    public OnListener onListener;
    private KProgressHUD mProgressHUD;
    String userid;

    public servicecollectAdapter(ServiceCollectActivity Activity, List<ShoucangBean.DataBean> data, String userid) {
        this.Activity = Activity;
        this.data = data;
        this.userid = userid;
        mProgressHUD = KProgressHUD.create(Activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
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
    MaterialDialog mMaterialDialog;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.servicecollect_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mShoucangTitle.setText(data.get(position).getTitle());
        holder.mShoucangMoney.setText("¥" + data.get(position).getPrice() + "/次");
        String pic = data.get(position).getPic();
        if (pic != null && !pic.equals("")) {
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + pic).into(holder.mShoucangImage);
        } else {
            holder.mShoucangImage.setImageResource(R.drawable.img_fw_small);
        }
        holder.mShoucangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * action	DelFavorite
uid	1
sid	1
type	service

                * */
                mMaterialDialog = new MaterialDialog(Activity)
                        .setTitle("提示")
                        .setMessage("您确定要取消收藏吗?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mProgressHUD.show();
                                mMaterialDialog.dismiss();
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("action","DelFavorite");
                                hashMap.put("uid",userid);
                                hashMap.put("sid",data.get(position).getService_id());
                                hashMap.put("type","service");
                                OkGo.post(RequestAddress.HOST+RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        mProgressHUD.dismiss();
                                        Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                        String code = (String) mapForJson.get("code");
                                        String secess = (String) mapForJson.get("secess");
                                        if (code.equals("888")&&secess.equals("true")){
                                            data.remove(position);
                                            notifyDataSetChanged();
                                        }
                                    }
                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        Toast.makeText(Activity, "取消收藏失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
                mMaterialDialog.show();
            }
        });

        holder.mSouchangLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity, FuWuXiangQingActivity.class);
                intent.putExtra("uid",userid);
                //服务id
                intent.putExtra("sid", data.get(position).getService_id());
                //服务uid
                intent.putExtra("fwuid", data.get(position).getUid());
                //服务uid
                intent.putExtra("mposition", position);
                Activity.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.shoucang_image)
        ImageView mShoucangImage;
        @Bind(R.id.shoucang_title)
        TextView mShoucangTitle;
        @Bind(R.id.shoucang_money)
        TextView mShoucangMoney;
        @Bind(R.id.shoucang_button)
        Button mShoucangButton;
        @Bind(R.id.souchang_layout)
        LinearLayout mSouchangLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnListener {
        void onItemClick(String id);
    }

    public void setListener(OnListener listener) {
        this.onListener = listener;
    }

}
