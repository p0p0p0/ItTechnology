package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.CollectFragmentInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mcxtzhang.commonadapter.lvgv.CommonAdapter;
import com.mcxtzhang.commonadapter.lvgv.ViewHolder;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.ChanpingBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.FananBean;
import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.XiangmuBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.SchemeDetailActivity;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.DateUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 收藏管理
 * 作者：王海宾 on 2017/4/21 0021 15:42
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class CollectFragment extends Fragment {
    @Bind(R.id.text111)
    TextView mText111;
    @Bind(R.id.quanbu_image)
    LinearLayout mQuanbuImage;
    @Bind(R.id.quanbu_list1)
    MyListView mQuanbuList1;
    private String mUserid;
    private String mId;
    private Context mContext;
    private int a;
    private KProgressHUD mKProgressHUD;

    public CollectFragment(int a) {
        this.a = a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_collectfragment, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        flag(a);
        KLog.e("走了走了");
        return view;
    }

    public void flag(int aa) {
        switch (aa) {
            case 0:
                //项目
                NetWork(mUserid, "3");
                break;
            case 1:
                //产品
                NetWork1(mUserid, "1", "6");
                break;
            case 2:
                //方案
                NetWork2(mUserid, "1", "13");
                break;
        }
    }

    //项目
    public void NetWork(String uid, String type) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetCollectInfo");
        hashMap.put("uid", uid);
        hashMap.put("type", type);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<XiangmuBean>() {
            @Override
            public void onSuccess(XiangmuBean xiangmuBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                List<?> data = xiangmuBean.getData();
                if (data.isEmpty()) {
                    mText111.setText("暂无收藏项目");
                    mQuanbuList1.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                    //     mQuanbuList1.setAdapter();
                }
            }
        });
    }

    public void NetWork1(String uid, String type, String mid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetCollectInfo");
        hashMap.put("uid", uid);
        hashMap.put("type", type);
        hashMap.put("mid", mid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<ChanpingBean>() {
            @Override
            public void onSuccess(ChanpingBean chanpingBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                List<ChanpingBean.DataBean> data = chanpingBean.getData();
                if (data.isEmpty()) {
                    KLog.e("null空了");
                    mText111.setText("暂无收藏产品");
                    mQuanbuList1.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                } else {
                    mQuanbuList1.setVisibility(View.VISIBLE);
                    mQuanbuImage.setVisibility(View.GONE);
                    setAdapter(mQuanbuList1, data, 0);
                    //  mQuanbuList1.setAdapter(new ChanpingScAdapter(data,getActivity()));
                }
            }
        });

    }

    public void NetWork2(String uid, String type, String mid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetCollectInfo");
        hashMap.put("uid", uid);
        hashMap.put("type", type);
        hashMap.put("mid", mid);
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<FananBean>() {
            @Override
            public void onSuccess(FananBean fananbean, Call call, Response response) {
                mKProgressHUD.dismiss();
                List<FananBean.DataBean> data = fananbean.getData();
                if (data.isEmpty()) {
                    mText111.setText("暂无收藏方案");
                    mQuanbuList1.setVisibility(View.GONE);
                    mQuanbuImage.setVisibility(View.VISIBLE);
                } else {
                    mQuanbuList1.setVisibility(View.VISIBLE);
                    mQuanbuImage.setVisibility(View.GONE);
                    setFAAdapter(mQuanbuList1,data,0);
                   // mQuanbuList1.setAdapter(new Fananadapter(data, getActivity()));
                }
            }
        });
    }


    public void onResume() {
        super.onResume();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        MobclickAgent.onResume(getActivity());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    public void setAdapter(ListView view, final List<ChanpingBean.DataBean> dataBeanList, final int aa) {
        view.setAdapter(new CommonAdapter<ChanpingBean.DataBean>(getContext(), dataBeanList, R.layout.item_chanpingsc) {
            @Override
            public void convert(final ViewHolder viewHolder, final ChanpingBean.DataBean dataBean, int i) {
                viewHolder.setText(R.id.goods_dg_title, dataBean.getTitle());
                viewHolder.setText(R.id.goods_dg_money, dataBean.getPrice());
                viewHolder.getPosition();
                //
                final String s = DateUtils.timesTwo(dataBean.getOn_time());
                viewHolder.setText(R.id.goods_dg_count, s);
                String pic = dataBean.getPic();
                if (pic != null && !pic.equals("")) {
                    KLog.e("whb", pic);
                    String iamgeurl = RequestAddress.IMAGE1 + dataBean.getPic();
                    Picasso.with(getContext()).load(iamgeurl).fit().into((ImageView) viewHolder.getView(R.id.goods_dg_image));
                }
                viewHolder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        mKProgressHUD.show();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "DelFavorite");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", dataBean.getService_id());
                        hashMap.put("uid", mUserid);
                        hashMap.put("type", "service");
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                mKProgressHUD.dismiss();
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    Toast.makeText(ECApplication.context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                    ((SwipeMenuLayout) viewHolder.getConvertView()).quickClose();
                               /*     dataBeanList.remove(viewHolder.getPosition());
                                    notifyDataSetChanged();*/
                                    NetWork1(mUserid, "1", "6");
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                mKProgressHUD.dismiss();
                                ((SwipeMenuLayout) viewHolder.getConvertView()).quickClose();
                                Toast.makeText(ECApplication.context, "取消收藏失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
    public void setFAAdapter(ListView view, final List<FananBean.DataBean> dataBeanList, final int aa) {
        view.setAdapter(new CommonAdapter<FananBean.DataBean>(getContext(), dataBeanList, R.layout.item_schemehallsc) {
            @Override
            public void convert(final ViewHolder viewHolder, final FananBean.DataBean dataBean, int i) {
                viewHolder.setText(R.id.schemehall_domain, dataBean.getIndus_pname()+" | "+dataBean.getIndus_name());
                viewHolder.setOnClickListener(R.id.schemehall_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), SchemeDetailActivity.class);
                        intent.putExtra("schemeid",dataBean.getService_id());
                        getActivity().startActivity(intent);
                    }
                });
                viewHolder.setText(R.id.schemehall_title, dataBean.getTitle());
                String url = dataBean.getPic();
                KLog.e("iamgeuuuuu"+url);
                if (url.equals("0")) {
                    KLog.e("uri0", url);
                }else if(url.isEmpty()){
                    KLog.e("uri空", url);
                }else{
                    KLog.e("uri", url);
                    Picasso.with(getActivity()).load(RequestAddress.IMAGE1+url).fit().into((ImageView) viewHolder.getView(R.id.schemehall_image));
                }
                String type = dataBean.getPrice();
                Double cny0 = Double.parseDouble(type); //6.20
                if (cny0 > 0.00) {
                    viewHolder.setImageResource(R.id.schemehall_image_type,R.drawable.icon_sf);
                } else {
                    viewHolder.setImageResource(R.id.schemehall_image_type,R.drawable.icon_mf);
                }
                viewHolder.getPosition();
                viewHolder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        mKProgressHUD.show();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "DelFavorite");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", dataBean.getService_id());
                        hashMap.put("uid", mUserid);
                        hashMap.put("type", "service");
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                mKProgressHUD.dismiss();
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    Toast.makeText(ECApplication.context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                    ((SwipeMenuLayout) viewHolder.getConvertView()).quickClose();
                               /*     dataBeanList.remove(viewHolder.getPosition());
                                    notifyDataSetChanged();*/
                                    NetWork2(mUserid, "1", "6");
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                mKProgressHUD.dismiss();
                                ((SwipeMenuLayout) viewHolder.getConvertView()).quickClose();
                                Toast.makeText(ECApplication.context, "取消收藏失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}
