package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.RegionInfoFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.Bean.AreaBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CallBack;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：王海宾 on 2017/5/4 0004 16:35
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class JiedaoFragment extends Fragment {
    CallBack callBack=null;
    private List<AreaBean.DataBean> mData;
    @Bind(R.id.sheng_listview)
    MyListView mShengListview;
    @Bind(R.id.nav_view)
    LinearLayout mNavView;
    @Bind(R.id.sheng_fanhui)
    ImageView mShengFanhui;
    private Jiedao mShi;
    List<AreaBean.DataBean.CcdataBean> ccdata;
    String sname;
    String sid;
    ShengFragment shengFragment;
    String sname1;
    String sid1;
    ShiFragment shiFragment;
    public JiedaoFragment(String sname, String sid, ShengFragment shengFragment, String sname1, String sid1, ShiFragment shiFragment, List<AreaBean.DataBean.CcdataBean> ccdata) {
        this.sname = sname;
        this.sid = sid;
        this.shengFragment = shengFragment;
        this.sname1 = sname1;
        this.sid1 = sid1;
        this.shiFragment = shiFragment;
        this.ccdata = ccdata;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sheng, container, false);
        ButterKnife.bind(this, view);
        town();
        return view;
    }

    public void town() {
        mShi = new Jiedao(ccdata);
        mShengListview.setAdapter(mShi);
        if (mShengListview != null) {
            mShengListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    FragmentManager manager =getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    List<Fragment> fragments = new ArrayList<Fragment>();
                    fragments.add(shengFragment);
                    fragments.add(shiFragment);
                    fragments.add(JiedaoFragment.this);
                    for (Fragment fragment : fragments) {
                        transaction.remove(fragment);
                    }
                    transaction.commit();
                    SharedPrefsUtil.put(ECApplication.context,"shengid",sid +","+ sid1 +","+ ccdata.get(position).getCcid());
                    String diqu = sid +","+ sid1 +","+ ccdata.get(position).getCcid();
                    String diqu1 = sname +"-"+ sname1 +"-"+ ccdata.get(position).getCcname();
                    KLog.e("地区",sname+sname1+ccdata.get(position).getCcname());
                    Bundle bundle=new Bundle();
                    bundle.putString("name",diqu);
                    bundle.putString("name1",diqu1);
                    callBack.call(bundle);
                    //把你的选择地区写在fragment里面
                    mShi.setSelectedPosition(position);
                    mShi.notifyDataSetInvalidated();
                }
            });
        }
    }

    @OnClick(R.id.sheng_fanhui)
    public void onClick() {
        getFragmentManager().popBackStack();
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        callBack= (CallBack) activity;
    }

}

class Jiedao extends BaseAdapter {
    private int selectedPosition = -1;
    List<AreaBean.DataBean.CcdataBean> data;
    public Jiedao(List<AreaBean.DataBean.CcdataBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.sheng_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (selectedPosition == position) {
            KLog.e("position+++++++", position);
            viewHolder.mShengItemview.setBackgroundColor(CommonUtil.getColor(R.color.juse));
            viewHolder.mShengText.setTextColor(CommonUtil.getColor(R.color.juse));
        } else {
            KLog.e("position---------", position);
            viewHolder.mShengItemview.setBackgroundColor(Color.TRANSPARENT);
            viewHolder.mShengText.setTextColor(CommonUtil.getColor(R.color.text_brown));
        }
        viewHolder.mShengText.setText(data.get(position).getCcname());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.sheng_text)
        TextView mShengText;
        @Bind(R.id.sheng_itemview)
        View mShengItemview;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setSelectedPosition(int position) {
        KLog.e("stSelectedPosition+++++", position);
        selectedPosition = position;
    }

}
