package com.oim.business.handler;

import com.oim.app.AppContext;
import com.oim.bean.UserCategory;
import com.oim.bean.UserCategoryMember;
import com.oim.common.app.handler.SendHandler;
import com.oim.net.message.UserMessage;
import com.only.net.data.action.DataBackAction;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年1月8日 下午8:22:48
 * @version 0.0.1
 */
public class UserCategoryHandler extends SendHandler {

	public UserCategoryHandler(AppContext appContext) {
		super(appContext);
	}

	/***
	 * 新增好友分组
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param name
	 * @param action
	 */
	public void addUserCategory(String name, DataBackAction action) {

		UserCategory userCategory = new UserCategory();
		userCategory.setName(name);

		UserMessage userMessage = new UserMessage();
		userMessage.setUserCategory(userCategory);
		userMessage.setController("202");
		userMessage.setMethod("0001");
		this.write(userMessage, action);
	}

	/**
	 * 添加好友
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param userCategoryId
	 * @param memberUserId
	 * @param remark
	 * @param action
	 */
	public void addUserCategoryMember(String userCategoryId, String memberUserId, String remark, DataBackAction action) {
		UserCategoryMember userCategoryMember = new UserCategoryMember();

		userCategoryMember.setUserCategoryId(userCategoryId);
		userCategoryMember.setMemberUserId(memberUserId);
		userCategoryMember.setRemark(remark);

		UserMessage userMessage = new UserMessage();

		userMessage.setUserCategoryMember(userCategoryMember);

		userMessage.setController("202");
		userMessage.setMethod("0002");
		this.write(userMessage, action);
	}
}
