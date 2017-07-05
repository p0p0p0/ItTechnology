package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.ProjectMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/29 0029.
 */
public class TaskAdapter extends BaseAdapter {
    private Context context;
    private List<TaskBean.DataBean> dataBeen;

    public TaskAdapter(Context context, List<TaskBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_task, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
       viewHolder.mTaskTitle.setText(dataBeen.get(position).getTask_title());
        viewHolder.mTaskMoney.setText(dataBeen.get(position).getShow_cash());
        String time = dataBeen.get(position).getSub_time();
        //时间
        String  times = DateUtils.timesTwo(time);
        //用户名
        String username = dataBeen.get(position).getUsername();
        //S地区
        String diqu = dataBeen.get(position).getProvince_name()+
                dataBeen.get(position).getCity_name()+
                dataBeen.get(position).getArea_name();
        viewHolder.mTaskInfo.setText("任务截至日期:  "+times+"  |  "+username+"  |  "+diqu);
        viewHolder.mTaskTitlemTaskClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = dataBeen.get(position).getTask_id();
                Intent intent = new Intent(context, ProjectMinuteActivity.class);
                intent.putExtra("layoutid", id);
                SharedPrefsUtil.put(ECApplication.context, "layoutid", id);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.task_title)
        TextView mTaskTitle;
        @Bind(R.id.task_money)
        TextView mTaskMoney;
        @Bind(R.id.task_info)
        TextView mTaskInfo;
        @Bind(R.id.task_click)
        LinearLayout mTaskTitlemTaskClick;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
