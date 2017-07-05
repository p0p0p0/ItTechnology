package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackware on 2016/9/10.
 */
public class ExamplePagerAdapter extends PagerAdapter {
    /*private List<String> mDataList;
    ArrayList<View> viewContainter;
    private LinkedList<View> mViewCache;
    private LayoutInflater mLayoutInflater = null;
    private String s;
    private String bb;
    private int positiona;
    Context context;
    private List<NewsBean.DataBean> data;
    List<NewsBean.DataBean> mdata;

    public ExamplePagerAdapter(List<String> dataList, Context context) {
        this.mDataList = dataList;
        this.data = data;
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(ECApplication.context);
    }
    public void setData(List<NewsBean.DataBean> mdata){
        this.mdata = mdata;
    }


    public int getDataList() {
        return positiona;
    }

    public void setDataList(String s) {
        this.s = s;
    }

    public void notifys() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        KLog.e("instantiateItem", position);
        positiona = position;
        ViewHolder viewHolder = null;
        View convertView = null;
        if (mViewCache == null) {
            convertView = this.mLayoutInflater.inflate(R.layout.viewadapter_item_layout,
                    null, false);
            TextView textView = (TextView) convertView.findViewById(R.id.view_pager_item_textview);
            MyListView lsitview = (MyListView) convertView.findViewById(R.id.news_listview);
            ImageView image = (ImageView) convertView.findViewById(R.id.news_iamge);
            viewHolder = new ViewHolder();
            viewHolder.textView = textView;
            viewHolder.mNewsListview = lsitview;
            viewHolder.Newsimage = image;
            convertView.setTag(viewHolder);
        } else {
            convertView = mViewCache.removeFirst();
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String pp = mdata.get(position).getTitle();
        String image = mdata.get(position).getPicurl();
        Picasso.with(ECApplication.context).load(RequestAddress.IMAGE2 + image).into(viewHolder.Newsimage
        );
        viewHolder.textView.setText(pp);
        viewHolder.textView.setTextColor(Color.YELLOW);
        viewHolder.textView.setBackgroundColor(Color.GRAY);
        viewHolder.mNewsListview.setAdapter(new NewsAdapter(mdata,context));
        container.addView(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View contentView = (View) object;
        if (contentView != null) {
            container.removeView(contentView);
        } else {
            mViewCache.add(contentView);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataList.get(position);
    }

    public final class ViewHolder {
        public TextView textView;
        public ImageView Newsimage;
        public MyListView mNewsListview;
    }*/
    private List<String> mDataList;
    ArrayList<View> views;
    public ExamplePagerAdapter(List<String> dataList, ArrayList<View> views) {
        mDataList = dataList;
        this.views = views;
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        KLog.e("positionaaaaa",position);
        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataList.get(position);
    }
}
