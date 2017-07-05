package com.safetyhuge.chanlian.safetyhuge.views;
/**
 * 
 * @ClassName: TPresenter
 * @Description: TODO所有呈现者父类，用于处理视图数据业务。解耦视图与数据的联系
 * @author BSJ
 * @email:xxxx@xxx.xxx
 * @date 2016-2-19
 *
 */
public class TPresenter {
	public IViewUpdate iViewUpdate;
	public TPresenter()
	{
		
	}
	public TPresenter(IViewUpdate iViewUpdate)
	{
		this.iViewUpdate= iViewUpdate;
	}
	/**
	 * 设置视图更新接口
	 * @param iViewUpdate
	 */
	public void setITViewUpdate(IViewUpdate iViewUpdate)
	{
		this.iViewUpdate= iViewUpdate;
	}
}
  