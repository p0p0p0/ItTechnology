package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.RegionInfoFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProjectBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.CallBack;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.CommonUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/4 0004 14:46
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class ShengFragment extends Fragment {
    @Bind(R.id.sheng_listview)
    MyListView mShengListview;
    @Bind(R.id.nav_view)
    LinearLayout mNavView;
    @Bind(R.id.sheng_fanhui)
    ImageView mShengFanhui;
    CallBack callBack=null;
    private ArrayList<String> mRegionIdyList, mRegionyNameList;
    private sheng mSheng;
    private String mShengid,mShengName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sheng, container, false);
        ButterKnife.bind(this, view);
        alents();
     SharedPrefsUtil.remove(ECApplication.context,"shengid");
        SharedPrefsUtil.remove(ECApplication.context,"shengname");
        return view;
    }

    private void alents() {
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params("action", "GetTaskTextInfo").execute(new JsonCallback<ProjectBean>() {
            @Override
            public void onSuccess(ProjectBean projectBean, Call call, Response response) {
                ProjectBean.DataBean data = projectBean.getData();
                List<ProjectBean.DataBean.RegionBean> regionBeen = projectBean.getData().getRegion();
                List<ProjectBean.DataBean.IndusBean> dataBeen = projectBean.getData().getIndus();
                mRegionyNameList = new ArrayList<String>();
                mRegionIdyList = new ArrayList<String>();
                for (ProjectBean.DataBean.RegionBean regionBean : regionBeen) {
                    mRegionyNameList.add(regionBean.getPname());
                    mRegionIdyList.add(regionBean.getPid());
                }
                mSheng = new sheng(mRegionyNameList, mRegionIdyList);
                mShengListview.setAdapter(mSheng);
            }
        });
        if (mShengListview != null) {
            mShengListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //把你的选择地区写在fragment里面
                    mShengid = mRegionIdyList.get(position);
                    mShengName = mRegionyNameList.get(position);
                    SharedPrefsUtil.put(ECApplication.context,"shengid",mShengid+","+"0"+","+"0");
                    SharedPrefsUtil.put(ECApplication.context,"shengname",mShengName);
                    ShiFragment fragment = new ShiFragment(mRegionyNameList.get(position)+"",mRegionIdyList.get(position)+"",ShengFragment.this);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_right_entry, R.anim.hold,R.anim.hold,R.anim.slide_right_exit);
                    fragmentTransaction.add(R.id.goods_soppingg,fragment)
                            .addToBackStack(null)
                            .commit();
                    mSheng.setSelectedPosition(position);
                    mSheng.notifyDataSetInvalidated();
                }
            });
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.sheng_fanhui)
    public void onClick() {
        getFragmentManager().popBackStack();
        Bundle bundle=new Bundle();
        if (mShengid!=null){
            bundle.putString("mShengid",mShengid);
        }
        if (mShengName!=null){
            bundle.putString("mShengName",mShengName);
        }
        callBack.call(bundle);
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        callBack= (CallBack) activity;
    }
}

class sheng extends BaseAdapter {
    private int selectedPosition = -1;
    ArrayList<String> regionyNameList;
    ArrayList<String> regionIdyList;

    public sheng(ArrayList<String> regionyNameList, ArrayList<String> regionIdyList) {
        this.regionyNameList = regionyNameList;
        this.regionIdyList = regionIdyList;
    }

    @Override
    public int getCount() {
        return regionyNameList.size();
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
        viewHolder.mShengText.setText(regionyNameList.get(position));

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

