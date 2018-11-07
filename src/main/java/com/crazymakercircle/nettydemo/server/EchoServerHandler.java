package com.crazymakercircle.nettydemo.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Service;

@Service("echoServerHandler")
public class EchoServerHandler extends ChannelInboundHandlerAdapter
{
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("server received data :" + msg);
        ctx.write(msg);//写回数据，
    }
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //flush掉所有写回的数据
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE); //当flush完成后关闭channel
    }
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        //捕捉异常信息
        cause.printStackTrace();
        //出现异常时关闭channel
        ctx.close();
    }
}
