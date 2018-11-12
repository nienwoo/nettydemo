package com.crazymakercircle.chat.client;

import com.crazymakercircle.chat.common.bean.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service("DemoClient")
public class DemoClient
{
    static final Logger LOGGER =
            LoggerFactory.getLogger(DemoClient.class);
    // 服务器ip地址
    @Value("${server.ip}")
    private String host;
    // 服务器端口
    @Value("${server.port}")
    private int port;

    // 通过nio方式来接收连接和处理连接
    private EventLoopGroup group = new NioEventLoopGroup();


    private Channel channel;

    /**
     * 唯一标记
     */
    private boolean initFalg = true;

    /**
     * 客户端的是Bootstrap，服务端的则是 ServerBootstrap。
     * 都是AbstractBootstrap的子类。
     **/
    public void run()
    {
        doConnect(new Bootstrap(), group);
    }

    /**
     * 重连
     */
    public void doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup)
    {
        ChannelFuture f = null;
        try
        {
            if (bootstrap != null)
            {
                bootstrap.group(eventLoopGroup);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
                bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
                bootstrap.remoteAddress(host, port);

                // 设置通道初始化
                bootstrap.handler(
                        new ChannelInitializer<SocketChannel>()
                        {
                            public void initChannel(SocketChannel ch) throws Exception
                            {

                            }
                        }
                );

                f = bootstrap.connect().addListener((ChannelFuture futureListener) ->
                {
                    final EventLoop eventLoop = futureListener.channel().eventLoop();
                    if (!futureListener.isSuccess())
                    {
                        LOGGER.info("与服务端断开连接!在10s之后准备尝试重连!");
                        eventLoop.schedule(() -> doConnect(new Bootstrap(), eventLoop), 10, TimeUnit.SECONDS);

                        initFalg = false;
                    }
                    else
                    {
                        initFalg = true;
                    }
                    if (initFalg)
                    {
                        LOGGER.info("EchoClient客户端连接成功!");

                        LOGGER.info(new Date() + ": 连接成功，启动控制台线程……");
                        channel = futureListener.channel();
                        startConsoleThread();
                    }

                });

                // 阻塞
                f.channel().closeFuture().sync();
            }
        } catch (Exception e)
        {
            LOGGER.info("客户端连接失败!" + e.getMessage());
        }

    }

    private void startConsoleThread()
    {
        new Thread(() ->
        {
            for (int i = 0; i < 1000; i++)
            {

                sendMsg("通过疯狂创客圈IM发消息,序号为:"+i);
            }

         }).start();
    }


    public  void sendMsg(String content)
    {
        LOGGER.info("发送消息");


        byte[] bytes = content.getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = channel.alloc().buffer();
        buffer.writeBytes(bytes);
        channel.writeAndFlush(buffer);
    }

}
