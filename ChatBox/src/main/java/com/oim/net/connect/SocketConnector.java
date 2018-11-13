/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.net.connect;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.oim.net.connect.codec.DataCodecFactory;
import com.only.net.connect.ConnectData;
import com.only.net.connect.Connector;
import com.only.net.data.Data;
import com.only.xml.XmlTools;

import org.apache.mina.core.service.IoHandlerAdapter;

/**
 * date 2012-9-14 17:40:54
 * 
 * @author XiaHui
 */
public class SocketConnector implements Connector {

	private IoSession session;
	private IoConnector ioConnector;
	private IoHandlerAdapter handler;
	private XmlTools xmlTools = new XmlTools();
	public SocketConnector(IoHandlerAdapter handler) {
		this.handler = handler;
		initConnector();
	}

	private void initConnector() {
		ioConnector = new NioSocketConnector();
		ioConnector.getFilterChain().addLast("mis", new ProtocolCodecFilter(new DataCodecFactory()));// 添加过滤器
		ioConnector.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		ioConnector.setHandler(handler);// 添加业务逻辑处理类
	}

	public boolean connect(ConnectData connectData) {
		boolean mark = true;
		try {
			ConnectFuture connect = ioConnector.connect(new InetSocketAddress(connectData.getHostAddress(), connectData.getPort()));// 创建连接
			connect.awaitUninterruptibly(connectData.getTimeOut());// 30000//
			session = connect.getSession();// 获取session
			mark = null != session;
		} catch (Exception e) {
			mark = false;
		}
		return mark;
	}

	public IoSession getSession() {
		return session;
	}

	public boolean isConnected() {
		return (null != session && session.isConnected() && !session.isClosing());
	}

	public void closeConnect() {
		if (null != session) {
			session.close(true);
			session = null;
		}
	}

	public boolean write(Data o) {
		boolean mark = isConnected();
		if (mark) {
			
			String xml = xmlTools.objectToXmlNode(o);
			session.write(xml);
			session.setAttribute(Data.class, o);
		}
		return mark;
	}
}
