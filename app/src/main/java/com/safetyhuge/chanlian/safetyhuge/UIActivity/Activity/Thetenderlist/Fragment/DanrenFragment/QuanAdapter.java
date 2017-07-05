package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.roundimage.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.TenderMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
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
 * 作者：王海宾 on 2017/4/15 0015 19:16
 * 邮箱：995696826@qq.com
 */
public class QuanAdapter extends BaseAdapter {
    private Context context;
    boolean b;
    List<QuanbuBean.DataBean.WorkInfoBean> data;
    EditDialogCallBack callBack;
    private int aa1 = -1;

    public QuanAdapter(Context context, List<QuanbuBean.DataBean.WorkInfoBean> data, int aa1) {
        this.context = context;
        this.data = data;
        this.aa1 = aa1;
    }


    @Override
    public int getCount() {
        KLog.e("data.size()", data.size());
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    public boolean get() {
        boolean b = true;
        return b;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        KLog.e("aaaaa", aa1);
        if (aa1 == 0 || aa1 == 1) {
            //单人悬赏,多人悬赏
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_quanbu, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String xmFbUid = (String) SharedPrefsUtil.get(ECApplication.context, "XmFbUid", "");
            String userUid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
            Picasso.with(context).load(RequestAddress.IMAGE1 + "/" + data.get(position).getUpic()).fit().into(viewHolder.mQuanbuIamge);
            viewHolder.mQuanbuName.setText("投标者:" + data.get(position).getUsername());
            //work_desc
            viewHolder.mQuanbuNeirong.setText(data.get(position).getWork_desc());
            //work_time
            String tiem = data.get(position).getWork_time();
            String timedate = DateUtils.timedate(tiem);
            viewHolder.mQuanbuTime.setText("投标时间: " + timedate);
            String zhuangtai = data.get(position).getWork_status();
            KLog.e("zhuangtai", zhuangtai);
            if (zhuangtai.equals("0")) {
                viewHolder.mQuanbuZhuangtai.setText("");
                viewHolder.mButtonInfo.setVisibility(View.VISIBLE);
            } else if (zhuangtai.equals("1")) {
                viewHolder.mQuanbuZhuangtai.setText("一等奖");
                viewHolder.mButtonInfo.setVisibility(View.VISIBLE);
            } else if (zhuangtai.equals("2")) {
                viewHolder.mQuanbuZhuangtai.setText("二等奖");
                viewHolder.mButtonInfo.setVisibility(View.VISIBLE);
            } else if (zhuangtai.equals("3")) {
                viewHolder.mQuanbuZhuangtai.setText("三等奖");
                viewHolder.mButtonInfo.setVisibility(View.VISIBLE);
            } else if (zhuangtai.equals("4")) {
                viewHolder.mQuanbuZhuangtai.setText("中标");
                viewHolder.mButtonInfo.setVisibility(View.VISIBLE);
            } else if (zhuangtai.equals("5")) {
                viewHolder.mQuanbuZhuangtai.setText("入围");
                viewHolder.mButtonInfo.setVisibility(View.VISIBLE);
            } else if (zhuangtai.equals("6")) {
                viewHolder.mQuanbuZhuangtai.setText("");
            } else if (zhuangtai.equals("7")) {
                viewHolder.mQuanbuZhuangtai.setText("淘汰");
                viewHolder.mButtonInfo.setVisibility(View.GONE);
            }
            final String mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
            final String xmid = (String) SharedPrefsUtil.get(ECApplication.context, "xmid", "");
            //work_pirze
            final List<String> ss = data.get(position).getWork_pirze();
            KLog.e("sss", ss.size());
            if (ss != null) {
                if (ss.size() != 0 && aa1 == 0) {
                    for (int i = 0; i < ss.size(); i++) {
                        if (!ss.get(i).equals("设为淘汰")) {
                            viewHolder.mQuanbuMoren.setVisibility(View.GONE);
                            if (!ss.get(i).equals("设为入围")) {
                                viewHolder.mQuanbuRuwei.setVisibility(View.GONE);
                                if (!ss.get(i).equals("设为中标")) {
                                    viewHolder.mQuanbuZhongbiao.setVisibility(View.GONE);
                                } else {
                                    viewHolder.mQuanbuZhongbiao.setVisibility(View.VISIBLE);
                                }
                            } else {
                                viewHolder.mQuanbuRuwei.setVisibility(View.VISIBLE);
                            }
                        } else {
                            viewHolder.mQuanbuMoren.setVisibility(View.VISIBLE);
                        }
                    }
                } else if (ss.size() != 0 && aa1 == 1) {
                    viewHolder.mButtonInfo.setVisibility(View.GONE);
                    viewHolder.mDuorenButton.setVisibility(View.VISIBLE);
                    for (int i = 0; i < ss.size(); i++) {
                        KLog.e("sss", ss.get(i));
                        if (!ss.get(i).equals("设为淘汰")) {
                            KLog.e("ssss0", ss.get(i));
                            viewHolder.mDuorenMoren.setVisibility(View.GONE);
                            if (!ss.get(i).equals("一等奖")) {
                                KLog.e("ssss1", ss.get(i));
                                viewHolder.mDuorenYidengjiang.setVisibility(View.GONE);
                                if (!ss.get(i).equals("二等奖")) {
                                    KLog.e("ssss2", ss.get(i));
                                    viewHolder.mDuorenErdengjiang.setVisibility(View.GONE);
                                    if (!ss.get(i).equals("三等奖")) {
                                        KLog.e("ssss3", ss.get(i));
                                        viewHolder.mDuorenSandengjiang.setVisibility(View.GONE);
                                    } else {
                                        viewHolder.mDuorenSandengjiang.setVisibility(View.GONE);
                                    }
                                } else {
                                    viewHolder.mDuorenErdengjiang.setVisibility(View.VISIBLE);
                                }
                            } else {
                                viewHolder.mDuorenYidengjiang.setVisibility(View.VISIBLE);
                            }
                        } else {
                            viewHolder.mDuorenMoren.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    viewHolder.mDuorenButton.setVisibility(View.GONE);
                }
            }
            String fl = data.get(position).getWork_hidden();
            KLog.e("whb", fl);
            if (xmFbUid.equals(mUserid)) {
                viewHolder.mQuanbuNeirong.setVisibility(View.VISIBLE);
                viewHolder.yincang.setVisibility(View.GONE);
            } else if (fl.equals("0")) {
                viewHolder.mQuanbuNeirong.setVisibility(View.VISIBLE);
                viewHolder.yincang.setVisibility(View.GONE);
            } else {
                viewHolder.mQuanbuNeirong.setVisibility(View.GONE);
                viewHolder.yincang.setVisibility(View.VISIBLE);
            }
            //多人设为一等奖
            viewHolder.mDuorenYidengjiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.clickOk();
                    }
                    network(mUserid, xmid, data.get(position).getWork_id(), "1",position,0);
                }
            });
            //多人设为二等奖
            viewHolder.mDuorenErdengjiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.clickOk();
                    }
                    network(mUserid, xmid, data.get(position).getWork_id(), "2",position,1);
                }
            });
            //多人设为三等奖
            viewHolder.mDuorenSandengjiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.clickOk();
                    }
                    network(mUserid, xmid, data.get(position).getWork_id(), "3",position,2);
                }
            });
            //设为中标
            viewHolder.mQuanbuZhongbiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    network(mUserid, xmid, data.get(position).getWork_id(), "4",position,3);
                }
            });

            //设为入围
            viewHolder.mQuanbuRuwei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    network(mUserid, xmid, data.get(position).getWork_id(), "5",position,4);
                }
            });
            //设为淘汰
            viewHolder.mQuanbuMoren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    network(mUserid, xmid, data.get(position).getWork_id(), "7",position,5);
                }
            });
            //多人设为淘汰
            viewHolder.mDuorenMoren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.clickOk();
                    }
                    network(mUserid, xmid, data.get(position).getWork_id(), "7",position,6);
                }
            });
            viewHolder.mQuanbuChakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> s = data.get(position).getAttachment();
                    KLog.e("sss", s.size());
                    if (s.size() != 0) {
                    } else {
                        Toast.makeText(context, "没有附件", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            viewHolder.mdanren_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = data.get(position).getTask_id();
                    Intent intent = new Intent(context, TenderMinuteActivity.class);
                    intent.putExtra("layoutid", id);
                    if (aa1==0||aa1==1){
                        //多人单人
                        intent.putExtra("flag", 0);
                        intent.putExtra("flagss", 1);
                   /* }else if (model_id.equals("4")){
                        //普通
                        intent.putExtra("flag", 1);
                    }
                    else if (model_id.equals("5")){
                        //订金
                        intent.putExtra("flag", 2);*/
                    }
                    if (viewHolder.yincang.getVisibility()==View.GONE){
                        Toast.makeText(context, "您无权查看", Toast.LENGTH_SHORT).show();
                    }else{
                        context.startActivity(intent);
                    }
                }
            });
            return convertView;
        } else if (aa1 == 2 || aa1 == 3) {
            //普通招标,订金招标
            final ViewHolders viewHolders;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_zhaobiao, null);
                viewHolders = new ViewHolders(convertView);
                convertView.setTag(viewHolders);
            } else {
                viewHolders = (ViewHolders) convertView.getTag();
            }
            Picasso.with(context).load(RequestAddress.IMAGE1 + "/" + data.get(position).getUpic()).fit().into(viewHolders.mZhaobiaoImage);
            viewHolders.mZhaobiaoName.setText("投标者:" + data.get(position).getUsername());
            //内容
            viewHolders.mZhaobiaoNeirong.setText(data.get(position).getMessage());
            //金钱
            viewHolders.mZhaobiaoJinqian.setText(data.get(position).getQuote());
            //地区
            viewHolders.mZhaobiaoDiqu.setText(data.get(position).getArea());
            //周期
            viewHolders.mZhaobiaoDay.setText(data.get(position).getCycle() + "天");
            //时间
            String tiem = data.get(position).getBid_time();
            String timedate = DateUtils.timedate(tiem);
            viewHolders.mZhaobiaoTime.setText("投标时间: " + timedate);
            String zhuangtai = data.get(position).getBid_status();
            KLog.e("zhuangtai", zhuangtai);
            if (zhuangtai.equals("0")) {
                viewHolders.mZhaobiaoZhuangtai.setText("");
            } else if (zhuangtai.equals("4")) {
                viewHolders.mZhaobiaoZhuangtai.setText("中标");
            } else if (zhuangtai.equals("7")) {
                viewHolders.mZhaobiaoZhuangtai.setText("淘汰");
            }
            final String mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
            final String xmid = (String) SharedPrefsUtil.get(ECApplication.context, "xmid", "");
            //work_pirze
            final List<String> ss = data.get(position).getWork_pirze();
            KLog.e("sss", ss.size());
            if (ss != null) {
                if (ss.size() != 0) {
                    viewHolders.mZhaobiaoInfo.setVisibility(View.VISIBLE);
                    for (int i = 0; i < ss.size(); i++) {
                        if (!ss.get(i).equals("设为淘汰")) {
                            viewHolders.mZhaobiaoTaotai.setVisibility(View.GONE);
                            if (!ss.get(i).equals("设为中标")) {
                                viewHolders.mZhaobiaoZhongbiao.setVisibility(View.GONE);
                            } else {
                                viewHolders.mZhaobiaoTaotai.setVisibility(View.VISIBLE);
                            }
                        } else {
                            viewHolders.mZhaobiaoTaotai.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    viewHolders.mZhaobiaoInfo.setVisibility(View.GONE);
                }
            }
            String fl = data.get(position).getWork_hidden();
        /*    if (fl.equals("0")) {
                viewHolder.yincang.setVisibility(View.GONE);
            } else {
                viewHolder.mQuanbuNeirong.setVisibility(View.GONE);
                viewHolder.yincang.setVisibility(View.VISIBLE);
            }*/
            //设为中标
            viewHolders.mZhaobiaoZhongbiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.clickOk();
                    }
                    network(mUserid, xmid, data.get(position).getWork_id(), "4",position,7);
                }
            });

            //设为淘汰
            viewHolders.mZhaobiaoTaotai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.clickOk();
                    }
                    network(mUserid, xmid, data.get(position).getWork_id(), "7",position,8);
                }
            });
            viewHolders.myincang_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = data.get(position).getTask_id();
                    Intent intent = new Intent(context, TenderMinuteActivity.class);
                    intent.putExtra("layoutid", id);
                    if (aa1==2){
                        //多人单人
                        intent.putExtra("flag", 1);
                        intent.putExtra("flagss", 1);
                   /* }else if (model_id.equals("4")){
                        //普通
                        intent.putExtra("flag", 1);
                    }
                    else if (model_id.equals("5")){
                        //订金
                        intent.putExtra("flag", 2);*/
                    }
                    if (aa1==3){
                        intent.putExtra("flag", 2);
                        intent.putExtra("flagss", 1);
                    }
                   /* if (viewHolders.yincang.getVisibility()==View.GONE){
                        Toast.makeText(context, "您无权查看", Toast.LENGTH_SHORT).show();
                    }else{*/
                        context.startActivity(intent);
                  /*  }*/
                }
            });
            return convertView;
        }
        return null;
    }


    static class ViewHolder {
        @Bind(R.id.quanbu_iamge)
        CircleImageView mQuanbuIamge;
        @Bind(R.id.quanbu_name)
        TextView mQuanbuName;
        @Bind(R.id.quanbu_zhuangtai)
        TextView mQuanbuZhuangtai;
        @Bind(R.id.quanbu_neirong)
        TextView mQuanbuNeirong;
        @Bind(R.id.quanbu_time)
        TextView mQuanbuTime;
        @Bind(R.id.quanbu_chakan)
        TextView mQuanbuChakan;
        @Bind(R.id.quanbu_moren)
        Button mQuanbuMoren;
        @Bind(R.id.quanbu_ruwei)
        Button mQuanbuRuwei;
        @Bind(R.id.quanbu_zhongbiao)
        Button mQuanbuZhongbiao;
        @Bind(R.id.button_info)
        LinearLayout mButtonInfo;
        @Bind(R.id.yincang)
        TextView yincang;
        //多人
        @Bind(R.id.duoren_moren)
        Button mDuorenMoren;
        @Bind(R.id.duoren_yidengjiang)
        Button mDuorenYidengjiang;
        @Bind(R.id.duoren_erdengjiang)
        Button mDuorenErdengjiang;
        @Bind(R.id.duoren_sandengjiang)
        Button mDuorenSandengjiang;
        @Bind(R.id.duoren_button)
        LinearLayout mDuorenButton;
        //  android:id="@+id/danren_layout"
        @Bind(R.id.danren_layout)
        LinearLayout mdanren_layout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolders {
        @Bind(R.id.zhaobiao_image)
        CircleImageView mZhaobiaoImage;
        @Bind(R.id.zhaobiao_name)
        TextView mZhaobiaoName;
        @Bind(R.id.zhaobiao_zhuangtai)
        TextView mZhaobiaoZhuangtai;
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
        @Bind(R.id.zhaobiao_taotai)
        Button mZhaobiaoTaotai;
        @Bind(R.id.zhaobiao_zhongbiao)
        Button mZhaobiaoZhongbiao;
        @Bind(R.id.zhaobiao_info)
        LinearLayout mZhaobiaoInfo;
        @Bind(R.id.yincang_layout)
        LinearLayout myincang_layout;

        ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setCallBack(EditDialogCallBack callBack) {
        this.callBack = callBack;
    }

    public interface EditDialogCallBack {
        void clickOk();
    }

    public void network(String uid, String task_id, String work_id, String work_status, final int position, final int flag) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "DraftSelection");
        hashMap.put("uid", uid);
        hashMap.put("task_id", task_id);
        hashMap.put("work_id", work_id);
        hashMap.put("work_status", work_status);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                HashMap<String, Object> map = (HashMap<String, Object>) JSONUtil.getMapForJson(s);
                String msg = (String) map.get("code");
                if (msg.equals("888")) {
                    Toast.makeText(context, "设置成功", Toast.LENGTH_SHORT).show();
                    //设为中标
                    if (flag==3){
                        List<String> work_pirze = data.get(position).getWork_pirze();
                        for (int i = 0; i < work_pirze.size(); i++) {
                            if (work_pirze.get(i).equals("设为中标")){
                                work_pirze.remove(i);
                            }
                        }
                        data.get(position).setWork_status("4");
                        data.get(position).setWork_pirze(work_pirze);
                        notifyDataSetChanged();
                    }
                    //设为入围
                    if (flag==4){
                        List<String> work_pirze = data.get(position).getWork_pirze();
                        for (int i = 0; i < work_pirze.size(); i++) {
                            if (work_pirze.get(i).equals("设为入围")){
                                work_pirze.remove(i);
                            }
                        }
                        data.get(position).setWork_status("5");
                        data.get(position).setWork_pirze(work_pirze);
                        notifyDataSetChanged();
                    }
                    //设为淘汰
                    if (flag==5){
                        data.get(position).setWork_status("7");
                        notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(context, "设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
