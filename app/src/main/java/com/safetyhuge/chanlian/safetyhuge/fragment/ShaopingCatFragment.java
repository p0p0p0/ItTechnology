package com.safetyhuge.chanlian.safetyhuge.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.IndentActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.GoodsCartBean.LookCart;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.ShopcartAdapter;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.ECApplication.context;
import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;

/**
 * 徐艳昭
 * 15326057231@163.com
 * 2017/3/6
 **/
public class ShaopingCatFragment extends Fragment implements ShopcartAdapter.CheckInterface, ShopcartAdapter
        .ModifyCountInterface, ShopcartAdapter.GroupEdtorListener {
    /* @Bind(R.id.back)
     ImageView back;*/
    @Bind(R.id.title_goods)
    TextView title;
    @Bind(R.id.subtitle)
    TextView subtitle;
    @Bind(R.id.top_bar)
    LinearLayout topBar;
    @Bind(R.id.elv_shoppingCar)
    ExpandableListView exListView;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.all_chekbox)
    CheckBox allChekbox;
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.tv_go_to_pay)
    TextView tvGoToPay;

    @Bind(R.id.ll_shar)
    LinearLayout llShar;
    @Bind(R.id.ll_info)
    LinearLayout llInfo;
    @Bind(R.id.ll_info1)
    LinearLayout llInfo1;
    @Bind(R.id.tv_share)
    TextView tvShare;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.ll_cart)
    LinearLayout ll_shoppingCar;
    @Bind(R.id.gouwuche_kong)
    LinearLayout mGouwucheKong;

    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    //    private             List<StoreInfo>                  groups     = new ArrayList<StoreInfo>();// 组元素数据列表
    //    private             List<StoreInfo>                  groups1    = new ArrayList<StoreInfo>();// 组元素数据列表
    //    private             TreeMap<String, List<GoodsInfo>> children   = new TreeMap<String, List<GoodsInfo>>();//
    // 子元素数据列表
    //    private             TreeMap<String, List<GoodsInfo>> children1  = new TreeMap<String, List<GoodsInfo>>();//
    // 子元素数据列表
    private int flag = 0;
    public static final String HOST1 = "http://192.168.1.216/appdata.php?";

    private String mUserid;
    private Context mContext;
    private ShopcartAdapter elvAdapter;
    private List<LookCart.DataBean> mDataBeen;

    private List<LookCart.DataBean> shops = new ArrayList<>();      //全部一级数据
    private List<List<LookCart.DataBean.DetailsBean>> productsList = new ArrayList<>(); //全部二级集合
    private StringBuilder mBuilder,mBuilder1,mBuilderId,mBuilderSId;
    private boolean mFlag = true;
    private KProgressHUD mProgressHUD;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(context, R.layout.activity_goods, null);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mUserid = (String) SharedPrefsUtil.get(mContext, "UserUid", "");
        mProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f).show();
        return view;
    }

    /**
     * 初始化elv，设置适配器，设置监听及展开状态
     *
     * @param mDataBeen 用户购买的全部商家及其商品数据
     */
    private void initExpandableListView(List<LookCart.DataBean> mDataBeen) {
        elvAdapter = new ShopcartAdapter(mDataBeen, getActivity());
        elvAdapter.setCheckInterface(this);// 关键步骤1,设置复选框接口
        elvAdapter.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        elvAdapter.setmListener(this);
        exListView.setAdapter(elvAdapter);
        for (int i = 0; i < elvAdapter.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }

        //初始化两个数据集合
        shops.addAll(mDataBeen);

        for (LookCart.DataBean shopBean : mDataBeen) {   //遍历集合，获取全部二级集合对象并存储
            productsList.add(shopBean.getDetails());
        }
    }

    //    /**
    //     * 设置购物车产品数量
    //     */
    private void setCartNum() {
        KLog.e("setCartNum", shops.size() + "");
        int count = 0;
        for (int i = 0; i < shops.size(); i++) {
            shops.get(i).setChoosed(allChekbox.isChecked());
            List<LookCart.DataBean.DetailsBean> details = shops.get(i).getDetails();
            for (LookCart.DataBean.DetailsBean detail : details) {
                count += 1;
            }
             /* List<GoodsInfo> childs = dataBean.get(group.getId());
              for (GoodsInfo goodsInfo : childs) {
               }*/
        }

        //购物车已清空
        if (count == 0) {
            clearCart();
        } else {
            title.setText("购物车" + "(" + count + ")");
        }
    }

    private void clearCart() {
        title.setText("购物车" + "(" + 0 + ")");
        subtitle.setVisibility(View.GONE);
        ll_shoppingCar.setVisibility(View.GONE);
        mGouwucheKong.setVisibility(View.VISIBLE);
        ll_shoppingCar.setVisibility(View.GONE);
    }

    /**
     * 遍历集合，如果被选中，则执行删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        //        List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>();// 待删除的组元素列表
        //        for (int i = 0; i < groups.size(); i++) {
        //            StoreInfo group = groups.get(i);
        //            if (group.isChoosed()) {
        //                toBeDeleteGroups.add(group);
        //            }
        //            List<GoodsInfo> toBeDeleteProducts = new ArrayList<GoodsInfo>();// 待删除的子元素列表
        //            List<GoodsInfo> childs = children.get(group.getId());
        //            for (int j = 0; j < childs.size(); j++) {
        //                if (childs.get(j).isChoosed()) {
        //                    toBeDeleteProducts.add(childs.get(j));
        //                }
        //            }
        //            childs.removeAll(toBeDeleteProducts);
        //        }
        //        groups.removeAll(toBeDeleteGroups);
        //记得重新设置购物车
        //setCartNum();

        //先判断shop 是否被选中，如果被选中，直接删除组，并删除对应的组中的商品集合；如果shop未被选中，则去删除其中的单个商品
        //        private List<LookCart.DataBean>                   shops        = new ArrayList<>();      //全部一级数据
        //        private List<List<LookCart.DataBean.DetailsBean>> productsList = new ArrayList<>(); //全部二级集合

        ListIterator shopsIterator = shops.listIterator();
        while (shopsIterator.hasNext()) {
            int index = shopsIterator.nextIndex();   //获取索引
            LookCart.DataBean shop = (LookCart.DataBean) shopsIterator.next();
            if (shop.isChoosed()) {     //如果商店被选中，移除商店及对应的商品
                shopsIterator.remove();
                productsList.remove(index);
            } else {        //如果没有选中店铺，遍历具体的商品
                List<LookCart.DataBean.DetailsBean> products = productsList.get(index);
                ListIterator productIterator = products.listIterator();
                while (productIterator.hasNext()) {
                    LookCart.DataBean.DetailsBean product = (LookCart.DataBean.DetailsBean) productIterator.next();
                    if (product.isChoosed()) {
                        productIterator.remove();
                    }
                }
                if (0 == products.size()) {    //如果商品空了
                    productsList.remove(products);
                    shopsIterator.remove();
                }
            }
        }
        shops.removeAll(productsList);
        elvAdapter.notifyDataSetChanged();
        //  setCartNum();
        //clearCart();
        calculate();
    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        LookCart.DataBean.DetailsBean product = ( LookCart.DataBean.DetailsBean) elvAdapter.getChild(groupPosition, childPosition);
        String currentCount = product.getAmount();
        int Count = Integer.valueOf(currentCount);
        Count++;
        product.setAmount(Count+"");
        ((TextView) showCountView).setText(currentCount + "");
        elvAdapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        LookCart.DataBean.DetailsBean product = ( LookCart.DataBean.DetailsBean) elvAdapter.getChild(groupPosition, childPosition);
        String currentCount = product.getAmount();
        int Count = Integer.valueOf(currentCount);
        if (Count == 1) {
            return;
        }
        Count--;
        product.setAmount(Count+"");
        ((TextView) showCountView).setText(currentCount + "");
        elvAdapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
        //删除商品，（如果商品全删了，则删除对应的集合）
        //        children.get(groups.get(groupPosition).getId()).remove(childPosition);
        //        StoreInfo group = groups.get(groupPosition);
        //        List<GoodsInfo> childs = children.get(group.getId());
        //        if (childs.size() == 0) {
        //            groups.remove(groupPosition);
        //        }
    /*    List<LookCart.DataBean.DetailsBean> products = productsList.get(groupPosition); //获取当前商品所在的集合
        if (products.size() > 0) {
            KLog.e("childPosition");
            products.remove(childPosition);
        }

        //移除完毕之后，再判断一次集合大小
        if (products.size() == 0) {
            KLog.e("productsList");
            KLog.e("groupPosition");
            productsList.remove(groupPosition);
            shops.remove(groupPosition);
        }

        //     handler.sendEmptyMessage(0);
        calculate();*/
        elvAdapter.notifyDataSetInvalidated();
        mProgressHUD.show();
        //goodsDel(mUserid,mBuilder);
        NetWork(mUserid);

    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        //        StoreInfo group = groups.get(groupPosition);
        //        List<GoodsInfo> childs = children.get(group.getId());
        //                for (int i = 0; i < childs.size(); i++) {
        //                    childs.get(i).setChoosed(isChecked);
        //                }
        for (LookCart.DataBean.DetailsBean product : productsList.get(groupPosition)) {
            product.setChoosed(isChecked);
        }

        if (isAllCheck()) {
            allChekbox.setChecked(true);
        } else {
            allChekbox.setChecked(false);
        }
        elvAdapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosiTion, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        //StoreInfo group = groups.get(groupPosition);
        //List<GoodsInfo> childs = children.get(group.getId());
        List<LookCart.DataBean.DetailsBean> products = productsList.get(groupPosition);
        LookCart.DataBean shop = shops.get(groupPosition);
        for (int i = 0; i < products.size(); i++) {
            // 不全选中
            if (products.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            shop.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            shop.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {
            allChekbox.setChecked(true);// 全选
        } else {
            allChekbox.setChecked(false);// 反选
        }
        elvAdapter.notifyDataSetChanged();
        calculate();

    }

    /**
     * 是否结算某个店铺中的全部
     */
    private boolean isAllCheck() {
        for (LookCart.DataBean shop : shops) {
            if (!shop.isChoosed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < shops.size(); i++) {
            shops.get(i).setChoosed(allChekbox.isChecked());
            List<LookCart.DataBean.DetailsBean> products = productsList.get(i);   //处理该商铺中全部商品的选中状态
            for (LookCart.DataBean.DetailsBean product : products) {
                product.setChoosed(allChekbox.isChecked());
            }
        }
        elvAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计要结算的商品总量和总价
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        mBuilder = new StringBuilder();
        mBuilder1 = new StringBuilder();
        mBuilderId = new StringBuilder();
        mBuilderSId = new StringBuilder();
        for (List<LookCart.DataBean.DetailsBean> products : productsList) {
            for (LookCart.DataBean.DetailsBean product : products) {
                if (product.isChoosed()) {
                    totalCount++;
                    KLog.e("product.getPrice()", product.getPrice());
                    KLog.e("product.getNum()()", product.getAmount());
                    String amount = product.getAmount();
                    int count = Integer.valueOf(amount);
                    KLog.e("shanchu", product.getService_id());
                    mBuilder.append(product.getService_id() + ",");
                    mBuilder1.append(product.getAmount()+",");
                    mBuilderId.append(product.getD_id()+",");
                    mBuilderSId.append(product.getShop_id()+",");
                    KLog.e("getShop_id",product.getShop_id());
                    totalPrice += product.getPrice() * count;
                }
            }
        }

        tvTotalPrice.setText("￥" + totalPrice);
        tvGoToPay.setText("去支付(" + totalCount + ")");
        if (totalCount == 0) {
            setCartNum();
        } else {
            title.setText("购物车" + "(" + totalCount + ")");
        }
    }

    @OnClick({R.id.all_chekbox, R.id.tv_delete, R.id.tv_go_to_pay, R.id.subtitle, R.id.tv_save, R.id.tv_share})
    public void onClick(View view) {
        AlertDialog alert;
        switch (view.getId()) {
            case R.id.all_chekbox:
                doCheckAll();
                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(getActivity(), "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goodsDel(mUserid, mBuilder);
                        KLog.e(":mBuilder", mBuilder.toString());
                    }
                });
                alert.show();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount == 0) {
                    Toast.makeText(getActivity(), "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle("操作提示");
                alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //遍历集合，获取被选中的数据，传递给结算 页面
                        List<LookCart.DataBean> checkoutShops = new ArrayList<>();      //有商品要结算的店铺
                        List<List<LookCart.DataBean.DetailsBean>> checkoutProductsList = new ArrayList<>(); //总的结算集合
                        for (int i = 0; i < productsList.size(); i++) { //遍历每个商铺的商品集合
                            List<LookCart.DataBean.DetailsBean> products = productsList.get(i);
                            List<LookCart.DataBean.DetailsBean> checkoutProducts = new ArrayList<>();
                            for (LookCart.DataBean.DetailsBean product : products) {
                                if (product.isChoosed()) {
                                    checkoutProducts.add(product);  //把需要结算的商品加入集合
                                }
                            }

                            if (checkoutProducts.size() > 0) {
                                checkoutProductsList.add(checkoutProducts); //将包含要结算商品的各个集合加入总的结算集合
                                checkoutShops.add(shops.get(i));
                            }
                        }
                        Intent intent = new Intent(ECApplication.context, IndentActivity.class);
                        intent.putExtra("checkoutShops", (Serializable) checkoutShops);
                        intent.putExtra("checkoutProductsList", (Serializable) checkoutProductsList);
                        intent.putExtra("money", totalPrice + "");
                        intent.putExtra("mBuilder1", mBuilder1 + "");
                        intent.putExtra("mBuilderId", mBuilderId + "");
                        intent.putExtra("mBuilderSId", mBuilderSId + "");
                        intent.putExtra("flag", "0");
                        mContext.startActivity(intent);
                    }
                });
                alert.show();

                break;
            case R.id.subtitle:
                if (flag == 0) {
                    llInfo.setVisibility(View.GONE);
                    tvGoToPay.setVisibility(View.GONE);
                    llShar.setVisibility(View.VISIBLE);
                    subtitle.setText("完成");
                } else if (flag == 1) {
                    llInfo.setVisibility(View.VISIBLE);
                    tvGoToPay.setVisibility(View.VISIBLE);
                    llShar.setVisibility(View.GONE);
                    subtitle.setText("编辑");
                }
                flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
                break;
            case R.id.tv_share:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要分享的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_save:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要收藏的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
        }
    }


    @Override
    public void groupEdit(int groupPosition) {
        shops.get(groupPosition).setIsEdtor(true);
        elvAdapter.notifyDataSetChanged();
    }

    //    Handler handler = new Handler() {
    //        @Override
    //        public void handleMessage(Message msg) {
    //            super.handleMessage(msg);
    //            //删除购物车后动态改变数量
    //            setCartNum();
    //        }
    //    };

    /**
     * 请求网络，获取数据
     *
     * @param uid 当前登录用户id
     */
    public void NetWork(String uid) {
        HttpParams mParams = new HttpParams();
        mParams.put("action", "GetDesiredInfo");
        mParams.put("uid", uid);
        //查看
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(mParams).execute(new JsonCallback<LookCart>() {
            @Override
            public void onSuccess(LookCart lookCart, Call call, Response response) {
                shops.clear();
                productsList.clear();
                mProgressHUD.dismiss();
                mDataBeen = lookCart.getData();
                if (mDataBeen != null && mDataBeen.size() > 0) {    //数据不为空时展示数据
                    mGouwucheKong.setVisibility(View.GONE);
                    ll_shoppingCar.setVisibility(View.VISIBLE);
                    subtitle.setVisibility(View.VISIBLE);
                    //mDataBean 中包含所有的商家对象，其中的details 是每个商家用户加入购物车的商品
                    llInfo1.setVisibility(View.VISIBLE);
                    initExpandableListView(mDataBeen);
                } else {
                    subtitle.setVisibility(View.GONE);
                    mGouwucheKong.setVisibility(View.VISIBLE);
                    ll_shoppingCar.setVisibility(View.GONE);
                    title.setText("购物车");
                }
            }
        });
    }

    /**
     * 删除商品
     *
     * @param uid
     * @param sid
     */
    public void goodsDel(String uid, StringBuilder sid) {
        HashMap<String, String> hashMap = new HashMap<>();
        sid = sid.deleteCharAt(sid.length() - 1);
        hashMap.put("action", "DelDesired");
        hashMap.put("uid", uid);
        hashMap.put("sid", sid.toString());
        OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Map<String, Object> map = getMapForJson(s);
                String code = (String) map.get("code");
                String error = (String) map.get("error");
                if (code.equals("888")) {
                    Toast.makeText(context, "删除商品成功", Toast.LENGTH_SHORT).show();
                    mProgressHUD.show();
                    NetWork(mUserid);
                } else {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onResume() {
        super.onResume();
        tvGoToPay.setText("去支付(" + 0 + ")");
        title.setText("购物车" + "(" + 0 + ")");
        tvTotalPrice.setText("￥0.0");
        elvAdapter = null;
        totalPrice = 0;
        totalCount = 0;
        mUserid = (String) SharedPrefsUtil.get(context, "UserUid", "");
        MobclickAgent.onResume(getActivity());
        NetWork(mUserid);
        allChekbox.setChecked(false);
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

}