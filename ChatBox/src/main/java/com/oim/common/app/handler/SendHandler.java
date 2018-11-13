package com.oim.common.app.handler;

import com.oim.app.AppContext;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackAction;

/**
 * @author XiaHui
 * @date 2015年3月16日 下午3:07:15
 */
public abstract class SendHandler {
	
	protected AppContext appContext;

	public SendHandler(AppContext appContext) {
		this.appContext = appContext;
	}

	/**
	 * 发送信息
	 * 
	 * @param data
	 *            :信息
	 */
	public void write(Data data) {
		appContext.write(data);
	}

	/**
	 * 发送信息
	 * 
	 * @param data
	 *            :信息
	 * @param dataBackAction
	 *            :回调Action
	 */
	public void write(Data data, DataBackAction dataBackAction) {
		appContext.write(data, dataBackAction);
	}
}
