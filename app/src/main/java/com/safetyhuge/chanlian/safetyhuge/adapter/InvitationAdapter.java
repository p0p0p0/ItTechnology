package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.YaoqingBean;
import com.safetyhuge.chanlian.safetyhuge.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/14 0014 17:26
 * 邮箱：995696826@qq.com
 */
public class InvitationAdapter extends BaseAdapter {
    private List<YaoqingBean.DataBean> dataBeen;
    // 上下文
    private Context context;
    // 用来导入布局
    private LayoutInflater inflater = null;
    private String flag;
    private static List<String> mStringList ;

    public InvitationAdapter(Context context, List<YaoqingBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }


    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List getList() {
        return mStringList;
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_inivyaoqing, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String a = dataBeen.get(position).getProvince_name();
        String a1 = dataBeen.get(position).getCity_name();
        String a2 = dataBeen.get(position).getArea_name();
        viewHolder.mInivyaoqingDiqu.setText(a + " " + a1 + " " + a2);
        viewHolder.mInivyaoqingMoney.setText(dataBeen.get(position).getShow_cash());
        viewHolder.mInivyaoqingTile.setText(position + 1 + "、" + dataBeen.get(position).getTask_title());
        // 根据isSelected来设置checkbox的选中状况
        viewHolder.mInivyaoqingChx.setChecked(dataBeen.get(position).isChoosed());
        viewHolder.mInivyaoqingChx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.mInivyaoqingChx.setChecked(((CheckBox) v).isChecked());
                dataBeen.get(position).setChoosed( ((CheckBox) v).isChecked());
                checkInterface.checkChild(position, ((CheckBox) v).isChecked());// 暴露子选接口
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.inivyaoqing_chx)
        CheckBox mInivyaoqingChx;
        @Bind(R.id.inivyaoqing_tile)
        TextView mInivyaoqingTile;
        @Bind(R.id.inivyaoqing_money)
        TextView mInivyaoqingMoney;
        @Bind(R.id.inivyaoqing_diqu)
        TextView mInivyaoqingDiqu;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private CheckInterface       checkInterface;
    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 子选框状态改变时触发的事件
         *
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        void checkChild(int childPosition, boolean isChecked);
    }
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }
}
