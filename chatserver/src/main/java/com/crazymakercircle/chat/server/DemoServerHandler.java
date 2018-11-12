package com.crazymakercircle.chat.server;


import com.crazymakercircle.chat.common.bean.msg.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service("DemoServerHandler")
public class DemoServerHandler extends ChannelInboundHandlerAdapter
{
    static final Logger LOGGER = LoggerFactory.getLogger(DemoServerHandler.class);

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

        ByteBuf byteBuf = (ByteBuf) msg;

        LOGGER.info(" 服务端读到数据 ：{} ", count++ +" 次 " + byteBuf.toString(Charset.forName("utf-8")));


    }
    private int count=0;

}
