package com.crazymakercircle.chat.common.builder;

import com.crazymakercircle.chat.common.ProtoInstant;
import com.crazymakercircle.chat.common.bean.msg.ProtoMsg;
import org.apache.commons.lang.StringUtils;

public class ServerMsgBuilder
{

	public static ProtoMsg.Message buildChatResponse(long seqId, ProtoInstant.ResultCodeEnum en){
		ProtoMsg.Message.Builder mb = ProtoMsg.Message.newBuilder()
				.setType(ProtoMsg.HeadType.MESSAGE_RESPONSE)  //设置消息类型
		        .setSequence(seqId);                 //设置应答流水，与请求对应
		ProtoMsg.MessageResponse.Builder rb = ProtoMsg.MessageResponse.newBuilder()
				.setCode(en.getCode())
				.setInfo(en.getDesc())
				.setExpose(1);
		mb.setMessageResponse(rb.build());
		return mb.build();
	}


	/**
	 *登录应答 应答消息protobuf
	 */
	public static ProtoMsg.Message buildLoginResponce(ProtoInstant.ResultCodeEnum en, long seqId, String sessionId){
		ProtoMsg.Message.Builder mb = ProtoMsg.Message.newBuilder()
				.setType(ProtoMsg.HeadType.MESSAGE_RESPONSE)  //设置消息类型
				.setSequence(seqId);  //设置应答流水，与请求对应

		ProtoMsg.LoginResponse.Builder rb = ProtoMsg.LoginResponse.newBuilder()
				.setCode(en.getCode())
				.setInfo(en.getDesc())
				.setExpose(1);

		if(StringUtils.isNotEmpty(sessionId)){
			//返回当前用户的chatId,后面聊天时候的会话ID
			rb.setSessionId(sessionId);
		}
		mb.setLoginResponse(rb.build());
		return mb.build();
	}



}
