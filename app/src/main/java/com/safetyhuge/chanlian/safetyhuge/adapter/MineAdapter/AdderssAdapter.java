package com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.MineBean.AddressBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.AdddizhiActivity;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.ArrdessActivity;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.ButtonUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：王海宾 on 2017/4/25 0025 21:32
 * 邮箱：995696826@qq.com
 */
public class AdderssAdapter extends BaseAdapter {
    List<AddressBean.DataBean> data;
    ArrdessActivity arrdessActivity;
    EditDialogCallBack callBack;
    String flag;
    MaterialDialog mMaterialDialog;
    KProgressHUD mProgressHUD;

    public AdderssAdapter(List<AddressBean.DataBean> data, ArrdessActivity arrdessActivity, String flag) {
        this.data = data;
        this.arrdessActivity = arrdessActivity;
        this.flag = flag;
        mProgressHUD = KProgressHUD.create(arrdessActivity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(arrdessActivity, R.layout.item_address, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mAddressName.setText(data.get(position).getY_name());
        viewHolder.mAddressPhone.setText(data.get(position).getY_phone());
        viewHolder.mAddressInfo.setText(data.get(position).getY_address() + data.get(position).getY_addresses());
        viewHolder.mAddressBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(arrdessActivity, AdddizhiActivity.class);
                intent.putExtra("flags", "1");
                intent.putExtra("name", data.get(position).getY_name());
                intent.putExtra("phone", data.get(position).getY_phone());
                intent.putExtra("address", data.get(position).getY_address());
                intent.putExtra("addresser", data.get(position).getY_addresses());
                intent.putExtra("id", data.get(position).getY_id());
                arrdessActivity.startActivity(intent);
            }
        });
        if (flag.equals("0")) {
            viewHolder.mAddressLayout1.setVisibility(View.VISIBLE);
            viewHolder.mAddressSeting.setClickable(false);
        } else {
            viewHolder.mAddressLayout1.setVisibility(View.GONE);
            viewHolder.mAddressSeting.setClickable(true);
            viewHolder.mAddressSeting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPrefsUtil.put(ECApplication.context, "address_name", data.get(position).getY_name());
                    SharedPrefsUtil.put(ECApplication.context, "address_phone", data.get(position).getY_phone());
                    SharedPrefsUtil.put(ECApplication.context, "address_addresses", data.get(position).getY_address() + data.get(position).getY_addresses());
                    arrdessActivity.finish();
                }
            });
        }
        viewHolder.mAddressDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ButtonUtils.isFastDoubleClick()){
                    return;
                }else{
                    mMaterialDialog = new MaterialDialog(arrdessActivity)
                            .setTitle("提示")
                            .setMessage("您确定已完成帮助吗?")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mProgressHUD.show();
                                    mMaterialDialog.dismiss();
                                    Adderss(data.get(position).getY_id(), position);
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            });

                    mMaterialDialog.show();

                    KLog.e(position);
                }
            }
        });
        return convertView;
    }

    private void Adderss(String p, final int position) {
        KLog.e(p + "+++++");
        OkGo.post(RequestAddress.HOST + RequestAddress.SCDZ).params("y_id", p + "").execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> mapForJson = JSONUtil.getMapForJson(s);
                Object message = mapForJson.get("message");
                if (message.equals("取消成功")) {
                    mProgressHUD.dismiss();
                    Toast.makeText(arrdessActivity, "删除成功", Toast.LENGTH_SHORT).show();
                    data.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(arrdessActivity, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    static class ViewHolder {
        @Bind(R.id.address_name)
        TextView mAddressName;
        @Bind(R.id.address_phone)
        TextView mAddressPhone;
        @Bind(R.id.address_info)
        TextView mAddressInfo;
        @Bind(R.id.address_seting)
        LinearLayout mAddressSeting;
        @Bind(R.id.address_shanchu)
        Button mAddressShanchu;
        @Bind(R.id.address_layout)
        RelativeLayout mAddressLayout;
        @Bind(R.id.address_layout1)
        RelativeLayout mAddressLayout1;
        @Bind(R.id.address_del)
        LinearLayout mAddressDel;
        @Bind(R.id.address_bianji)
        LinearLayout mAddressBianji;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setCallBack(EditDialogCallBack callBack) {
        this.callBack = callBack;
    }

    public interface EditDialogCallBack {
        void clickOk();
    }
}
