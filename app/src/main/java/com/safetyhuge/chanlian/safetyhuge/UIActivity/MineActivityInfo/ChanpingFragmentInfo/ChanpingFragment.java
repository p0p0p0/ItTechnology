package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ChanpingFragmentInfo;

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

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mcxtzhang.commonadapter.lvgv.CommonAdapter;
import com.mcxtzhang.commonadapter.lvgv.ViewHolder;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.GoodsDaoBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.GoodsMinuteActivity;
import com.safetyhuge.chanlian.safetyhuge.adapter.GoodsDaoAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyScrollview;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/21 0021 16:18
 * 邮箱：995696826@qq.com
 */
@SuppressLint("ValidFragment")
public class ChanpingFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.scrollview_layout)
    MyScrollview mScrollviewLayout;
    /*  @Bind(R.id.recycler_view)
          RecyclerView mRecyclerView;*/
    private List<GoodsDaoBean.DataBean> mWork_info;
    private List<GoodsDaoBean.DataBean> mWork_info1;
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
    private GoodsDaoAdapter mQuanAdapter;

    public ChanpingFragment(int a) {
        this.a = a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chanpinfragment, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        mKProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中.....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        NetWork(mUserid);
        return view;
    }

    public void NetWork(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GoodsManagement");
        hashMap.put("uid", id);
        hashMap.put("mid", "6");
        KLog.e("hashMap", hashMap.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new JsonCallback<GoodsDaoBean>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(GoodsDaoBean daoBean, Call call, Response response) {
                mKProgressHUD.dismiss();
                mWork_info = daoBean.getData();
                KLog.e("mWork_info", mWork_info.size());
                mWork_info1 = new ArrayList<GoodsDaoBean.DataBean>();
                if (a == 0) {
                    //上架
                    if (mWork_info != null) {
                        KLog.e("zoule");
                        for (GoodsDaoBean.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getService_status();
                            if (str.equals("2")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        KLog.e("mWork_info1", mWork_info1.size());
                        if (mWork_info1.size() != 0) {
                            KLog.e("zoule11111111");
                            mKProgressHUD.dismiss();
                            mScrollviewLayout.setVisibility(View.VISIBLE);
                            setAdapter(mQuanbuList1, mWork_info1, 0);
                        } else {
                            mKProgressHUD.dismiss();
                            mScrollviewLayout.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无上架订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mScrollviewLayout.setVisibility(View.GONE);
                        mText111.setText("暂无上架订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //下架
                } else if (a == 1) {
                    if (mWork_info != null) {
                        for (GoodsDaoBean.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getService_status();
                            if (str.equals("3")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        KLog.e("mWork_info1", mWork_info1.size());
                        if (mWork_info1.size() != 0) {
                            mKProgressHUD.dismiss();
                            mScrollviewLayout.setVisibility(View.VISIBLE);
                            setAdapter(mQuanbuList1, mWork_info1, 1);
                        } else {
                            mKProgressHUD.dismiss();
                            mScrollviewLayout.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无下架订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mScrollviewLayout.setVisibility(View.GONE);
                        mText111.setText("暂无下架订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }

                    //审核
                } else if (a == 2) {
                    if (mWork_info != null) {
                        for (GoodsDaoBean.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getService_status();
                            if (str.equals("1")) {
                                mWork_info1.add(workInfoBean);
                                KLog.e("mWork_info1", mWork_info1);
                            }
                        }
                        KLog.e("mWork_info1", mWork_info1.size());
                        if (mWork_info1.size() != 0) {
                            mKProgressHUD.dismiss();
                            mScrollviewLayout.setVisibility(View.VISIBLE);
                            setAdapter(mQuanbuList1, mWork_info1, 2);
                        } else {
                            mKProgressHUD.dismiss();
                            mScrollviewLayout.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无待审核订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mScrollviewLayout.setVisibility(View.GONE);
                        mText111.setText("暂无待审核订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                    //拒绝
                } else if (a == 3) {
                    if (mWork_info != null) {
                        for (GoodsDaoBean.DataBean workInfoBean : mWork_info) {
                            String str = workInfoBean.getService_status();
                            if (str.equals("4")) {
                                mWork_info1.add(workInfoBean);
                            }
                        }
                        KLog.e("mWork_info1", mWork_info1.size());
                        if (mWork_info1.size() != 0) {
                            mKProgressHUD.dismiss();
                            mScrollviewLayout.setVisibility(View.VISIBLE);
                            setAdapter(mQuanbuList1, mWork_info1, 2);
                        } else {
                            mKProgressHUD.dismiss();
                            mScrollviewLayout.setVisibility(View.GONE);
                            mQuanbuImage.setVisibility(View.VISIBLE);
                            mText111.setText("暂无被拒绝订单");
                        }
                    } else {
                        mKProgressHUD.dismiss();
                        mScrollviewLayout.setVisibility(View.GONE);
                        mText111.setText("暂无被拒绝订单");
                        mQuanbuImage.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                mKProgressHUD.dismiss();
                super.onError(call, response, e);
            }
        });
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {

    }


    public void setAdapter(ListView view, List<GoodsDaoBean.DataBean> dataBeanList, final int aa) {
        view.setAdapter(new CommonAdapter<GoodsDaoBean.DataBean>(getContext(), dataBeanList, R.layout.item_goodsdg_item) {
            @Override
            public void convert(final ViewHolder viewHolder, final GoodsDaoBean.DataBean dataBean, int i) {
                viewHolder.setOnClickListener(R.id.goods_dg_click, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), GoodsMinuteActivity.class);
                        intent.putExtra("goodsid", dataBean.getService_id() + "");
                        startActivity(intent);
                    }
                });
                String pic = dataBean.getPic();
                if (pic != null && !pic.equals("")) {
                    KLog.e("whb",pic);
                    String iamgeurl = RequestAddress.IMAGE1 + dataBean.getPic();
                    Picasso.with(getContext()).load(iamgeurl).fit().into((ImageView) viewHolder.getView(R.id.goods_dg_image));
                }
                viewHolder.setText(R.id.goods_dg_title, dataBean.getTitle());
                viewHolder.setText(R.id.goods_dg_money, "￥ " + dataBean.getPrice());
                viewHolder.setText(R.id.goods_dg_count, "月销量: " + dataBean.getSale_num());
                if (a == 2 || a == 3) {
                    viewHolder.setVisible(R.id.btnDelete, false);
                    viewHolder.setVisible(R.id.btnPutaway, false);
                }
                if (a == 0) {
                    viewHolder.setText(R.id.btnPutaway, "下架");
                } else if (a == 1) {
                    viewHolder.setText(R.id.btnPutaway, "上架");
                }
                viewHolder.setOnClickListener(R.id.btnPutaway, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (a == 0) {
                            Stop(dataBean.getService_id());
                        } else if (a == 1) {
                            Start(dataBean.getService_id());
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mKProgressHUD.show();
                        ((SwipeMenuLayout) viewHolder.getConvertView()).quickClose();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "DelGooods");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", dataBean.getService_id());
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    NetWork(mUserid);
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                mKProgressHUD.dismiss();
                            }
                        });
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }

    MaterialDialog mMaterialDialog;

    public void Start(final String mSid) {
        mMaterialDialog = new MaterialDialog(getActivity())
                .setTitle("提示")
                .setMessage("你确定上架产品吗?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mKProgressHUD.show();
                        mMaterialDialog.dismiss();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "Grounding");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", mSid);
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    KLog.e("whb", code);
                                    NetWork(mUserid);
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });

        mMaterialDialog.show();
    }

    public void Stop(final String mSid) {
        mMaterialDialog = new MaterialDialog(getActivity())
                .setTitle("提示")
                .setMessage("你确定下架产品吗?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mKProgressHUD.show();
                        mMaterialDialog.dismiss();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("action", "Undercarriage");
                        hashMap.put("uid", mUserid);
                        hashMap.put("sid", mSid);
                        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                mKProgressHUD.dismiss();
                                KLog.json(s);
                                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                                String code = (String) mapForJson.get("code");
                                if (code.equals("888")) {
                                    NetWork(mUserid);
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });

        mMaterialDialog.show();
    }
}