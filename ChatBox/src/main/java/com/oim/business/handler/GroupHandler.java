package com.oim.business.handler;

import com.oim.app.AppContext;
import com.oim.bean.Group;
import com.oim.common.app.handler.SendHandler;
import com.oim.net.message.GroupMessage;
import com.oim.net.message.different.PageImpl;
import com.only.net.data.action.DataBackAction;
import com.only.net.data.action.DataBackActionAdapter;

/**
 * @author XiaHui
 * @date 2015年3月16日 下午3:23:23
 */
public class GroupHandler extends SendHandler {

	public GroupHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 发送获取群列表请求
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 */
	public void getGroupCategoryWithGroupList() {
		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setController("203");
		groupMessage.setMethod("0004");
		this.write(groupMessage);
	}

	/**
	 * 发送新建群信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param group
	 * @param action
	 */
	public void addGroup(Group group, DataBackAction action) {
		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setController("203");
		groupMessage.setMethod("0008");
		groupMessage.setGroup(group);
		this.write(groupMessage, action);
	}

	/***
	 * 发送修改群信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param group
	 * @param action
	 */
	public void updateGroup(Group group, DataBackAction action) {
		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setController("203");
		groupMessage.setMethod("0009");
		groupMessage.setGroup(group);
		this.write(groupMessage, action);
	}

	/**
	 * 发送查询群消息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param group
	 * @param page
	 * @param dataBackAction
	 */
	public void queryGroupList(Group group, PageImpl page, DataBackActionAdapter dataBackAction) {
		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setController("203");
		groupMessage.setMethod("0004");
		groupMessage.setGroup(group);
		groupMessage.setPageImpl(page == null ? (new PageImpl()) : page);
		groupMessage.setController("203");
		groupMessage.setMethod("0006");
		this.write(groupMessage, dataBackAction);
	}

	/**
	 * 发送获取群成员信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param groupId
	 * @param action
	 */
	public void getGroupMemberListWithUserDataList(String groupId, DataBackAction action) {
		GroupMessage groupMessage = new GroupMessage();
		groupMessage.setId(groupId);
		groupMessage.setController("203");
		groupMessage.setMethod("0010");
		this.write(groupMessage, action);
	}
}
