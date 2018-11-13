package com.oim.business.service;

import java.util.List;

import javax.swing.ImageIcon;

import com.oim.app.AppContext;
import com.oim.bean.Group;
import com.oim.business.handler.UserHandler;
import com.oim.business.manage.ChatManage;
import com.oim.business.manage.PromptManage;
import com.oim.common.action.CallAction;
import com.oim.common.app.service.Service;
import com.oim.common.box.GroupBox;
import com.oim.common.box.HeadImageIconBox;
import com.oim.common.box.UserDataBox;
import com.oim.common.box.UserDataTempBox;
import com.oim.common.sound.SoundHandler;
import com.oim.net.message.ChatMessage;
import com.oim.net.message.UserMessage;
import com.oim.net.message.data.ChatData;
import com.oim.net.message.data.UserData;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackAction;
import com.only.net.data.action.DataBackActionAdapter;

/**
 * 描述：
 * 
 * @author 夏辉
 * @date 2014年3月31日 上午11:45:15 version 0.0.1
 */
public class ChatService extends Service {

	public ChatService(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 处理接受到用户的聊天信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param chatMessage
	 */
	public void userChat(ChatMessage chatMessage) {
		String sendId = chatMessage.getSendId();
		final List<ChatData> chatDataList = chatMessage.getChatDataList();
		UserData userData = UserDataBox.get(sendId);// 先从好友集合里面获取用户信息，如果用户不是好友，那么从服务器下载用户信息
		if (null == userData) {// 为null说明发送信息的不在好友列表，那么就要从服务器获取发送信息用户的信息了
			DataBackAction dataBackAction = new DataBackActionAdapter() {

				@Override
				public void backExecute(Data data) {
					if (data instanceof UserMessage) {// 获取用户信息成功后，显示其聊天信息
						UserMessage userMessage = (UserMessage) data;
						UserData userData = userMessage.getUserData();
						UserDataTempBox.put(userData.getId(), userData);
						showChatData(userData, chatDataList);
					}
				}
			};
			UserHandler uh = this.appContext.getHandler(UserHandler.class);
			uh.getUserDataById(sendId, dataBackAction);
		} else {
			showChatData(userData, chatDataList);
		}
	}

	/**
	 * 显示聊天信息
	 * 
	 * @param userData
	 * @param chatDataList
	 */
	private void showChatData(final UserData userData, List<ChatData> chatDataList) {
		ChatManage chatManage = this.appContext.getManage(ChatManage.class);
		boolean mark = chatManage.userChat(userData, chatDataList);
		if (!mark) {// 如果正显示的聊天窗口并不是发送信息的用户，那么就要跳动其头像、以及闪动系统托盘

			CallAction callAction = new CallAction() {

				@Override
				public void execute() {
					java.awt.EventQueue.invokeLater(new Runnable() {

						@Override
						public void run() {
							ChatManage chatManage = appContext.getManage(ChatManage.class);
							chatManage.showCahtFrame(userData);
						}
					});
				}
			};

			ImageIcon imageIcon = HeadImageIconBox.getUserHeadImageIcon(userData.getHead(), 16);
			PromptManage pm = this.appContext.getManage(PromptManage.class);
			pm.put(userData.getId(), imageIcon, callAction);
			pm.showUserHeadPulse(userData.getId(), true);// 跳动用户头像
			pm.playSound(SoundHandler.sound_type_message);// 播放消息声音
		}
	}

	/**
	 * 接受群星信息，同用户信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param chatMessage
	 */
	public void groupChat(ChatMessage chatMessage) {
		String groupId = chatMessage.getReceiveId();
		final List<ChatData> chatDataList = chatMessage.getChatDataList();
		final Group group = GroupBox.get(groupId);// 先从好友集合里面获取用户信息，如果用户不是好友，那么从服务器下载用户信息
		if (null != group) {
			String sendId = chatMessage.getSendId();
			UserData userData = UserDataTempBox.get(sendId);

			if (null == userData) {
				DataBackAction dataBackAction = new DataBackActionAdapter() {

					@Override
					public void backExecute(Data data) {
						if (data instanceof UserMessage) {
							UserMessage userMessage = (UserMessage) data;
							UserData userData = userMessage.getUserData();
							UserDataTempBox.put(userData.getId(), userData);
							showChatData(group, userData, chatDataList);

						}
					}
				};
				UserHandler uh = this.appContext.getHandler(UserHandler.class);
				uh.getUserDataById(sendId, dataBackAction);
			} else {
				showChatData(group, userData, chatDataList);
			}
		}
	}

	private void showChatData(final Group group, UserData userData, List<ChatData> chatDataList) {
		ChatManage chatManage = this.appContext.getManage(ChatManage.class);
		boolean mark = chatManage.groupChat(group, userData, chatDataList);
		if (!mark) {

			CallAction callAction = new CallAction() {

				@Override
				public void execute() {
					java.awt.EventQueue.invokeLater(new Runnable() {

						@Override
						public void run() {
							ChatManage chatManage = appContext.getManage(ChatManage.class);
							chatManage.showCahtFrame(group);
						}
					});
				}
			};

			ImageIcon imageIcon = HeadImageIconBox.getGroupHeadImageIcon(group.getHead(), 16);
			PromptManage pm = this.appContext.getManage(PromptManage.class);
			pm.put(group.getId(), imageIcon, callAction);
			pm.showGroupHeadPulse(group.getId(), true);
			pm.playSound(SoundHandler.sound_type_message);
		}
	}
}
