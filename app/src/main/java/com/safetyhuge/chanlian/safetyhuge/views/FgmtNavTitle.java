package com.safetyhuge.chanlian.safetyhuge.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.safetyhuge.chanlian.safetyhuge.R;

/**
 * 
 * @ClassName: FHandler
 * @Description: 上标题导航条
 * @author BSJ
 * @email:xxxx@xxx.xxx
 * @date 2016-2-15
 *
 */
public class FgmtNavTitle extends HBaseFgmt {
	private ImageView btn_left, btn_right;// 标题栏左右ImageView
	private TextView tv_title;// 标题
	private OnNavClikeEvent once;
	private TextView tv_right;
	private RelativeLayout mytitle;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v=inflater.inflate(R.layout.fgmt_title_view, container, false);
		initView(v);
		return v;
	}
	
	/**
	 * 绑定视图
	 * @param v
	 */
	private void initView(View v)
	{
		btn_left = (ImageView) v.findViewById(R.id.title_btn_left);
		btn_right = (ImageView) v.findViewById(R.id.title_btn_right);
		tv_title = (TextView) v.findViewById(R.id.title_tv_text);	
		tv_right = (TextView) v.findViewById(R.id.title_txt_right);
		mytitle = (RelativeLayout) v.findViewById(R.id.mytitle);
		btn_left.setOnClickListener(ocl);
		btn_right.setOnClickListener(ocl);
		tv_right.setOnClickListener(ocl);
	}
	/**
	 * 设置标题
	 * @param title
	 */
	public void setTitle(String title)
	{
		tv_title.setText(title);
	}
	/**
	 * 设置左按钮隐藏
	 */
	public void setLeftBtnDisplay(int display)
	{
		if(btn_left!=null){
		  btn_left.setVisibility(display);
		}
	}
	//隐藏标题栏
	public void hideTitle()
	{
		mytitle.setVisibility(View.GONE);
	}
	/**
	 * 设置右按钮隐藏
	 */
	public void setRightBtnDisplay(int display )
	{
		btn_right.setVisibility(display);
	}
	/**
	 * 设置左按钮内容
	 * @param txt
	 * @param res
	 */
	public void setLeftBtnContent(String txt,int res)
	{		
		btn_left.setBackgroundResource(res);
	}
	/**
	 * 设置右按钮内容
	 * @param txt
	 * @param res
	 */
	public void setRightBtnContent(String txt,int res)
	{
		btn_right.setBackgroundResource(res);
	}
	
	/***
	 * 设置右TextView 内容
	 */
	public void setRightTxVContent(String txt,int color){
		tv_right.setText(txt);	
		tv_right.setTextColor(color);
	}
	/**
	 * 设置右textView 可见
	 */
	
	public void setRightTxVDisplay(int display){
		tv_right.setVisibility(display);
	}
	/**
	 * 设置事件处理回调
	 * @param once
	 */
	public void setOnClikeEvent(OnNavClikeEvent once)
	{
		this.once=once;
	}
	/**
	 * 事件处理
	 */
	private OnClickListener ocl=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(null==once)
				return;
			if(v.getId()==R.id.title_btn_left)
			{
				once.onLeftBtnEvent(v);
			}
			else if(v.getId()==R.id.title_btn_right)
			{
				once.onRightBtnEvent(v);
			}else if(v.getId()==R.id.title_txt_right){
				once.onRightTxVEvent(v);
			}
			
		}
	};
	
	//-----------------------------------------事件接口

	public interface OnNavClikeEvent
	{
		public void onLeftBtnEvent(View v);
		public void onRightBtnEvent(View v);
		public void onRightTxVEvent(View v);
	}
}
  