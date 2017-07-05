package com.safetyhuge.chanlian.safetyhuge.UIActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beiing.roundimage.CircleImageView;
import com.example.zhouwei.library.CustomPopWindow;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.QiuzhuBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.ServiceBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskList;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.FuWuXiangQingActivity;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.QZXiangXiActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.safetyhuge.chanlian.safetyhuge.views.SwipeRefreshView;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.ServeFragment.jingdu;
import static com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.ServeFragment.weidu;
import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/6/5 0005 14:58
 * 邮箱：995696826@qq.com
 */

public class FuJInSSActivity extends HBaseAct {
    @Bind(R.id.blank_sousuo_item)
    TextView mBlankSousuoItem;
    @Bind(R.id.blank_sousuo_et)
    EditText mBlankSousuoEt;
    @Bind(R.id.fuwu_soushuo)
    LinearLayout mFuwuSoushuo;
    @Bind(R.id.sousuo_listView)
    ListView mSousuoListView;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.sopping_swlayout)
    SwipeRefreshView mSoppingSwlayout;
    @Bind(R.id.sousuo_button)
    Button mSousuoButton;
    private CustomPopWindow mCustomPopWindow;
    private KProgressHUD mKProgressHUD;
    private List<TaskList.DataBean> mTaskListData;
    int count = 1;
    private String mS, mUserid;
    private List<ServiceBean.DataBean> mData;
    private List<QiuzhuBean.DataBean> mDataBeans;


    @Override
    protected void onCreate(Bundle arg0) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(arg0);
        setContentView(R.layout.activity_fuwu_search);
        ButterKnife.bind(this);
        mBlankSousuoEt.requestFocus();
        mKProgressHUD = KProgressHUD.create(FuJInSSActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
        //用户id
        mUserid = (String) get(ECApplication.context, "UserUid", "1");
      /*  TaskList();
        NetWork(mUserid, "1", "6", "", "", "", "", "", "", "", "");*/
        //  initView();
        // RequestAddress.HOST
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }

    public void NetWork1(final String anInt) {
        HashMap<String, String> hashMap = new HashMap<>();
        mS = mBlankSousuoItem.getText().toString();
        if (mS.equals("项目  ")) {
            hashMap.put("action", "GetServiceInfo");
        } else if (mS.equals("求助  ")) {
            hashMap.put("action", "GetSeekHelpInfo");
        }
        //目标经度
        hashMap.put("lng", jingdu);
        hashMap.put("lat", weidu);
        hashMap.put("uid", "0");
        //distance
        hashMap.put("distance", "10");
        hashMap.put("keyword", anInt);
        KLog.e("whb", hashMap.toString());
        if (mS.equals("项目  ")) {
            OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ServiceBean>() {
                @Override
                public void onSuccess(ServiceBean dataBean, Call call, Response response) {
                    mKProgressHUD.dismiss();
                    mData = dataBean.getData();
                    if (!mData.isEmpty()) {
                        mKProgressHUD.dismiss();
                        mSousuoListView.setVisibility(View.VISIBLE);
                        mQuanbuImage.setVisibility(View.GONE);
                        if (mS.equals("项目  ")) {
                            mSousuoListView.setAdapter(new SouSuoAdapter(mData, FuJInSSActivity.this, 0));
                        }
                    } else {
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
            });
        } else {
            OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<QiuzhuBean>() {
                @Override
                public void onSuccess(QiuzhuBean dataBean, Call call, Response response) {
                    mKProgressHUD.dismiss();
                    mDataBeans = dataBean.getData();
                    if (!mDataBeans.isEmpty()) {
                        mKProgressHUD.dismiss();
                        mSousuoListView.setVisibility(View.VISIBLE);
                        mQuanbuImage.setVisibility(View.GONE);
                        mSousuoListView.setAdapter(new SouSuoAdapter(mDataBeans, FuJInSSActivity.this,1));
                    } else {
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


    }


    public void initView() {
        //下拉刷新
        mSoppingSwlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mSoppingSwlayout.setRefreshing(false);
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                    }
                }, 1200);
            }
        });
        mSoppingSwlayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                count++;
                mS = mBlankSousuoItem.getText().toString();
                String trim = mBlankSousuoEt.getText().toString().trim();
                if (mS.equals("项目  ")) {
                    NetWork1(trim);
                } else if (mS.equals("求助  ")) {
                    NetWork1(trim);
                }
                KLog.e("whbxount", count + "");

            }
        });
    }

    private void showPopMenu() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu_item_fujin, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(mFuwuSoushuo, 0, 0);
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.menu1:
                        mBlankSousuoItem.setText("项目  ");
                        mBlankSousuoEt.setText("");
                        mBlankSousuoEt.setHint("请输入服务关键字");
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.GONE);
                        break;
                    case R.id.menu2:
                        mBlankSousuoItem.setText("求助  ");
                        mBlankSousuoEt.setText("");
                        mBlankSousuoEt.setHint("请输入求助关键字");
                        mSousuoListView.setVisibility(View.GONE);
                        mQuanbuImage.setVisibility(View.GONE);
                        break;
                }
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
    }


    @OnClick({R.id.sousuo_button, R.id.blank_sousuo_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blank_sousuo_item:
                showPopMenu();
                break;
            case R.id.sousuo_button:
                mKProgressHUD.show();
                String s = mBlankSousuoItem.getText().toString();
                String trim = mBlankSousuoEt.getText().toString().trim();
                if (s.equals("项目  ")) {
                    NetWork1(trim);
                } else if (s.equals("求助  ")) {
                    NetWork1(trim);
                }
                break;
        }
    }

    public void back_text_view(View view) {
        finish();
    }

}

