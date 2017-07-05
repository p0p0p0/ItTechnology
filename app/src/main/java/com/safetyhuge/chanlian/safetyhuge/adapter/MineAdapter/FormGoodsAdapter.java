package com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.FormGoodsBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.PayActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ChakanGoodsActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.PingjiaActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/24 0024 15:45
 * 邮箱：995696826@qq.com
 */
public class FormGoodsAdapter extends BaseAdapter {
    Activity context;
    List<FormGoodsBean.DataBean> work_info;
    EditDialogCallBack callBack;;
    int flag;
    private String mGoodsId;
    private String mUserid;
    private MaterialDialog mDialog;

    public FormGoodsAdapter(Activity context, List<FormGoodsBean.DataBean> work_info, int flag) {
        this.context = context;
        this.work_info = work_info;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return work_info.size();
    }

    @Override
    public Object getItem(int position) {
        return work_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_formgoods, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (!work_info.get(position).getPic().isEmpty()) {
            String iamgeurl =  RequestAddress.IMAGE1 + work_info.get(position).getPic();
            //截取#之前的字符串
            String str = iamgeurl;
            if (str.indexOf(",") != -1) {
                String b = str.substring(0, str.indexOf(","));
                KLog.e("indexof", str.substring(0, str.indexOf(",")));
                Picasso.with(context).load(b).into(viewHolder.mFormgoodsIamge);
            } else {
                System.out.println(str);
                Picasso.with(context).load(str).into(viewHolder.mFormgoodsIamge);
            }
        } else {
            KLog.e("空白");
            viewHolder.mFormgoodsIamge.setImageResource(R.drawable.img_pru7);
        }
        viewHolder.mFormgoodsName.setText("卖家: " + work_info.get(position).getSeller_info().getUsername());
        String money = work_info.get(position).getPrice();
        if (money.isEmpty()) {
            String money1 = work_info.get(position).getOrder_amount();
            KLog.e("钱数");
            viewHolder.mFormgoodsJiage.setText("共计" + work_info.get(position).getNum() + "产品" + " 合计 " + money1 + " 元");

        } else {
            viewHolder.mFormgoodsJiage.setText("共计" + work_info.get(position).getNum() + "产品" + " 合计" + work_info.get(position).getPrice() + "元");
        }
        viewHolder.mFormgoodsTitle.setText(work_info.get(position).getOrder_name());
        if (flag == 0) {
            String order_status = work_info.get(position).getOrder_status();
            Object show_status = work_info.get(position).getMark_status();
            if (!order_status.equals("wait")) {
                if (!order_status.equals("confirm")) {
                    if (!order_status.equals("arbitral")) {
                        if (!order_status.equals("ok")) {
                        } else {
                            viewHolder.mFormgoodsZhuangtai.setText("已付款");
                            viewHolder.mFormgoodsShouhuo.setVisibility(View.VISIBLE);
                            viewHolder.mFormgoodsPingjia.setVisibility(View.GONE);
                            viewHolder.mFormgoodsFukuan.setVisibility(View.GONE);
                            //查看订单
                            viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context,ChakanGoodsActivity.class);
                                    intent.putExtra("title",work_info.get(position).getOrder_name());
                                    intent.putExtra("flag","待收货");
                                    intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                                    intent.putExtra("num", work_info.get(position).getNum());
                                    intent.putExtra("money",work_info.get(position).getPrice());
                                    intent.putExtra("money1",work_info.get(position).getOrder_amount());
                                    intent.putExtra("time",work_info.get(position).getOrder_time());
                                    intent.putExtra("userid",mUserid);
                                    intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                                    intent.putExtra("image",work_info.get(position).getPic());
                                    intent.putExtra("mposition",position);
                                    context.startActivity(intent);
                                }
                            });
                        }
                    } else {
                        viewHolder.mFormgoodsZhuangtai.setText("交易仲裁");
                        viewHolder.mFormgoodsPingjia.setVisibility(View.GONE);
                        viewHolder.mFormgoodsFukuan.setVisibility(View.GONE);
                        viewHolder.mFormgoodsShouhuo.setVisibility(View.GONE);
                        //查看订单
                        viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context,ChakanGoodsActivity.class);
                                intent.putExtra("title",work_info.get(position).getOrder_name());
                                intent.putExtra("flag","交易仲裁");
                                intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                                intent.putExtra("num", work_info.get(position).getNum());
                                intent.putExtra("money",work_info.get(position).getPrice());
                                intent.putExtra("money1",work_info.get(position).getOrder_amount());
                                intent.putExtra("time",work_info.get(position).getOrder_time());
                                intent.putExtra("userid",mUserid);
                                intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                                intent.putExtra("image",work_info.get(position).getPic());
                                intent.putExtra("mposition",position);
                                context.startActivity(intent);
                            }
                        });
                    }
                } else {
                    if (show_status != null) {
                        if (!show_status.equals("0")) {
                            KLog.e("交易完成");
                            viewHolder.mFormgoodsZhuangtai.setText("交易完成");
                            viewHolder.mFormgoodsPingjia.setVisibility(View.GONE);
                            viewHolder.mFormgoodsFukuan.setVisibility(View.GONE);
                            viewHolder.mFormgoodsShouhuo.setVisibility(View.GONE);
                            //查看订单
                            viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context,ChakanGoodsActivity.class);
                                    intent.putExtra("title",work_info.get(position).getOrder_name());
                                    intent.putExtra("flag","交易完成");
                                    intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                                    intent.putExtra("num", work_info.get(position).getNum());
                                    intent.putExtra("money",work_info.get(position).getPrice());
                                    intent.putExtra("money1",work_info.get(position).getOrder_amount());
                                    intent.putExtra("time",work_info.get(position).getOrder_time());
                                    intent.putExtra("userid",mUserid);
                                    intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                                    intent.putExtra("image",work_info.get(position).getPic());
                                    intent.putExtra("mposition",position);
                                    context.startActivity(intent);
                                }
                            });
                        } else {
                            viewHolder.mFormgoodsZhuangtai.setText("待评价");
                            viewHolder.mFormgoodsPingjia.setVisibility(View.VISIBLE);
                            viewHolder.mFormgoodsFukuan.setVisibility(View.GONE);
                            viewHolder.mFormgoodsShouhuo.setVisibility(View.GONE);
                            //评价
                            viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context,ChakanGoodsActivity.class);
                                    intent.putExtra("title",work_info.get(position).getOrder_name());
                                    intent.putExtra("flag","待评价");
                                    intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                                    intent.putExtra("num", work_info.get(position).getNum());
                                    intent.putExtra("money",work_info.get(position).getPrice());
                                    intent.putExtra("money1",work_info.get(position).getOrder_amount());
                                    intent.putExtra("time",work_info.get(position).getOrder_time());
                                    intent.putExtra("userid",mUserid);
                                    intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                                    intent.putExtra("image",work_info.get(position).getPic());
                                    intent.putExtra("mposition",position);
                                    context.startActivity(intent);
                                }
                            });
                        }
                    }
                }
            } else {
                viewHolder.mFormgoodsZhuangtai.setText("待付款");
                viewHolder.mFormgoodsFukuan.setVisibility(View.VISIBLE);
                viewHolder.mFormgoodsShouhuo.setVisibility(View.GONE);
                viewHolder.mFormgoodsPingjia.setVisibility(View.GONE);
                //付款
                viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,ChakanGoodsActivity.class);
                        intent.putExtra("title",work_info.get(position).getOrder_name());
                        intent.putExtra("flag","待付款");
                        intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                        intent.putExtra("num", work_info.get(position).getNum());
                        intent.putExtra("money",work_info.get(position).getPrice());
                        intent.putExtra("money1",work_info.get(position).getOrder_amount());
                        intent.putExtra("time",work_info.get(position).getOrder_time());
                        intent.putExtra("userid",mUserid);
                        intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                        intent.putExtra("image",work_info.get(position).getPic());
                        intent.putExtra("mposition",position);
                        context.startActivity(intent);
                    }
                });
            }
        } else if (flag == 1) {
            viewHolder.mFormgoodsZhuangtai.setText("待付款");
            viewHolder.mFormgoodsFukuan.setVisibility(View.VISIBLE);
            viewHolder.mFormgoodsShouhuo.setVisibility(View.GONE);
            viewHolder.mFormgoodsPingjia.setVisibility(View.GONE);
            //付款
            viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ChakanGoodsActivity.class);
                    intent.putExtra("title",work_info.get(position).getOrder_name());
                    intent.putExtra("flag","待付款");
                    intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                    intent.putExtra("num", work_info.get(position).getNum());
                    intent.putExtra("money",work_info.get(position).getPrice());
                    intent.putExtra("money1",work_info.get(position).getOrder_amount());
                    intent.putExtra("time",work_info.get(position).getOrder_time());
                    intent.putExtra("userid",mUserid);
                    intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                    intent.putExtra("image",work_info.get(position).getPic());
                    intent.putExtra("mposition",position);

                    context.startActivity(intent);
                }
            });
        } else if (flag == 2) {
            viewHolder.mFormgoodsZhuangtai.setText("已付款");
            viewHolder.mFormgoodsShouhuo.setVisibility(View.VISIBLE);
            viewHolder.mFormgoodsPingjia.setVisibility(View.GONE);
            viewHolder.mFormgoodsFukuan.setVisibility(View.GONE);

            //查看订单
            viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ChakanGoodsActivity.class);
                    intent.putExtra("title",work_info.get(position).getOrder_name());
                    intent.putExtra("flag","待收货");
                    intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                    intent.putExtra("num", work_info.get(position).getNum());
                    intent.putExtra("money",work_info.get(position).getPrice());
                    intent.putExtra("money1",work_info.get(position).getOrder_amount());
                    intent.putExtra("time",work_info.get(position).getOrder_time());
                    intent.putExtra("userid",mUserid);
                    intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                    intent.putExtra("image",work_info.get(position).getPic());
                    intent.putExtra("mposition",position);
                    context.startActivity(intent);
                }
            });
        } else if (flag == 3) {
            viewHolder.mFormgoodsZhuangtai.setText("待评价");
            viewHolder.mFormgoodsPingjia.setVisibility(View.VISIBLE);
            viewHolder.mFormgoodsFukuan.setVisibility(View.GONE);
            viewHolder.mFormgoodsShouhuo.setVisibility(View.GONE);

            //评价
            viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ChakanGoodsActivity.class);
                    intent.putExtra("title",work_info.get(position).getOrder_name());
                    intent.putExtra("flag","待评价");
                    intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                    intent.putExtra("num", work_info.get(position).getNum());
                    intent.putExtra("money",work_info.get(position).getPrice());
                    intent.putExtra("money1",work_info.get(position).getOrder_amount());
                    intent.putExtra("time",work_info.get(position).getOrder_time());
                    intent.putExtra("userid",mUserid);
                    intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                    intent.putExtra("image",work_info.get(position).getPic());
                    intent.putExtra("mposition",position);

                    context.startActivity(intent);
                }
            });

        } else {
            viewHolder.mFormgoodsZhuangtai.setText("交易仲裁");
            viewHolder.mFormgoodsPingjia.setVisibility(View.GONE);
            viewHolder.mFormgoodsFukuan.setVisibility(View.GONE);
            viewHolder.mFormgoodsShouhuo.setVisibility(View.GONE);
            //查看订单
            viewHolder.mFormgoodsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ChakanGoodsActivity.class);
                    intent.putExtra("title",work_info.get(position).getOrder_name());
                    intent.putExtra("flag","交易仲裁");
                    intent.putExtra("name",work_info.get(position).getSeller_info().getUsername());
                    intent.putExtra("num", work_info.get(position).getNum());
                    intent.putExtra("money",work_info.get(position).getPrice());
                    intent.putExtra("money1",work_info.get(position).getOrder_amount());
                    intent.putExtra("time",work_info.get(position).getOrder_time());
                    intent.putExtra("userid",mUserid);
                    intent.putExtra("goodsid",work_info.get(position).getOrder_id());
                    intent.putExtra("image",work_info.get(position).getPic());
                    intent.putExtra("mposition",position);

                    context.startActivity(intent);
                }
            });
        }
        String order_status = work_info.get(position).getShow_status();
        mUserid = (String) SharedPrefsUtil.get(ECApplication.context, "UserUid", "");
        //订单id
        viewHolder.mFormgoodsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodsId = work_info.get(position).getOrder_id();
                //删除订单
                KLog.e("mUserid",mUserid);
                KLog.e("goods", mGoodsId);
              /*  if (callBack != null) {
                    callBack.clickOk();
                }*/
                shanchu(mUserid,mGoodsId,position);
            }
        });
        //付款
        viewHolder.mFormgoodsButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Toast.makeText(context, "付款", Toast.LENGTH_SHORT).show();
                KLog.e("mUserid",mUserid);
                KLog.e("goods", mGoodsId);
                Intent intent = new Intent(context, PayActivity.class);
                intent.putExtra("oid",work_info.get(position).getOrder_id());
                intent.putExtra("mflag", "2");
                intent.putExtra("money",work_info.get(position).getPrice());
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        //评价
        viewHolder.mFormgoodsButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodsId = work_info.get(position).getOrder_id();
                Intent intent =new Intent(context,PingjiaActivity.class);
                intent.putExtra("muserid",mUserid);
                intent.putExtra("goodsid",mGoodsId);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        //确实收货
        viewHolder.mFormgoodsButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodsId = work_info.get(position).getOrder_id();
                KLog.e("mUserid",mUserid);
                KLog.e("goods", mGoodsId);
                mDialog = new MaterialDialog(context).setTitle("提示").setMessage("您确定收到产品了吗?").setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        shouhuo(mUserid,mGoodsId,position);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.formgoods_name)
        TextView mFormgoodsName;
        @Bind(R.id.formgoods_zhuangtai)
        TextView mFormgoodsZhuangtai;
        @Bind(R.id.formgoods_iamge)
        ImageView mFormgoodsIamge;
        @Bind(R.id.formgoods_title)
        TextView mFormgoodsTitle;
        @Bind(R.id.formgoods_layout)
        LinearLayout mFormgoodsLayout;
        @Bind(R.id.formgoods_jiage)
        TextView mFormgoodsJiage;
        @Bind(R.id.formgoods_button1)
        Button mFormgoodsButton1;
        @Bind(R.id.formgoods_button2)
        Button mFormgoodsButton2;
        @Bind(R.id.formgoods_fukuan)
        LinearLayout mFormgoodsFukuan;
        @Bind(R.id.formgoods_button3)
        Button mFormgoodsButton3;
        @Bind(R.id.formgoods_pingjia)
        LinearLayout mFormgoodsPingjia;
        @Bind(R.id.toubiao_moshi)
        TextView mToubiaoMoshi;
        @Bind(R.id.formgoods_button4)
        Button mFormgoodsButton4;
        @Bind(R.id.formgoods_shouhuo)
        LinearLayout mFormgoodsShouhuo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public void setCallBack(EditDialogCallBack callBack) {
        this.callBack = callBack;
    }

    public interface EditDialogCallBack {
        void clickOk(int i);
    }

    /**
     * 收货接口
     */
    public void shouhuo(String uid, String oid, final int position){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("action","ConfirmReceipt");
        hashMap.put("uid",uid);
        hashMap.put("oid",oid);
        OkGo.post(RequestAddress.HOST+RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = mapForJson.get("code")+"";
                if (code.equals("888")){
                    Toast.makeText(context, "收货成功", Toast.LENGTH_SHORT).show();
                    work_info.remove(position);
                    KLog.e("work_info",work_info.size());
                    if (work_info.size()==0){
                        if (callBack != null) {
                            callBack.clickOk(0);
                        }
                    }
                    notifyDataSetChanged();
                    mDialog.dismiss();
                }
            }
        });
    }
    /**
     * 删除接口
     */
    public void shanchu(String uid, String oid, final int position){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("action","DelGoodsOrder");
        hashMap.put("uid",uid);
        hashMap.put("oid",oid);
        OkGo.post(RequestAddress.HOST+RequestAddress.GOODS).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                String code = mapForJson.get("code")+"";
                if (code.equals("888")){
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    work_info.remove(position);
                    notifyDataSetChanged();
                    if (work_info.size()==0){
                        if (callBack != null) {
                            callBack.clickOk(1);
                        }
                    }
                    mDialog.dismiss();
                }else {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
