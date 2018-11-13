package com.oim.business.handler;

import com.oim.app.AppContext;
import com.oim.bean.GroupCategory;
import com.oim.bean.GroupCategoryMember;
import com.oim.common.app.handler.SendHandler;
import com.oim.net.message.GroupMessage;
import com.only.net.data.action.DataBackAction;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年1月8日 下午8:22:48
 * @version 0.0.1
 */
public class GroupCategoryHandler extends SendHandler {

	public GroupCategoryHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 向服务器发送添加群分组信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param name
	 * @param action
	 */
	public void addGroupCategory(String name, DataBackAction action) {

		GroupCategory groupCategory = new GroupCategory();
		groupCategory.setName(name);

		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setGroupCategory(groupCategory);
		groupMessage.setController("204");
		groupMessage.setMethod("0001");
		this.write(groupMessage, action);
	}

	/**
	 * 向服务器发送添加加入群信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param groupCategoryId
	 * @param groupId
	 * @param remark
	 * @param action
	 */
	public void addGroupCategoryMember(String groupCategoryId, String groupId, String remark, DataBackAction action) {
		GroupCategoryMember groupCategoryMember = new GroupCategoryMember();

		groupCategoryMember.setGroupCategoryId(groupCategoryId);//要加入的群分配的分组。
		groupCategoryMember.setGroupId(groupId);//群id
		groupCategoryMember.setRemark(remark);//群的备注名

		GroupMessage groupMessage = new GroupMessage();

		groupMessage.setGroupCategoryMember(groupCategoryMember);

		groupMessage.setController("204");
		groupMessage.setMethod("0002");
		this.write(groupMessage, action);
	}
}
