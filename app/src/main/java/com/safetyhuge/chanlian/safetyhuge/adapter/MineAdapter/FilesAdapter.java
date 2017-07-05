package com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo.FileGltActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.utils.IntentUtils;
import com.socks.library.KLog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 作者：王海宾 on 2017/4/26 0026 19:00
 * 邮箱：995696826@qq.com
 */
public class FilesAdapter extends BaseAdapter {
    List<String> list;
    String fileUrl;
    FileGltActivity fileGltActivity;

    public FilesAdapter(List<String> list, String fileUrl, FileGltActivity fileGltActivity) {
        this.list = list;
        this.fileUrl = fileUrl;
        this.fileGltActivity = fileGltActivity;
    }

    @Override
    public int getCount() {
        return list.size();
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
            convertView = View.inflate(ECApplication.context, R.layout.item_files, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String url = list.get(position);
        final String str = fileUrl + url;
        KLog.e("ssss",str);
        viewHolder.mFileName.setText(list.get(position));
        viewHolder.mFileLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileGltActivity.startActivity(IntentUtils.openFile(str));
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.file_name)
        TextView mFileName;
        @Bind(R.id.file_look)
        Button mFileLook;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
