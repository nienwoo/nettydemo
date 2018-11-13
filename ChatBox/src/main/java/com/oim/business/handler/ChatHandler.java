package com.oim.business.handler;

import java.util.List;

import com.oim.app.AppContext;
import com.oim.common.app.handler.SendHandler;
import com.oim.net.message.ChatMessage;
import com.oim.net.message.data.ChatData;

/**
 * @author XiaHui
 * @date 2015年3月16日 下午3:23:23
 */
public class ChatHandler extends SendHandler {

	public ChatHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 发送聊天信息给用户
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param receiveId
	 * @param sendId
	 * @param chatDataList
	 */
	public void sendUserChatMessage(String receiveId, String sendId, List<ChatData> chatDataList) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setController("300");
		chatMessage.setMethod("0001");
		chatMessage.setChatDataList(chatDataList);//聊天内容
		chatMessage.setSendId(sendId);//发送人的id
		chatMessage.setReceiveId(receiveId);//接受信息的用户id
		this.appContext.write(chatMessage);
	}

	/**
	 * 发送抖动窗口
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param receiveId
	 * @param sendId
	 */
	public void sendShake(String receiveId, String sendId) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setController("300");
		chatMessage.setMethod("0002");
		chatMessage.setSendId(sendId);
		chatMessage.setReceiveId(receiveId);
		this.appContext.write(chatMessage);
	}

	/**
	 * 发送群信息
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param receiveId
	 * @param sendId
	 * @param chatDataList
	 */
	public void sendGroupChatMessage(String receiveId, String sendId, List<ChatData> chatDataList) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setController("300");
		chatMessage.setMethod("0003");
		chatMessage.setChatDataList(chatDataList);
		chatMessage.setSendId(sendId);//发送人的id
		chatMessage.setReceiveId(receiveId);//接受信息的群id
		this.appContext.write(chatMessage);
	}
}