class SouSuoAdapter extends BaseAdapter {
    List<ServiceBean.DataBean> mDataBean;
    Context context;
    int i;
    List<QiuzhuBean.DataBean> bean;
    String status = "";

    public SouSuoAdapter(List<ServiceBean.DataBean> mDataBean, Context context, int i) {
        this.mDataBean = mDataBean;
        this.context = context;
        this.i = i;
    }

    public SouSuoAdapter(List<QiuzhuBean.DataBean> bean, FuJInSSActivity context, int i) {
        this.bean = bean;
        this.context = context;
        this.i = i;
    }


    @Override
    public int getCount() {
        KLog.e("mDataBean", mDataBean.size());
        return mDataBean.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_sousuo_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String help_status = bean.get(position).getHelp_status();
        if (help_status != null && !help_status.equals("")) {
            if (help_status.equals("6")) {
                status = "已完成";
                //   holder.mDataBean.setText("已完成");
            } else if (help_status.equals("5")) {
                status = "处理中";
                // holder.mZhuangtai.setText("处理中");
            }
        }
        if (i == 0){
            holder.mSousuoTitle.setText(mDataBean.get(position).getTitle());
            KLog.e("whb", mDataBean.get(position).getTitle());
            holder.mSousuoContent.setText(mDataBean.get(position).getContent());
            holder.mSousuoMoney.setText(mDataBean.get(position).getPrice());
            Picasso.with(context).load(RequestAddress.IMAGE1 + mDataBean.get(position).getUser_pic()).fit().into(holder.mSousuoImage);
        }else{
            holder.mSousuoTitle.setText(bean.get(position).getTitle());
            KLog.e("whb", bean.get(position).getTitle());
            holder.mSousuoContent.setText(bean.get(position).getContent());
            holder.mSousuoMoney.setText(bean.get(position).getPrice());
            Picasso.with(context).load(RequestAddress.IMAGE1 + bean.get(position).getUser_pic()).fit().into(holder.mSousuoImage);
        }

        holder.mSousuoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    Intent intent = new Intent(context, FuWuXiangQingActivity.class);
                    //服务id
                    intent.putExtra("sid", mDataBean.get(position).getService_id());
                    //图片
                    intent.putExtra("fwuid", mDataBean.get(position).getUid());
                    context.startActivity(intent);
                }
                if (i == 1) {
                    Intent intent = new Intent(context, QZXiangXiActivity.class);
                    String pic = bean.get(position).getPic();
                    if (pic != null && !pic.equals("")) {
                        intent.putExtra("pic", pic);
                    } else {
                        intent.putExtra("pic", "");
                    }
                    String user_pic = bean.get(position).getUser_pic();
                    if (user_pic != null && !user_pic.equals("")) {
                        intent.putExtra("user_pic", user_pic.replace("../", ""));
                    } else {
                        intent.putExtra("user_pic", "");
                    }
                    String username = bean.get(position).getUsername();
                    intent.putExtra("username", username);
                    intent.putExtra("zhuangtai", status);
                    String title = bean.get(position).getTitle();
                    intent.putExtra("title", title);
                    String price = bean.get(position).getPrice();
                    intent.putExtra("price", price);
                    String s = bean.get(position).getHelp_time() + bean.get(position).getUnit_time();
                    intent.putExtra("time", s);
                    String content = bean.get(position).getContent();
                    intent.putExtra("content", content);
                    String lng = bean.get(position).getLng();
                    intent.putExtra("lng", lng);
                    String lat = bean.get(position).getLat();
                    intent.putExtra("lat", lat);
                    context.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.sousuo_image)
        CircleImageView mSousuoImage;
        @Bind(R.id.sousuo_title)
        TextView mSousuoTitle;
        @Bind(R.id.sousuo_content)
        TextView mSousuoContent;
        @Bind(R.id.sousuo_money)
        TextView mSousuoMoney;
        @Bind(R.id.sousuo_layout)
        LinearLayout mSousuoLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

