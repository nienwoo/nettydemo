package com.crazymakercircle.chat.common.builder;

import com.crazymakercircle.chat.common.bean.ChatMsg;
import com.crazymakercircle.chat.common.bean.User;
import com.crazymakercircle.chat.common.bean.msg.ProtoMsg;

public class ClientMsgBuilder
{

    /**
     * 基础 Builder
      */
    private static class BaseBuilder
    {
        private  User user;
        protected ProtoMsg.HeadType type;
        private long seqId;

        public BaseBuilder(ProtoMsg.HeadType type,User user)
        {
            this.type = type;

            this.user=user;
        }

       /**
         * 构建消息 基础部分
         */
        public ProtoMsg.Message buildPartial()
        {
            seqId = genSeqId();

            ProtoMsg.Message.Builder mb = ProtoMsg.Message.newBuilder()
                    .setType(type)
                    .setSequence(seqId)
                    .setSessionId(user.getSessionId());
            return mb.buildPartial();
        }

    }


    /**
     * 登陆消息Builder
     */
    private static class LoginMsgBuilder extends BaseBuilder
    {
        private final User user;

        public LoginMsgBuilder(User user)
        {
            super(ProtoMsg.HeadType.LOGIN_REQUEST,user);
            this.user = user;
        }

        public ProtoMsg.Message build()
        {
            ProtoMsg.Message message = buildPartial();
            ProtoMsg.LoginRequest.Builder lb = ProtoMsg.LoginRequest.newBuilder()
                    .setDeviceId(user.getDevId())
                    .setPlatform(user.getPlatform().ordinal())
                    .setToken(user.getToken())
                    .setUid(user.getUid());
            return message.toBuilder().setLoginRequest(lb).build();
        }
    }



    /**
     * 聊天消息Builder
     */
    private static class ChatMsgBuilder extends BaseBuilder
    {


        private ChatMsg chatMsg;


        public ChatMsgBuilder(ChatMsg chatMsg,User user)
        {
            super(ProtoMsg.HeadType.LOGIN_REQUEST,user);
            this.chatMsg = chatMsg;
        }


        public ProtoMsg.Message build()
        {
            //基础部分
            ProtoMsg.Message message = buildPartial();
            //内嵌部分
            ProtoMsg.MessageRequest.Builder cb
                    = ProtoMsg.MessageRequest.newBuilder();

            chatMsg.fillMsg(cb);

            return message.toBuilder().setMessageRequest(cb).build();
        }
    }

    public static long genSeqId()
    {
        return System.currentTimeMillis();
    }


    public  static   ProtoMsg.Message buildLoginMsg(User user){
        LoginMsgBuilder builder=new LoginMsgBuilder(user);
        return  builder.build();

    }

    public  static   ProtoMsg.Message buildChatMsg(ChatMsg chatMsg,User user){
        ChatMsgBuilder builder=new ChatMsgBuilder(chatMsg, user);
        return  builder.build();

    }
}
