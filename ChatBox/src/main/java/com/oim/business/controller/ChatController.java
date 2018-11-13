package com.oim.business.controller;

import com.oim.app.AppContext;
import com.oim.business.manage.ChatManage;
import com.oim.business.manage.PromptManage;
import com.oim.business.service.ChatService;
import com.oim.common.annotation.ActionMapping;
import com.oim.common.annotation.MethodMapping;
import com.oim.common.app.controller.AbstractController;
import com.oim.common.sound.SoundHandler;
import com.oim.net.message.ChatMessage;

/**
 * 描述：负责接受聊天相关业务的控制层
 * 
 * @author 夏辉
 * @date 2014年6月14日 下午9:31:55
 * @version 0.0.1
 */

@ActionMapping(value = "300")
public class ChatController extends AbstractController {

	public ChatController(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 收到用户聊天信息
	 * 
	 * @param chatMessage
	 */
	@MethodMapping(value = "0001")
	public void getUserChatMessage(ChatMessage chatMessage) {
		ChatService chatService = appContext.getService(ChatService.class);
		chatService.userChat(chatMessage);
	}

	/**
	 * 收到抖动窗口信息
	 * 
	 * @param chatMessage
	 */
	@MethodMapping(value = "0002")
	public void getShake(ChatMessage chatMessage) {
		PromptManage pm = this.appContext.getManage(PromptManage.class);
		pm.playSound(SoundHandler.sound_type_shake);//播放抖动声音
		ChatManage chatManage = appContext.getManage(ChatManage.class);
		String sendId = chatMessage.getSendId();
		chatManage.doShake(sendId);//执行抖动效果
	}

	/**
	 * 收到群信息
	 * 
	 * @param chatMessage
	 */
	@MethodMapping(value = "0003")
	public void getGroupChatMessage(ChatMessage chatMessage) {
		ChatService chatService = appContext.getService(ChatService.class);
		chatService.groupChat(chatMessage);
	}
}
