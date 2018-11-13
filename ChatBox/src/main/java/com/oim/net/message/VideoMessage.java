package com.oim.net.message;

import com.oim.net.message.data.VideoAddress;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年1月13日 下午9:07:43
 * @version 0.0.1
 */
public class VideoMessage extends Message {

	private String sendId;
	private String receiveId;
	private VideoAddress videoAddress;
	private String actionType;

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

	public VideoAddress getVideoAddress() {
		return videoAddress;
	}

	public void setVideoAddress(VideoAddress videoAddress) {
		this.videoAddress = videoAddress;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
