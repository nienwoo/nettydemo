package com.crazymakercircle.chat.server;

import com.crazymakercircle.chat.common.codec.PackageSpliter;
import com.crazymakercircle.chat.common.codec.ProtobufDecoder;
import com.crazymakercircle.chat.common.codec.ProtobufEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service("ChatServer")
public class ChatServer
{
    static final Logger LOGGER = LoggerFactory.getLogger(ChatServer.class);
    // 服务器端口
    @Value("${server.port}")
    private int port;
    // 通过nio方式来接收连接和处理连接
    private static EventLoopGroup boss = new NioEventLoopGroup();
    private static EventLoopGroup work = new NioEventLoopGroup();

    // 启动引导器
    private static ServerBootstrap b = new ServerBootstrap();
    @Autowired
    private ChatServerHandler serverHandler;

    public void run()
    {
        try
        {
            b.group(boss, work);
            // 设置nio类型的channel
            b.channel(NioServerSocketChannel.class);
            // 设置监听端口
            b.localAddress(new InetSocketAddress(port));
            // 设置通道初始化
            b.childHandler(new ChannelInitializer<SocketChannel>()
            {
                //有连接到达时会创建一个channel
                protected void initChannel(SocketChannel ch) throws Exception
                {
                    ch.pipeline().addLast(new PackageSpliter());
                    ch.pipeline().addLast(new ProtobufDecoder());
                    ch.pipeline().addLast(new ProtobufEncoder());
                    // pipeline管理channel中的Handler
                    // 在channel队列中添加一个handler来处理业务
                    ch.pipeline().addLast("serverHandler", serverHandler);
                }
            });
            // 配置完成，开始绑定server
            // 通过调用sync同步方法阻塞直到绑定成功

            ChannelFuture f = b.bind().sync();
            LOGGER.info(ChatServer.class.getName() +
                    " started and listen on " + f.channel().localAddress());

            // 监听服务器关闭事件
            // 应用程序会一直等待，直到channel关闭
            f.channel().closeFuture().sync();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }

}
