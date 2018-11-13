package com.oim.net.message;

import java.util.List;

import com.oim.bean.Group;
import com.oim.bean.GroupCategory;
import com.oim.bean.GroupCategoryMember;
import com.oim.bean.GroupMember;
import com.oim.net.message.data.UserData;
import com.oim.net.message.different.PageImpl;

/**
 * @description:
 * @author XiaHui
 * @date 2014年8月1日 下午5:03:04
 * @version 1.0.0
 */
public class GroupMessage extends Message {

	private String id;
	private int number;

	private PageImpl pageImpl;

	private Group group;
	private GroupCategory groupCategory;
	private GroupCategoryMember groupCategoryMember;
	private GroupMember groupMember;

	private List<Group> groupList;
	private List<GroupCategory> groupCategoryList;
	private List<GroupCategoryMember> groupCategoryMemberList;
	private List<GroupMember> groupMemberList;

	private List<UserData> userDataList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public PageImpl getPageImpl() {
		return pageImpl;
	}

	public void setPageImpl(PageImpl pageImpl) {
		this.pageImpl = pageImpl;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GroupCategory getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(GroupCategory groupCategory) {
		this.groupCategory = groupCategory;
	}

	public GroupCategoryMember getGroupCategoryMember() {
		return groupCategoryMember;
	}

	public void setGroupCategoryMember(GroupCategoryMember groupCategoryMember) {
		this.groupCategoryMember = groupCategoryMember;
	}

	public GroupMember getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(GroupMember groupMember) {
		this.groupMember = groupMember;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public List<GroupCategory> getGroupCategoryList() {
		return groupCategoryList;
	}

	public void setGroupCategoryList(List<GroupCategory> groupCategoryList) {
		this.groupCategoryList = groupCategoryList;
	}

	public List<GroupCategoryMember> getGroupCategoryMemberList() {
		return groupCategoryMemberList;
	}

	public void setGroupCategoryMemberList(List<GroupCategoryMember> groupCategoryMemberList) {
		this.groupCategoryMemberList = groupCategoryMemberList;
	}

	public List<GroupMember> getGroupMemberList() {
		return groupMemberList;
	}

	public void setGroupMemberList(List<GroupMember> groupMemberList) {
		this.groupMemberList = groupMemberList;
	}

	public List<UserData> getUserDataList() {
		return userDataList;
	}

	public void setUserDataList(List<UserData> userDataList) {
		this.userDataList = userDataList;
	}

}
