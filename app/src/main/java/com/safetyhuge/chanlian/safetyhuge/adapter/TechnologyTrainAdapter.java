package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.safetyhuge.chanlian.safetyhuge.Bean.TechnologyBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.socks.library.KLog;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Chinalink on 2017/3/30 0030.
 */
public class TechnologyTrainAdapter extends BaseAdapter {
    private Context context;
    private List<TechnologyBean.DataBean> dataBeen;
    private int flag;

    public TechnologyTrainAdapter(Context context, List<TechnologyBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }


    @Override
    public int getCount() {
        KLog.e("sizesize", dataBeen.size());
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
            convertView = View.inflate(context, R.layout.item_technolog, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
      /*  String imageUrl = dataBeen.get(position).getPicurl();
        KLog.e("imageUrl", imageUrl);
        if (!imageUrl.isEmpty()) {
            Picasso.with(context).load(RequestAddress.IMAGE2
                    + imageUrl).fit().into(viewHolder.mTechnologIamge);
        }
        viewHolder.mTechnologFname.setText(dataBeen.get(position).getFname());
        viewHolder.mTechnologName.setText(dataBeen.get(position).getTitle());
        viewHolder.mTechnologLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.e("id", dataBeen.get(position).getFid());
                Intent intent = new Intent(context, TechnologyminuteActivity.class);
                intent.putExtra("title", dataBeen.get(position).getTitle());
                intent.putExtra("fid", dataBeen.get(position).getFid());
                intent.putExtra("xiazai", dataBeen.get(position).getSofturl());
                intent.putExtra("fileUrl", dataBeen.get(position).getSofturl());
                context.startActivity(intent);
            }
        });*/
        return convertView;
    }

    static class ViewHolder {
      /*  @Bind(R.id.technolog_iamges)
        ImageView mTechnologIamge;
        @Bind(R.id.technolog_name)
        TextView mTechnologName;
        @Bind(R.id.technolog_fname)
        TextView mTechnologFname;
        @Bind(R.id.technolog_layou)
        RelativeLayout mTechnologLayou;*/

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
