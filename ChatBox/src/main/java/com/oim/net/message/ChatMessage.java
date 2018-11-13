package com.oim.net.message;

import java.util.List;

import com.oim.net.message.data.ChatData;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年4月16日 下午8:09:32
 * @version 0.0.1
 */
public class ChatMessage extends Message {

	private String sendId;
	private String receiveId;
	private List<ChatData> chatDataList;

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public List<ChatData> getChatDataList() {
		return chatDataList;
	}

	public void setChatDataList(List<ChatData> chatDataList) {
		this.chatDataList = chatDataList;
	}

}
