package com.oim.ui.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import com.oim.app.AppContext;
import com.oim.bean.Group;
import com.oim.bean.GroupCategory;
import com.oim.bean.UserCategory;
import com.oim.business.handler.GroupHandler;
import com.oim.business.handler.UserHandler;
import com.oim.business.manage.ListManage;
import com.oim.common.app.view.AbstractView;
import com.oim.common.box.GroupBox;
import com.oim.common.box.HeadImageIconBox;
import com.oim.common.box.UserDataBox;
import com.oim.net.message.GroupMessage;
import com.oim.net.message.UserMessage;
import com.oim.net.message.data.UserData;
import com.oim.net.message.different.PageImpl;
import com.oim.ui.FindFrame;
import com.oim.ui.component.WaitingPanel.WaitingType;
import com.oim.ui.component.event.ExecuteAction;
import com.oim.ui.find.ItemHead;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackActionAdapter;
import com.only.net.data.action.ExecutionType;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class FindView extends AbstractView {

	FindFrame findFrame = new FindFrame();
	ExecuteAction userExecuteAction;
	ExecuteAction groupExecuteAction;
	PageImpl userPage;
	PageImpl groupPage;
	UserData userData;
	Group group;

	public FindView(AppContext appContext) {
		super(appContext);
		initEvent();
	}

	private void initEvent() {
		userExecuteAction = new ExecuteAction() {

			@Override
			public <T, E> E execute(T value) {
				openAddView((ItemHead) value);
				return null;
			}
		};
		groupExecuteAction = new ExecuteAction() {

			@Override
			public <T, E> E execute(T value) {
				openJoinGroupView((ItemHead) value);
				return null;
			}
		};
		findFrame.addUserPageChangeAction(new ExecuteAction() {

			@Override
			public <T, E> E execute(T value) {
				if (value instanceof Integer) {
					userPageChange((Integer) value);
				}
				return null;
			}
		});

		findFrame.addQueryUserAction(new ExecuteAction() {

			@Override
			public <T, E> E execute(T value) {
				if (value instanceof UserData) {
					queryUserDataList((UserData) value);
				}
				return null;
			}
		});

		findFrame.addGroupPageChangeAction(new ExecuteAction() {

			@Override
			public <T, E> E execute(T value) {
				if (value instanceof Integer) {
					groupPageChange((Integer) value);
				}
				return null;
			}
		});

		findFrame.addQueryGroupAction(new ExecuteAction() {

			@Override
			public <T, E> E execute(T value) {
				if (value instanceof Group) {
					queryGroupList((Group) value);
				}
				return null;
			}
		});
	}

	public void setVisible(boolean visible) {
		findFrame.setVisible(visible);
	}

	public boolean isShowing() {
		return findFrame.isShowing();

	}

	public void initData() {
		findFrame.initData();
	}

	private void userPageChange(int number) {
		if (null == userPage) {
			userPage = new PageImpl();
			userPage.setPageSize(16);
		}
		userPage.setPageNumber(number);
		queryUserDataList(userData, userPage);
	}

	private void groupPageChange(int number) {
		if (null == groupPage) {
			groupPage = new PageImpl();
			groupPage.setPageSize(16);
		}
		groupPage.setPageNumber(number);
		queryGroupList(group, userPage);
	}

	public void queryUserDataList(UserData userData) {
		this.userData = userData;
		if (null == userPage) {
			userPage = new PageImpl();
			userPage.setPageSize(16);
		}
		userPage.setPageNumber(1);
		queryUserDataList(userData, userPage);
	}

	public void queryGroupList(Group group) {
		this.group = group;
		if (null == groupPage) {
			groupPage = new PageImpl();
			groupPage.setPageSize(16);
		}
		groupPage.setPageNumber(1);
		queryGroupList(group, groupPage);
	}

	public void queryUserDataList(UserData userData, PageImpl page) {

		findFrame.showUserWaiting(true, WaitingType.waiting);
		DataBackActionAdapter dataBackAction = new DataBackActionAdapter() {

			@Override
			public void execute(Data data, ExecutionType executionType) {
				if (ExecutionType.back == executionType) {
					findFrame.showUserWaiting(false, WaitingType.waiting);
				} else {
					findFrame.showUserWaiting(true, WaitingType.result);
				}
			}

			@Override
			public void backExecute(Data data) {
				if (data instanceof UserMessage) {
					UserMessage message = (UserMessage) data;
					setUserDataList(message.getUserDataList(), message.getPageImpl());
				}
			}
		};

		UserHandler uh = this.appContext.getHandler(UserHandler.class);
		uh.queryUserDataList(userData, page, dataBackAction);
	}

	public void queryGroupList(Group group, PageImpl page) {

		findFrame.showGroupWaiting(true, WaitingType.waiting);
		DataBackActionAdapter dataBackAction = new DataBackActionAdapter() {

			@Override
			public void execute(Data data, ExecutionType executionType) {
				if (ExecutionType.back == executionType) {
					findFrame.showGroupWaiting(false, WaitingType.waiting);
				} else {
					findFrame.showGroupWaiting(true, WaitingType.result);
				}
			}

			@Override
			public void backExecute(Data data) {
				if (data instanceof GroupMessage) {
					GroupMessage message = (GroupMessage) data;
					setGroupList(message.getGroupList(), message.getPageImpl());
				}
			}
		};

		GroupHandler gh = this.appContext.getHandler(GroupHandler.class);
		gh.queryGroupList(group, page, dataBackAction);
	}

	public void setUserDataList(List<UserData> userDataList, PageImpl page) {
		List<ItemHead> itemHeadList = new ArrayList<ItemHead>();
		if (null != userDataList) {
			for (UserData userData : userDataList) {
				ItemHead itemHead = new ItemHead();
				itemHead.add(userExecuteAction);
				itemHead.addAttribute(UserData.class.getName(), userData);
				itemHead.setHeadIcon(HeadImageIconBox.getUserHeadImageIcon(userData.getHead(), 40, 40));
				itemHead.setName(userData.getNickname());
				itemHead.setShowText(userData.getSignature());
				itemHead.setPreferredSize(new Dimension(185, 55));
				itemHeadList.add(itemHead);
			}
		}
		findFrame.setUserPage(page.getPageNumber(), page.getTotalPage());
		findFrame.setUserItemList(itemHeadList);
	}

	public void setGroupList(List<Group> groupList, PageImpl page) {
		List<ItemHead> itemHeadList = new ArrayList<ItemHead>();
		if (null != groupList) {
			for (Group group : groupList) {
				ItemHead itemHead = new ItemHead();
				itemHead.add(groupExecuteAction);
				itemHead.addAttribute(Group.class, group);
				itemHead.setHeadIcon(HeadImageIconBox.getGroupHeadImageIcon(group.getHead(), 40, 40));
				// itemHead.setHeadIcon(ImageUtil.getRoundedCornerIcon("Resources/Images/Default/GroupHead/"
				// + group.getDefaultHead() + ".png", 40, 40, 40, 40));
				itemHead.setName(group.getName());
				itemHead.setShowText(group.getIntroduce());
				itemHead.setPreferredSize(new Dimension(185, 55));
				itemHeadList.add(itemHead);
			}
		}
		findFrame.setGroupPage(page.getPageNumber(), page.getTotalPage());
		findFrame.setGroupItemList(itemHeadList);
	}

	public void openAddView(ItemHead itemHead) {
		UserData user = itemHead.getAttribute(UserData.class.getName());
		ListManage listManage = this.appContext.getManage(ListManage.class);
		if (UserDataBox.get(user.getId()) != null) {
			findFrame.showPromptMessage(user.getNickname() + "已经是你的好友！");
			return;
		}
		AddView addView = appContext.getSingleView(AddView.class);
		List<UserCategory> userCategoryList = listManage.getUserCategoryList();
		addView.set(user, userCategoryList);
		addView.setVisible(true);
	}

	public void openJoinGroupView(ItemHead itemHead) {
		Group g = itemHead.getAttribute(Group.class);
		ListManage listManage = this.appContext.getManage(ListManage.class);
		if (GroupBox.get(g.getId()) != null) {
			findFrame.showPromptMessage("你已经加入" + g.getName());
			return;
		}
		AddView addView = appContext.getSingleView(AddView.class);
		List<GroupCategory> groupCategoryList = listManage.getGroupCategoryList();
		addView.set(g, groupCategoryList);
		addView.setVisible(true);
	}
}
