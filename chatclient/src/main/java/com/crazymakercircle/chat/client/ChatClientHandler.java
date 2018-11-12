package com.crazymakercircle.chat.client;


import com.crazymakercircle.chat.common.session.Session;
import com.crazymakercircle.chat.common.bean.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("ChatClientHandler")
public class ChatClientHandler extends ChannelInboundHandlerAdapter
{


    static final Logger LOGGER = LoggerFactory.getLogger(ChatClientHandler.class);

    private AttributeKey<Session> sk = AttributeKey.valueOf("session");

    /**
     * 此方法会在连接到服务器后被调用
     */

    public void channelActive(ChannelHandlerContext ctx)
    {

        LOGGER.info(new Date() + "[疯狂创客圈IM] 登录成功");


    }

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        LOGGER.info(msg.toString());

        if (msg != null && msg instanceof ProtoMsg.Message) {
            ProtoMsg.Message pkg = (ProtoMsg.Message) msg;
            // 聊天处理
            ProtoMsg.MessageRequest request = pkg.getMessageRequest();
            LOGGER.info("chatMsg{}", "from=" + request.getFrom() + " , to="+request.getTo() + " , content="+request.getContent());

        }


    }

    /**
     * 捕捉到异常
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    {
        cause.printStackTrace();
        ctx.close();
    }




}
