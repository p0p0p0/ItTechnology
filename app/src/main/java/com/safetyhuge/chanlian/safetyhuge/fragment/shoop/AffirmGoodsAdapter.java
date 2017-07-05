package com.safetyhuge.chanlian.safetyhuge.fragment.shoop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.FaPiaoActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.IndentActivity;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.GoodsCartBean.LookCart;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by F57 on 2017/5/1.
 */
public class AffirmGoodsAdapter extends BaseAdapter {
    IndentActivity indentActivity;
    /**
     * 一级数据
     */
    List<LookCart.DataBean> checkoutShops;
    /**
     * 二级数据
     */
    List<List<LookCart.DataBean.DetailsBean>> checkoutProductsList;
    //定义一个HashMap，用来存放EditText的值，Key是position
    HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

    private Map map;
    private double totalPrice = 0.00;// 购买的商品总价
    private int mCounts = 0;
    StringBuilder mBuilder = new StringBuilder();
    ArrayList<String> mList = new ArrayList<>();
    String text;
    String str = "0";
    ListView nearby;

    public AffirmGoodsAdapter(IndentActivity indentActivity, List<LookCart.DataBean> checkoutShops, List<List<LookCart.DataBean.DetailsBean>> checkoutProductsList, String text, ListView nearby) {
        this.checkoutProductsList = checkoutProductsList;
        this.checkoutShops = checkoutShops;
        this.indentActivity = indentActivity;
        this.text = text;
        this.nearby = nearby;
        KLog.e("info1", text);
    }

    public HashMap<Integer, String> getMap() {
        return hashMap;
    }

    @Override
    public int getCount() {
        return checkoutShops.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setString(String str) {
        this.str = str;
        KLog.e("str", str);

    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(indentActivity, R.layout.item_goods_one, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mItemGoodsUsername.setText(checkoutShops.get(i).getUsername());
        viewHolder.mItemGoodsListview.setAdapter(new AffirmItemAdapter(checkoutShops.get(i).getDetails()));
        for (int i1 = 0; i1 < checkoutShops.get(i).getDetails().size(); i1++) {
            if (checkoutShops.get(i).getDetails().get(i1).getInvoice().equals("1")) {
                viewHolder.mGoodsOneFapiao.setVisibility(View.VISIBLE);
            }
            KLog.e("str1111", str);
            if (str != null) {
                if (str.equals("0")) {
                    viewHolder.mGoodsOneContent.setText("不开发票");
                } else {
                    viewHolder.mGoodsOneContent.setText(str);
                }
            }
            final int finalI = i1;
            if (checkoutShops.get(i).getDetails().get(i1).getInvoice().equals("1")) {
              //  viewHolder.mGoodsOneFapiao.setVisibility(View.VISIBLE);
               mList.add(checkoutShops.get(i).getDetails().get(i1).getService_id());
            } else {
            }
            viewHolder.mGoodsOneFapiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KLog.e("finalI", finalI);
                    KLog.e("i", i);
                    Intent intent = new Intent(indentActivity, FaPiaoActivity.class);
                    intent.putExtra("sid", mBuilder + "");
                    intent.putStringArrayListExtra("sidinfo", mList);
                    indentActivity.startActivityForResult(intent, 1);
                }
            });
            String amount = checkoutShops.get(i).getDetails().get(i1).getAmount();
            int count = Integer.valueOf(amount);
            double price = checkoutShops.get(i).getDetails().get(i1).getPrice();
            totalPrice += price * count;
            mCounts = mCounts + count;
            viewHolder.mItemGoodsCount.setText("共计" + count + "件产品,");
            viewHolder.mItemGoodsMoney.setText("小计 ¥" + price + "");
        }
        viewHolder.mItemGoodsEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                hashMap.put(i, s.toString());
            }
        });
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_goods_username)
        TextView mItemGoodsUsername;
        @Bind(R.id.item_goods_listview)
        MyListView mItemGoodsListview;
        @Bind(R.id.item_goods_count)
        TextView mItemGoodsCount;
        @Bind(R.id.item_goods_money)
        TextView mItemGoodsMoney;
        @Bind(R.id.item_goods_et)
        EditText mItemGoodsEt;
        @Bind(R.id.goods_one_content)
        TextView mGoodsOneContent;
        @Bind(R.id.goods_one_fapiao)
        RelativeLayout mGoodsOneFapiao;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 监听编辑状态
     */
    public interface Map {
        void mapInfo(HashMap<Integer, String> map);
    }

    public void setMap(Map map) {
        this.map = map;
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
