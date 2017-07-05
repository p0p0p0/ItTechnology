/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.yuntongxun.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.safetyhuge.chanlian.safetyhuge.IM.ui.chatting;

import android.content.Context;

import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天插件功能控制器
 * 
 * @author Jorstin Chan@容联•云通讯
 * @date 2014-12-10
 * @version 4.0
 */
public class AppPanelControl {

	private Context mContext;

	private static boolean isShowVoipCall = true;

	public int[] cap = new int[] { R.string.app_panel_pic,
			R.string.app_panel_tackpic };

	public int[] capVoip = new int[] { R.string.app_panel_pic,
			R.string.app_panel_tackpic, R.string.app_panel_file,
			R.string.app_panel_voice, R.string.app_panel_video, R.string.app_panel_read_after_fire, R.string.app_panel_location };

	/**
     *
     */
	public AppPanelControl() {
		mContext = CCPAppManager.getContext();
	}
	
	
	

	public static void setShowVoipCall(boolean isShowVoipCall) {
		AppPanelControl.isShowVoipCall = isShowVoipCall;
	}

   


	/**
	 *
	 * @return
	 */
	public List<Capability> getCapability() {
		List<Capability> capabilities = new ArrayList<Capability>();

		/*if (isShowVoipCall&&SDKCoreHelper.getInstance().isSupportMedia()) {
			for (int i = 0; i < capVoip.length; i++) {
				Capability capability = getCapability(capVoip[i]);
				capabilities.add(capabilities.size(), capability);
			}

		} else {*/
			for (int i = 0; i < cap.length; i++) {
				Capability capability = getCapability(cap[i]);
				capabilities.add(capabilities.size(), capability);
			}
		//}
		return capabilities;
	}

	/**
	 * @param resid
	 * @return
	 */
	private Capability getCapability(int resid) {
		Capability capability = null;
		switch (resid) {
		case R.string.app_panel_pic:
			capability = new Capability(getContext().getString(
					R.string.app_panel_pic), R.drawable.image_icon);
			break;
		case R.string.app_panel_tackpic:

			capability = new Capability(getContext().getString(
					R.string.app_panel_tackpic), R.drawable.photograph_icon);
			break;
		case R.string.app_panel_file:
			break;
		case R.string.app_panel_voice:
			break;
		case R.string.app_panel_video:
			break;
		case R.string.app_panel_read_after_fire:
			break;
		case R.string.app_panel_location:
			
			break;

		default:
			break;
		}
		capability.setId(resid);
		return capability;
	}

	/**
	 * @return
	 */
	private Context getContext() {
		if (mContext == null) {
			mContext = ECApplication.getInstance().getApplicationContext();
		}
		return mContext;
	}
}
