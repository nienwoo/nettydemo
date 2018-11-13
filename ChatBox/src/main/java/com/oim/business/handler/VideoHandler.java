package com.oim.business.handler;

import com.oim.app.AppContext;
import com.oim.common.app.handler.SendHandler;
import com.oim.net.message.VideoMessage;
import com.oim.net.message.data.VideoAddress;
import com.only.net.data.action.DataBackAction;

/**
 * @author XiaHui
 * @date 2015年3月16日 下午3:23:23
 */
public class VideoHandler extends SendHandler {

	public VideoHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 获取服务器的视频服务端口
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param dataBackAction
	 */
	public void getVideoServerPort(DataBackAction dataBackAction) {

		VideoAddress videoAddress = new VideoAddress();

		VideoMessage videoMessage = new VideoMessage();
		videoMessage.setController("302");
		videoMessage.setMethod("0001");
		videoMessage.setVideoAddress(videoAddress);
		this.write(videoMessage, dataBackAction);
	}

	/**
	 * 请求视频聊天
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 */
	public void requestVideo(String sendId, String receiveId) {
		VideoMessage videoMessage = new VideoMessage();
		videoMessage.setController("302");
		videoMessage.setMethod("0003");
		videoMessage.setSendId(sendId);
		videoMessage.setReceiveId(receiveId);
		this.write(videoMessage);
	}
	
	public void responseVideo(String sendId, String receiveId,String actionType) {
		VideoMessage videoMessage = new VideoMessage();
		videoMessage.setController("302");
		videoMessage.setMethod("0004");
		videoMessage.setSendId(sendId);
		videoMessage.setReceiveId(receiveId);
		videoMessage.setActionType(actionType);
		this.write(videoMessage);
	}
	
	
	public void getUserVideoAddress(String userId,DataBackAction dataBackAction) {
		VideoMessage videoMessage = new VideoMessage();
		videoMessage.setController("302");
		videoMessage.setMethod("0002");
		videoMessage.setReceiveId(userId);
		this.write(videoMessage,dataBackAction);
	}
}
