package com.oim.business.handler;

import com.oim.app.AppContext;
import com.oim.bean.User;
import com.oim.common.app.handler.SendHandler;
import com.oim.common.box.PersonalBox;
import com.oim.common.util.BeanUtils;
import com.oim.net.message.UserMessage;
import com.oim.net.message.data.UserData;
import com.oim.net.message.different.PageImpl;
import com.only.net.data.action.DataBackAction;
import com.only.net.data.action.DataBackActionAdapter;

/**
 * @author XiaHui
 * @date 2015年3月16日 下午3:23:23
 */
public class UserHandler extends SendHandler {

	public UserHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 发送获取好友信息
	 */
	public void getUserCategoryWithUserList() {
		UserMessage userMessage = new UserMessage();
		userMessage.setController("201");
		userMessage.setMethod("0004");
		this.write(userMessage);
	}

	/**
	 * 查询用户
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param userData
	 * @param page
	 * @param dataBackAction
	 */
	public void queryUserDataList(UserData userData, PageImpl page, DataBackActionAdapter dataBackAction) {
		UserMessage userMessage = new UserMessage();
		userMessage.setUserData(userData);
		userMessage.setPageImpl(page == null ? (new PageImpl()) : page);
		userMessage.setController("201");
		userMessage.setMethod("0006");
		this.write(userMessage, dataBackAction);
	}

	/**
	 * 用户状态发生变化时，给好友发送状态变化信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param status
	 */
	public void sendUpdateStatus(int status) {
		User sendUser = PersonalBox.get(User.class);
		UserData userData = new UserData();
		BeanUtils.copyProperties(userData, sendUser);
		userData.setStatus(status);
		UserMessage userMessage = new UserMessage();
		userMessage.setController("201");
		userMessage.setMethod("0008");
		userMessage.setUserData(userData);
		this.write(userMessage);
	}

	/***
	 * 获取用户详细信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param userId
	 * @param dataBackAction
	 */
	public void getUserDataById(String userId, DataBackAction dataBackAction) {
		UserMessage userMessage = new UserMessage();
		userMessage.setController("201");
		userMessage.setMethod("0005");
		userMessage.setId(userId);
		this.write(userMessage, dataBackAction);
	}
}
