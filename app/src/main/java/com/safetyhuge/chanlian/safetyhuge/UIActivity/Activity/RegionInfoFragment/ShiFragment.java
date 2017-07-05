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
import com.safetyhuge.chanlian.safetyhuge.Bean.AreaBean;
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
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/5/4 0004 16:23
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class ShiFragment extends Fragment {
    private List<AreaBean.DataBean> mData;
    @Bind(R.id.sheng_listview)
    MyListView mShengListview;
    @Bind(R.id.nav_view)
    LinearLayout mNavView;
    @Bind(R.id.sheng_fanhui)
    ImageView mShengFanhui;
    CallBack callBack=null;
    private ArrayList<String> mAreaNameList,MAreaIdList;
    String sname;
    private shi mShi;
    ShengFragment shengFragment;
    String sid;
    private String mSid,mSname;

    public ShiFragment(String sname, String sid, ShengFragment shengFragment) {
        this.sname=sname;
        this.sid = sid;
        this.shengFragment = shengFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sheng, container, false);
        ButterKnife.bind(this, view);
        town(sid,"");
        return view;
    }
    public void town(String pid, String cid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetTaskRegionByPid");
        hashMap.put("pid", pid);
        hashMap.put("cid", cid);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<AreaBean>() {
            @Override
            public void onSuccess(AreaBean areaBean, Call call, Response response) {
                mData = areaBean.getData();
                mAreaNameList = new ArrayList<>();
                MAreaIdList = new ArrayList<>();
                for (AreaBean.DataBean dataBean : mData) {
                    mAreaNameList.add(dataBean.getCname());
                    MAreaIdList.add(dataBean.getCid());
                }
                mShi = new shi(mAreaNameList);
                mShengListview.setAdapter(mShi);
            }
        });
        if (mShengListview != null) {
            mShengListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //把你的选择地区写在fragment里面
                    mSid = MAreaIdList.get(position);
                    mSname = mAreaNameList.get(position);
                    SharedPrefsUtil.put(ECApplication.context,"shengid",sid+","+mSid+","+"0");
                    SharedPrefsUtil.put(ECApplication.context,"shengname",sname+","+mSname);
                    JiedaoFragment fragment = new JiedaoFragment(sname,sid,shengFragment,mSname,mSid,ShiFragment.this,mData.get(position).getCcdata());
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_right_entry, R.anim.hold,R.anim.hold,R.anim.slide_right_exit);
                    fragmentTransaction.add(R.id.goods_soppingg,fragment)
                            .addToBackStack(null)
                            .commit();
                    mShi.setSelectedPosition(position);
                    mShi.notifyDataSetInvalidated();
                }
            });
        }
    }
    @OnClick(R.id.sheng_fanhui)
    public void onClick() {
        getFragmentManager().popBackStack();
        Bundle bundle=new Bundle();
        if (mSid!=null){
            bundle.putString("shiid",mSid+","+sid);
        }
        if (mSname!=null){
            bundle.putString("shiname",sname+mSname);
            KLog.e("shiname",mSname+sname);
        }
        callBack.call(bundle);
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        callBack= (CallBack) activity;
    }

}
class shi extends BaseAdapter {
    private int selectedPosition = -1;
    ArrayList<String> regionyNameList;

    public shi(ArrayList<String> regionyNameList) {
        this.regionyNameList = regionyNameList;

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
