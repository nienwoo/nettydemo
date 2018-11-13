package com.oim.business.controller;

import com.oim.app.AppContext;
import com.oim.business.constant.VideoConstant;
import com.oim.business.handler.UserHandler;
import com.oim.business.manage.VideoManage;
import com.oim.common.annotation.ActionMapping;
import com.oim.common.annotation.MethodMapping;
import com.oim.common.app.controller.AbstractController;
import com.oim.common.box.UserDataBox;
import com.oim.common.box.UserDataTempBox;
import com.oim.net.message.UserMessage;
import com.oim.net.message.VideoMessage;
import com.oim.net.message.data.UserData;
import com.oim.net.message.data.VideoAddress;
import com.only.net.data.Data;
import com.only.net.data.action.DataBackAction;
import com.only.net.data.action.DataBackActionAdapter;

/**
 * 描述：
 * 
 * @author 夏辉
 * @date 2014年6月14日 下午9:31:55
 * @version 0.0.1
 */
@ActionMapping(value = "302")
public class VideoController extends AbstractController {

	public VideoController(AppContext appContext) {
		super(appContext);
	}

	@MethodMapping(value = "0003")
	public void getRequest(VideoMessage videoMessage) {
		String sendId = videoMessage.getSendId();
		UserData userData = UserDataBox.get(sendId);// 先从好友集合里面获取用户信息，如果用户不是好友，那么从服务器下载用户信息
		if (null == userData) {// 为null说明发送信息的不在好友列表，那么就要从服务器获取发送信息用户的信息了
			DataBackAction dataBackAction = new DataBackActionAdapter() {
				@Override
				public void backExecute(Data data) {
					if (data instanceof UserMessage) {// 获取用户信息成功后，显示其聊天信息
						UserMessage userMessage = (UserMessage) data;
						UserData userData = userMessage.getUserData();
						UserDataTempBox.put(userData.getId(), userData);
						showGetVideoFrame(userData);
					}
				}
			};
			UserHandler uh = this.appContext.getHandler(UserHandler.class);
			uh.getUserDataById(sendId, dataBackAction);
		} else {
			showGetVideoFrame(userData);
		}
	}

	private void showGetVideoFrame(UserData userData) {
		VideoManage vm = this.appContext.getManage(VideoManage.class);
		vm.showGetVideoFrame(userData);
	}

	@MethodMapping(value = "0004")
	public void getResponse(VideoMessage videoMessage) {
		String actionType = videoMessage.getActionType();
		String sendId = videoMessage.getSendId();
		VideoAddress videoAddress = videoMessage.getVideoAddress();
		VideoManage vm = this.appContext.getManage(VideoManage.class);
		if (VideoConstant.action_type_agree.equals(actionType)) {
			vm.getAgree(sendId, videoAddress);
		} else {
			vm.getShut(sendId);
		}
	}
}
