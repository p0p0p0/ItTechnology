package com.safetyhuge.chanlian.safetyhuge.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safetyhuge.chanlian.safetyhuge.R;

/**
 * Created by Administrator on 2016/9/7 0007.
 * 本应用中所有Fragment的基类
 */
public abstract class HBaseFgmt extends Fragment {
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return super.onCreateView(inflater, container, savedInstanceState);
        }

/**
 *
 * @Title: startActAnim
 * @Description: 启动其他活动时带动画
 * @param @param intent 参数
 * @return void 返回类型
 * @throws
 */
public void startActAnim(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.act_dync_in_from_right,R.anim.act_dync_out_to_left);
        }
/**
 *
 * @Title: startActAnim
 * @Description: 启动其他活动时带动画
 * @param @param intent 参数
 * @return void 返回类型
 * @throws
 */
public void startActAnim(Intent intent,int inanim,int outanim) {
        startActivity(intent);
        getActivity().overridePendingTransition(inanim,outanim);
        }
/**
 * 启动activity并返回结�?
 * @param intent
 * @param requestCode
 */
public void startActivityForResultAnim(Intent intent,int requestCode)
        {
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.act_dync_in_from_right,R.anim.act_dync_out_to_left);
        }
/**
 * 启动activity并返回结�?
 * @param intent
 * @param requestCode
 */
public void startActivityForResultAnim(Intent intent,int requestCode,int inanim,int outanim)
        {
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(inanim,outanim);
        }

/**
 * @Title: exitAct
 * @Description: �?出activity方法�?,处理善后数据.子类可重�?
 * @return void 返回类型
 * @throws
 */
public void exitAct() {
        getActivity().finish();
//		overridePendingTransition(in, out);
        getActivity().overridePendingTransition(R.anim.act_dync_in_from_left,
        R.anim.act_dync_out_to_right);
        }
/**
 * @Title: exitAct
 * @Description: �?出activity方法�?,处理善后数据.子类可重�?
 * @return void 返回类型
 * @throws
 */
public void exitAct(int inanim,int outanim) {
        getActivity().finish();
//		overridePendingTransition(in, out);
        getActivity().overridePendingTransition(inanim,outanim);
        }
}
