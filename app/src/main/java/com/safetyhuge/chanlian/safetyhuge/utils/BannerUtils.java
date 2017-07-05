package com.safetyhuge.chanlian.safetyhuge.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager自动轮播
 * Created by XuYanZzhao on 2016/9/26.
 */
public class BannerUtils {
	List<ImageView> list = new ArrayList<>();//控件集合
	private ArrayList<String> imgPaths;//链接集合
	private ViewPager mViewPager;
	private LinearLayout linearLayout;//小圆点的布局
	private Activity context;
	private ArrayList<String> gotoUrl;//跳转链接集合
	private boolean isStart = true;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int item = mViewPager.getCurrentItem();
			switch (msg.what) {
				//接收消息，滚动ViewPager
				case 100:
					if (item >= imgPaths.size() - 1) {
						item = 0;
						mViewPager.setCurrentItem(item);
					} else {
						item++;
						mViewPager.setCurrentItem(item);
					}
					break;
			}
		}
	};


	public BannerUtils(Activity contexts, ArrayList<String> imgPaths, ArrayList<String> gotoUrl,
					   ViewPager mViewPager, LinearLayout linearLayout) {
		this.context = contexts;
		this.imgPaths = imgPaths;
		this.mViewPager = mViewPager;
		this.linearLayout = linearLayout;
//		this.gotoUrl = gotoUrl;
		initBannerNoSelect();//初始化
		scrollViewPager();//无限循环
	}

//	不带跳转的轮播
	private void initBannerNoSelect() {
		//添加数据
		imgPaths.add(imgPaths.get(0));//头加最后
		imgPaths.add(0, imgPaths.get(imgPaths.size() - 1));//最后加头
//		gotoUrl.add(gotoUrl.get(0));
//		gotoUrl.add(0, gotoUrl.get(gotoUrl.size() - 1));
		//清空控件
		linearLayout.removeAllViews();
		//初始化控件
		for (int i = 0; i < imgPaths.size(); i++) {
			ImageView imageView = new ImageView(context);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			//下载图片
			Picasso.with(context).load(imgPaths.get(i)).placeholder(R.mipmap.img_swiper)
					.error(R.mipmap.img_swiper).fit().into(imageView);
			list.add(imageView);

			//获取圆点图片的数据源
			View view = new View(context);
			view.setBackgroundResource(R.mipmap.home_circle_gray);
			//给View设置宽高
			//给View设置宽高
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					DPUtils.dp2px(context, 10), DPUtils.dp2px(context, 10));
			params.setMargins(2, 0, 2, 0);
			view.setLayoutParams(params);
			//将view添加到linearLayout容器中
			linearLayout.addView(view);
		}
		//第一个圆点选中
		linearLayout.getChildAt(1).setBackgroundResource(R.mipmap.home_circle_white);
		//隐藏0和最后圆点
		linearLayout.getChildAt(0).setVisibility(View.GONE);
		linearLayout.getChildAt(imgPaths.size() - 1).setVisibility(View.GONE);
		//设置适配器
		mViewPager.setAdapter(new BannerPageAdapter());
		mViewPager.setCurrentItem(1, false);
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset,
									   int positionOffsetPixels) {
				//控制轮播
				if (position == 0 && positionOffset == 0.0) {//当滑动0的时候
					mViewPager.setCurrentItem(imgPaths.size() - 2, false);//跳到倒数第二个
				} else if (position == imgPaths.size() - 1 && positionOffset == 0.0) {//当滑动最后一个的时候
					mViewPager.setCurrentItem(1, false);//跳到第一个
				}
			}

			@Override
			public void onPageSelected(int position) {
				//控制圆点
				if (position != 0 && position != imgPaths.size() - 1) {
					for (int i = 0; i < imgPaths.size(); i++) {
						linearLayout.getChildAt(i).setBackgroundResource(R.mipmap.home_circle_gray);
					}
					linearLayout.getChildAt(position)
							.setBackgroundResource(R.mipmap.home_circle_white);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}

	//开启线程循环发送消息
	private void scrollViewPager() {
		new Thread() {
			@Override
			public void run() {
				try {
					while (isStart) {
						sleep(4000);
						handler.sendEmptyMessage(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	//停止轮播调用的方法
	public void stop() {
		isStart = false;
	}

	//适配器
	class BannerPageAdapter extends PagerAdapter implements View.OnClickListener {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			list.get(position).setOnClickListener(this);
			return list.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(list.get(position));
		}

		@Override
		public void onClick(View v) {
//			if ("http".equals(gotoUrl.get(mViewPager.getCurrentItem()).substring(0, 4))) {
//				Intent intent = new Intent(context, ViolationQueryActivity.class);
//				intent.putExtra("gotourl", gotoUrl.get(mViewPager.getCurrentItem()));
//				context.startActivity(intent);
//			}
		}
	}
}
