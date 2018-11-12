package com.crazymakercircle.chat.server;


import com.crazymakercircle.chat.common.bean.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ChatServerHandler")
public class ChatServerHandler extends ChannelInboundHandlerAdapter
{
    static final Logger LOGGER = LoggerFactory.getLogger(ChatServerHandler.class);

    /**
     * 建立连接时
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        LOGGER.info("CHANNEL_ACTIVE " + ctx.channel().remoteAddress());

    }
    /**
     * 收到消息
     * @param ctx
     * @param msg
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg)
    {


        if (msg != null && msg instanceof ProtoMsg.Message) {
            ProtoMsg.Message pkg = (ProtoMsg.Message) msg;
            // 聊天处理
            ProtoMsg.MessageRequest request = pkg.getMessageRequest();
            LOGGER.info("收到第"+  count++ +" 此消息: {}",  "  content="+request.getContent());

        }

    }
    private int count=0;

}
