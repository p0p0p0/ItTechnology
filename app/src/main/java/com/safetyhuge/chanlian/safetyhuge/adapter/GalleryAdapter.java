package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.ServeFragment;
import com.socks.library.KLog;

import java.util.List;

/**
 * 作者：王海宾 on 2017/5/16 0016 21:03
 * 邮箱：995696826@qq.com
 */
public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{

    private LayoutInflater mInflater;
    private List<String> mDatas;

    ServeFragment context;
    public GalleryAdapter(Context context, List<String> datats)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        TextView mTxt;
    }

    @Override
    public int getItemCount()
    {        KLog.e("size",mDatas.size());

        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.activity_index_gallery_item,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mTxt = (TextView) view
                .findViewById(R.id.text_view_item);
        KLog.e("+++++++++++++++++++","111111111111");
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
        KLog.e("aaa",mDatas.get(i)+"");
        viewHolder.mTxt.setText(mDatas.get(i)+"");
    }

}