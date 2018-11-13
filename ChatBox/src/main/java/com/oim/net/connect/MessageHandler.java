package com.oim.net.connect;

import com.only.net.data.Data;

/**
 * @Author: XiaHui
 * @Date: 2016年1月4日
 * @ModifyUser: XiaHui
 * @ModifyDate: 2016年1月4日
 */
public interface MessageHandler {

	public void setBackTime(long time);

	public void back(Data data);

	public void addExceptionData(Data data);
}
