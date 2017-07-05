package com.safetyhuge.chanlian.safetyhuge.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.safetyhuge.chanlian.safetyhuge.Bean.ProjectminuteBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.IntentUtils;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil.get;

/**
 * 作者：王海宾 on 2017/4/12 0012 19:04
 * 邮箱：995696826@qq.com
 */
public class FileAdapter extends BaseAdapter {
    Context context;
    List<ProjectminuteBean.DataBean.FileBean> fileBeen;
    private boolean mB = true;
    private ArrayList<String> mArrayList;
    private File mFile1;
    private File mUrlss;
    private String mMUrlss;
    String uid;
    public FileAdapter(Context context, List<ProjectminuteBean.DataBean.FileBean> fileBeen,String uid) {
        this.context = context;
        this.fileBeen = fileBeen;
        this.uid = uid;
    }


    @Override
    public int getCount() {
        return fileBeen.size();
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

        KLog.e("mMUrlss",mMUrlss);
        String url = (String) get(context, uid+"s"+position, "");
        String urls = (String) get(context, uid+"ss"+position, "");
        if (url != ""&&urls!="") {
            if (url.equals(urls)){
                KLog.e("urls", urls);
                KLog.e("url", url);
                viewHolder.mFilePath.setVisibility(View.GONE);
                viewHolder.mfile_look.setVisibility(View.VISIBLE);
            }
        }


        mArrayList = new ArrayList<>();
        viewHolder.mFileName.setText(fileBeen.get(position).getFile_name());
        viewHolder.mFilePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = fileBeen.get(position).getFile_path();
                KLog.e("url",url);
                OkGo.post(RequestAddress.IMAGE1 + "/" + url).execute(new FileCallback(Environment.getExternalStorageDirectory() + "/itkjg/"
                        ,fileBeen.get(position).getFile_name()) {
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        KLog.e("file", file);
                        mFile1 = file;
                        SharedPrefsUtil.put(context, uid+position, mFile1+"");
                        KLog.e("position",position);
                        KLog.e("mFile1",mFile1);
                        String str = file.toString();
                        String str1 = str.substring(str.indexOf("download/") + 9);
                        KLog.e("name", str1);
                        SharedPrefsUtil.put(context, uid+"s"+position, str1);
                        SharedPrefsUtil.put(context, uid+"ss"+position, str1);
                      viewHolder.mFilePath.setVisibility(View.GONE);
                        viewHolder.mfile_look.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        KLog.e("progress",progress);
                        if (progress==1.0){
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
                mMUrlss = (String) get(context, uid+position, "");
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

    public void NewWork(String url) {
        OkGo.post(RequestAddress.IMAGE1 + "/" + url).execute(new FileCallback() {
            @Override
            public void onSuccess(File file, Call call, Response response) {
                KLog.e("file", file);
                String str = file.toString();
                String str1 = str.substring(str.indexOf("download/") + 9);
                KLog.e("name", str1);
                SharedPrefsUtil.put(context, "file", str1);
                mB = false;
            }
        });
    }
}
