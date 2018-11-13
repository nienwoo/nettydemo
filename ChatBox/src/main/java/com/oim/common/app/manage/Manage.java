package com.oim.common.app.manage;

import com.oim.app.AppContext;
import com.oim.common.app.view.AbstractView;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackAction;

/**
 * 当接受到服务信息时：数据流向设计为  net->controller->service->view<br>
 * 当用户从ui操作向服务器发送数据为view->manage->handler->net
 * @author XiaHui
 * @date 2015年4月12日 下午1:44:00
 * @version 0.0.1
 */
public abstract class Manage {
	
	protected AppContext appContext;

	public Manage(AppContext appContext) {
		this.appContext = appContext;
	}

	public <T> T getSingleView(Class<? extends AbstractView> classType) {
		return appContext.getSingleView(classType);
	}

	/**
	 * 发送信息
	 * 
	 * @param data :信息
	 */
	public void write(Data data) {
		appContext.write(data);
	}

	/**
	 * 发送信息
	 * 
	 * @param data :信息
	 * @param dataBackAction :回调Action
	 */
	public void write(Data data, DataBackAction dataBackAction) {
		appContext.write(data, dataBackAction);
	}
}
