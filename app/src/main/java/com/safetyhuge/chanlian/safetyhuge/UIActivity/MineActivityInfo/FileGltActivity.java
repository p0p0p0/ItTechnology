package com.safetyhuge.chanlian.safetyhuge.UIActivity.MineActivityInfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.adapter.MineAdapter.FilesAdapter;
import com.safetyhuge.chanlian.safetyhuge.baseview.MyListView;
import com.safetyhuge.chanlian.safetyhuge.utils.ActivitiesCollector;
import com.safetyhuge.chanlian.safetyhuge.views.HBaseAct;
import com.socks.library.KLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：王海宾 on 2017/4/18 0018 10:15
 * 邮箱：995696826@qq.com
 */
public class FileGltActivity extends HBaseAct {
    @Bind(R.id.filegl_list)
    MyListView mFileglList;
    @Bind(R.id.gouwuche_kong)
    LinearLayout mGouwucheKong;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesCollector.addActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filegl);
        ButterKnife.bind(this);
        intent = new Intent();
        String fileUrl = Environment.getExternalStorageDirectory() + "/itkjg/";
        List<String> stringList = GetVideoFileName(fileUrl);
        if (stringList != null) {
            if (!stringList.isEmpty()) {
                mFileglList.setVisibility(View.VISIBLE);
                mGouwucheKong.setVisibility(View.GONE);
                mFileglList.setAdapter(new FilesAdapter(stringList, fileUrl, this));
            }else{
                mFileglList.setVisibility(View.GONE);
                mGouwucheKong.setVisibility(View.VISIBLE);
            }
        }else{
            mFileglList.setVisibility(View.GONE);
            mGouwucheKong.setVisibility(View.VISIBLE);
        }
    }

    public void back_text_view(View view) {
        exitAct();
    }

    // 获取当前目录下所有的文件
    public static List<String> GetVideoFileName(String fileAbsolutePath) {
        List<String> vecFile = new ArrayList<>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();
        if (subFile != null) {
            for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
                // 判断是否为文件夹
                if (!subFile[iFileLength].isDirectory()) {
                    String filename = subFile[iFileLength].getName();
                    KLog.e("filename", filename + "");
                    vecFile.add(filename);
                }
            }
        }
        return vecFile;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesCollector.removeActivity(this);
    }
}
