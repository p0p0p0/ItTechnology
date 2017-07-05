package com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.ToubiaoBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.TenderMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.ProjectMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.socks.library.KLog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/22 0022 14:15
 * 邮箱：995696826@qq.com
 */
public class ToubiaoAdapter extends BaseAdapter {
    Context context;
    List<ToubiaoBean.DataBean> dataBeen;
    private int mA;

    public ToubiaoAdapter(Context context, List<ToubiaoBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_toubiao, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String modeli_id = dataBeen.get(position).getModel_id();
        KLog.e("modeli_id",modeli_id);
        if (modeli_id!=null){
            if (modeli_id.equals("1")) {
                viewHolder.mToubiaoMoshi.setText("单人悬赏");
            } else if (modeli_id.equals("2")) {
                viewHolder.mToubiaoMoshi.setText("多人悬赏");
            } else if (modeli_id.equals("4")) {
                viewHolder.mToubiaoMoshi.setText("普通招标");
            } else if (modeli_id.equals("5")) {
                viewHolder.mToubiaoMoshi.setText("订金招标");
            }
        }
        String zhuangtai = dataBeen.get(position).getStatus();
        String s  = new String();
        String task = dataBeen.get(position).getTask_status();
      /*  KLog.e("zhuangtai",zhuangtai);
        KLog.e("task",task);*/
        if (task!=null){
            mA = Integer.parseInt(task);
        if (zhuangtai!=null&&task!=null){
            if (zhuangtai.equals("0")){
                String text =mA>4 ? "未中标":"投标中";
                viewHolder.mToubiaoZhuangtai.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                viewHolder.mToubiaoZhuangtai.setText(text);
            } else if(zhuangtai.equals("1")){
                String text =mA>4 ? "未中标":"一等奖";
                viewHolder.mToubiaoZhuangtai.setTextColor(CommonUtil.getColor(R.color.jue));
                viewHolder.mToubiaoZhuangtai.setText(text);
            }else if(zhuangtai.equals("2")){
                String text =mA>4 ? "未中标":"二等奖";
                viewHolder.mToubiaoZhuangtai.setTextColor(CommonUtil.getColor(R.color.jue));
                viewHolder.mToubiaoZhuangtai.setText(text);
            }else if(zhuangtai.equals("3")){
                String text =mA>4 ? "未中标":"三等奖";
                viewHolder.mToubiaoZhuangtai.setTextColor(CommonUtil.getColor(R.color.jue));
                viewHolder.mToubiaoZhuangtai.setText(text);
            }else if(zhuangtai.equals("4")){
                String text =mA>4 ? "中标":"未中标";
                KLog.e("zoule",mA);
                viewHolder.mToubiaoZhuangtai.setTextColor(CommonUtil.getColor(R.color.jue));
                viewHolder.mToubiaoZhuangtai.setText(text);
            }else if(zhuangtai.equals("5")){
                String text =mA>4 ? "入围":"未中标";
                viewHolder.mToubiaoZhuangtai.setTextColor(CommonUtil.getColor(R.color.jue));
                viewHolder.mToubiaoZhuangtai.setText(text);
            }else if(zhuangtai.equals("7")){
                String text =mA>4 ? "淘汰":"未中标";
                viewHolder.mToubiaoZhuangtai.setTextColor(CommonUtil.getColor(R.color.jiujiu));
                viewHolder.mToubiaoZhuangtai.setText(text);
            }
        }
        }

        viewHolder.mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = dataBeen.get(position).getTask_id();
                    Intent intent = new Intent(context, ProjectMinuteActivity.class);
                    intent.putExtra("layoutid", id);
                    context.startActivity(intent);
                    KLog.e("layoutid", id);
                }
            });
        viewHolder.mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String id = taskListData.get(position).getTask_id();
                Intent intent = new Intent(context, ProjectMinuteActivity.class);
                intent.putExtra("layoutid", id);
                context.startActivity(intent);
                KLog.e("layoutid", id);*/
              //查看标书详情
                String id = dataBeen.get(position).getTask_id();
                Intent intent = new Intent(context, TenderMinuteActivity.class);
                intent.putExtra("layoutid", id);
                String model_id = dataBeen.get(position).getModel_id();
                if (model_id.equals("1")||model_id.equals("2")){
                    //多人单人
                    intent.putExtra("flag", 0);
                }else if (model_id.equals("4")){
                    //普通
                    intent.putExtra("flag", 1);
                }
                else if (model_id.equals("5")){
                    //订金
                    intent.putExtra("flag", 2);
                }
                context.startActivity(intent);
            }
        });
        String task_title = dataBeen.get(position).getTask_title();
        viewHolder.mToubiaoBianhao.setText("项目编号:  "+dataBeen.get(position).getId());
        viewHolder.mToubiaoMingcheng.setText("项目名称:  "+ task_title);
        viewHolder.mToubiaoItem.setText("投标时间:  "+ DateUtils.timedate(dataBeen.get(position).getOntime()));
        if (task_title!=null&&!task_title.equals("")){
            viewHolder.mButton1.setClickable(true);
            viewHolder.mButton2.setClickable(true);
        }else{
            viewHolder.mButton1.setClickable(false);
            viewHolder.mButton2.setClickable(false);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.toubiao_bianhao)
        TextView mToubiaoBianhao;
        @Bind(R.id.toubiao_zhuangtai)
        TextView mToubiaoZhuangtai;
        @Bind(R.id.toubiao_mingcheng)
        TextView mToubiaoMingcheng;
        @Bind(R.id.toubiao_item)
        TextView mToubiaoItem;
        @Bind(R.id.toubiao_moshi)
        TextView mToubiaoMoshi;
        @Bind(R.id.button1)
        Button mButton1;
        @Bind(R.id.button2)
        Button mButton2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
