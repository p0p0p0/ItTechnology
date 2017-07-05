package com.safetyhuge.chanlian.safetyhuge.fragment.shoop;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.GoodsCartBean.AmendCart;
import com.safetyhuge.chanlian.safetyhuge.fragment.shoop.GoodsCartBean.LookCart;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;


/**
 * 购物车数据适配器
 */
public class ShopcartAdapter extends BaseExpandableListAdapter {

    //    private List<StoreInfo>              groups;
    //    private Map<String, List<GoodsInfo>> children;
    private Context              context;
    private CheckInterface       checkInterface;
    private ModifyCountInterface modifyCountInterface;
    public int flag = 0;
    private GroupEdtorListener mListener;
    private HttpParams mParams;
    private JSONObject         mObject;
    //添加购物车
    String mUrl = "http://192.168.1.216/appdata.php?";
    private StringBuilder mBuilder;
    private StringBuilder mBuilder1;
    private String        mUserid;

    private List<LookCart.DataBean>                   shops        = new ArrayList<>();      //全部一级数据
    private List<List<LookCart.DataBean.DetailsBean>> productsList = new ArrayList<>(); //全部二级集合

    public ShopcartAdapter(List<LookCart.DataBean> mDataBeen, Context context) {
        this.context = context;
        //        shops.addAll(mDataBeen);
        this.shops = mDataBeen;
        for (LookCart.DataBean shopBean : mDataBeen) {   //遍历集合，获取全部二级集合对象并存储
            productsList.add(shopBean.getDetails());
        }
    }

    public GroupEdtorListener getmListener() {
        return mListener;
    }

