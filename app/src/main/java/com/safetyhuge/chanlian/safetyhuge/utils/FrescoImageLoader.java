package com.safetyhuge.chanlian.safetyhuge.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by F-57 on 2017/1/13.
 */
public class FrescoImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //用fresco加载图片
      /*  Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
        Picasso.with(context).load((String) path).into(imageView);*/
        //Picasso 加载图片简单用法
        Picasso.with(context).load((String) path).fit().into(imageView);
}
}
