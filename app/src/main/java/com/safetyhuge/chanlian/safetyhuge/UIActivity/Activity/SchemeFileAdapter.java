package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.IntentUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/4/28 0028 15:29
 * 邮箱：995696826@qq.com
 */
public class SchemeFileAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> scheme;
    String uid1;
    private String mS;
    private String mMUrlss;
    String userid;

    KProgressHUD     mProgressHUD;
    public SchemeFileAdapter(Context context, ArrayList<String> scheme, String userid, String uid1) {
        this.scheme = scheme;
        this.context = context;
        this.uid1 = uid1;
        this.userid = userid;
             mProgressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("下载中....")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }

    @Override
    public int getCount() {
        return scheme.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_file, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String url = (String) get(context, uid1+"s"+position, "");
        String urls = (String) get(context, uid1+"ss"+position, "");
        if (url != ""&&urls!="") {
            if (url.equals(urls)){
                KLog.e("urls", urls);
                KLog.e("url", url);
                viewHolder.mFilePath.setVisibility(View.GONE);
                viewHolder.mfile_look.setVisibility(View.VISIBLE);
            }
        }


        ArrayList<String> mArrayList = new ArrayList<>();
        mS = scheme.get(position);
        final String str11 = mS.substring(mS.indexOf("uploads/") + 19);
        KLog.e("ssss", mS);
        KLog.e("ssss111", str11);
        viewHolder.mFileName.setText(str11);
        viewHolder.mFilePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressHUD.show();
                NetWork(userid,uid1);
                String url = scheme.get(position);
                KLog.e("url",url);
                OkGo.post(RequestAddress.IMAGE1 + "/" + url).execute(new FileCallback(Environment.getExternalStorageDirectory() + "/itkjg/"
                        ,str11) {
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        KLog.e("file", file);
                        File mFile1 = file;
                        mProgressHUD.dismiss();
                        SharedPrefsUtil.put(context, uid1+position, mFile1+"");
                        KLog.e("position",position);
                        KLog.e("mFile1",mFile1);
                        String str = file.toString();
                        String str1 = str.substring(str.indexOf("download/") + 9);
                        KLog.e("name", str1);
                        SharedPrefsUtil.put(context, uid1+"s"+position, str1);
                        SharedPrefsUtil.put(context, uid1+"ss"+position, str1);
                        viewHolder.mFilePath.setVisibility(View.GONE);
                        viewHolder.mfile_look.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        KLog.e("progress",progress);
                        if (progress==1.0){
                            Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
                            viewHolder.mFilePath.setVisibility(View.GONE);
                            viewHolder.mfile_look.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
        viewHolder.mfile_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMUrlss = (String) get(context, uid1+position, "");
                if (mMUrlss !=null){
                    KLog.e("mUrlss", mMUrlss.toString());
                    context.startActivity(IntentUtils.openFile(mMUrlss));
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.file_name)
        TextView mFileName;
        @Bind(R.id.file_path)
        Button mFilePath;
        @Bind(R.id.file_look)
        Button mfile_look;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public void NetWork(String uid,String sid){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("action","DownFile");
        hashMap.put("uid",uid);
        hashMap.put("sid",sid);
        OkGo.post(RequestAddress.HOST+RequestAddress.SCJC).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });
    }

}
