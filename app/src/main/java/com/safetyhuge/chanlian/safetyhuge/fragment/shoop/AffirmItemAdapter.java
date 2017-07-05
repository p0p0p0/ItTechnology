package com.safetyhuge.chanlian.safetyhuge.fragment.shoop;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.GoodsCartBean.LookCart;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by F57 on 2017/5/1.
 */
public class AffirmItemAdapter extends BaseAdapter {
    List<LookCart.DataBean.DetailsBean> details;

    public AffirmItemAdapter(List<LookCart.DataBean.DetailsBean> details) {
        this.details = details;
    }

    @Override
    public int getCount() {
        KLog.e("details",details.size());
        return details.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(ECApplication.context, R.layout.item_goods_two, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mItemGoodsCount1.setText("x" + details.get(i).getAmount());
        viewHolder.mItemGoodsTitle1.setText(details.get(i).getTitle());
        Picasso.with(ECApplication.context).load(RequestAddress.IMAGE1 + details.get(i).getPic()).into(viewHolder.mItemGoodsIamge);
        viewHolder.mItemGoodsMoney.setText("¥ "+details.get(i).getPrice()+"");
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_goods_iamge)
        ImageView mItemGoodsIamge;
        @Bind(R.id.item_goods_title1)
        TextView mItemGoodsTitle1;
        @Bind(R.id.item_goods_money)
        TextView mItemGoodsMoney;
        @Bind(R.id.item_goods_count1)
        TextView mItemGoodsCount1;
        @Bind(R.id.formgoods_layout)
        LinearLayout mFormgoodsLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
