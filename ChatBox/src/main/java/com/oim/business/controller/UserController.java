package com.oim.business.controller;

import java.util.List;

import com.oim.app.AppContext;
import com.oim.bean.UserCategory;
import com.oim.bean.UserCategoryMember;
import com.oim.business.service.UserService;
import com.oim.common.annotation.ActionMapping;
import com.oim.common.annotation.MethodMapping;
import com.oim.common.app.controller.AbstractController;
import com.oim.net.message.UserMessage;
import com.oim.net.message.data.UserData;

/**
 * 描述：
 * 
 * @author 夏辉
 * @date 2014年6月14日 下午9:31:55
 * @version 0.0.1
 */

@ActionMapping(value = "201")
public class UserController extends AbstractController {

	public UserController(AppContext appContext) {
		super(appContext);
	}

	/***
	 * 接受服务器传来好友列表信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param userMessage
	 */
	@MethodMapping(value = "0004")
	public void setUserCategoryWithUserList(UserMessage userMessage) {
		List<UserCategory> userCategoryList = userMessage.getUserCategoryList();
		List<UserData> userDataList = userMessage.getUserDataList();
		List<UserCategoryMember> userCategoryMemberList = userMessage.getUserCategoryMemberList();
		UserService userService = appContext.getService(UserService.class);
		userService.setUserCategoryWithUserList(userCategoryList, userDataList, userCategoryMemberList);
	}

	/***
	 * 接受用户信息更新
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param userMessage
	 */
	@MethodMapping(value = "0008")
	public void updateUser(final UserMessage userMessage) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				UserService userService = appContext.getService(UserService.class);
				userService.updateUserData(userMessage.getUserData());
			}
		});
	}
}
