package com.safetyhuge.chanlian.safetyhuge.adapter;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.TaskList;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.ProjectMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.StringUtils;
import com.socks.library.KLog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by F-57 on 2017/3/21.
 */

public class TaskListAdapter extends BaseAdapter {
    private List<TaskList.DataBean> taskListData;
    private int a;
    private Activity context;
    private String mModel;

    public TaskListAdapter(Activity context, List<TaskList.DataBean> taskListData, int a) {
        this.taskListData = taskListData;
        this.a = a;
        this.context = context;
    }


    @Override
    public int getCount() {
        if (a == 2||a==1) {
            return 3;
        } else {
            return taskListData.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return taskListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if (a==1) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(ECApplication.context, R.layout.item_tasklist, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        } else if (a == 2) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(ECApplication.context, R.layout.item_tasklist, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mProjectLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = taskListData.get(position).getTask_id();
                    Intent intent = new Intent(context, ProjectMinuteActivity.class);
                    intent.putExtra("layoutid", id);
                    SharedPrefsUtil.put(ECApplication.context, "layoutid", id);
                    context.startActivity(intent);
                    KLog.e("layoutid", id);
                }
            });
            viewHolder.mTvProjectTitle.setText(taskListData.get(position).getTask_title());
            viewHolder.mTvContent.setText("   " + StringUtils.Transition(taskListData.get(position).getTask_desc()));
            viewHolder.mTvBudget.setText(taskListData.get(position).getShow_cash());
            String item = taskListData.get(position).getSub_time();
            String a = DateUtils.timesTwo(item);
            viewHolder.mTvTime.setText(a);
            String region = taskListData.get(position).
                    getProvince_name() + taskListData.get(position).
                    getCity_name() + taskListData.get(position).getArea_name();
            viewHolder.mTvLocation.setText(region);
            return convertView;
        } else {
            ViewHolders viewHolders;
            if (convertView == null) {
                convertView = View.inflate(ECApplication.context, R.layout.item_project, null);
                viewHolders = new ViewHolders(convertView);
                convertView.setTag(viewHolders);
            } else {
                viewHolders = (ViewHolders) convertView.getTag();
            }
            viewHolders.mProjectLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = taskListData.get(position).getTask_id();
                    Intent intent = new Intent(context, ProjectMinuteActivity.class);
                    intent.putExtra("layoutid", id);
                    context.startActivity(intent);
                    KLog.e("layoutid", id);
                }
            });
            viewHolders.mProjectTitle.setText(taskListData.get(position).getTask_title());
            viewHolders.mProjectMoney.setText(taskListData.get(position).getShow_cash());
            KLog.e("pid", taskListData.get(position).getIndus_pid());
            KLog.e("taskListData.get(position).getShow_cash()", taskListData.get(position).getShow_cash());
            viewHolders.product_count.setText(position + 1 + "");
            String mModel_id = taskListData.get(position).getModel_id();
            KLog.e("mModel_id", mModel_id);
            //悬赏
            if (mModel_id.equals("1")) {
                mModel = "单人悬赏";
            } else if (mModel_id.equals("2")) {
                mModel = "多人悬赏";
            } else if (mModel_id.equals("4")) {
                mModel = "普通招标";
            } else if (mModel_id.equals("5")) {
                mModel = "订金招标";
            }
            KLog.e("mModel", mModel);
            viewHolders.tv_text_moshi.setText("  |  " + taskListData.get(position).getProvince_name() + taskListData.get(position).getCity_name() + taskListData.get(position).getArea_name());
            viewHolders.mProjetRegion.setText(" | " + mModel);

            return convertView;
        }
    }


    static class ViewHolder {
        @Bind(R.id.tv_project_title)
        TextView mTvProjectTitle;
        @Bind(R.id.tv_content)
        TextView mTvContent;
        @Bind(R.id.tv_budget)
        TextView mTvBudget;
        @Bind(R.id.tv_time)
        TextView mTvTime;
        @Bind(R.id.tv_location)
        TextView mTvLocation;
        @Bind(R.id.project_layout2)
        LinearLayout mProjectLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolders {
        @Bind(R.id.project_title)
        TextView mProjectTitle;
        @Bind(R.id.project_money)
        TextView mProjectMoney;
        @Bind(R.id.projet_region)
        TextView mProjetRegion;
        @Bind(R.id.project_count1)
        TextView product_count;
        @Bind(R.id.tv_text_moshi)
        TextView tv_text_moshi;
        @Bind(R.id.project_layout1)
        LinearLayout mProjectLayout;

        ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

