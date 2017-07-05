package com.safetyhuge.chanlian.safetyhuge.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.safetyhuge.chanlian.safetyhuge.R;


/**
 * Created by F57 on 2016/9/26 0026.
 */
public class RatioImageView extends ImageView {
    private float ratio = 0f;//宽高比
    public RatioImageView(Context context) {
        this(context,null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        //获取自定义属性的值
      /*  String namespace = "http://schemas.android.com/apk/res/res-auto";
        ratio = attrs.getAttributeFloatValue(namespace, "ratio", 0f);*/
        //工作中推荐使用这一种
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        ratio = array.getFloat(R.styleable.RatioImageView_ratio,0f);
        array.recycle();
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //设置宽高比
    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1.获取当前ImageView的宽度，
        //通过widthMeasureSpec来取出当前imageView的宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //2.根据宽高比，计算对应的高度
        if(ratio!=0){
            //获取对应的高度
            float height = width/ratio;
            //3.将高度设置给ImageView
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