    public void setmListener(GroupEdtorListener mListener) {
        this.mListener = mListener;
    }


    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        KLog.e("shops",shops.size());
        return shops.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {    //统计二级集合中每个集合对象包含具体商品对象的总数量
        return productsList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return shops.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return productsList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final GroupViewHolder gholder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_shopcart_group, null);
            gholder = new GroupViewHolder(convertView);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
        // final StoreInfo group = (StoreInfo) getGroup(groupPosition);
        final LookCart.DataBean shopBean = shops.get(groupPosition);
        gholder.tvSourceName.setText(shopBean.getShop_name());
        KLog.e("getShop_name",shopBean.getShop_name());
        gholder.determineChekbox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                shopBean.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
            }
        });
        gholder.determineChekbox.setChecked(shopBean.isChoosed());
        if (shopBean.isEdtor()) {
            gholder.tvStoreEdtor.setText("完成");
        } else {
            gholder.tvStoreEdtor.setText("编辑");
        }
        gholder.tvStoreEdtor.setOnClickListener(new GroupViewClick(groupPosition, gholder.tvStoreEdtor, shopBean));
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild,
                             View convertView, final ViewGroup parent) {

        final ChildViewHolder cholder;
        if (convertView == null) {
            convertView = View.inflate(ECApplication.context, R.layout.item_shopcart_product, null);
            // if(isLastChild&&getChild(groupPosition,childPosition)!=null){
            //     View    v = View.inflate(context, R.layout.child_footer,null);
            //     TextView txtFooter = (TextView)v.findViewById(R.id.txtFooter);
            //     txtFooter.setText("店铺满99元包邮");
            //     if(convertView instanceof ViewGroup){
            //         ((ViewGroup) convertView).addView(v);
            //     }
            // }

            cholder = new ChildViewHolder(convertView);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) convertView.getTag();
        }

        if (shops.get(groupPosition).isEdtor() == true) {
            cholder.llEdtor.setVisibility(View.VISIBLE);
            cholder.rlNoEdtor.setVisibility(View.GONE);
        } else {
            cholder.llEdtor.setVisibility(View.GONE);
            cholder.rlNoEdtor.setVisibility(View.VISIBLE);
        }
        //        final GoodsInfo mGoodsInfo = (GoodsInfo) getChild(groupPosition, childPosition);
        final LookCart.DataBean.DetailsBean mGoodsInfo = productsList.get(groupPosition).get(childPosition);


       /* if(isLastChild&&getChild(groupPosition,childPosition)!=null){
            cholder.stub.setVisibility(View.VISIBLE);
            //  TextView tv= (TextView) cholder.stub.findViewById(R.id.txtFooter);//这里用来动态显示店铺满99元包邮文字内容
        }else{
            cholder.stub.setVisibility(View.GONE);
        }*/
        if (mGoodsInfo != null) {
            cholder.tvIntro.setText(mGoodsInfo.getTitle());              // 品名 TODO objName?? title??
            cholder.tvPrice.setText("￥" + mGoodsInfo.getPrice() + "");     //价格
            cholder.tvNum.setText(String.valueOf(mGoodsInfo.getAmount()));  //数量
            String iamgeurl = RequestAddress.IMAGE1 + "/" + mGoodsInfo.getPic();    //图片地址
            //截取#之前的字符串
            String str = iamgeurl;
            if (str.indexOf(",") != -1) {
                String b = str.substring(0, str.indexOf(","));
                KLog.e("indexof", str.substring(0, str.indexOf(",")));
                Picasso.with(context).load(b).fit().into(cholder.ivAdapterListPic);
            } else {
                System.out.println(str);
                Picasso.with(context).load(str).fit().into(cholder.ivAdapterListPic);
            }
            cholder.tvDiscountPrice.setText(mGoodsInfo.getObj_id());    //商品id
            cholder.tvBuyNum.setText("x" + mGoodsInfo.getAmount());
            cholder.checkBox.setChecked(mGoodsInfo.isChoosed());
            cholder.checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGoodsInfo.setChoosed(((CheckBox) v).isChecked());
                    cholder.checkBox.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                    String str = mGoodsInfo.getObj_id() + ",";
                    Log.e("srt", str);
                    mBuilder = new StringBuilder();
                    mBuilder.append(str);
                    //Toast.makeText(context,str, Toast.LENGTH_SHORT).show();
                }
            });
            //增加
            cholder.tvAdd.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.tvNum, cholder.checkBox
                            .isChecked());// 暴露增加接口
                    //  AmendCart(2, NetWorkParameter(2, "update_cart","1",mGoodsInfo.getId(),null));
                }
            });
            //减少
            cholder.tvReduce.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.tvNum, cholder.checkBox
                            .isChecked());// 暴露删减接口
                    //  AmendCart(2, NetWorkParameter(2, "update_cart","2",mGoodsInfo.getId(),null));
                }
            });
            mUserid = (String) SharedPrefsUtil.get(context, "UserUid", "");
            //删除 购物车
            cholder.tvGoodsDelete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alert = new AlertDialog.Builder(context).create();
                    alert.setTitle("操作提示");
                    alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String str = mGoodsInfo.getObj_id() + ",";
                            Log.e("srt", str);
                            mBuilder = new StringBuilder();
                            mBuilder.append(str);
                            Log.e("length", mBuilder.length() + "");
                            Log.e("aa", mBuilder.toString());
                            AmendCart(1, NetWorkParameter(1, "DelDesired", "", mGoodsInfo.getObj_id(), mBuilder),groupPosition,childPosition);
                        }
                    });
                    alert.show();
                }
            });
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;

    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删除子item
         */
        void childDelete(int groupPosition, int childPosition);
    }

    /**
     * 监听编辑状态
     */
    public interface GroupEdtorListener {
        void groupEdit(int groupPosition);
    }

    /**
     * 使某个组处于编辑状态
     * <p/>
     * groupPosition组的位置
     */
    class GroupViewClick implements OnClickListener {
        private int               groupPosition;
        private Button            edtor;
        private LookCart.DataBean shopBean;

        public GroupViewClick(int groupPosition, Button edtor, LookCart.DataBean shopBean) {
            this.groupPosition = groupPosition;
            this.edtor = edtor;
            this.shopBean = shopBean;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == edtor.getId()) {
                if (shopBean.isEdtor()) {
                    shopBean.setIsEdtor(false);
                } else {
                    shopBean.setIsEdtor(true);

                }
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 组元素绑定器
     */
    static class GroupViewHolder {
        @Bind( R.id.determine_chekbox )
        CheckBox determineChekbox;
        @Bind( R.id.tv_source_name )
        TextView tvSourceName;
        @Bind( R.id.tv_store_edtor )
        Button   tvStoreEdtor;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 子元素绑定器
     */
    static class ChildViewHolder {
        @Bind( R.id.check_box )
        CheckBox       checkBox;
        @Bind( R.id.iv_adapter_list_pic )
        ImageView      ivAdapterListPic;
        @Bind( R.id.tv_intro )
        TextView       tvIntro;
        @Bind( R.id.tv_color_size )
        TextView       tvColorSize;
        @Bind( R.id.tv_price )
        TextView       tvPrice;
        @Bind( R.id.tv_discount_price )
        TextView       tvDiscountPrice;
        @Bind( R.id.tv_buy_num )
        TextView       tvBuyNum;
        @Bind( R.id.rl_no_edtor )
        RelativeLayout rlNoEdtor;
        @Bind( R.id.tv_reduce )
        TextView       tvReduce;
        @Bind( R.id.tv_num )
        TextView       tvNum;
        @Bind( R.id.tv_add )
        TextView       tvAdd;
        @Bind( R.id.ll_change_num )
        LinearLayout   llChangeNum;
        @Bind( R.id.tv_colorsize )
        TextView       tvColorsize;
        @Bind( R.id.tv_goods_delete )
        TextView       tvGoodsDelete;
        @Bind( R.id.ll_edtor )
        LinearLayout   llEdtor;

        /*  @BindView(R.id.stub)
          ViewStub stub;*/
        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public HttpParams NetWorkParameter(int i, String pm, String step, String id, StringBuilder builder) {
        mObject = new JSONObject();
        mParams = new HttpParams();
        try {
            switch (i) {
                case 1:
                    //删除
                    mBuilder1 = builder.deleteCharAt(builder.length() - 1);
                    mParams.put("action", pm);
                    mParams.put("uid", mUserid);
                    mParams.put("sid", mBuilder1.toString());
                    Log.e("tag1", mParams.toString());
                    Log.e("tag2", mObject.toString());
                    break;
                case 2:
                    //修改
                    mObject.put("user_id", "1111");//
                    mObject.put("goods_id", id);
                    mObject.put("step", step);
                    mParams.put("act", pm);
                    mParams.put("goods", mObject.toString());
                    Log.e("tag1", mParams.toString());
                    Log.e("tag2", mObject.toString());
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mParams;

    }

    public void AmendCart(int i, HttpParams httpParams, final int groupPosition, final int childPosition) {
        switch (i) {
            case 1:
                //删除
                OkGo.post(RequestAddress.HOST + RequestAddress.GOODS).params(httpParams).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        KLog.json("ssss", s);
                        Map<String, Object> map = getMapForJson(s);
                        String code = (String) map.get("code");
                        String error = (String) map.get("error");
                        if (code.equals("888")) {
                            Toast.makeText(context, "删除商品成功", Toast.LENGTH_SHORT).show();
                            modifyCountInterface.childDelete(groupPosition, childPosition);
                        } else {
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                        mBuilder.delete(0, mBuilder1.length());
                    }
                });
                break;
            case 2:
                //修改
                OkGo.post(mUrl).params(httpParams).execute(new JsonCallback<AmendCart>() {
                    @Override
                    public void onSuccess(AmendCart data4, Call call, Response response) {
                        if (data4.getData().isEmpty()) {
                            Toast.makeText(context, "没了", Toast.LENGTH_SHORT).show();
                        } else if (data4.getMsg().equals("库存不足")) {
                            Toast.makeText(context, "库存不足", Toast.LENGTH_SHORT).show();
                        } else if (data4.getMsg().equals("success")) {
                            Toast.makeText(context, data4.getData().get(0).getGoods_number() + "", Toast
                                    .LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
