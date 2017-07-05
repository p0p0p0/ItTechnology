package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.FwxiangxiBean;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean.PingJiaBean1;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
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
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/5/19 0019 16:00
 * 邮箱：995696826@qq.com
 */

public class FwXiangxiActivity extends HBaseAct {
    KProgressHUD mKProgressHUD;
    Context mContext;
    @Bind(R.id.technology_button1)
    Button mTechnologyButton1;
    @Bind(R.id.view_1)
    View mView1;
    @Bind(R.id.technology_button2)
    Button mTechnologyButton2;
    @Bind(R.id.view_2)
    View mView2;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.fwxiangxi_image)
    ImageView mFwxiangxiImage;
    @Bind(R.id.fwxiangxi_title)
    TextView mFwxiangxiTitle;
    @Bind(R.id.fwxiangxi_money)
    TextView mFwxiangxiMoney;
    @Bind(R.id.fwxiangxi_image1)
    CircleImageView mFwxiangxiImage1;
    @Bind(R.id.fwxiangxi_name)
    TextView mFwxiangxiName;
    @Bind(R.id.serviceindentminute_button)
    Button mServiceindentminuteButton;
    @Bind(R.id.serviceindent)
    TextView mServiceindent;
    @Bind(R.id.list_view)
    MyListView mListView;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.so)
    ScrollView mSo;
    private int count = 0;
    private int count2 = 0;
    private int mFlag;
    private String mId;
    MaterialDialog mMaterialDialog;
    private Map<String, Object> mMapForJson;
    private Object mObject;
    private List<FwxiangxiBean.DataBean> mData;
    private String mUserimage;
    boolean b = true;
    private List<PingJiaBean1.DataBean> mData1;

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fwxiangxi_layout);
        //用户id
        String mUserid = (String) get(ECApplication.context, "UserUid", "");
        //用户名称
        String mUsername = (String) SharedPrefsUtil.get(ECApplication.context, "name", "");
        mContext = this;
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mFlag = intent.getIntExtra("flag", -1);
        mId = intent.getStringExtra("id");
        mUserimage = intent.getStringExtra("userimage");
        mSo.setVisibility(View.INVISIBLE);
        netWork();
        InitView();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }

    private void netWork() {
        OkGo.post(RequestAddress.HOST + RequestAddress.FWXX).params("service_id", mId).execute(new JsonCallback<FwxiangxiBean>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(FwxiangxiBean fwxiangxiBean, Call call, Response response) {
                mData = fwxiangxiBean.getData();
                mKProgressHUD.dismiss();
                mSo.setVisibility(View.VISIBLE);
                String pic = mData.get(0).getPic();
                String title = mData.get(0).getTitle();
                String price = mData.get(0).getPrice();
                String username = mData.get(0).getUsername();
                if (!mUserimage.equals("")) {
                    Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + mUserimage).into(mFwxiangxiImage1);
                }
                Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + pic).fit().into(mFwxiangxiImage);
                mFwxiangxiTitle.setText(title);
                mFwxiangxiMoney.setText("¥" + price);
                mFwxiangxiName.setText(username);
                mServiceindent.setText(mData.get(0).getContent());
            }
        });
    }

    private void InitView() {
        switch (mFlag) {
            case 0:
                mServiceindentminuteButton.setText("暂停服务");
                break;
            case 1:
                mServiceindentminuteButton.setText("恢复服务");
                break;
        }
        mServiceindentminuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog = new MaterialDialog(FwXiangxiActivity.this)
                        .setTitle("提示")
                        .setMessage("你确定要暂停服务吗?暂停期间,您的服务不会被展示,也无法再被预约.但不会影响已经接单的服务")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mKProgressHUD.show();
                                mMaterialDialog.dismiss();
                                if (mServiceindentminuteButton.getText().toString().equals("暂停服务")) {
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("service_id", mId);
                                    hashMap.put("fw_status", "1");
                                    OkGo.post(RequestAddress.HOST + "fuwuzt.php").params(hashMap).execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            KLog.json(s);
                                            mMapForJson = JSONUtil.getMapForJson(s);
                                            mObject = mMapForJson.get("code");
                                            mKProgressHUD.dismiss();
                                            if (mObject.toString().equals("200")) {
                                                mServiceindentminuteButton.setText("恢复服务");
                                            }
                                        }

                                        @Override
                                        public void onError(Call call, Response response, Exception e) {
                                            super.onError(call, response, e);
                                            mKProgressHUD.dismiss();
                                        }
                                    });
                                } else {
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("service_id", mId);
                                    hashMap.put("fw_status", "0");
                                    OkGo.post(RequestAddress.HOST + "fuwuzt.php").params(hashMap).execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            KLog.json(s);
                                            mMapForJson = JSONUtil.getMapForJson(s);
                                            mObject = mMapForJson.get("code");
                                            mKProgressHUD.dismiss();
                                            if (mObject.toString().equals("200")) {
                                                mServiceindentminuteButton.setText("暂停服务");
                                            }
                                        }

                                        @Override
                                        public void onError(Call call, Response response, Exception e) {
                                            super.onError(call, response, e);
                                            mKProgressHUD.dismiss();
                                        }
                                    });
                                }
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
    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.technology_button1, R.id.technology_button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.technology_button1:
                mView2.setVisibility(View.INVISIBLE);
                mView1.setVisibility(View.VISIBLE);
                if (count == 0) {
                    mServiceindent.setVisibility(View.VISIBLE);
                    mListView.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.GONE);
                }
                count++;
                count2 = 0;
                break;
            case R.id.technology_button2:
                mView1.setVisibility(View.INVISIBLE);
                mView2.setVisibility(View.VISIBLE);
                if (count2 == 0) {
                  /*  if (b==true){
                        mKProgressHUD.show();
                    }*/
                //    if (mData1==null){
                        mKProgressHUD.show();
                        mServiceindent.setVisibility(View.GONE);
                        mListView.setVisibility(View.VISIBLE);
                        NewWork1(mId);
                  /*  }else{
                        mServiceindent.setVisibility(View.GONE);
                        mListView.setVisibility(View.VISIBLE);
                    }*/
                }
                count2++;
                count = 0;
                break;
        }
    }

    private void NewWork1(String id) {
        OkGo.post(RequestAddress.HOST + "pinglunsp.php").params("service_id", id).execute(new JsonCallback<PingJiaBean1>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(PingJiaBean1 pingJiaBean1, Call call, Response response) {
                mData1 = pingJiaBean1.getData();
                mKProgressHUD.dismiss();
                if (mData1 != null) {
                    if (!mData1.isEmpty()) {
                        mListView.setVisibility(View.VISIBLE);
                        mQuanbuImage.setVisibility(View.GONE);
                        mListView.setAdapter(new FwXiangXiPj(mData1));
                         b  =false;
                    } else {
                        mListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                } else {
                    mListView.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void back_text_view(View view) {
        exitAct();
    }

    private void FragmentInfo(Fragment fragment, int a, List<Fragment> fragments) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.serviceindent, fragment);
        transaction.commit();
        if (a == 0) {
            KLog.e("size", fragments.size());
            for (Fragment fragment1 : fragments) {
                if (fragment1 != null) {
                    transaction.remove(fragment1);
                    KLog.e("fragments", fragment1.toString());
                }
            }
        }
    }

}


class FwXiangXiPj extends BaseAdapter {
    List<PingJiaBean1.DataBean> data;

    public FwXiangXiPj(List<PingJiaBean1.DataBean> data) {
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
            convertView = View.inflate(ECApplication.context, R.layout.item_fwpj, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String user_pic = data.get(position).getUser_pic();
        KLog.e("data.get(position).getUser_pic()", user_pic);
        if (user_pic!=null&&!user_pic.equals("")){
            Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + user_pic.replace("../","")).fit().into(viewHolder.mFwImage);
        }else{
            viewHolder.mFwImage.setImageResource(R.drawable.icon_small_tx);
        }
        viewHolder.mFwName.setText(data.get(position).getBy_username());
        viewHolder.mFwTime.setText(DateUtils.timesTwo(data.get(position).getMark_time()));
        String mark_status = data.get(position).getMark_status();
        int b = Integer.valueOf(mark_status);
        switch (b) {
            case 1:
                viewHolder.mFwHua.setImageResource(R.drawable.icon_haoping);
                break;
            case 2:
                viewHolder.mFwHua.setImageResource(R.drawable.icon_zhongping);
                break;
            case 3:
                viewHolder.mFwHua.setImageResource(R.drawable.icon_chaping);
                break;
        }
      /*  if (){

        }*/
      KLog.e("whb",data.get(position).getMark_content());
      if (data.get(position).getMark_content()!=null){
          viewHolder.mFwContent.setText(data.get(position).getMark_content());
      }else{
          viewHolder.mFwContent.setText("该用户暂未填写评价!");
      }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.Fw_image)
        CircleImageView mFwImage;
        @Bind(R.id.Fw_name)
        TextView mFwName;
        @Bind(R.id.Fw_time)
        TextView mFwTime;
        @Bind(R.id.Fw_hua)
        ImageView mFwHua;
        @Bind(R.id.Fw_content)
        TextView mFwContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
